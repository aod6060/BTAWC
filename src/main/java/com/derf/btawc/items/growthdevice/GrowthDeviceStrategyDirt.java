package com.derf.btawc.items.growthdevice;

import com.derf.btawc.util.Utils;

import net.minecraft.block.BlockGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GrowthDeviceStrategyDirt implements IGrowthDeviceStrategy {

	@Override
	public void update(World world, IBlockState state, BlockPos pos) {
		
		if(Utils.noise(10)) {
			world.setBlockState(pos, Blocks.GRASS.getDefaultState());
		}
	}

}
