/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.hammercraftfantasy.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;

import net.minecraft.world.level.block.Block;

import net.mcreator.hammercraftfantasy.block.*;
import net.mcreator.hammercraftfantasy.HammercraftfantasyMod;

public class HammercraftfantasyModBlocks {
	public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(HammercraftfantasyMod.MODID);
	public static final DeferredBlock<Block> OCEANOFSOULS_PORTAL;
	public static final DeferredBlock<Block> CHAOSTHORN;
	public static final DeferredBlock<Block> CRYSTALSAND;
	public static final DeferredBlock<Block> PLAGUELOG;
	public static final DeferredBlock<Block> PLAGUELEAVES;
	public static final DeferredBlock<Block> ROTFLYNEST;
	public static final DeferredBlock<Block> PLAGUEPLANKS;
	public static final DeferredBlock<Block> CHANGINGGLASS;
	public static final DeferredBlock<Block> MAZECRYSTAL;
	public static final DeferredBlock<Block> TRICKYSTONEBRICK;
	public static final DeferredBlock<Block> CHAMPIONSTHRONE_PORTAL;
	public static final DeferredBlock<Block> CHAMPIONSGARDEN_PORTAL;
	public static final DeferredBlock<Block> CHAMPIONSLIBRARY_PORTAL;
	public static final DeferredBlock<Block> CHAMPIONSPALACE_PORTAL;
	public static final DeferredBlock<Block> ALTAROFSUFFERING;
	public static final DeferredBlock<Block> K_CHAMPION_SUMMON_ALTAR;
	public static final DeferredBlock<Block> N_CHAMPION_SUMMON_ALTAR;
	public static final DeferredBlock<Block> T_CHAMPION_SUMMON_ALTAR;
	public static final DeferredBlock<Block> S_CHAMPION_SUMMON_ALTAR;
	public static final DeferredBlock<Block> CRYSTALGRASS;
	public static final DeferredBlock<Block> DEMONSTEELORE;
	static {
		OCEANOFSOULS_PORTAL = REGISTRY.register("oceanofsouls_portal", OceanofsoulsPortalBlock::new);
		CHAOSTHORN = REGISTRY.register("chaosthorn", ChaosthornBlock::new);
		CRYSTALSAND = REGISTRY.register("crystalsand", CrystalsandBlock::new);
		PLAGUELOG = REGISTRY.register("plaguelog", PlaguelogBlock::new);
		PLAGUELEAVES = REGISTRY.register("plagueleaves", PlagueleavesBlock::new);
		ROTFLYNEST = REGISTRY.register("rotflynest", RotflynestBlock::new);
		PLAGUEPLANKS = REGISTRY.register("plagueplanks", PlagueplanksBlock::new);
		CHANGINGGLASS = REGISTRY.register("changingglass", ChangingglassBlock::new);
		MAZECRYSTAL = REGISTRY.register("mazecrystal", MazecrystalBlock::new);
		TRICKYSTONEBRICK = REGISTRY.register("trickystonebrick", TrickystonebrickBlock::new);
		CHAMPIONSTHRONE_PORTAL = REGISTRY.register("championsthrone_portal", ChampionsthronePortalBlock::new);
		CHAMPIONSGARDEN_PORTAL = REGISTRY.register("championsgarden_portal", ChampionsgardenPortalBlock::new);
		CHAMPIONSLIBRARY_PORTAL = REGISTRY.register("championslibrary_portal", ChampionslibraryPortalBlock::new);
		CHAMPIONSPALACE_PORTAL = REGISTRY.register("championspalace_portal", ChampionspalacePortalBlock::new);
		ALTAROFSUFFERING = REGISTRY.register("altarofsuffering", AltarofsufferingBlock::new);
		K_CHAMPION_SUMMON_ALTAR = REGISTRY.register("k_champion_summon_altar", KChampionSummonAltarBlock::new);
		N_CHAMPION_SUMMON_ALTAR = REGISTRY.register("n_champion_summon_altar", NChampionSummonAltarBlock::new);
		T_CHAMPION_SUMMON_ALTAR = REGISTRY.register("t_champion_summon_altar", TChampionSummonAltarBlock::new);
		S_CHAMPION_SUMMON_ALTAR = REGISTRY.register("s_champion_summon_altar", SChampionSummonAltarBlock::new);
		CRYSTALGRASS = REGISTRY.register("crystalgrass", CrystalgrassBlock::new);
		DEMONSTEELORE = REGISTRY.register("demonsteelore", DemonsteeloreBlock::new);
	}
	// Start of user code block custom blocks
	// End of user code block custom blocks
}