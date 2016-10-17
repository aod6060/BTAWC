package com.derf.btawc.items.tools;

import java.util.ArrayList;
import java.util.List;

import com.derf.btawc.blocks.BlockManager;
import com.derf.btawc.util.OreDictionaryUtils;
import com.derf.btawc.util.Utils;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemPickaxeOfGreed extends ItemPickaxe {

	private int count;
	
	private static List<Block> blocks = new ArrayList<Block>();
	
	public ItemPickaxeOfGreed(String name, int count, int maxDamage) {
		super(ToolMaterial.DIAMOND);
		this.count = count;
		this.setUnlocalizedName(name);
		this.setMaxDamage(maxDamage);
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
		
		blocks.add(Blocks.LIT_REDSTONE_ORE);
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
	
	@Override
	public boolean isFull3D() {
		return true;
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
					List<ItemStack> stacks = state.getBlock().getDrops(world, pos, state, Utils.rand.nextInt(4));
					
					for(ItemStack s : stacks) {
						world.spawnEntityInWorld(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), s));
					}
				}
			}
		}
		
		return super.onBlockDestroyed(stack, world, state, pos, entityLiving);
	}
	
}
