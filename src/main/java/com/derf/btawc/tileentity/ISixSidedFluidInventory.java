package com.derf.btawc.tileentity;

import java.util.List;

import com.derf.btawc.fluid.FluidTank;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public interface ISixSidedFluidInventory extends ISixSided {
	void setType(EnumFacing side, EnumSixSided type);
	
	EnumSixSided getType(EnumFacing side);
	
	boolean isTypeOff(EnumFacing side);
	
	boolean isTypePull(EnumFacing side);
	
	boolean isTypePush(EnumFacing side);
	
	boolean isTypeDisabled(EnumFacing side);
	
	boolean isSixSidedFluidInventory(TileEntity entity);
	
	List<EnumSixSided> getAllTypes();
	
	void drain(EnumFacing face);
	
	void fill(EnumFacing face);
	
	FluidTank getTank();
	
	List<FluidTank> getTanks();
	
}
