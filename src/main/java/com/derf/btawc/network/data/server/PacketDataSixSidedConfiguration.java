package com.derf.btawc.network.data.server;

import com.derf.btawc.network.data.IPacketData;

import net.minecraft.nbt.NBTTagCompound;

public class PacketDataSixSidedConfiguration implements IPacketData {

	@Override
	public void readFromNBT(NBTTagCompound compound) {
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		return compound;
	}

	@Override
	public void updatePacketData() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "six_sided_configuration";
	}

}
