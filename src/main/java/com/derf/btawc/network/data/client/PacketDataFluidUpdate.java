package com.derf.btawc.network.data.client;

import com.derf.btawc.fluid.FluidTank;
import com.derf.btawc.network.data.IPacketData;
import com.derf.btawc.tileentity.tank.TileEntityTank;
import com.derf.btawc.util.NBTUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketDataFluidUpdate implements IPacketData {

	// Block Pos for the tileentity
	private BlockPos pos;
	// My fluid tank...
	private FluidTank tank = new FluidTank();
	
	// Default Packet for Factory
	public PacketDataFluidUpdate() {}
	
	public PacketDataFluidUpdate(BlockPos pos, FluidTank tank) {
		this.pos = pos;
		this.tank = tank;
	}
	
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

}
