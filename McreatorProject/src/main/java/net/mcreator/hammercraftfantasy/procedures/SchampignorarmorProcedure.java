package net.mcreator.hammercraftfantasy.procedures;

import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;

import net.mcreator.hammercraftfantasy.init.HammercraftfantasyModItems;

@EventBusSubscriber
public class SchampignorarmorProcedure {

    @SubscribeEvent
    public static void onEntityAttacked(LivingIncomingDamageEvent event) {
        Entity attacker = event.getSource().getEntity();
        Entity directEntity = event.getSource().getDirectEntity();

        // 1. 早退判定：必须是玩家近战
        if (!(attacker instanceof Player player) || directEntity != player) {
            return;
        }

        // 2. 检查色孽神选全套装备
        if (player.getItemBySlot(EquipmentSlot.FEET).getItem() == HammercraftfantasyModItems.SCHAMP_BOOTS.get()
                && player.getItemBySlot(EquipmentSlot.LEGS).getItem() == HammercraftfantasyModItems.SCHAMP_LEGGINGS.get()
                && player.getItemBySlot(EquipmentSlot.CHEST).getItem() == HammercraftfantasyModItems.SCHAMP_CHESTPLATE.get()
                && player.getItemBySlot(EquipmentSlot.HEAD).getItem() == HammercraftfantasyModItems.SCHAMP_HELMET.get()) {

            // 3. 取消常规伤害，施加无视护甲的真实伤害
            float rawDamage = event.getAmount();
            event.setCanceled(true);
            
            // 使用原版的 genericKill 伤害源（天然自带 BYPASSES_ARMOR 标签）
            event.getEntity().hurt(event.getEntity().damageSources().genericKill(), rawDamage);
        }
    }
}