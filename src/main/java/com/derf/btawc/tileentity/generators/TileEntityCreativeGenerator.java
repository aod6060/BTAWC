package com.derf.btawc.tileentity.generators;

import java.util.List;

import javax.annotation.Nullable;

import com.derf.btawc.energy.EnergyStorage;
import com.derf.btawc.items.ItemsManager;
import com.derf.btawc.util.Holder;
import com.derf.btawc.util.InventoryUtils;

import cofh.api.energy.IEnergyReceiver;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

public class TileEntityCreativeGenerator extends TileEntityGenerator implements IInventory {
	public static final int SPEED_UPGRADE_SLOT = 0;
	public static final int MAX_SLOT_SIZE = 1;
	public static final int MAX_CAPACITY = 1000000000; // Holds a trillion RF
	// Inventory
	private ItemStack[] inventory = new ItemStack[MAX_SLOT_SIZE];
	private String name;
	// This is the default rf/t
	private int energyTicks = 128;
	// This is the speed upgrades
	//public int speedUpgrades = 0; // [0 - 4]
	// This is insantiy points
	public int insantity = 1; // 1 - 4096
	// This is the current ticks
	public int currentEnergyTicks = 0;
	
	public TileEntityCreativeGenerator() {
		super();
		this.storage = new EnergyStorage(MAX_CAPACITY);
	}

	@Override
	public void update() {
		
		// Caculate Addition mul for speedUpgrades
		if(!worldObj.isRemote) {
			/*
			int mul = 1;
			
			int size = 0;
			
			if(this.inventory[SPEED_UPGRADE_SLOT] != null) {
				size = this.inventory[SPEED_UPGRADE_SLOT].stackSize;
			}
			
			for(int i = 0; i < size; i++) {
				mul *= 2;
			}
			
			// Calculate Actual RF/t
			this.currentEnergyTicks = this.energyTicks * mul * this.insantity;
			
			if(this.currentEnergyTicks > this.storage.getCapacity()) {
				this.currentEnergyTicks = this.storage.getCapacity();
			}
		
			this.storeIntoBuffer(this.currentEnergyTicks);
			this.outputAllSides(this.currentEnergyTicks);
			
			int delta = storage.receiveEnergy(this.currentEnergyTicks, true);
			this.storage.receiveEnergy(delta, false);
			
			List<Holder> sides = Holder.getHolders(pos);
			for(Holder side : sides) {
				TileEntity entity = worldObj.getTileEntity(side.getPos());
				
				if(entity != null && entity instanceof IEnergyReceiver) {
					IEnergyReceiver handler = (IEnergyReceiver)entity;
					int ee = this.extractEnergy(side.getDirection(), this.currentEnergyTicks, true);
					int er = handler.receiveEnergy(side.getDirection().getOpposite(), ee, true);
					this.extractEnergy(side.getDirection(), er, false);
					handler.receiveEnergy(side.getDirection().getOpposite(), er, false);
				}
			}
			*/
			this.onEnergyUpdate(this.currentEnergyTicks);
			this.markDirty();
		}
		
	}

	@Override
	protected void caculateRFTicks() {
		int mul = 1;
		
		int size = 0;
		
		if(this.inventory[SPEED_UPGRADE_SLOT] != null) {
			size = this.inventory[SPEED_UPGRADE_SLOT].stackSize;
		}
		
		for(int i = 0; i < size; i++) {
			mul *= 2;
		}
		
		// Calculate Actual RF/t
		this.currentEnergyTicks = this.energyTicks * mul * this.insantity;
		
		if(this.currentEnergyTicks > this.storage.getCapacity()) {
			this.currentEnergyTicks = this.storage.getCapacity();
		}
	}
	
	@Override
	public String printEnergyValue() {
		String s = String.format("[%d/%d] %d RF/t", this.storage.getEnergyStored(), this.storage.getMaxEnergyStored(), this.currentEnergyTicks);
		return s;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.currentEnergyTicks = compound.getInteger("CurrentEnergyTicks");
		//this.speedUpgrades = tag.getInteger("SpeedUpgrades");
		this.insantity = compound.getInteger("Insanity");
		// Inventory
		InventoryUtils.loadInventory(this, compound);
		
		if(compound.hasKey("CustomName")) {
			this.name = compound.getString("CustomName");
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setInteger("CurrentEnergyTicks", this.currentEnergyTicks);
		//tag.setInteger("SpeedUpgrades", this.speedUpgrades);
		compound.setInteger("Insanity", this.insantity);
		// Inventory
		InventoryUtils.saveInventory(this, compound);
		
		if(this.hasCustomName()) {
			compound.setString("CustomName", this.name);
		}
		
		return super.writeToNBT(compound);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.hasCustomName()? this.name : "container.creative_generator";
	}

	@Override
	public boolean hasCustomName() {
		// TODO Auto-generated method stub
		return this.name != null && !this.name.isEmpty();
	}

	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return this.inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		// TODO Auto-generated method stub
		return this.inventory[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		// TODO Auto-generated method stub
		return ItemStackHelper.getAndSplit(this.inventory, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		// TODO Auto-generated method stub
		return ItemStackHelper.getAndRemove(this.inventory, index);
	}

	@Override
	public void setInventorySlotContents(int index, @Nullable ItemStack stack) {
		this.inventory[index] = stack;
		
		if(stack != null && stack.stackSize > this.getInventoryStackLimit()) {
			stack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		// TODO Auto-generated method stub
		return this.worldObj.getTileEntity(pos) != this? false : player.getDistanceSq(pos.add(0.5, 0.5, 0.5)) <= 64.0;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return isSpeedUpgrade(stack);
	}

	@Override
	public int getField(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value) {
	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		for(int i = 0; i < inventory.length; i++) {
			inventory[i] = null;
		}
	}

	public static boolean isSpeedUpgrade(ItemStack stack) {
		return stack.getItem() == ItemsManager.speedUpgradeChip;
	}
	
	public int getNumberOfSpeedUpgrades() {
		int num = 0;
		
		if(this.inventory[SPEED_UPGRADE_SLOT] != null) {
			num = this.inventory[SPEED_UPGRADE_SLOT].stackSize;
		}
		return num;
	}
}
