package com.derf.btawc.items.growthdevice;

import java.util.Random;

import com.derf.btawc.util.Utils;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GrowthDeviceStrategyCrops implements IGrowthDeviceStrategy {

	@Override
	public void update(World world, IBlockState state, BlockPos pos) {
		int age = state.getValue(BlockCrops.AGE);
		if(age < 7) {
			if(Utils.binaryNoise()) {
				++age;
				world.setBlockState(pos, state.withProperty(BlockCrops.AGE, age));
			}
		}
	}

}
