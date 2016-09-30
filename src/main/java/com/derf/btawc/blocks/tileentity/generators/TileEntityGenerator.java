package com.derf.btawc.blocks.tileentity.generators;

import com.derf.btawc.blocks.tileentity.TileEntityBasic;
import com.derf.btawc.energy.EnergyStorage;
import com.derf.btawc.energy.IEnergyLevelPrintable;

import cofh.api.energy.IEnergyProvider;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class TileEntityGenerator extends TileEntityBasic implements IEnergyProvider, IEnergyLevelPrintable {
	
	protected EnergyStorage storage = null;
	
	/*
	@Override
	public void updateEntity() {
		// TODO Auto-generated method stub
		super.updateEntity();
		// Update Generator per tick
		updateGeneratorInternalStorage();
		// Update TileEntities with the internal storage
		updateTileEntities();
	}
	*/
	
	protected abstract void updateTileEntities();

	protected abstract void updateGeneratorInternalStorage();

	@Override
	public int getEnergyStored(EnumFacing from) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxEnergyStored(EnumFacing from) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean canConnectEnergy(EnumFacing from) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int extractEnergy(EnumFacing from, int maxExtract, boolean simulate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		storage.readFromNBT(tag);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		storage.writeToNBT(tag);
		return tag;
	}
	
	@Override
	public String printEnergyValue() {
		String s = String.format("[%d/%d] %d RF/t", this.storage.getEnergyStored(), this.storage.getMaxEnergyStored(), this.storage.getMaxReceive());
		return s;
	}
	
	@SideOnly(Side.CLIENT)
	public int getEnergyLevelScaled(int scale) {
		if(this.storage.getEnergyStored() == 0) {
			return 0;
		} else {
			return this.storage.getEnergyStored() * scale / this.storage.getMaxEnergyStored();
		}
	}
	
	public EnergyStorage getStorage() {
		return this.storage;
	}
}
