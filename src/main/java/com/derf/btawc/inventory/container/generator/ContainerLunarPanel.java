package com.derf.btawc.inventory.container.generator;

import com.derf.btawc.inventory.container.ContainerBasic;
import com.derf.btawc.inventory.container.IIsSlotCorrect;
import com.derf.btawc.inventory.slot.SlotFilterBuilder;
import com.derf.btawc.items.ItemsManager;
import com.derf.btawc.tileentity.IField;
import com.derf.btawc.tileentity.generators.TileEntityLunarPanel;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerLunarPanel extends ContainerBasic implements IField, IIsSlotCorrect {

	private static final int LUNAR_PANE_SLOT = 0;
	private static final int SPEED_UPGRADE_SLOT = 1;
	private static final int PLAYER_HIDDEN_INVENTORY = 2;
	private static final int PLAYER_ACTIVE_INVENTORY = 3;
	
	private final InventoryPlayer player;
	private TileEntityLunarPanel generator;
	
	private int energy; // 0
	private int capacity; // 1
	private int maxExtract; // 2
	private int maxReceive; // 3
	private int currentEnergyTicks; // 4
	private int insantity; // 5
	private int efficency; // 6
	
	public ContainerLunarPanel(InventoryPlayer player, TileEntityLunarPanel generator) {
		this.player = player;
		this.generator = generator;
		// Lunar Pane Slot [0] (151, 17)
		SlotFilterBuilder builder = new SlotFilterBuilder(generator, 0, 151, 17);
		builder.add(ItemsManager.lunarPane);
		this.addSlotToContainer(builder.create());
		// Speed Upgrade Slot [1] (151, 41)
		builder = new SlotFilterBuilder(generator, 1, 151, 41);
		builder.add(ItemsManager.speedUpgradeChip);
		this.addSlotToContainer(builder.create());
		// Player Inventory [2-37] (9,97) (9,155)
		this.createPlayerInventory(player, 9, 97, 9, 155);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		// TODO Auto-generated method stub
		return generator.isUseableByPlayer(player);
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
			for(int j = 0; j < this.generator.getField(j); j++) {
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
		// TODO Auto-generated method stub
		super.updateProgressBar(id, data);
		this.generator.setField(id, data);
	}

	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index) {
		// TODO Auto-generated method stub
		ItemStack stack = null;
		Slot slot = (Slot)this.inventorySlots.get(index);
		if(slot != null && slot.getHasStack()) {
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			
			if(!this.isSlotCorrect(LUNAR_PANE_SLOT, index) && !this.isSlotCorrect(SPEED_UPGRADE_SLOT, index)) {
				if(stack1.getItem() == ItemsManager.lunarPane) {
					if(!this.merge(stack1, 0, 1, false)) {
						return null;
					}
				} else if(stack1.getItem() == ItemsManager.speedUpgradeChip) {
					if(!this.merge(stack1, 1, 2, false)) {
						return null;
					}
				} else if(this.isSlotCorrect(PLAYER_HIDDEN_INVENTORY, index)) {
					if(!this.merge(stack1, 29, 38, false)) {
						return null;
					}
				} else if(this.isSlotCorrect(PLAYER_ACTIVE_INVENTORY, index)) {
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
		return 7;
	}

	public boolean isSlotCorrect(int type, int index) {
		switch(type) {
		case LUNAR_PANE_SLOT:
			return index == 0;
		case SPEED_UPGRADE_SLOT:
			return index == 1;
		case PLAYER_HIDDEN_INVENTORY:
			return index >= 2 && index < 29;
		case PLAYER_ACTIVE_INVENTORY:
			return index >= 29 && index < 38;
		default:
			return false;
		}
	}
}
