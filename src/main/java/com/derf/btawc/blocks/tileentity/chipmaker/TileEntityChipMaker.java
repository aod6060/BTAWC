package com.derf.btawc.blocks.tileentity.chipmaker;

import com.derf.btawc.blocks.chipmaker.BlockChipMaker;
import com.derf.btawc.blocks.tileentity.IFuelUsage;
import com.derf.btawc.blocks.tileentity.IProcess;
import com.derf.btawc.blocks.tileentity.TileEntityBasic;
import com.derf.btawc.blocks.tileentity.TileEntityProcessUtils;
import com.derf.btawc.recipe.ChipMakerRecipe;
import com.derf.btawc.recipe.ChipMakerRecipeManager;
import com.derf.btawc.recipe.RecipeHolder;
import com.derf.btawc.util.FuelUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityChipMaker extends TileEntityBasic implements IInventory, ITickable, IFuelUsage, IProcess {

	// Static Stuff
	public static final int COOKING_SPEED = 600;
	public static final int MATERIAL_SLOT = 0;
	public static final int REDSTONE_SLOT = 1;
	public static final int OUTPUT_SLOT = 2;
	public static final int FUEL_SLOT = 3;
	public static final int INVENTORY_SIZE = 4;
	
	// Private Stuffed needed By inventory
	//rivate Inventory inventory = new Inventory(4, "container.chip_maker");
	private ItemStack[] inventory = new ItemStack[INVENTORY_SIZE];
	
	private String name;
	private int burnTime;
	private int currentItemBurnTime;
	private int cookTime;
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.hasCustomName()? this.name : "container.chip_maker";
	}

	public void setName(String name) {
		this.name = name;
	}
	@Override
	public boolean hasCustomName() {
		// TODO Auto-generated method stub
		return this.name != null && !this.name.isEmpty();
	}

	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return inventory[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(this.inventory, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		boolean b = false;
		
		if(index == OUTPUT_SLOT) {
			b = false;
		} else if(index == MATERIAL_SLOT) {
			b = true;
		} else if(index == REDSTONE_SLOT) {
			b = stack.getItem() == Items.REDSTONE;
		} else {
			ItemStack fuelStack = this.inventory[FUEL_SLOT];
			b = FuelUtils.isItemFuel(stack) || SlotFurnaceFuel.isBucket(stack) && (fuelStack == null || fuelStack.getItem() != Items.BUCKET);
		}
		
		return b;
	}

	@Override
	public int getField(int id) {
		int temp = 0;
		switch(id) {
		case 0:
			temp = this.burnTime;
			break;
		case 1:
			temp = this.currentItemBurnTime;
			break;
		case 2:
			temp = this.cookTime;
			break;
		}
		return temp;
	}

	@Override
	public void setField(int id, int value) {
		switch(id) {
		case 0:
			burnTime = value;
			break;
		case 1:
			this.currentItemBurnTime = value;
			break;
		case 2:
			this.cookTime = value;
			break;
		}
	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public void clear() {
		for(ItemStack s : inventory) {
			s = null;
		}
	}

	@Override
	public void update() {
		TileEntityProcessUtils.handleProcessWithFuel(worldObj, this, this, this, this);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.burnTime = compound.getInteger("BurnTime");
		this.cookTime = compound.getInteger("CookTime");
		this.currentItemBurnTime = FuelUtils.getItemBurnTime(this.getStackInSlot(FUEL_SLOT));
		// Handle Inventory
		NBTTagList list = compound.getTagList("Items", Constants.NBT.TAG_COMPOUND);
		this.inventory = new ItemStack[this.getSizeInventory()];
		
		for(int i = 0; i < list.tagCount(); i++) {
			NBTTagCompound comp = list.getCompoundTagAt(i);
			int index = comp.getInteger("Slot");
			if(index >= 0 && index < this.getSizeInventory()) {
				this.inventory[index] = ItemStack.loadItemStackFromNBT(comp);
			}
		}
		
		if(compound.hasKey("CustomName")) {
			this.name = compound.getString("CustomName");
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("BurnTime", this.burnTime);
		compound.setInteger("CookTime", this.cookTime);
		// Handle Inventory
		NBTTagList list = new NBTTagList();
		for(int i = 0; i < this.getSizeInventory(); i++) {
			if(this.inventory[i] != null) {
				NBTTagCompound comp = new NBTTagCompound();
				comp.setInteger("Slot", i);
				inventory[i].writeToNBT(comp);
				list.appendTag(comp);
			}
		}
		compound.setTag("Items", list);
		if(this.hasCustomName()) {
			compound.setString("CustomName", this.name);
		}
		return compound;
	}

	@Override
	public boolean canProcess() {
		RecipeHolder<ChipMakerRecipe, ItemStack> holder = ChipMakerRecipeManager.getResult(this.getStackInSlot(MATERIAL_SLOT), this.getStackInSlot(REDSTONE_SLOT));
		
		
		if(holder == null) {
			return false;
		}
		
		if(this.getStackInSlot(OUTPUT_SLOT) == null) {
			return true;
		}
		
		if(!this.getStackInSlot(OUTPUT_SLOT).isItemEqual(holder.getResult())) {
			return false;
		}
		
		int result = getStackInSlot(OUTPUT_SLOT).stackSize + holder.getResult().stackSize;
		
		return result <= this.getInventoryStackLimit() && result <= this.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
	}

	@Override
	public void process() {
		if(this.canProcess()) {
			RecipeHolder<ChipMakerRecipe, ItemStack> result = ChipMakerRecipeManager.getResult(
					this.getStackInSlot(MATERIAL_SLOT), 
					this.getStackInSlot(REDSTONE_SLOT));
			
			if(this.getStackInSlot(OUTPUT_SLOT) == null) {
				this.setInventorySlotContents(OUTPUT_SLOT, result.getResult().copy());
			} else if(this.getStackInSlot(OUTPUT_SLOT).getItem() == result.getResult().getItem()) {
				this.getStackInSlot(OUTPUT_SLOT).stackSize += result.getResult().stackSize;
			}
			// Update Material slot
			if(this.getStackInSlot(MATERIAL_SLOT) != null) {
				--this.getStackInSlot(MATERIAL_SLOT).stackSize;
				if(this.getStackInSlot(MATERIAL_SLOT).stackSize <= 0) {
					this.setInventorySlotContents(MATERIAL_SLOT, null);
				}
			}
			// Update Redstone slot
			if(this.getStackInSlot(REDSTONE_SLOT) != null) {
				this.getStackInSlot(REDSTONE_SLOT).stackSize -= result.getRecipe().getRedstone().stackSize;
				if(this.getStackInSlot(REDSTONE_SLOT).stackSize <= 0) {
					this.setInventorySlotContents(REDSTONE_SLOT, null);
				}
			}
		}
	}

	@Override
	public boolean isInputNull() {
		/*
		boolean b = false;
		for(int i = 0; i < 2; i++) {
			b = b || this.getStackInSlot(i) != null;
		}
		*/
		return this.getStackInSlot(MATERIAL_SLOT) != null && this.getStackInSlot(REDSTONE_SLOT) != null;
	}

	@Override
	public void setCurrentItemBurnTime(int itemBurnTime) {
		this.currentItemBurnTime = itemBurnTime;
	}

	@Override
	public void incrementCookingTime() {
		++this.cookTime;
	}

	@Override
	public int getCookingTime() {
		// TODO Auto-generated method stub
		return this.cookTime;
	}

	@Override
	public void resetCookingTime() {
		this.cookTime = 0;
	}

	@Override
	public int getMaxCookingTime() {
		// TODO Auto-generated method stub
		return COOKING_SPEED;
	}

	@Override
	public void updateBlockState() {
		BlockChipMaker.setChipMakerState(isBurning(), worldObj, pos);
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

	public boolean isSlotNull(int index) {
		return getStackInSlot(index) == null;
	}

	@SideOnly(Side.CLIENT)
	public int getBuringTimeRemainingScaled(int scale) {
		if(this.currentItemBurnTime == 0) {
			this.currentItemBurnTime = this.getMaxCookingTime();
		}
		return this.burnTime * scale / this.currentItemBurnTime;
	}
	
	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int scale) {
		// TODO Auto-generated method stub
		return this.cookTime * scale / COOKING_SPEED;
	}
}
