package com.derf.btawc.network.data.server;

import com.derf.btawc.network.data.IPacketData;
import com.derf.btawc.network.data.IPacketDataCallback;
import com.derf.btawc.network.data.IPacketDataServer;
import com.derf.btawc.tileentity.EnumSixSided;
import com.derf.btawc.tileentity.ISixSided;
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
			TileEntity entity = world.getTileEntity(this.pos);
			if(entity instanceof ISixSided) {
				ISixSided s = (ISixSided)entity;
				s.setType(this.facing, type);
			}
		}
	}

	public BlockPos getPos() {
		return pos;
	}

	public void setPos(BlockPos pos) {
		this.pos = pos;
	}

	public EnumFacing getFacing() {
		return facing;
	}

	public void setFacing(EnumFacing facing) {
		this.facing = facing;
	}

	public EnumSixSided getType() {
		return type;
	}

	public void setType(EnumSixSided type) {
		this.type = type;
	}

	private static class Callback implements IPacketDataCallback {
		private BlockPos pos;
		private EnumFacing facing;
		private EnumSixSided type;
		
		public Callback() {}
		
		public Callback(BlockPos pos, EnumFacing facing, EnumSixSided type) {
			this.pos = pos;
			this.facing = facing;
			this.type = type;
		}
		
		@Override
		public void call(IPacketData dataPacket) {
			if(dataPacket instanceof PacketDataSixSidedConfiguration) {
				PacketDataSixSidedConfiguration conf = (PacketDataSixSidedConfiguration)dataPacket;
				conf.setPos(this.pos);
				conf.setFacing(facing);
				conf.setType(type);
			}
		}
		
	}
	
	public static IPacketDataCallback createCallback(BlockPos pos, EnumFacing facing, EnumSixSided sided) {
		return new Callback(pos, facing, sided);
	}
}
