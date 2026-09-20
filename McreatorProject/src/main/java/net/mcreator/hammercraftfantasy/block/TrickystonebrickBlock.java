package net.mcreator.hammercraftfantasy.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import net.mcreator.hammercraftfantasy.procedures.TrickystonebrickentitymovingProcedure;

public class TrickystonebrickBlock extends Block {
	public TrickystonebrickBlock() {
		super(BlockBehaviour.Properties.of().strength(1.5f, 6f).requiresCorrectToolForDrops());
	}

	@Override
	public void stepOn(Level world, BlockPos pos, BlockState blockstate, Entity entity) {
		super.stepOn(world, pos, blockstate, entity);
		TrickystonebrickentitymovingProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), entity);
	}
}