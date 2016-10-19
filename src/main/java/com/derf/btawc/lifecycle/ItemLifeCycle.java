package com.derf.btawc.lifecycle;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemLifeCycle implements IItemLifeCycle {

	private String registerName;
	private Item item;
	private CreativeTabs tabs;
	private String oreDictionaryName = null;
	
	// Default Constructor
	public ItemLifeCycle() {}
	
	public ItemLifeCycle(
			String registerName,
			Item item,
			CreativeTabs tabs,
			String oreDictionaryName) {
		this.registerName = registerName;
		this.item = item;
		this.tabs = tabs;
		this.oreDictionaryName = oreDictionaryName;
	}
	@Override
	public String getRegisteryName() {
		// TODO Auto-generated method stub
		return this.registerName;
	}

	@Override
	public Item getItem() {
		// TODO Auto-generated method stub
		return this.item;
	}

	@Override
	public String getOreDictionaryName() {
		// TODO Auto-generated method stub
		return this.oreDictionaryName;
	}

	@Override
	public CreativeTabs getCreativeTabs() {
		// TODO Auto-generated method stub
		return this.tabs;
	}

	public boolean hasOreDictionaryName() {
		return this.oreDictionaryName != null && !this.oreDictionaryName.isEmpty();
	}
}
