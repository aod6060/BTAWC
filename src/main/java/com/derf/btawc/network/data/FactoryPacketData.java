package com.derf.btawc.network.data;

import java.util.HashMap;
import java.util.Map;

import com.derf.btawc.network.data.client.PacketDataEnergyStorageUpdate;
import com.derf.btawc.network.data.client.PacketDataFluidUpdate;
import com.derf.btawc.network.data.server.PacketDataCreativeGeneratorConfiguration;
import com.derf.btawc.network.data.server.PacketDataSixSidedConfiguration;

public final class FactoryPacketData {
	private static Map<String, Class<? extends IPacketData>> packetDatas = new HashMap<String, Class<? extends IPacketData>>();
	
	/**
	 * Internal don't use 
	 * @param name
	 * @return
	 */
	public static IPacketData createPacketData(String name) {
		IPacketData data = null;
		
		try {
			data = packetDatas.get(name).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	public static IPacketData createPacketData(String name, IPacketDataCallback cb) {
		IPacketData data = null;
		
		try {
			data = packetDatas.get(name).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		if(cb != null) {
			cb.call(data);
		}
		
		return data;
	}
	public static void create() {
		// Register PacketDataFluidUpdate
		registerPacketData("passible", PacketDataPassible.class);
		registerPacketData("fluid_update", PacketDataFluidUpdate.class);
		registerPacketData("six_sided_configuration", PacketDataSixSidedConfiguration.class);
		registerPacketData("creative_generator_configuration", PacketDataCreativeGeneratorConfiguration.class);
		registerPacketData("energy_storage_update", PacketDataEnergyStorageUpdate.class);
	}
	
	
	public static void registerPacketData(String name, Class<? extends IPacketData> clz) {
		packetDatas.put(name, clz);
	}
}
