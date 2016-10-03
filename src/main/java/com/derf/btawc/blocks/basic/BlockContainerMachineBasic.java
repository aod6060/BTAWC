package com.derf.btawc.blocks.basic;

import java.util.Random;

import com.derf.btawc.Loader;
import com.derf.btawc.blocks.BlockManager;
import com.derf.btawc.blocks.tileentity.furnace.TileEntityAlloyFurnace;
import com.derf.btawc.client.gui.GuiHandler;

import net.minecraft.block.Block;
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

public abstract class BlockContainerMachineBasic extends BlockContainerBasic {
	
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	protected Block drop = null;
	
	public BlockContainerMachineBasic(
			String name,
			Material material, 
			float hardness, 
			float resistance,
			float lightLevel, 
			String toolClass, 
			int level, 
			SoundType sound,
			Block drop) {
		
		super(name, material, hardness, resistance, lightLevel, toolClass, level, sound);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.drop = drop;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		// TODO Auto-generated method stub
		return Item.getItemFromBlock(drop);
	}

	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		// TODO Auto-generated method stub
		return new ItemStack(drop);
	}
	
	@Override
	public void onBlockAdded(
			World world, 
			BlockPos pos, 
			IBlockState state) {
		
		if(!world.isRemote) {
			IBlockState north = world.getBlockState(pos.north());
			IBlockState south = world.getBlockState(pos.south());
			IBlockState west = world.getBlockState(pos.west());
			IBlockState east = world.getBlockState(pos.east());
			EnumFacing facing = (EnumFacing)state.getValue(FACING);
			
			if(facing == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock()) {
				facing = EnumFacing.SOUTH;
			} else if(facing == EnumFacing.SOUTH && south.isFullBlock() && !north.isFullBlock()) {
				facing = EnumFacing.NORTH;
			} else if(facing == EnumFacing.WEST && west.isFullBlock() && !east.isFullBlock()) {
				facing = EnumFacing.EAST;
			} else if(facing == EnumFacing.EAST && east.isFullBlock() && !west.isFullBlock()) {
				facing = EnumFacing.WEST;
			}
			
			world.setBlockState(pos, state.withProperty(FACING, facing), 2);
		}
	}
	
	@Override
	public IBlockState onBlockPlaced(
			World world, 
			BlockPos pos,
			EnumFacing facing, 
			float hitX, 
			float hitY, 
			float hitZ,
			int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}
	
	@Override
	public void onBlockPlacedBy(
			World world, 
			BlockPos pos, 
			IBlockState state, 
			EntityLivingBase placer,
			ItemStack stack) {
		
		world.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
		
		if(stack.hasDisplayName()) {
			onCustomName(world, pos, stack);
		}
	}
	
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing facing = EnumFacing.getFront(meta);
		
		if(facing.getAxis() == EnumFacing.Axis.Y) {
			facing = EnumFacing.NORTH;
		}
		
		return this.getDefaultState().withProperty(FACING, facing);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing)state.getValue(FACING)).getIndex();
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirror) {
		return state.withRotation(mirror.toRotation((EnumFacing)state.getValue(FACING)));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {FACING});
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
			onOpenGui(world, pos, player);
		}
		
		return true;
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		// Do something here :)
		TileEntity entity = world.getTileEntity(pos);
		
		onBlockMined(world, pos, entity);
		
		super.breakBlock(world, pos, state);
	}
	
	// Abstract Methods that need to be implemented
	public abstract void onCustomName(World world, BlockPos pos, ItemStack stack);
	
	public abstract void onOpenGui(World world, BlockPos pos, EntityPlayer player);
	
	public abstract void onBlockMined(World world, BlockPos pos, TileEntity entity);
	
}
