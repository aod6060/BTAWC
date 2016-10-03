package com.derf.btawc.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.minecraft.item.ItemStack;

public final class ChipMakerRecipeManager {
	
	
	
	private static Map<ChipMakerRecipe, ItemStack> recipes = new HashMap<ChipMakerRecipe, ItemStack>();
	
	public static void addRecipe(ChipMakerRecipe recipe, ItemStack result) {
		recipes.put(recipe, result);
	}
	
	public static void addRecipe(ItemStack material, int amount, ItemStack result) {
		recipes.put(new ChipMakerRecipe(material, amount), result);
	}
	
	public static RecipeHolder<ChipMakerRecipe, ItemStack> getResult(ItemStack material, ItemStack redstone) {
		RecipeHolder<ChipMakerRecipe, ItemStack> temp = null;
		
		Set<Entry<ChipMakerRecipe, ItemStack>> set = recipes.entrySet();
		
		for(Entry<ChipMakerRecipe, ItemStack> s : set) {
			if(s.getKey().isRecipe(material, redstone)) {
				temp = new RecipeHolder<ChipMakerRecipe, ItemStack>(s.getKey(), s.getValue());
				break;
			}
		}
		
		return temp;
	}
	
	public static Map<ChipMakerRecipe, ItemStack> getRecipeMap() {
		return recipes;
	}
	
	public static List<Entry<ChipMakerRecipe, ItemStack>> toList() {
		Set<Entry<ChipMakerRecipe, ItemStack>> es = recipes.entrySet();
		List<Entry<ChipMakerRecipe, ItemStack>> temp = new ArrayList<Entry<ChipMakerRecipe, ItemStack>>();
		for(Entry<ChipMakerRecipe, ItemStack> e : es) {
			temp.add(e);
		}
		return temp;
	}
} 
