package com.derf.btawc.items;

import java.util.ArrayList;
import java.util.List;

import com.derf.btawc.util.Timer;

import net.minecraft.block.BlockBeetroot;
import net.minecraft.block.BlockCactus;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockReed;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemGrowthDevice extends ItemBasic {
	
	
	protected int size;
	protected Timer timer;
	
	public ItemGrowthDevice(String name, int size, int speed) {
		super(name);
		this.size = size;
		this.timer = new Timer(speed);
		this.setMaxStackSize(1);
	}

	@Override
	public EnumActionResult onItemUse(
			ItemStack stack, 
			EntityPlayer player,
			World world, 
			BlockPos pos,
			EnumHand hand, 
			EnumFacing facing,
			float hitX, 
			float hitY, 
			float hitZ) {
		
		EnumActionResult result = EnumActionResult.FAIL;
		
		if(!world.isRemote) {
			List<BlockPos> bp = this.getPosition(pos);
			List<IBlockState> states = this.getBlocksStates(world, bp);
			
			
			if(timer.isTime()) {
				for(int i = 0; i < states.size(); i++) {
					BlockPos p = bp.get(i);
					IBlockState state = states.get(i);
					
					if(state.getBlock() instanceof BlockCrops) {
						this.updateCrop(p, state, world);
					} else {
						if(state.getBlock() instanceof IGrowable) {
							IGrowable growable = (IGrowable)state.getBlock();
							if(growable.canGrow(world, p, state, false)) {
								growable.grow(world, itemRand, p, state);
							}
						}
					}
				}
				timer.reset();
			} else {
				timer.update();
			}
			
			result = EnumActionResult.SUCCESS;
		} else {
			result = EnumActionResult.SUCCESS;
		}

		return result;
	}
	
	
	private void updateCrop(BlockPos pos, IBlockState state, World world) {
		if(state.getBlock() instanceof BlockBeetroot) {
			int age = state.getValue(BlockBeetroot.BEETROOT_AGE).intValue();
			if(age < 3) {
				if(Item.itemRand.nextInt(2) == 0) {
					++age;
					world.setBlockState(pos, state.withProperty(BlockBeetroot.BEETROOT_AGE, age));
				}
			}
		} else {
			int age = state.getValue(BlockCrops.AGE).intValue();
			if(age < 7) {
				if(Item.itemRand.nextInt(2) == 0) {
					++age;
					world.setBlockState(pos, state.withProperty(BlockCrops.AGE, age));
				}
			}
		}
	}
	
	private List<BlockPos> getPosition(BlockPos pos) {
		List<BlockPos> list = new ArrayList<BlockPos>();
		this.createPosition(list, new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()));
		this.createPosition(list, pos);
		this.createPosition(list, new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ()));
		return list;
	}
	
	private void createPosition(List<BlockPos> list, BlockPos c) {
		float halfSize = size / 2;
		
		for(int z = 0; z < this.size; z++) {
			for(int x = 0; x < this.size; x++) {
				int nx = (int) ((x - halfSize) + c.getX());
				int nz = (int) ((z - halfSize) + c.getZ());
				list.add(new BlockPos(nx, c.getY(), nz));
			}
		}
	}
	
	private List<IBlockState> getBlocksStates(World world, List<BlockPos> pos) {
		List<IBlockState> temp = new ArrayList<IBlockState>();
		
		for(BlockPos p : pos) {
			temp.add(world.getBlockState(p));
		}
		
		return temp;
	}
}
