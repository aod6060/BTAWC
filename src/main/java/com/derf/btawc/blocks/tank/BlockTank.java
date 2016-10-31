package com.derf.btawc.blocks.tank;

import com.derf.btawc.Loader;
import com.derf.btawc.blocks.basic.BlockContainerBasic;
import com.derf.btawc.client.gui.GuiHandler;
import com.derf.btawc.tileentity.tank.TileEntityTank;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class BlockTank extends BlockContainerBasic {

	public BlockTank() {
		super("tank", Material.IRON, 2.0f, 2.0f, 0.0f, "pickaxe", 0, SoundType.GLASS);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		// TODO Auto-generated method stub
		return new TileEntityTank();
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntity entity = world.getTileEntity(pos);
		if(entity instanceof IInventory) {
			InventoryHelper.dropInventoryItems(world, pos, (IInventory)entity);	
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
				// Open Gui
				player.openGui(Loader.INSTANCE, GuiHandler.TANK, world, pos.getX(), pos.getY(), pos.getZ());
			}
		}
		return true;
	}
	
	
}
