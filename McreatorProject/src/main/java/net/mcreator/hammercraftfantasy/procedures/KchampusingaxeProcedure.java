package net.mcreator.hammercraftfantasy.procedures;

import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;

import net.mcreator.hammercraftfantasy.init.HammercraftfantasyModItems;

@EventBusSubscriber
public class KchampusingaxeProcedure {

    @SubscribeEvent
    public static void onEntityAttacked(LivingDamageEvent.Pre event) {
        // 1. 过滤：攻击发起者（Causing Entity）必须是玩家，且必须是近战攻击（直接伤害来源就是玩家本身）
        Entity attacker = event.getSource().getEntity();
        Entity directEntity = event.getSource().getDirectEntity();

        if (!(attacker instanceof Player player) || directEntity != player) {
            return;
        }

        // 2. 判断玩家主手持有的物品是否为斧头（兼容原版及其他 Mod 的所有斧头）
        ItemStack mainHandStack = player.getMainHandItem();
        boolean isAxe = (mainHandStack.getItem() instanceof AxeItem) 
                     || mainHandStack.is(ItemTags.AXES);

        if (!isAxe) {
            return;
        }

        // 3. 检测玩家是否穿戴全套恐虐冠军套（kchamp）
        if (player.getItemBySlot(EquipmentSlot.FEET).getItem() == HammercraftfantasyModItems.KCHAMP_BOOTS.get()
                && player.getItemBySlot(EquipmentSlot.LEGS).getItem() == HammercraftfantasyModItems.KCHAMP_LEGGINGS.get()
                && player.getItemBySlot(EquipmentSlot.CHEST).getItem() == HammercraftfantasyModItems.KCHAMP_CHESTPLATE.get()
                && player.getItemBySlot(EquipmentSlot.HEAD).getItem() == HammercraftfantasyModItems.KCHAMP_HELMET.get()) {

            // 4. 斧子近战伤害翻倍（原伤害 * 2.0F）
            event.setNewDamage(event.getNewDamage() * 2.0F);
        }
    }
}