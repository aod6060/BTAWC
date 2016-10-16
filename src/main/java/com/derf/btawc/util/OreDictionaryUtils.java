package com.derf.btawc.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public final class OreDictionaryUtils {
	public static List<String> search(String query) {
		List<String> temp = new ArrayList<String>();
		String[] names = OreDictionary.getOreNames();
		
		for(String n : names) {
			if(n.startsWith(query)) {
				temp.add(n);
			}
		}
		
		return temp;
	}
	
	public static List<List<ItemStack>> searchForItemStack(String query) {
		List<List<ItemStack>> temp = new ArrayList<List<ItemStack>>();
		List<String> names = search(query);
		
		for(String n : names) {
			List<ItemStack> stacks = OreDictionary.getOres(n);
			temp.add(stacks);
		}
		
		return temp;
	}
	
	public static List<ItemStack> getItemsStacksFromItemStack(ItemStack stack) {
		int[] ids = OreDictionary.getOreIDs(stack);
		
		if(ids.length >= 0) {
			return null;
		}
		
		String name = OreDictionary.getOreName(ids[0]);
		
		List<ItemStack> temp = OreDictionary.getOres(name);
		
		return temp;
	}
}
