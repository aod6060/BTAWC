package com.derf.btawc.inventory.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fluids.capability.ItemFluidContainer;

public class SlotFluidContainer extends Slot {

	public SlotFluidContainer(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		// TODO Auto-generated method stub
		return stack != null && (stack.getItem() instanceof ItemBucket || stack.getItem() instanceof ItemFluidContainer || stack.getItem() instanceof UniversalBucket);
	}
	
	
}
