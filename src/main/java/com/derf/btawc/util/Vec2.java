package com.derf.btawc.util;

public class Vec2 {
	private float x;
	private float y;
	
	
	public Vec2() {
		this(0, 0);
	}
	
	public Vec2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	
	
}
