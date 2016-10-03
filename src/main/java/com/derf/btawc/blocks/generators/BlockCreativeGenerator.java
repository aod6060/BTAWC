package com.derf.btawc.blocks.generators;

import com.derf.btawc.Loader;
import com.derf.btawc.blocks.basic.BlockContainerBasic;
import com.derf.btawc.blocks.tileentity.generators.TileEntityCreativeGenerator;
import com.derf.btawc.client.gui.GuiHandler;
import com.derf.btawc.energy.IEnergyLevelPrintable;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCreativeGenerator extends BlockContainerBasic {
	
	public BlockCreativeGenerator() {
		super("creative_generator", Material.IRON, 2.0f, 2.0f, 0, "pickaxe", 0, SoundType.METAL);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityCreativeGenerator();
	}

	@Override
	public boolean onBlockActivated(
			World world, 
			BlockPos pos, 
			IBlockState state, 
			EntityPlayer player,
			EnumHand hand, 
			ItemStack heldItem,
			EnumFacing side, 
			float hitX, 
			float hitY,
			float hitZ) {
		
		if(!world.isRemote) {
			player.openGui(Loader.INSTANCE, GuiHandler.CREATIVE_GENERATOR_GUI, world, pos.getX(), pos.getY(), pos.getZ());
		}
		
		return true;
	}
	
}
