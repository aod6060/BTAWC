package com.derf.btawc.util;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class WorldUtils {
	public static TileEntity getTileEntity(World world, BlockPos pos) {
		return world.getTileEntity(pos.getX(), pos.getY(), pos.getZ());
	}
	
	public static Block getBlock(World world, BlockPos pos) {
		return world.getBlock(pos.getX(), pos.getY(), pos.getZ());
	}
}
