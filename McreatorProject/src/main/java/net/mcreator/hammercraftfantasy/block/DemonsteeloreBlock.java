package net.mcreator.hammercraftfantasy.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class DemonsteeloreBlock extends Block {
	public DemonsteeloreBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.DEEPSLATE_TILES).strength(6.5f, 6f).requiresCorrectToolForDrops());
	}
}