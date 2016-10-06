package com.derf.btawc.recipe;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.minecraft.item.ItemStack;

public final class ChipMakerRecipeManager {
	
	private static Map<ChipMakerRecipe, ItemStack> recipes = new HashMap<ChipMakerRecipe, ItemStack>();
	private static int id = -1;
	private static Comparator<Entry<ChipMakerRecipe, ItemStack>> comp = create();
	
	public static void addRecipe(ChipMakerRecipe recipe, ItemStack result) {
		recipes.put(recipe, result);
	}
	
	public static void addRecipe(ItemStack material, int amount, ItemStack result) {
		recipes.put(new ChipMakerRecipe(++id, material, amount), result);
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
		temp.sort(comp);
		return temp;
	}
	
	public static Comparator<Entry<ChipMakerRecipe, ItemStack>> create() {
		return new Comparator<Entry<ChipMakerRecipe, ItemStack>>() {
			@Override
			public int compare(Entry<ChipMakerRecipe, ItemStack> entry1, Entry<ChipMakerRecipe, ItemStack> entry2) {
				return entry1.getKey().getId() - entry2.getKey().getId();
			}
		};
	}
} 
