package com.derf.btawc.blocks.energystorage;

import com.derf.btawc.tileentity.energystorage.TileEntityEnergyStorageBasic;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockEnergyStorageBasic extends BlockEnergyStorage {

	public BlockEnergyStorageBasic() {
		super("energy_storage_basic");
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		// TODO Auto-generated method stub
		return new TileEntityEnergyStorageBasic();
	}

}
