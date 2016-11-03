package com.derf.btawc.util;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

public final class NBTUtils {
	
	/**
	 * This will load block pos from NBT
	 * @param compound
	 * @return
	 */
	public static BlockPos loadBlockPos(NBTTagCompound compound) {
		int x = compound.getInteger("pos_x");
		int y = compound.getInteger("pos_y");
		int z = compound.getInteger("pos_z");
		return new BlockPos(x, y, z);
	}
	
	/**
	 * This will save Block Pos to NBT
	 * @param compound
	 * @param pos
	 */
	public static void saveBlockPos(NBTTagCompound compound, BlockPos pos) {
		compound.setInteger("pos_x", pos.getX());
		compound.setInteger("pos_y", pos.getY());
		compound.setInteger("pos_z", pos.getZ());
	}
}
