package com.derf.btawc.blocks.tileentity.chipmaker;

import com.derf.btawc.blocks.chipmaker.BlockChipMaker;
import com.derf.btawc.blocks.tileentity.IFuelUsage;
import com.derf.btawc.blocks.tileentity.IProcess;
import com.derf.btawc.blocks.tileentity.TileEntityBasic;
import com.derf.btawc.blocks.tileentity.TileEntityProcessUtils;
import com.derf.btawc.inventory.Inventory;
import com.derf.btawc.recipe.ChipMakerRecipe;
import com.derf.btawc.recipe.ChipMakerRecipeManager;
import com.derf.btawc.recipe.RecipeHolder;
import com.derf.btawc.util.FuelUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

public class TileEntityChipMaker extends TileEntityBasic implements IInventory, ITickable, IFuelUsage, IProcess {

	// Static Stuff
	public static final int COOKING_SPEED = 600;
	public static final int MATERIAL_SLOT = 0;
	public static final int REDSTONE_SLOT = 1;
	public static final int OUTPUT_SLOT = 2;
	public static final int FUEL_SLOT = 3;
	
	// Private Stuffed needed By inventory
	private Inventory inventory = new Inventory(4, "container.chip_maker");
	private int burnTime;
	private int currentItemBurnTime;
	private int cookTime;
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return inventory.getName();
	}

	@Override
	public boolean hasCustomName() {
		// TODO Auto-generated method stub
		return inventory.hasCustomName();
	}

	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return inventory.getSizeInventory();
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		// TODO Auto-generated method stub
		return inventory.getStackInSlot(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		// TODO Auto-generated method stub
		return inventory.decrStackSize(index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		// TODO Auto-generated method stub
		return inventory.removeStackFromSlot(index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		inventory.setInventorySlotContents(index, stack);
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return inventory.getInventoryStackLimit();
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getField(int id) {
		int temp = 0;
		switch(id) {
		case 0:
			temp = this.burnTime;
			break;
		case 1:
			temp = this.currentItemBurnTime;
			break;
		case 2:
			temp = this.cookTime;
			break;
		}
		return temp;
	}

	@Override
	public void setField(int id, int value) {
		switch(id) {
		case 0:
			burnTime = value;
			break;
		case 1:
			this.currentItemBurnTime = value;
			break;
		case 2:
			this.cookTime = value;
			break;
		}
	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public void clear() {
		inventory.clear();
	}

	@Override
	public void update() {
		TileEntityProcessUtils.handleProcessWithFuel(worldObj, this, this, this, this);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		inventory.readFromNBT(compound);
		this.burnTime = compound.getInteger("BurnTime");
		this.cookTime = compound.getInteger("CookTime");
		this.currentItemBurnTime = FuelUtils.getItemBurnTime(this.getStackInSlot(FUEL_SLOT));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		inventory.writeToNBT(compound);
		compound.setInteger("BurnTime", this.burnTime);
		compound.setInteger("CookTime", this.cookTime);
		return super.writeToNBT(compound);
	}

	@Override
	public boolean canProcess() {
		RecipeHolder<ChipMakerRecipe, ItemStack> holder = ChipMakerRecipeManager.getResult(this.getStackInSlot(MATERIAL_SLOT), this.getStackInSlot(REDSTONE_SLOT));
		
		
		if(holder == null) {
			return false;
		}
		
		if(this.getStackInSlot(OUTPUT_SLOT) == null) {
			return true;
		}
		
		if(!this.getStackInSlot(OUTPUT_SLOT).isItemEqual(holder.getResult())) {
			return false;
		}
		
		int result = getStackInSlot(OUTPUT_SLOT).stackSize + holder.getResult().stackSize;
		
		return result <= this.getInventoryStackLimit() && result <= this.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
	}

	@Override
	public void process() {
		if(this.canProcess()) {
			RecipeHolder<ChipMakerRecipe, ItemStack> result = ChipMakerRecipeManager.getResult(
					this.getStackInSlot(MATERIAL_SLOT), 
					this.getStackInSlot(REDSTONE_SLOT));
			
			if(this.getStackInSlot(OUTPUT_SLOT) == null) {
				this.setInventorySlotContents(OUTPUT_SLOT, result.getResult().copy());
			} else if(this.getStackInSlot(OUTPUT_SLOT).getItem() == result.getResult().getItem()) {
				this.getStackInSlot(OUTPUT_SLOT).stackSize += result.getResult().stackSize;
			}
			// Update Material slot
			if(this.getStackInSlot(MATERIAL_SLOT) != null) {
				--this.getStackInSlot(MATERIAL_SLOT).stackSize;
				if(this.getStackInSlot(MATERIAL_SLOT).stackSize <= 0) {
					this.setInventorySlotContents(MATERIAL_SLOT, null);
				}
			}
			// Update Redstone slot
			if(this.getStackInSlot(MATERIAL_SLOT) != null) {
				this.getStackInSlot(MATERIAL_SLOT).stackSize -= result.getRecipe().getRedstone().stackSize;
				if(this.getStackInSlot(MATERIAL_SLOT).stackSize <= 0) {
					this.setInventorySlotContents(MATERIAL_SLOT, null);
				}
			}
		}
	}

	@Override
	public boolean isInputNull() {
		boolean b = false;
		for(int i = 0; i < 2; i++) {
			b = b || this.inventory.getStackInSlot(i) != null;
		}
		return b;
	}

	@Override
	public void setCurrentItemBurnTime(int itemBurnTime) {
		this.currentItemBurnTime = itemBurnTime;
	}

	@Override
	public void incrementCookingTime() {
		++this.cookTime;
	}

	@Override
	public int getCookingTime() {
		// TODO Auto-generated method stub
		return this.cookTime;
	}

	@Override
	public void resetCookingTime() {
		this.cookTime = 0;
	}

	@Override
	public int getMaxCookingTime() {
		// TODO Auto-generated method stub
		return COOKING_SPEED;
	}

	@Override
	public void updateBlockState() {
		BlockChipMaker.setChipMakerState(isBurning(), worldObj, pos);
	}

	@Override
	public boolean isBurning() {
		// TODO Auto-generated method stub
		return this.burnTime > 0;
	}

	@Override
	public void decreaseFuelTime() {
		--this.burnTime;
	}

	@Override
	public boolean isBurnTimeZero() {
		// TODO Auto-generated method stub
		return this.burnTime == 0;
	}

	@Override
	public int getFuelSlot() {
		// TODO Auto-generated method stub
		return FUEL_SLOT;
	}

	@Override
	public void setBurnTime(int burnTime) {
		this.burnTime = burnTime;
	}

	
}
