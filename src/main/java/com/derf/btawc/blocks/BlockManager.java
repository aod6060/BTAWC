package com.derf.btawc.blocks;

import com.derf.btawc.ModRegistry;
import com.derf.btawc.blocks.chipmaker.BlockChipMaker;
import com.derf.btawc.blocks.cobblestonegenerator.BlockCobblestoneGenerator;
import com.derf.btawc.blocks.furnace.BlockAlloyFurnace;
import com.derf.btawc.blocks.furnace.BlockSuperFurnace;
import com.derf.btawc.blocks.generators.BlockCreativeGenerator;
import com.derf.btawc.blocks.generators.BlockSolidFuelGenerator;
import com.derf.btawc.blocks.itembuffer.BlockItemBuffer;
import com.derf.btawc.blocks.lootpresents.BlockLootPresent;
import com.derf.btawc.blocks.tileentity.chipmaker.TileEntityChipMaker;
import com.derf.btawc.blocks.tileentity.cobblestonegenerator.TileEntityCobblestoneGenerator;
import com.derf.btawc.blocks.tileentity.furnace.TileEntityAlloyFurnace;
import com.derf.btawc.blocks.tileentity.furnace.TileEntitySuperFurnace;
import com.derf.btawc.blocks.tileentity.generators.TileEntityCreativeGenerator;
import com.derf.btawc.blocks.tileentity.generators.TileEntitySolidFuelGenerator;
import com.derf.btawc.blocks.tileentity.itembuffer.TileEntityItemBuffer;
import com.derf.btawc.blocks.witherproof.BlockWitherProof;
import com.derf.btawc.blocks.witherproof.BlockWitherProofGlass;
import com.derf.btawc.creativetabs.CreativeTabsManager;
import com.derf.btawc.items.ItemsManager;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public final class BlockManager {
	
	// Generators
	public static Block creativeGenerator; // Simple Creative Generator...
	// solid fuel generator
	public static Block solidFuelGenerator;
	public static Block solidFuelGeneratorOn;
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
	// Misc
	
	public static final void create() {
		// Generator
		creativeGenerator = new BlockCreativeGenerator();
		// Solid Fuel Generator
		solidFuelGenerator = new BlockSolidFuelGenerator(0.0f, false);
		solidFuelGeneratorOn = new BlockSolidFuelGenerator(1.0f, true);
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
	}
	
	public static final void register() {
		// Generators
		ModRegistry.registerBlock(creativeGenerator, "creative_generator");
		// Solid Fuel Generator
		ModRegistry.registerBlock(solidFuelGenerator, "solid_fuel_generator");
		ModRegistry.registerBlock(solidFuelGeneratorOn, "solid_fuel_generator_on");
		//GameRegistry.registerBlock(energyStorageBasic, "energy_storage_basic");
		// Super Furnace
		ModRegistry.registerBlock(superFurnace, "super_furnace");
		ModRegistry.registerBlock(superFurnaceOn, "super_furnace_on");
		// Wither Proof Block
		ModRegistry.registerBlock(witherProofBlock, "wither_proof");
		ModRegistry.registerBlock(witherProofLight, "wither_proof_light");
		ModRegistry.registerBlock(witherProofGlass, "wither_proof_glass");
		// Alloy Furnace
		ModRegistry.registerBlock(alloyFurnace, "alloy_furnace");
		ModRegistry.registerBlock(alloyFurnaceOn, "alloy_furnace_on");
		// Chip Maker
		ModRegistry.registerBlock(chipMaker, "chip_maker");
		ModRegistry.registerBlock(chipMakerOn, "chip_maker_on");
		// Item Buffer
		ModRegistry.registerBlock(itemBuffer, "item_buffer");
		// Simple Dungeon present
		ModRegistry.registerBlock(simpleDungeonPresent, "simple_dungeon_present");
		// Cobblestone Generator
		ModRegistry.registerBlock(cobblestoneGenerator, "cobblestone_generator");
	}
	
	public static final void registerTileEntities() {
		// Generators
		ModRegistry.registerTileEntity(TileEntityCreativeGenerator.class, "creative_generator");
		// Solid Fuel Generator
		ModRegistry.registerTileEntity(TileEntitySolidFuelGenerator.class, "solid_fuel_generator");
		// Storage
		//GameRegistry.registerTileEntity(TileEntityEnergyStorageBasic.class, "energy_storage_basic");
		// Super Furnace
		ModRegistry.registerTileEntity(TileEntitySuperFurnace.class, "super_furnace");
		// Alloy Furnace
		ModRegistry.registerTileEntity(TileEntityAlloyFurnace.class, "alloy_furnace");
		// Chip Maker
		ModRegistry.registerTileEntity(TileEntityChipMaker.class, "chip_maker");
		// Item Buffer
		ModRegistry.registerTileEntity(TileEntityItemBuffer.class, "item_buffer");
		// Cobblestone Generator
		ModRegistry.registerTileEntity(TileEntityCobblestoneGenerator.class, "cobblestone_generator");
	}
	
	public static final void creativeTabs() {
		// Generators
		creativeGenerator.setCreativeTab(CreativeTabsManager.tabBTAWC);
		// Solid Fuel Generator
		solidFuelGenerator.setCreativeTab(CreativeTabsManager.tabBTAWC);
		//energyStorageBasic.setCreativeTab(CreativeTabsManager.tabBTAWC);
		// Super Furnace
		superFurnace.setCreativeTab(CreativeTabsManager.tabBTAWC);
		// Wither Proof Block
		witherProofBlock.setCreativeTab(CreativeTabsManager.tabBTAWC);
		witherProofLight.setCreativeTab(CreativeTabsManager.tabBTAWC);
		witherProofGlass.setCreativeTab(CreativeTabsManager.tabBTAWC);
		// Alloy Furnace
		alloyFurnace.setCreativeTab(CreativeTabsManager.tabBTAWC);
		// Chip Maker
		chipMaker.setCreativeTab(CreativeTabsManager.tabBTAWC);
		// Item Buffer
		itemBuffer.setCreativeTab(CreativeTabsManager.tabBTAWC);
		// Simple Dungeon Present
		simpleDungeonPresent.setCreativeTab(CreativeTabsManager.tabBTAWC);
		// Cobblestone Generator
		cobblestoneGenerator.setCreativeTab(CreativeTabsManager.tabBTAWC);
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
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(solidFuelGenerator), 
				"sss",
				"sfs",
				"sgs",
				's', ItemsManager.steelIngot,
				'f', Blocks.FURNACE,
				'g', ItemsManager.goldChip);
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
		
	}

	public static void addToOreDictionary() {
	}

	@SideOnly(Side.CLIENT)
	public static void registerVarients() {
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerRenderer() {
		// Generators
		// Creative Generator
		ModRegistry.registerRender(creativeGenerator, "creative_generator");
		// Solid Fuel Generator
		ModRegistry.registerRender(solidFuelGenerator, "solid_fuel_generator");
		ModRegistry.registerRender(solidFuelGeneratorOn, "solid_fuel_generator_on");
		// Wither Proof
		ModRegistry.registerRender(witherProofBlock, "wither_proof");
		ModRegistry.registerRender(witherProofGlass, "wither_proof_glass");
		ModRegistry.registerRender(witherProofLight, "wither_proof_light");
		// Basic Machines (don't use rf but fuel)
		// Super Furnace
		ModRegistry.registerRender(superFurnace, "super_furnace");
		ModRegistry.registerRender(superFurnaceOn, "super_furnace_on");
		// Alloy Furnace
		ModRegistry.registerRender(alloyFurnace, "alloy_furnace");
		ModRegistry.registerRender(alloyFurnaceOn, "alloy_furnace_on");
		// Chip Maker
		ModRegistry.registerRender(chipMaker, "chip_maker");
		ModRegistry.registerRender(chipMakerOn, "chip_maker_on");
		// Item Buffer
		ModRegistry.registerRender(itemBuffer, "item_buffer");
		// Simple Dungeon Present
		ModRegistry.registerRender(simpleDungeonPresent, "simple_dungeon_present");
		// Cobblestone Generator
		ModRegistry.registerRender(cobblestoneGenerator, "cobblestone_generator");
	}


}
