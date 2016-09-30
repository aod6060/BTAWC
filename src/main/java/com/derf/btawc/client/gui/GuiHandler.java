package com.derf.btawc.client.gui;

import com.derf.btawc.blocks.inventory.container.furnace.ContainerAlloyFurnace;
import com.derf.btawc.blocks.inventory.container.furnace.ContainerSuperFurnace;
import com.derf.btawc.blocks.inventory.container.generator.ContainerCreativeGenerator;
import com.derf.btawc.blocks.tileentity.furnace.TileEntityAlloyFurnace;
import com.derf.btawc.blocks.tileentity.furnace.TileEntitySuperFurnace;
import com.derf.btawc.blocks.tileentity.generators.TileEntityCreativeGenerator;
import com.derf.btawc.client.gui.furnace.GuiContainerAlloyFurnace;
import com.derf.btawc.client.gui.furnace.GuiContainerSuperFurnace;
import com.derf.btawc.client.gui.generators.GuiContainerCreativeGenerator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	
	// Super Furnace
	public final static int SUPER_FURNACE_GUI = 0;
	// Alloy Furnace
	public final static int ALLOY_FURNACE_GUI = 1;
	// Creative Generator
	public final static int CREATIVE_GENERATOR_GUI = 2;
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}

}
