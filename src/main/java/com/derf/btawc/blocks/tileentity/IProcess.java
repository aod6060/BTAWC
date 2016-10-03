package com.derf.btawc.blocks.tileentity;

/**
 * This is a wrapper for a TileEntity process. It hides the implementation using 
 * a simple public interface. I'll be using the to decouple the process from the 
 * fuel usage. To see how this is used check the TileEntityProcessUtils class.
 * @author Fred
 *
 */
public interface IProcess {
	
	/**
	 * Checks to see if the recipe can be processed by tile entity
	 */
	boolean canProcess();
	
	/**
	 * Processes the the recipe for the tile entity
	 */
	void process();

	/**
	 * Check if inputs are null
	 * @return
	 */
	boolean isInputNull();

	/**
	 * Sets the current item burn time
	 * @param itemBurnTime
	 */
	void setCurrentItemBurnTime(int itemBurnTime);

	/**
	 * Increments the cooking time for the process
	 */
	void incrementCookingTime();

	/**
	 * Returns the Cooking Time for the process
	 * @return
	 */
	int getCookingTime();

	/**
	 * Resets the cooking time to zero
	 */
	void resetCookingTime();

	/**
	 * Gets the max cooking time for the process
	 * @return
	 */
	int getMaxCookingTime();

	/**
	 * Updates the block state for a block
	 */
	void updateBlockState();
}
