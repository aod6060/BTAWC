package com.derf.btawc.blocks.furnace;

import java.util.Random;

import com.derf.btawc.Loader;
import com.derf.btawc.blocks.BlockManager;
import com.derf.btawc.blocks.basic.BlockContainerMachineBasic;
import com.derf.btawc.client.gui.GuiHandler;
import com.derf.btawc.tileentity.furnace.TileEntityAlloyFurnace;

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

public class BlockAlloyFurnace extends BlockContainerMachineBasic {
	private static boolean keepInventory = false;
	private boolean on;
	
	public BlockAlloyFurnace(int lightLevel, boolean on) {
		super("alloy_furnace", Material.ROCK, 2.0f, 2.0f, lightLevel, "pickaxe", 0, SoundType.STONE, BlockManager.alloyFurnace);
		this.on = on;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityAlloyFurnace();
	}
	
	@Override
	public void onCustomName(World world, BlockPos pos, ItemStack stack) {
		if(world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof TileEntityAlloyFurnace) {
			TileEntityAlloyFurnace entity = (TileEntityAlloyFurnace) world.getTileEntity(pos);
			entity.setName(stack.getDisplayName());
		}
	}

	@Override
	public void onOpenGui(World world, BlockPos pos, EntityPlayer player) {
		player.openGui(Loader.INSTANCE, GuiHandler.ALLOY_FURNACE_GUI, world, pos.getX(), pos.getY(), pos.getZ());
	}
	
	@Override
	public void onBlockMined(World world, BlockPos pos, TileEntity entity) {
		if(!keepInventory) {
			if(entity instanceof TileEntityAlloyFurnace) {
				InventoryHelper.dropInventoryItems(world, pos, (TileEntityAlloyFurnace)entity);
			}
		}
	}
	
	public static void setAlloyFurnaceState(boolean active, World world, BlockPos pos) {
		// Work on this after I get done with the tileentity...
		IBlockState state = world.getBlockState(pos);
		TileEntity entity = world.getTileEntity(pos);
		keepInventory = true;
		if(active) {
			world.setBlockState(pos, BlockManager.alloyFurnaceOn.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
		} else {
			world.setBlockState(pos, BlockManager.alloyFurnace.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
		}
		keepInventory = false;
		if(entity != null) {
			entity.validate();
			world.setTileEntity(pos, entity);
		}
	}
}
