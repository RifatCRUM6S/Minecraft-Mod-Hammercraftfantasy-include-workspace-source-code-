package net.mcreator.hammercraftfantasy.item;

import net.neoforged.neoforge.registries.RegisterEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.Holder;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.Minecraft;
import net.minecraft.Util;

import net.mcreator.hammercraftfantasy.init.HammercraftfantasyModItems;
import net.mcreator.hammercraftfantasy.client.model.Modelring_of_lust;

import java.util.Map;
import java.util.List;
import java.util.EnumMap;
import java.util.Collections;

@EventBusSubscriber
public abstract class RingoflustItem extends ArmorItem {
    public static Holder<ArmorMaterial> ARMOR_MATERIAL = null;

    @SubscribeEvent
    public static void registerArmorMaterial(RegisterEvent event) {
        event.register(Registries.ARMOR_MATERIAL, registerHelper -> {
            ArmorMaterial armorMaterial = new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 0);
                map.put(ArmorItem.Type.LEGGINGS, 0);
                map.put(ArmorItem.Type.CHESTPLATE, 0);
                map.put(ArmorItem.Type.HELMET, 1);
                map.put(ArmorItem.Type.BODY, 0);
            }), 1, BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.EMPTY), () -> Ingredient.of(new ItemStack(HammercraftfantasyModItems.OBSESSIONINGOT.get())),
                    List.of(new ArmorMaterial.Layer(ResourceLocation.parse("hammercraftfantasy:ringoflust"))), 0f, 0f);
            registerHelper.register(ResourceLocation.parse("hammercraftfantasy:ringoflust"), armorMaterial);
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
                            Map.of("head", new Modelring_of_lust(Minecraft.getInstance().getEntityModels().bakeLayer(Modelring_of_lust.LAYER_LOCATION)).head, 
                                   "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), 
                                   "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()), 
                                   "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), 
                                   "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), 
                                   "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), 
                                   "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                }
                armorModel.crouching = living.isShiftKeyDown();
                armorModel.riding = defaultModel.riding;
                armorModel.young = living.isBaby();
                return armorModel;
            }
        }, HammercraftfantasyModItems.RINGOFLUST_HELMET.get());
    }

    public RingoflustItem(ArmorItem.Type type, Item.Properties properties) {
        super(ARMOR_MATERIAL, type, properties);
    }

    public static class Helmet extends RingoflustItem {
        public Helmet() {
            // 设置 600 点耐久（在 30 秒内耗尽）
            super(ArmorItem.Type.HELMET, new Item.Properties().durability(600).rarity(Rarity.UNCOMMON));
        }

        private final ResourceLocation armorTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/ringoflust.png");

        @Override
        public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
            return armorTexture;
        }

        // 1.21.1 标准：替换为全局物品 InventoryTick，专门针对头部（HEAD）位置校验
        @Override
        public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
            super.inventoryTick(stack, level, entity, slotId, isSelected);

            if (level.isClientSide() || !(entity instanceof Player player)) return;

            // 确保玩家确实穿戴了此头盔在头部格子里
            if (player.getItemBySlot(EquipmentSlot.HEAD) == stack) {
                
                // 1. 扣耐久（使用 1.21 的 HEAD 槽位）
                stack.hurtAndBreak(1, player, EquipmentSlot.HEAD);

                // 2. 1.21 数据组件读写 NBT
                CustomData customData = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
                CompoundTag nbt = customData.copyTag();

                int timer = nbt.getInt("EatTimer") + 1;
                boolean isFed = nbt.getBoolean("IsFed");

                // 每 40 Ticks（2 秒）尝试吞噬祭品
                if (timer >= 40) {
                    timer = 0;
                    isFed = tryConsumeItems(player);
                    
                    if (isFed) {
                        level.playSound(null, player.getX(), player.getY(), player.getZ(), 
                            SoundEvents.DONKEY_EAT, SoundSource.PLAYERS, 0.5F, 1.2F);
                    }
                }

                // 更新写入 NBT 数据组件
                nbt.putInt("EatTimer", timer);
                nbt.putBoolean("IsFed", isFed);
                CustomData.set(DataComponents.CUSTOM_DATA, stack, nbt);

                // 3. 只有吃饱（IsFed）时才触发周围 8 格清除仇恨
                if (isFed && player.tickCount % 5 == 0) {
                    clearNearbyMobsAggro(level, player);
                }
            }
        }

        private boolean tryConsumeItems(Player player) {
            if (player.getAbilities().instabuild) return true;

            if (hasEnoughItem(player, Items.GOLD_INGOT, 6)) {
                consumeItem(player, Items.GOLD_INGOT, 6);
                return true;
            } else if (hasEnoughItem(player, Items.DIAMOND, 1)) {
                consumeItem(player, Items.DIAMOND, 1);
                return true;
            }

            return false;
        }

        private boolean hasEnoughItem(Player player, Item targetItem, int amountNeeded) {
            int count = 0;
            for (ItemStack invStack : player.getInventory().items) {
                if (invStack.getItem() == targetItem) {
                    count += invStack.getCount();
                    if (count >= amountNeeded) return true;
                }
            }
            return false;
        }

        private void consumeItem(Player player, Item targetItem, int amountToConsume) {
            for (ItemStack invStack : player.getInventory().items) {
                if (invStack.getItem() == targetItem) {
                    int removeCount = Math.min(amountToConsume, invStack.getCount());
                    invStack.shrink(removeCount);
                    amountToConsume -= removeCount;
                    if (amountToConsume <= 0) break;
                }
            }
        }

        private void clearNearbyMobsAggro(Level level, Player player) {
            net.minecraft.world.phys.AABB area = player.getBoundingBox().inflate(8.0D);
            List<Mob> nearbyMobs = level.getEntitiesOfClass(Mob.class, area);

            for (Mob mob : nearbyMobs) {
                if (mob.getTarget() == player) {
                    mob.setTarget(null);
                    mob.setLastHurtByMob(null);
                }
            }
        }
    }
}