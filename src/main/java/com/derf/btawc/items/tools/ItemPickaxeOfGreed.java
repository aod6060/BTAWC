package com.derf.btawc.items.tools;

import java.util.ArrayList;
import java.util.List;

import com.derf.btawc.Loader;
import com.derf.btawc.util.OreDictionaryUtils;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemPickaxeOfGreed extends ItemPickaxe {

	private int count;
	
	private static List<Block> blocks = new ArrayList<Block>();
	
	public ItemPickaxeOfGreed(String name, int count) {
		super(ToolMaterial.EMERALD);
		this.count = count;
		this.setUnlocalizedName(name);
		this.setTextureName(Loader.MODID + ":" + name);
	}
	
	public static void registerOres() {
		List<List<ItemStack>> result = OreDictionaryUtils.searchForItemStack("ore");
		
		for(List<ItemStack> stacks : result) {
			for(ItemStack stack : stacks) {
				Block block = Block.getBlockFromItem(stack.getItem());
				if(block != null) {
					System.out.println("block added to PickaxeOfGreed: " + block.getLocalizedName());
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
	
	@Override
	public boolean isFull3D() {
		return true;
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
				if(this.isBlockDuplicatable(block)) {
					for(int i = 0; i < this.count - 1; i++) {
						
						List<ItemStack> stacks = block.getDrops(world, x, y, z, world.getBlockMetadata(x, y, z), this.itemRand.nextInt(4));
						
						for(ItemStack s : stacks) {
							EntityItem items = new EntityItem(world, x, y, z, s);
							world.spawnEntityInWorld(items);
						}
					}
				}
			}
		
		return super.onBlockDestroyed(stack, world, block, x, y, z, entity);
	}
}
