package com.derf.btawc.blocks.tileentity.generators;

import com.derf.btawc.BTAWCLogger;
import com.derf.btawc.energy.EnergyStorage;
import com.derf.btawc.items.ItemsManager;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

/**
 * Alright time for the solar panel. I'm currently looking at the Daylight Scensor to see how to 
 * works and I'm going to use its source as a base.
 * @author Fred
 *
 */
public class TileEntitySolarPanel extends TileEntityGenerator implements IInventory {
	// Statics Stuff
	public static final int SPEED_UPGRADE_SLOT = 0;
	public static final int MAX_SLOT_SIZE = 1;
	public static final int MAX_CAPACITY = 500000;
	// Caculate Insantity
	public static final int INSANTITY_MAX = 4096;
	// Inventory
	private ItemStack[] inventory = new ItemStack[MAX_SLOT_SIZE];
	// Custom Name
	private String name;
	// Default Energy Ticks
	private int energyTicks = 128;
	// Instantiy
	private int insantity = 1;
	// Current Energy Ticks
	private int currentEnergyTicks = 0;
	
	public TileEntitySolarPanel() {
		super();
		this.storage = new EnergyStorage(MAX_CAPACITY);
	}
	
	@Override
	public void update() {
		if(!worldObj.isRemote && worldObj.getWorldTime() % 20L == 0L) {
			int baseRF = this.caculateBaseRF();
			int speedUpgrades = this.caculateSpeedUpdates();
			int insantity = this.caculateInsantityFromPanes();
			this.currentEnergyTicks = baseRF;
			
		}
	}
	
	private int caculateInsantityFromPanes() {
		// TODO Auto-generated method stub
		return 0;
	}

	private int caculateSpeedUpdates() {
		// TODO Auto-generated method stub
		return 0;
	}

	private int caculateBaseRF() {
		int i = worldObj.getLightFor(EnumSkyBlock.SKY, pos) - worldObj.getSkylightSubtracted();
		int value = 4; // Efficency;
		int efficency = value + i;
		efficency = MathHelper.clamp_int(efficency, 0, 4); // This will effectively make sure that the
		// Block produces power 
		return (int)(energyTicks * ((float) efficency / 4.0f));
	}
	@Override
	public String getName() {
		return this.hasCustomName()? name : "container.solar_panel";
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean hasCustomName() {
		return this.name != null && !this.name.isEmpty();
	}

	@Override
	public int getSizeInventory() {
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
		if(stack!=null && stack.stackSize > this.getInventoryStackLimit()) {
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
		if(stack != null) {
			return stack.getItem() == ItemsManager.speedUpgradeChip;
		}
		return false;
	}

	@Override
	public int getField(int id) {
		int value = 0;
		
		switch(id) {
		case 0:
			value = this.getStorage().getEnergy();
			break;
		case 1:
			value = this.getStorage().getCapacity();
			break;
		case 2:
			value = this.getStorage().getMaxExtract();
			break;
		case 3:
			value = this.getStorage().getMaxReceive();
			break;
		case 4:
			value = this.currentEnergyTicks;
			break;
		case 5:
			value = this.insantity;
			break;
		}
		
		return value;
	}

	@Override
	public void setField(int id, int value) {
		switch(id) {
		case 0:
			this.getStorage().setEnergy(value);
			break;
		case 1:
			this.getStorage().setCapacity(value);
			break;
		case 2:
			this.getStorage().setMaxExtract(value);
			break;
		case 3:
			this.getStorage().setMaxReceive(value);
			break;
		case 4:
			this.currentEnergyTicks = value;
			break;
		case 5:
			this.insantity = value;
			break;
		}
	}

	@Override
	public int getFieldCount() {
		return 6;
	}

	@Override
	public void clear() {
		this.inventory = new ItemStack[MAX_SLOT_SIZE];
	}

}
