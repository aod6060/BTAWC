package com.derf.btawc.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MobDropUtils {
	
	public static class Drops {
		private ItemStack stack;
		private int chance;
		
		public Drops(ItemStack stack, int chance) {
			this.stack = stack;
			this.chance = chance;
		}
		
		public ItemStack getStack() {
			return stack;
		}
		
		public int getChance() {
			return chance;
		}
		
		public static Drops create(ItemStack stack, int chance) {
			return new Drops(stack, chance);
		}
	}
	
	public static Map<Class<? extends Entity>, List<Drops>> drops = new HashMap<Class<? extends Entity>, List<Drops>>();
	
	public static List<Drops> getDropFromEntity(Entity entity) {
		return drops.get(entity.getClass());
	}
	
	public static void registerMobDrops() {
		/*
		// Aggressive Mobs
		// blaze
		addDrops(EntityBlaze.class, Items.blaze_powder, 3);
		addDrops(EntityBlaze.class, Items.blaze_rod, 5);
		// Cave Spider
		addDrops(EntityCaveSpider.class, Items.string, 3);
		addDrops(EntityCaveSpider.class, Items.spider_eye, 5);
		// Creeper
		addDrops(EntityCreeper.class, Items.gunpowder, 3);
		// Enderman
		addDrops(EntityEnderman.class, Items.ender_pearl, 3);
		// Ghast
		addDrops(EntityGhast.class, Items.ghast_tear, 3);
		// Iron Golumn
		addDrops(EntityIronGolem.class, Items.iron_ingot, 3);
		// Magma Cube
		addDrops(EntityMagmaCube.class, Items.magma_cream, 3);
		// Zombie Pigman
		addDrops(EntityPigZombie.class, Items.rotten_flesh, 3);
		addDrops(EntityPigZombie.class, Items.gold_nugget, 5);
		// Skeleton + wither Skeleton
		addDrops(EntitySkeleton.class, Items.bone, 3);
		addDrops(EntitySkeleton.class, new ItemStack(Items.skull, 1, 1), 10);
		// Slime
		addDrops(EntitySlime.class, Items.slime_ball, 3);
		// Snowgolem
		addDrops(EntitySnowman.class, Items.snowball, 3);
		// Spider
		addDrops(EntitySpider.class, Items.string, 3);
		addDrops(EntitySpider.class, Items.spider_eye, 5);
		// Witch
		addDrops(EntityWitch.class, Items.glowstone_dust, 5);
		addDrops(EntityWitch.class, Items.gunpowder, 5);
		addDrops(EntityWitch.class, Items.glass_bottle, 5);
		addDrops(EntityWitch.class, Items.redstone, 5);
		addDrops(EntityWitch.class, Items.spider_eye, 5);
		addDrops(EntityWitch.class, Items.sugar, 5);
		addDrops(EntityWitch.class, Items.stick, 3);
		// Zombie
		addDrops(EntityZombie.class, Items.rotten_flesh, 3);
		// Passive Mobs
		// Chicken
		addDrops(EntityChicken.class, Items.feather, 3);
		addDrops(EntityChicken.class, Items.chicken, 5);
		// Cow
		addDrops(EntityCow.class, Items.beef, 3);
		addDrops(EntityCow.class, Items.leather, 5);
		// Horse
		addDrops(EntityHorse.class, Items.leather, 3);
		addDrops(EntityHorse.class, Items.saddle, 5);
		addDrops(EntityHorse.class, Items.iron_horse_armor, 10);
		addDrops(EntityHorse.class, Items.golden_horse_armor, 15);
		addDrops(EntityHorse.class, Items.diamond_horse_armor, 20);
		// Mooshroom
		addDrops(EntityMooshroom.class, Items.beef, 3);
		addDrops(EntityMooshroom.class, Blocks.red_mushroom, 3);
		addDrops(EntityMooshroom.class, Blocks.brown_mushroom, 3);
		addDrops(EntityMooshroom.class, Items.leather, 5);
		// Pig
		addDrops(EntityPig.class, Items.porkchop, 3);
		addDrops(EntityPig.class, Items.saddle, 20);
		addDrops(EntityPig.class, Items.carrot_on_a_stick, 20);
		// Sheep
		addDrops(EntitySheep.class, Blocks.wool, 3);
		// Villager
		addDrops(EntityVillager.class, Items.emerald, 3);
		// Bosses
		// Wither
		addDrops(EntityWither.class, new ItemStack(Items.skull, 1, 1), 3);
		addDrops(EntityWither.class, Items.nether_star, 5);
		// Ender Dragon
		addDrops(EntityDragon.class, Blocks.dragon_egg, 5);
		addDrops(EntityDragonPart.class, Blocks.dragon_egg, 5);
		*/
	}
	
	public static void addDrops(Class<? extends Entity> clz, Block block, int chance) {
		addDrops(clz, Item.getItemFromBlock(block), chance);
	}
	
	public static void addDrops(Class<? extends Entity> clz, Item item, int chance) {
		addDrops(clz, new ItemStack(item, 1), chance);
	}
	
	public static void addDrops(Class<? extends Entity> clz, ItemStack stack, int chance) {
		if(!drops.containsKey(clz)) {
			drops.put(clz, new ArrayList<Drops>());
		}
		drops.get(clz).add(MobDropUtils.Drops.create(stack, chance));
	}
}
