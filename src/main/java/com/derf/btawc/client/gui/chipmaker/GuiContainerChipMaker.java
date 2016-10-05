package com.derf.btawc.client.gui.chipmaker;

import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;

import com.derf.btawc.Loader;
import com.derf.btawc.blocks.tileentity.chipmaker.TileEntityChipMaker;
import com.derf.btawc.client.gui.GuiContainerBasic;
import com.derf.btawc.inventory.container.ContainerChipMaker;
import com.derf.btawc.recipe.ChipMakerRecipe;
import com.derf.btawc.recipe.ChipMakerRecipeManager;
import com.derf.btawc.util.GuiRectArea;
import com.derf.btawc.util.Vec2;

import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiContainerChipMaker extends GuiContainerBasic {
	private static final ResourceLocation chipMakerMainGui = new ResourceLocation(Loader.MODID + ":textures/gui/container/chip_maker_main_gui.png");
	private static final ResourceLocation chipMakerRecipeGui = new ResourceLocation(Loader.MODID + ":textures/gui/container/chip_maker_recipe_gui.png");
	
	private static final List<Entry<ChipMakerRecipe, ItemStack>> recipes = ChipMakerRecipeManager.toList();
	
	private final InventoryPlayer player;
	private TileEntityChipMaker entity;
	
	private boolean toggleRecipe = false;
	
	private GuiRectArea recipe_button = new GuiRectArea(160, 8, 169, 16);
	
	public GuiContainerChipMaker(InventoryPlayer player, TileEntityChipMaker entity) {
		super(new ContainerChipMaker(player, entity));
		this.player = player;
		this.entity = entity;
		this.xSize = 176;
		this.ySize = 176;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		// TODO Auto-generated method stub
		this.renderBackgroundImage(chipMakerMainGui);
		int k = this.getK();
		int l = this.getL();
		
		if(this.entity.isBurning()) {
			int i1 = this.entity.getBuringTimeRemainingScaled(14);
			this.drawTexturedModalRect(k + 28, l + 41 + 15 - i1, 176, 14 - i1, 14, i1 + 1);
			i1 = this.entity.getCookProgressScaled(31);
			this.drawTexturedModalRect(k + 72, l + 16, 176, 16, i1 + 1, 16);
		}
		
		this.renderRecipeGue(this.xSize + k, l);
	}
	
	private void renderRecipeGue(int x, int y) {
		if(toggleRecipe) {
			this.mc.getTextureManager().bindTexture(chipMakerRecipeGui);
			this.drawTexturedModalRect(x, y, 0, 0, 120, 64);
		}
	}

	@Override
	protected void mouseClicked(int x, int y, int mouseButton) throws IOException {
		// TODO Auto-generated method stub
		super.mouseClicked(x, y, mouseButton);
		
		int k = this.getK();
		int l = this.getL();
		
		if(mouseButton == 0 || mouseButton == 1) {
			Vec2 mc = new Vec2(x - k, y - l);
			
			
			if(this.recipe_button.collide(mc)) {
				this.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
				this.toggleRecipe = !this.toggleRecipe;
			}
			
			if(toggleRecipe) {
				// Do stuff with the other menu
			}
		}
	}
	
	
}
