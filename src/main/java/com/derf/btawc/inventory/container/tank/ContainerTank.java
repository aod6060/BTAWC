package com.derf.btawc.inventory.container.tank;

import com.derf.btawc.inventory.container.ContainerBasic;
import com.derf.btawc.inventory.container.IIsSlotCorrect;
import com.derf.btawc.inventory.slot.SlotFluidContainer;
import com.derf.btawc.inventory.slot.SlotOutput;
import com.derf.btawc.tileentity.EnumSixSided;
import com.derf.btawc.tileentity.IField;
import com.derf.btawc.tileentity.tank.TileEntityTank;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fluids.capability.ItemFluidContainer;

public class ContainerTank extends ContainerBasic implements IField, IIsSlotCorrect {
	// Statics
	private static final int INPUT_SLOT = 0;
	private static final int OUTPUT_SLOT = 1;
	private static final int PLAYER_HIDDEN_INVENTORY = 2;
	private static final int PLAYER_ACTIVE_INVENTORY = 3;
	// Container Fields
	private final InventoryPlayer player;
	private TileEntityTank tank;
	
	// Fields
	public final EnumSixSided[] sided = new EnumSixSided[6];
	
	public ContainerTank(InventoryPlayer player, TileEntityTank tank) {
		this.player = player;
		this.tank = tank;
		// Input slot [0] (151, 17)
		this.addSlotToContainer(new SlotFluidContainer(tank, 0, 151, 17));
		// Output slot [1] (151, 37)
		this.addSlotToContainer(new SlotOutput(tank, 1, 151, 37));
		// Player Inventory [2-27] (9, 97) and (9, 155)
		this.createPlayerInventory(player, 9, 97, 9, 155);
		
		for(int i = 0; i < this.getFieldCount(); i++) {
			sided[i] = EnumSixSided.OFF;
		}
	}
	
	
	@Override
	public void addListener(IContainerListener listener) {
		// TODO Auto-generated method stub
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.tank);
	}


	@Override
	public void detectAndSendChanges() {
		// TODO Auto-generated method stub
		super.detectAndSendChanges();
		for(int i = 0; i < this.listeners.size(); i++) {
			IContainerListener listener = this.listeners.get(i);
			for(int j = 0; j < this.getFieldCount(); j++) {
				if(this.getField(j) != this.tank.getField(j)) {
					listener.sendProgressBarUpdate(this, j, this.tank.getField(j));
				}
			}
		}
		
		for(int i = 0; i < this.getFieldCount(); i++) {
			this.setField(i, this.tank.getField(i));
		}
	}


	@Override
	public void updateProgressBar(int id, int data) {
		// TODO Auto-generated method stub
		super.updateProgressBar(id, data);
		this.tank.setField(id, data);
	}


	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index) {
		ItemStack stack = null;
		Slot slot = (Slot)this.inventorySlots.get(index);
		
		if(slot != null && slot.getHasStack()) {
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			
			if(this.isSlotCorrect(OUTPUT_SLOT, index)) {
				if(!this.merge(stack1, 2, 38, true)) {
					return null;
				}
				slot.onSlotChange(stack1, stack);
			} else if(!this.isSlotCorrect(INPUT_SLOT, index)) {
				if(stack != null && (stack.getItem() instanceof ItemBucket || stack.getItem() instanceof ItemFluidContainer || stack.getItem() instanceof UniversalBucket)) {
					if(!this.merge(stack1, 0, 1, false)) {
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
	public boolean canInteractWith(EntityPlayer player) {
		// TODO Auto-generated method stub
		return tank.isUseableByPlayer(player);
	}

	@Override
	public boolean isSlotCorrect(int type, int index) {
		switch(type) {
		case INPUT_SLOT:
			return index == 0;
		case OUTPUT_SLOT:
			return index == 1;
		case PLAYER_HIDDEN_INVENTORY:
			return index >= 2 && index < 29;
		case PLAYER_ACTIVE_INVENTORY:
			return index >= 29 && index < 38;
		default:
			return false;
		}
	}

	@Override
	public int getField(int index) {
		return this.sided[index].ordinal();
	}

	@Override
	public void setField(int index, int value) {
		this.sided[index] = EnumSixSided.values()[value];
	}

	@Override
	public int getFieldCount() {
		return this.sided.length;
	}

}
