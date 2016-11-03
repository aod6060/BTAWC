package com.derf.btawc.network.handlers;

import com.derf.btawc.network.CallablePacketHandler;
import com.derf.btawc.network.RunnablePacketHandler;
import com.derf.btawc.network.packets.PacketToClient;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketToClientHandler implements IMessageHandler<PacketToClient, IMessage> {

	@Override
	public IMessage onMessage(PacketToClient message, MessageContext ctx) {
		
		if(ctx.side == Side.CLIENT) {
			Minecraft.getMinecraft().addScheduledTask(new RunnablePacketHandler(message.getPacketData()));
			//Minecraft.getMinecraft().addScheduledTask(new CallablePacketHandler(message.getPacketData()));
		}
		
		return null;
	}
	
}
