package com.derf.btawc.blocks.tileentity.energystorage;

import com.derf.btawc.energy.EnergyStorage;

public class TileEntityEnergyStorageBasic extends TileEntityEnergyStorage {
	
	public TileEntityEnergyStorageBasic() {
		this.storage = new EnergyStorage(10000, 100);
	}
}
