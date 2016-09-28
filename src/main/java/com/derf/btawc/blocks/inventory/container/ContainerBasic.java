package com.derf.btawc.blocks.inventory.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public abstract class ContainerBasic extends Container {
	
	protected void createPlayerInventory(InventoryPlayer inventory, int x1, int y1, int x2, int y2) {
		// Create Player Inventory 9 - 35
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 9; x++) {
				this.addSlotToContainer(new Slot(inventory, (y*9+x) + 9, x * 18 + x1, y * 18 + y1));
			}
		}
		// Create Player Inventory 0 - 8
		for(int x = 0; x < 9; x++) {
			this.addSlotToContainer(new Slot(inventory, x, x * 18 + x2, y2));
		}
	}
}
