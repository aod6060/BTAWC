package com.derf.btawc.inventory.container.itembuffer;

import com.derf.btawc.inventory.container.ContainerBasic;
import com.derf.btawc.tileentity.EnumSixSided;
import com.derf.btawc.tileentity.IField;
import com.derf.btawc.tileentity.itembuffer.TileEntityItemBuffer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerItemBuffer extends ContainerBasic implements IField {

	// Private Fields
	private final InventoryPlayer player;
	private TileEntityItemBuffer itembuffer;
	
	// Fields
	public final EnumSixSided[] sided = new EnumSixSided[6];
	
	public ContainerItemBuffer(InventoryPlayer player, TileEntityItemBuffer itembuffer) {
		this.player = player;
		this.itembuffer = itembuffer;
		
		// Create Slot for itembuffer inventory [0-8] (9, 17)
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 3; x++) {
				this.addSlotToContainer(new Slot(itembuffer, y * 3 + x, x * 18 + 9, y * 18 + 17));
			}
		}
		// Create Player inventory [9-44]
		this.createPlayerInventory(player, 9, 93, 9, 151);
		
		// Set Sided to off
		for(int i = 0; i < sided.length; i++) {
			sided[i] = EnumSixSided.OFF;
		}
	}
	
	
	@Override
	public void addListener(IContainerListener listener) {
		// TODO Auto-generated method stub
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.itembuffer);
	}


	@Override
	public void detectAndSendChanges() {
		// TODO Auto-generated method stub
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.listeners.size(); i++) {
			IContainerListener listener = this.listeners.get(i);
			for(int j = 0; j < this.getFieldCount(); j++) {
				if(this.getField(j) != this.itembuffer.getField(j)) {
					listener.sendProgressBarUpdate(this, j, this.itembuffer.getField(j));
				}
			}
		}
		
		for(int i = 0; i < this.getFieldCount(); i++) {
			this.setField(i, this.itembuffer.getField(i));
		}
	}


	@Override
	public void updateProgressBar(int id, int data) {
		this.itembuffer.setField(id, data);
	}


	@Override
	public boolean canInteractWith(EntityPlayer player) {
		// TODO Auto-generated method stub
		return this.itembuffer.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index) {
		ItemStack stack = null;
		Slot slot = this.inventorySlots.get(index);
		if(slot != null && slot.getHasStack()) {
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			
			if(this.isItemBufferInventory(index)) {
				if(!this.merge(stack1, 9, 45, false)) {
					return null;
				}
			} else if(!this.isItemBufferInventory(index)) {
				if(!this.merge(stack1, 0, 9, false)) {
					return null;
				}
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

	private boolean isItemBufferInventory(int index) {
		return index >= 0 && index < 9;
	}
	
	private boolean isPlayerHiddenInventory(int index) {
		return index >= 9 && index < 36;
	}
	
	private boolean isPlayerRegularInventory(int index) {
		return index >= 36 && index < 45;
	}

	@Override
	public int getField(int id) {
		return this.sided[id].ordinal();
	}

	@Override
	public void setField(int id, int value) {
		this.sided[id] = EnumSixSided.values()[value];
	}

	@Override
	public int getFieldCount() {
		return sided.length;
	}
}
