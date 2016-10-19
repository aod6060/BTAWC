package com.derf.btawc.items.tools;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;

import com.derf.btawc.BTAWCLogger;
import com.derf.btawc.Loader;
import com.derf.btawc.blocks.BlockManager;
import com.derf.btawc.util.OreDictionaryUtils;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemAxeOfGreed extends ItemAxe {

	private int count;
	private static List<Block> blocks = new ArrayList<Block>();
	
	public ItemAxeOfGreed(String name, int count, int maxDamage) {
		super(ToolMaterial.DIAMOND);
		this.setUnlocalizedName(name);
		this.count = count;
		this.setMaxDamage(maxDamage);
	}
	
	@Override
	public boolean onBlockDestroyed(
			ItemStack stack, 
			World world, 
			IBlockState state, 
			BlockPos pos,
			EntityLivingBase entityLiving) {
		
		if(!world.isRemote) {
			if(this.isBlockDuplicatable(state.getBlock())) {
				for(int i = 0; i < this.count - 1; i++) {
					List<ItemStack> stacks = state.getBlock().getDrops(world, pos, state, 0);
					
					for(ItemStack s : stacks) {
						world.spawnEntityInWorld(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), s));
					}
				}
			}
		}
		return super.onBlockDestroyed(stack, world, state, pos, entityLiving);
	}


	public static void registerLogs() {
		List<List<ItemStack>> result = OreDictionaryUtils.searchForItemStack("log");
		
		for(List<ItemStack> stacks : result) {
			for(ItemStack stack : stacks) {
				Block block = Block.getBlockFromItem(stack.getItem());
				if(block != null) {
					//System.out.println("block added to AxeOfGreed: " + block.getLocalizedName());
					BTAWCLogger.getLogger().log(Level.INFO, "Block was added to Axe of Greed: "+block.getLocalizedName());
					blocks.add(block);
				}
			}
		}
		
		result = OreDictionaryUtils.searchForItemStack("wood");
		
		for(List<ItemStack> stacks : result) {
			for(ItemStack stack : stacks) {
				Block block = Block.getBlockFromItem(stack.getItem());
				if(block != null) {
					//System.out.println("block added to AxeOfGreed: " + block.getLocalizedName());
					BTAWCLogger.getLogger().log(Level.INFO, "Block was added to Axe of Greed: "+block.getLocalizedName());
					blocks.add(block);
				}
			}
		}
		
		blocks.add(BlockManager.simpleDungeonPresent);
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
