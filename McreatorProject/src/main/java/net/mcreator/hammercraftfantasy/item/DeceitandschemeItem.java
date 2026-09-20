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
import net.mcreator.hammercraftfantasy.client.model.Modeldeceit_and_scheme;

import java.util.Map;
import java.util.List;
import java.util.EnumMap;
import java.util.Collections;

@EventBusSubscriber
public abstract class DeceitandschemeItem extends ArmorItem {
    public static Holder<ArmorMaterial> ARMOR_MATERIAL = null;

    @SubscribeEvent
    public static void registerArmorMaterial(RegisterEvent event) {
        event.register(Registries.ARMOR_MATERIAL, registerHelper -> {
            ArmorMaterial armorMaterial = new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 4);
                map.put(ArmorItem.Type.LEGGINGS, 6);
                map.put(ArmorItem.Type.CHESTPLATE, 8);
                map.put(ArmorItem.Type.HELMET, 4);
                map.put(ArmorItem.Type.BODY, 8);
            }), 25, BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.EMPTY), () -> Ingredient.of(new ItemStack(Items.NETHERITE_INGOT)), List.of(new ArmorMaterial.Layer(ResourceLocation.parse("hammercraftfantasy:deceit_and_scheme"))), 4f, 0.1f);
            registerHelper.register(ResourceLocation.parse("hammercraftfantasy:deceitandscheme"), armorMaterial);
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
                            Map.of("head", new Modeldeceit_and_scheme(Minecraft.getInstance().getEntityModels().bakeLayer(Modeldeceit_and_scheme.LAYER_LOCATION)).head, "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body",
                                    new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm",
                                    new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg",
                                    new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                }
                armorModel.crouching = living.isShiftKeyDown();
                armorModel.riding = defaultModel.riding;
                armorModel.young = living.isBaby();
                return armorModel;
            }
        }, HammercraftfantasyModItems.DECEITANDSCHEME_HELMET.get());
        event.registerItem(new IClientItemExtensions() {
            private HumanoidModel armorModel = null;

            @Override
            @OnlyIn(Dist.CLIENT)
            public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                if (armorModel == null) {
                    Modeldeceit_and_scheme model = new Modeldeceit_and_scheme(Minecraft.getInstance().getEntityModels().bakeLayer(Modeldeceit_and_scheme.LAYER_LOCATION));
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
        }, HammercraftfantasyModItems.DECEITANDSCHEME_CHESTPLATE.get());
        event.registerItem(new IClientItemExtensions() {
            private HumanoidModel armorModel = null;

            @Override
            @OnlyIn(Dist.CLIENT)
            public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                if (armorModel == null) {
                    Modeldeceit_and_scheme model = new Modeldeceit_and_scheme(Minecraft.getInstance().getEntityModels().bakeLayer(Modeldeceit_and_scheme.LAYER_LOCATION));
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
        }, HammercraftfantasyModItems.DECEITANDSCHEME_LEGGINGS.get());
        event.registerItem(new IClientItemExtensions() {
            private HumanoidModel armorModel = null;

            @Override
            @OnlyIn(Dist.CLIENT)
            public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                if (armorModel == null) {
                    Modeldeceit_and_scheme model = new Modeldeceit_and_scheme(Minecraft.getInstance().getEntityModels().bakeLayer(Modeldeceit_and_scheme.LAYER_LOCATION));
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
        }, HammercraftfantasyModItems.DECEITANDSCHEME_BOOTS.get());
    }

    public DeceitandschemeItem(ArmorItem.Type type, Item.Properties properties) {
        super(ARMOR_MATERIAL, type, properties);
    }

    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers() {
        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();
        
        // 1. 保留默认原生的物理护甲与韧性
        ItemAttributeModifiers defaultModifiers = super.getDefaultAttributeModifiers();
        for (ItemAttributeModifiers.Entry entry : defaultModifiers.modifiers()) {
            builder.add(entry.attribute(), entry.modifier(), entry.slot());
        }

        // 2. 检测并注入铁魔法属性及底层强化槽
        if (ModList.get().isLoaded("irons_spellbooks")) {
            EquipmentSlotGroup slotGroup = EquipmentSlotGroup.bySlot(this.getEquipmentSlot());
            String slotSuffix = "_" + this.getType().getName();

            // --- [通用基础法术属性] ---
            // 最大法力 +81 (单件)
            BuiltInRegistries.ATTRIBUTE.getHolder(ResourceLocation.parse("irons_spellbooks:max_mana")).ifPresent(attr -> 
                builder.add(attr, new AttributeModifier(
                    ResourceLocation.parse("hammercraftfantasy:deceit_base_mana" + slotSuffix),
                    81.0, 
                    AttributeModifier.Operation.ADD_VALUE
                ), slotGroup)
            );

            // 基础施法强度 +5% (单件)
            BuiltInRegistries.ATTRIBUTE.getHolder(ResourceLocation.parse("irons_spellbooks:spell_power")).ifPresent(attr -> 
                builder.add(attr, new AttributeModifier(
                    ResourceLocation.parse("hammercraftfantasy:deceit_base_spell_power" + slotSuffix),
                    0.05, 
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ), slotGroup)
            );

            // 法力回复 +10% (单件)
            BuiltInRegistries.ATTRIBUTE.getHolder(ResourceLocation.parse("irons_spellbooks:mana_regen")).ifPresent(attr -> 
                builder.add(attr, new AttributeModifier(
                    ResourceLocation.parse("hammercraftfantasy:deceit_base_mana_regen" + slotSuffix),
                    0.10, 
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ), slotGroup)
            );

            // 开放 2 个升级强化槽 (单件)
            BuiltInRegistries.ATTRIBUTE.getHolder(ResourceLocation.parse("irons_spellbooks:upgrade_slots")).ifPresent(attr -> 
                builder.add(attr, new AttributeModifier(
                    ResourceLocation.parse("hammercraftfantasy:deceit_upgrade_slots" + slotSuffix),
                    2.0, 
                    AttributeModifier.Operation.ADD_VALUE
                ), slotGroup)
            );
        }

        return builder.build();
    }

    public static class Helmet extends DeceitandschemeItem {
        public Helmet() {
            super(ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(37)).rarity(Rarity.UNCOMMON));
        }

        private final ResourceLocation armorTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/deceit_and_scheme_layer_1.png");

        @Override
        public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
            return armorTexture;
        }
    }

    public static class Chestplate extends DeceitandschemeItem {
        public Chestplate() {
            super(ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(37)).rarity(Rarity.UNCOMMON));
        }

        private final ResourceLocation armorTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/deceit_and_scheme_layer_1.png");

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

                boolean hasFullSet = (player.getItemBySlot(EquipmentSlot.FEET).getItem() == HammercraftfantasyModItems.DECEITANDSCHEME_BOOTS.get())
                        && (player.getItemBySlot(EquipmentSlot.LEGS).getItem() == HammercraftfantasyModItems.DECEITANDSCHEME_LEGGINGS.get())
                        && (player.getItemBySlot(EquipmentSlot.CHEST).getItem() == HammercraftfantasyModItems.DECEITANDSCHEME_CHESTPLATE.get())
                        && (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == HammercraftfantasyModItems.DECEITANDSCHEME_HELMET.get());

                if (!player.isCreative() && !player.isSpectator()) {
                    if (hasFullSet && flyCooldown <= 0) {
                        
                        if (player.getAbilities().flying) {
                            if (flyTimer <= 0) {
                                player.getPersistentData().putDouble("fly_timer", 30);
                            } 
                            else if (flyTimer == 1) {
                                player.getAbilities().mayfly = false;
                                player.getAbilities().flying = false;
                                player.onUpdateAbilities();
                                player.fallDistance = 0.0F;
                                player.getPersistentData().putDouble("fly_cooldown", 100);
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

    public static class Leggings extends DeceitandschemeItem {
        public Leggings() {
            super(ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(37)).rarity(Rarity.UNCOMMON));
        }

        private final ResourceLocation armorTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/deceit_and_scheme_layer_1.png");

        @Override
        public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
            return armorTexture;
        }
    }

    public static class Boots extends DeceitandschemeItem {
        public Boots() {
            super(ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(37)).rarity(Rarity.UNCOMMON));
        }

        private final ResourceLocation armorTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/deceit_and_scheme_layer_1.png");

        @Override
        public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
            return armorTexture;
        }
    }
}