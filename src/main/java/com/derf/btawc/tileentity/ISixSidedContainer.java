package com.derf.btawc.tileentity;

import java.util.List;

public interface ISixSidedContainer extends ISixSided {
	<T extends ISixSided> void add(T sided);
	
	<T extends ISixSided> T get(int index);
	
	int size();
	
	<T extends ISixSided> List<T> toList();
}
