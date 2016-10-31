package com.derf.btawc;

import com.derf.btawc.proxy.IProxy;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid=Loader.MODID, name=Loader.NAME, version=Loader.VERSION)
public class Loader {
	// Some meta data for the mod
	public final static String MODID = "btawc";
	public final static String NAME = "BTAWC";
	public final static String VERSION = "2.0.2 alpha";
	// Instance for the mod
	@Instance
	public static Loader INSTANCE = new Loader();
	// Proxy
	@SidedProxy(clientSide="com.derf.btawc.proxy.ProxyClient", serverSide="com.derf.btawc.proxy.ProxyServer")
	public static IProxy proxy;
	
	static {
		FluidRegistry.enableUniversalBucket();
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		BTAWCLogger.init();
		proxy.preInit(e);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
		
		// Small little test for something...
	}
}
