package com.derf.btawc.blocks.itembuffer;

import java.util.Collection;

import com.derf.btawc.Loader;
import com.derf.btawc.blocks.basic.BlockContainerBasic;
import com.derf.btawc.client.gui.GuiHandler;
import com.derf.btawc.tileentity.EnumSixSided;
import com.derf.btawc.tileentity.ISixSided;
import com.derf.btawc.tileentity.itembuffer.TileEntityItemBuffer;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockItemBuffer extends BlockContainerBasic {
	
	public BlockItemBuffer() {
		super("item_buffer", Material.ROCK, 2.0f, 2.0f, 0.0f, "pickaxe", 0, SoundType.STONE);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		// TODO Auto-generated method stub
		return new TileEntityItemBuffer();
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		// TODO Auto-generated method stub
		
		TileEntity entity = world.getTileEntity(pos);
		
		if(entity instanceof TileEntityItemBuffer) {
			InventoryHelper.dropInventoryItems(world, pos, (TileEntityItemBuffer)entity);
		}
		super.breakBlock(world, pos, state);
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
			
			if(player.isSneaking()) {
				player.addChatMessage(new TextComponentString("Side: "+side));
			} else {
				player.openGui(Loader.INSTANCE, GuiHandler.ITEM_BUFFER_GUI, world, pos.getX(), pos.getY(), pos.getZ());
			}
		}
		
		return true;
	}

	/*
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		IBlockState temp = state;
		
		TileEntity entity = world.getTileEntity(pos);
		
		if(entity instanceof ISixSidedInventory) {
			ISixSidedInventory i = (ISixSidedInventory)entity;
			temp = temp.withProperty(WEST, i.getType(EnumFacing.WEST))
				   .withProperty(EAST,  i.getType(EnumFacing.EAST))
				   .withProperty(NORTH, i.getType(EnumFacing.NORTH))
				   .withProperty(SOUTH,  i.getType(EnumFacing.SOUTH))
				   .withProperty(UP, i.getType(EnumFacing.UP))
				   .withProperty(DOWN, i.getType(EnumFacing.DOWN));
		}
		
		return temp;
	}

	
	@Override
	protected BlockStateContainer createBlockState() {
		// TODO Auto-generated method stub
		return new BlockStateContainer(this, new IProperty[] {WEST, EAST, NORTH, SOUTH, UP, DOWN});
	}
	
	public static class PropertySixSidedType extends PropertyEnum<EnumSixSidedType> {
		// Construactor
		protected PropertySixSidedType(String name, Collection<EnumSixSidedType> allowedValues) {
			super(name, EnumSixSidedType.class, allowedValues);
			// TODO Auto-generated constructor stub
		}
		
		public static PropertySixSidedType create(String name, Collection<EnumSixSidedType> values) {
			return new PropertySixSidedType(name, values);
		}
		
		public static PropertySixSidedType create(String name, Predicate<EnumSixSidedType> filter) {
			return create(name, Collections2.<EnumSixSidedType>filter(Lists.newArrayList(EnumSixSidedType.values()), filter));
		}
		
		public static PropertySixSidedType create(String name) {
			return create(name, Predicates.<EnumSixSidedType>alwaysTrue());
		}
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		// TODO Auto-generated method stub
		return this.getDefaultState();
	}
	
	*/
	
}
