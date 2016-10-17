package com.derf.btawc.blocks.lootpresents;

import java.util.List;
import java.util.Random;

import com.derf.btawc.blocks.basic.BlockBasic;
import com.derf.btawc.util.LootUtils;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLootPresent extends BlockBasic {

	private ResourceLocation location = null;
	
	public BlockLootPresent(String name, ResourceLocation location) {
		super(name, Material.CLOTH, 1.0f, 1.0f, 0.0f, "axe", 0, SoundType.CLOTH);
		this.location = location;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		return LootUtils.grabRandomItemStacksFromLootTable((World) world, this.location);
	}

	@Override
	public int quantityDropped(Random random) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
