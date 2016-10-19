package com.derf.btawc.blocks.tileentity.generators;

import java.util.List;

import com.derf.btawc.blocks.generators.BlockSolidFuelGenerator;
import com.derf.btawc.blocks.tileentity.IFuelUsage;
import com.derf.btawc.energy.EnergyStorage;
import com.derf.btawc.util.FuelUtils;
import com.derf.btawc.util.Holder;
import com.derf.btawc.util.InventoryUtils;
import com.derf.btawc.util.Utils;

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
	public static final int SPEED_UPGRADE_SLOT = 1;
	public static final int FUEL_EFFICIENCY_SLOT = 2;
	public static final int MAX_SLOT_SIZE = 3;
	public static final int MAX_CAPACITY = 1000000000;
	// Caculate Insantiy
	public static final int INSANTITY_MIN = 1;
	public static final int INSANTITY_MAX = 4096;
	public static final int MAX_FUEL_SATURATION = 1000000;
	// Fuel Generator Inventory
	private ItemStack[] inventory = new ItemStack[MAX_SLOT_SIZE];
	// burn time
	private int burnTime;
	// Max Burn Time
	private int maxBurnTime = 2048; // This is the max burn time for all fuel
	// This is the default rf/t
	private int energyTicks = 128;
	// Insanity Modifier
	// This is the insantiy modifier
	private int insantity = 1; // [1-4096]
	// Current Energy Ticks
	
	// Name
	private String name;

	public TileEntitySolidFuelGenerator() {
		super();
		this.storage = new EnergyStorage(MAX_CAPACITY);
	}
	
	@Override
	public void update() {
		// Handle Fuel
		// Charge Internal Buffer
		// Power Receivers on 6 faces.
		boolean flag = this.isBurning();
		boolean flag1 = false;
		
		if(this.isBurning()) {
			this.decreaseFuelTime();
		}
		
		if(!worldObj.isRemote) {
			if(!this.isBurnTimeZero() || this.getStackInSlot(this.getFuelSlot()) != null) {
				if(this.isBurnTimeZero() && !this.getStorage().isFull()) {
					this.insantity = this.calculateInsantiyFromFuel();
					this.setBurnTime(this.maxBurnTime);
					if(this.isBurning()) {
						flag1 = true;
						if(this.getStackInSlot(this.getFuelSlot()) != null) {
							this.decrStackSize(this.getFuelSlot(), 1);
							if(this.getStackInSlot(this.getFuelSlot()).stackSize <= 0) {
								this.setInventorySlotContents(this.getFuelSlot(), this.getStackInSlot(this.getFuelSlot()).getItem().getContainerItem(this.getStackInSlot(this.getFuelSlot())));
							}
						}
					}
				}		
				
				if(this.isBurning() && !this.getStorage().isFull()) {
				// Increment Storage Buffer
				}
			}
			
			if(flag != this.isBurning()) {
				flag1 = true;
				BlockSolidFuelGenerator.updateBlockState(this.isBurning(), worldObj, pos);
			}
		}
		
		if(flag1) {
			this.markDirty();
		}
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
		case 5:
			value = this.energyTicks;
			break;
		case 6: 
			value = this.insantity;
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
		case 5:
			this.energyTicks = value;
			break;
		case 6: 
			this.insantity = value;
			break;
		}
	}

	@Override
	public int getFieldCount() {
		return 7;
	}

	@Override
	public void clear() {
		for(int i = 0; i < this.inventory.length; i++) {
			this.inventory[i] = null;
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.burnTime = compound.getInteger("BurnTime");
		// Inventory
		InventoryUtils.loadInventory(this, compound);
		if(compound.hasKey("CustomName")) {
			this.name = compound.getString("CustomName");
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		super.writeToNBT(compound);
		compound.setInteger("BurnTime", this.burnTime);
		// Inventory
		InventoryUtils.saveInventory(this, compound);
		if(this.hasCustomName()) {
			compound.setString("CustomName", this.name);
		}
		
		return compound;
	}
	
	public int calculateInsantiyFromFuel() {
		
		int temp = INSANTITY_MIN;
		
		if(this.getStackInSlot(this.getFuelSlot()) != null) {
			
			int fuel_ticks = FuelUtils.getItemBurnTime(this.getStackInSlot(this.getFuelSlot()));
			
			if(fuel_ticks > MAX_FUEL_SATURATION) {
				fuel_ticks = MAX_FUEL_SATURATION;
			}
			
			double re_fuel_tick = (double)fuel_ticks / (double)MAX_FUEL_SATURATION;
			
			double d = Utils.lerp(re_fuel_tick, 0.0f, 1.0f);
			
			temp = (int) (d * INSANTITY_MAX);
			
			if(temp < INSANTITY_MIN) {
				temp = INSANTITY_MIN;
			}
		}
		
		return temp;
	}
}
