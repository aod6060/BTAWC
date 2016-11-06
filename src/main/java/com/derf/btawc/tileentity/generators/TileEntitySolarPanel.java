package com.derf.btawc.tileentity.generators;

import com.derf.btawc.energy.EnergyStorage;
import com.derf.btawc.items.ItemsManager;
import com.derf.btawc.util.InventoryUtils;
import com.derf.btawc.util.Utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Alright time for the solar panel. I'm currently looking at the Daylight Sensor to see how to 
 * works and I'm going to use its source as a base.
 * @author Fred Cook
 */
public class TileEntitySolarPanel extends TileEntityGenerator implements IInventory {
	// Statics Stuff
	public static final int SOLAR_PANE_SLOT = 0; // up to 64...
	public static final int SPEED_UPGRADE_SLOT = 1;
	public static final int MAX_SLOT_SIZE = 2;
	public static final int MAX_CAPACITY = 500000;
	// Caculate Insantity
	public static final int INSANTITY_MIN = 1;
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
	// Efficency
	private int efficency;
	
	public TileEntitySolarPanel() {
		super();
		this.storage = new EnergyStorage(MAX_CAPACITY);
	}
	
	@Override
	public void update() {
		if(!worldObj.isRemote) {
			
			this.efficency = this.getGeneratorEfficency();
			
			if(!this.isLessThanZero()) {
				this.getStorage().setMaxTransfer(this.g);
				this.onEnergyUpdate();
			} else {
				if(!this.getStorage().isEmpty()) {
					this.currentEnergyTicks = this.energyTicks * this.caculateSpeedUpdates();
					this.outputAllSides();
				}
			}
			this.markDirty();
		}
	}

	@Override
	protected void caculateRFTicks() {
		int baseRF = this.caculateBaseRF();
		int speedUpgrades = this.caculateSpeedUpdates();
		this.insantity = this.caculateInsantityFromPanes();
		this.currentEnergyTicks = baseRF * speedUpgrades * insantity;
	}

	private int caculateInsantityFromPanes() {
		int temp = 1;
		if(this.getStackInSlot(SOLAR_PANE_SLOT) != null) {
			int stackSize = this.getStackInSlot(SOLAR_PANE_SLOT).stackSize;
			float rep_stackSize = (float) stackSize / 64f;
			int modifier = (int) Utils.lerp(rep_stackSize, INSANTITY_MIN, INSANTITY_MAX);
			temp *= modifier;
		}
		return temp;
	}

	private int caculateSpeedUpdates() {
		int temp = 1;
		if(this.getStackInSlot(SPEED_UPGRADE_SLOT) != null) {
			for(int i = 0; i < this.getStackInSlot(SPEED_UPGRADE_SLOT).stackSize; i++) {
				temp *= 2;
			}
		}
		return temp;
	}

	public boolean isLessThanZero() {
		int efficency = this.getGeneratorEfficency();
		return efficency == 0;
	}
	
	public int getEfficency() {
		return this.efficency;
	}
	
	protected int getGeneratorEfficency() {
		int i = worldObj.getLightFor(EnumSkyBlock.SKY, pos) - worldObj.getSkylightSubtracted();
		int value = 5;
		int efficency = value + i;
		efficency= MathHelper.clamp_int(efficency, 0, 5);
		return efficency;
	}
	private int caculateBaseRF() {
		int efficency = this.getGeneratorEfficency();
		// Block produces power 
		return (int)(energyTicks * ((float) efficency / 5.0f));
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
			if(index == SPEED_UPGRADE_SLOT && stack.getItem() == ItemsManager.speedUpgradeChip) {
				return true;
			} else if(index == SOLAR_PANE_SLOT && stack.getItem() == ItemsManager.solarPane) {
				return true;
			}
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
		case 6:
			value = this.efficency;
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
		case 6:
			this.efficency = value;
			break; // Do Nothing because this value is procedurally generated.
		}
	}

	@Override
	public int getFieldCount() {
		return 7;
	}

	@Override
	public void clear() {
		this.inventory = new ItemStack[MAX_SLOT_SIZE];
	}

	@SideOnly(Side.CLIENT)
	public int getSun(int scale) {
		int i = worldObj.getLightFor(EnumSkyBlock.SKY, pos) - worldObj.getSkylightSubtracted();
		int value = 4; // Efficency;
		int efficency = value + i;
		efficency = MathHelper.clamp_int(efficency, 0, 4);
		return efficency * scale / 100;
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
		this.insantity = compound.getInteger("Insanity");
		// Inventory
		InventoryUtils.loadInventory(this, compound);
		if(compound.hasKey("CustomName")) {
			this.name = compound.getString("CustomName");
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		compound.setInteger("CurrentEnergyTicks", this.currentEnergyTicks);
		compound.setInteger("Insanity", this.insantity);
		// Inventory
		InventoryUtils.saveInventory(this, compound);
		if(this.hasCustomName()) {
			compound.setString("CustomName", this.name);
		}
		
		return super.writeToNBT(compound);
	}
	
	
}
