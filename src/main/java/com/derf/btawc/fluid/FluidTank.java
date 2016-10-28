package com.derf.btawc.fluid;

import javax.annotation.Nullable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

public class FluidTank implements IFluidTank, IFluidHandler, IFluidTankChecks {

	private FluidStack fluid = null;
	private int capacity;
	private IFluidTankProperties[] tankProps = null;
	private boolean canFill = true;
	private boolean canDrain = true;
	
	public FluidTank() {
		this(1000);
	}
	
	public FluidTank(int capacity) {
		this(null, capacity);
	}
	
	public FluidTank(@Nullable FluidStack fluid, int capacity) {
		this.fluid = fluid;
		this.capacity = capacity;
	}
	
	@Override
	public IFluidTankProperties[] getTankProperties() {
		if(this.tankProps == null) {
			this.tankProps = new IFluidTankProperties[] { new FluidTankProperties(this) };
		}
		return this.tankProps;
	}

	@Override
	public FluidStack drain(FluidStack resource, boolean doDrain) {
		if(resource == null || resource.isFluidEqual(this.getFluid())) {
			return null;
		}
		return this.drain(resource.amount, doDrain);
	}
	
	@Override
	public FluidStack getFluid() {
		return this.fluid;
	}

	public void setFluid(FluidStack stack) {
		this.fluid = stack;
	}
	
	@Override
	public int getFluidAmount() {
		int amount = 0;
		if(fluid != null) {
			amount = fluid.amount;
		}
		return amount;
	}

	@Override
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	@Override
	public FluidTankInfo getInfo() {
		return new FluidTankInfo(this);
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		
		if(!this.canFillFluidType(resource)) {
			return 0;
		}
		
		// Handle Fill
		if(resource == null || resource.amount <= 0) {
			return 0;
		}
		
		if(!this.canFill()) {
			if(fluid == null) {
				return Math.min(capacity, resource.amount);
			}
			
			if(!fluid.isFluidEqual(resource)) {
				return 0;
			}
			
			return Math.min(capacity - fluid.amount, resource.amount);
		}
		
		if(fluid == null) {
			fluid = new FluidStack(resource, Math.min(capacity, resource.amount));
			return fluid.amount;
		}
		
		if(!fluid.isFluidEqual(resource)) {
			return 0;
		}
		
		int filled = capacity - fluid.amount;
		
		if(resource.amount < filled) {
			fluid.amount += resource.amount;
			filled = resource.amount;
		} else {
			fluid.amount = capacity;
		}
		
		return filled;
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		
		if(fluid == null || maxDrain <= 0) {
			return null;
		}
		
		int drained = maxDrain;
		
		if(fluid.amount < drained) {
			drained = fluid.amount;
		}
		
		FluidStack stack = new FluidStack(fluid, drained);
		
		if(doDrain) {
			fluid.amount -= drained;
			
			if(fluid.amount <= 0) {
				fluid = null;
			}
		}
		return stack;
	}

	@Override
	public boolean canFill() {
		return this.canFill;
	}

	@Override
	public boolean canDrain() {
		return this.canDrain;
	}

	public void setCanFill(boolean fill) {
		this.canFill = fill;
	}

	public void setCanDrain(boolean drain) {
		this.canDrain = drain;
	}

	@Override
	public boolean canFillFluidType(FluidStack fluid) {
		// TODO Auto-generated method stub
		return this.canFill();
	}

	@Override
	public boolean canDrainFluidType(FluidStack fluid) {
		// TODO Auto-generated method stub
		return this.canDrain();
	}
	
	public void readFromNBT(NBTTagCompound compound) {
		if(!compound.hasKey("Empty")) {
			FluidStack fluid = FluidStack.loadFluidStackFromNBT(compound);
			this.setFluid(fluid);
		} else {
			this.setFluid(null);
		}
	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		
		if(fluid != null) {
			fluid.writeToNBT(compound);
		} else {
			compound.setString("Empty", "");
		}
		return compound;
	}
	
	
}
