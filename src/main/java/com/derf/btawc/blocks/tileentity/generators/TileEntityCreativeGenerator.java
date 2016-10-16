package com.derf.btawc.blocks.tileentity.generators;

import java.util.List;

import javax.annotation.Nullable;

import com.derf.btawc.energy.EnergyStorage;
import com.derf.btawc.items.ItemsManager;
import com.derf.btawc.util.Holder;

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
	private static final int SPEED_UPGRADE_SLOT = 0;
	private static final int MAX_CAPACITY = 134217728;
	// Inventory
	private ItemStack[] inventory = new ItemStack[1];
	private String name;
	// This is the default rf/t
	private int energyTicks = 64;
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
		
		if(!worldObj.isRemote) {
			this.markDirty();
		}
		
	}

	@Override
	public String printEnergyValue() {
		String s = String.format("[%d/%d] %d RF/t", this.storage.getEnergyStored(), this.storage.getMaxEnergyStored(), this.currentEnergyTicks);
		return s;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.currentEnergyTicks = tag.getInteger("CurrentEnergyTicks");
		//this.speedUpgrades = tag.getInteger("SpeedUpgrades");
		this.insantity = tag.getInteger("Insanity");
		
		NBTTagList list = tag.getTagList("Items", Constants.NBT.TAG_COMPOUND);
		this.inventory = new ItemStack[this.getSizeInventory()];
		
		for(int i = 0; i < list.tagCount(); i++) {
			NBTTagCompound comp = list.getCompoundTagAt(i);
			int index = comp.getInteger("Slot");
			if(index >= 0 && index < this.getSizeInventory()) {
				this.inventory[index] = ItemStack.loadItemStackFromNBT(comp);
			}
		}
		
		if(tag.hasKey("CustomName")) {
			this.name = tag.getString("CustomName");
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setInteger("CurrentEnergyTicks", this.currentEnergyTicks);
		//tag.setInteger("SpeedUpgrades", this.speedUpgrades);
		tag.setInteger("Insanity", this.insantity);
		NBTTagList list = new NBTTagList();
		
		for(int i = 0; i < this.inventory.length; i++) {
			if(this.inventory[i] != null) {
				NBTTagCompound comp = new NBTTagCompound();
				comp.setInteger("Slot", i);
				inventory[i].writeToNBT(comp);
				list.appendTag(comp);
			}
		}
		tag.setTag("Items", list);
		
		if(this.hasCustomName()) {
			tag.setString("CustomName", this.name);
		}
		
		return super.writeToNBT(tag);
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
