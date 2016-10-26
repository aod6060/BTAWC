package com.derf.btawc.blocks.tank;

import com.derf.btawc.blocks.basic.BlockContainerBasic;
import com.derf.btawc.tileentity.tank.TileEntityTank;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

public class BlockTank extends BlockContainerBasic {

	public BlockTank() {
		super("tank", Material.IRON, 2.0f, 2.0f, 0.0f, "pickaxe", 0, SoundType.METAL);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		// TODO Auto-generated method stub
		return new TileEntityTank();
	}
}
