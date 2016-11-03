package com.derf.btawc.network.packets;

import java.io.IOException;

import com.derf.btawc.network.data.FactoryPacketData;
import com.derf.btawc.network.data.IPacketData;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * This is a packet that will be sent to the client from 
 * the server. All packet data is from the 
 * com.derf.btawc.network.data.client package.
 * Don't use the server package thats for the 
 * PacketToServer packet.
 * @author Fred
 *
 */
public class PacketToClient implements IMessage {
	// Packet Data
	private IPacketData packetData = FactoryPacketData.createPacketData("passible");

	// For registration
	public PacketToClient() {}
	
	public PacketToClient(IPacketData packetData) {
		this.packetData = packetData;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		PacketBuffer buffer = new PacketBuffer(buf);
		NBTTagCompound compound = null;
		try {
			compound = buffer.readNBTTagCompoundFromBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		// Check if compound is null
		if(compound != null) {
			String name = compound.getString("name");
			this.packetData = FactoryPacketData.createPacketData(name);
			this.packetData.readFromNBT(compound);
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		PacketBuffer buffer = new PacketBuffer(buf);
		NBTTagCompound compound = new NBTTagCompound();
		compound.setString("name", packetData.getName());
		packetData.writeToNBT(compound);
		buffer.writeNBTTagCompoundToBuffer(compound);
	}

	public IPacketData getPacketData() {
		return packetData;
	}
	
	
}
