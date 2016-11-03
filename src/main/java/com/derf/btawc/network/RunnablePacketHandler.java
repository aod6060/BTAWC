package com.derf.btawc.network;

import com.derf.btawc.network.data.IPacketData;

public class RunnablePacketHandler implements Runnable {

	private IPacketData packetData = null;

	// Default Constructor
	public RunnablePacketHandler() {}

	public RunnablePacketHandler(IPacketData packetData) {
		this.packetData = packetData;
	}
	@Override
	public void run() {
		if(this.packetData != null) {
			this.packetData.updatePacketData();
		}
	}

}
