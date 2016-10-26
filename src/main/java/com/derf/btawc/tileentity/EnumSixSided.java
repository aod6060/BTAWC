package com.derf.btawc.tileentity;

import net.minecraft.util.IStringSerializable;

public enum EnumSixSided implements IStringSerializable{
	OFF("off"),
	PUSH("push"),
	PULL("pull"),
	DISABLED("disabled");
	
	private String name;
	
	private EnumSixSided(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
}
