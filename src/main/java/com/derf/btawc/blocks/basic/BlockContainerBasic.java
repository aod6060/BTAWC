package com.derf.btawc.blocks.basic;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;

public abstract class BlockContainerBasic extends BlockContainer {

	public BlockContainerBasic(
			String name, 
			Material material,
			float hardness,
			float resistance,
			float lightLevel,
			String toolClass,
			int level,
			SoundType sound) {
		super(material);
		this.setUnlocalizedName(name);
		this.setHardness(hardness);
		this.setResistance(resistance);
		this.setLightLevel(lightLevel);
		this.setHarvestLevel(toolClass, level);
		this.setSoundType(sound);
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		// TODO Auto-generated method stub
		return EnumBlockRenderType.MODEL;
	}

	
}
