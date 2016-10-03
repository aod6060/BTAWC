package com.derf.btawc.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.util.Constants;

public class Inventory implements IInventory {

	private ItemStack[] inventory;
	private String lang;
	private String name;
	
	public Inventory(int size, String lang) {
		this.inventory = new ItemStack[size];
		this.lang = lang;
	}
	
	@Override
	public String getName() {
		return this.hasCustomName()? this.name : lang;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean hasCustomName() {
		// TODO Auto-generated method stub
		return this.name != null && this.name.isEmpty();
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentString(this.name);
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
		return ItemStackHelper.getAndSplit(this.inventory, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
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
		return 64;
	}

	@Override
	public void markDirty() {}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return false; // Don't Use because only returns false
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
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
		for(ItemStack s : this.inventory) {
			s = null;
		}
	}

	public void readFromNBT(NBTTagCompound tag) {
		NBTTagList list = tag.getTagList("Items", Constants.NBT.TAG_COMPOUND);
		this.inventory = new ItemStack[this.getSizeInventory()];
		for(int i =0; i < list.tagCount(); i++) {
			NBTTagCompound comp = list.getCompoundTagAt(i);
			int index = comp.getInteger("Slot");
			if(index >= 0 && index < this.getSizeInventory()) {
				this.inventory[index] = ItemStack.loadItemStackFromNBT(comp);
			}
		}
		if(tag.hasKey("CustomName")) {
			this.name = tag.getString("CustomName");
		}
	}
	
	public void writeToNBT(NBTTagCompound tag) {
		NBTTagList list = new NBTTagList();
		for(int i = 0; i < this.getSizeInventory(); i++) {
			if(this.inventory[i] != null) {
				NBTTagCompound comp = new NBTTagCompound();
				comp.setInteger("Slot", i);
				inventory[i].writeToNBT(comp);
				list.appendTag(comp);
			}
		}
		
		if(this.hasCustomName()) {
			tag.setString("CustomName", this.name);
		}
	}
}
