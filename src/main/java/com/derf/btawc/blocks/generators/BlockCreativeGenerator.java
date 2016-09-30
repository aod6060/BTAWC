package com.derf.btawc.blocks.generators;

import com.derf.btawc.Loader;
import com.derf.btawc.blocks.basic.BlockContainerBasic;
import com.derf.btawc.blocks.tileentity.generators.TileEntityCreativeGenerator;
import com.derf.btawc.client.gui.GuiHandler;
import com.derf.btawc.energy.IEnergyLevelPrintable;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCreativeGenerator extends BlockContainerBasic {
	
	public BlockCreativeGenerator() {
		super("creative_generator", Material.IRON, 2.0f, 2.0f, 0, "pickaxe", 0, SoundType.METAL);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		// TODO Auto-generated method stub
		return new TileEntityCreativeGenerator();
	}

	/*
	@Override
	public boolean onBlockActivated(
			World world, 
			int x, 
			int y, 
			int z,
			EntityPlayer player, 
			int side, 
			float tx, 
			float ty, 
			float tz) {
		boolean b = false;
		
		if(!world.isRemote) {
			player.openGui(Loader.INSTANCE, GuiHandler.CREATIVE_GENERATOR_GUI, world, x, y, z);
		}
		
		return b;
	}
	*/
}
