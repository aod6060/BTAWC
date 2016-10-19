package com.derf.btawc.lifecycle;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;

import com.derf.btawc.BTAWCLogger;
import com.derf.btawc.ModRegistry;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public final class LifeCycleManager {
	// Item Life Cycles
	private static List<IItemLifeCycle> items = new ArrayList<IItemLifeCycle>();
	// Block Life Cycles
	private static List<IBlockLifeCycle> blocks = new ArrayList<IBlockLifeCycle>();
	
	
	public static void addItemLifeCycle(			
			String registerName,
			Item item,
			CreativeTabs tabs,
			String oreDictionaryName) {
		addItemLifeCycle(new ItemLifeCycle(registerName, item, tabs, oreDictionaryName));
	}
	
	public static void addItemLifeCycle(IItemLifeCycle lifeCycle) {
		items.add(lifeCycle);
	}
	
	public static void addBlockLifeCycle(
			String registryName, 
			Block block, 
			CreativeTabs tabs,
			Class<? extends TileEntity> tileEntityClass, 
			String oreDictionaryName) {
		addBlockLifeCycle(new BlockLifeCycle(registryName, block, tabs, tileEntityClass, oreDictionaryName));
	}
	
	public static void addBlockLifeCycle(IBlockLifeCycle lifeCycle) {
		blocks.add(lifeCycle);
	}
	
	public static void register() {
		// Do Items first
		BTAWCLogger.getLogger().log(Level.INFO, "Getting started on BTAWC life cycle.");
		BTAWCLogger.getLogger().log(Level.INFO, "Handle Item Life Cycles");
		for(IItemLifeCycle item : items) {
			ModRegistry.registerItem(item.getItem(), item.getRegisteryName());
			BTAWCLogger.getLogger().log(Level.INFO, "Registered Item: " + item.getRegisteryName());
		}
		BTAWCLogger.getLogger().log(Level.INFO, "Handle Block Life Cycles");
		// Do Blocks next
		for(IBlockLifeCycle block : blocks) {
			ModRegistry.registerBlock(block.getBlock(), block.getRegistryName());
			BTAWCLogger.getLogger().log(Level.INFO, "Registered Block: " + block.getRegistryName());
		}
	}
	
	public static void tileEntities() {
		// Register Tile Entities
		BTAWCLogger.getLogger().log(Level.INFO, "Handle TileEntity Registraction...");
		for(IBlockLifeCycle block : blocks) {
			if(block.hasTileEnttiy()) {
				ModRegistry.registerTileEntity(block.getTileEntityClass(), block.getRegistryName());
				BTAWCLogger.getLogger().log(Level.INFO, "Register TileEntity: " + block.getRegistryName());
			}
		}
	}
	
	public static void creativeTabs() {
		// Add items and blocks to creativetabs
		BTAWCLogger.getLogger().log(Level.INFO, "Handle CreativeTabs Registraction...");
		BTAWCLogger.getLogger().log(Level.INFO, "Handle Item Life Cycles");
		for(IItemLifeCycle item : items) {
			if(item.getCreativeTabs() != null) {
				item.getItem().setCreativeTab(item.getCreativeTabs());
				BTAWCLogger.getLogger().log(Level.INFO, "Added " + item.getRegisteryName() + " to " + item.getCreativeTabs());
			}
		}
		BTAWCLogger.getLogger().log(Level.INFO, "Handle Block Life Cycles");
		for(IBlockLifeCycle block : blocks) {
			if(block.getCreativeTabs() != null) {
				block.getBlock().setCreativeTab(block.getCreativeTabs());
				BTAWCLogger.getLogger().log(Level.INFO, "Added " + block.getRegistryName() + " to " + block.getCreativeTabs());
			}
		}
	}
	
	public static void oreDictionary() {
		BTAWCLogger.getLogger().log(Level.INFO, "Handle OreDictionary Registraction...");
		BTAWCLogger.getLogger().log(Level.INFO, "Handle Item Life Cycles");
		for(IItemLifeCycle item : items) {
			if(item.hasOreDictionaryName()) {
				OreDictionary.registerOre(item.getOreDictionaryName(), item.getItem());
				BTAWCLogger.getLogger().log(Level.INFO, "Added Item to Ore Dictionary: "+item.getRegisteryName());
			}
		}
		BTAWCLogger.getLogger().log(Level.INFO, "Handle Block Life Cycles");
		for(IBlockLifeCycle block : blocks) {
			if(block.hasOredictionaryName()) {
				OreDictionary.registerOre(block.getOreDictionaryName(), block.getBlock());
				BTAWCLogger.getLogger().log(Level.INFO, "Added Block to Ore Dictionary: "+block.getRegistryName());
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerRender() {
		BTAWCLogger.getLogger().log(Level.INFO, "Handle Render Registraction...");
		BTAWCLogger.getLogger().log(Level.INFO, "Handle Item Life Cycles");
		for(IItemLifeCycle item : items) {
			ModRegistry.registerRender(item.getItem(), item.getRegisteryName());
			BTAWCLogger.getLogger().log(Level.INFO, "Added Item Render for: "+item.getRegisteryName());
		}
		BTAWCLogger.getLogger().log(Level.INFO, "Handle Block Life Cycles");
		for(IBlockLifeCycle block : blocks) {
			ModRegistry.registerRender(block.getBlock(), block.getRegistryName());
			BTAWCLogger.getLogger().log(Level.INFO, "Added Block Render for: "+block.getRegistryName());
		}
	}
}
