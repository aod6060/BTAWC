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
		// Aggressive Mobs
		// blaze
		addDrops(EntityBlaze.class, Items.BLAZE_POWDER, 3);
		addDrops(EntityBlaze.class, Items.BLAZE_ROD, 5);
		// Cave Spider
		addDrops(EntityCaveSpider.class, Items.STRING, 3);
		addDrops(EntityCaveSpider.class, Items.SPIDER_EYE, 5);
		// Creeper
		addDrops(EntityCreeper.class, Items.GUNPOWDER, 3);
		// Enderman
		addDrops(EntityEnderman.class, Items.ENDER_PEARL, 3);
		// Ghast
		addDrops(EntityGhast.class, Items.GHAST_TEAR, 3);
		// Iron Golumn
		addDrops(EntityIronGolem.class, Items.IRON_INGOT, 3);
		// Magma Cube
		addDrops(EntityMagmaCube.class, Items.MAGMA_CREAM, 3);
		// Zombie Pigman
		addDrops(EntityPigZombie.class, Items.ROTTEN_FLESH, 3);
		addDrops(EntityPigZombie.class, Items.GOLD_NUGGET, 5);
		// Skeleton + wither Skeleton
		addDrops(EntitySkeleton.class, Items.BONE, 3);
		addDrops(EntitySkeleton.class, new ItemStack(Items.SKULL, 1, 1), 10);
		// Slime
		addDrops(EntitySlime.class, Items.SLIME_BALL, 3);
		// Snowgolem
		addDrops(EntitySnowman.class, Items.SNOWBALL, 3);
		// Spider
		addDrops(EntitySpider.class, Items.STRING, 3);
		addDrops(EntitySpider.class, Items.SPIDER_EYE, 5);
		// Witch
		addDrops(EntityWitch.class, Items.GLOWSTONE_DUST, 5);
		addDrops(EntityWitch.class, Items.GUNPOWDER, 5);
		addDrops(EntityWitch.class, Items.GLASS_BOTTLE, 5);
		addDrops(EntityWitch.class, Items.REDSTONE, 5);
		addDrops(EntityWitch.class, Items.SPIDER_EYE, 5);
		addDrops(EntityWitch.class, Items.SUGAR, 5);
		addDrops(EntityWitch.class, Items.STICK, 3);
		// Zombie
		addDrops(EntityZombie.class, Items.ROTTEN_FLESH, 3);
		// Passive Mobs
		// Chicken
		addDrops(EntityChicken.class, Items.FEATHER, 3);
		addDrops(EntityChicken.class, Items.CHICKEN, 5);
		// Cow
		addDrops(EntityCow.class, Items.BEEF, 3);
		addDrops(EntityCow.class, Items.LEATHER, 5);
		// Horse
		addDrops(EntityHorse.class, Items.LEATHER, 3);
		addDrops(EntityHorse.class, Items.SADDLE, 50);
		addDrops(EntityHorse.class, Items.IRON_HORSE_ARMOR, 100);
		addDrops(EntityHorse.class, Items.GOLDEN_HORSE_ARMOR, 150);
		addDrops(EntityHorse.class, Items.DIAMOND_HORSE_ARMOR, 200);
		// Mooshroom
		addDrops(EntityMooshroom.class, Items.BEEF, 3);
		addDrops(EntityMooshroom.class, Blocks.RED_MUSHROOM, 3);
		addDrops(EntityMooshroom.class, Blocks.BROWN_MUSHROOM, 3);
		addDrops(EntityMooshroom.class, Items.LEATHER, 5);
		// Pig
		addDrops(EntityPig.class, Items.PORKCHOP, 3);
		addDrops(EntityPig.class, Items.SADDLE, 200);
		addDrops(EntityPig.class, Items.CARROT_ON_A_STICK, 200);
		// Sheep
		addDrops(EntitySheep.class, Blocks.WOOL, 5);
		addDrops(EntitySheep.class, Items.MUTTON, 3);
		// Villager
		addDrops(EntityVillager.class, Items.EMERALD, 3);
		// Bosses
		// Wither
		addDrops(EntityWither.class, new ItemStack(Items.SKULL, 1, 1), 3);
		addDrops(EntityWither.class, Items.NETHER_STAR, 5);
		// Ender Dragon
		addDrops(EntityDragon.class, Blocks.DRAGON_EGG, 5);
		addDrops(EntityDragonPart.class, Blocks.DRAGON_EGG, 5);
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
