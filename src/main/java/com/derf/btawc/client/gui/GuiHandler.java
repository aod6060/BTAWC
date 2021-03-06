package com.derf.btawc.client.gui;

import com.derf.btawc.client.gui.chipmaker.GuiContainerChipMaker;
import com.derf.btawc.client.gui.furnace.GuiContainerAlloyFurnace;
import com.derf.btawc.client.gui.furnace.GuiContainerSuperFurnace;
import com.derf.btawc.client.gui.generators.GuiContainerCreativeGenerator;
import com.derf.btawc.client.gui.generators.GuiContainerLunarPanel;
import com.derf.btawc.client.gui.generators.GuiContainerSolarPanel;
import com.derf.btawc.client.gui.generators.GuiContainerSolidFuelGenerator;
import com.derf.btawc.client.gui.itembuffer.GuiContainerItemBuffer;
import com.derf.btawc.client.gui.tank.GuiContainerTank;
import com.derf.btawc.inventory.container.chipmaker.ContainerChipMaker;
import com.derf.btawc.inventory.container.furnace.ContainerAlloyFurnace;
import com.derf.btawc.inventory.container.furnace.ContainerSuperFurnace;
import com.derf.btawc.inventory.container.generator.ContainerCreativeGenerator;
import com.derf.btawc.inventory.container.generator.ContainerLunarPanel;
import com.derf.btawc.inventory.container.generator.ContainerSolarPanel;
import com.derf.btawc.inventory.container.generator.ContainerSolidFuelGenerator;
import com.derf.btawc.inventory.container.itembuffer.ContainerItemBuffer;
import com.derf.btawc.inventory.container.tank.ContainerTank;
import com.derf.btawc.tileentity.chipmaker.TileEntityChipMaker;
import com.derf.btawc.tileentity.furnace.TileEntityAlloyFurnace;
import com.derf.btawc.tileentity.furnace.TileEntitySuperFurnace;
import com.derf.btawc.tileentity.generators.TileEntityCreativeGenerator;
import com.derf.btawc.tileentity.generators.TileEntityLunarPanel;
import com.derf.btawc.tileentity.generators.TileEntitySolarPanel;
import com.derf.btawc.tileentity.generators.TileEntitySolidFuelGenerator;
import com.derf.btawc.tileentity.itembuffer.TileEntityItemBuffer;
import com.derf.btawc.tileentity.tank.TileEntityTank;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	
	// Super Furnace
	public final static int SUPER_FURNACE_GUI = 0;
	// Alloy Furnace
	public final static int ALLOY_FURNACE_GUI = 1;
	// Creative Generator
	public final static int CREATIVE_GENERATOR_GUI = 2;
	// Chip Maker
	public final static int CHIP_MAKER_GUI = 3;
	// Item Buffer
	public static final int ITEM_BUFFER_GUI = 4;
	// Solid Fuel Generator
	public static final int SOLID_FUEL_GENERATOR_GUI = 5;
	// Solar Panel
	public static final int SOLAR_PANEL_GUI = 6;
	// Lunar Panel
	public static final int LUNAR_PANEL_GUI = 7;
	// Tank
	public static final int TANK = 8;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		
		switch(ID) {
		case SUPER_FURNACE_GUI:
			return new ContainerSuperFurnace(player.inventory, (TileEntitySuperFurnace) world.getTileEntity(pos));
		case ALLOY_FURNACE_GUI:
			return new ContainerAlloyFurnace(player.inventory, (TileEntityAlloyFurnace)world.getTileEntity(pos));
		case CREATIVE_GENERATOR_GUI:
			return new ContainerCreativeGenerator(player.inventory, (TileEntityCreativeGenerator)world.getTileEntity(pos));
		case CHIP_MAKER_GUI:
			return new ContainerChipMaker(player.inventory, (TileEntityChipMaker)world.getTileEntity(pos));
		case ITEM_BUFFER_GUI:
			return new ContainerItemBuffer(player.inventory, (TileEntityItemBuffer)world.getTileEntity(pos));
		case SOLID_FUEL_GENERATOR_GUI:
			return new ContainerSolidFuelGenerator(player.inventory, (TileEntitySolidFuelGenerator)world.getTileEntity(pos));
		case SOLAR_PANEL_GUI:
			return new ContainerSolarPanel(player.inventory, (TileEntitySolarPanel)world.getTileEntity(pos));
		case LUNAR_PANEL_GUI:
			return new ContainerLunarPanel(player.inventory, (TileEntityLunarPanel)world.getTileEntity(pos));
		case TANK:
			return new ContainerTank(player.inventory, (TileEntityTank)world.getTileEntity(pos));
		}
		
		return null;
	}
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		BlockPos pos = new BlockPos(x, y, z);
		
		switch(ID) {
		case SUPER_FURNACE_GUI:
			return new GuiContainerSuperFurnace(player.inventory, (TileEntitySuperFurnace)world.getTileEntity(pos));
		case ALLOY_FURNACE_GUI:
			return new GuiContainerAlloyFurnace(player.inventory, (TileEntityAlloyFurnace)world.getTileEntity(pos));
		case CREATIVE_GENERATOR_GUI:
			return new GuiContainerCreativeGenerator(player.inventory, (TileEntityCreativeGenerator)world.getTileEntity(pos));
		case CHIP_MAKER_GUI:
			return new GuiContainerChipMaker(player.inventory, (TileEntityChipMaker)world.getTileEntity(pos));
		case ITEM_BUFFER_GUI:
			return new GuiContainerItemBuffer(player.inventory, (TileEntityItemBuffer)world.getTileEntity(pos));
		case SOLID_FUEL_GENERATOR_GUI:
			return new GuiContainerSolidFuelGenerator(player.inventory, (TileEntitySolidFuelGenerator)world.getTileEntity(pos));
		case SOLAR_PANEL_GUI:
			return new GuiContainerSolarPanel(player.inventory, (TileEntitySolarPanel)world.getTileEntity(pos));
		case LUNAR_PANEL_GUI:
			return new GuiContainerLunarPanel(player.inventory, (TileEntityLunarPanel)world.getTileEntity(pos));
		case TANK:
			return new GuiContainerTank(player.inventory, (TileEntityTank)world.getTileEntity(pos));
		}
		
		return null;
	}

}
