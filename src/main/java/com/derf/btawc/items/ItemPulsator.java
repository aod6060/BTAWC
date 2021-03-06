package com.derf.btawc.items;

import java.util.List;

import com.derf.btawc.util.Vec3;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class ItemPulsator extends ItemBasic {

	private double strength;
	private double range;
	
	public ItemPulsator(double strength, double range) {
		super("pulsator");
		this.strength = strength;
		this.range = range;
		this.setMaxStackSize(1);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(
			ItemStack stack,
			World world, 
			EntityPlayer player,
			EnumHand hand) {
		// TODO Auto-generated method stub
		
		if(!world.isRemote) {
			this.onPulsated(stack, world, player, EntityCreature.class);
			this.onPulsated(stack, world, player, EntitySlime.class);
			this.onPulsated(stack, world, player, EntityFlying.class);
			this.onPulsated(stack, world, player, EntityWither.class);
			this.onPulsated(stack, world, player, EntityDragon.class);
		}
		return super.onItemRightClick(stack, world, player, hand);
	}
	
	private void onPulsated(ItemStack stack, World world, EntityPlayer player, Class<? extends Entity> entity) {
		AxisAlignedBB box = this.createBox(player, this.range);
		List<Entity> list = this.getEntitesWithinAABB(world, entity, box);
		Vec3 v = new Vec3(player.posX, player.posY, player.posZ);
		for(Entity ent : list) {
			
			if(ent.isEntityAlive()) {
				Vec3 e = new Vec3(ent.posX, ent.posY, ent.posZ);
				Vec3 s = Vec3.sub(e, v);
				s = Vec3.unit(s);
				s = Vec3.mul(s, this.strength);
				ent.motionX += s.getX();
				ent.motionZ += s.getZ();
				ent.motionY += this.strength;
			}
		}
	}
	
	private AxisAlignedBB createBox(Entity player, double range) {
		double r = range * 0.5;
		AxisAlignedBB box = new AxisAlignedBB(
				player.posX - r,
				player.posY - r,
				player.posZ - r,
				player.posX + r,
				player.posY + r,
				player.posZ + r
		);
		
		return box;
	}
	
	private List<Entity> getEntitesWithinAABB(World world, Class clz, AxisAlignedBB box) {
		List<Entity> temp;
		temp = world.getEntitiesWithinAABB(clz, box);
		return temp;
	}
}
