package com.derf.btawc.blocks.witherproof;

import com.derf.btawc.blocks.basic.BlockBasic;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWitherProof extends BlockBasic {

	public BlockWitherProof(String name, float lightlevel, SoundType type) {
		super(name, Material.rock, 2.0f, 100.0f, lightlevel, "pickaxe", 2, type);
	}

	@Override
	public void onBlockExploded(World world, int x, int y, int z, Explosion explosion) {
		// Does nothing because this block can't be destroyed by a wither
	}

	@Override
	public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
		boolean b = super.canEntityDestroy(world, x, y, z, entity);
		
		if(entity instanceof EntityWither) {
			return false;
		}
		
		return b;
	}
	
	
}
