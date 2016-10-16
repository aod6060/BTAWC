package com.derf.btawc.network;

import com.derf.btawc.Loader;
import com.derf.btawc.network.packets.PacketCreativeGeneratorInfo;
import com.derf.btawc.network.packets.PacketItemBuffer;
import com.derf.btawc.network.packets.PacketItemBufferOnClose;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public final class PacketHandler {
	private static int packetId = 0;
	public static SimpleNetworkWrapper INSTANCE = null;
	
	public static int nextID() {
		return ++packetId;
	}
	
	public static void registerMessages() {
		INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Loader.MODID);
		// Register Custom Messages
		// Creative Generator
		INSTANCE.registerMessage(PacketCreativeGeneratorInfo.Handler.class, PacketCreativeGeneratorInfo.class, nextID(), Side.SERVER);
		// Item Buffer
		INSTANCE.registerMessage(PacketItemBuffer.Handler.class, PacketItemBuffer.class, nextID(), Side.SERVER);
		// Item Buffer On Close
		INSTANCE.registerMessage(PacketItemBufferOnClose.Handler.class, PacketItemBufferOnClose.class, nextID(), Side.SERVER);
	}
	
}
