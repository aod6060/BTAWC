package com.derf.btawc.items;

import com.derf.btawc.creativetabs.CreativeTabsManager;
import com.derf.btawc.items.growthdevice.ItemGrowthDeviceBasic;
import com.derf.btawc.items.growthdevice.ItemGrowthDeviceMega;
import com.derf.btawc.items.growthdevice.ItemGrowthDeviceSuper;
import com.derf.btawc.items.growthdevice.ItemGrowthDeviceUltimate;
import com.derf.btawc.items.magnet.ItemMagnetAnimals;
import com.derf.btawc.items.magnet.ItemMagnetItems;
import com.derf.btawc.items.misc.ItemAnimalIngotCooked;
import com.derf.btawc.items.misc.ItemAnimalIngotUncooked;
import com.derf.btawc.items.tools.ItemAxeOfGreed;
import com.derf.btawc.items.tools.ItemHoeOfGreed;
import com.derf.btawc.items.tools.ItemPickaxeOfGreed;
import com.derf.btawc.items.tools.ItemSwordOfGreed;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

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
	// Sword of Greedy Wrath
	// Misc
	public static Item netharStarMesh;
	public static Item netharStarIngot;			// This is a new ingot that is made from nether star, diamonds, and iron...
	public static Item animalIngotUncooked;		// This is a new ingot that is mode from steak, pork, chicken...
	public static Item animalIngotCooked;		// Very good food source :D
	public static Item crystalOfGreed;			// Very greedy crystal :)
	public static Item mobIngot;				// Very scary ingot
	public static Item crystalOfWrath;			// Very angry crystal :)
	public static Item graphite;				// Used for solar and lunar panels
	
	public static final void create() {
		// Growth Device
		growthDeviceFrame = new ItemBasic("growth_device_frame");
		growthDevice = new ItemGrowthDeviceBasic();
		growthDeviceSuper = new ItemGrowthDeviceSuper();
		growthDeviceMega = new ItemGrowthDeviceMega();
		growthDeviceUltra = new ItemGrowthDeviceUltimate();
		// Magnet
		magnetFrame = new ItemBasic("magnet_frame");
		magnetItems = new ItemMagnetItems();
		magnetAnimals = new ItemMagnetAnimals();
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
		// Misc
		netharStarMesh = new ItemBasic("nethar_star_mesh");
		netharStarIngot = new ItemBasic("nethar_star_ingot");
		animalIngotUncooked = new ItemAnimalIngotUncooked();
		animalIngotCooked = new ItemAnimalIngotCooked();
		crystalOfGreed = new ItemBasic("crystal_of_greed");
		mobIngot = new ItemBasic("mob_ingot");
		crystalOfWrath = new ItemBasic("crystal_of_wrath");
		graphite = new ItemBasic("graphite");
	}
	
	public static final void register() {
		// Growth Device
		GameRegistry.registerItem(growthDeviceFrame, "growth_device_frame");
		GameRegistry.registerItem(growthDevice, "growth_device");
		GameRegistry.registerItem(growthDeviceSuper, "growth_device_super");
		GameRegistry.registerItem(growthDeviceMega, "growth_device_mega");
		GameRegistry.registerItem(growthDeviceUltra, "growth_device_ultimate");
		// Magnet
		GameRegistry.registerItem(magnetFrame, "magnet_frame");
		GameRegistry.registerItem(magnetItems, "magnet_items");
		GameRegistry.registerItem(magnetAnimals, "magnet_animals");
		// Flowtation Device
		GameRegistry.registerItem(flowtationDeviceFrame, "flowtation_device_frame");
		GameRegistry.registerItem(flowtationDevice, "flowtation_device");
		// Hoe of Greed
		GameRegistry.registerItem(hoeOfGreed, "hoe_of_greed_basic");
		GameRegistry.registerItem(hoeOfGreedSuper, "hoe_of_greed_super");
		GameRegistry.registerItem(hoeOfGreedMega, "hoe_of_greed_mega");
		GameRegistry.registerItem(hoeOfGreedUltra, "hoe_of_greed_ultra");
		// Pickaxe of Greed
		GameRegistry.registerItem(pickaxeOfGreed, "pickaxe_of_greed_basic");
		GameRegistry.registerItem(pickaxeOfGreedSuper, "pickaxe_of_greed_super");
		GameRegistry.registerItem(pickaxeOfGreedMega, "pickaxe_of_greed_mega");
		GameRegistry.registerItem(pickaxeOfGreedUltra, "pickaxe_of_greed_ultra");
		// Axe of Greed
		GameRegistry.registerItem(axeOfGreed, "axe_of_greed_basic");
		GameRegistry.registerItem(axeOfGreedSuper, "axe_of_greed_super");
		GameRegistry.registerItem(axeOfGreedMega, "axe_of_greed_mega");
		GameRegistry.registerItem(axeOfGreedUltra, "axe_of_greed_ultra");
		// Sword of Greed
		GameRegistry.registerItem(swordOfGreed, "sword_of_greed_basic");
		GameRegistry.registerItem(swordOfGreedSuper, "sword_of_greed_super");
		GameRegistry.registerItem(swordOfGreedMega, "sword_of_greed_mega");
		GameRegistry.registerItem(swordOfGreedUltra, "sword_of_greed_ultra");
		// Sword of Greed
		GameRegistry.registerItem(swordOfGreed, "sword_of_greed_basic");
		// Misc
		GameRegistry.registerItem(netharStarMesh, "nethar_star_mesh");
		GameRegistry.registerItem(netharStarIngot, "nethar_star_ingot");
		GameRegistry.registerItem(animalIngotUncooked, "animal_ingot_uncooked");
		GameRegistry.registerItem(animalIngotCooked, "animal_ingot_cooked");
		GameRegistry.registerItem(crystalOfGreed, "crystal_of_greed");
		GameRegistry.registerItem(mobIngot, "mob_ingot");
		GameRegistry.registerItem(crystalOfWrath, "crystal_of_wrath");
		GameRegistry.registerItem(graphite, "graphite");
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
		// Sword of Greed
		swordOfGreed.setCreativeTab(CreativeTabsManager.tabBTAWC);
		// Misc
		netharStarMesh.setCreativeTab(CreativeTabsManager.tabBTAWC);
		netharStarIngot.setCreativeTab(CreativeTabsManager.tabBTAWC);
		animalIngotUncooked.setCreativeTab(CreativeTabsManager.tabBTAWC);
		animalIngotCooked.setCreativeTab(CreativeTabsManager.tabBTAWC);
		crystalOfGreed.setCreativeTab(CreativeTabsManager.tabBTAWC);
		mobIngot.setCreativeTab(CreativeTabsManager.tabBTAWC);
		crystalOfWrath.setCreativeTab(CreativeTabsManager.tabBTAWC);
		graphite.setCreativeTab(CreativeTabsManager.tabBTAWC);
	}
	
	public static final void crafting() {
		// Grow Device
		
		// Grow Device Frame
		GameRegistry.addShapedRecipe(
				new ItemStack(growthDeviceFrame),
				"wbw",
				"bib",
				"wbw",
				'w', Blocks.planks,
				'b', new ItemStack(Items.dye, 1, 15),
				'i', Items.iron_ingot
		);
		// Growth Device
		GameRegistry.addShapedRecipe(
				new ItemStack(growthDevice), 
				" i ",
				"ifi",
				" i ",
				'i', Items.iron_ingot,
				'f', growthDeviceFrame);
		// Growth Device Super
		GameRegistry.addShapedRecipe(
				new ItemStack(growthDeviceSuper), 
				" g ",
				"gfg",
				" g ",
				'g', Items.gold_ingot,
				'f', growthDevice);
		// Growth Device Mega
		GameRegistry.addShapedRecipe(
				new ItemStack(growthDeviceMega), 
				" d ",
				"dfd",
				" d ",
				'd', Items.diamond,
				'f', growthDeviceSuper);
		// Growth Device Ultimate
		GameRegistry.addShapedRecipe(
				new ItemStack(growthDeviceUltra), 
				" n ",
				"nfn",
				" n ",
				'n', netharStarIngot,
				'f', growthDeviceMega);
		// Magnet
		
		// Magnet Frame
		GameRegistry.addShapedRecipe(
				new ItemStack(magnetFrame), 
				"i i",
				"i i",
				"ggg",
				'i', Items.iron_ingot,
				'g', Items.gold_ingot);
		
		// Magnet Items
		GameRegistry.addShapedRecipe(
				new ItemStack(magnetItems), 
				" i ",
				"imi",
				" r ",
				'i', Items.iron_ingot,
				'r', Blocks.redstone_block,
				'm', magnetFrame);
		
		GameRegistry.addShapedRecipe(
				new ItemStack(magnetAnimals), 
				" i ",
				"imi",
				" r ",
				'i', animalIngotCooked,
				'r', Blocks.redstone_block,
				'm', magnetFrame);
		
		// Flowtation Device
		
		// Flowtation Device Frame
		GameRegistry.addShapedRecipe(
				new ItemStack(flowtationDeviceFrame), 
				" s ",
				"sfs",
				"fff",
				's', Items.stick,
				'f', Items.feather);
		
		// Flowtation Device
		GameRegistry.addShapedRecipe(
				new ItemStack(flowtationDevice), 
				" i ",
				"ifi",
				" i ",
				'i', netharStarIngot,
				'f', flowtationDeviceFrame);
		
		// Hoe of Greed
		// Hoe of Greed Basic
		GameRegistry.addShapedRecipe(
				new ItemStack(hoeOfGreed), 
				"igi",
				"ghg",
				"igi",
				'i', Items.iron_ingot,
				'g', crystalOfGreed,
				'h', Items.diamond_hoe);
		// Hoe of Greed Super
		GameRegistry.addShapedRecipe(
				new ItemStack(hoeOfGreedSuper), 
				"igi",
				"ghg",
				"igi",
				'i', Items.gold_ingot,
				'g', crystalOfGreed,
				'h', hoeOfGreed);
		// Hoe of Greed Mega
		GameRegistry.addShapedRecipe(
				new ItemStack(hoeOfGreedMega), 
				"igi",
				"ghg",
				"igi",
				'i', Items.diamond,
				'g', crystalOfGreed,
				'h', hoeOfGreedSuper);
		// Hoe of Greed Ultra
		GameRegistry.addShapedRecipe(
				new ItemStack(hoeOfGreedUltra), 
				"igi",
				"ghg",
				"igi",
				'i', netharStarIngot,
				'g', crystalOfGreed,
				'h', hoeOfGreedMega);
		// Pickaxe of Greed
		// Hoe of Greed Basic
		GameRegistry.addShapedRecipe(
				new ItemStack(pickaxeOfGreed), 
				"igi",
				"ghg",
				"igi",
				'i', Items.iron_ingot,
				'g', crystalOfGreed,
				'h', Items.diamond_pickaxe);
		// Hoe of Greed Super
		GameRegistry.addShapedRecipe(
				new ItemStack(pickaxeOfGreedSuper), 
				"igi",
				"ghg",
				"igi",
				'i', Items.gold_ingot,
				'g', crystalOfGreed,
				'h', pickaxeOfGreed);
		// Hoe of Greed Mega
		GameRegistry.addShapedRecipe(
				new ItemStack(pickaxeOfGreedMega), 
				"igi",
				"ghg",
				"igi",
				'i', Items.diamond,
				'g', crystalOfGreed,
				'h', pickaxeOfGreedSuper);
		// Hoe of Greed Ultra
		GameRegistry.addShapedRecipe(
				new ItemStack(pickaxeOfGreedUltra), 
				"igi",
				"ghg",
				"igi",
				'i', netharStarIngot,
				'g', crystalOfGreed,
				'h', pickaxeOfGreedMega);
		// Axe of Greed
		// Hoe of Greed Basic
		GameRegistry.addShapedRecipe(
				new ItemStack(axeOfGreed), 
				"igi",
				"ghg",
				"igi",
				'i', Items.iron_ingot,
				'g', crystalOfGreed,
				'h', Items.diamond_axe);
		// Hoe of Greed Super
		GameRegistry.addShapedRecipe(
				new ItemStack(axeOfGreedSuper), 
				"igi",
				"ghg",
				"igi",
				'i', Items.gold_ingot,
				'g', crystalOfGreed,
				'h', axeOfGreed);
		// Hoe of Greed Mega
		GameRegistry.addShapedRecipe(
				new ItemStack(axeOfGreedMega), 
				"igi",
				"ghg",
				"igi",
				'i', Items.diamond,
				'g', crystalOfGreed,
				'h', axeOfGreedSuper);
		// Hoe of Greed Ultra
		GameRegistry.addShapedRecipe(
				new ItemStack(axeOfGreedUltra), 
				"igi",
				"ghg",
				"igi",
				'i', netharStarIngot,
				'g', crystalOfGreed,
				'h', axeOfGreedMega);
		
		// Sword of Greed
		// Hoe of Greed Basic
		GameRegistry.addShapedRecipe(
				new ItemStack(swordOfGreed), 
				"igi",
				"ghg",
				"igi",
				'i', Items.iron_ingot,
				'g', crystalOfGreed,
				'h', Items.diamond_sword);
		// Hoe of Greed Super
		GameRegistry.addShapedRecipe(
				new ItemStack(swordOfGreedSuper), 
				"igi",
				"ghg",
				"igi",
				'i', Items.gold_ingot,
				'g', crystalOfGreed,
				'h', swordOfGreed);
		// Hoe of Greed Mega
		GameRegistry.addShapedRecipe(
				new ItemStack(swordOfGreedMega), 
				"igi",
				"ghg",
				"igi",
				'i', Items.diamond,
				'g', crystalOfGreed,
				'h', swordOfGreedSuper);
		// Hoe of Greed Ultra
		GameRegistry.addShapedRecipe(
				new ItemStack(swordOfGreedUltra), 
				"igi",
				"ghg",
				"igi",
				'i', netharStarIngot,
				'g', crystalOfGreed,
				'h', swordOfGreedMega);
		
		// Misc
		
		// Nethar Star Mesh
		GameRegistry.addShapedRecipe(
				new ItemStack(netharStarMesh),
				"gdg",
				"dnd",
				"gdg",
				'g', Items.gold_ingot,
				'd', Items.diamond,
				'n', Items.nether_star);
		// Nethar Star Ingot
		GameRegistry.addSmelting(netharStarMesh, new ItemStack(netharStarIngot), 255.0f);
		
		// Animal Ingot Uncooked
		GameRegistry.addShapelessRecipe(
				new ItemStack(animalIngotUncooked), 
				Items.chicken,
				Items.chicken,
				Items.chicken,
				Items.beef,
				Items.beef,
				Items.beef,
				Items.porkchop,
				Items.porkchop,
				Items.porkchop);
		
		// Animal Ingot Cooked
		GameRegistry.addSmelting(animalIngotUncooked, new ItemStack(animalIngotCooked), 5.0f);
		
		// Crystal of Greed
		GameRegistry.addShapedRecipe(
				new ItemStack(crystalOfGreed), 
				"lgl",
				"idi",
				"lgl",
				'l', new ItemStack(Items.dye, 1, 4),
				'i', Items.iron_ingot,
				'g', Items.gold_ingot,
				'd', Items.diamond);
	}
}
