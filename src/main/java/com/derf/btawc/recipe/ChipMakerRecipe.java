package com.derf.btawc.recipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ChipMakerRecipe {
	private ItemStack material = null;
	private ItemStack redstone = null;
	
	
	public ChipMakerRecipe() {}
	
	public ChipMakerRecipe(ItemStack material, int amount) {
		this.material = material;
		this.redstone = new ItemStack(Items.REDSTONE, amount);
	}
	
	public ItemStack getMaterial() {
		return material;
	}
	
	public ItemStack getRedstone() {
		return redstone;
	}
	
	public boolean isRecipe(ItemStack material, ItemStack redstone) {
		boolean b = false;
		if(material != null && material.getItem() == this.material.getItem()) {
			if(redstone != null && redstone.stackSize >= this.redstone.stackSize) {
				b = true;
			} else {
				b = false;
			}
		} else {
			b = false;
		}
		
		return b;
	}
}
