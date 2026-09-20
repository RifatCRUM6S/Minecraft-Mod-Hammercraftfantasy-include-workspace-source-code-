package net.mcreator.hammercraftfantasy.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class PlagueplanksBlock extends Block {
	public PlagueplanksBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1f).instrument(NoteBlockInstrument.BASS));
	}
}