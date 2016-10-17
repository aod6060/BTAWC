package com.derf.btawc.sound;

import com.derf.btawc.Loader;
import com.derf.btawc.ModRegistry;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public final class SoundManager {
	public static SoundEvent air_horn;
	
	
	public static void create() {
		air_horn = registerSound("sfx.air_horn");
	}
	
	private static SoundEvent registerSound(String name) {
		ResourceLocation res = new ResourceLocation(Loader.MODID, name);
		SoundEvent e = new SoundEvent(res).setRegistryName(name);
		ModRegistry.register(e);
		return e;
	}
}
