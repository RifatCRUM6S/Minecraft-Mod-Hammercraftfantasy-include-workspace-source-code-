package net.mcreator.hammercraftfantasy.procedures;

import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.DamageTypeTags;

import net.mcreator.hammercraftfantasy.init.HammercraftfantasyModItems;

@EventBusSubscriber
public class DeceitandschemerangeattackdetectProcedure {

    @SubscribeEvent
    public static void onEntityAttacked(LivingDamageEvent.Pre event) {
        // 1. 过滤：攻击发起者（Causing Entity）必须是玩家
        Entity attacker = event.getSource().getEntity();
        if (!(attacker instanceof Player player)) {
            return;
        }

        // 2. 远程攻击/非纯近战攻击判定：
        Entity directEntity = event.getSource().getDirectEntity();
        
        // 条件 A：直接伤害来源属于 Projectile（箭矢、三叉戟、TacZ枪弹等）
        boolean isProjectile = (directEntity instanceof Projectile) 
                            || event.getSource().is(DamageTypeTags.IS_PROJECTILE);

        // 条件 B：直接来源与源头玩家不同（例如其他 Mod 的自定义远程攻击实体）
        boolean isIndirect = (directEntity != null && directEntity != player);

        // 条件 C：喷火器（Flamershead）特判支持
        ItemStack mainHandStack = player.getMainHandItem();
        ItemStack offHandStack = player.getOffhandItem();
        boolean isFlamerAttack = (mainHandStack.getItem() == HammercraftfantasyModItems.FLAMERSHEAD.get() 
                               || offHandStack.getItem() == HammercraftfantasyModItems.FLAMERSHEAD.get());

        // 如果既不是弹射物，也不是间接攻击，也不是喷火器攻击，则视为普通近战，直接跳过
        if (!isProjectile && !isIndirect && !isFlamerAttack) {
            return;
        }

        // 3. 读取玩家 4 个装备槽位的 Item
        var boots = player.getItemBySlot(EquipmentSlot.FEET).getItem();
        var leggings = player.getItemBySlot(EquipmentSlot.LEGS).getItem();
        var chestplate = player.getItemBySlot(EquipmentSlot.CHEST).getItem();
        var helmet = player.getItemBySlot(EquipmentSlot.HEAD).getItem();

        // 4. 判定进阶套装：奸奇神选 Champion 套装（Tchamp） -> 远程伤害翻倍（+100% / *2.0）
        if (boots == HammercraftfantasyModItems.TCHAMP_BOOTS.get()
                && leggings == HammercraftfantasyModItems.TCHAMP_LEGGINGS.get()
                && chestplate == HammercraftfantasyModItems.TCHAMP_CHESTPLATE.get()
                && helmet == HammercraftfantasyModItems.TCHAMP_HELMET.get()) {

            event.setNewDamage(event.getNewDamage() * 2.0F);
            return;
        }

        // 5. 判定基础套装：欺诈与阴谋套装（Deceit and Scheme） -> 远程伤害增加 50%（+50% / *1.5）
        if (boots == HammercraftfantasyModItems.DECEITANDSCHEME_BOOTS.get()
                && leggings == HammercraftfantasyModItems.DECEITANDSCHEME_LEGGINGS.get()
                && chestplate == HammercraftfantasyModItems.DECEITANDSCHEME_CHESTPLATE.get()
                && helmet == HammercraftfantasyModItems.DECEITANDSCHEME_HELMET.get()) {

            event.setNewDamage(event.getNewDamage() * 1.5F);
        }
    }
}