package com.derf.btawc.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemFlowtationDevice extends ItemBasic {
	
	public ItemFlowtationDevice() {
		super("flowtation_device");
		this.setMaxStackSize(1);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(
			ItemStack stack, 
			World world, 
			EntityPlayer player,
			EnumHand hand) {
		if(stack.getTagCompound() == null) {
			NBTTagCompound tag = new NBTTagCompound();
			tag.setBoolean("toggle", false);
			stack.setTagCompound(tag);
		}
		
		if(!player.isSneaking()) {
			this.toggle(stack);
		}
		
		if(!world.isRemote) {
			String s = String.format("%s: is %s", stack.getDisplayName(), ((isToggle(stack))? "on" : "off"));
			player.addChatMessage(new TextComponentString(s));
		}
		
		return super.onItemRightClick(stack, world, player, hand);
	}


	private void onFlied(ItemStack stack, EntityPlayer player, boolean toggle) {
		if(!player.capabilities.isCreativeMode) {
			player.capabilities.allowFlying = toggle;
			
			if(player.capabilities.isFlying && !toggle) {
				player.capabilities.isFlying = false;
			}
		}
	}

	
	@Override
	public void onUpdate(
			ItemStack stack, 
			World world, 
			Entity entity, 
			int meta,
			boolean b) {
		// TODO Auto-generated method stub
		super.onUpdate(stack, world, entity, meta, b);
		
		if(stack.getTagCompound() == null) {
			NBTTagCompound tag = new NBTTagCompound();
			tag.setBoolean("toggle", false);
			stack.setTagCompound(tag);
		}
		
		
		
		if(!this.checkIfFlowtationDeviceExists(stack, (EntityPlayer)entity)) {
			if(entity instanceof EntityPlayer) {
				onFlied(stack, (EntityPlayer)entity, this.isToggle(stack));
			}
		}
		
	}
	
	private void toggle(ItemStack stack) {
		this.setToggle(stack, !this.isToggle(stack));
	}
	
	private boolean isToggle(ItemStack stack) {
		return stack.getTagCompound().getBoolean("toggle");
	}
	
	private void setToggle(ItemStack stack, boolean value) {
		stack.getTagCompound().setBoolean("toggle", value);
	}
	
	private boolean checkIfFlowtationDeviceExists(ItemStack stack, EntityPlayer player) {
		boolean b = false;
		InventoryPlayer inventory = player.inventory;
		
		for(int i = 0; i < inventory.getSizeInventory(); i++) {
			ItemStack item = inventory.getStackInSlot(i);
			
			if(item != null) {
				if(item == stack) {
					break;
				}
				
				if(item.getItem() == stack.getItem()) {
					b = true;
					break;
				}
			}
		}
		
		return b;
		
	}
}
