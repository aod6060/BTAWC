package com.derf.btawc.blocks.tileentity.cobblestonegenerator;

import java.util.List;

import com.derf.btawc.blocks.tileentity.TileEntityBasic;
import com.derf.btawc.inventory.EnumSixSidedType;
import com.derf.btawc.inventory.ISixSidedInventory;
import com.derf.btawc.util.InventoryUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileEntityCobblestoneGenerator extends TileEntityBasic implements IInventory, ITickable {
	public static final int COBBLESTONE_SLOT = 0;
	
	private ItemStack[] inventory = new ItemStack[1];
	private String name;
	// I don't need a six sided inventory for this. I probably don't need a gui either hmm maybe
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.hasCustomName()? this.name : "container.cobblestone_generator";
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
		// TODO Auto-generated method stub
		return this.worldObj.getTileEntity(pos) != this? false : player.getDistanceSq(pos.add(0.5, 0.5, 0.5)) <= 64.0;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		// TODO Auto-generated method stub
		return stack.getItem() == Item.getItemFromBlock(Blocks.COBBLESTONE);
	}

	@Override
	public int getField(int id) {return 0;}

	@Override
	public void setField(int id, int value) {}

	@Override
	public int getFieldCount() {return 0;}

	@Override
	public void clear() {
		this.inventory = new ItemStack[1];
	}

	@Override
	public void update() {
		if(!worldObj.isRemote) {
			InventoryUtils.addToInventory(this, new ItemStack(Blocks.COBBLESTONE), 0);
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		super.readFromNBT(compound);
		InventoryUtils.loadInventory(this, compound);
		if(compound.hasKey("CustomName")) {
			this.name = compound.getString("CustomName");
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		InventoryUtils.saveInventory(this, compound);
		if(this.hasCustomName()) {
			compound.setString("CustomName", this.name);
		}
		return super.writeToNBT(compound);
	}

	
}
