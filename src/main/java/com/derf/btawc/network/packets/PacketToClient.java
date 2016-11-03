package com.derf.btawc.network.packets;

import com.derf.btawc.network.data.IPacketData;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PacketToClient implements IMessage {
	
	private IPacketData packetData = null;

	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}
}
