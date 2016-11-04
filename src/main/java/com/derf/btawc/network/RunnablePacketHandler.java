package com.derf.btawc.network;

import com.derf.btawc.network.data.IPacketData;
import com.derf.btawc.network.data.IPacketDataServer;

public class RunnablePacketHandler implements Runnable {

	private IPacketData packetData = null;
	private int dimension = -1;
	
	// Default Constructor
	public RunnablePacketHandler() {}

	public RunnablePacketHandler(IPacketData packetData) {
		this.packetData = packetData;
	}
	
	public RunnablePacketHandler(IPacketData packetData, int dimension) {
		this.packetData = packetData;
		this.dimension = dimension;
	}
	@Override
	public void run() {
		if(this.packetData != null) {
			
			if(this.packetData instanceof IPacketDataServer) {
				IPacketDataServer packetDataServer = (IPacketDataServer)this.packetData;
				packetDataServer.updatePacketData(this.dimension);
			} else {
				this.packetData.updatePacketData();
			}
		}
	}

}
