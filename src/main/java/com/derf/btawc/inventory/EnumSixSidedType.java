package com.derf.btawc.inventory;

import net.minecraft.util.IStringSerializable;

public enum EnumSixSidedType implements IStringSerializable{
	OFF("off"),
	PUSH("push"),
	PULL("pull"),
	DISABLED("disabled");
	
	private String name;
	
	private EnumSixSidedType(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
}
