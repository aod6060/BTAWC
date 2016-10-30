package com.derf.btawc.tileentity.tank;

import java.util.ArrayList;
import java.util.List;

import com.derf.btawc.fluid.FluidTank;
import com.derf.btawc.fluid.FluidTankChecksAdapter;
import com.derf.btawc.fluid.IFluidTankChecks;
import com.derf.btawc.tileentity.EnumSixSided;
import com.derf.btawc.tileentity.ISixSidedFluidInventory;
import com.derf.btawc.tileentity.TileEntityBasic;
import com.derf.btawc.util.InventoryUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.ItemFluidContainer;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;
import net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper;

public class TileEntityTank extends TileEntityBasic implements ITickable, IInventory, ISixSidedFluidInventory {

	public static final int MB_TICK = 100;
	public static final int INPUT_SLOT = 0;
	public static final int OUTPUT_SLOT = 1;
	public static final int SLOT_SIZE = 2;
	
	private FluidTank tank = new FluidTank(Fluid.BUCKET_VOLUME * 500);
	private String name = null;
	private ItemStack[] inventory = new ItemStack[SLOT_SIZE];
	private EnumFacing[] faces = new EnumFacing[6];
	private EnumSixSided[] sided = new EnumSixSided[6];
	
	public TileEntityTank() {
		for(int i = 0; i < sided.length; i++) {
			sided[i] = EnumSixSided.OFF;
			faces[i] = EnumFacing.values()[i];
		}
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
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
		// Read Tank
		this.tank.readFromNBT(compound);
		// Read Inventory
		InventoryUtils.loadInventory(this, compound);
		if(compound.hasKey("CustomName")) {
			this.name = compound.getString("CustomName");
		}
		// Read Six Sided Fluid Inventory
		NBTTagList list = compound.getTagList("Sided", Constants.NBT.TAG_COMPOUND);
		this.sided = new EnumSixSided[6];
		for(int i = 0; i < list.tagCount(); i++) {
			NBTTagCompound comp = list.getCompoundTagAt(i);
			this.sided[i] = EnumSixSided.values()[comp.getInteger("Value")];
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		// Tank
		this.tank.writeToNBT(compound);
		// Inventory
		InventoryUtils.saveInventory(this, compound);
		// Six Sided Fluid Inventory
		NBTTagList list = new NBTTagList();
		for(int i = 0; i < this.sided.length; i++) {
			NBTTagCompound comp = new NBTTagCompound();
			comp.setInteger("Value", this.sided[i].ordinal());
			list.appendTag(comp);
		}
		compound.setTag("Sided", list);
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
		
		if(index == 0 && (stack.getItem() instanceof ItemBucket || stack.getItem() instanceof ItemFluidContainer)) {
			return true;
		}
		
		return false;
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
		
		EnumFacing opposite = face.getOpposite();
		BlockPos otherEntityPos = pos.add(face.getDirectionVec());
		TileEntity entity = worldObj.getTileEntity(otherEntityPos);
		
		// Handle Drains
		if(entity != null) {
			if(this.isSixSidedFluidInventory(entity)) {
				ISixSidedFluidInventory s = (ISixSidedFluidInventory)entity;
				
				if(!s.isTypeDisabled(opposite) && !s.isTypePull(opposite)) {
					FluidTank tank = s.getTank();
					
					if(this.tank.canDrain() && !this.tank.isFluidTankEmpty()) {
						FluidStack fluid = this.tank.drain(MB_TICK, false);
						
						if(tank.canFill() && tank.getFluid().containsFluid(fluid) && !this.tank.isFluidTankFull()) {
							fluid = this.tank.drain(fluid, true);
							tank.fill(fluid, true);
						}
					}
				}
			} else if(entity.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, opposite)) {
				Object obj = entity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, opposite);
				
				if(obj instanceof FluidTank) {
					FluidTank tank = (FluidTank)obj;
					
					if(this.tank.canDrain() && !this.tank.isFluidTankEmpty()) {
						FluidStack fluid = this.tank.drain(MB_TICK, false);
						
						if(tank.canFill() && tank.getFluid().containsFluid(fluid) && !this.tank.isFluidTankFull()) {
							fluid = this.tank.drain(fluid, true);
							tank.fill(fluid, true);
						}
					}
				} else if(obj instanceof net.minecraftforge.fluids.FluidTank) {
					net.minecraftforge.fluids.FluidTank tank = (net.minecraftforge.fluids.FluidTank)obj;
					IFluidTankChecks checks = new FluidTankChecksAdapter(tank);
					
					if(this.tank.canDrain() && !this.tank.isFluidTankEmpty()) {
						FluidStack fluid = this.tank.drain(MB_TICK, false);
						if(checks.canFill() && tank.getFluid().containsFluid(fluid) && !checks.isFluidTankFull()) {
							fluid = this.tank.drain(fluid, true);
							tank.fill(fluid, true);
						}
					}
				}
			}
		}
	}

	@Override
	public void fill(EnumFacing face) {
		EnumFacing opposite = face.getOpposite();
		BlockPos otherEntityPos = pos.add(face.getDirectionVec());
		TileEntity entity = worldObj.getTileEntity(otherEntityPos);
		
		if(entity != null) {
			if(this.isSixSidedFluidInventory(entity)) {
				ISixSidedFluidInventory s = (ISixSidedFluidInventory)entity;
				
				if(!s.isTypeDisabled(opposite) && !s.isTypePush(opposite)) {
					// Handle Fill
					FluidTank tank = s.getTank();
					
					if(tank.canDrain() && !tank.isFluidTankEmpty()) {
						
						FluidStack fluid = tank.drain(MB_TICK, false);
						if(this.tank.canFill() && this.tank.getFluid().containsFluid(fluid) && !this.tank.isFluidTankFull()) {
							fluid = tank.drain(fluid, true);
							this.tank.fill(fluid, true);
						}
					}
				}
			} else if(entity.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, opposite)) {
				Object obj = entity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, opposite);
				
				if(obj instanceof FluidTank) {
					FluidTank tank = (FluidTank)obj;
					
					if(tank.canDrain() && !tank.isFluidTankEmpty()) {
						FluidStack fluid = tank.drain(MB_TICK, false);
						
						if(this.tank.canFill() && this.tank.getFluid().containsFluid(fluid) && !this.tank.isFluidTankFull()) {
							fluid = tank.drain(fluid, true);
							this.tank.fill(fluid, true);
						}
					}
				} else if(obj instanceof net.minecraftforge.fluids.FluidTank){
					net.minecraftforge.fluids.FluidTank tank = (net.minecraftforge.fluids.FluidTank)obj;
					// Because I'm wrapping this with my interfaces because its better OOP
					IFluidTankChecks checks = new FluidTankChecksAdapter(tank);
					
					if(checks.canDrain() && !checks.isFluidTankEmpty()) {
						FluidStack fluid = tank.getFluid();
						
						if(this.tank.canFill() && this.tank.getFluid().containsFluid(fluid) && !this.tank.isFluidTankFull()) {
							fluid = tank.drain(fluid, true);
							this.tank.fill(fluid, true);
						}
					}
				}
			}
		}
	}
	
	@Override
	public FluidTank getTank() {
		return this.tank;
	}
	
	@Override
	public List<FluidTank> getTanks() {
		List<FluidTank> temp = new ArrayList<FluidTank>();
		temp.add(this.tank);
		return temp;
	}
	
	@Override
	public void update() {
		if(!worldObj.isRemote) {
			// This is the six sided inventory
			for(EnumFacing face : faces) {
				if(this.isTypeOff(face) || this.isTypeDisabled(face)) {
					continue;
				} else {
					if(this.isTypePull(face)) {
						this.fill(face);
					} else if(this.isTypePush(face)) {
						this.drain(face);
					}
				}
			}
			
			// Fill and Drain bucket
			
			if(this.getStackInSlot(INPUT_SLOT) != null && this.getStackInSlot(OUTPUT_SLOT) == null) {
				ItemStack stack = this.getStackInSlot(INPUT_SLOT);
				boolean handledFluidContainer = false;
				
				if(stack.getItem() instanceof ItemBucket) {
					ICapabilityProvider provider = stack.getItem().initCapabilities(stack, new NBTTagCompound());
					handledFluidContainer = handleBucket(provider, stack);
				} else if(stack.getItem() instanceof ItemFluidContainer){
					ICapabilityProvider provider = stack.getItem().initCapabilities(stack, new NBTTagCompound());
					handledFluidContainer = handleFluidContainer(provider, stack);
				}
				
				if(handledFluidContainer) {
					// Move stack to output slot
					this.setInventorySlotContents(OUTPUT_SLOT, stack);
					this.setInventorySlotContents(INPUT_SLOT, null);
				}
			}
			
			// Mark dirty for saving...
			this.markDirty();
		}
	}

	private boolean handleFluidContainer(ICapabilityProvider provider, ItemStack stack) {
		boolean b = false;
		
		if(provider instanceof FluidHandlerItemStack) {
			FluidHandlerItemStack wrapper = (FluidHandlerItemStack)provider;
			
			if(wrapper.getFluid() == null) {
				// Fill Fluid Container
				if(this.tank.getFluidAmount() >= Fluid.BUCKET_VOLUME) {
					FluidStack fluid = this.tank.drain(Fluid.BUCKET_VOLUME, false);
					
					if(wrapper.canFillFluidType(fluid)) {
						int amount = wrapper.fill(fluid, true);
						this.tank.drain(amount, true);
						b = true;
					}
				}
			} else {
				// Drain Fluid Container
				if(this.tank.getFluidAmount() <= Fluid.BUCKET_VOLUME) {
					FluidStack fluid = wrapper.drain(Fluid.BUCKET_VOLUME, false);
					if(this.tank.canFillFluidType(fluid)) {
						int amount = this.tank.fill(fluid, true);
						wrapper.drain(amount, true);
						b = true;
					}
				}
			}
		}
		
		return b;
	}

	private boolean handleBucket(ICapabilityProvider provider, ItemStack stack) {
		boolean b = false;
		
		if(provider instanceof FluidBucketWrapper) {
			FluidBucketWrapper wrapper = (FluidBucketWrapper)provider;
			
			if(wrapper.getFluid() == null) {
				// Fill Bucket
				if(this.tank.getFluidAmount() >= Fluid.BUCKET_VOLUME) {
					FluidStack fluid = this.tank.drain(Fluid.BUCKET_VOLUME, false);
					
					if(wrapper.canFillFluidType(fluid)) {
						int amount = wrapper.fill(fluid, true);
						this.tank.drain(amount, true);
						b = true;
					}
				}
			} else {
				// Drain Bucket
				if(this.tank.getFluidAmount() <= Fluid.BUCKET_VOLUME) {
					FluidStack fluid = wrapper.drain(Fluid.BUCKET_VOLUME, false);
					
					if(this.tank.canFillFluidType(fluid)) {
						int amount = this.tank.fill(fluid, true);
						wrapper.drain(fluid, true);
						b = true;
					}
				}
			}
		}
		
		return b;
	}
}
