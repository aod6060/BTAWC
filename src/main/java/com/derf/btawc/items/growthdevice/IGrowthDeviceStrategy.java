package com.derf.btawc.items.growthdevice;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IGrowthDeviceStrategy {
	void update(World world, IBlockState state, BlockPos pos);
}
