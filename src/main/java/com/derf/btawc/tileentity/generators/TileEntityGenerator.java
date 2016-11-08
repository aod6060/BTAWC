package com.derf.btawc.tileentity.generators;

import java.util.List;

import com.derf.btawc.energy.EnergyStorage;
import com.derf.btawc.energy.IEnergyLevelPrintable;
import com.derf.btawc.network.PacketHandler;
import com.derf.btawc.network.data.FactoryPacketData;
import com.derf.btawc.network.data.client.PacketDataEnergyStorageUpdate;
import com.derf.btawc.tileentity.TileEntityBasic;
import com.derf.btawc.util.Holder;

import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class TileEntityGenerator extends TileEntityBasic implements ITickable, IEnergyProvider, IEnergyLevelPrintable {
	
	protected EnergyStorage storage = null;

	@Override
	public int getEnergyStored(EnumFacing from) {
		// TODO Auto-generated method stub
		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(EnumFacing from) {
		// TODO Auto-generated method stub
		return storage.getMaxEnergyStored();
	}

	@Override
	public boolean canConnectEnergy(EnumFacing from) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int extractEnergy(EnumFacing from, int maxExtract, boolean simulate) {
		// TODO Auto-generated method stub
		return storage.extractEnergy(maxExtract, simulate);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		storage.readFromNBT(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		return storage.writeToNBT(super.writeToNBT(compound));
	}
	
	@Override
	public String printEnergyValue() {
		String s = String.format("[%d/%d] %d RF/t", this.storage.getEnergyStored(), this.storage.getMaxEnergyStored(), this.storage.getMaxReceive());
		return s;
	}
	
	@SideOnly(Side.CLIENT)
	public int getEnergyLevelScaled(int scale) {
		double ret = (double)this.storage.getEnergyStored() * scale / (double)this.storage.getMaxEnergyStored();
		return (int)ret;
	}
	
	public EnergyStorage getStorage() {
		return this.storage;
	}
	
	public void setStorage(EnergyStorage storage) {
		// Copy stuff over the client doen't just copy instances.
		this.storage = new EnergyStorage(storage.getEnergy(), storage.getCapacity(), storage.getMaxReceive(), storage.getMaxExtract());
	}
	
	protected void storeIntoBuffer() {
		int value = this.storage.receiveEnergy(this.getStorage().getMaxReceive(), true);
		this.storage.receiveEnergy(value, false);
	}
	
	protected void outputAllSides() {
		List<Holder> sides = Holder.getHolders(pos);
		for(Holder side : sides) {
			TileEntity entity = worldObj.getTileEntity(side.getPos());
			if(entity != null && entity instanceof IEnergyReceiver) {
				IEnergyReceiver handler = (IEnergyReceiver)entity;
				int ee = this.extractEnergy(side.getDirection(), this.getStorage().getMaxExtract(), true);
				int er = handler.receiveEnergy(side.getDirection().getOpposite(), ee, true);
				this.extractEnergy(side.getDirection(), er, false);
				handler.receiveEnergy(side.getDirection().getOpposite(), er, false);
			}
		}
	}
	
	protected void onEnergyUpdate() {
		caculateRFTicks();
		this.storeIntoBuffer();
		this.outputAllSides();
		this.sendToClient();
	}
	
	protected void sendToClient() {
		PacketHandler.sendPacketToClient(
				FactoryPacketData.createPacketData("energy_storage_update", 
						PacketDataEnergyStorageUpdate.createCallback(pos, storage)));
	}
	// Abstract Methods
	protected abstract void caculateRFTicks();


}
