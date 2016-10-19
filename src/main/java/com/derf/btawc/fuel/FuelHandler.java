package com.derf.btawc.fuel;

import com.derf.btawc.items.ItemsManager;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;

public class FuelHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		int burn = 0;
		if(fuel.getItem() == ItemsManager.netherStarFuel) {
			burn = 1000000;
		}
		return burn;
	}

}
