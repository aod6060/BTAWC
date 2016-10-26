package com.derf.btawc.blocks.generators;

import com.derf.btawc.Loader;
import com.derf.btawc.blocks.BlockManager;
import com.derf.btawc.blocks.basic.BlockContainerMachineBasic;
import com.derf.btawc.client.gui.GuiHandler;
import com.derf.btawc.tileentity.generators.TileEntitySolidFuelGenerator;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockSolidFuelGenerator extends BlockContainerMachineBasic {
	private static boolean keepInventory = false;
	private boolean on = false;
	
	public BlockSolidFuelGenerator(float lightLevel, boolean on) {
		super("solid_fuel_generator", Material.IRON, 2.0f, 2.0f, lightLevel, "pickaxe", 0, SoundType.METAL, BlockManager.solidFuelGenerator);
		this.on = on;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntitySolidFuelGenerator();
	}

	@Override
	public void onCustomName(World world, BlockPos pos, ItemStack stack) {
		if(world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof TileEntitySolidFuelGenerator) {
			TileEntitySolidFuelGenerator entity = (TileEntitySolidFuelGenerator)world.getTileEntity(pos);
			entity.setName(stack.getDisplayName());
		}
	}

	@Override
	public void onOpenGui(World world, BlockPos pos, EntityPlayer player) {
		player.openGui(Loader.INSTANCE, GuiHandler.SOLID_FUEL_GENERATOR_GUI, world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public void onBlockMined(World world, BlockPos pos, TileEntity entity) {
		if(!keepInventory) {
			if(entity instanceof TileEntitySolidFuelGenerator) {
				InventoryHelper.dropInventoryItems(world, pos, (TileEntitySolidFuelGenerator)entity);
			}
		}
	}

	public static void updateBlockState(boolean active, World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		TileEntity entity = world.getTileEntity(pos);
		keepInventory = true;
		if(active) {
			world.setBlockState(pos, BlockManager.solidFuelGeneratorOn.getDefaultState().withProperty(FACING, state.getValue(FACING)));
		} else {
			world.setBlockState(pos, BlockManager.solidFuelGenerator.getDefaultState().withProperty(FACING, state.getValue(FACING)));
		}
		keepInventory = false;
		if(entity != null) {
			entity.validate();
			world.setTileEntity(pos, entity);
		}
	}

}
