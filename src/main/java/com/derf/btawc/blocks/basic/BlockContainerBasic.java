package com.derf.btawc.blocks.basic;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

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

}
