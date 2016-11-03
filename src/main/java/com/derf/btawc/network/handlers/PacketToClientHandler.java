package com.derf.btawc.network.handlers;

import com.derf.btawc.network.packets.PacketToClient;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketToClientHandler implements IMessageHandler<PacketToClient, IMessage> {

	@Override
	public IMessage onMessage(PacketToClient message, MessageContext ctx) {
		
		if(ctx.side == Side.CLIENT) {
			
		}
		
		return null;
	}
	
}
