package com.derf.btawc.handler;

import net.minecraftforge.common.MinecraftForge;

public final class HandlerManager {
	public static void create() {
		MinecraftForge.EVENT_BUS.register(new PlayerHandler());
	}
}
