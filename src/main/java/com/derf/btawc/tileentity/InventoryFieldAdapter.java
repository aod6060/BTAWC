package com.derf.btawc.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

/**
 * This class is used to help with 
 * @author Fred
 *
 */
public class InventoryFieldAdapter implements IInventory {

	private IField field = null;
	
	public InventoryFieldAdapter(IField field) {
		this.field = field;
	}
	
	@Override
	public String getName() {
		return null;
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public ITextComponent getDisplayName() {
		return null;
	}

	@Override
	public int getSizeInventory() {
		return 0;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return null;
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return null;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return null;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {}

	@Override
	public int getInventoryStackLimit() {
		return 0;
	}

	@Override
	public void markDirty() {}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return false;
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
		return this.field.getField(id);
	}

	@Override
	public void setField(int id, int value) {
		this.field.setField(id, value);
	}

	@Override
	public int getFieldCount() {
		return this.field.getFieldCount();
	}

	@Override
	public void clear() {}

}
