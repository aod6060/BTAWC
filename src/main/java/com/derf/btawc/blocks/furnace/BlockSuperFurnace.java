package com.derf.btawc.blocks.furnace;

import java.util.Random;

import com.derf.btawc.Loader;
import com.derf.btawc.blocks.BlockManager;
import com.derf.btawc.blocks.basic.BlockContainerMachineBasic;
import com.derf.btawc.blocks.tileentity.furnace.TileEntitySuperFurnace;
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

public class BlockSuperFurnace extends BlockContainerMachineBasic {
	private static boolean keepInventory;
	
	private boolean on;
	
	public BlockSuperFurnace(int lightLevel, boolean on) {
		super("super_furnace", Material.ROCK, 2.0f, 2.0f, lightLevel, "pickaxe", 0, SoundType.STONE, BlockManager.superFurnace);
		this.on = on;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntitySuperFurnace();
	}

	@Override
	public void onCustomName(World world, BlockPos pos, ItemStack stack) {
		if(world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof TileEntitySuperFurnace) {
			TileEntitySuperFurnace entity = (TileEntitySuperFurnace)world.getTileEntity(pos);
			entity.setName(stack.getDisplayName());
		}
	}

	@Override
	public void onOpenGui(World world, BlockPos pos, EntityPlayer player) {
		player.openGui(Loader.INSTANCE, GuiHandler.SUPER_FURNACE_GUI, world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public void onBlockMined(World world, BlockPos pos, TileEntity entity) {
		// Do something here :)
		if(!keepInventory) {
			if(entity instanceof TileEntitySuperFurnace) {
				// Commented it out until I 
				InventoryHelper.dropInventoryItems(world, pos, (TileEntitySuperFurnace)entity);
			}
		}
	}
	
	public static void setSuperFurnaceState(boolean active, World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		TileEntity entity = world.getTileEntity(pos);
		keepInventory = true;
		if(active) {
			world.setBlockState(pos, BlockManager.superFurnaceOn.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
		} else {
			world.setBlockState(pos, BlockManager.superFurnace.getDefaultState().withProperty(FACING,  state.getValue(FACING)), 3);
		}
		keepInventory = false;
		if(entity != null) {
			entity.validate();
			world.setTileEntity(pos, entity);
		}
	}
}
