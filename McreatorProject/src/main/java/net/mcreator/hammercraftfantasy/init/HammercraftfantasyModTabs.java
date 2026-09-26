/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.hammercraftfantasy.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcreator.hammercraftfantasy.HammercraftfantasyMod;

@EventBusSubscriber
public class HammercraftfantasyModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HammercraftfantasyMod.MODID);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> HAMMERCRAFT_FITEMS = REGISTRY.register("hammercraft_fitems",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.hammercraftfantasy.hammercraft_fitems")).icon(() -> new ItemStack(HammercraftfantasyModItems.DEMON_ESSENCE.get())).displayItems((parameters, tabData) -> {
				tabData.accept(HammercraftfantasyModItems.DEMON_ESSENCE.get());
				tabData.accept(HammercraftfantasyModItems.HELL_BLADE.get());
				tabData.accept(HammercraftfantasyModBlocks.CHAOSTHORN.get().asItem());
				tabData.accept(HammercraftfantasyModItems.RAGEANDFURY_HELMET.get());
				tabData.accept(HammercraftfantasyModItems.RAGEANDFURY_CHESTPLATE.get());
				tabData.accept(HammercraftfantasyModItems.RAGEANDFURY_LEGGINGS.get());
				tabData.accept(HammercraftfantasyModItems.RAGEANDFURY_BOOTS.get());
				tabData.accept(HammercraftfantasyModItems.CORRUPTIONANDROT_HELMET.get());
				tabData.accept(HammercraftfantasyModItems.CORRUPTIONANDROT_CHESTPLATE.get());
				tabData.accept(HammercraftfantasyModItems.CORRUPTIONANDROT_LEGGINGS.get());
				tabData.accept(HammercraftfantasyModItems.CORRUPTIONANDROT_BOOTS.get());
				tabData.accept(HammercraftfantasyModItems.OBSESSIONANDDESIRE_HELMET.get());
				tabData.accept(HammercraftfantasyModItems.OBSESSIONANDDESIRE_CHESTPLATE.get());
				tabData.accept(HammercraftfantasyModItems.OBSESSIONANDDESIRE_LEGGINGS.get());
				tabData.accept(HammercraftfantasyModItems.OBSESSIONANDDESIRE_BOOTS.get());
				tabData.accept(HammercraftfantasyModItems.DECEITANDSCHEME_HELMET.get());
				tabData.accept(HammercraftfantasyModItems.DECEITANDSCHEME_CHESTPLATE.get());
				tabData.accept(HammercraftfantasyModItems.DECEITANDSCHEME_LEGGINGS.get());
				tabData.accept(HammercraftfantasyModItems.DECEITANDSCHEME_BOOTS.get());
				tabData.accept(HammercraftfantasyModItems.RAGEESSENCE.get());
				tabData.accept(HammercraftfantasyModItems.RAGEINGOT.get());
				tabData.accept(HammercraftfantasyModItems.KHORNETEMPLATE.get());
				tabData.accept(HammercraftfantasyModItems.DECEITESSENCE.get());
				tabData.accept(HammercraftfantasyModItems.DECEITINGOT.get());
				tabData.accept(HammercraftfantasyModItems.TZEENTCHTEMPLATE.get());
				tabData.accept(HammercraftfantasyModItems.OBSESSIONESSENCE.get());
				tabData.accept(HammercraftfantasyModItems.OBSESSIONINGOT.get());
				tabData.accept(HammercraftfantasyModItems.SLAANESHTEMPLATE.get());
				tabData.accept(HammercraftfantasyModItems.CORRUPTIONESSENCE.get());
				tabData.accept(HammercraftfantasyModItems.CORRUPTIONINGOT.get());
				tabData.accept(HammercraftfantasyModItems.NURGLETEMPLATE.get());
				tabData.accept(HammercraftfantasyModItems.RAGEBATTLEAXE.get());
				tabData.accept(HammercraftfantasyModItems.RAGEMATTER.get());
				tabData.accept(HammercraftfantasyModItems.DECEITMATTER.get());
				tabData.accept(HammercraftfantasyModItems.OBSESSIONMATTER.get());
				tabData.accept(HammercraftfantasyModItems.CORRUPTIONMATTER.get());
				tabData.accept(HammercraftfantasyModItems.DEMONSMETALNUGGET.get());
				tabData.accept(HammercraftfantasyModItems.DEMONSMETALINGOT.get());
				tabData.accept(HammercraftfantasyModBlocks.CRYSTALSAND.get().asItem());
				tabData.accept(HammercraftfantasyModBlocks.PLAGUELOG.get().asItem());
				tabData.accept(HammercraftfantasyModBlocks.PLAGUELEAVES.get().asItem());
				tabData.accept(HammercraftfantasyModBlocks.ROTFLYNEST.get().asItem());
				tabData.accept(HammercraftfantasyModItems.DIVINEPLAGUE.get());
				tabData.accept(HammercraftfantasyModItems.GRANDFATHERSSOUP.get());
				tabData.accept(HammercraftfantasyModItems.GARDENERSHOE.get());
				tabData.accept(HammercraftfantasyModBlocks.PLAGUEPLANKS.get().asItem());
				tabData.accept(HammercraftfantasyModItems.KEYOFGARDEN.get());
				tabData.accept(HammercraftfantasyModItems.KEYOFTHRONE.get());
				tabData.accept(HammercraftfantasyModItems.KEYOFLIBRARY.get());
				tabData.accept(HammercraftfantasyModItems.KEYOFPALACE.get());
				tabData.accept(HammercraftfantasyModItems.DEMONSTEELSWORD.get());
				tabData.accept(HammercraftfantasyModItems.DEMONSTEELARMOR_HELMET.get());
				tabData.accept(HammercraftfantasyModItems.DEMONSTEELARMOR_CHESTPLATE.get());
				tabData.accept(HammercraftfantasyModItems.DEMONSTEELARMOR_LEGGINGS.get());
				tabData.accept(HammercraftfantasyModItems.DEMONSTEELARMOR_BOOTS.get());
				tabData.accept(HammercraftfantasyModBlocks.CHANGINGGLASS.get().asItem());
				tabData.accept(HammercraftfantasyModBlocks.MAZECRYSTAL.get().asItem());
				tabData.accept(HammercraftfantasyModItems.RITUALDAGGER.get());
				tabData.accept(HammercraftfantasyModItems.FLAMERSHEAD.get());
				tabData.accept(HammercraftfantasyModBlocks.TRICKYSTONEBRICK.get().asItem());
				tabData.accept(HammercraftfantasyModItems.KCHAMP_HELMET.get());
				tabData.accept(HammercraftfantasyModItems.KCHAMP_CHESTPLATE.get());
				tabData.accept(HammercraftfantasyModItems.KCHAMP_LEGGINGS.get());
				tabData.accept(HammercraftfantasyModItems.KCHAMP_BOOTS.get());
				tabData.accept(HammercraftfantasyModItems.NCHAMP_HELMET.get());
				tabData.accept(HammercraftfantasyModItems.NCHAMP_CHESTPLATE.get());
				tabData.accept(HammercraftfantasyModItems.NCHAMP_LEGGINGS.get());
				tabData.accept(HammercraftfantasyModItems.NCHAMP_BOOTS.get());
				tabData.accept(HammercraftfantasyModItems.TCHAMP_HELMET.get());
				tabData.accept(HammercraftfantasyModItems.TCHAMP_CHESTPLATE.get());
				tabData.accept(HammercraftfantasyModItems.TCHAMP_LEGGINGS.get());
				tabData.accept(HammercraftfantasyModItems.TCHAMP_BOOTS.get());
				tabData.accept(HammercraftfantasyModItems.SCHAMP_HELMET.get());
				tabData.accept(HammercraftfantasyModItems.SCHAMP_CHESTPLATE.get());
				tabData.accept(HammercraftfantasyModItems.SCHAMP_LEGGINGS.get());
				tabData.accept(HammercraftfantasyModItems.SCHAMP_BOOTS.get());
				tabData.accept(HammercraftfantasyModBlocks.ALTAROFSUFFERING.get().asItem());
				tabData.accept(HammercraftfantasyModBlocks.K_CHAMPION_SUMMON_ALTAR.get().asItem());
				tabData.accept(HammercraftfantasyModBlocks.N_CHAMPION_SUMMON_ALTAR.get().asItem());
				tabData.accept(HammercraftfantasyModBlocks.T_CHAMPION_SUMMON_ALTAR.get().asItem());
				tabData.accept(HammercraftfantasyModBlocks.S_CHAMPION_SUMMON_ALTAR.get().asItem());
				tabData.accept(HammercraftfantasyModItems.RINGOFLUST_HELMET.get());
				tabData.accept(HammercraftfantasyModBlocks.DEMONSTEELORE.get().asItem());
				tabData.accept(HammercraftfantasyModItems.DEMONSTRUENAME.get());
				tabData.accept(HammercraftfantasyModItems.SILVERSHARPSWORD.get());
			}).build());

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
			tabData.accept(HammercraftfantasyModItems.SKARBRAND_SPAWN_EGG.get());
			tabData.accept(HammercraftfantasyModItems.BLOODLETTER_SPAWN_EGG.get());
			tabData.accept(HammercraftfantasyModItems.UNKNOW_BEAUTY_SPAWN_EGG.get());
			tabData.accept(HammercraftfantasyModItems.DEMONEZ_SPAWN_EGG.get());
			tabData.accept(HammercraftfantasyModItems.HERALDOF_KHORNE_SPAWN_EGG.get());
			tabData.accept(HammercraftfantasyModItems.NURGLING_SPAWN_EGG.get());
			tabData.accept(HammercraftfantasyModItems.PINK_HORROR_SPAWN_EGG.get());
			tabData.accept(HammercraftfantasyModItems.BLUE_HORROR_SPAWN_EGG.get());
			tabData.accept(HammercraftfantasyModItems.FLESHHOUNDS_SPAWN_EGG.get());
			tabData.accept(HammercraftfantasyModItems.KCHAOSWARRIOR_SPAWN_EGG.get());
			tabData.accept(HammercraftfantasyModItems.KCULTIST_SPAWN_EGG.get());
			tabData.accept(HammercraftfantasyModItems.ROTFLY_SPAWN_EGG.get());
			tabData.accept(HammercraftfantasyModItems.PLAGUEBEARER_SPAWN_EGG.get());
			tabData.accept(HammercraftfantasyModItems.GREATUNCLEANONE_SPAWN_EGG.get());
			tabData.accept(HammercraftfantasyModItems.ROTMAGGOT_SPAWN_EGG.get());
			tabData.accept(HammercraftfantasyModItems.PLAGUEZOMBIE_SPAWN_EGG.get());
			tabData.accept(HammercraftfantasyModItems.NAGOXO_SPAWN_EGG.get());
			tabData.accept(HammercraftfantasyModItems.CHAOSSPAWN_SPAWN_EGG.get());
			tabData.accept(HammercraftfantasyModItems.FORSAKEN_SPAWN_EGG.get());
			tabData.accept(HammercraftfantasyModItems.FIENDOFSLAANESH_SPAWN_EGG.get());
			tabData.accept(HammercraftfantasyModItems.DEAMONETTE_SPAWN_EGG.get());
			tabData.accept(HammercraftfantasyModItems.STEEDOFSLAANESH_SPAWN_EGG.get());
			tabData.accept(HammercraftfantasyModItems.TAMURKHAN_SPAWN_EGG.get());
			tabData.accept(HammercraftfantasyModItems.VALKIA_SPAWN_EGG.get());
			tabData.accept(HammercraftfantasyModItems.SIGVALD_SPAWN_EGG.get());
			tabData.accept(HammercraftfantasyModItems.SILVERGUARD_SPAWN_EGG.get());
			tabData.accept(HammercraftfantasyModItems.SCREAMER_SPAWN_EGG.get());
			tabData.accept(HammercraftfantasyModItems.FLAMER_SPAWN_EGG.get());
			tabData.accept(HammercraftfantasyModItems.TZAANGOR_SPAWN_EGG.get());
			tabData.accept(HammercraftfantasyModItems.ERIC_SPAWN_EGG.get());
			tabData.accept(HammercraftfantasyModItems.ERICSPAWN_SPAWN_EGG.get());
			tabData.accept(HammercraftfantasyModItems.MAGGOTLORD_SPAWN_EGG.get());
			tabData.accept(HammercraftfantasyModItems.TZAANGORHALBERD_SPAWN_EGG.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			tabData.accept(HammercraftfantasyModItems.DEMON_ESSENCE.get());
			tabData.accept(HammercraftfantasyModItems.RAGEBATTLEAXE.get());
			tabData.accept(HammercraftfantasyModItems.GARDENERSHOE.get());
			tabData.accept(HammercraftfantasyModItems.DEMONSTEELSWORD.get());
			tabData.accept(HammercraftfantasyModItems.DEMONSTEELAXE.get());
			tabData.accept(HammercraftfantasyModItems.DEMONSTEELPICKAXE.get());
			tabData.accept(HammercraftfantasyModItems.DEMONSTEELSHOVEL.get());
			tabData.accept(HammercraftfantasyModItems.DEMONSTEELHOE.get());
			tabData.accept(HammercraftfantasyModItems.RITUALDAGGER.get());
			tabData.accept(HammercraftfantasyModItems.SILVERSHARPSWORD.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.COMBAT) {
			tabData.accept(HammercraftfantasyModItems.RAGEANDFURY_HELMET.get());
			tabData.accept(HammercraftfantasyModItems.RAGEANDFURY_CHESTPLATE.get());
			tabData.accept(HammercraftfantasyModItems.RAGEANDFURY_LEGGINGS.get());
			tabData.accept(HammercraftfantasyModItems.RAGEANDFURY_BOOTS.get());
			tabData.accept(HammercraftfantasyModItems.CORRUPTIONANDROT_HELMET.get());
			tabData.accept(HammercraftfantasyModItems.CORRUPTIONANDROT_CHESTPLATE.get());
			tabData.accept(HammercraftfantasyModItems.CORRUPTIONANDROT_LEGGINGS.get());
			tabData.accept(HammercraftfantasyModItems.CORRUPTIONANDROT_BOOTS.get());
			tabData.accept(HammercraftfantasyModItems.OBSESSIONANDDESIRE_HELMET.get());
			tabData.accept(HammercraftfantasyModItems.OBSESSIONANDDESIRE_CHESTPLATE.get());
			tabData.accept(HammercraftfantasyModItems.OBSESSIONANDDESIRE_LEGGINGS.get());
			tabData.accept(HammercraftfantasyModItems.OBSESSIONANDDESIRE_BOOTS.get());
			tabData.accept(HammercraftfantasyModItems.DECEITANDSCHEME_HELMET.get());
			tabData.accept(HammercraftfantasyModItems.DECEITANDSCHEME_CHESTPLATE.get());
			tabData.accept(HammercraftfantasyModItems.DECEITANDSCHEME_LEGGINGS.get());
			tabData.accept(HammercraftfantasyModItems.DECEITANDSCHEME_BOOTS.get());
			tabData.accept(HammercraftfantasyModItems.GARDENERSHOE.get());
			tabData.accept(HammercraftfantasyModItems.DEMONSTEELSWORD.get());
			tabData.accept(HammercraftfantasyModItems.DEMONSTEELARMOR_HELMET.get());
			tabData.accept(HammercraftfantasyModItems.DEMONSTEELARMOR_CHESTPLATE.get());
			tabData.accept(HammercraftfantasyModItems.DEMONSTEELARMOR_LEGGINGS.get());
			tabData.accept(HammercraftfantasyModItems.DEMONSTEELARMOR_BOOTS.get());
			tabData.accept(HammercraftfantasyModItems.RITUALDAGGER.get());
			tabData.accept(HammercraftfantasyModItems.FLAMERSHEAD.get());
			tabData.accept(HammercraftfantasyModItems.KCHAMP_HELMET.get());
			tabData.accept(HammercraftfantasyModItems.KCHAMP_CHESTPLATE.get());
			tabData.accept(HammercraftfantasyModItems.KCHAMP_LEGGINGS.get());
			tabData.accept(HammercraftfantasyModItems.KCHAMP_BOOTS.get());
			tabData.accept(HammercraftfantasyModItems.NCHAMP_HELMET.get());
			tabData.accept(HammercraftfantasyModItems.NCHAMP_CHESTPLATE.get());
			tabData.accept(HammercraftfantasyModItems.NCHAMP_LEGGINGS.get());
			tabData.accept(HammercraftfantasyModItems.NCHAMP_BOOTS.get());
			tabData.accept(HammercraftfantasyModItems.TCHAMP_HELMET.get());
			tabData.accept(HammercraftfantasyModItems.TCHAMP_CHESTPLATE.get());
			tabData.accept(HammercraftfantasyModItems.TCHAMP_LEGGINGS.get());
			tabData.accept(HammercraftfantasyModItems.TCHAMP_BOOTS.get());
			tabData.accept(HammercraftfantasyModItems.SCHAMP_HELMET.get());
			tabData.accept(HammercraftfantasyModItems.SCHAMP_CHESTPLATE.get());
			tabData.accept(HammercraftfantasyModItems.SCHAMP_LEGGINGS.get());
			tabData.accept(HammercraftfantasyModItems.SCHAMP_BOOTS.get());
			tabData.accept(HammercraftfantasyModItems.RINGOFLUST_HELMET.get());
			tabData.accept(HammercraftfantasyModItems.DEMONSTRUENAME.get());
			tabData.accept(HammercraftfantasyModItems.SILVERSHARPSWORD.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.INGREDIENTS) {
			tabData.accept(HammercraftfantasyModItems.KHORNETEMPLATE.get());
			tabData.accept(HammercraftfantasyModItems.TZEENTCHTEMPLATE.get());
			tabData.accept(HammercraftfantasyModItems.SLAANESHTEMPLATE.get());
			tabData.accept(HammercraftfantasyModItems.NURGLETEMPLATE.get());
			tabData.accept(HammercraftfantasyModItems.DEMONSMETALINGOT.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
			tabData.accept(HammercraftfantasyModItems.GRANDFATHERSSOUP.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
			tabData.accept(HammercraftfantasyModBlocks.CRYSTALGRASS.get().asItem());
			tabData.accept(HammercraftfantasyModBlocks.DEMONSTEELORE.get().asItem());
		}
	}
}