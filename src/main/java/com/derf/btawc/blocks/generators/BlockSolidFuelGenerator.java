package com.derf.btawc.blocks.generators;

import com.derf.btawc.blocks.BlockManager;
import com.derf.btawc.blocks.basic.BlockContainerMachineBasic;
import com.derf.btawc.blocks.tileentity.generators.TileEntitySolidFuelGenerator;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
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
			
		}
	}

	@Override
	public void onOpenGui(World world, BlockPos pos, EntityPlayer player) {
	}

	@Override
	public void onBlockMined(World world, BlockPos pos, TileEntity entity) {
		if(!keepInventory) {
			if(entity instanceof TileEntitySolidFuelGenerator) {
				
			}
		}
	}

	public static void updateBlockState(boolean burning, World worldObj, BlockPos pos) {
	}

}
