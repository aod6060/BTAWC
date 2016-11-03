package com.derf.btawc.network.data;

import net.minecraft.nbt.NBTTagCompound;

/**
 * This is a simple implementation of the IPacketData that 
 * passes the stuff back to the callable methods. Its simply
 * used to make sure that there are no null exceptions.
 * @author Fred
 *
 */
public class PacketDataPassible implements IPacketData {

	@Override
	public void readFromNBT(NBTTagCompound compound) {}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		return compound;
	}

	@Override
	public void updatePacketData() {
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "passible";
	}

}
