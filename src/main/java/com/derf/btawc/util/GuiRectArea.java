package com.derf.btawc.util;

public class GuiRectArea {
	int left;
	int right;
	int top;
	int bottom;
	
	public GuiRectArea(int left, int top, int right, int bottom) {
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getBottom() {
		return bottom;
	}

	public void setBottom(int bottom) {
		this.bottom = bottom;
	}
	
	public boolean collide(Vec2 m) {
		return getLeft() < m.getX() &&
			   getRight() > m.getX() &&
			   getTop() < m.getY() &&
			   getBottom() > m.getY();
	}
}
