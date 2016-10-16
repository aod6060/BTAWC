package com.derf.btawc.inventory;

import java.util.List;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public interface ISixSidedInventory {
	void setType(EnumFacing side, EnumSixSidedType type);
	
	EnumSixSidedType getType(EnumFacing side);
	
	boolean isTypeOff(EnumFacing side);
	
	boolean isTypePull(EnumFacing side);
	
	boolean isTypePush(EnumFacing side);
	
	boolean isTypeDisabled(EnumFacing side);
	
	boolean isInventory(TileEntity entity);
	
	boolean isSixSidedInventory(TileEntity entity);
	
	boolean isSidedInventory(TileEntity entity);
	
	List<EnumSixSidedType> getAllTypes();
	
	void pull(EnumFacing face);
	
	void push(EnumFacing face);
	
}
