package net.mcreator.hammercraftfantasy.procedures;

import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;

@EventBusSubscriber
public class OnEntityAttackSyncSwingProcedure {
    private static final TagKey<EntityType<?>> LACKS_SWING_TAG = 
        TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("hammercraftfantasy:lacks_attack_swing"));

    @SubscribeEvent
    public static void onLivingDamage(LivingIncomingDamageEvent event) {
        Entity attacker = event.getSource().getEntity();

        if (attacker instanceof LivingEntity _livEnt && !_livEnt.level().isClientSide()) {
            if (attacker.getType().is(LACKS_SWING_TAG)) {
                long currentTime = _livEnt.level().getGameTime();
                long lastAttackTime = _livEnt.getPersistentData().getLong("LastSwingTime");

                if (currentTime - lastAttackTime >= 8) {
                    _livEnt.getPersistentData().putLong("LastSwingTime", currentTime);
                    _livEnt.swing(InteractionHand.MAIN_HAND, true);
                }
            }
        }
    }

    public static void execute(Entity sourceentity) {
    }
}