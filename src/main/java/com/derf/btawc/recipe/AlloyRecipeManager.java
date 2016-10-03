package com.derf.btawc.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.minecraft.item.ItemStack;

public final class AlloyRecipeManager {
	private static Map<AlloyRecipe, ItemStack> recipes = new HashMap<AlloyRecipe, ItemStack>();
	
	public static void addRecipe(AlloyRecipe recipe, ItemStack output) {
		recipes.put(recipe, output);
	}
	
	public static void addRecipe(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack input4, ItemStack result) {
		recipes.put(new AlloyRecipe(input1, input2, input3, input4), result);
	}
	
	public static ItemStack getResult(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack input4) {
		Set<Map.Entry<AlloyRecipe, ItemStack>> entrySet = recipes.entrySet();
		ItemStack result = null;
		
		for(Map.Entry<AlloyRecipe, ItemStack> e : entrySet) {
			if(e.getKey().isRecipe(input1, input2, input3, input4)) {
				result = e.getValue();
				break;
			}
		}
		
		return result;
	}
	
	public static Map<AlloyRecipe, ItemStack> getRecipeMap() {
		return recipes;
	}
	
	public static List<Entry<AlloyRecipe, ItemStack>> toList() {
		Set<Map.Entry<AlloyRecipe, ItemStack>> es = recipes.entrySet();
		List<Map.Entry<AlloyRecipe, ItemStack>> temp = new ArrayList<Map.Entry<AlloyRecipe, ItemStack>>();
		for(Map.Entry<AlloyRecipe, ItemStack> e : es) {
			temp.add(e);
		}
		return temp;
	}
}
