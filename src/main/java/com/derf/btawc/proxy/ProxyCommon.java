package com.derf.btawc.proxy;

import com.derf.btawc.Loader;
import com.derf.btawc.ModRegistry;
import com.derf.btawc.blocks.BlockManager;
import com.derf.btawc.client.gui.GuiHandler;
import com.derf.btawc.creativetabs.CreativeTabsManager;
import com.derf.btawc.fuel.FuelHandler;
import com.derf.btawc.handler.HandlerManager;
import com.derf.btawc.items.ItemsManager;
import com.derf.btawc.items.growthdevice.ItemGrowthDevice;
import com.derf.btawc.items.tools.ItemAxeOfGreed;
import com.derf.btawc.items.tools.ItemPickaxeOfGreed;
import com.derf.btawc.lifecycle.LifeCycleManager;
import com.derf.btawc.network.PacketHandler;
import com.derf.btawc.sound.SoundManager;
import com.derf.btawc.util.MobDropUtils;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class ProxyCommon implements IProxy {

	@Override
	public void preInit(FMLPreInitializationEvent e) {
		ItemsManager.create();
		BlockManager.create();
		CreativeTabsManager.create();
		ItemsManager.lifeCycle();
		BlockManager.lifeCycle();
		LifeCycleManager.register();
		LifeCycleManager.tileEntities();
		LifeCycleManager.creativeTabs();
		// Register Network Packets
		PacketHandler.registerMessages();
		// Create and Register Sounds
		SoundManager.create();
		ModRegistry.registerFuelHandler(new FuelHandler());
	}

	@Override
	public void init(FMLInitializationEvent e) {
		//LifeCycleManager.crafting();
		BlockManager.crafting(); // Keep these till I implement my new crafting system...
		ItemsManager.crafting();
		
		HandlerManager.create();
		NetworkRegistry.INSTANCE.registerGuiHandler(Loader.INSTANCE, new GuiHandler());
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		LifeCycleManager.oreDictionary();
		ItemGrowthDevice.registerGrowthDeviceStrategies();
		ItemPickaxeOfGreed.registerOres();
		ItemAxeOfGreed.registerLogs();
		MobDropUtils.registerMobDrops();
	}

}
