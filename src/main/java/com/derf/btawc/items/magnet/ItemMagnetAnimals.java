package com.derf.btawc.items.magnet;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;

public class ItemMagnetAnimals extends ItemMagnet {

	public ItemMagnetAnimals() {
		super("magnet_animals", 16.0, 0.2);
		this.addEntity(EntityAnimal.class);
	}

	@Override
	public void onMagnetEntityUpdate(Entity item) {
		// TODO Auto-generated method stub
		super.onMagnetEntityUpdate(item);
		
		item.fallDistance = 0;
	}
	
	
}
