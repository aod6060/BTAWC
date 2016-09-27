package com.derf.btawc.items.magnet;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;

public class ItemMagnetItems extends ItemMagnet {
	public ItemMagnetItems() {
		super("magnet_items", 16.0, 0.5, 0.2);
		this.addEntity(EntityItem.class);
		this.addEntity(EntityXPOrb.class);
	}
}
