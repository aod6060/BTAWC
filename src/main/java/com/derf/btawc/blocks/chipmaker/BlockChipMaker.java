package com.derf.btawc.blocks.chipmaker;

import java.util.Random;

import com.derf.btawc.Loader;
import com.derf.btawc.blocks.BlockManager;
import com.derf.btawc.blocks.basic.BlockContainerBasic;
import com.derf.btawc.blocks.basic.BlockContainerMachineBasic;
import com.derf.btawc.blocks.tileentity.chipmaker.TileEntityChipMaker;
import com.derf.btawc.blocks.tileentity.furnace.TileEntityAlloyFurnace;
import com.derf.btawc.client.gui.GuiHandler;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockChipMaker extends BlockContainerMachineBasic {
	public static boolean keepInventory = false;
	private boolean on;
	
	public BlockChipMaker(float lightLevel, boolean on) {
		super("chip_maker", Material.ROCK, 2.0f, 2.0f, lightLevel, "pickaxe", 0, SoundType.STONE, BlockManager.chipMaker);
		this.on = on;
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityChipMaker();
	}
	
	@Override
	public void onCustomName(World world, BlockPos pos, ItemStack stack) {
		if(world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof TileEntityChipMaker) {
			// Set Custom Name
		}
	}

	@Override
	public void onOpenGui(World world, BlockPos pos, EntityPlayer player) {
		// Open Gui
	}
	
	@Override
	public void onBlockMined(World world, BlockPos pos, TileEntity entity) {
		if(!keepInventory) {
			if(entity instanceof TileEntityChipMaker) {
				// Drop Inventory
			}
		}
	}

	public static void setChipMakerState(boolean active, World worldObj, BlockPos pos) {
		
	}
}
