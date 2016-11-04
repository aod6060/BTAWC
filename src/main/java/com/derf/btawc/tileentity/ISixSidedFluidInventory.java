package com.derf.btawc.tileentity;

import java.util.List;

import com.derf.btawc.fluid.FluidTank;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public interface ISixSidedFluidInventory extends ISixSided {
	
	boolean isSixSidedFluidInventory(TileEntity entity);
	
	void drain(EnumFacing face);
	
	void fill(EnumFacing face);
	
	FluidTank getTank();
	
	List<FluidTank> getTanks();
	
}
