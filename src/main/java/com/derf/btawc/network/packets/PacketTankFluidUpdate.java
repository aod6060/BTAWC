package com.derf.btawc.network.packets;

import java.io.IOException;

import com.derf.btawc.fluid.FluidTank;
import com.derf.btawc.tileentity.tank.TileEntityTank;
import com.google.common.util.concurrent.ListenableFuture;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

/**
 * This is will push an update to the client side from server when tanks are involved.
 * @author Fred
 *
 */
public class PacketTankFluidUpdate implements IMessage, Runnable {
	private BlockPos pos;
	// Tank 
	private FluidTank tank = new FluidTank();
	
	public PacketTankFluidUpdate() {}
	
	public PacketTankFluidUpdate(BlockPos pos, FluidTank tank) {
		this.pos = pos;
		this.tank = tank;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		PacketBuffer buff = new PacketBuffer(buf);
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
		buff.writeBlockPos(this.pos);
		NBTTagCompound comp = new NBTTagCompound();
		tank.writeToNBT(comp);
		buff.writeNBTTagCompoundToBuffer(comp);
	}
	
	public static class Handler implements IMessageHandler<PacketTankFluidUpdate, IMessage> {

		@Override
		public IMessage onMessage(PacketTankFluidUpdate message, MessageContext ctx) {
			
			if(ctx.side == Side.CLIENT) {
				Minecraft.getMinecraft().addScheduledTask(message);
			}
			
			return null;
		}
		
	}

	@Override
	public void run() {
		World world = Minecraft.getMinecraft().theWorld;
		
		if(world != null) {
			TileEntity entity = world.getTileEntity(pos);
			
			if(entity instanceof TileEntityTank) {
				TileEntityTank tank = (TileEntityTank)entity;
				tank.setTank(this.tank);
			}
		}
	}
}
