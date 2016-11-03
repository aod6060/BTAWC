package com.derf.btawc.network.data.client;

import com.derf.btawc.fluid.FluidTank;
import com.derf.btawc.network.data.IPacketData;
import com.derf.btawc.network.data.IPacketDataCallback;
import com.derf.btawc.tileentity.tank.TileEntityTank;
import com.derf.btawc.util.NBTUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PacketDataFluidUpdate implements IPacketData {

	// Block Pos for the tileentity
	private BlockPos pos;
	// My fluid tank...
	private FluidTank tank = new FluidTank();
	
	// Default Packet for Factory
	public PacketDataFluidUpdate() {}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		// Load Block Pos
		this.pos = NBTUtils.loadBlockPos(compound);
		tank.readFromNBT(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		NBTUtils.saveBlockPos(compound, pos);
		tank.writeToNBT(compound);
		return compound;
	}

	@Override
	public void updatePacketData() {
		World world = Minecraft.getMinecraft().theWorld;
		
		if(world != null) {
			TileEntity entity = world.getTileEntity(this.pos);
			
			if(entity instanceof TileEntityTank) {
				TileEntityTank tank = (TileEntityTank)entity;
				tank.setTank(this.tank);
			}
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "fluid_update";
	}

	public BlockPos getPos() {
		return pos;
	}

	public void setPos(BlockPos pos) {
		this.pos = pos;
	}

	public FluidTank getTank() {
		return tank;
	}

	public void setTank(FluidTank tank) {
		this.tank = tank;
	}
	
	private static class Callback implements IPacketDataCallback {

		private BlockPos pos;
		private FluidTank tank;
		
		public Callback() {}
		
		public Callback(BlockPos pos, FluidTank tank) {
			this.tank = tank;
			this.pos = pos;
		}
		
		@Override
		public void call(IPacketData dataPacket) {
			if(dataPacket instanceof PacketDataFluidUpdate) {
				PacketDataFluidUpdate update = (PacketDataFluidUpdate)dataPacket;
				update.setPos(pos);
				update.setTank(tank);
			}
		}
	}
	
	public static IPacketDataCallback createCallback(BlockPos pos, FluidTank tank) {
		return new Callback(pos, tank);
	}
}
