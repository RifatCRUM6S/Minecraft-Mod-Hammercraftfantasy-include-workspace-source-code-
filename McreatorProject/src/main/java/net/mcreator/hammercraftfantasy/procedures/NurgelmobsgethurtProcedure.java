package net.mcreator.hammercraftfantasy.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;

import net.mcreator.hammercraftfantasy.init.HammercraftfantasyModEntities;

public class NurgelmobsgethurtProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z) {
        if (!(world instanceof ServerLevel _level)) return;
        RandomSource random = _level.getRandom();

        double chance = random.nextDouble();
        int count = 0;
        if (chance < 0.25) {
            count = 2;
        } else if (chance < 0.40) {
            count = 3;
        } else {
            return;
        }

        for (int i = 0; i < count; i++) {
            // 生成位置：腰部高度 + 随机偏移
            double offsetX = (random.nextDouble() - 0.5) * 0.6;
            double offsetZ = (random.nextDouble() - 0.5) * 0.6;
            BlockPos spawnPos = BlockPos.containing(x + offsetX, y + 1.0, z + offsetZ);

            Entity entityToSpawn = HammercraftfantasyModEntities.ROTMAGGOT.get()
                    .spawn(_level, spawnPos, MobSpawnType.MOB_SUMMONED);
            if (entityToSpawn != null) {
                entityToSpawn.setYRot(random.nextFloat() * 360F);

                // 随机水平方向（均匀分布 0~2π）
                double angle = random.nextDouble() * 2 * Math.PI;
                double horizSpeed = 0.3 + random.nextDouble() * 0.4;
                double upSpeed = 0.2 + random.nextDouble() * 0.3;
                entityToSpawn.setDeltaMovement(Math.cos(angle) * horizSpeed, upSpeed, Math.sin(angle) * horizSpeed);
            }
        }
    }
}