package com.derf.btawc.network.handlers;

import com.derf.btawc.network.RunnablePacketHandler;
import com.derf.btawc.network.packets.PacketToServer;

import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketToServerHandler implements IMessageHandler<PacketToServer, IMessage> {

	@Override
	public IMessage onMessage(PacketToServer message, MessageContext ctx) {
		if(ctx.side == Side.SERVER) {
			int dimension = message.getDimension();
			DimensionManager.getWorld(dimension).addScheduledTask(new RunnablePacketHandler(message.getPacketData(), dimension));
		}
		
		return null;
	}

}
