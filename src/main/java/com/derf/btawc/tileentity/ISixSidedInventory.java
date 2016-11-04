package com.derf.btawc.tileentity;

import java.util.List;

import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public interface ISixSidedInventory extends ISixSided {
	
	boolean isInventory(TileEntity entity);
	
	boolean isSixSidedInventory(TileEntity entity);
	
	boolean isSidedInventory(TileEntity entity);
	
	void pull(EnumFacing face);
	
	void push(EnumFacing face);
	
	IInventory getInventory();
	
	List<IInventory> getInventories();
	
}
