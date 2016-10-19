package com.derf.btawc.inventory.slot;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotFilter extends Slot {

	// This will hold items that I want the slot to uses
	private List<Item> items = new ArrayList<Item>();
	
	public SlotFilter(IInventory inventoryIn, int index, int xPosition, int yPosition, List<Item> items) {
		super(inventoryIn, index, xPosition, yPosition);
		copyList(items);
	}

	private void copyList(List<Item> is) {
		for(Item i : is) {
			items.add(i);
		}
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		boolean b = false;
		for(Item item : items) {
			if(item == stack.getItem()) {
				b = true;
				break;
			}
		}
		return b;
	}

	
	
}
