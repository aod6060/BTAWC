package com.derf.btawc.items.tools;

import java.util.List;

import com.derf.btawc.items.ItemBasic;
import com.derf.btawc.util.Utils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemHoeOfGreed extends ItemBasic {

	protected int size;
	protected int count;
	
	public ItemHoeOfGreed(String name, int maxDamage, int size, int count) {
		super(name);
		this.setMaxStackSize(1);
		this.setMaxDamage(maxDamage);
		this.size = size;
		this.count = count;
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
		EnumActionResult result = EnumActionResult.PASS;
		
		if(player.canPlayerEdit(pos, facing, stack)) {
			List<BlockPos> bps = Utils.getBlockPositions(pos, EnumFacing.UP, size);
			List<IBlockState> states = Utils.getStates(world, bps);
			
			for(int i = 0; i < states.size(); i++) {
				BlockPos p = bps.get(i);
				IBlockState state = states.get(i);
				Block block = state.getBlock();
				
				if(facing != EnumFacing.DOWN && world.isAirBlock(p.up())) {
					if(block == Blocks.GRASS || block == Blocks.GRASS_PATH) {
						this.setBlock(stack, player, world, p, Blocks.FARMLAND.getDefaultState());
					}
					
					if(block == Blocks.DIRT) {
						switch((BlockDirt.DirtType)state.getValue(BlockDirt.VARIANT)) {
						case DIRT:
							this.setBlock(stack, player, world, p, Blocks.FARMLAND.getDefaultState());
							break;
						case COARSE_DIRT:
							this.setBlock(stack, player, world, p, Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT));
							break;
						default:
							break;
							
						}
					}
				}
			}
			
			result = EnumActionResult.SUCCESS;
		}
		
		return result;
	}
	
	private void setBlock(ItemStack stack, EntityPlayer player, World world, BlockPos pos, IBlockState state) {
		world.playSound(player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0f, 1.0f);
		if(!world.isRemote) {
			world.setBlockState(pos, state);
			stack.damageItem(1, player);
		}
	}

	@Override
	public boolean onBlockDestroyed(
			ItemStack stack, 
			World world, 
			IBlockState state, 
			BlockPos pos,
			EntityLivingBase entityLiving) {
		
		if(state.getBlock() instanceof BlockCrops) {
			if(!world.isRemote) {
				for(int i = 0; i < this.count - 1; i++) {
					
					if(state.getValue(BlockCrops.AGE) == 7) {
						List<ItemStack> drops = state.getBlock().getDrops(world, pos, state, 0);
						for(ItemStack s : drops) {
							world.spawnEntityInWorld(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), s));
						}
					}
				}
			}
		}
		
		return super.onBlockDestroyed(stack, world, state, pos, entityLiving);
	}

	@Override
	public boolean isFull3D() {
		return true;
	}
}
