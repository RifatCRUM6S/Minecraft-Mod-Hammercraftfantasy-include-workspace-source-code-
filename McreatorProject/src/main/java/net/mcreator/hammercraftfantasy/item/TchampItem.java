package net.mcreator.hammercraftfantasy.item;

import net.neoforged.neoforge.registries.RegisterEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.ModList;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Holder;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.Minecraft;
import net.minecraft.Util;

import net.mcreator.hammercraftfantasy.init.HammercraftfantasyModItems;
import net.mcreator.hammercraftfantasy.client.model.Modelt_champions_armor;

import java.util.Map;
import java.util.List;
import java.util.EnumMap;
import java.util.Collections;

@EventBusSubscriber
public abstract class TchampItem extends ArmorItem {
    public static Holder<ArmorMaterial> ARMOR_MATERIAL = null;

    @SubscribeEvent
    public static void registerArmorMaterial(RegisterEvent event) {
        event.register(Registries.ARMOR_MATERIAL, registerHelper -> {
            ArmorMaterial armorMaterial = new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 6);
                map.put(ArmorItem.Type.LEGGINGS, 8);
                map.put(ArmorItem.Type.CHESTPLATE, 8);
                map.put(ArmorItem.Type.HELMET, 6);
                map.put(ArmorItem.Type.BODY, 8);
            }), 40, BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.EMPTY), () -> Ingredient.of(), List.of(new ArmorMaterial.Layer(ResourceLocation.parse("hammercraftfantasy:t_champ_armor"))), 4f, 0.2f);
            registerHelper.register(ResourceLocation.parse("hammercraftfantasy:tchamp"), armorMaterial);
            ARMOR_MATERIAL = BuiltInRegistries.ARMOR_MATERIAL.wrapAsHolder(armorMaterial);
        });
    }

    @SubscribeEvent
    public static void registerItemExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(new IClientItemExtensions() {
            private HumanoidModel armorModel = null;

            @Override
            @OnlyIn(Dist.CLIENT)
            public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                if (armorModel == null) {
                    armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(),
                            Map.of("head", new Modelt_champions_armor(Minecraft.getInstance().getEntityModels().bakeLayer(Modelt_champions_armor.LAYER_LOCATION)).head, "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body",
                                    new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm",
                                    new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg",
                                    new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                }
                armorModel.crouching = living.isShiftKeyDown();
                armorModel.riding = defaultModel.riding;
                armorModel.young = living.isBaby();
                return armorModel;
            }
        }, HammercraftfantasyModItems.TCHAMP_HELMET.get());
        event.registerItem(new IClientItemExtensions() {
            private HumanoidModel armorModel = null;

            @Override
            @OnlyIn(Dist.CLIENT)
            public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                if (armorModel == null) {
                    Modelt_champions_armor model = new Modelt_champions_armor(Minecraft.getInstance().getEntityModels().bakeLayer(Modelt_champions_armor.LAYER_LOCATION));
                    armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(),
                            Map.of("body", model.body, "left_arm", model.left_arm, "right_arm", model.right_arm, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat",
                                    new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg",
                                    new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                }
                armorModel.crouching = living.isShiftKeyDown();
                armorModel.riding = defaultModel.riding;
                armorModel.young = living.isBaby();
                return armorModel;
            }
        }, HammercraftfantasyModItems.TCHAMP_CHESTPLATE.get());
        event.registerItem(new IClientItemExtensions() {
            private HumanoidModel armorModel = null;

            @Override
            @OnlyIn(Dist.CLIENT)
            public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                if (armorModel == null) {
                    Modelt_champions_armor model = new Modelt_champions_armor(Minecraft.getInstance().getEntityModels().bakeLayer(Modelt_champions_armor.LAYER_LOCATION));
                    armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(),
                            Map.of("left_leg", model.left_leggings, "right_leg", model.right_leggings, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                                    "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm",
                                    new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                }
                armorModel.crouching = living.isShiftKeyDown();
                armorModel.riding = defaultModel.riding;
                armorModel.young = living.isBaby();
                return armorModel;
            }
        }, HammercraftfantasyModItems.TCHAMP_LEGGINGS.get());
        event.registerItem(new IClientItemExtensions() {
            private HumanoidModel armorModel = null;

            @Override
            @OnlyIn(Dist.CLIENT)
            public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                if (armorModel == null) {
                    Modelt_champions_armor model = new Modelt_champions_armor(Minecraft.getInstance().getEntityModels().bakeLayer(Modelt_champions_armor.LAYER_LOCATION));
                    armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(),
                            Map.of("left_leg", model.left_boots, "right_leg", model.right_boots, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body",
                                    new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm",
                                    new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                }
                armorModel.crouching = living.isShiftKeyDown();
                armorModel.riding = defaultModel.riding;
                armorModel.young = living.isBaby();
                return armorModel;
            }
        }, HammercraftfantasyModItems.TCHAMP_BOOTS.get());
    }

    public TchampItem(ArmorItem.Type type, Item.Properties properties) {
        super(ARMOR_MATERIAL, type, properties);
    }

    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers() {
        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();

        ItemAttributeModifiers defaultModifiers = super.getDefaultAttributeModifiers();
        for (ItemAttributeModifiers.Entry entry : defaultModifiers.modifiers()) {
            builder.add(entry.attribute(), entry.modifier(), entry.slot());
        }

        if (ModList.get().isLoaded("irons_spellbooks")) {
            EquipmentSlotGroup slotGroup = EquipmentSlotGroup.bySlot(this.getEquipmentSlot());
            String slotSuffix = "_" + this.getType().getName();

            BuiltInRegistries.ATTRIBUTE.getHolder(ResourceLocation.parse("irons_spellbooks:max_mana")).ifPresent(attr -> 
                builder.add(attr, new AttributeModifier(
                    ResourceLocation.parse("hammercraftfantasy:tchamp_base_mana" + slotSuffix),
                    120.0, 
                    AttributeModifier.Operation.ADD_VALUE
                ), slotGroup)
            );

            BuiltInRegistries.ATTRIBUTE.getHolder(ResourceLocation.parse("irons_spellbooks:cast_time_reduction")).ifPresent(attr -> 
                builder.add(attr, new AttributeModifier(
                    ResourceLocation.parse("hammercraftfantasy:tchamp_base_cast_time" + slotSuffix),
                    0.15, 
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ), slotGroup)
            );

            BuiltInRegistries.ATTRIBUTE.getHolder(ResourceLocation.parse("irons_spellbooks:spell_power")).ifPresent(attr -> 
                builder.add(attr, new AttributeModifier(
                    ResourceLocation.parse("hammercraftfantasy:tchamp_base_spell_power" + slotSuffix),
                    0.15, 
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ), slotGroup)
            );

            BuiltInRegistries.ATTRIBUTE.getHolder(ResourceLocation.parse("irons_spellbooks:mana_regen")).ifPresent(attr -> 
                builder.add(attr, new AttributeModifier(
                    ResourceLocation.parse("hammercraftfantasy:tchamp_base_mana_regen" + slotSuffix),
                    0.20, 
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ), slotGroup)
            );
        }

        return builder.build();
    }

    public static class Helmet extends TchampItem {
        public Helmet() {
            super(ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(100)).rarity(Rarity.EPIC));
        }

        private final ResourceLocation armorTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/t_champ_armor.png");

        @Override
        public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
            return armorTexture;
        }
    }

    public static class Chestplate extends TchampItem {
        public Chestplate() {
            super(ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(100)).rarity(Rarity.EPIC));
        }

        private final ResourceLocation armorTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/t_champ_armor.png");

        @Override
        public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
            return armorTexture;
        }

        @Override
        public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
            super.inventoryTick(stack, level, entity, slotId, isSelected);

            if (slotId == 38 && entity instanceof Player player) {

                double flyCooldown = player.getPersistentData().getDouble("fly_cooldown");
                double flyTimer = player.getPersistentData().getDouble("fly_timer");

                if (flyCooldown > 0) {
                    player.getPersistentData().putDouble("fly_cooldown", flyCooldown - 1);
                }
                if (flyTimer > 0) {
                    player.getPersistentData().putDouble("fly_timer", flyTimer - 1);
                }

                boolean hasFullSet = (player.getItemBySlot(EquipmentSlot.FEET).getItem() == HammercraftfantasyModItems.TCHAMP_BOOTS.get())
                        && (player.getItemBySlot(EquipmentSlot.LEGS).getItem() == HammercraftfantasyModItems.TCHAMP_LEGGINGS.get())
                        && (player.getItemBySlot(EquipmentSlot.CHEST).getItem() == HammercraftfantasyModItems.TCHAMP_CHESTPLATE.get())
                        && (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == HammercraftfantasyModItems.TCHAMP_HELMET.get());

                if (!player.isCreative() && !player.isSpectator()) {
                    if (hasFullSet && flyCooldown <= 0) {
                        
                        if (player.getAbilities().flying) {
                            if (flyTimer <= 0) {
                                player.getPersistentData().putDouble("fly_timer", 60);
                            } 
                            else if (flyTimer == 1) {
                                player.getAbilities().mayfly = false;
                                player.getAbilities().flying = false;
                                player.onUpdateAbilities();
                                
                                player.fallDistance = 0.0F;
                                
                                player.getPersistentData().putDouble("fly_cooldown", 60);
                            }
                        } else {
                            if (!player.getAbilities().mayfly) {
                                player.getAbilities().mayfly = true;
                                player.onUpdateAbilities();
                            }
                        }
                    } else {
                        if (player.getAbilities().mayfly || player.getAbilities().flying) {
                            player.getAbilities().mayfly = false;
                            player.getAbilities().flying = false;
                            player.onUpdateAbilities();
                            
                            player.fallDistance = 0.0F;
                        }
                    }
                }
            }
        }
    }

    public static class Leggings extends TchampItem {
        public Leggings() {
            super(ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(100)).rarity(Rarity.EPIC));
        }

        private final ResourceLocation armorTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/t_champ_armor.png");

        @Override
        public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
            return armorTexture;
        }
    }

    public static class Boots extends TchampItem {
        public Boots() {
            super(ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(100)).rarity(Rarity.EPIC));
        }

        private final ResourceLocation armorTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/t_champ_armor.png");

        @Override
        public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
            return armorTexture;
        }
    }
}