package com.derf.btawc.fluid;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

public class FluidTankProperties implements IFluidTankProperties {

	private final IFluidTank tank;
	
	public FluidTankProperties(IFluidTank tank) {
		this.tank = tank;
	}
	
	@Override
	public FluidStack getContents() {
		FluidStack stack = tank.getFluid();
		return stack == null? null : stack.copy();
	}

	@Override
	public int getCapacity() {

		return tank.getCapacity();
	}

	@Override
	public boolean canFill() {
		
		boolean b = false;
		
		IFluidTankChecks checks = null;
		
		if(tank instanceof IFluidTankChecks) {
			checks = (IFluidTankChecks)tank;
		} else {
			checks = new FluidTankChecksAdapter((net.minecraftforge.fluids.FluidTank)tank);
		}
		
		return checks.canFill();
	}

	@Override
	public boolean canDrain() {
		
		boolean b = false;
		
		IFluidTankChecks checks = null;
		
		if(tank instanceof IFluidTankChecks) {
			checks = (IFluidTankChecks)tank;
		} else {
			checks = new FluidTankChecksAdapter((net.minecraftforge.fluids.FluidTank)tank);
		}
		
		return checks.canDrain();
	}

	@Override
	public boolean canFillFluidType(FluidStack fluidStack) {
		return this.canFill();
	}

	@Override
	public boolean canDrainFluidType(FluidStack fluidStack) {
		return this.canDrain();
	}

}
