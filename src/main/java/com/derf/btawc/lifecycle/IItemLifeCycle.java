package com.derf.btawc.lifecycle;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public interface IItemLifeCycle {
	/**
	 * Grabs the Registry Name
	 * @return
	 */
	String getRegisteryName();
	
	/**
	 * Grabs the item that is going to be registered
	 * @return
	 */
	Item getItem();
	
	/**
	 * Grabs the items recipe
	 * @return
	 */
	String getOreDictionaryName();
	
	/**
	 * This will return creative tabs
	 * @return
	 */
	CreativeTabs getCreativeTabs();
	
	/**
	 * This will check to see if the item has an oredictionary entry for it...
	 * @return
	 */
	boolean hasOreDictionaryName();
	
}
