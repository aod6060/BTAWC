package com.derf.btawc.items;

import com.derf.btawc.ModRegistry;
import com.derf.btawc.creativetabs.CreativeTabsManager;
import com.derf.btawc.items.airhorn.ItemAirHorn;
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
import com.derf.btawc.lifecycle.LifeCycleManager;

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
	public static Item speedUpgradeChip; // Makes a machine produce faster
	public static Item efficencyUpgradeChip; // Makes a machine waste less fuel or energy
	// Air Horn - Because I can :)
	public static Item airHorn;
	// Nether Star Fuel
	public static Item netherStarFuel; // Lasts forever, about 1,000,000 ticks lol
	// Panels
	public static Item solarPane;
	public static Item lunarPane;
	
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
		speedUpgradeChip = new ItemUpgrades("speed_upgrade_chip");
		efficencyUpgradeChip = new ItemUpgrades("efficency_upgrade_chip");
		// Air Horn
		airHorn = new ItemAirHorn();
		// Nether Star Fuel
		netherStarFuel = new ItemBasic("nether_star_fuel");
		// Solar Pane
		solarPane = new ItemBasic("solar_pane");
		// Lunar Pane
		lunarPane = new ItemBasic("lunar_pane");
		
	}
	
	public static final void lifeCycle() {
		// Growth Device
		LifeCycleManager.addItemLifeCycle("growth_device_frame", growthDeviceFrame, CreativeTabsManager.tabBTAWC, null);
		LifeCycleManager.addItemLifeCycle("growth_device", growthDevice, CreativeTabsManager.tabBTAWC, null);
		LifeCycleManager.addItemLifeCycle("growth_device_super", growthDeviceSuper, CreativeTabsManager.tabBTAWC, null);
		LifeCycleManager.addItemLifeCycle("growth_device_mega", growthDeviceMega, CreativeTabsManager.tabBTAWC, null);
		LifeCycleManager.addItemLifeCycle("growth_device_ultra", growthDeviceUltra, CreativeTabsManager.tabBTAWC, null);
		// Magnet
		LifeCycleManager.addItemLifeCycle("magnet_frame", magnetFrame, CreativeTabsManager.tabBTAWC, null);
		LifeCycleManager.addItemLifeCycle("magnet_items", magnetItems, CreativeTabsManager.tabBTAWC, null);
		LifeCycleManager.addItemLifeCycle("magnet_animals", magnetAnimals, CreativeTabsManager.tabBTAWC, null);
		LifeCycleManager.addItemLifeCycle("magnet_mobs", magnetMobs, CreativeTabsManager.tabBTAWC, null);
		LifeCycleManager.addItemLifeCycle("magnet_wither", magnetWither, CreativeTabsManager.tabBTAWC, null);
		// Flowtation Device
		LifeCycleManager.addItemLifeCycle("flowtation_device_frame", flowtationDeviceFrame, CreativeTabsManager.tabBTAWC, null);
		LifeCycleManager.addItemLifeCycle("flowtation_device", flowtationDevice, CreativeTabsManager.tabBTAWC, null);
		// Hoe of Greed
		LifeCycleManager.addItemLifeCycle("hoe_of_greed_basic", hoeOfGreed, CreativeTabsManager.tabBTAWC, null);
		LifeCycleManager.addItemLifeCycle("hoe_of_greed_super", hoeOfGreedSuper, CreativeTabsManager.tabBTAWC, null);
		LifeCycleManager.addItemLifeCycle("hoe_of_greed_mega", hoeOfGreedMega, CreativeTabsManager.tabBTAWC, null);
		LifeCycleManager.addItemLifeCycle("hoe_of_greed_ultra", hoeOfGreedUltra, CreativeTabsManager.tabBTAWC, null);
		// Pickaxe of Greed
		LifeCycleManager.addItemLifeCycle("pickaxe_of_greed_basic", pickaxeOfGreed, CreativeTabsManager.tabBTAWC, null);
		LifeCycleManager.addItemLifeCycle("pickaxe_of_greed_super", pickaxeOfGreedSuper, CreativeTabsManager.tabBTAWC, null);
		LifeCycleManager.addItemLifeCycle("pickaxe_of_greed_mega", pickaxeOfGreedMega, CreativeTabsManager.tabBTAWC, null);
		LifeCycleManager.addItemLifeCycle("pickaxe_of_greed_ultra", pickaxeOfGreedUltra, CreativeTabsManager.tabBTAWC, null);
		// Axe of Greed
		LifeCycleManager.addItemLifeCycle("axe_of_greed_basic", axeOfGreed, CreativeTabsManager.tabBTAWC, null);
		LifeCycleManager.addItemLifeCycle("axe_of_greed_super", axeOfGreedSuper, CreativeTabsManager.tabBTAWC, null);
		LifeCycleManager.addItemLifeCycle("axe_of_greed_mega", axeOfGreedMega, CreativeTabsManager.tabBTAWC, null);
		LifeCycleManager.addItemLifeCycle("axe_of_greed_ultra", axeOfGreedUltra, CreativeTabsManager.tabBTAWC, null);
		// Sword of Greed
		LifeCycleManager.addItemLifeCycle("sword_of_greed_basic", swordOfGreed, CreativeTabsManager.tabBTAWC, null);
		LifeCycleManager.addItemLifeCycle("sword_of_greed_super", swordOfGreedSuper, CreativeTabsManager.tabBTAWC, null);
		LifeCycleManager.addItemLifeCycle("sword_of_greed_mega", swordOfGreedMega, CreativeTabsManager.tabBTAWC, null);
		LifeCycleManager.addItemLifeCycle("sword_of_greed_ultra", swordOfGreedUltra, CreativeTabsManager.tabBTAWC, null);
		// Pulsator
		LifeCycleManager.addItemLifeCycle("pulsator", pulsator, CreativeTabsManager.tabBTAWC, null);
		// Materials
		// Ingots
		LifeCycleManager.addItemLifeCycle("nether_star_ingot", netherStarIngot, CreativeTabsManager.tabBTAWC, "ingotNetherStar");
		LifeCycleManager.addItemLifeCycle("animal_ingot", animalIngot, CreativeTabsManager.tabBTAWC, "ingotAnimal");
		LifeCycleManager.addItemLifeCycle("mob_ingot", mobIngot, CreativeTabsManager.tabBTAWC, "ingotMob");
		LifeCycleManager.addItemLifeCycle("giron_ingot", gironIngot, CreativeTabsManager.tabBTAWC, "ingotGiron");
		LifeCycleManager.addItemLifeCycle("steel_ingot", steelIngot, CreativeTabsManager.tabBTAWC, "ingotSteel");
		LifeCycleManager.addItemLifeCycle("graphite", graphite, CreativeTabsManager.tabBTAWC, "graphite");
		// Crystals
		LifeCycleManager.addItemLifeCycle("crystal_of_greed", crystalOfGreed, CreativeTabsManager.tabBTAWC, "crystalGreed");
		LifeCycleManager.addItemLifeCycle("crystal_of_wrath", crystalOfWrath, CreativeTabsManager.tabBTAWC, "crystalWrath");
		// Chips
		LifeCycleManager.addItemLifeCycle("iron_chip", ironChip, CreativeTabsManager.tabBTAWC, null);
		LifeCycleManager.addItemLifeCycle("gold_chip", goldChip, CreativeTabsManager.tabBTAWC, null);
		LifeCycleManager.addItemLifeCycle("giron_chip", gironChip, CreativeTabsManager.tabBTAWC, null);
		LifeCycleManager.addItemLifeCycle("pulsating_chip", pulsatingChip, CreativeTabsManager.tabBTAWC, null);
		// Upgrade Chips
		LifeCycleManager.addItemLifeCycle("speed_upgrade_chip", speedUpgradeChip, CreativeTabsManager.tabBTAWC, null);
		LifeCycleManager.addItemLifeCycle("efficency_upgrade_chip", efficencyUpgradeChip, CreativeTabsManager.tabBTAWC, null);
		// Air Horn
		LifeCycleManager.addItemLifeCycle("air_horn", airHorn, CreativeTabsManager.tabBTAWC, null);
		// Fuel
		LifeCycleManager.addItemLifeCycle("nether_star_fuel", netherStarFuel, CreativeTabsManager.tabBTAWC, null);
		// Solar Pane
		LifeCycleManager.addItemLifeCycle("solar_pane", solarPane, CreativeTabsManager.tabBTAWC, null);
		// Lunar Pane
		LifeCycleManager.addItemLifeCycle("lunar_pane", lunarPane, CreativeTabsManager.tabBTAWC, null);
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
		// Efficency Upgrade chips [goldChip + 16 redstone]
		ModRegistry.addChipMakerRecipe(
				new ItemStack(goldChip), 
				16, 
				new ItemStack(efficencyUpgradeChip));
		// Air Horn
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(airHorn), 
				" r ",
				"sgs",
				"sss",
				'r', graphite,
				's', steelIngot,
				'g', Items.GLASS_BOTTLE);
		// Nether Star Fuel
		ModRegistry.addAlloyFurnaceRecipe(
				new ItemStack(netherStarIngot), 
				new ItemStack(Items.COAL), 
				new ItemStack(Items.COAL), 
				new ItemStack(netherStarIngot), 
				new ItemStack(netherStarFuel, 4));
		// Torch Recipe for Nether Star Fuel
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(Blocks.TORCH, 64), 
				"n",
				"s",
				'n', netherStarFuel,
				's', Items.STICK);
		// Solar Pane Recipe
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(solarPane),
				"ggg",
				"sis",
				"sss",
				'g', ItemsManager.graphite,
				's', ItemsManager.steelIngot,
				'i', Items.IRON_INGOT);
		
		// Lunar Pane Recipe
		ModRegistry.addShapedCraftingRecipe(
				new ItemStack(lunarPane), 
				"ggg",
				"sis",
				"sss",
				'g', ItemsManager.graphite,
				's', ItemsManager.steelIngot,
				'i', Items.GOLD_INGOT);
	}
}
