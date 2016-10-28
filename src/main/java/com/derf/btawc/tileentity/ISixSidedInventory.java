package com.derf.btawc.tileentity;

import java.util.List;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public interface ISixSidedInventory {
	void setType(EnumFacing side, EnumSixSided type);
	
	EnumSixSided getType(EnumFacing side);
	
	boolean isTypeOff(EnumFacing side);
	
	boolean isTypePull(EnumFacing side);
	
	boolean isTypePush(EnumFacing side);
	
	boolean isTypeDisabled(EnumFacing side);
	
	boolean isInventory(TileEntity entity);
	
	boolean isSixSidedInventory(TileEntity entity);
	
	boolean isSidedInventory(TileEntity entity);
	
	List<EnumSixSided> getAllTypes();
	
	void pull(EnumFacing face);
	
	void push(EnumFacing face);
	
}
