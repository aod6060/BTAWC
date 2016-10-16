package com.derf.btawc.util;

import javax.annotation.Nullable;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public final class InventoryUtils {
	
	/**
	 * Checks to see if the inventory is full
	 * @param inventory
	 * @return
	 */
	public static boolean isInventoryFull(IInventory inventory) {
		boolean b = true;
		
		for(int i = 0; i < inventory.getSizeInventory(); i++) {
			if(inventory.getStackInSlot(i) == null || 
			  (inventory.getStackInSlot(i).stackSize < inventory.getInventoryStackLimit() &&
			   inventory.getStackInSlot(i).stackSize < inventory.getStackInSlot(i).getMaxStackSize())) {
				b = false;
				break;
			}
		}
		return b;
	}
	
	/**
	 * Checks to see if the inventory is empty
	 * @param inventory
	 * @return
	 */
	public static boolean isInventoryEmpty(IInventory inventory) {
		boolean b = true;
		
		for(int i = 0; i < inventory.getSizeInventory(); i++) {
			if(inventory.getStackInSlot(i) != null) {
				b = false;
				break;
			}
		}
		return b;
		
	}
	
	/**
	 * This will move an Item to a different inventory
	 * @param IInventory to - Inventory that you want to add a ItemStack too
	 * @param IInventory from - Inventory that you want to remove an ItemStack from
	 * @param int to_index
	 * @param int from_index
	 * @param int amount
	 */
	public static void moveToInventory(IInventory to, IInventory from, int to_index, int from_index, int amount) {
		ItemStack stack = removeFromInventory(from, from_index, amount);
		if(stack != null) {
			addToInventory(to, stack, to_index);
		}
	}
	
	/**
	 * This is remove a set amount of items from an inventory
	 * @param IInventory inventory
	 * @param int index - The index for the item
	 * @param int amount - The amount you want
	 * @return
	 */
	@Nullable
	public static ItemStack removeFromInventory(IInventory inventory, int index, int amount) {
		ItemStack stack = null;
		
		if(index >= 0 && index < inventory.getSizeInventory() && inventory.getStackInSlot(index) != null && amount > 0) {
			stack = inventory.getStackInSlot(index).splitStack(amount);
			if(inventory.getStackInSlot(index).stackSize <= 0) {
				inventory.setInventorySlotContents(index, null);
			}
		}
		
		return stack;
	}
	
	/**
	 * This will remove all items from a inventory slot
	 * @param IInventory inventory
	 * @param int index
	 * @return
	 */
	@Nullable
	public static ItemStack removeAllItemsFromSlot(IInventory inventory, int index) {
		ItemStack stack = null;
		if(index >= 0 && index < inventory.getSizeInventory()) {
			stack = inventory.getStackInSlot(index);
			inventory.setInventorySlotContents(index, null);
		}
		return stack;
	}
	
	/**
	 * This will add items to an inventory
	 * @param IInventory inventory 
	 * @param ItemStack stack - This is the item stack you want to add
	 * @param int index - This is the index of the inventory
	 */
	public static void addToInventory(IInventory inventory, ItemStack stack, int index) {
		if(inventory.isItemValidForSlot(index, stack)) {
			if(inventory.getStackInSlot(index) == null) {
				inventory.setInventorySlotContents(index, stack.copy());
				return;
			}
			
			if(canItemStackBeAddedToSlot(inventory, stack, index)) {
				int delta = inventory.getStackInSlot(index).getMaxStackSize() - inventory.getStackInSlot(index).stackSize;
				
				if(delta >= stack.stackSize) {
					inventory.getStackInSlot(index).stackSize += stack.stackSize;
				} else if(delta < stack.stackSize) {
					stack.stackSize -= delta;
					inventory.getStackInSlot(index).stackSize += delta;
				}
				return;
			}
		}
	}
	
	/**
	 * This will return an inventory index that is either null, or if you can add to an item stack...
	 * @param IInventory inventory
	 * @param ItemStack stack
	 * @return int - the index of the inventory or -1 if it doesn't exist.
	 */
	public static int getInventoryIndex(IInventory inventory, ItemStack stack) {
		int index = -1;
		
		if(!isInventoryFull(inventory)) {
			for(int i = 0; i < inventory.getSizeInventory(); i++) {
				if(inventory.isItemValidForSlot(i, stack)) {
					if(inventory.getStackInSlot(i) == null) {
						index = i;
						break;
					}
					
					if(canItemStackBeAddedToSlot(inventory, stack, i)) {
						index = i;
						break;
					}
				}
			}
		}
		return index;
	}
	
	public static int getInventoryIndex(IInventory inventory) {
		int index = -1;
		
		if(!isInventoryEmpty(inventory)) {
			for(int i = 0; i < inventory.getSizeInventory(); i++) {
				if(inventory.getStackInSlot(i) != null) {
					index = i;
					break;
				}
			}
		}
		return index;
	}
	/**
	 * Checks and sees if an ItemStack can be added to an item slot
	 * @param IInentory inventory
	 * @param ItemStack stack
	 * @param int index
	 * @return
	 */
	public static boolean canItemStackBeAddedToSlot(IInventory inventory, ItemStack stack, int index) {
		return (inventory.getStackInSlot(index).getItem() == stack.getItem() &&
			   inventory.getStackInSlot(index).getItemDamage() == stack.getItemDamage() &&
			   inventory.getStackInSlot(index).stackSize < inventory.getInventoryStackLimit() &&
			   inventory.getStackInSlot(index).stackSize < inventory.getStackInSlot(index).getMaxStackSize());
	}
	
	public static void setSlotToNullIfZero(IInventory inventory, int index) {
		if(inventory.getStackInSlot(index) != null && 
		   inventory.getStackInSlot(index).stackSize <= 0) {
			inventory.setInventorySlotContents(index, null);
		}
	}
}
