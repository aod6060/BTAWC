package com.derf.btawc.items.growthdevice;

import java.util.Random;

import com.derf.btawc.util.Utils;

import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GrowthDeviceStrategyDefault implements IGrowthDeviceStrategy {

	@Override
	public void update(World world, IBlockState state, BlockPos pos) {
		if(Utils.binaryNoise()) {
			IGrowable growable = (IGrowable)state.getBlock();
			if(growable.canGrow(world, pos, state, false)) {
				growable.grow(world, Utils.rand, pos, state);
			}
		}
	}

}
