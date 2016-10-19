package com.derf.btawc.lifecycle;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;

/**
 * This interface is for block registration for my LifeCycle system...
 * @author Fred
 *
 */
public interface IBlockLifeCycle {
	/**
	 * This is grab the blocks registry name
	 * @return String
	 */
	String getRegistryName();
	
	/**
	 * This will grab the blocks ore dictionary name or null
	 * @return STring
	 */
	String getOreDictionaryName();
	
	/**
	 * This will return the block
	 * @return Block
	 */
	Block getBlock();
	
	/**
	 * This will return the the tileentity class or null
	 * @return Class<? extends TileEntity>
	 */
	Class<? extends TileEntity> getTileEntityClass();
	
	/**
	 * This will return the creative tabs for the block
	 * @return
	 */
	CreativeTabs getCreativeTabs();
	
	/**
	 * This checks to see if a oreDictionary entry was entered
	 * @return
	 */
	boolean hasOredictionaryName();
	
	/**
	 * Checks to see if the blocks has a tile entity.
	 * @return
	 */
	boolean hasTileEnttiy();
}
