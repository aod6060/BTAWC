package com.derf.btawc.tileentity;

import java.util.ArrayList;
import java.util.List;

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

}
