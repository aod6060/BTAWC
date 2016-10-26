package com.derf.btawc.network.packets;

import com.derf.btawc.tileentity.EnumSixSided;
import com.derf.btawc.tileentity.itembuffer.TileEntityItemBuffer;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketItemBuffer implements IMessage {

	private int dimesion;
	private BlockPos pos;
	private EnumFacing facing;
	private EnumSixSided type;
	
	public PacketItemBuffer() {}
	
	public PacketItemBuffer(int dimension, BlockPos pos, EnumFacing facing, EnumSixSided type) {
		this.dimesion = dimension;
		this.pos = pos;
		this.facing = facing;
		this.type = type;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		PacketBuffer buff = new PacketBuffer(buf);
		this.dimesion = buff.readInt();
		this.pos = buff.readBlockPos();
		this.facing = EnumFacing.values()[buff.readInt()];
		this.type = EnumSixSided.values()[buff.readInt()];
	}

	@Override
	public void toBytes(ByteBuf buf) {
		PacketBuffer buff = new PacketBuffer(buf);
		buff.writeInt(this.dimesion);
		buff.writeBlockPos(this.pos);
		buff.writeInt(facing.ordinal());
		buff.writeInt(type.ordinal());
	}

	public void onMessageFromClient(MessageContext ctx) {
		World world = DimensionManager.getWorld(this.dimesion);
		
		if(world != null) {
			TileEntity entity = world.getTileEntity(pos);
			if(entity instanceof TileEntityItemBuffer) {
				TileEntityItemBuffer itembuffer = (TileEntityItemBuffer)entity;
				itembuffer.setType(this.facing, this.type);
			}
		}
	}
	
	public static class Handler implements IMessageHandler<PacketItemBuffer, IMessage> {

		@Override
		public IMessage onMessage(PacketItemBuffer message, MessageContext ctx) {
			
			if(ctx.side == Side.SERVER) {
				message.onMessageFromClient(ctx);
			}
			
			return null;
		}
		
	}
}
