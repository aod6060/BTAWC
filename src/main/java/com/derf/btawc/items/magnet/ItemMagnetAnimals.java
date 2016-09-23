package com.derf.btawc.items.magnet;

import net.minecraft.entity.passive.EntityAnimal;

public class ItemMagnetAnimals extends ItemMagnet {

	public ItemMagnetAnimals() {
		super("magnet_animals", 16.0, 0.3);
		this.addEntity(EntityAnimal.class);
	}

}
