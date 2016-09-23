package com.derf.btawc.blocks;

import com.derf.btawc.blocks.energystorage.BlockEnergyStorageBasic;
import com.derf.btawc.blocks.generators.BlockCreativeGenerator;
import com.derf.btawc.blocks.tileentity.energystorage.TileEntityEnergyStorageBasic;
import com.derf.btawc.blocks.tileentity.generators.TileEntityCreativeGenerator;
import com.derf.btawc.creativetabs.CreativeTabsManager;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public final class BlockManager {
	
	// Generators
	public static Block creativeGenerator; // Simple Creative Generator...
	// Storage
	public static Block energyStorageBasic; // Basic Energy Storage cap 10000 transfer rate 100
	// Wireless Energy Access
	// Misc
	
	public static final void create() {
		creativeGenerator = new BlockCreativeGenerator();
		energyStorageBasic = new BlockEnergyStorageBasic();
	}
	
	public static final void register() {
		GameRegistry.registerBlock(creativeGenerator, "creative_generator");
		GameRegistry.registerBlock(energyStorageBasic, "energy_storage_basic");
	}
	
	public static final void registerTileEntities() {
		// Generators
		GameRegistry.registerTileEntity(TileEntityCreativeGenerator.class, "creative_generator");
		// Storage
		GameRegistry.registerTileEntity(TileEntityEnergyStorageBasic.class, "energy_storage_basic");
	}
	
	public static final void creativeTabs() {
		creativeGenerator.setCreativeTab(CreativeTabsManager.tabBTAWC);
		energyStorageBasic.setCreativeTab(CreativeTabsManager.tabBTAWC);
	}
	
	public static final void crafting() {
		
	}
}
