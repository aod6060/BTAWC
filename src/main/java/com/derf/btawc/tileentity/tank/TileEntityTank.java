package com.derf.btawc.tileentity.tank;

import java.util.ArrayList;
import java.util.List;

import com.derf.btawc.fluid.FluidTank;
import com.derf.btawc.tileentity.EnumSixSided;
import com.derf.btawc.tileentity.ISixSidedFluidInventory;
import com.derf.btawc.tileentity.TileEntityBasic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.ItemFluidContainer;

public class TileEntityTank extends TileEntityBasic implements ITickable, IInventory, ISixSidedFluidInventory {

	private FluidTank tank = new FluidTank(Fluid.BUCKET_VOLUME * 500);
	private String name = null;
	private ItemStack[] inventory = new ItemStack[2];
	private EnumFacing[] faces = new EnumFacing[6];
	private EnumSixSided[] sided = new EnumSixSided[6];
	
	public TileEntityTank() {
		for(int i = 0; i < sided.length; i++) {
			sided[i] = EnumSixSided.OFF;
			faces[i] = EnumFacing.values()[i];
		}
	}
	
	@Override
	public void update() {
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		// TODO Auto-generated method stub
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			return (T) tank;
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		super.readFromNBT(compound);
		this.tank.readFromNBT(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		this.tank.writeToNBT(compound);
		return super.writeToNBT(compound);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.hasCustomName()? this.name : "container.tank";
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean hasCustomName() {
		// TODO Auto-generated method stub
		return this.name != null || !this.name.isEmpty();
	}

	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return this.inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		// TODO Auto-generated method stub
		return this.inventory[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		// TODO Auto-generated method stub
		return ItemStackHelper.getAndSplit(this.inventory, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		// TODO Auto-generated method stub
		return ItemStackHelper.getAndRemove(this.inventory, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		this.inventory[index] = stack;
		if(stack != null && stack.stackSize > this.getInventoryStackLimit()) {
			stack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		// TODO Auto-generated method stub
		return this.worldObj.getTileEntity(pos) != this? false : player.getDistanceSq(pos.add(0.5, 0.5, 0.5)) <= 64.0;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return stack.getItem() instanceof ItemBucket || stack.getItem() instanceof ItemFluidContainer;
	}

	@Override
	public int getField(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value) {
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		this.inventory = new ItemStack[2];
	}

	@Override
	public void setType(EnumFacing side, EnumSixSided type) {
		sided[side.ordinal()] = type;
	}

	@Override
	public EnumSixSided getType(EnumFacing side) {
		// TODO Auto-generated method stub
		return sided[side.ordinal()];
	}

	@Override
	public boolean isTypeOff(EnumFacing side) {
		// TODO Auto-generated method stub
		return sided[side.ordinal()] == EnumSixSided.OFF;
	}

	@Override
	public boolean isTypePull(EnumFacing side) {
		// TODO Auto-generated method stub
		return sided[side.ordinal()] == EnumSixSided.PULL;
	}

	@Override
	public boolean isTypePush(EnumFacing side) {
		// TODO Auto-generated method stub
		return sided[side.ordinal()] == EnumSixSided.PUSH;
	}

	@Override
	public boolean isTypeDisabled(EnumFacing side) {
		// TODO Auto-generated method stub
		return sided[side.ordinal()] == EnumSixSided.DISABLED;
	}

	@Override
	public boolean isSixSidedFluidInventory(TileEntity entity) {
		// TODO Auto-generated method stub
		return entity instanceof ISixSidedFluidInventory;
	}

	@Override
	public List<EnumSixSided> getAllTypes() {
		// TODO Auto-generated method stub
		List<EnumSixSided> temp = new ArrayList<EnumSixSided>();
		
		for(EnumSixSided type : sided) {
			temp.add(type);
		}
		return temp;
	}

	@Override
	public void drain(EnumFacing face) {
	}

	@Override
	public void fill(EnumFacing face) {
	}
	
	
}
