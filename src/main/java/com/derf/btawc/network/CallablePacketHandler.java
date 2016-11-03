package com.derf.btawc.network;

import java.util.concurrent.Callable;

import com.derf.btawc.network.data.IPacketData;

public class CallablePacketHandler implements Callable<IPacketData> {

	private IPacketData packetData = null;
	
	public CallablePacketHandler() {}
	
	public CallablePacketHandler(IPacketData packetData) {
		this.packetData = packetData;
	}
	
	@Override
	public IPacketData call() throws Exception {
		
		if(this.packetData != null) {
			this.packetData.updatePacketData();
		} else {
			throw new Exception("BTAWC: The packet data object was null!");
		}
		return this.packetData;
	}

}
