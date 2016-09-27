package com.derf.btawc.items.magnet;

import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;

public class ItemMagnetMobs extends ItemMagnet {

	public ItemMagnetMobs() {
		super("magnet_mobs", 32, 4, 0.5);
		this.addEntity(EntityMob.class);
		this.addEntity(EntitySlime.class);
		this.addEntity(EntityGhast.class);
	}

}
