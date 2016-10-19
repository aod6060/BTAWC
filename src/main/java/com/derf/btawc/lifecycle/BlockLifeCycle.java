package com.derf.btawc.lifecycle;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;

public class BlockLifeCycle implements IBlockLifeCycle {
	
	private String registryName;
	private Block block;
	private CreativeTabs tabs;
	private Class<? extends TileEntity> tileEntityClass = null;
	private String oreDictionaryName = null;
	
	
	// Created a default constructor for reasons
	public BlockLifeCycle() {}
	
	public BlockLifeCycle(
			String registryName, 
			Block block, 
			CreativeTabs tabs,
			Class<? extends TileEntity> tileEntityClass, 
			String oreDictionaryName) {
		this.registryName = registryName;
		this.block = block;
		this.tileEntityClass = tileEntityClass;
		this.oreDictionaryName = oreDictionaryName;
		this.tabs = tabs;
	}
	
	@Override
	public String getRegistryName() {
		// TODO Auto-generated method stub
		return this.registryName;
	}

	@Override
	public String getOreDictionaryName() {
		// TODO Auto-generated method stub
		return this.oreDictionaryName;
	}

	@Override
	public Block getBlock() {
		// TODO Auto-generated method stub
		return this.block;
	}

	@Override
	public Class<? extends TileEntity> getTileEntityClass() {
		// TODO Auto-generated method stub
		return tileEntityClass;
	}

	@Override
	public boolean hasOredictionaryName() {
		// TODO Auto-generated method stub
		return this.oreDictionaryName != null && !this.oreDictionaryName.isEmpty();
	}

	@Override
	public boolean hasTileEnttiy() {
		// TODO Auto-generated method stub
		return this.tileEntityClass != null;
	}

	@Override
	public CreativeTabs getCreativeTabs() {
		// TODO Auto-generated method stub
		return tabs;
	}

}
