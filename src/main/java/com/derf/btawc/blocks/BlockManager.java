package com.derf.btawc.blocks;

import com.derf.btawc.ModRegistry;
import com.derf.btawc.blocks.chipmaker.BlockChipMaker;
import com.derf.btawc.blocks.cobblestonegenerator.BlockCobblestoneGenerator;
import com.derf.btawc.blocks.furnace.BlockAlloyFurnace;
import com.derf.btawc.blocks.furnace.BlockSuperFurnace;
import com.derf.btawc.blocks.generators.BlockCreativeGenerator;
import com.derf.btawc.blocks.generators.BlockLunarPanel;
import com.derf.btawc.blocks.generators.BlockSolarPanel;
import com.derf.btawc.blocks.generators.BlockSolidFuelGenerator;
import com.derf.btawc.blocks.itembuffer.BlockItemBuffer;
import com.derf.btawc.blocks.lootpresents.BlockLootPresent;
import com.derf.btawc.blocks.tank.BlockTank;
import com.derf.btawc.blocks.witherproof.BlockWitherProof;
import com.derf.btawc.blocks.witherproof.BlockWitherProofGlass;
import com.derf.btawc.creativetabs.CreativeTabsManager;
import com.derf.btawc.items.ItemsManager;
import com.derf.btawc.lifecycle.LifeCycleManager;
import com.derf.btawc.tileentity.chipmaker.TileEntityChipMaker;
import com.derf.btawc.tileentity.cobblestonegenerator.TileEntityCobblestoneGenerator;
import com.derf.btawc.tileentity.furnace.TileEntityAlloyFurnace;
import com.derf.btawc.tileentity.furnace.TileEntitySuperFurnace;
import com.derf.btawc.tileentity.generators.TileEntityCreativeGenerator;
import com.derf.btawc.tileentity.generators.TileEntityLunarPanel;
import com.derf.btawc.tileentity.generators.TileEntitySolarPanel;
import com.derf.btawc.tileentity.generators.TileEntitySolidFuelGenerator;
import com.derf.btawc.tileentity.itembuffer.TileEntityItemBuffer;
import com.derf.btawc.tileentity.tank.TileEntityTank;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.storage.loot.LootTableList;

public final class BlockManager {
	
	// Generators
	public static Block creativeGenerator; // Simple Creative Generator...
	// solid fuel generator
	public static Block solidFuelGenerator;
	public static Block solidFuelGeneratorOn;
	// solar panel
	public static Block solarPanel;
	// lunar panel
	public static Block lunarPanel;
	// Storage
	public static Block energyStorageBasic; // Basic Energy Storage cap 10000 transfer rate 100
	// Wireless Energy Access
	// Super Furnace
	public static Block superFurnace; // 9x9 furnace...
	public static Block superFurnaceOn;
	// Wither Proof Block
	public static Block witherProofBlock; // Prefents a wither from excaping...
	public static Block witherProofLight; // This is a light version of the wither proof blocks
	public static Block witherProofGlass; // This is a wither proof version of glass
	// Alloy Furnace
	public static Block alloyFurnace;
	public static Block alloyFurnaceOn;
	// Chip Maker, This is used to create the chipMaker
	public static Block chipMaker;
	public static Block chipMakerOn;
	// Item Buffer (Simple item transport to test how item transport works). 
	public static Block itemBuffer;
	// Loot Presents
	public static Block simpleDungeonPresent;
	// Cobblestone Generator (Creates cobble stone passively)
	public static Block cobblestoneGenerator;
	// Tank
	public static Block tank;
	// Misc
	
	// Looped Registry :)
	
	public static final void create() {
		// Generator
		creativeGenerator = new BlockCreativeGenerator();
		// Solid Fuel Generator
		solidFuelGenerator = new BlockSolidFuelGenerator(0.0f, false);
		solidFuelGeneratorOn = new BlockSolidFuelGenerator(1.0f, true);
		// Solar Panel
		solarPanel = new BlockSolarPanel();
		// Lunar Panel
		lunarPanel = new BlockLunarPanel();
		//energyStorageBasic = new BlockEnergyStorageBasic();
		// Super Furnace
		superFurnace = new BlockSuperFurnace(0, false);
		superFurnaceOn = new BlockSuperFurnace(1, true);
		// Wither Proof Block
		witherProofBlock = new BlockWitherProof("wither_proof", 0.0f, SoundType.STONE);
		witherProofLight = new BlockWitherProof("wither_proof_light", 1.0f, SoundType.GLASS);
		witherProofGlass = new BlockWitherProofGlass("wither_proof_glass", 0.0f, SoundType.GLASS);
		// Alloy Furnace
		alloyFurnace = new BlockAlloyFurnace(0, false);
		alloyFurnaceOn = new BlockAlloyFurnace(1, true);
		// Chip Maker
		chipMaker = new BlockChipMaker(0, false);
		chipMakerOn = new BlockChipMaker(1, false);
		// Item Buffer
		itemBuffer = new BlockItemBuffer();
		// Simple Dungeon Present
		simpleDungeonPresent = new BlockLootPresent("simple_dungeon_present", LootTableList.CHESTS_SIMPLE_DUNGEON);
		// Cobblestone Generator
		cobblestoneGenerator = new BlockCobblestoneGenerator();
		// Tank
		tank = new BlockTank();
	}
	
	public static void lifeCycle() {
		// Creative Generator
		LifeCycleManager.addBlockLifeCycle("creative_generator", creativeGenerator, CreativeTabsManager.tabBTAWC, TileEntityCreativeGenerator.class, null);
		// Solid Fuel Generator
		LifeCycleManager.addBlockLifeCycle("solid_fuel_generator", solidFuelGenerator, CreativeTabsManager.tabBTAWC, TileEntitySolidFuelGenerator.class, null);
		LifeCycleManager.addBlockLifeCycle("solid_fuel_generator_on", solidFuelGeneratorOn, null, null, null);
		// Solar Panel
		LifeCycleManager.addBlockLifeCycle("solar_panel", solarPanel, CreativeTabsManager.tabBTAWC, TileEntitySolarPanel.class, null);
		// Lunar Panel
		LifeCycleManager.addBlockLifeCycle("lunar_panel", lunarPanel, CreativeTabsManager.tabBTAWC, TileEntityLunarPanel.class, null);
		// Super Furnace
		LifeCycleManager.addBlockLifeCycle("super_furnace", superFurnace, CreativeTabsManager.tabBTAWC, TileEntitySuperFurnace.class, null);
		LifeCycleManager.addBlockLifeCycle("super_furnace_on", superFurnaceOn, null, null, null);
		// Wither Proof Blocks
		LifeCycleManager.addBlockLifeCycle("wither_proof", witherProofBlock, CreativeTabsManager.tabBTAWC, null, null);
		LifeCycleManager.addBlockLifeCycle("wither_proof_light", witherProofLight, CreativeTabsManager.tabBTAWC, null, null);
		LifeCycleManager.addBlockLifeCycle("wither_proof_glass", witherProofGlass, CreativeTabsManager.tabBTAWC, null, null);
		// Alloy Furnace
		LifeCycleManager.addBlockLifeCycle("alloy_furnace", alloyFurnace, CreativeTabsManager.tabBTAWC, TileEntityAlloyFurnace.class, null);
		LifeCycleManager.addBlockLifeCycle("alloy_furnace_on", alloyFurnaceOn, null, null, null);
		// Chip Maker
		LifeCycleManager.addBlockLifeCycle("chip_maker", chipMaker, CreativeTabsManager.tabBTAWC, TileEntityChipMaker.class, null);
		LifeCycleManager.addBlockLifeCycle("chip_maker_on", chipMakerOn, null, null, null);
		// Item Buffer
		LifeCycleManager.addBlockLifeCycle("item_buffer", itemBuffer, CreativeTabsManager.tabBTAWC, TileEntityItemBuffer.class, null);
		// Simple Dungeon Present
		LifeCycleManager.addBlockLifeCycle("simple_dungeon_present", simpleDungeonPresent, CreativeTabsManager.tabBTAWC, null, null);
		// Cobblestone Generator
		LifeCycleManager.addBlockLifeCycle("cobblestone_generator", cobblestoneGenerator, CreativeTabsManager.tabBTAWC, TileEntityCobblestoneGenerator.class, null);
		// Tank
		LifeCycleManager.addBlockLifeCycle("tank", tank, CreativeTabsManager.tabBTAWC, TileEntityTank.class, null);
	}
	
	public static final void crafting() {
		// Basic Blocks
		// Wither Proof Block
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(witherProofBlock, 4), 
				"oio",
				"ioi",
				"oio",
				'i', ItemsManager.mobIngot,
				'o', Blocks.OBSIDIAN);
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(witherProofLight, 8), 
				"lll",
				"lpl",
				"lll",
				'l', witherProofBlock,
				'p', Blocks.GLOWSTONE);
		
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(witherProofGlass, 8), 
				"ggg",
				"gpg",
				"ggg",
				'g', witherProofBlock,
				'p', Blocks.GLASS);
		// Basic Machines (doesn't use RF)
		// Super Furnace
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(superFurnace), 
				"fff",
				"fff",
				"fff",
				'f', Blocks.FURNACE);
		// Alloy Furnace
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(alloyFurnace), 
				" f ",
				"fif",
				" f ",
				'f', Blocks.FURNACE,
				'i', Blocks.IRON_BLOCK);
		// Chip Maker
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(chipMaker), 
				" g ",
				"sfs",
				" r ",
				'g', ItemsManager.gironIngot,
				's', ItemsManager.steelIngot,
				'f', Blocks.FURNACE,
				'r', Blocks.REDSTONE_BLOCK);
		
		// Generators (Produces RF) generators use gold chip for recipe
		// Solid Fuel Generator
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(solidFuelGenerator), 
				"sss",
				"sfs",
				"sgs",
				's', ItemsManager.steelIngot,
				'f', Blocks.FURNACE,
				'g', ItemsManager.goldChip);
		// Solar Panel
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(solarPanel), 
				"sgs",
				"sis",
				"scs",
				's', ItemsManager.steelIngot,
				'g', ItemsManager.graphite,
				'i', Items.IRON_INGOT,
				'c', ItemsManager.goldChip);
		// Lunar Panel
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(lunarPanel), 
				"sgs",
				"sis",
				"scs",
				's', ItemsManager.steelIngot,
				'g', ItemsManager.graphite,
				'i', Items.GOLD_INGOT,
				'c', ItemsManager.goldChip);
		// Item Buffer
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(itemBuffer), 
				"sts",
				"tct",
				"sts",
				's', new ItemStack(Blocks.STONE, 1, 0),
				't', ItemsManager.steelIngot,
				'c', Blocks.CHEST);
		// Simple Dungeon Present
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(simpleDungeonPresent), 
				"wdw",
				"mcm",
				"wsw",
				'w', Blocks.WOOL,
				'd', Blocks.DIAMOND_BLOCK,
				'm', ItemsManager.mobIngot,
				'c', Blocks.CHEST,
				's', Blocks.COBBLESTONE);
		// Cobblestone Generator
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(cobblestoneGenerator), 
				"nsn",
				"lcw",
				"nsn",
				'c', Blocks.COBBLESTONE,
				'l', Items.LAVA_BUCKET,
				'w', Items.WATER_BUCKET,
				'n', new ItemStack(Blocks.STONE, 1, 0),
				's', ItemsManager.steelIngot);
		// Tank
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(tank), 
				"sss",
				"ggg",
				"sss",
				's', ItemsManager.steelIngot,
				'g', Blocks.GLASS);
	}
}
