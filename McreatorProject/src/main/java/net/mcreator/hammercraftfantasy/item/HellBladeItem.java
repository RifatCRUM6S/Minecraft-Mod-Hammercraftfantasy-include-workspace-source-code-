package net.mcreator.hammercraftfantasy.item;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.core.registries.Registries;

import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;

public class HellBladeItem extends SwordItem {
    private static final Tier TOOL_TIER = new Tier() {
        @Override
        public int getUses() {
            return 250;
        }

        @Override
        public float getSpeed() {
            return 8f;
        }

        @Override
        public float getAttackDamageBonus() {
            return 0;
        }

        @Override
        public TagKey<Block> getIncorrectBlocksForDrops() {
            return BlockTags.INCORRECT_FOR_DIAMOND_TOOL;
        }

        @Override
        public int getEnchantmentValue() {
            return 10;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(new ItemStack(Items.NETHERITE_SCRAP));
        }
    };

    public HellBladeItem() {
        super(TOOL_TIER, new Item.Properties().attributes(SwordItem.createAttributes(TOOL_TIER, 6f, -2.4f)));
    }

    // Core event handler for custom skull drops compatible with MC 1.21.1
    @EventBusSubscriber
    public static class HellBladeLootHandler {
        @SubscribeEvent
        public static void onLivingDrops(LivingDropsEvent event) {
            DamageSource source = event.getSource();
            
            // 1. Check if the attacker is a player and holding the HellBlade
            if (source.getEntity() instanceof Player player) {
                ItemStack mainHandItem = player.getMainHandItem();
                
                if (mainHandItem.getItem() instanceof HellBladeItem) {
                    LivingEntity victim = event.getEntity();
                    var random = victim.getRandom();
                    
                    // 2. Safe enchanting lookup for 1.21.1 Data Pack Component registry
                    int lootingLevel = 0;
                    if (mainHandItem.isEnchanted()) {
                        try {
                            var enchantmentRegistry = player.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
                            var lootingHolder = enchantmentRegistry.get(Enchantments.LOOTING);
                            if (lootingHolder.isPresent()) {
                                lootingLevel = EnchantmentHelper.getItemEnchantmentLevel(lootingHolder.get(), mainHandItem);
                            }
                        } catch (Exception e) {
                            lootingLevel = 0; // Fallback to 0 if any registry exceptions occur
                        }
                    }
                    
                    float roll = random.nextFloat();
                    
                    // ================= Case A: Wither Skeleton =================
                    if (victim instanceof WitherSkeleton) {
                        float chance = 0.15f + (lootingLevel * 0.02f);
                        
                        // debug logging
                        System.out.println("HellBlade Loot Tracker: Killed Wither Skeleton | Looting: " + lootingLevel + " | Target Chance: " + chance + " | Rolled: " + roll);
                        
                        if (roll < chance) {
                            // Clear original drop to prevent double head drop bug
                            event.getDrops().removeIf(itemEntity -> itemEntity.getItem().getItem() == Items.WITHER_SKELETON_SKULL);
                            event.getDrops().add(victim.spawnAtLocation(new ItemStack(Items.WITHER_SKELETON_SKULL)));
                        }
                        
                    // ================= Case B: Other specific mobs =================
                    } else if (victim instanceof AbstractSkeleton || victim instanceof Villager 
                            || victim instanceof Pillager || victim instanceof Witch || victim instanceof Player) {
                        
                        float chance = 0.75f + (lootingLevel * 0.05f);
                        
                        // debug logging
                        System.out.println("HellBlade Loot Tracker: Killed Target Mob (" + victim.getType().getDescriptionId() + ") | Looting: " + lootingLevel + " | Target Chance: " + chance + " | Rolled: " + roll);
                        
                        if (roll < chance) {
                            // Clear original drop to ensure exactly one head drops
                            event.getDrops().removeIf(itemEntity -> itemEntity.getItem().getItem() == Items.SKELETON_SKULL);
                            event.getDrops().add(victim.spawnAtLocation(new ItemStack(Items.SKELETON_SKULL)));
                        }
                    }
                }
            }
        }
    }
}