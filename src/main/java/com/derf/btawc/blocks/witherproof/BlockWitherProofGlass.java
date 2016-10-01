package com.derf.btawc.blocks.witherproof;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockWitherProofGlass extends BlockWitherProof {

	public BlockWitherProofGlass(String name, float lightlevel, SoundType type) {
		super(name, lightlevel, type);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shouldSideBeRendered(
			IBlockState blockState, 
			IBlockAccess blockAccess, 
			BlockPos pos,
			EnumFacing side) {
		
		IBlockState state = blockAccess.getBlockState(pos.offset(side));
		Block block = state.getBlock();
		
		if(blockState != state) {
			return true;
		}
		
		if(block == this) {
			return false;
		}
		
		return block == this ? false : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
	}
	
	
}
