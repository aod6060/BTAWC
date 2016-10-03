package com.derf.btawc.blocks.tileentity;

public interface IField {
	
	/**
	 * This will return a field from a TileEntity
	 * @param index
	 * @return
	 */
	int getField(int index);
	
	/**
	 * This will set a given field for a TileEntity
	 * @param index
	 * @param value
	 */
	void setField(int index, int value);
	
	/**
	 * This will return the number of fields for the TileEntity
	 * @return
	 */
	public int getFieldCount();
	
}
