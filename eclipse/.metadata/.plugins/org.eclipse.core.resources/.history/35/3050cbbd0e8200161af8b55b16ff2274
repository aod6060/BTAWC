package com.derf.btawc.proxy;

import com.derf.btawc.blocks.BlockManager;
import com.derf.btawc.creativetabs.CreativeTabsManager;
import com.derf.btawc.handler.HandlerManager;
import com.derf.btawc.items.ItemsManager;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ProxyCommon implements IProxy {

	@Override
	public void preInit(FMLPreInitializationEvent e) {
		ItemsManager.create();
		BlockManager.create();
		ItemsManager.register();
		BlockManager.register();
		BlockManager.registerTileEntities();
		CreativeTabsManager.create();
		ItemsManager.creativeTabs();
		BlockManager.creativeTabs();
	}

	@Override
	public void init(FMLInitializationEvent e) {
		ItemsManager.crafting();
		BlockManager.crafting();
		HandlerManager.create();
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
	}

}
