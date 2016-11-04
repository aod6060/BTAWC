package com.derf.btawc.network.packets;

import java.io.IOException;

import com.derf.btawc.network.data.FactoryPacketData;
import com.derf.btawc.network.data.IPacketData;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PacketToServer implements IMessage {

	// Packet Data
	private IPacketData packetData = FactoryPacketData.createPacketData("passible");
	private int dimension = 0;
	
	public PacketToServer() {}
	
	public PacketToServer(IPacketData data, int dimension) {
		this.packetData = data;
		this.dimension = dimension;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		PacketBuffer buffer = new PacketBuffer(buf);
		NBTTagCompound compound = null;
		
		try {
			compound = buffer.readNBTTagCompoundFromBuffer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Check if compound is null
		if(compound != null) {
			this.dimension = compound.getInteger("dimension");
			String name = compound.getString("name");
			this.packetData = FactoryPacketData.createPacketData(name);
			this.packetData.readFromNBT(compound);
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		PacketBuffer buffer = new PacketBuffer(buf);
		NBTTagCompound compound = new NBTTagCompound();
		compound.setInteger("dimension", this.dimension);
		compound.setString("name", packetData.getName());
		packetData.writeToNBT(compound);
		buffer.writeNBTTagCompoundToBuffer(compound);
	}

	public IPacketData getPacketData() {
		return this.packetData;
	}
	
	public int getDimension() {
		return this.dimension;
	}
}
