package net.mcreator.hammercraftfantasy.procedures;

import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;

import net.mcreator.hammercraftfantasy.init.HammercraftfantasyModItems;

@EventBusSubscriber
public class NchampattackingsickProcedure {

    @SubscribeEvent
    public static void onEntityAttacked(LivingDamageEvent.Pre event) {
        // 1. 过滤：攻击发起者必须是玩家，且必须是近战攻击（直接伤害来源就是玩家本身）
        Entity attacker = event.getSource().getEntity();
        Entity directEntity = event.getSource().getDirectEntity();

        if (!(attacker instanceof Player player) || directEntity != player) {
            return;
        }

        // 2. 检查受害者（Target）是否处于中毒状态（MobEffects.POISON）
        LivingEntity target = event.getEntity();
        if (target == null || !target.hasEffect(MobEffects.POISON)) {
            return;
        }

        // 3. 检测玩家是否穿戴全套纳垢冠军套（Nchamp）
        if (player.getItemBySlot(EquipmentSlot.FEET).getItem() == HammercraftfantasyModItems.NCHAMP_BOOTS.get()
                && player.getItemBySlot(EquipmentSlot.LEGS).getItem() == HammercraftfantasyModItems.NCHAMP_LEGGINGS.get()
                && player.getItemBySlot(EquipmentSlot.CHEST).getItem() == HammercraftfantasyModItems.NCHAMP_CHESTPLATE.get()
                && player.getItemBySlot(EquipmentSlot.HEAD).getItem() == HammercraftfantasyModItems.NCHAMP_HELMET.get()) {

            // 4. 对中毒敌人造成的近战伤害翻倍（原伤害 * 2.0F）
            event.setNewDamage(event.getNewDamage() * 2.0F);
        }
    }
}