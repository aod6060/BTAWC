package com.derf.btawc.blocks.tileentity;

/**
 * This handles fuel usage. It can be different types of fuel because the 
 * TileEntityProcessUtils doesn't care about the details of the implementation.
 * @author Fred
 *
 */
public interface IFuelUsage {
	/**
	 * Check and sees if the fuels is buring
	 * @return
	 */
	boolean isBurning();

	/**
	 * Decrements Fuel Time
	 */
	void decreaseFuelTime();

	/**
	 * Check to see if burn time is zero
	 * @return
	 */
	boolean isBurnTimeZero();

	/**
	 * Grabs the fuel slot from the inventory
	 * @return
	 */
	int getFuelSlot();

	/**
	 * Sets the burn time for the fuel
	 * @param itemBurnTime
	 */
	void setBurnTime(int itemBurnTime);
}
