package com.derf.btawc.network.data.server;

import com.derf.btawc.network.data.IPacketDataServer;
import com.derf.btawc.tileentity.EnumSixSided;
import com.derf.btawc.tileentity.itembuffer.TileEntityItemBuffer;
import com.derf.btawc.tileentity.tank.TileEntityTank;
import com.derf.btawc.util.NBTUtils;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class PacketDataSixSidedConfiguration implements IPacketDataServer {

	private BlockPos pos;
	private EnumFacing facing;
	private EnumSixSided type;
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		pos = NBTUtils.loadBlockPos(compound);
		this.facing = NBTUtils.loadEnumFacing(compound);
		this.type = NBTUtils.loadEnumSixSided(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		NBTUtils.saveBlockPos(compound, pos);
		NBTUtils.saveEnumFacing(compound, facing);
		NBTUtils.saveEnumSixSided(compound, type);
		return compound;
	}

	@Override
	public void updatePacketData() {} // This Sound not be called...

	@Override
	public String getName() {
		return "six_sided_configuration";
	}

	@Override
	public void updatePacketData(int dimension) {
		World world = DimensionManager.getWorld(dimension);
		if(world != null) {
			TileEntity entity = world.getTileEntity(pos);
			if(entity instanceof TileEntityItemBuffer) {
				TileEntityItemBuffer itemBuffer = (TileEntityItemBuffer)entity;
				itemBuffer.setType(this.facing, type);
			} else if(entity instanceof TileEntityTank) {
				TileEntityTank tank = (TileEntityTank)entity;
			}
		}
	}

}
