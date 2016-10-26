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
	private int currentEnergy;
	private int maxEnergy;
	private int maxExtract;
	private int maxReceive;
	private int currentEnergyTicks;
	public int insantiy;
	
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
	public void addListener(IContainerListener listener) {
		// TODO Auto-generated method stub
		super.addListener(listener);
		listener.sendProgressBarUpdate(this, 0, this.generator.getStorage().getEnergyStored());
		listener.sendProgressBarUpdate(this, 1, this.generator.getStorage().getCapacity());
		listener.sendProgressBarUpdate(this, 2, this.generator.getStorage().getMaxReceive());
		listener.sendProgressBarUpdate(this, 3, this.generator.getStorage().getMaxExtract());
		listener.sendProgressBarUpdate(this, 4, this.generator.currentEnergyTicks);
		listener.sendProgressBarUpdate(this, 5, this.generator.insantity);
	}
	
	@Override
	public void detectAndSendChanges() {
		// TODO Auto-generated method stub
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.listeners.size(); i++) {
			IContainerListener listener = this.listeners.get(i);
			
			if(this.currentEnergy != this.generator.getStorage().getEnergy()) {
				listener.sendProgressBarUpdate(this, 0, this.generator.getStorage().getEnergy());
			}
			
			if(this.maxEnergy != this.generator.getStorage().getCapacity()) {
				listener.sendProgressBarUpdate(this, 1, this.generator.getStorage().getCapacity());
			}
			
			if(this.maxReceive != this.generator.getStorage().getMaxReceive()) {
				listener.sendProgressBarUpdate(this, 2, this.generator.getStorage().getMaxReceive());
			}
			
			if(this.maxExtract != this.generator.getStorage().getMaxExtract()) {
				listener.sendProgressBarUpdate(this, 3, this.generator.getStorage().getMaxExtract());
			}
			
			if(this.currentEnergyTicks != this.generator.currentEnergyTicks) {
				listener.sendProgressBarUpdate(this, 4, this.generator.currentEnergyTicks);
			}
			
			if(this.insantiy != this.generator.insantity) {
				listener.sendProgressBarUpdate(this, 5, this.generator.insantity);
			}
		}
		
		this.currentEnergy = this.generator.getStorage().getEnergy();
		this.maxEnergy = this.generator.getStorage().getCapacity();
		this.maxReceive = this.generator.getStorage().getMaxReceive();
		this.maxExtract = this.generator.getStorage().getMaxExtract();
		this.currentEnergyTicks = this.generator.currentEnergyTicks;
		this.insantiy = this.generator.insantity;
	}
	
	
	@Override
	public void updateProgressBar(int index, int value) {
		super.updateProgressBar(index, value);
		switch(index) {
		case 0:
			this.generator.getStorage().setEnergy(value);
			break;
		case 1:
			this.generator.getStorage().setCapacity(value);
			break;
		case 2:
			this.generator.getStorage().setMaxReceive(value);
			break;
		case 3:
			this.generator.getStorage().setMaxExtract(value);
			break;
		case 4:
			this.generator.currentEnergyTicks = value;
			break;
		case 5:
			this.generator.insantity = value;
			break;
		}
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
