package com.derf.btawc.blocks.tileentity.generators;

import com.derf.btawc.blocks.tileentity.TileEntityBasic;
import com.derf.btawc.energy.EnergyStorage;
import com.derf.btawc.energy.IEnergyLevelPrintable;

import cofh.api.energy.IEnergyProvider;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class TileEntityGenerator extends TileEntityBasic implements ITickable, IEnergyProvider, IEnergyLevelPrintable {
	
	protected EnergyStorage storage = null;
	
	@Override
	public void update() {
		// Update Generator per tick
		updateGeneratorInternalStorage();
		// Update TileEntities with the internal storage
		updateTileEntities();
	}
	
	protected abstract void updateTileEntities();

	protected abstract void updateGeneratorInternalStorage();

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
