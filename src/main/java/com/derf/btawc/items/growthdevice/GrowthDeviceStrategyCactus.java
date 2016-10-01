package com.derf.btawc.items.growthdevice;

import com.derf.btawc.util.Utils;

import net.minecraft.block.BlockCactus;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GrowthDeviceStrategyCactus implements IGrowthDeviceStrategy {

	@Override
	public void update(World world, IBlockState state, BlockPos pos) {
		int age = state.getValue(BlockCactus.AGE);
		
		if(age < 15) {
			if(Utils.binaryNoise()) {
				++age;
				world.setBlockState(pos, state.withProperty(BlockCactus.AGE, age));
			}
		}
	}

}
