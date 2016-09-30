package com.derf.btawc.items.misc;

import com.derf.btawc.Loader;

import net.minecraft.item.ItemFood;

public abstract class ItemAnimalIngot extends ItemFood {

	public ItemAnimalIngot(String name, int heal, float saturation, boolean wolfLikes) {
		super(heal, saturation, wolfLikes);
		this.setUnlocalizedName(name);
	}
}
