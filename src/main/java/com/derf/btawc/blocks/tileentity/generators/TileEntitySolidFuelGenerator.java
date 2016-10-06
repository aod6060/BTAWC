package com.derf.btawc.blocks.tileentity.generators;

import java.util.List;

import com.derf.btawc.blocks.tileentity.IFuelUsage;
import com.derf.btawc.energy.EnergyStorage;
import com.derf.btawc.util.FuelUtils;
import com.derf.btawc.util.Holder;

import cofh.api.energy.IEnergyReceiver;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;


/**
 * 

Alright I'm back to doing the generators... I've been thinking of what I want to do with them. Right now I'm working on the Solid Fuel Generator. What I want to do with it is make it change how much energy it produces depending on what fuel you give it. The run ticks doesn't really matter I'm think of making it run for a select number of ticks. But change it energy value with how rich the fuel is.

    * Energy Production is Variable
    * Fuel Ticks is constant. 
    * Energy Value for fuel. 
    * Minimum is 100 RF/t to 1,000,000 RF/t depending on the fuel. 
    * All fuel has a constant 1800 Fuel/t
    * Default energy output is 100 RF/t without fuel


 * @author Fred
 *
 */
public class TileEntitySolidFuelGenerator extends TileEntityGenerator implements IInventory, IFuelUsage {
	// Static Stuff
	public static final int FUEL_SLOT = 0;
	// Fuel Generator Inventory
	private ItemStack[] inventory = new ItemStack[1];
	// burn time
	private int burnTime;
	// Name
	private String name;

	public TileEntitySolidFuelGenerator() {
		super();
		this.storage = new EnergyStorage(1000000, 100);
	}
	
	@Override
	public void update() {
		// Handle Fuel
		// Charge Internal Buffer
		// Power Receivers on 6 faces.
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return this.hasCustomName()? this.name : "container.solid_fuel_generator";
	}

	@Override
	public boolean hasCustomName() {
		return this.name != null && !this.name.isEmpty();
	}

	@Override
	public boolean isBurning() {
		// TODO Auto-generated method stub
		return this.burnTime > 0;
	}

	@Override
	public void decreaseFuelTime() {
		--this.burnTime;
	}

	@Override
	public boolean isBurnTimeZero() {
		// TODO Auto-generated method stub
		return this.burnTime == 0;
	}

	@Override
	public int getFuelSlot() {
		// TODO Auto-generated method stub
		return FUEL_SLOT;
	}

	@Override
	public void setBurnTime(int burnTime) {
		this.burnTime = burnTime;
	}

	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return this.inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return this.inventory[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(this.inventory, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(this.inventory, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
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
		return this.worldObj.getTileEntity(pos) != this? false : player.getDistanceSq(pos.add(0.5, 0.5, 0.5)) <= 64.0;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return FuelUtils.isItemFuel(stack) || SlotFurnaceFuel.isBucket(stack) && (stack == null || stack.getItem() != Items.BUCKET);
	}

	@Override
	public int getField(int id) {
		int value = 0;
		switch(id) {
		case 0:
			value = this.burnTime;
			break;
		case 1:
			value = this.getStorage().getEnergy();
			break;
		case 2:
			value = this.getStorage().getCapacity();
			break;
		case 3:
			value = this.getStorage().getMaxExtract();
			break;
		case 4:
			value = this.getStorage().getMaxReceive();
			break;
		}
		return value;
	}

	@Override
	public void setField(int id, int value) {
		switch(id) {
		case 0:
			this.burnTime = value;
			break;
		case 1:
			this.getStorage().setEnergy(value);
			break;
		case 2:
			this.getStorage().setCapacity(value);
			break;
		case 3:
			this.getStorage().setMaxExtract(value);
			break;
		case 4:
			this.getStorage().setMaxReceive(value);
			break;
		}
	}

	@Override
	public int getFieldCount() {
		return 5;
	}

	@Override
	public void clear() {
		for(int i = 0; i < this.inventory.length; i++) {
			this.inventory[i] = null;
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.burnTime = tag.getInteger("BurnTime");
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
		// TODO Auto-generated method stub
		super.writeToNBT(tag);
		tag.setInteger("BurnTime", this.burnTime);
		NBTTagList list = new NBTTagList();
		for(int i = 0; i < this.getSizeInventory(); i++) {
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
		
		return tag;
	}
	
	
}
