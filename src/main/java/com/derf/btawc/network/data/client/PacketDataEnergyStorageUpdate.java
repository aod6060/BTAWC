package com.derf.btawc.network.data.client;

import com.derf.btawc.energy.EnergyStorage;
import com.derf.btawc.network.data.IPacketData;
import com.derf.btawc.network.data.IPacketDataCallback;
import com.derf.btawc.tileentity.generators.TileEntityGenerator;
import com.derf.btawc.util.NBTUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PacketDataEnergyStorageUpdate implements IPacketData {

	private BlockPos pos;
	private EnergyStorage storage = new EnergyStorage(1000);
	
	public PacketDataEnergyStorageUpdate() {}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		this.pos = NBTUtils.loadBlockPos(compound);
		storage.readFromNBT(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		NBTUtils.saveBlockPos(compound, this.pos);
		storage.writeToNBT(compound);
		return compound;
	}

	@Override
	public void updatePacketData() {
		World world = Minecraft.getMinecraft().theWorld;
		
		if(world != null) {
			TileEntity entity = world.getTileEntity(pos);
			
			if(entity instanceof TileEntityGenerator) {
				TileEntityGenerator generator = (TileEntityGenerator)entity;
				generator.setStorage(this.storage);
			}
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "energy_storage_update";
	}

	public BlockPos getPos() {
		return pos;
	}

	public void setPos(BlockPos pos) {
		this.pos = pos;
	}

	public EnergyStorage getStorage() {
		return storage;
	}

	public void setStorage(EnergyStorage storage) {
		this.storage = storage;
	}
	
	private static class Callback implements IPacketDataCallback {
		private BlockPos pos;
		private EnergyStorage storage;
		
		public Callback() {}
		
		public Callback(BlockPos pos, EnergyStorage storage) {
			this.pos = pos;
			this.storage = storage;
		}
		
		@Override
		public void call(IPacketData dataPacket) {
			if(dataPacket instanceof PacketDataEnergyStorageUpdate) {
				PacketDataEnergyStorageUpdate update = (PacketDataEnergyStorageUpdate)dataPacket;
				update.setPos(pos);
				update.setStorage(storage);
			}
		}
		
	}
	
	public static IPacketDataCallback createCallback(BlockPos pos, EnergyStorage storage) {
		return new Callback(pos, storage);
	}
}
