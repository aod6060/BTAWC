package com.derf.btawc.tileentity;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.EnumFacing;

public class SixSidedContainer implements ISixSidedContainer {

	private List<ISixSided> list = new ArrayList<ISixSided>();
	
	@Override
	public <T extends ISixSided> void add(T sided) {
		list.add(sided);
	}

	@Override
	public <T extends ISixSided> T get(int index) {
		return (T) list.get(index);
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public <T extends ISixSided> List<T> toList() {
		return (List<T>) list;
	}

	@Override
	public void setType(EnumFacing side, EnumSixSided type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EnumSixSided getType(EnumFacing side) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isTypeOff(EnumFacing side) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTypePull(EnumFacing side) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTypePush(EnumFacing side) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTypeDisabled(EnumFacing side) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<EnumSixSided> getAllTypes() {
		// TODO Auto-generated method stub
		return null;
	}

}
