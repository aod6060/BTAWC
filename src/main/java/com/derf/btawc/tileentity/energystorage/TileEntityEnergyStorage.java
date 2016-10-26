package com.derf.btawc.tileentity.energystorage;

import java.util.List;

import com.derf.btawc.energy.EnergyStorage;
import com.derf.btawc.energy.IEnergyLevelPrintable;
import com.derf.btawc.tileentity.TileEntityBasic;
import com.derf.btawc.util.Holder;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public abstract class TileEntityEnergyStorage extends TileEntityBasic implements IEnergyProvider, IEnergyReceiver, IEnergyLevelPrintable {

	protected EnergyStorage storage = null;
	
	/*
	@Override
	public void updateEntity() {
		// TODO Auto-generated method stub
		super.updateEntity();
		
		BlockPos pos = new BlockPos(this.xCoord, this.yCoord, this.zCoord);
		List<Holder> sides = Holder.getHolders(pos);
		
		for(Holder side : sides) {
			TileEntity entity = WorldUtils.getTileEntity(worldObj, side.getPos());
			
			if(entity != null && entity instanceof IEnergyReceiver) {
				IEnergyReceiver handler = (IEnergyReceiver)entity;
				int ee = this.extractEnergy(side.getDirection(), storage.getMaxExtract(), true);
				int er = handler.receiveEnergy(side.getDirection().getOpposite(), ee, true);
				this.extractEnergy(side.getDirection(), er, false);
				handler.receiveEnergy(side.getDirection().getOpposite(), er, false);
			}
			
			
		}
	}
	*/
	
	/*
	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		// TODO Auto-generated method stub
		return true;
	}
	*/
	
	/*
	@Override
	public void printEnergyValue(EntityPlayer player) {
		String s = String.format("Energy Level: [%d/%d] transfers %d RF/t", this.getEnergyStored(ForgeDirection.UNKNOWN), this.getMaxEnergyStored(ForgeDirection.UNKNOWN), this.storage.getMaxReceive());
		player.addChatMessage(new ChatComponentText(s));
	}
	*/
	@Override
	public String printEnergyValue() {
		String s = String.format("Energy Level: [%d/%d] transfers %d RF/t", this.storage.getEnergyStored(), this.storage.getMaxEnergyStored(), this.storage.getMaxReceive());
		return s;
	}
	/*
	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		return this.storage.receiveEnergy(maxReceive, simulate);
	}
	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
		return this.storage.extractEnergy(maxExtract, simulate);
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		// TODO Auto-generated method stub
		return this.storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		// TODO Auto-generated method stub
		return this.storage.getMaxEnergyStored();
	}
	*/
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		// TODO Auto-generated method stub
		super.readFromNBT(tag);
		storage.readFromNBT(tag);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		// TODO Auto-generated method stub
		super.writeToNBT(tag);
		storage.writeToNBT(tag);
		return tag;
	}
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
	public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int extractEnergy(EnumFacing from, int maxExtract, boolean simulate) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
