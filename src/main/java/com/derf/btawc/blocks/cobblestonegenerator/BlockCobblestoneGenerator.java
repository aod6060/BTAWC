package com.derf.btawc.blocks.cobblestonegenerator;

import com.derf.btawc.blocks.basic.BlockContainerBasic;
import com.derf.btawc.tileentity.cobblestonegenerator.TileEntityCobblestoneGenerator;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class BlockCobblestoneGenerator extends BlockContainerBasic {

	public BlockCobblestoneGenerator() {
		super("cobblestone_generator", Material.ROCK, 2.0f, 2.0f, 0.0f, "pickaxe", 0, SoundType.STONE);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		// TODO Auto-generated method stub
		return new TileEntityCobblestoneGenerator();
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
		}
		
		return super.onBlockActivated(world, pos, state, player, hand, heldItem, side, hitX, hitY, hitZ);
	}
	
	
}
