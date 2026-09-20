package net.mcreator.hammercraftfantasy.procedures;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

public class Altarofsuffering_chargeProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null)
            return;

        BlockPos pos = BlockPos.containing(x, y, z);
        BlockState blockState = world.getBlockState(pos);

        // 1. 检查玩家主手是否持有充能物品（下界之星）
        ItemStack mainHandItem = entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY;
        if (mainHandItem.getItem() == Items.NETHER_STAR) {

            // 2. 读取当前 BlockState 的 chargelevel 属性（充能等级 0~4）
            int currentCharge = 0;
            if (blockState.getBlock().getStateDefinition().getProperty("chargelevel") instanceof IntegerProperty _integerProp) {
                currentCharge = blockState.getValue(_integerProp);
            }

            // 3. 如果充能未满（小于 4）
            if (currentCharge < 4) {
                int nextCharge = currentCharge + 1;

                // A. 更新 BlockState 属性（改变方块贴图/外观）
                if (blockState.getBlock().getStateDefinition().getProperty("chargelevel") instanceof IntegerProperty _integerProp) {
                    world.setBlock(pos, blockState.setValue(_integerProp, nextCharge), 3);
                }

                // B. 更新 Block Entity NBT 标签（后台保存数据）
                BlockEntity _ent = world.getBlockEntity(pos);
                if (_ent != null) {
                    _ent.getPersistentData().putDouble("chargelevel", nextCharge);
                    if (world instanceof Level _level)
                        _level.sendBlockUpdated(pos, _ent.getBlockState(), _ent.getBlockState(), 3);
                }

                // C. 扣除玩家主手 1 个下界之星
                if (entity instanceof Player _player) {
                    if (!_player.getAbilities().instabuild) { // 非创造模式才扣除
                        mainHandItem.shrink(1);
                    }
                }

                // D. 播放充能音效与紫水晶/末影粒子特效
                if (world instanceof Level _level) {
                    if (!_level.isClientSide()) {
                        _level.playSound(null, pos, SoundEvents.END_PORTAL_FRAME_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                    }
                }
                if (world instanceof ServerLevel _level) {
                    _level.sendParticles(ParticleTypes.REVERSE_PORTAL, x + 0.5, y + 1.2, z + 0.5, 20, 0.2, 0.2, 0.2, 0.05);
                }

                // E. 如果刚刚充满（达到 4 次），向玩家发送提示信息
                if (nextCharge == 4 && entity instanceof Player _player && !world.isClientSide()) {
                    _player.sendSystemMessage(Component.literal("§cThe Altar of Agony is fully charged! Sacrifice your soulmate to begin the ritual..."));
                }
            }
        }
    }
}