package com.derf.btawc.network.handlers;

import com.derf.btawc.network.packets.PacketToServer;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketToServerHandler implements IMessageHandler<PacketToServer, IMessage> {

	@Override
	public IMessage onMessage(PacketToServer message, MessageContext ctx) {
		if(ctx.side == Side.SERVER) {
			
		}
		
		return null;
	}

}
