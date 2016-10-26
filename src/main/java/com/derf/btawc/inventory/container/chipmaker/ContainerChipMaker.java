package com.derf.btawc.inventory.container.chipmaker;

import com.derf.btawc.inventory.container.ContainerBasic;
import com.derf.btawc.tileentity.IField;
import com.derf.btawc.tileentity.chipmaker.TileEntityChipMaker;
import com.derf.btawc.util.FuelUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerChipMaker extends ContainerBasic implements IField {

	private static final int COOKING_TIME = 600;
	
	private final InventoryPlayer player;
	private TileEntityChipMaker entity;
	private int burnTime;
	private int currentItemBurnTime;
	private int cookTime;
	
	public ContainerChipMaker(InventoryPlayer player, TileEntityChipMaker entity) {
		this.player = player;
		this.entity = entity;
		// Create Material Slot 	[0] pos <17, 17>
		this.addSlotToContainer(new Slot(entity, 0, 17, 17));
		// Create Redstone Slot	 	[1] pos <41, 17>
		this.addSlotToContainer(new Slot(entity, 1, 41, 17) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				if(stack == null) {
					return false;
				}
				return stack.getItem() == Items.REDSTONE;
			}
			
		});
		// Create Output Slot   	[2] pos <136, 17>
		this.addSlotToContainer(new Slot(entity, 2, 136, 17) {

			@Override
			public boolean isItemValid(ItemStack stack) {
				// TODO Auto-generated method stub
				return false;
			}
			
		});
		// Create Fuel Slot     	[3] pos <28, 58>
		this.addSlotToContainer(new Slot(entity, 3, 28, 58) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				// TODO Auto-generated method stub
				return FuelUtils.isItemFuel(stack);
			}
		});
		// Create Player Inventory	[4-39] pos <8, 94> and <8, 152>
		this.createPlayerInventory(player, 8, 94, 8, 152);
		
	}
	
	
	@Override
	public void addListener(IContainerListener listener) {
		// TODO Auto-generated method stub
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.entity);
	}

	@Override
	public void detectAndSendChanges() {
		// TODO Auto-generated method stub
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.listeners.size(); i++) {
			IContainerListener listener = this.listeners.get(i);
			
			for(int j = 0; j < this.getFieldCount(); j++) {
				if(this.getField(j) != this.entity.getField(j)) {
					listener.sendProgressBarUpdate(this, j, this.entity.getField(j));
				}
			}
		}
		
		for(int i = 0; i < this.getFieldCount(); i++) {
			this.setField(i, this.entity.getField(i));
		}
	}
	
	
	@Override
	public void updateProgressBar(int id, int data) {
		this.entity.setField(id, data);
	}


	@Override
	public boolean canInteractWith(EntityPlayer player) {
		// TODO Auto-generated method stub
		return this.entity.isUseableByPlayer(player);
	}
	
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index) {
		ItemStack stack = null;
		
		Slot slot = (Slot)this.inventorySlots.get(index);
		
		if(slot != null && slot.getHasStack()) {
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			
			if(this.isOutputSlot(index)) {
				if(!this.merge(stack1, 4, 40, true)) {
					return null;
				}
				
				slot.onSlotChange(stack1, stack);
			} else if(!this.isFuelSlot(index) && !this.isInputSlot(index) && !this.isRedstoneSlot(index)) {
				if(entity.isSlotNull(1) && stack1.getItem() == Items.REDSTONE) {
					if(!this.merge(stack1, 1, 2, false)) {
						return null;
					}
				} else if(entity.isSlotNull(3) && FuelUtils.isItemFuel(stack1)) {
					if(!this.merge(stack1, 3, 4, false)) {
						return null;
					}
				} else if(entity.isSlotNull(0)) {
					if(!this.merge(stack1, 0, 1, false)) {
						return null;
					}
				} else if(this.isPlayerHiddenInventorySlot(index)) {
					if(!this.merge(stack1, 31, 40, false)) {
						return null;
					}
				} else if(this.isPlayerActiveInventorySlot(index)) {
					if(!this.merge(stack1, 4, 31, false)) {
						return null;
					}
				}
			} else if(!this.merge(stack1, 4, 40, false)) {
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


	@Override
	public int getField(int index) {
		int value = 0;
		switch(index) {
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
	public void setField(int index, int value) {
		switch(index) {
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
		// TODO Auto-generated method stub
		return 3;
	}
	
	/**
	 * Slot 0
	 * @param index
	 * @return
	 */
	private boolean isInputSlot(int index) {
		return index == 0;
	}
	
	/**
	 * Slot 1
	 * @param index
	 * @return
	 */
	private boolean isRedstoneSlot(int index) {
		return index == 1;
	}
	
	/**
	 * Slot 2
	 * @param index
	 * @return
	 */
	private boolean isOutputSlot(int index) {
		return index == 2;
	}
	
	/**
	 * Slot 3
	 * @param index
	 * @return
	 */
	private boolean isFuelSlot(int index) {
		return index == 3;
	}
	
	/**
	 * Slot 4 - 30
	 * @param index
	 * @return
	 */
	private boolean isPlayerHiddenInventorySlot(int index) {
		return index >= 4 && index < 31;
	}
	
	/**
	 * Slot 31 - 39
	 * @param index
	 * @return
	 */
	private boolean isPlayerActiveInventorySlot(int index) {
		return index >= 31 && index < 40;
	}
}
