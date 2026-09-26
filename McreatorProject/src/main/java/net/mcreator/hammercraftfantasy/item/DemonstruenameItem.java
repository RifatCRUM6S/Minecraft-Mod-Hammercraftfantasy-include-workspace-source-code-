package net.mcreator.hammercraftfantasy.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import net.mcreator.hammercraftfantasy.init.HammercraftfantasyModEntities;
import net.mcreator.hammercraftfantasy.entity.DemonthrallEntity;

public class DemonstruenameItem extends Item {
    public DemonstruenameItem() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemstack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack itemstack, LivingEntity livingEntity) {
        return 60; // 蓄力 3 秒
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
        entity.startUsingItem(hand);
        return InteractionResultHolder.consume(entity.getItemInHand(hand));
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int count) {
        if (level.isClientSide()) {
            double posX = livingEntity.getX();
            double posY = livingEntity.getY();
            double posZ = livingEntity.getZ();

            // 生成火焰粒子
            for (int i = 0; i < 2; i++) {
                double offsetX = (level.random.nextDouble() - 0.5D) * 1.2D;
                double offsetY = level.random.nextDouble() * 1.8D;
                double offsetZ = (level.random.nextDouble() - 0.5D) * 1.2D;

                level.addParticle(
                    ParticleTypes.FLAME,
                    posX + offsetX,
                    posY + offsetY,
                    posZ + offsetZ,
                    0.0D, 0.02D, 0.0D
                );
            }

            // 生成附魔台符文粒子
            for (int i = 0; i < 3; i++) {
                double angle = level.random.nextDouble() * Math.PI * 2;
                double distance = 1.0D + level.random.nextDouble() * 0.8D;

                double pX = posX + Math.cos(angle) * distance;
                double pY = posY + level.random.nextDouble() * 1.8D;
                double pZ = posZ + Math.sin(angle) * distance;

                double speedX = (posX - pX) * 0.5D;
                double speedY = (posY + 1.0D - pY) * 0.5D;
                double speedZ = (posZ - pZ) * 0.5D;

                level.addParticle(
                    ParticleTypes.ENCHANT,
                    pX, pY, pZ,
                    speedX, speedY, speedZ
                );
            }
        }

        super.onUseTick(level, livingEntity, stack, count);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        ItemStack resultStack = super.finishUsingItem(stack, level, livingEntity);

        if (!level.isClientSide() && livingEntity instanceof Player player) {
            BlockPos spawnPos = findSafeSpawnPos(level, player.blockPosition());

            // 播放恶魔降临的轰鸣音效（凋零降临音效）
            level.playSound(
                null,
                spawnPos,
                SoundEvents.WITHER_SPAWN, 
                SoundSource.HOSTILE,
                1.0F,
                0.8F
            );

            // 40% 概率召唤失败，生成反噬恶魔 Bloodletter
            if (level.random.nextFloat() < 0.40F) {
                var bloodletter = HammercraftfantasyModEntities.BLOODLETTER.get().create(level);
                if (bloodletter != null) {
                    bloodletter.moveTo(
                        spawnPos.getX() + 0.5D,
                        spawnPos.getY(),
                        spawnPos.getZ() + 0.5D,
                        player.getYRot(),
                        0.0F
                    );

                    if (level instanceof ServerLevel serverLevel) {
                        bloodletter.finalizeSpawn(
                            serverLevel,
                            serverLevel.getCurrentDifficultyAt(spawnPos),
                            MobSpawnType.MOB_SUMMONED,
                            null
                        );
                    }

                    // 不驯服，直接锁定玩家为攻击目标
                    bloodletter.setTarget(player);
                    level.addFreshEntity(bloodletter);

                    // 红色反噬提示
                    player.sendSystemMessage(Component.literal("§cThe ritual spiraled out of control! An untamed demon turns upon you!"));
                }
            } else {
                // 60% 概率召唤成功，生成驯服的 Demonthrall
                DemonthrallEntity demonthrall = HammercraftfantasyModEntities.DEMONTHRALL.get().create(level);
                if (demonthrall != null) {
                    demonthrall.moveTo(
                        spawnPos.getX() + 0.5D, 
                        spawnPos.getY(), 
                        spawnPos.getZ() + 0.5D, 
                        player.getYRot(), 
                        0.0F
                    );

                    if (level instanceof ServerLevel serverLevel) {
                        demonthrall.finalizeSpawn(
                            serverLevel, 
                            serverLevel.getCurrentDifficultyAt(spawnPos), 
                            MobSpawnType.MOB_SUMMONED, 
                            null
                        );
                    }

                    demonthrall.tame(player);
                    demonthrall.setPersistenceRequired();

                    level.broadcastEntityEvent(demonthrall, (byte) 7);

                    level.addFreshEntity(demonthrall);

                    // 绿色成功提示
                    player.sendSystemMessage(Component.literal("§aThe true name has been spoken, bound to your will!"));
                }
            }

            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }

        return resultStack;
    }

    private BlockPos findSafeSpawnPos(Level level, BlockPos center) {
        for (int r = 1; r <= 3; r++) {
            for (int x = -r; x <= r; x++) {
                for (int z = -r; z <= r; z++) {
                    for (int y = -1; y <= 1; y++) {
                        BlockPos checkPos = center.offset(x, y, z);
                        if (isSafeLocation(level, checkPos)) {
                            return checkPos;
                        }
                    }
                }
            }
        }
        return center;
    }

    private boolean isSafeLocation(Level level, BlockPos pos) {
        BlockPos feet = pos;
        BlockPos head = pos.above();
        BlockPos ground = pos.below();

        return level.getBlockState(ground).isSolidRender(level, ground)
                && level.isEmptyBlock(feet)
                && level.isEmptyBlock(head);
    }
}