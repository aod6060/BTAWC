package com.derf.btawc.items.tools;

import java.util.List;

import com.derf.btawc.Loader;
import com.derf.btawc.util.MobDropUtils;
import com.derf.btawc.util.MobDropUtils.Drops;
import com.google.common.collect.Multimap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ItemSwordOfGreed extends ItemSword {

	private int count;
	private float damage;
	
	public ItemSwordOfGreed(String name, int damage, int count, int max) {
		super(ToolMaterial.EMERALD);
		this.damage = damage;
		this.count = count;
		this.setMaxDamage(max);
		this.setUnlocalizedName(name);
		this.setTextureName(Loader.MODID + ":" + name);
	}
	
	@Override
    public float func_150931_i()
    {
        return damage;
    }
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		
		if(!player.worldObj.isRemote) {
			
			if(entity.isEntityAlive()) {
				// Do Item Drops
				List<Drops> drops = MobDropUtils.getDropFromEntity(entity);
				
				
				if(drops != null && drops.size() > 0) {
					for(Drops d : drops) {
						
						int chance = d.getChance();
						
						if(itemRand.nextInt(chance * 2) == 0) {
							ItemStack s = new ItemStack(d.getStack().getItem(), this.count, d.getStack().getItemDamage());
							EntityItem item = new EntityItem(player.worldObj, entity.posX, entity.posY, entity.posZ, s);
							player.worldObj.spawnEntityInWorld(item);
						}
						
					}
				}
			}
		}
		
		return super.onLeftClickEntity(stack, player, entity);
	}
	
	public int getCount() {
		return count;
	}
	
    /**
     * Gets a map of item attribute modifiers, used by ItemSword to increase hit damage.
     */
    public Multimap getItemAttributeModifiers()
    {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", (double)(this.damage + 4.0f), 0));
        return multimap;
    }
}