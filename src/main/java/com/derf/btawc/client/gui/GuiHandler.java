package com.derf.btawc.client.gui;

import com.derf.btawc.blocks.tileentity.chipmaker.TileEntityChipMaker;
import com.derf.btawc.blocks.tileentity.furnace.TileEntityAlloyFurnace;
import com.derf.btawc.blocks.tileentity.furnace.TileEntitySuperFurnace;
import com.derf.btawc.blocks.tileentity.generators.TileEntityCreativeGenerator;
import com.derf.btawc.client.gui.chipmaker.GuiContainerChipMaker;
import com.derf.btawc.client.gui.furnace.GuiContainerAlloyFurnace;
import com.derf.btawc.client.gui.furnace.GuiContainerSuperFurnace;
import com.derf.btawc.client.gui.generators.GuiContainerCreativeGenerator;
import com.derf.btawc.inventory.container.ContainerAlloyFurnace;
import com.derf.btawc.inventory.container.ContainerChipMaker;
import com.derf.btawc.inventory.container.ContainerCreativeGenerator;
import com.derf.btawc.inventory.container.ContainerSuperFurnace;

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
		}
		
		return null;
	}

}
