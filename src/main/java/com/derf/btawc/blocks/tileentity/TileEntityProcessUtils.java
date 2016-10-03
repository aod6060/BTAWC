package com.derf.btawc.blocks.tileentity;

import com.derf.btawc.blocks.furnace.BlockAlloyFurnace;
import com.derf.btawc.util.FuelUtils;

import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public final class TileEntityProcessUtils {
	
	
	public static void handleProcessWithFuel(
			World world, 
			TileEntity entity,
			IInventory inventory,
			IProcess process,
			IFuelUsage usage) {
		boolean flag = usage.isBurning();
		boolean flag1 = false;
		
		// Decrement Burning time...
		if(usage.isBurning()) {
			usage.decreaseFuelTime();
		}
		
		if(!world.isRemote) {
			if(!usage.isBurnTimeZero() || inventory.getStackInSlot(usage.getFuelSlot()) != null && process.isInputNull()) {
				if(usage.isBurnTimeZero() && process.canProcess()) {
					process.setCurrentItemBurnTime(FuelUtils.getItemBurnTime(inventory.getStackInSlot(usage.getFuelSlot())));
					usage.setBurnTime(FuelUtils.getItemBurnTime(inventory.getStackInSlot(usage.getFuelSlot())));
					if(usage.isBurning()) {
						flag1 = true;
						if(inventory.getStackInSlot(usage.getFuelSlot()) != null) {
							inventory.decrStackSize(usage.getFuelSlot(), 1);
							if(inventory.getStackInSlot(usage.getFuelSlot()).stackSize <= 0) {
								inventory.setInventorySlotContents(usage.getFuelSlot(), inventory.getStackInSlot(usage.getFuelSlot()).getItem().getContainerItem(inventory.getStackInSlot(usage.getFuelSlot())));
							}
						}
					}
				}
				if(usage.isBurning() && process.canProcess()) {
					process.incrementCookingTime();
					if(process.getCookingTime() >= process.getMaxCookingTime()) {
						process.resetCookingTime();
						process.process();
						flag1 = true;
					}
				} else {
					process.resetCookingTime();
				}
			}
			if(flag != usage.isBurning()) {
				flag1 = true;
				process.updateBlockState();
			}
		}
		if(flag1) {
			entity.markDirty();
		}
	}
}
