package com.derf.btawc.util;

public class BlockPos {
	private int x;
	private int y;
	private int z;
	
	public BlockPos() {
		x = 0; 
		y = 0; 
		z = 0;
	}
	
	public BlockPos(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	@Override
	public String toString() {
		return "BlockPos [x=" + x + ", y=" + y + ", z=" + z + "]";
	}
	
	public static BlockPos add(BlockPos a, BlockPos b) {
		return new BlockPos(a.getX() + b.getX(), a.getY() + b.getY(), a.getZ() + b.getZ());
	}
}
