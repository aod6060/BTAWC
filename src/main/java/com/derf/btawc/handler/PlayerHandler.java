package com.derf.btawc.handler;

import com.derf.btawc.items.ItemsManager;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerHandler {
	
	@SubscribeEvent
	public void onEntityUpdateEvent(LivingUpdateEvent e) {
		
		
		if(e.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)e.getEntityLiving();
			
			ItemStack stack = grabFlowtationDevice(player);
			
			if(stack == null) {
				if(!player.capabilities.isCreativeMode) {
					
					if(player.capabilities.allowFlying) {
						player.capabilities.isFlying = false;
					}
					
					player.capabilities.allowFlying = false;
				}
			}
		}
	}

	private ItemStack grabFlowtationDevice(EntityPlayer player) {
		// TODO Auto-generated method stub
		ItemStack stack = null;
		
		InventoryPlayer inventory = player.inventory;
		
		
		for(int i = 0; i < inventory.getSizeInventory(); i++) {
			ItemStack temp = inventory.getStackInSlot(i);
			
			if(temp != null && temp.getItem() == ItemsManager.flowtationDevice) {
				stack = temp;
				break;
			}
		}
		
		return stack;
	}
}
