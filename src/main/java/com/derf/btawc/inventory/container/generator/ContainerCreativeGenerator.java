package com.derf.btawc.inventory.container.generator;

import com.derf.btawc.inventory.container.ContainerBasic;
import com.derf.btawc.items.ItemsManager;
import com.derf.btawc.tileentity.generators.TileEntityCreativeGenerator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

public class ContainerCreativeGenerator extends ContainerBasic {

	// Stuff for the container...
	private final InventoryPlayer player;
	private TileEntityCreativeGenerator generator;
	
	public ContainerCreativeGenerator(InventoryPlayer player, TileEntityCreativeGenerator generator) {
		this.player = player;
		this.generator = generator;
		// Speed upgrade [0] (143, 15)
		this.addSlotToContainer(new Slot(generator, 0, 143, 15) {

			@Override
			public boolean isItemValid(ItemStack stack) {
				// TODO Auto-generated method stub
				return stack.getItem() == ItemsManager.speedUpgradeChip;
			}
			
		});
		// Create Player Inventory [8, 92], [8, 150]
		this.createPlayerInventory(player, 8, 92, 8, 150);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return this.generator.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index) {
		ItemStack stack = null;
		Slot slot = (Slot)this.inventorySlots.get(index);
		if(slot != null && slot.getHasStack()) {
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			
			if(!this.isSpeedUpgradeSlot(index)) {
				if(TileEntityCreativeGenerator.isSpeedUpgrade(stack1)){
					if(!this.merge(stack1, 0, 1, false)) {
						return null;
					}
				} else if(this.isPlayerHiddenInventory(index)) {
					if(!this.mergeItemStack(stack1, 28, 37, false)) {
						return null;
					}
				} else if(this.isPlayerInventory(index)) {
					if(!this.mergeItemStack(stack1, 1, 28, false)) {
						return null;
					}
				}
			} else if(!this.merge(stack1, 1, 37, false)) {
				return null;
			}
			
			if(stack1.stackSize == 0) {
				slot.putStack(null);
			} else {
				slot.onSlotChanged();
			}
			
			if(stack1.stackSize == stack.stackSize) {
				return null;
			}
			
			slot.onPickupFromSlot(player, stack1);
			
		}
		return stack;
	}
	
	private boolean isSpeedUpgradeSlot(int index) {
		return index == 0; 
	}
	/**
	 * Slots 0 - 26
	 * @param this index of the slot
	 * @return boolean
	 */
	private boolean isPlayerHiddenInventory(int index) {
		return index >= 1 && index < 28;
	}
	
	/**
	 * slot 27 - 35
	 * @param this index of the slot
	 * @return boolean
	 */
	private boolean isPlayerInventory(int index) {
		return index  >= 28 && index < 37;
	}
}
