package com.derf.btawc.blocks.furnace;

import java.util.Random;

import com.derf.btawc.Loader;
import com.derf.btawc.blocks.BlockManager;
import com.derf.btawc.blocks.basic.BlockContainerBasic;
import com.derf.btawc.blocks.tileentity.furnace.TileEntitySuperFurnace;
import com.derf.btawc.client.gui.GuiHandler;

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

public class BlockSuperFurnace extends BlockContainerBasic {

	public static PropertyDirection FACING = BlockHorizontal.FACING;
	
	private Random rand = new Random();
	private static boolean keepInventory;
	
	
	private boolean on;
	
	public BlockSuperFurnace(int lightLevel, boolean on) {
		super("super_furnace", Material.ROCK, 2.0f, 2.0f, lightLevel, "pickaxe", 0, SoundType.STONE);
		this.on = on;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntitySuperFurnace();
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(BlockManager.superFurnace);
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(BlockManager.superFurnace);
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
			} else if(facing == EnumFacing.WEST && west.isFullBlock() && !west.isFullBlock()) {
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
			int meta, 
			EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	public void onBlockPlacedBy(
			World world, 
			BlockPos pos, 
			IBlockState state, 
			EntityLivingBase placer,
			ItemStack stack) {
		world.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()));
		
		if(stack.hasDisplayName()) {
			
			if(world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof TileEntitySuperFurnace) {
				TileEntitySuperFurnace entity = (TileEntitySuperFurnace)world.getTileEntity(pos);
				entity.setName(stack.getDisplayName());
			}
		}
	}
	
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		// Do something here :)
		if(!keepInventory) {
			TileEntity entity = world.getTileEntity(pos);
			if(entity instanceof TileEntitySuperFurnace) {
				// Commented it out until I 
				InventoryHelper.dropInventoryItems(world, pos, (TileEntitySuperFurnace)entity);
			}
		}
		super.breakBlock(world, pos, state);
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
			// Activate Gui for the player
			player.openGui(Loader.INSTANCE, GuiHandler.SUPER_FURNACE_GUI, world, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}

	public static void setSuperFurnaceState(boolean active, World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		TileEntity entity = world.getTileEntity(pos);
		keepInventory = true;
		if(active) {
			world.setBlockState(pos, BlockManager.superFurnaceOn.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
		} else {
			world.setBlockState(pos, BlockManager.superFurnace.getDefaultState().withProperty(FACING,  state.getValue(FACING)), 3);
		}
		keepInventory = false;
		if(entity != null) {
			entity.validate();
			world.setTileEntity(pos, entity);
		}
	}
}
