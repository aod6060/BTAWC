package com.derf.btawc.recipe.handler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.minecraft.item.ItemStack;

public final class AlloyFurnaceRecipeManager {
	private static Map<AlloyFurnaceRecipe, ItemStack> recipes = new HashMap<AlloyFurnaceRecipe, ItemStack>();
	private static int id = -1;
	private static Comparator<Entry<AlloyFurnaceRecipe, ItemStack>> comp = create();
	
	public static void addRecipe(AlloyFurnaceRecipe recipe, ItemStack output) {
		recipes.put(recipe, output);
	}
	
	public static void addRecipe(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack input4, ItemStack result) {
		recipes.put(new AlloyFurnaceRecipe(++id, input1, input2, input3, input4), result);
	}
	
	public static ItemStack getResult(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack input4) {
		Set<Map.Entry<AlloyFurnaceRecipe, ItemStack>> entrySet = recipes.entrySet();
		ItemStack result = null;
		
		for(Map.Entry<AlloyFurnaceRecipe, ItemStack> e : entrySet) {
			if(e.getKey().isRecipe(input1, input2, input3, input4)) {
				result = e.getValue();
				break;
			}
		}
		
		return result;
	}
	
	public static Map<AlloyFurnaceRecipe, ItemStack> getRecipeMap() {
		return recipes;
	}
	
	public static List<Entry<AlloyFurnaceRecipe, ItemStack>> toList() {
		Set<Map.Entry<AlloyFurnaceRecipe, ItemStack>> es = recipes.entrySet();
		List<Map.Entry<AlloyFurnaceRecipe, ItemStack>> temp = new ArrayList<Map.Entry<AlloyFurnaceRecipe, ItemStack>>();
		for(Map.Entry<AlloyFurnaceRecipe, ItemStack> e : es) {
			temp.add(e);
		}
		temp.sort(comp);
		return temp;
	}
	
	private static Comparator<Entry<AlloyFurnaceRecipe, ItemStack>> create() {
		return new Comparator<Entry<AlloyFurnaceRecipe, ItemStack>>() {
			@Override
			public int compare(Entry<AlloyFurnaceRecipe, ItemStack> entry1, Entry<AlloyFurnaceRecipe, ItemStack> entry2) {
				// TODO Auto-generated method stub
				return entry1.getKey().getId() - entry2.getKey().getId();
			}
		};
	}
}
