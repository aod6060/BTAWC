package com.derf.btawc.client.gui;

import com.derf.btawc.blocks.inventory.container.ContainerSuperFurnace;
import com.derf.btawc.blocks.tileentity.TileEntitySuperFurnace;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
	
	public final static int SUPER_FURNACE_GUI = 0;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID) {
		case 0:
			return new ContainerSuperFurnace(player.inventory, (TileEntitySuperFurnace) world.getTileEntity(x, y, z));
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID) {
		case 0:
			return new GuiContainerSuperFurnace(player.inventory, (TileEntitySuperFurnace)world.getTileEntity(x, y, z));
		}
		
		return null;
	}

}
