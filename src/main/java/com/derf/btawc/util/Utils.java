package com.derf.btawc.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * This class contains useful function that can be applied anywhere...
 * @author Fred
 *
 */
public final class Utils {
	public static Random rand = new Random();
	
	public static boolean binaryNoise() {
		return rand.nextBoolean();
	}
	
	public static boolean noise(int max) {
		return rand.nextInt(max) == 0;
	}
	
	public static List<BlockPos> getBlockPositions(BlockPos center, EnumFacing facing, int size) {
		List<BlockPos> temp = new ArrayList<BlockPos>();
		
		if(facing == EnumFacing.UP) {
			getBlockPosTB(temp, center, size);
		} else if(facing == EnumFacing.DOWN) {
			getBlockPosTB(temp, center, size);
		} else if(facing == EnumFacing.NORTH) {
			getBlockPosNS(temp, center, size);
		} else if(facing == EnumFacing.SOUTH) {
			getBlockPosNS(temp, center, size);
		} else if(facing == EnumFacing.WEST) {
			getBlockPosEW(temp, center, size);
		} else if(facing == EnumFacing.EAST) {
			getBlockPosEW(temp, center, size);
		}
		
		return temp;
	}
	
	private static void getBlockPosEW(List<BlockPos> temp, BlockPos center, int size) {
		float halfSize = size / 2;
		// YZ Axis
		for(int z = 0; z < size; z++) {
			for(int y = 0; y < size; y++) {
				int ny = (int)((y - halfSize) + center.getY());
				int nz = (int)((z - halfSize) + center.getZ());
				temp.add(new BlockPos(center.getX(), ny, nz));
			}
		}
	}

	private static void getBlockPosNS(List<BlockPos> temp, BlockPos center, int size) {
		float halfSize = size / 2;
		
		// XY Axis
		
		for(int y = 0; y < size; y++) {
			for(int x = 0; x < size; x++) {
				int nx = (int)((x-halfSize) + center.getX());
				int ny = (int)((y-halfSize) + center.getY());
				temp.add(new BlockPos(nx, ny, center.getZ()));
			}
		}
	}

	private static void getBlockPosTB(List<BlockPos> bps, BlockPos center, int size) {
		float halfSize = size / 2;
		
		for(int z = 0; z < size; z++) {
			for(int x = 0; x < size; x++) {
				int nx = (int)((x - halfSize) + center.getX());
				int nz = (int)((z - halfSize) + center.getZ());
				bps.add(new BlockPos(nx, center.getY(), nz));
			}
		}
	}
	
	public static List<IBlockState> getStates(World world, List<BlockPos> bps) {
		List<IBlockState> states = new ArrayList<IBlockState>();
		
		for(BlockPos p : bps) {
			states.add(world.getBlockState(p));
		}
		
		return states;
	}
}
