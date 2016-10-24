package com.derf.btawc.jei.chipmaker;

import java.util.List;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.item.ItemStack;

public class ChipMakerRecipeWrapper extends BlankRecipeWrapper {

	private final ItemStack input;
	private final ItemStack redstone;
	private final ItemStack output;
	
	//public ChipMakerRecipeWrapper() {}
	
	public ChipMakerRecipeWrapper(ItemStack input, ItemStack redstone, ItemStack output) {
		this.input = input;
		this.redstone = redstone;
		this.output = output;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInput(ItemStack.class, input);
		ingredients.setInput(ItemStack.class, redstone);
		ingredients.setOutput(ItemStack.class, output);
	}

	public ItemStack getInput() {
		return this.input;
	}
	
	public ItemStack getRedstone() {
		return this.redstone;
	}
	
	public ItemStack getOutput() {
		return this.output;
	}
}
