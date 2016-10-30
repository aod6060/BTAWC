package com.derf.btawc.fluid;

import net.minecraftforge.fluids.FluidStack;

public interface IFluidTankChecks {
	boolean canFill();
	
	boolean canDrain();
	
	boolean canFillFluidType(FluidStack fluid);
	
	boolean canDrainFluidType(FluidStack fluid);
	
	boolean isFluidTankFull();
	
	boolean isFluidTankEmpty();
}
