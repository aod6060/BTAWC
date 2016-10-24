package com.derf.btawc.blocks.tileentity.furnace;

import javax.annotation.Nullable;

import com.derf.btawc.blocks.furnace.BlockAlloyFurnace;
import com.derf.btawc.blocks.tileentity.TileEntityBasic;
import com.derf.btawc.recipe.handler.AlloyFurnaceRecipeManager;
import com.derf.btawc.util.FuelUtils;
import com.derf.btawc.util.InventoryUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityAlloyFurnace extends TileEntityBasic implements ITickable, IInventory {
	// Static Stuff
	public static final int COOKING_SPEED = 400;
	public static final int INPUT_1 = 0;
	public static final int INPUT_2 = 1;
	public static final int INPUT_3 = 2;
	public static final int INPUT_4 = 3;
	public static final int OUTPUT = 4;
	public static final int FUEL_SLOT = 5;
	
	// Alloy Furnace Inventory
	// 0-3 Input, 4 Output and 5 Fuel Slot
	private ItemStack[] inventory = new ItemStack[6];
	
	// Note set these values to private once I get the field stuff done
	public int burnTime;
	public int currentItemBurnTime;
	public int cookTime;
	private String name;

	@Override
	public int getSizeInventory() {
		return this.inventory.length;
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
	
	private boolean canSmelt() {
		// Input is 0 - 3, Output 4
		ItemStack stack = AlloyFurnaceRecipeManager.getResult(inventory[INPUT_1], inventory[INPUT_2], inventory[INPUT_3], inventory[INPUT_4]);
		
		if(stack == null) {
			return false;
		}
		
		if(this.inventory[OUTPUT] == null) {
			return true;
		}
		
		if(!this.inventory[OUTPUT].isItemEqual(stack)) {
			return false;
		}
		
		int result = inventory[OUTPUT].stackSize + stack.stackSize;
		return result <= this.getInventoryStackLimit() && result <= this.inventory[OUTPUT].getMaxStackSize();
	}
	
	private void smeltItem() {
		if(this.canSmelt()) {
			ItemStack stack = AlloyFurnaceRecipeManager.getResult(inventory[INPUT_1], inventory[INPUT_2], inventory[INPUT_3], inventory[INPUT_4]);
			
			if(this.inventory[OUTPUT] == null) {
				this.inventory[OUTPUT] = stack.copy();
			} else if(this.inventory[OUTPUT].getItem() == stack.getItem()) {
				this.inventory[OUTPUT].stackSize += stack.stackSize;
			}
			
			this.updateSlot(INPUT_1);
			this.updateSlot(INPUT_2);
			this.updateSlot(INPUT_3);
			this.updateSlot(INPUT_4);
		}
	}
	
	private void updateSlot(int slot) {
		if(this.inventory[slot] != null) {
			--this.inventory[slot].stackSize;
			if(this.inventory[slot].stackSize <= 0) {
				this.inventory[slot] = null;
			}
		}
	}
	
	public boolean checkIfInputAreAllNull() {
		boolean b = false;
		for(int i = 0; i < 4; i++) {
			b = b || this.inventory[i] != null;
		}
		return b;
	}
	
	public boolean isSlotNull(int slot) {
		return this.inventory[slot] == null;
	}
	
	public boolean isBurning() {
		return this.burnTime > 0;
	}
	
	public static boolean isBuring(IInventory inventory) {
		return inventory.getField(0) > 0;
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
		return this.hasCustomName()? this.name : "container.alloy_furnace";
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
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		boolean b = false;
		
		if(index == this.OUTPUT) {
			b = false;
		} else if(index != FUEL_SLOT) {
			b = true;
		} else {
			ItemStack fuelStack = this.inventory[FUEL_SLOT];
			b = FuelUtils.isItemFuel(stack) || SlotFurnaceFuel.isBucket(stack) && (fuelStack == null || fuelStack.getItem() != Items.BUCKET);
		}
		return b;
	}

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
		for(ItemStack s : inventory) {
			s = null;
		}
	}

	@Override
	public void update() {
		boolean flag = this.isBurning();
		boolean flag1 = false;
		
		// Decrement Burning time...
		if(this.isBurning()) {
			--this.burnTime;
		}
		
		if(!this.worldObj.isRemote) {
			// Using Fuel
			if(this.burnTime != 0 || this.inventory[FUEL_SLOT] != null && this.checkIfInputAreAllNull()) {
				if(this.burnTime == 0 && this.canSmelt()) {
					this.currentItemBurnTime = this.burnTime = FuelUtils.getItemBurnTime(this.inventory[FUEL_SLOT]);
					if(this.isBurning()) {
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
						this.smeltItem();
						flag1 = true;
					}
				} else {
					this.cookTime = 0;
				}
			}
			
			if(flag != this.burnTime > 0) {
				flag1 = true;
				BlockAlloyFurnace.setAlloyFurnaceState(this.burnTime > 0, this.worldObj, pos);
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
		NBTTagList list = compound.getTagList("Items", Constants.NBT.TAG_COMPOUND);
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
		
		for(int i = 0; i < this.inventory.length; i++) {
			if(this.inventory[i] != null) {
				NBTTagCompound comp = new NBTTagCompound();
				comp.setInteger("Slot", i);
				inventory[i].writeToNBT(comp);
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
