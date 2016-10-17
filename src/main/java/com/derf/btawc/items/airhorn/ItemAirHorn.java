package com.derf.btawc.items.airhorn;

import com.derf.btawc.items.ItemBasic;
import com.derf.btawc.sound.SoundManager;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemAirHorn extends ItemBasic {

	public ItemAirHorn() {
		super("air_horn");
		this.setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(
			ItemStack stack, 
			World world, 
			EntityPlayer player,
			EnumHand hand) {
		
		BlockPos pos = player.getPosition();
		pos = pos.add(0.5, 0.5, 0.5);
		world.playSound(player, pos, SoundManager.air_horn, SoundCategory.BLOCKS, 1.0f, 1.0f);
		
		return super.onItemRightClick(stack, world, player, hand);
	}

	@Override
	public boolean isFull3D() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
