package com.derf.btawc.util;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;

public class GeneratorFuelUtils {
	public boolean isItemFuel(ItemStack stack) {
		return getItemBurnTime(stack) > 0;
	}

	public static int getItemBurnTime(ItemStack stack) {
		// TODO Auto-generated method stub
		int burnTime = 0;
		
		if(stack == null) {
			burnTime = 0;
		} else {
			Item item = stack.getItem();
			
			if(item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air) {
				Block block = Block.getBlockFromItem(item);
				
				if(block == Blocks.wooden_slab) {
					burnTime = 150;
				} else if(block.getMaterial() == Material.wood) {
					burnTime = 300;
				} else if(block == Blocks.coal_block) {
					burnTime = 16000;
				}
			} else if(item instanceof ItemTool && ((ItemTool)item).getToolMaterialName().equals("WOOD")) {
				burnTime = 200;
			} else if(item instanceof ItemSword && ((ItemSword)item).getToolMaterialName().equals("WOOD")) {
				burnTime = 200;
			} else if(item instanceof ItemHoe && ((ItemHoe)item).getToolMaterialName().equals("WOOD")) {
				burnTime = 200;
			} else if(item == Items.stick) {
				burnTime = 100;
			} else if(item == Items.coal) {
				burnTime = 1600;
			} else if(item == Items.lava_bucket) {
				burnTime = 20000;
			} else if(item == Item.getItemFromBlock(Blocks.sapling)) {
				burnTime = 100;
			} else if(item == Items.blaze_rod) {
				burnTime = 2400;
			} else {
				burnTime = GameRegistry.getFuelValue(stack);
			}
		}
		
		return burnTime;
	}
}
