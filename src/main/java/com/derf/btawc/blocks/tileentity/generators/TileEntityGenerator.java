package com.derf.btawc.blocks.tileentity.generators;

import java.util.List;

import com.derf.btawc.blocks.tileentity.TileEntityBasic;
import com.derf.btawc.energy.EnergyStorage;
import com.derf.btawc.energy.IEnergyLevelPrintable;
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
	
	protected void storeIntoBuffer(int currentEnergyTicks) {
		int value = this.storage.receiveEnergy(currentEnergyTicks, true);
		this.storage.receiveEnergy(value, false);
	}
	
	protected void outputAllSides(int currentEnergyTicks) {
		List<Holder> sides = Holder.getHolders(pos);
		for(Holder side : sides) {
			TileEntity entity = worldObj.getTileEntity(side.getPos());
			if(entity != null && entity instanceof IEnergyReceiver) {
				IEnergyReceiver handler = (IEnergyReceiver)entity;
				int ee = this.extractEnergy(side.getDirection(), currentEnergyTicks, true);
				int er = handler.receiveEnergy(side.getDirection().getOpposite(), ee, true);
				this.extractEnergy(side.getDirection(), er, false);
				handler.receiveEnergy(side.getDirection().getOpposite(), er, false);
			}
		}
	}
	
	protected void onEnergyUpdate(int currentEnergyTicks) {
		caculateRFTicks();
		this.storeIntoBuffer(currentEnergyTicks);
		this.outputAllSides(currentEnergyTicks);
	}
	
	// Abstract Methods
	protected abstract void caculateRFTicks();
}
