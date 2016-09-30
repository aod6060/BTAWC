package com.derf.btawc.util;

public class Timer {
	private int maxTime;
	private int time;
	
	public Timer(int maxTime) {
		this.maxTime = maxTime;
		this.time = this.maxTime;
	}
	
	public boolean isTime() {
		return this.time <= 0;
	}
	
	public void update() {
		--this.time;
	}
	
	public void reset() {
		this.time = this.maxTime;
	}
}
