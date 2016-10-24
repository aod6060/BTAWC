package com.derf.btawc.jei.alloyfurnace;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.derf.btawc.recipe.handler.AlloyFurnaceRecipe;
import com.derf.btawc.recipe.handler.AlloyFurnaceRecipeManager;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;

public class AlloyFurnaceRecipeMaker {
	public static List<AlloyFurnaceRecipeWrapper> getAlloyFurnaceResults(IJeiHelpers helpers) {
		IStackHelper stackHelper = helpers.getStackHelper();
		//AlloyFurnaceRecipeManager
		List<Entry<AlloyFurnaceRecipe, ItemStack>> alloyFurnaceRecipes = AlloyFurnaceRecipeManager.toList();
		
		List<AlloyFurnaceRecipeWrapper> recipes = new ArrayList<AlloyFurnaceRecipeWrapper>();
		
		for(Entry<AlloyFurnaceRecipe, ItemStack> entry : alloyFurnaceRecipes) {
			AlloyFurnaceRecipe input = entry.getKey();
			ItemStack output = entry.getValue();
			
			List<ItemStack> inputs = new ArrayList<ItemStack>();
			inputs.add(input.getInput1());
			inputs.add(input.getInput2());
			inputs.add(input.getInput3());
			inputs.add(input.getInput4());
			AlloyFurnaceRecipeWrapper recipe = new AlloyFurnaceRecipeWrapper(inputs, output);
			recipes.add(recipe);
		}
		
		return recipes;
	}
}
