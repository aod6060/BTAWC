package com.derf.btawc.inventory.container;

import com.derf.btawc.blocks.tileentity.chipmaker.TileEntityChipMaker;
import com.derf.btawc.util.FuelUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerChipMaker extends ContainerBasic {

	private static final int COOKING_TIME = 600;
	
	private final InventoryPlayer player;
	private TileEntityChipMaker entity;
	private int burnTime;
	private int currentItemBurnTime;
	private int cookTime;
	
	public ContainerChipMaker(InventoryPlayer player, TileEntityChipMaker entity) {
		this.player = player;
		this.entity = entity;
		// Create Material Slot 	[0] pos <17, 17>
		this.addSlotToContainer(new Slot(entity, 0, 17, 17));
		// Create Redstone Slot	 	[1] pos <41, 17>
		this.addSlotToContainer(new Slot(entity, 1, 41, 17) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				if(stack == null) {
					return false;
				}
				return stack.getItem() == Items.REDSTONE;
			}
			
		});
		// Create Output Slot   	[2] pos <136, 17>
		this.addSlotToContainer(new Slot(entity, 2, 136, 17) {

			@Override
			public boolean isItemValid(ItemStack stack) {
				// TODO Auto-generated method stub
				return false;
			}
			
		});
		// Create Fuel Slot     	[3] pos <28, 58>
		this.addSlotToContainer(new Slot(entity, 3, 28, 58) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				// TODO Auto-generated method stub
				return FuelUtils.isItemFuel(stack);
			}
		});
		// Create Player Inventory	[4-39] pos <8, 94> and <8, 152>
		this.createPlayerInventory(player, 8, 94, 8, 152);
		
	}
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		// TODO Auto-generated method stub
		return true;
	}

}
