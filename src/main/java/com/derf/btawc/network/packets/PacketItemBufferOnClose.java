package com.derf.btawc.network.packets;

import com.derf.btawc.tileentity.itembuffer.TileEntityItemBuffer;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketItemBufferOnClose implements IMessage {

	private int dimesion;
	private BlockPos pos;
	
	public PacketItemBufferOnClose() {}
	
	public PacketItemBufferOnClose(int dimension, BlockPos pos) {
		this.dimesion = dimension;
		this.pos = pos;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		PacketBuffer buff = new PacketBuffer(buf);
		this.dimesion = buff.readInt();
		this.pos = buff.readBlockPos();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		PacketBuffer buff = new PacketBuffer(buf);
		buff.writeInt(this.dimesion);
		buff.writeBlockPos(this.pos);
	}

	public void onMessageFromClient(MessageContext ctx) {
		World world = DimensionManager.getWorld(this.dimesion);
		
		if(world != null) {
			TileEntity entity = world.getTileEntity(pos);
			if(entity instanceof TileEntityItemBuffer) {
				TileEntityItemBuffer itembuffer = (TileEntityItemBuffer)entity;
				//itembuffer.updateBlockState();
			}
		}
	}
	
	public static class Handler implements IMessageHandler<PacketItemBufferOnClose, IMessage> {

		@Override
		public IMessage onMessage(PacketItemBufferOnClose message, MessageContext ctx) {
			
			if(ctx.side == Side.SERVER) {
				message.onMessageFromClient(ctx);
			}
			
			return null;
		}

		
	}
}
