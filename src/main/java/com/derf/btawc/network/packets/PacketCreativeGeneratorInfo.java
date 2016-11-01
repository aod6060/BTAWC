package com.derf.btawc.network.packets;

import com.derf.btawc.tileentity.generators.TileEntityCreativeGenerator;

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

public class PacketCreativeGeneratorInfo implements IMessage, Runnable {

	private int dimension;
	private BlockPos pos;
	private int insantity = 0;
	
	public PacketCreativeGeneratorInfo() {}
	
	public PacketCreativeGeneratorInfo(int dimension, BlockPos pos, int insantity) {
		this.dimension = dimension;
		this.pos = pos;
		this.insantity = insantity;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		PacketBuffer buff = new PacketBuffer(buf);
		dimension = buff.readInt();
		pos = buff.readBlockPos();
		insantity = buff.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		PacketBuffer buff = new PacketBuffer(buf);
		buff.writeInt(dimension);
		buff.writeBlockPos(pos);
		buff.writeInt(this.insantity);
	}
	
	
	public int getDimension() {
		return dimension;
	}


	public static class Handler implements IMessageHandler<PacketCreativeGeneratorInfo, IMessage> {

		@Override
		public IMessage onMessage(PacketCreativeGeneratorInfo message, MessageContext ctx) {
			
			if(ctx.side == Side.SERVER) {
				DimensionManager.getWorld(message.getDimension()).addScheduledTask(message);
			}
			return null;
		}
		
	}

	@Override
	public void run() {
		World world = DimensionManager.getWorld(this.dimension);
		
		if(world != null) {
			TileEntity entity = world.getTileEntity(pos);
			
			if(entity instanceof TileEntityCreativeGenerator) {
				TileEntityCreativeGenerator generator = (TileEntityCreativeGenerator)entity;
				generator.insantity = this.insantity;
			}
		}
	}
}
