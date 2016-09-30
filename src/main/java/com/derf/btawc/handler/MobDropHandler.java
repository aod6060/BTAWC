package com.derf.btawc.handler;

import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MobDropHandler {
	
	@SubscribeEvent
	public void onLivingDeath(LivingDeathEvent e) {
	}
	
	@SubscribeEvent
	public void onEvent(LivingDropsEvent e) {
	}
}
