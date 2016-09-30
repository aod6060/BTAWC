package com.derf.btawc.blocks.generators;

import com.derf.btawc.blocks.basic.BlockContainerBasic;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSolidFuelGenerator extends BlockContainerBasic {

	public BlockSolidFuelGenerator(String name, Material material, float hardness, float resistance, float lightLevel,
			String toolClass, int level, SoundType sound) {
		super(name, material, hardness, resistance, lightLevel, toolClass, level, sound);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		// TODO Auto-generated method stub
		return null;
	}

}
