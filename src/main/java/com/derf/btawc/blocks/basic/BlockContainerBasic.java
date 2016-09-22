package com.derf.btawc.blocks.basic;

import com.derf.btawc.Loader;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.Block.SoundType;
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
		this.setBlockName(name);
		this.setBlockTextureName(Loader.MODID + ":" + name);
		this.setHardness(hardness);
		this.setResistance(resistance);
		this.setLightLevel(lightLevel);
		this.setHarvestLevel(toolClass, level);
		this.setStepSound(sound);
	}

}
