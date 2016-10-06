package com.derf.btawc.recipe;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AlloyRecipe {
	private int id;
	// Input Stuff
	private ItemStack input1 = null;
	private ItemStack input2 = null;
	private ItemStack input3 = null;
	private ItemStack input4 = null;
	
	public AlloyRecipe(int id, ItemStack input1, ItemStack input2, ItemStack input3, ItemStack input4) {
		this.id = id;
		this.input1 = input1;
		this.input2 = input2;
		this.input3 = input3;
		this.input4 = input4;
	}
	
	public ItemStack getInput1() {
		return input1;
	}
	
	public ItemStack getInput2() {
		return input2;
	}
	
	public ItemStack getInput3() {
		return input3;
	}
	
	public ItemStack getInput4() {
		return input4;
	}
	
	public List<ItemStack> getInputs() {
		List<ItemStack> temp = new ArrayList<ItemStack>();
		temp.add(input1);
		temp.add(input2);
		temp.add(input3);
		temp.add(input4);
		return temp;
	}
	
	public boolean isRecipe(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack input4) {
		List<ItemStack> recipeStacks = this.getInputs();
		List<ItemStack> stacks = this.createItemStack(input1, input2, input3, input4);
		boolean b = true;
		
		
		for(int i = 0; i < 4; i++) {
			
			if(recipeStacks.get(i) != null && stacks.get(i) != null) {
				if(recipeStacks.get(i).getItem() == stacks.get(i).getItem()) {
					b = b && true;
				} else {
					b = b && false;
				}
			} else if(recipeStacks.get(i) != null && stacks.get(i) == null) {
				b = b && false;
			} else if(recipeStacks.get(i) == null && stacks.get(i) != null) {
				b = b && false;
			} else {
				b = b && true;
			}
			
		}
		
		return b;
	}
	
	private List<ItemStack> createItemStack(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack input4) {
		List<ItemStack> temp = new ArrayList<ItemStack>();
		temp.add(input1);
		temp.add(input2);
		temp.add(input3);
		temp.add(input4);
		return temp;
	}

	public int getId() {
		return id;
	}
	
	
}
