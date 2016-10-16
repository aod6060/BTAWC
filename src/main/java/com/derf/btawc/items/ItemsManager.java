package com.derf.btawc.items;

import com.derf.btawc.ModRegistry;
import com.derf.btawc.creativetabs.CreativeTabsManager;
import com.derf.btawc.items.growthdevice.ItemGrowthDevice;
import com.derf.btawc.items.magnet.ItemMagnetAnimals;
import com.derf.btawc.items.magnet.ItemMagnetItems;
import com.derf.btawc.items.magnet.ItemMagnetMobs;
import com.derf.btawc.items.magnet.ItemMagnetWither;
import com.derf.btawc.items.misc.ItemAnimalIngotCooked;
import com.derf.btawc.items.tools.ItemAxeOfGreed;
import com.derf.btawc.items.tools.ItemHoeOfGreed;
import com.derf.btawc.items.tools.ItemPickaxeOfGreed;
import com.derf.btawc.items.tools.ItemSwordOfGreed;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemsManager {
	
	// Growth Device
	public static Item growthDeviceFrame;		// First item you needed for this mod...
	public static Item growthDevice; 			// 3x3 slowest (Faster than a watering can)
	public static Item growthDeviceSuper; 		// 5x5 slow
	public static Item growthDeviceMega; 		// 7x7 fast
	public static Item growthDeviceUltra; 		// 9x9 fastest
	// Magnets
	public static Item magnetFrame;				// Magnet Frame
	public static Item magnetItems;				// This is a simple Item magnet
	public static Item magnetAnimals;			// This is a simple Magnet for farm animals
	public static Item magnetMobs;				// This is the Mob magnet
	public static Item magnetWither;			// This is the Wither magnet...
	// Flowtation Device
	public static Item flowtationDeviceFrame;	// Flowtation Device Frame
	public static Item flowtationDevice;		// Flowtation Device, allows you to have creative flight
	// Hoe of Greed
	public static Item hoeOfGreed;				// tier 1 hoe of greed, 3x3 space, and 2x produce
	public static Item hoeOfGreedSuper;			// tier 2 hoe of greed, 5x5 space, and 4x produce
	public static Item hoeOfGreedMega;			// tier 3 hoe of greed, 7x7 space, and 8x produce
	public static Item hoeOfGreedUltra;			// tier 4 hoe of greed, 9x9 space, and 16x produce
	// Pickaxe of Greed
	public static Item pickaxeOfGreed;			// tier 1, 2x produce on ores
	public static Item pickaxeOfGreedSuper;		// tier 2, 4x produce on ores
	public static Item pickaxeOfGreedMega;		// tier 3, 8x produce on ores
	public static Item pickaxeOfGreedUltra;		// tier 4, 16x produce on ores
	// Axe of Greed
	public static Item axeOfGreed;				// tier 1, 2x produce on ores
	public static Item axeOfGreedSuper;			// tier 2, 4x produce on ores
	public static Item axeOfGreedMega;			// tier 3, 8x produce on ores
	public static Item axeOfGreedUltra;			// tier 4, 16x produce on ores
	// Sword of Greed
	public static Item swordOfGreed;			// 2x mob drops, mob drop after hit 2x
	public static Item swordOfGreedSuper;		// 4x mob drops, mob drop after hit 4x
	public static Item swordOfGreedMega;		// 8x mob drops, mob drop after hit 8x
	public static Item swordOfGreedUltra;		// 16x mob drops, mob drop after hit 16x
	// Pulsator
	public static Item pulsator;				// Makes Creatures fly lol :D
	// Ingots	
	public static Item netherStarIngot;			// This is a new ingot that is made from nether star, diamonds, and iron...
	public static Item animalIngot;				// Very good food source :D	
	public static Item mobIngot;				// Very scary ingot	
	public static Item gironIngot;				// Used for machines
	public static Item steelIngot;				// Used for machines
	// Crystal	
	public static Item crystalOfGreed;			// Very greedy crystal :)
	public static Item crystalOfWrath;			// Very angry crystal :)
	// Non Metals
	public static Item graphite;				// Used for solar and lunar panels
	// Chips - These are used to for make machines
	public static Item ironChip;
	public static Item goldChip;
	public static Item gironChip;
	public static Item pulsatingChip;
	// Machine Upgrades
	public static Item speedUpgradeChip;

	
	public static final void create() {
		
		// Growth Device
		growthDeviceFrame = new ItemBasic("growth_device_frame");
		growthDevice = new ItemGrowthDevice("growth_device", 3, 1);
		growthDeviceSuper = new ItemGrowthDevice("growth_device_super", 5, 1);
		growthDeviceMega = new ItemGrowthDevice("growth_device_mega", 7, 1);
		growthDeviceUltra = new ItemGrowthDevice("growth_device_ultra", 9, 0);
		// Magnet
		magnetFrame = new ItemBasic("magnet_frame");
		magnetItems = new ItemMagnetItems();
		magnetAnimals = new ItemMagnetAnimals();
		magnetMobs = new ItemMagnetMobs();
		magnetWither = new ItemMagnetWither();
		// Flowtation Device
		flowtationDeviceFrame = new ItemBasic("flowtation_device_frame");
		flowtationDevice = new ItemFlowtationDevice();
		// Hoe of Greed
		hoeOfGreed = new ItemHoeOfGreed("hoe_of_greed_basic", 10000, 3, 2);
		hoeOfGreedSuper = new ItemHoeOfGreed("hoe_of_greed_super", 20000, 5, 4);
		hoeOfGreedMega = new ItemHoeOfGreed("hoe_of_greed_mega", 40000, 7, 8);
		hoeOfGreedUltra = new ItemHoeOfGreed("hoe_of_greed_ultra", 80000, 9, 16);
		// Pickaxe of Greed
		pickaxeOfGreed = new ItemPickaxeOfGreed("pickaxe_of_greed_basic", 2, 10000);			
		pickaxeOfGreedSuper = new ItemPickaxeOfGreed("pickaxe_of_greed_super", 4, 20000);		
		pickaxeOfGreedMega = new ItemPickaxeOfGreed("pickaxe_of_greed_mega", 8, 40000);		
		pickaxeOfGreedUltra = new ItemPickaxeOfGreed("pickaxe_of_greed_ultra", 16, 80000);
		// Axe of Greed
		axeOfGreed = new ItemAxeOfGreed("axe_of_greed_basic", 2, 10000);
		axeOfGreedSuper = new ItemAxeOfGreed("axe_of_greed_super", 4, 20000);
		axeOfGreedMega = new ItemAxeOfGreed("axe_of_greed_mega", 8, 40000);
		axeOfGreedUltra = new ItemAxeOfGreed("axe_of_greed_ultra", 16, 80000);
		// Sword of Greed
		swordOfGreed = new ItemSwordOfGreed("sword_of_greed_basic", 10, 2, 10000);
		swordOfGreedSuper = new ItemSwordOfGreed("sword_of_greed_super", 20, 4, 20000);
		swordOfGreedMega = new ItemSwordOfGreed("sword_of_greed_mega", 40, 8, 40000);
		swordOfGreedUltra = new ItemSwordOfGreed("sword_of_greed_ultra", 80, 16, 80000);
		// Pulsator
		pulsator = new ItemPulsator(2, 32.0f);
		// Misc
		netherStarIngot = new ItemBasic("nether_star_ingot");
		animalIngot = new ItemAnimalIngotCooked();
		crystalOfGreed = new ItemBasic("crystal_of_greed");
		mobIngot = new ItemBasic("mob_ingot");
		crystalOfWrath = new ItemBasic("crystal_of_wrath");
		graphite = new ItemBasic("graphite");
		gironIngot = new ItemBasic("giron_ingot");
		steelIngot = new ItemBasic("steel_ingot");
		// Chips
		ironChip = new ItemBasic("iron_chip");
		goldChip = new ItemBasic("gold_chip");
		gironChip = new ItemBasic("giron_chip");
		pulsatingChip = new ItemBasic("pulsating_chip");
		// Upgrade Chips
		speedUpgradeChip = new ItemBasic("speed_upgrade_chip");
		speedUpgradeChip.setMaxStackSize(4);
	}
	
	public static final void register() {
		// Growth Device
		ModRegistry.registerItem(growthDeviceFrame, "growth_device_frame");
		ModRegistry.registerItem(growthDevice, "growth_device");
		ModRegistry.registerItem(growthDeviceSuper, "growth_device_super");
		ModRegistry.registerItem(growthDeviceMega, "growth_device_mega");
		ModRegistry.registerItem(growthDeviceUltra, "growth_device_ultra");
		// Magnet
		ModRegistry.registerItem(magnetFrame, "magnet_frame");
		ModRegistry.registerItem(magnetItems, "magnet_items");
		ModRegistry.registerItem(magnetAnimals, "magnet_animals");
		ModRegistry.registerItem(magnetMobs, "magnet_mobs");
		ModRegistry.registerItem(magnetWither, "magnet_wither");
		// Flowtation Device
		ModRegistry.registerItem(flowtationDeviceFrame, "flowtation_device_frame");
		ModRegistry.registerItem(flowtationDevice, "flowtation_device");
		// Hoe of Greed
		ModRegistry.registerItem(hoeOfGreed, "hoe_of_greed_basic");
		ModRegistry.registerItem(hoeOfGreedSuper, "hoe_of_greed_super");
		ModRegistry.registerItem(hoeOfGreedMega, "hoe_of_greed_mega");
		ModRegistry.registerItem(hoeOfGreedUltra, "hoe_of_greed_ultra");
		// Pickaxe of Greed
		ModRegistry.registerItem(pickaxeOfGreed, "pickaxe_of_greed_basic");
		ModRegistry.registerItem(pickaxeOfGreedSuper, "pickaxe_of_greed_super");
		ModRegistry.registerItem(pickaxeOfGreedMega, "pickaxe_of_greed_mega");
		ModRegistry.registerItem(pickaxeOfGreedUltra, "pickaxe_of_greed_ultra");
		// Axe of Greed
		ModRegistry.registerItem(axeOfGreed, "axe_of_greed_basic");
		ModRegistry.registerItem(axeOfGreedSuper, "axe_of_greed_super");
		ModRegistry.registerItem(axeOfGreedMega, "axe_of_greed_mega");
		ModRegistry.registerItem(axeOfGreedUltra, "axe_of_greed_ultra");
		// Sword of Greed
		ModRegistry.registerItem(swordOfGreed, "sword_of_greed_basic");
		ModRegistry.registerItem(swordOfGreedSuper, "sword_of_greed_super");
		ModRegistry.registerItem(swordOfGreedMega, "sword_of_greed_mega");
		ModRegistry.registerItem(swordOfGreedUltra, "sword_of_greed_ultra");
		// Pulsator
		ModRegistry.registerItem(pulsator, "pulsator");
		// Misc
		ModRegistry.registerItem(netherStarIngot, "nether_star_ingot");
		ModRegistry.registerItem(animalIngot, "animal_ingot");
		ModRegistry.registerItem(crystalOfGreed, "crystal_of_greed");
		ModRegistry.registerItem(mobIngot, "mob_ingot");
		ModRegistry.registerItem(crystalOfWrath, "crystal_of_wrath");
		ModRegistry.registerItem(graphite, "graphite");
		ModRegistry.registerItem(gironIngot, "giron_ingot");
		ModRegistry.registerItem(steelIngot, "steel_ingot");
		// Chips
		ModRegistry.registerItem(ironChip, "iron_chip");
		ModRegistry.registerItem(goldChip, "gold_chip");
		ModRegistry.registerItem(gironChip, "giron_chip");
		ModRegistry.registerItem(pulsatingChip, "pulsating_chip");
		// Upgrade Chips
		ModRegistry.registerItem(speedUpgradeChip, "speed_upgrade_chip");
	}
	
	public static final void creativeTabs() {
		
		// Growth Device
		growthDeviceFrame.setCreativeTab(CreativeTabsManager.tabBTAWC);
		growthDevice.setCreativeTab(CreativeTabsManager.tabBTAWC);
		growthDeviceSuper.setCreativeTab(CreativeTabsManager.tabBTAWC);
		growthDeviceMega.setCreativeTab(CreativeTabsManager.tabBTAWC);
		growthDeviceUltra.setCreativeTab(CreativeTabsManager.tabBTAWC);
		// Magnet
		magnetFrame.setCreativeTab(CreativeTabsManager.tabBTAWC);
		magnetItems.setCreativeTab(CreativeTabsManager.tabBTAWC);
		magnetAnimals.setCreativeTab(CreativeTabsManager.tabBTAWC);
		magnetMobs.setCreativeTab(CreativeTabsManager.tabBTAWC);
		magnetWither.setCreativeTab(CreativeTabsManager.tabBTAWC);
		// Flowtation Device
		flowtationDeviceFrame.setCreativeTab(CreativeTabsManager.tabBTAWC);
		flowtationDevice.setCreativeTab(CreativeTabsManager.tabBTAWC);
		// hoe of greed
		hoeOfGreed.setCreativeTab(CreativeTabsManager.tabBTAWC);
		hoeOfGreedSuper.setCreativeTab(CreativeTabsManager.tabBTAWC);
		hoeOfGreedMega.setCreativeTab(CreativeTabsManager.tabBTAWC);
		hoeOfGreedUltra.setCreativeTab(CreativeTabsManager.tabBTAWC);
		// Pickaxe of Greed
		pickaxeOfGreed.setCreativeTab(CreativeTabsManager.tabBTAWC);
		pickaxeOfGreedSuper.setCreativeTab(CreativeTabsManager.tabBTAWC);
		pickaxeOfGreedMega.setCreativeTab(CreativeTabsManager.tabBTAWC);
		pickaxeOfGreedUltra.setCreativeTab(CreativeTabsManager.tabBTAWC);
		// Axe of Greed
		axeOfGreed.setCreativeTab(CreativeTabsManager.tabBTAWC);
		axeOfGreedSuper.setCreativeTab(CreativeTabsManager.tabBTAWC);
		axeOfGreedMega.setCreativeTab(CreativeTabsManager.tabBTAWC);
		axeOfGreedUltra.setCreativeTab(CreativeTabsManager.tabBTAWC);
		// Sword of Greed
		swordOfGreed.setCreativeTab(CreativeTabsManager.tabBTAWC);
		swordOfGreedSuper.setCreativeTab(CreativeTabsManager.tabBTAWC);
		swordOfGreedMega.setCreativeTab(CreativeTabsManager.tabBTAWC);
		swordOfGreedUltra.setCreativeTab(CreativeTabsManager.tabBTAWC);
		// Pulsator
		pulsator.setCreativeTab(CreativeTabsManager.tabBTAWC);
		// Misc
		netherStarIngot.setCreativeTab(CreativeTabsManager.tabBTAWC);
		animalIngot.setCreativeTab(CreativeTabsManager.tabBTAWC);
		crystalOfGreed.setCreativeTab(CreativeTabsManager.tabBTAWC);
		mobIngot.setCreativeTab(CreativeTabsManager.tabBTAWC);
		crystalOfWrath.setCreativeTab(CreativeTabsManager.tabBTAWC);
		graphite.setCreativeTab(CreativeTabsManager.tabBTAWC);
		gironIngot.setCreativeTab(CreativeTabsManager.tabBTAWC);
		steelIngot.setCreativeTab(CreativeTabsManager.tabBTAWC);
		// Chips
		ironChip.setCreativeTab(CreativeTabsManager.tabBTAWC);
		goldChip.setCreativeTab(CreativeTabsManager.tabBTAWC);
		gironChip.setCreativeTab(CreativeTabsManager.tabBTAWC);
		pulsatingChip.setCreativeTab(CreativeTabsManager.tabBTAWC);
		// Upgrade Chips
		speedUpgradeChip.setCreativeTab(CreativeTabsManager.tabBTAWC);
	}
	
	public static final void crafting() {
		// Grow Device
		
		// Grow Device Frame
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(growthDeviceFrame),
				"wbw",
				"bib",
				"wbw",
				'w', Blocks.PLANKS,
				'b', new ItemStack(Items.DYE, 1, 15),
				'i', Items.IRON_INGOT
		);
		
		// Growth Device
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(growthDevice), 
				" i ",
				"ifi",
				" i ",
				'i', Items.IRON_INGOT,
				'f', growthDeviceFrame);
		// Growth Device Super
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(growthDeviceSuper), 
				" g ",
				"gfg",
				" g ",
				'g', Items.GOLD_INGOT,
				'f', growthDevice);
		// Growth Device Mega
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(growthDeviceMega), 
				" d ",
				"dfd",
				" d ",
				'd', Items.DIAMOND,
				'f', growthDeviceSuper);
		// Growth Device Ultimate
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(growthDeviceUltra), 
				" n ",
				"nfn",
				" n ",
				'n', netherStarIngot,
				'f', growthDeviceMega);
		// Magnet
		
		// Magnet Frame
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(magnetFrame), 
				"i i",
				"i i",
				"ggg",
				'i', Items.IRON_INGOT,
				'g', Items.GOLD_INGOT);
		// Magnet Items
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(magnetItems), 
				" i ",
				"imi",
				" r ",
				'i', Items.IRON_INGOT,
				'r', Blocks.REDSTONE_BLOCK,
				'm', magnetFrame);
		
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(magnetAnimals), 
				" i ",
				"imi",
				" r ",
				'i', animalIngot,
				'r', Blocks.REDSTONE_BLOCK,
				'm', magnetFrame);
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(magnetMobs), 
				" i ",
				"imi",
				" r ",
				'i', mobIngot,
				'r', Blocks.REDSTONE_BLOCK,
				'm', magnetFrame);
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(magnetWither), 
				" i ",
				"imi",
				" r ",
				'i', netherStarIngot,
				'r', Blocks.REDSTONE_BLOCK,
				'm', magnetMobs);
		
		// Flowtation Device
		
		// Flowtation Device Frame
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(flowtationDeviceFrame), 
				" s ",
				"sfs",
				"fff",
				's', Items.STICK,
				'f', Items.FEATHER);
		// Flowtation Device
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(flowtationDevice), 
				" i ",
				"ifi",
				" i ",
				'i', netherStarIngot,
				'f', flowtationDeviceFrame);
		// Hoe of Greed
		// Hoe of Greed Basic
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(hoeOfGreed), 
				"igi",
				"ghg",
				"igi",
				'i', Items.IRON_INGOT,
				'g', crystalOfGreed,
				'h', Items.DIAMOND_HOE);
		// Hoe of Greed Super
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(hoeOfGreedSuper), 
				"igi",
				"ghg",
				"igi",
				'i', Items.GOLD_INGOT,
				'g', crystalOfGreed,
				'h', hoeOfGreed);
		// Hoe of Greed Mega
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(hoeOfGreedMega), 
				"igi",
				"ghg",
				"igi",
				'i', Items.DIAMOND,
				'g', crystalOfGreed,
				'h', hoeOfGreedSuper);
		// Hoe of Greed Ultra
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(hoeOfGreedUltra), 
				"igi",
				"ghg",
				"igi",
				'i', netherStarIngot,
				'g', crystalOfGreed,
				'h', hoeOfGreedMega);
		// Pickaxe of Greed
		// Hoe of Greed Basic
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(pickaxeOfGreed), 
				"igi",
				"ghg",
				"igi",
				'i', Items.IRON_INGOT,
				'g', crystalOfGreed,
				'h', Items.DIAMOND_PICKAXE);
		// Hoe of Greed Super
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(pickaxeOfGreedSuper), 
				"igi",
				"ghg",
				"igi",
				'i', Items.GOLD_INGOT,
				'g', crystalOfGreed,
				'h', pickaxeOfGreed);
		// Hoe of Greed Mega
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(pickaxeOfGreedMega), 
				"igi",
				"ghg",
				"igi",
				'i', Items.DIAMOND,
				'g', crystalOfGreed,
				'h', pickaxeOfGreedSuper);
		// Hoe of Greed Ultra
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(pickaxeOfGreedUltra), 
				"igi",
				"ghg",
				"igi",
				'i', netherStarIngot,
				'g', crystalOfGreed,
				'h', pickaxeOfGreedMega);
		// Axe of Greed
		// Hoe of Greed Basic
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(axeOfGreed), 
				"igi",
				"ghg",
				"igi",
				'i', Items.IRON_INGOT,
				'g', crystalOfGreed,
				'h', Items.DIAMOND_AXE);
		// Hoe of Greed Super
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(axeOfGreedSuper), 
				"igi",
				"ghg",
				"igi",
				'i', Items.GOLD_INGOT,
				'g', crystalOfGreed,
				'h', axeOfGreed);
		// Hoe of Greed Mega
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(axeOfGreedMega), 
				"igi",
				"ghg",
				"igi",
				'i', Items.DIAMOND,
				'g', crystalOfGreed,
				'h', axeOfGreedSuper);
		// Hoe of Greed Ultra
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(axeOfGreedUltra), 
				"igi",
				"ghg",
				"igi",
				'i', netherStarIngot,
				'g', crystalOfGreed,
				'h', axeOfGreedMega);
		
		// Sword of Greed
		// Hoe of Greed Basic
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(swordOfGreed), 
				"igi",
				"ghg",
				"igi",
				'i', Items.IRON_INGOT,
				'g', crystalOfGreed,
				'h', Items.DIAMOND_SWORD);
		// Hoe of Greed Super
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(swordOfGreedSuper), 
				"igi",
				"ghg",
				"igi",
				'i', Items.GOLD_INGOT,
				'g', crystalOfGreed,
				'h', swordOfGreed);
		// Hoe of Greed Mega
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(swordOfGreedMega), 
				"igi",
				"ghg",
				"igi",
				'i', Items.DIAMOND,
				'g', crystalOfGreed,
				'h', swordOfGreedSuper);
		// Hoe of Greed Ultra
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(swordOfGreedUltra), 
				"igi",
				"ghg",
				"igi",
				'i', netherStarIngot,
				'g', crystalOfGreed,
				'h', swordOfGreedMega);
		// Pulsator
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(pulsator), 
				"gmg",
				"ara",
				"gmg",
				'g', Items.GOLD_INGOT,
				'm', mobIngot,
				'a', animalIngot,
				'r', Blocks.REDSTONE_BLOCK);
		// Misc
		
		// Nether Star Ingot
		ModRegistry.addAlloyFurnaceRecipe(
				new ItemStack(Items.GOLD_INGOT), 
				new ItemStack(Items.DIAMOND), 
				new ItemStack(Items.NETHER_STAR), 
				null,
				new ItemStack(netherStarIngot));
		
		// Animal Ingot
		ModRegistry.addAlloyFurnaceRecipe(
				new ItemStack(Items.CHICKEN), 
				new ItemStack(Items.BEEF),
				new ItemStack(Items.PORKCHOP), 
				null, 
				new ItemStack(animalIngot));
		// Crystal of Greed
		ModRegistry.addAlloyFurnaceRecipe(
				new ItemStack(Items.DYE, 1, 4), 
				new ItemStack(Items.IRON_INGOT), 
				new ItemStack(Items.GOLD_INGOT), 
				new ItemStack(Items.DIAMOND), 
				new ItemStack(crystalOfGreed));
		// Mob Ingot
		ModRegistry.addAlloyFurnaceRecipe(
				new ItemStack(Items.GUNPOWDER), 
				new ItemStack(Items.STRING), 
				new ItemStack(Items.ROTTEN_FLESH), 
				null, 
				new ItemStack(mobIngot));
		// Crystal of Wrath
		ModRegistry.addAlloyFurnaceRecipe(
				new ItemStack(crystalOfGreed), 
				new ItemStack(Items.BLAZE_POWDER), 
				new ItemStack(Items.MAGMA_CREAM), 
				new ItemStack(Items.ENDER_PEARL), 
				new ItemStack(crystalOfWrath));
		
		// Graphite
		ModRegistry.addAlloyFurnaceRecipe(
				new ItemStack(Items.COAL), 
				new ItemStack(Blocks.SAND), 
				null, 
				null, 
				new ItemStack(graphite));
		// Giron Ingot
		ModRegistry.addAlloyFurnaceRecipe(
				new ItemStack(Items.IRON_INGOT),
				new ItemStack(Items.GOLD_INGOT),
				null,
				null,
				new ItemStack(gironIngot));
		// Steel Ingot
		ModRegistry.addAlloyFurnaceRecipe(
				new ItemStack(Items.COAL),
				new ItemStack(Items.IRON_INGOT),
				null,
				null,
				new ItemStack(steelIngot));
		// Chips
		// Iron Chip 4
		ModRegistry.addChipMakerRecipe(
				new ItemStack(Items.IRON_INGOT), 
				4, 
				new ItemStack(ironChip));
		// Gold Chip 4
		ModRegistry.addChipMakerRecipe(
				new ItemStack(Items.GOLD_INGOT), 
				4, 
				new ItemStack(goldChip));
		// Giron Chip 4
		ModRegistry.addChipMakerRecipe(
				new ItemStack(gironIngot), 
				4, 
				new ItemStack(gironChip));
		// Pulsating Chip 8
		ModRegistry.addChipMakerRecipe(
				new ItemStack(Items.ENDER_PEARL), 
				8, 
				new ItemStack(pulsatingChip));
		// Speed Upgrade Chips [ironChip + 16 redstone]
		ModRegistry.addChipMakerRecipe(
				new ItemStack(ironChip), 
				16, 
				new ItemStack(speedUpgradeChip));
	}
	
	public static void addToOreDictionary() {
		OreDictionary.registerOre("ingotNetharStar", netherStarIngot);
		OreDictionary.registerOre("ingotAnimal", animalIngot);
		OreDictionary.registerOre("ingotMob", mobIngot);
		OreDictionary.registerOre("ingotGiron", gironIngot);
		OreDictionary.registerOre("ingotSteel", steelIngot);
		OreDictionary.registerOre("crystalGreed", crystalOfGreed);
		OreDictionary.registerOre("crystalWrath", crystalOfWrath);
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerRenderer() {
		// Growth Device
		ModRegistry.registerRender(growthDeviceFrame, "growth_device_frame");
		ModRegistry.registerRender(growthDevice, "growth_device");
		ModRegistry.registerRender(growthDeviceSuper, "growth_device_super");
		ModRegistry.registerRender(growthDeviceMega, "growth_device_mega");
		ModRegistry.registerRender(growthDeviceUltra, "growth_device_ultra");
		// Magnets
		ModRegistry.registerRender(magnetFrame, "magnet_frame");
		ModRegistry.registerRender(magnetItems, "magnet_items");
		ModRegistry.registerRender(magnetAnimals, "magnet_animals");
		ModRegistry.registerRender(magnetMobs, "magnet_mobs");
		ModRegistry.registerRender(magnetWither, "magnet_wither");
		// Flowtation Device
		ModRegistry.registerRender(flowtationDeviceFrame, "flowtation_device_frame");
		ModRegistry.registerRender(flowtationDevice, "flowtation_device");
		// Hoe of Greed
		ModRegistry.registerRender(hoeOfGreed, "hoe_of_greed_basic");
		ModRegistry.registerRender(hoeOfGreedSuper, "hoe_of_greed_super");
		ModRegistry.registerRender(hoeOfGreedMega, "hoe_of_greed_mega");
		ModRegistry.registerRender(hoeOfGreedUltra, "hoe_of_greed_ultra");
		// Pickaxe of Greed
		ModRegistry.registerRender(pickaxeOfGreed, "pickaxe_of_greed_basic");
		ModRegistry.registerRender(pickaxeOfGreedSuper, "pickaxe_of_greed_super");
		ModRegistry.registerRender(pickaxeOfGreedMega, "pickaxe_of_greed_mega");
		ModRegistry.registerRender(pickaxeOfGreedUltra, "pickaxe_of_greed_ultra");
		// Axe of Greed
		ModRegistry.registerRender(axeOfGreed, "axe_of_greed_basic");
		ModRegistry.registerRender(axeOfGreedSuper, "axe_of_greed_super");
		ModRegistry.registerRender(axeOfGreedMega, "axe_of_greed_mega");
		ModRegistry.registerRender(axeOfGreedUltra, "axe_of_greed_ultra");
		// Sword of Greed
		ModRegistry.registerRender(swordOfGreed, "sword_of_greed_basic");
		ModRegistry.registerRender(swordOfGreedSuper, "sword_of_greed_super");
		ModRegistry.registerRender(swordOfGreedMega, "sword_of_greed_mega");
		ModRegistry.registerRender(swordOfGreedUltra, "sword_of_greed_ultra");
		// Pulsator
		ModRegistry.registerRender(pulsator, "pulsator");
		// Misc
		// Nether Star Ingot
		ModRegistry.registerRender(netherStarIngot, "nether_star_ingot");
		// Animal Ingot
		ModRegistry.registerRender(animalIngot, "animal_ingot");
		// Crystal of Greed
		ModRegistry.registerRender(crystalOfGreed, "crystal_of_greed");
		// Mob Ingot
		ModRegistry.registerRender(mobIngot, "mob_ingot");
		// Crystal of Wrath
		ModRegistry.registerRender(crystalOfWrath, "crystal_of_wrath");
		// graphite
		ModRegistry.registerRender(graphite, "graphite");
		// Giron Ingot
		ModRegistry.registerRender(gironIngot, "giron_ingot");
		// Steel Ingot
		ModRegistry.registerRender(steelIngot, "steel_ingot");
		// Chips
		ModRegistry.registerRender(ironChip, "iron_chip");
		ModRegistry.registerRender(goldChip, "gold_chip");
		ModRegistry.registerRender(gironChip, "giron_chip");
		ModRegistry.registerRender(pulsatingChip, "pulsating_chip");
		// Upgrade Chips
		ModRegistry.registerRender(speedUpgradeChip, "speed_upgrade_chip");
	}
}
