package com.derf.btawc.items.magnet;

import net.minecraft.entity.boss.EntityWither;

public class ItemMagnetWither extends ItemMagnet {

	public ItemMagnetWither() {
		super("magnet_wither", 64.0f, 4.0f, 0.9f);
		this.addEntity(EntityWither.class);
	}

}
