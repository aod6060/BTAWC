package com.derf.btawc.network.data;

import java.util.HashMap;
import java.util.Map;

import com.derf.btawc.network.data.client.PacketDataFluidUpdate;

public final class FactoryPacketData {
	
	
	private static Map<String, Class<? extends IPacketData>> packetDatas = new HashMap<String, Class<? extends IPacketData>>();
	
	/**
	 * This will initialize the 
	 * @param packetData
	 * @return
	 */
	public static IPacketData createPacketData(String packetData) {
		IPacketData data = null;
		
		try {
			data = packetDatas.get(packetData).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	public static void create() {
		// Register PacketDataFluidUpdate
		registerPacketData("fluid_update", PacketDataFluidUpdate.class);
	}
	
	
	public static void registerPacketData(String name, Class<? extends IPacketData> clz) {
		packetDatas.put(name, clz);
	}
}
