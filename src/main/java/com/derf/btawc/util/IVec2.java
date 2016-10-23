package com.derf.btawc.util;

public class IVec2 {
	private int x;
	private int y;
	
	public IVec2() {
		x = 0; y = 0;
	}
	
	public IVec2(int x, int y) {
		this.x = x;
		this.y = y;
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
	
	
}
