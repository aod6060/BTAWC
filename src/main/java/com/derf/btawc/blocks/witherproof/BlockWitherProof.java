package com.derf.btawc.blocks.witherproof;

import com.derf.btawc.blocks.basic.BlockBasic;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWitherProof extends BlockBasic {

	public BlockWitherProof(String name, float lightlevel, SoundType type) {
		super(name, Material.ROCK, 2.0f, 100.0f, lightlevel, "pickaxe", 2, type);
	}

	@Override
	public void onBlockExploded(World world, BlockPos pos, Explosion explosion) {
		super.onBlockExploded(world, pos, explosion);
	}

	@Override
	public boolean canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity) {
		boolean b = super.canEntityDestroy(state, world, pos, entity);
		
		if(entity instanceof EntityWither) {
			return false;
		}
		
		return b;
	}
	
}
