package com.derf.btawc.blocks.tileentity.generators;

import java.util.List;

import com.derf.btawc.energy.EnergyStorage;
import com.derf.btawc.util.Holder;

import cofh.api.energy.IEnergyReceiver;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCreativeGenerator extends TileEntityGenerator {
	
	public TileEntityCreativeGenerator() {
		super();
		this.storage = new EnergyStorage(1000000, 1000);
	}
	
	protected void updateTileEntities() {
		List<Holder> sides = Holder.getHolders(pos);
		for(Holder side : sides) {
			TileEntity entity = worldObj.getTileEntity(side.getPos());
			
			if(entity != null && entity instanceof IEnergyReceiver) {
				IEnergyReceiver handler = (IEnergyReceiver)entity;
				int ee = this.extractEnergy(side.getDirection(), storage.getMaxExtract(), true);
				int er = handler.receiveEnergy(side.getDirection().getOpposite(), ee, true);
				this.extractEnergy(side.getDirection(), er, false);
				handler.receiveEnergy(side.getDirection().getOpposite(), er, false);
			}
		}
	}

	protected void updateGeneratorInternalStorage() {
		int delta = storage.receiveEnergy(storage.getMaxReceive(), true);
		this.storage.receiveEnergy(delta, false);
	}

}
