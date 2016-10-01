package com.derf.btawc.items.growthdevice;

import java.util.Random;

import com.derf.btawc.util.Utils;

import net.minecraft.block.BlockFarmland;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GrowthDeviceStrategyFarmland implements IGrowthDeviceStrategy {

	@Override
	public void update(World world, IBlockState state, BlockPos pos) {
		int moist = state.getValue(BlockFarmland.MOISTURE);
		if(moist < 7) {
			if(Utils.binaryNoise()) {
				++moist;
				world.setBlockState(pos, state);
			}
		}
	}

}
