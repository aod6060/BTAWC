package com.derf.btawc.blocks.tileentity.generators;

import java.util.List;

import com.derf.btawc.energy.EnergyStorage;
import com.derf.btawc.util.Holder;

import cofh.api.energy.IEnergyReceiver;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCreativeGenerator extends TileEntityGenerator {
	private static final int MAX_CAPACITY = 134217728;
	// This is the default rf/t
	private int energyTicks = 64;
	// This is the speed upgrades
	public int speedUpgrades = 0;
	// This is insantiy points
	public int insantity = 1;
	// This is the current ticks
	public int currentEnergyTicks = 0;
	
	public TileEntityCreativeGenerator() {
		super();
		this.storage = new EnergyStorage(MAX_CAPACITY);
	}

	@Override
	public void update() {
		
		// Caculate Addition mul for speedUpgrades
		int mul = 1;
		
		for(int i = 0; i < speedUpgrades; i++) {
			mul *= 2;
		}
		
		// Calculate Actual RF/t
		this.currentEnergyTicks = this.energyTicks * mul * this.insantity;
		
		if(this.currentEnergyTicks > this.storage.getCapacity()) {
			this.currentEnergyTicks = this.storage.getCapacity();
		}
		
		int delta = storage.receiveEnergy(this.currentEnergyTicks, true);
		this.storage.receiveEnergy(delta, false);
		
		List<Holder> sides = Holder.getHolders(pos);
		for(Holder side : sides) {
			TileEntity entity = worldObj.getTileEntity(side.getPos());
			
			if(entity != null && entity instanceof IEnergyReceiver) {
				IEnergyReceiver handler = (IEnergyReceiver)entity;
				int ee = this.extractEnergy(side.getDirection(), this.currentEnergyTicks, true);
				int er = handler.receiveEnergy(side.getDirection().getOpposite(), ee, true);
				this.extractEnergy(side.getDirection(), er, false);
				handler.receiveEnergy(side.getDirection().getOpposite(), er, false);
			}
		}
		
		
	}

	@Override
	public String printEnergyValue() {
		String s = String.format("[%d/%d] %d RF/t", this.storage.getEnergyStored(), this.storage.getMaxEnergyStored(), this.currentEnergyTicks);
		return s;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		// TODO Auto-generated method stub
		super.readFromNBT(tag);
		this.currentEnergyTicks = tag.getInteger("CurrentEnergyTicks");
		this.speedUpgrades = tag.getInteger("SpeedUpgrades");
		this.insantity = tag.getInteger("Insanity");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		// TODO Auto-generated method stub
		super.writeToNBT(tag);
		tag.setInteger("CurrentEnergyTicks", this.currentEnergyTicks);
		tag.setInteger("SpeedUpgrades", this.speedUpgrades);
		tag.setInteger("Insanity", this.insantity);
		return tag;
	}

	
}
