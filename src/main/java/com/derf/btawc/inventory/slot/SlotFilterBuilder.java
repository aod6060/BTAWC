package com.derf.btawc.inventory.slot;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;

/**
 * This class will help with building of a SlotFilter
 * @author Fred
 *
 */
public class SlotFilterBuilder {
	
	private IInventory inventory;
	private int index;
	private int x;
	private int y;
	private List<Item> items = new ArrayList<Item>();
	
	// Default Constructor
	public SlotFilterBuilder() {}
	
	public SlotFilterBuilder(IInventory inventory, int index, int x, int y) {
		this.inventory = inventory;
		this.index = index;
		this.x = x;
		this.y = y;
	}
	
	public void add(Item item) {
		items.add(item);
	}
	
	public void add(Block block) {
		items.add(Item.getItemFromBlock(block));
	}
	
	public SlotFilter create() {
		return new SlotFilter(inventory, index, x, y, this.items);
	}
}
