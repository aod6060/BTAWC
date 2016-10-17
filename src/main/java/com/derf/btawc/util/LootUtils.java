package com.derf.btawc.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableManager;

/**
 * This is used to understand the loot table system.
 * @author Fred
 *
 */
public class LootUtils {
	
	private static Random rand = new Random();
	
	public static List<ItemStack> grabRandomItemStacksFromLootTable(World world, ResourceLocation location) {
		LootTableManager manager = new LootTableManager(new File("minecraft"));
		
		LootTable table = manager.getLootTableFromLocation(location);
		
		LootContext context = new LootContext(1.0f, (WorldServer) world, manager, null, null, null);
		
		return table.generateLootForPools(rand, context);
	}
}
