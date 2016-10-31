package com.derf.btawc.network.packets;

import java.io.IOException;

import com.derf.btawc.BTAWCLogger;
import com.derf.btawc.fluid.FluidTank;
import com.derf.btawc.tileentity.tank.TileEntityTank;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

/**
 * This is will push an update to the client side from server when tanks are involved.
 * @author Fred
 *
 */
public class PacketTankFluidUpdate implements IMessage {
	
	private int dimension;
	private BlockPos pos;
	// Tank 
	private FluidTank tank = new FluidTank();
	
	public PacketTankFluidUpdate() {}
	
	public PacketTankFluidUpdate(World world, BlockPos pos, FluidTank tank) {
		this.dimension = world.provider.getDimension();
		this.pos = pos;
		this.tank = tank;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		PacketBuffer buff = new PacketBuffer(buf);
		this.dimension = buff.readInt();
		this.pos = buff.readBlockPos();
		try {
			this.tank.readFromNBT(buff.readNBTTagCompoundFromBuffer());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		PacketBuffer buff = new PacketBuffer(buf);
		buff.writeInt(this.dimension);
		buff.writeBlockPos(this.pos);
		NBTTagCompound comp = new NBTTagCompound();
		tank.writeToNBT(comp);
		buff.writeNBTTagCompoundToBuffer(comp);
	}
	
	public void onMessageFromServer(MessageContext ctx) {
		World world = DimensionManager.getWorld(this.dimension);
		if(world != null) {
			TileEntity entity = world.getTileEntity(pos);
			
			if(entity instanceof TileEntityTank) {
				TileEntityTank tank = (TileEntityTank)entity;
				tank.setTank(this.tank);
			}
		}
	}
	
	public static class Handler implements IMessageHandler<PacketTankFluidUpdate, IMessage> {

		@Override
		public IMessage onMessage(PacketTankFluidUpdate message, MessageContext ctx) {
			
			if(ctx.side == Side.CLIENT) {
				message.onMessageFromServer(ctx);
			}
			
			return null;
		}
		
	}


}
