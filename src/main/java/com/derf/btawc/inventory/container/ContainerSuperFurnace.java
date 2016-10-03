package com.derf.btawc.inventory.container;

import com.derf.btawc.blocks.tileentity.furnace.TileEntitySuperFurnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerSuperFurnace extends ContainerBasic {
	// Statics
	private static final int COOKING_SPEED = 200;
	// 
	private final InventoryPlayer player;
	private TileEntitySuperFurnace furnace;
	private int burnTime;
	private int currentItemBurnTime;
	private int cookTime;
	
	public ContainerSuperFurnace(InventoryPlayer player, TileEntitySuperFurnace superFurnace) {
		this.furnace = superFurnace;
		this.player = player;
		
		// Create Input Slots for Super Furnace
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 3; x++) {
				this.addSlotToContainer(new Slot(superFurnace, y * 3 + x, x * 18 + 8, y * 18 + 16));
			}
		}
		// Create Output Slots for Super Furnace
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 3; x++) {
				this.addSlotToContainer(new Slot(superFurnace, (y*3+x)+9, x * 18 + 114, y * 18 + 16) {
					@Override
					public boolean isItemValid(ItemStack p_75214_1_) {
						return false;
					}
					
				});
			}
		}
		// Create Fuel Slot for Super Furnace
		this.addSlotToContainer(new Slot(superFurnace, 18, 26, 88));
		// Create Player Inventory
		this.createPlayerInventory(player, 6, 124, 6, 182);
	}

	
	@Override
	public void addListener(IContainerListener listener) {
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.furnace);
	}


	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for(int i = 0; i < this.listeners.size(); i++) {
			IContainerListener listener = this.listeners.get(i);
			
			if(this.burnTime != this.furnace.getField(0)) {
				listener.sendProgressBarUpdate(this, 0, this.furnace.getField(0));
			}
			
			if(this.currentItemBurnTime != this.furnace.getField(1)) {
				listener.sendProgressBarUpdate(this, 1, this.furnace.getField(1));
			}
			
			if(this.cookTime != this.furnace.getField(2)) {
				listener.sendProgressBarUpdate(this, 2, this.furnace.getField(2));
			}
		}
		this.burnTime = this.furnace.getField(0);
		this.currentItemBurnTime = this.furnace.getField(1);
		this.cookTime = this.furnace.getField(2);
	}


	@Override
	public void updateProgressBar(int index, int value) {
		this.furnace.setField(index, value);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return furnace.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index) {
		ItemStack stack = null;
		Slot slot = (Slot)this.inventorySlots.get(index);
		
		if(slot != null && slot.getHasStack()) {
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			
			if(this.isOutput(index)) {
				if(!this.mergeItemStack(stack1, 19, 55, true)) {
					return null;
				}
				
				slot.onSlotChange(stack1,  stack);
			} else if(!this.isFuel(index) && !this.isInput(index)) {
				if(FurnaceRecipes.instance().getSmeltingResult(stack1) != null) {
					if(!this.mergeItemStack(stack1, 0, 9, false)) {
						return null;
					}
				} else if(TileEntityFurnace.isItemFuel(stack1)) {
					if(!this.mergeItemStack(stack1, 18, 19, false)) {
						return null;
					}
				} else if(index >= 19 && index < 46) {
					if(!this.mergeItemStack(stack1, 46, 55, false)) {
						return null;
					}
				} else if(index >= 46 && index < 55) {
					if(!this.mergeItemStack(stack1, 19, 46, false)) {
						return null;
					}
				}
			} else if(!this.mergeItemStack(stack1, 19, 55, false)) {
				return null;
			}
			
			if(stack1.stackSize == 0) {
				slot.putStack((ItemStack)null);
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
	
	private boolean isInput(int index) {
		return index >= 0 && index < 9;
	}
	
	private boolean isOutput(int index) {
		return index >= 9 && index < 18;
	}
	
	private boolean isFuel(int index) {
		return index == 18;
	}
}
