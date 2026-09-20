package net.mcreator.hammercraftfantasy.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameType;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;

public class Steedofslaanesh_rightclickProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
        if (entity == null || sourceentity == null)
            return;

        // 【核心拦截 1】：客户端直接退出，所有逻辑（包括扣钻石和下马）全部由服务端说了算！
        if (world.isClientSide()) {
            return;
        }

        // 【核心拦截 2】：副手触发直接退出！彻底封死“一次吃 2 个钻石”的通道
        if (sourceentity instanceof LivingEntity _liv && _liv.getUsedItemHand() == InteractionHand.OFF_HAND) {
            return;
        }

        TamableAnimal tamable = (TamableAnimal) entity;

        // ==========================================
        //              1. 未驯服状态逻辑
        // ==========================================
        if (!tamable.isTame()) {

            // 判断主手是否为钻石，且玩家正在潜行（Shift）
            ItemStack mainHandItem = (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY);
            
            if (mainHandItem.getItem() == Items.DIAMOND && sourceentity.isShiftKeyDown()) {

                // 精准扣除 1 个钻石（仅生存/冒险模式）
                if (getEntityGameType(sourceentity) != GameType.CREATIVE) {
                    if (sourceentity instanceof Player _player) {
                        _player.getInventory().clearOrCountMatchingItems(p -> p.getItem() == Items.DIAMOND, 1, _player.inventoryMenu.getCraftSlots());
                    }
                }

                // 20% 概率驯服
                if (Math.random() < 0.20) {
                    if (sourceentity instanceof Player _owner) {
                        tamable.tame(_owner);

                        // 驯服成功：立刻清空仇恨，防止追打主人
                        if (entity instanceof Mob _mob) {
                            _mob.setTarget(null);
                            _mob.setLastHurtByMob(null);
                        }
                    }
                    if (world instanceof ServerLevel _serverLevel) {
                        _serverLevel.sendParticles(ParticleTypes.HEART, x, y + 1.0, z, 7, 0.3, 0.5, 0.3, 0.02);
                    }
                } else {
                    // 驯服失败：产生烟雾并强制拉开距离（踢下马）
                    if (world instanceof ServerLevel _serverLevel) {
                        _serverLevel.sendParticles(ParticleTypes.SMOKE, x, y + 1.0, z, 5, 0.2, 0.3, 0.2, 0.02);
                    }
                    kickPlayerOff(entity, sourceentity);
                }
            } 
            // 没拿钻石 / 没按 Shift 直接右键：直接强行踢下马
            else {
                kickPlayerOff(entity, sourceentity);
            }
        } 
        // ==========================================
        //              2. 已驯服状态逻辑
        // ==========================================
        else {
            if (tamable.isOwnedBy((LivingEntity) sourceentity)) {
                // 如果玩家已经在背上，按下 Shift 正常允许下马
                if (sourceentity.isPassenger() && sourceentity.getVehicle() == entity) {
                    return;
                }

                // 潜行右键：切换坐下 / 站立
                if (sourceentity.isShiftKeyDown()) {
                    tamable.setOrderedToSit(!tamable.isOrderedToSit());
                    if (sourceentity.isPassenger()) {
                        sourceentity.stopRiding();
                    }
                } 
                // 正常右键：允许上马骑乘
                else {
                    sourceentity.startRiding(entity);
                }
            }
        }
    }

    /**
     * 硬性切断原版骑乘：强制 stopRiding 并把玩家向后瞬移 0.5 格
     */
    private static void kickPlayerOff(Entity entity, Entity sourceentity) {
        sourceentity.stopRiding();
        
        // 计算马匹后方的坐标，直接传送玩家脱离骑乘判定区
        double lookX = entity.getLookAngle().x;
        double lookZ = entity.getLookAngle().z;
        
        if (Math.abs(lookX) < 0.01 && Math.abs(lookZ) < 0.01) {
            lookX = -Math.sin(Math.toRadians(entity.getYRot()));
            lookZ = Math.cos(Math.toRadians(entity.getYRot()));
        }

        // 向后传送 0.6 格，强行破开原版的 startRiding 锁定
        double targetX = sourceentity.getX() - lookX * 0.6;
        double targetZ = sourceentity.getZ() - lookZ * 0.6;
        
        sourceentity.teleportTo(targetX, sourceentity.getY(), targetZ);
        sourceentity.setDeltaMovement(new Vec3(-lookX * 0.4, 0.2, -lookZ * 0.4));
        sourceentity.hurtMarked = true;
    }

    private static GameType getEntityGameType(Entity entity) {
        if (entity instanceof ServerPlayer serverPlayer) {
            return serverPlayer.gameMode.getGameModeForPlayer();
        }
        return null;
    }
}