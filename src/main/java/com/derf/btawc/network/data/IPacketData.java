package com.derf.btawc.network.data;

import net.minecraft.nbt.NBTTagCompound;

public interface IPacketData {
	
	void readFromNBT(NBTTagCompound compound);
	
	NBTTagCompound writeToNBT(NBTTagCompound compound);
	
	void updatePacketData();
	
	String getName();
}
