package com.derf.btawc.tileentity.furnace;

import javax.annotation.Nullable;

import com.derf.btawc.blocks.furnace.BlockSuperFurnace;
import com.derf.btawc.tileentity.TileEntityBasic;
import com.derf.btawc.util.FuelUtils;
import com.derf.btawc.util.InventoryUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntitySuperFurnace extends TileEntityBasic implements IInventory, ITickable {
	// Inputs 0-8
	public static final int INPUT_1 = 0;
	public static final int INPUT_2 = 1;
	public static final int INPUT_3 = 2;
	public static final int INPUT_4 = 3;
	public static final int INPUT_5 = 4;
	public static final int INPUT_6 = 5;
	public static final int INPUT_7 = 6;
	public static final int INPUT_8 = 7;
	public static final int INPUT_9 = 8;
	// Outputs 9-18
	public static final int OUTPUT_1 = 9;
	public static final int OUTPUT_2 = 10;
	public static final int OUTPUT_3 = 11;
	public static final int OUTPUT_4 = 12;
	public static final int OUTPUT_5 = 13;
	public static final int OUTPUT_6 = 14;
	public static final int OUTPUT_7 = 15;
	public static final int OUTPUT_8 = 16;
	public static final int OUTPUT_9 = 17;
	public static final int FUEL_SLOT = 18;
	public static final int COOKING_SPEED = 200;
	// Furnace Inventory
	// 0-8 input, 9-17 output, 18 fuel...
	private ItemStack[] inventory = new ItemStack[19];
	public int burnTime;
	public int currentItemBurnTime;
	public int cookTime;
	private String name;
	
	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return this.inventory[index];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		return ItemStackHelper.getAndSplit(this.inventory, slot, amount);
	}
	
	@Override
	public void setInventorySlotContents(int slot, @Nullable ItemStack stack) {
		this.inventory[slot] = stack;
		
		if(stack != null && stack.stackSize > this.getInventoryStackLimit()) {
			stack.stackSize = this.getInventoryStackLimit();
		}
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getTileEntity(pos) != this? false : player.getDistanceSq(pos.add(0.5, 0.5, 0.5)) <= 64.0;
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		// 0-8 input, 9-17 output, 19 fuel.
		boolean b = false;
		
		if(this.isOutput(slot)) {
			b = false;
		} else if(this.isInput(slot)) {
			b = true;
		} else {
			ItemStack fuelStack = this.inventory[FUEL_SLOT];
			b = FuelUtils.isItemFuel(stack) || SlotFurnaceFuel.isBucket(stack) && (fuelStack == null || fuelStack.getItem() != Items.BUCKET);
		}
		
		return b;
	}
	
	private boolean isInput(int slot) {
		return INPUT_1 == slot||
			   INPUT_2 == slot||
			   INPUT_3 == slot||
			   INPUT_4 == slot||
			   INPUT_5 == slot||
			   INPUT_6 == slot||
			   INPUT_7 == slot||
			   INPUT_8 == slot||
			   INPUT_9 == slot;
	}
	
	private boolean isOutput(int slot) {
		return OUTPUT_1 == slot||
			   OUTPUT_2 == slot||
			   OUTPUT_3 == slot||
			   OUTPUT_4 == slot||
			   OUTPUT_5 == slot||
			   OUTPUT_6 == slot||
			   OUTPUT_7 == slot||
			   OUTPUT_8 == slot||
			   OUTPUT_9 == slot;
	}
	
	private boolean canSmelt() {
		boolean b = false;
		
		for(int i = 0; i < 9; i++) {
			b = b || this.canSmelt(i, i+9);
		}
		
		return b;
	}
	
	private boolean canSmelt(int inputSlot, int outputSlot) {
		if(this.inventory[inputSlot] == null) {
			return false;
		} else {
			ItemStack stack = FurnaceRecipes.instance().getSmeltingResult(this.inventory[inputSlot]);
			if(stack == null) return false;
			if(this.inventory[outputSlot] == null) return true;
			if(!this.inventory[outputSlot].isItemEqual(stack)) return false;
			int result = inventory[outputSlot].stackSize + stack.stackSize;
			return result <= this.getInventoryStackLimit() && result <= this.inventory[outputSlot].getMaxStackSize();
		}
	}
	
	private void smeltItems() {
		for(int i = 0; i < 9; i++) {
			this.smeltItem(i, i+9);
		}
	}
	
	private void smeltItem(int inputSlot, int outputSlot) {
		if(this.canSmelt(inputSlot, outputSlot)) {
			ItemStack stack = FurnaceRecipes.instance().getSmeltingResult(inventory[inputSlot]);
			
			if(this.inventory[outputSlot] == null) {
				this.inventory[outputSlot] = stack.copy();
			} else if(this.inventory[outputSlot].getItem() == stack.getItem()) {
				this.inventory[outputSlot].stackSize += stack.stackSize;
			}
			
			--this.inventory[inputSlot].stackSize;
			
			if(this.inventory[inputSlot].stackSize <= 0) {
				this.inventory[inputSlot] = null;
			}
		}
	}
	
	private boolean checkIfInputAreAllNull() {
		boolean b = false;
		
		for(int i = 0; i < 9; i++) {
			b = b || this.inventory[i] != null;
		}
		
		return b;
	}
	
	public boolean isBurning() {
		return this.burnTime > 0;
	}
	
	
	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int scale) {
		if(this.currentItemBurnTime == 0) {
			this.currentItemBurnTime = COOKING_SPEED;
		}
		
		return this.burnTime * scale / this.currentItemBurnTime;
	}
	
	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int scale) {
		return this.cookTime * scale / COOKING_SPEED;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return this.hasCustomName()? this.name : "container.super_furnace";
	}

	@Override
	public boolean hasCustomName() {
		return this.name != null && !this.name.isEmpty();
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(this.inventory, index);
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public int getField(int id) {
		int value = 0;
		switch(id) {
		case 0:
			value = this.burnTime;
			break;
		case 1:
			value = this.currentItemBurnTime;
			break;
		case 2:
			value = this.cookTime;
			break;
		}
		return value;
	}

	@Override
	public void setField(int id, int value) {
		switch(id) {
		case 0:
			this.burnTime = value;
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
		return 3;
	}

	@Override
	public void clear() {
		for(ItemStack s : this.inventory) {
			s = null;
		}
	}

	@Override
	public void update() {
		boolean flag = this.burnTime > 0;
		boolean flag1 = false;
		// Decrement Burning time...
		if(this.burnTime > 0) {
			--this.burnTime;
		}
		
		if(!this.worldObj.isRemote) {
			// Using Fuel
			if(this.burnTime != 0 || this.inventory[FUEL_SLOT] != null && this.checkIfInputAreAllNull()) {
				if(this.burnTime == 0 && this.canSmelt()) {
					this.currentItemBurnTime = this.burnTime = FuelUtils.getItemBurnTime(this.inventory[FUEL_SLOT]);
					
					if(this.burnTime > 0) {
						flag1 = true;
						if(this.inventory[FUEL_SLOT] != null) {
							--this.inventory[FUEL_SLOT].stackSize;
							if(this.inventory[FUEL_SLOT].stackSize <= 0) {
								this.inventory[FUEL_SLOT] = this.inventory[FUEL_SLOT].getItem().getContainerItem(this.inventory[FUEL_SLOT]);
							}
						}
					}
				}
				
				if(this.isBurning() && this.canSmelt()) {
					++this.cookTime;
					if(this.cookTime >= COOKING_SPEED) {
						this.cookTime = 0;
						this.smeltItems();
						flag1 = true;
					}
				} else {
					this.cookTime = 0;
				}
				
			}
			
			if(flag != this.burnTime > 0) {
				flag1 = true;
				// Update Furnace State
				BlockSuperFurnace.setSuperFurnaceState(this.burnTime > 0, this.worldObj, this.pos);
			}
		}
		
		if(flag1) {
			this.markDirty();
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		/*
		NBTTagList list = tag.getTagList("Items", Constants.NBT.TAG_COMPOUND);
		this.inventory = new ItemStack[this.getSizeInventory()];
		
		for(int i = 0; i < list.tagCount(); i++) {
			NBTTagCompound comp = list.getCompoundTagAt(i);
			int index = comp.getInteger("Slot");
			if(index >= 0 && index < this.getSizeInventory()) {
				this.inventory[index] = ItemStack.loadItemStackFromNBT(comp);
			}
		}
		*/
		InventoryUtils.loadInventory(this, compound);
		this.burnTime = compound.getInteger("BurnTime");
		this.cookTime = compound.getInteger("CookTime");
		this.currentItemBurnTime = FuelUtils.getItemBurnTime(this.inventory[FUEL_SLOT]);
		
		if(compound.hasKey("CustomName")) {
			this.name = compound.getString("CustomName");
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("BurnTime", this.burnTime);
		compound.setInteger("CookTime", this.cookTime);
		/*
		NBTTagList list = new NBTTagList();
		
		for(int i =0; i < this.inventory.length; i++) {
			
			if(this.inventory[i] != null) {
				NBTTagCompound comp = new NBTTagCompound();
				comp.setInteger("Slot", i);
				this.inventory[i].writeToNBT(comp);
				list.appendTag(comp);
			}
			
		}
		
		compound.setTag("Items", list);
		*/
		InventoryUtils.saveInventory(this, compound);
		
		if(this.hasCustomName()) {
			compound.setString("CustomName", this.name);
		}
		return compound;
	}
}
