package net.mcreator.hammercraftfantasy.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

public class Entitys_trytoattack_playanimeProcedure {
    public static boolean execute(Entity entity) {
        if (entity == null)
            return false;

        if (entity instanceof LivingEntity _livEnt) {
            if (_livEnt.swinging || _livEnt.swingTime == 1) {
                entity.getPersistentData().putInt("AttackAnimationTimer", 20);
            }
        }

        int timer = entity.getPersistentData().getInt("AttackAnimationTimer");
        if (timer > 0) {
            entity.getPersistentData().putInt("AttackAnimationTimer", timer - 1);
            return true;
        }

        return false;
    }
}