package com.derf.btawc.fluid;

import net.minecraftforge.fluids.FluidStack;

/**
 * Simple Adapter for the minecraftforge tank because it doesn't use an
 * Interface to check if the Tank can fill or drain...
 * @author Fred
 *
 */
public class FluidTankChecksAdapter implements IFluidTankChecks{

	private net.minecraftforge.fluids.FluidTank tank = null;
	
	public FluidTankChecksAdapter(net.minecraftforge.fluids.FluidTank tank) {
		this.tank = tank;
	}
	
	@Override
	public boolean canFill() {
		return tank.canFill();
	}

	@Override
	public boolean canDrain() {
		return tank.canDrain();
	}

	@Override
	public boolean canFillFluidType(FluidStack fluid) {
		// TODO Auto-generated method stub
		return tank.canFillFluidType(fluid);
	}

	@Override
	public boolean canDrainFluidType(FluidStack fluid) {
		// TODO Auto-generated method stub
		return tank.canDrainFluidType(fluid);
	}
	
	
}
