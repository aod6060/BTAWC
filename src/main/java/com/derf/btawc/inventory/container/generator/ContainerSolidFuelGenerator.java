package com.derf.btawc.inventory.container.generator;

import com.derf.btawc.inventory.container.ContainerBasic;
import com.derf.btawc.inventory.slot.SlotFilterBuilder;
import com.derf.btawc.inventory.slot.SlotFuel;
import com.derf.btawc.items.ItemsManager;
import com.derf.btawc.tileentity.IField;
import com.derf.btawc.tileentity.generators.TileEntitySolidFuelGenerator;
import com.derf.btawc.util.FuelUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSolidFuelGenerator extends ContainerBasic implements IField {

	private final InventoryPlayer player;
	private TileEntitySolidFuelGenerator generator;
	
	private int burnTime;
	private int energy;
	private int capacity;
	private int maxExtract;
	private int maxReceive;
	private int currentEnergyTicks;
	private int insantity;
	
	public ContainerSolidFuelGenerator(InventoryPlayer player, TileEntitySolidFuelGenerator generator) {
		this.player = player;
		this.generator = generator;
		// Fuel Slot [0] (76, 56)
		this.addSlotToContainer(new SlotFuel(generator, 0, 76, 56));
		// Speed Upgrade Slot [1] (150, 16)
		SlotFilterBuilder builder = new SlotFilterBuilder(generator, 1, 150, 16);
		builder.add(ItemsManager.speedUpgradeChip);
		this.addSlotToContainer(builder.create());
		// Efficency Upgrade Slot [2] (150, 40)
		builder = new SlotFilterBuilder(generator, 2, 150, 40);
		builder.add(ItemsManager.efficencyUpgradeChip);
		this.addSlotToContainer(builder.create());
		// Player slots [3 - 38] hidden (8,96) active (8,154)
		this.createPlayerInventory(player, 8, 96, 8, 154);
	}
	
	
	@Override
	public void addListener(IContainerListener listener) {
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.generator);
	}
	
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.listeners.size(); i++) {
			IContainerListener listener = this.listeners.get(i);
			
			for(int j = 0; j < this.getFieldCount(); j++) {
				if(this.getField(j) != this.generator.getField(j)) {
					listener.sendProgressBarUpdate(this, j, this.generator.getField(j));
				}
			}
		}
		
		for(int i = 0; i < this.getFieldCount(); i++) {
			this.setField(i, this.generator.getField(i));
		}
	}
	
	
	@Override
	public void updateProgressBar(int id, int data) {
		this.generator.setField(id, data);
	}
	
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index) {
		ItemStack stack = null;
		Slot slot = (Slot)this.inventorySlots.get(index);
		if(slot != null && slot.getHasStack()) {
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			
			if(!this.isFuelSlot(index) && !this.isSpeedUpgradeSlot(index) && !this.isEfficencySlot(index)) {
				if(FuelUtils.isItemFuel(stack1)) {
					if(!this.merge(stack1, 0, 1, false)) {
						return null;
					}
				} else if(stack1.getItem() == ItemsManager.speedUpgradeChip) {
					if(!this.merge(stack1, 1, 2, false)) {
						return null;
					}
				} else if(stack1.getItem() == ItemsManager.efficencyUpgradeChip) {
					if(!this.merge(stack1, 2, 3, false)) {
						return null;
					}
				} else if(this.isPlayerHiddenInventory(index)) {
					if(!this.merge(stack1, 30, 39, false)) {
						return null;
					}
				} else if(this.isPlayerActiveInventory(index)) {
					if(!this.merge(stack1, 3, 30, false)) {
						return null;
					}
				}
			} else if(!this.merge(stack1, 3, 39, false)) {
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
	public boolean canInteractWith(EntityPlayer player) {
		// TODO Auto-generated method stub
		return generator.isUseableByPlayer(player);
	}

	@Override
	public int getField(int index) {
		int value = 0;
		switch(index) {
		case 0:
			value = this.burnTime;
			break;
		case 1:
			value = this.energy;
			break;
		case 2:
			value = this.capacity;
			break;
		case 3:
			value = this.maxExtract;
			break;
		case 4:
			value = this.maxReceive;
			break;
		case 5:
			value = this.currentEnergyTicks;
			break;
		case 6: 
			value = this.insantity;
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
			this.energy = value;
			break;
		case 2:
			this.capacity = value;
			break;
		case 3:
			this.maxExtract = value;
			break;
		case 4:
			this.maxReceive = value;
			break;
		case 5:
			this.currentEnergyTicks = value;
			break;
		case 6: 
			this.insantity = value;
			break;
		}
	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 7;
	}

	// Add stuff for transfer
	// Fuel Slot [0]
	private boolean isFuelSlot(int index) {
		return index == 0;
	}
	// Speed Upgrade Slot [1]
	private boolean isSpeedUpgradeSlot(int index) {
		return index == 1;
	}
	// Efficency Upgrade Slot [2]
	private boolean isEfficencySlot(int index) {
		return index == 2;
	}
	// Player Hidden Inventory [3-29]
	private boolean isPlayerHiddenInventory(int index) {
		return index >= 3 && index < 30;
	}
	// Player Active Inventory [30-38]
	private boolean isPlayerActiveInventory(int index) {
		return index >= 30 && index < 39;
	}
}
