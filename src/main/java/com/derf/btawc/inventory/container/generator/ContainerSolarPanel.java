package com.derf.btawc.inventory.container.generator;

import com.derf.btawc.inventory.container.ContainerBasic;
import com.derf.btawc.inventory.slot.SlotFilterBuilder;
import com.derf.btawc.items.ItemsManager;
import com.derf.btawc.tileentity.IField;
import com.derf.btawc.tileentity.generators.TileEntitySolarPanel;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSolarPanel extends ContainerBasic implements IField{
	
	private final InventoryPlayer player;
	private TileEntitySolarPanel generator;
	
	
	private int energy; // 0
	private int capacity; // 1
	private int maxExtract; // 2
	private int maxReceive; // 3
	private int currentEnergyTicks; // 4
	private int insantity; // 5
	private int efficency; // 6
	
	public ContainerSolarPanel(InventoryPlayer player, TileEntitySolarPanel generator) {
		this.player = player;
		this.generator = generator;
		// Solar Pane Slot [0]
		SlotFilterBuilder builder = new SlotFilterBuilder(generator, 0, 151, 17);
		builder.add(ItemsManager.solarPane);
		this.addSlotToContainer(builder.create());
		// Speed Upgrades [1]
		builder = new SlotFilterBuilder(generator, 1, 151, 41);
		builder.add(ItemsManager.speedUpgradeChip);
		this.addSlotToContainer(builder.create());
		// Player Inventory [2-37]
		this.createPlayerInventory(player, 9, 97, 9, 155);
	}
	
	
	@Override
	public void addListener(IContainerListener listener) {
		// TODO Auto-generated method stub
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.generator);
	}


	@Override
	public void detectAndSendChanges() {
		// TODO Auto-generated method stub
		super.detectAndSendChanges();
		for(int i = 0; i < this.listeners.size(); i++) {
			IContainerListener listener = this.listeners.get(i);
			for(int j = 0; j < this.generator.getFieldCount(); j++) {
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
			
			if(!this.isSolarPaneSlot(index) && !this.isSpeedUpgradeSlot(index)) {
				if(stack1.getItem() == ItemsManager.solarPane) {
					if(!this.merge(stack1, 0, 1, false)) {
						return null;
					}
				} else if(stack1.getItem() == ItemsManager.speedUpgradeChip) {
					if(!this.merge(stack1, 1, 2, false)) {
						return null;
					}
				} else if(this.isHiddenPlayerInventory(index)) {
					if(!this.merge(stack1, 29, 38, false)) {
						return null;
					}
				} else if(this.isActivePlayerInventory(index)) {
					if(!this.merge(stack1, 2, 29, false)) {
						return null;
					}
				}
			} else if(!this.merge(stack1, 2, 38, false)) {
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
			value = this.energy;
			break;
		case 1:
			value = this.capacity;
			break;
		case 2:
			value = this.maxExtract;
			break;
		case 3:
			value = this.maxReceive;
			break;
		case 4:
			value = this.currentEnergyTicks;
			break;
		case 5:
			value = this.insantity;
			break;
		case 6:
			value = this.efficency;
			break;
		}
		
		return value;
	}
	@Override
	public void setField(int index, int value) {
		switch(index) {
		case 0:
			this.energy = value;
			break;
		case 1:
			this.capacity = value;
			break;
		case 2:
			this.maxExtract = value;
			break;
		case 3:
			this.maxReceive = value;
			break;
		case 4:
			this.currentEnergyTicks = value;
			break;
		case 5:
			this.insantity = value;
			break;
		case 6:
			this.efficency = value;
			break;
		}
	}
	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 7;
	}
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		// TODO Auto-generated method stub
		return generator.isUseableByPlayer(player);
	}
	
	private boolean isSolarPaneSlot(int index) {
		return index == 0;
	}
	
	private boolean isSpeedUpgradeSlot(int index) {
		return index == 1;
	}
	
	private boolean isHiddenPlayerInventory(int index) {
		return index >= 2 && index < 29;
	}
	
	private boolean isActivePlayerInventory(int index) {
		return index >= 29 && index < 38;
	}
}
