package com.derf.btawc.jei.chipmaker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.derf.btawc.recipe.handler.ChipMakerRecipe;
import com.derf.btawc.recipe.handler.ChipMakerRecipeManager;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;

public class ChipMakerRecipeMaker {
	public static List<ChipMakerRecipeWrapper> getChipMakerResults(IJeiHelpers helper) {
		IStackHelper stackHelper = helper.getStackHelper();
		List<Entry<ChipMakerRecipe, ItemStack>> recipes = ChipMakerRecipeManager.toList();
		List<ChipMakerRecipeWrapper> temp = new ArrayList<ChipMakerRecipeWrapper>();
		
		for(Entry<ChipMakerRecipe, ItemStack> r : recipes) {
			ChipMakerRecipe rep = r.getKey();
			ItemStack output = r.getValue();
			
			ChipMakerRecipeWrapper wrapper = new ChipMakerRecipeWrapper(rep.getMaterial(), rep.getRedstone(), output);
			temp.add(wrapper);
		}
		
		return temp;
	}
}
