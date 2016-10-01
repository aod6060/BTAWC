package com.derf.btawc.items.growthdevice;

import java.util.Random;

import com.derf.btawc.util.Utils;

import net.minecraft.block.BlockBeetroot;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GrowthDeviceStrategyBeetroot implements IGrowthDeviceStrategy {

	@Override
	public void update(World world, IBlockState state, BlockPos pos) {
		int age = state.getValue(BlockBeetroot.BEETROOT_AGE);
		if(age < 3) {
			if(Utils.binaryNoise()) {
				++age;
				world.setBlockState(pos, state.withProperty(BlockBeetroot.BEETROOT_AGE, age));
			}
		}
	}

}
