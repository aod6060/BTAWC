package com.derf.btawc.creativetabs;

import com.derf.btawc.items.ItemsManager;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public final class CreativeTabsManager {

	public static CreativeTabs tabBTAWC;
	
	public static void create() {
		tabBTAWC = new CreativeTabs("tabBTAWC") {

			@Override
			public Item getTabIconItem() {
				// TODO Auto-generated method stub
				return Items.REDSTONE;
			}
			
		};
	}
}
