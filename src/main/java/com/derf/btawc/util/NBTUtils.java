package com.derf.btawc.util;

import com.derf.btawc.tileentity.EnumSixSided;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public final class NBTUtils {
	
	/**
	 * This will load BlockPos from NBT
	 * @param compound
	 * @return
	 */
	public static BlockPos loadBlockPos(NBTTagCompound compound) {
		
		NBTTagCompound blockPos = compound.getCompoundTag("block_pos");
		int x = blockPos.getInteger("x");
		int y = blockPos.getInteger("y");
		int z = blockPos.getInteger("z");
		
		return new BlockPos(x, y, z);
	}
	
	/**
	 * This will save BlockPos to NBT
	 * @param compound
	 * @param pos
	 */
	public static void saveBlockPos(NBTTagCompound compound, BlockPos pos) {
		NBTTagCompound blockPos = new NBTTagCompound();
		blockPos.setInteger("x", pos.getX());
		blockPos.setInteger("y", pos.getY());
		blockPos.setInteger("z", pos.getZ());
		compound.setTag("block_pos", blockPos);
	}
	
	/**
	 * This will load EnumFacing from NBT
	 * @param compound
	 * @return
	 */
	public static EnumFacing loadEnumFacing(NBTTagCompound compound) {
		NBTTagCompound enumFacing = compound.getCompoundTag("enum_facing");
		return EnumFacing.values()[enumFacing.getInteger("facing")];
	}
	
	/**
	 * This will save EnumFacing to NBT
	 * @param compound
	 * @param facing
	 */
	public static void saveEnumFacing(NBTTagCompound compound, EnumFacing facing) {
		NBTTagCompound enumFacing = new NBTTagCompound();
		enumFacing.setInteger("facing", facing.ordinal());
		compound.setTag("enum_facing", enumFacing);
	}
	
	/**
	 * This will load EnumSixSided from NBT
	 * @param compound
	 * @return
	 */
	public static EnumSixSided loadEnumSixSided(NBTTagCompound compound) {
		NBTTagCompound sixSided = compound.getCompoundTag("six_sided");
		return EnumSixSided.values()[sixSided.getInteger("sided")];
	}
	
	/**
	 * This will save EnumSixSided to NBT
	 * @param compound
	 * @param sided
	 */
	public static void saveEnumSixSided(NBTTagCompound compound, EnumSixSided sided) {
		NBTTagCompound sixSided = new NBTTagCompound();
		sixSided.setInteger("sided", sided.ordinal());
		compound.setTag("six_sided", sixSided);
	}
}
