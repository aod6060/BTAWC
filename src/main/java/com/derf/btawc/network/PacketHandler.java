package com.derf.btawc.network;

import com.derf.btawc.Loader;
import com.derf.btawc.network.packets.PacketCreativeGeneratorInfo;
import com.derf.btawc.network.packets.PacketSixSidedConfiguration;
import com.derf.btawc.network.packets.PacketTankFluidUpdate;

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
		// Six Sided Configuration
		INSTANCE.registerMessage(PacketSixSidedConfiguration.Handler.class, PacketSixSidedConfiguration.class, nextID(), Side.SERVER);
		// Tank Fluid Update
		INSTANCE.registerMessage(PacketTankFluidUpdate.Handler.class, PacketTankFluidUpdate.class, nextID(), Side.CLIENT);
	}
	
}
