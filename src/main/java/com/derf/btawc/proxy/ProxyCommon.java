package com.derf.btawc.proxy;

import com.derf.btawc.Loader;
import com.derf.btawc.blocks.BlockManager;
import com.derf.btawc.client.gui.GuiHandler;
import com.derf.btawc.creativetabs.CreativeTabsManager;
import com.derf.btawc.handler.HandlerManager;
import com.derf.btawc.items.ItemsManager;
import com.derf.btawc.items.tools.ItemAxeOfGreed;
import com.derf.btawc.items.tools.ItemPickaxeOfGreed;
import com.derf.btawc.util.MobDropUtils;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

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
		NetworkRegistry.INSTANCE.registerGuiHandler(Loader.INSTANCE, new GuiHandler());
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		ItemPickaxeOfGreed.registerOres();
		ItemAxeOfGreed.registerLogs();
		MobDropUtils.registerMobDrops();
	}

}
