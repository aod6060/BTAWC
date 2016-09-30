package com.derf.btawc.blocks.energystorage;

import com.derf.btawc.blocks.basic.BlockContainerBasic;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public abstract class BlockEnergyStorage extends BlockContainerBasic {

	public BlockEnergyStorage(String name) {
		super(name, Material.IRON, 2.0f, 2.0f, 0, "pickaxe", 0, SoundType.METAL);
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
		// TODO Auto-generated method stub
		
		boolean b = false;
		
		if(!world.isRemote) {
			BlockPos pos = new BlockPos(x, y, z);
			
			TileEntity entity = WorldUtils.getTileEntity(world, pos);
			
			if(entity != null && entity instanceof IEnergyLevelPrintable) {
				IEnergyLevelPrintable handler = (IEnergyLevelPrintable)entity;
				handler.printEnergyValue(player);
				b = true;
			}
		}
		
		return b;
	}
	*/
	
}
