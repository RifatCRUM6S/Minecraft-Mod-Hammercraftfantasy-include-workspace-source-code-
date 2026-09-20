package net.mcreator.hammercraftfantasy.block;

import net.neoforged.neoforge.common.util.TriState;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import com.mojang.serialization.MapCodec;

public class CrystalsandBlock extends FallingBlock {
	public static final MapCodec<CrystalsandBlock> CODEC = simpleCodec(properties -> new CrystalsandBlock());

	public MapCodec<CrystalsandBlock> codec() {
		return CODEC;
	}

	public CrystalsandBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.ICE).sound(SoundType.SAND).strength(0.5f, 10f).instrument(NoteBlockInstrument.SNARE));
	}

	@Override
	public TriState canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction direction, BlockState plant) {
		return TriState.TRUE;
	}
}