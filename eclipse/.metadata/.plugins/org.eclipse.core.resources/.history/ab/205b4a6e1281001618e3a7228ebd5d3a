package com.derf.btawc.items;

import com.derf.btawc.creativetabs.CreativeTabsManager;

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
	
	// Misc
	public static Item netharStarMesh;
	public static Item netharStarIngot;			// This is a new ingot that is made from nether star, diamonds, and iron...
	public static Item animalIngotUncooked;		// This is a new ingot that is mode from steak, pork, chicken...
	public static Item animalIngotCooked;		// Very good food source :D
	
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
		// Misc
		netharStarMesh = new ItemBasic("nethar_star_mesh");
		netharStarIngot = new ItemBasic("nethar_star_ingot");
		animalIngotUncooked = new ItemAnimalIngotUncooked();
		animalIngotCooked = new ItemAnimalIngotCooked();
		
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
		// Misc
		GameRegistry.registerItem(netharStarMesh, "nethar_star_mesh");
		GameRegistry.registerItem(netharStarIngot, "nethar_star_ingot");
		GameRegistry.registerItem(animalIngotUncooked, "animal_ingot_uncooked");
		GameRegistry.registerItem(animalIngotCooked, "animal_ingot_cooked");
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
		// Misc
		netharStarMesh.setCreativeTab(CreativeTabsManager.tabBTAWC);
		netharStarIngot.setCreativeTab(CreativeTabsManager.tabBTAWC);
		animalIngotUncooked.setCreativeTab(CreativeTabsManager.tabBTAWC);
		animalIngotCooked.setCreativeTab(CreativeTabsManager.tabBTAWC);
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
	}
}
