package com.derf.btawc.items.tools;

import java.util.ArrayList;
import java.util.List;

import com.derf.btawc.Loader;
import com.derf.btawc.util.OreDictionaryUtils;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemAxeOfGreed extends ItemAxe {

	private int count;
	private static List<Block> blocks = new ArrayList<Block>();
	
	public ItemAxeOfGreed(String name, int count, int maxDamage) {
		super(ToolMaterial.EMERALD);
		this.setUnlocalizedName(name);
		this.setTextureName(Loader.MODID + ":" + name);
		this.count = count;
		this.setMaxDamage(maxDamage);
	}

	@Override
	public boolean onBlockDestroyed(
			ItemStack stack, 
			World world, 
			Block block, 
			int x,
			int y, 
			int z, 
			EntityLivingBase entity) {
		
		
		if(!world.isRemote) {
			if(isBlockDuplicatable(block)) {
				for(int i = 0; i < this.count - 1; i++) {
					List<ItemStack> stacks = block.getDrops(world, x, y, z, world.getBlockMetadata(x, y, z), itemRand.nextInt(4));
					
					for(ItemStack s : stacks) {
						EntityItem items = new EntityItem(world, x, y, z, s);
						world.spawnEntityInWorld(items);
					}
				}
			}
		}
		return super.onBlockDestroyed(stack, world, block, x, y, z,entity);
	}
	
	public static void registerLogs() {
		List<List<ItemStack>> result = OreDictionaryUtils.searchForItemStack("log");
		
		for(List<ItemStack> stacks : result) {
			for(ItemStack stack : stacks) {
				Block block = Block.getBlockFromItem(stack.getItem());
				if(block != null) {
					System.out.println("block added to AxeOfGreed: " + block.getLocalizedName());
					blocks.add(block);
				}
			}
		}
	}
	
	public static boolean isBlockDuplicatable(Block block) {
		boolean b = false;
		
		for(Block bl : blocks) {
			if(bl == block) {
				b = true;
				break;
			}
		}
		
		return b;
	}
}
