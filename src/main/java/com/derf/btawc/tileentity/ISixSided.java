package com.derf.btawc.tileentity;

import java.util.List;

import net.minecraft.util.EnumFacing;

/**
 * This is a simple taging interface for SixSided Inventory of any type. And there are 4 of them
 * 
 * IInventory
 * FluidTank
 * EnergyStorage
 * Redstone.
 * 
 * 
 * in BTAWC Extensions eventually
 * IC2 energy storage
 * Botaina Mana Storage
 * ...
 * More mod storage with 6 sided inventories :)
 * @author Fred
 *
 */
public interface ISixSided {
	void setType(EnumFacing side, EnumSixSided type);
	
	EnumSixSided getType(EnumFacing side);
	
	boolean isTypeOff(EnumFacing side);
	
	boolean isTypePull(EnumFacing side);
	
	boolean isTypePush(EnumFacing side);
	
	boolean isTypeDisabled(EnumFacing side);
	
	List<EnumSixSided> getAllTypes();
}
