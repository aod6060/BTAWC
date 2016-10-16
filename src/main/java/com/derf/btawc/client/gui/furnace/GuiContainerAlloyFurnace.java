package com.derf.btawc.client.gui.furnace;

import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;

import org.lwjgl.opengl.GL11;

import com.derf.btawc.Loader;
import com.derf.btawc.blocks.tileentity.furnace.TileEntityAlloyFurnace;
import com.derf.btawc.client.Color;
import com.derf.btawc.client.gui.GuiContainerBasic;
import com.derf.btawc.inventory.container.furnace.ContainerAlloyFurnace;
import com.derf.btawc.recipe.AlloyRecipe;
import com.derf.btawc.recipe.AlloyRecipeManager;
import com.derf.btawc.util.GuiRect;
import com.derf.btawc.util.OreDictionaryUtils;
import com.derf.btawc.util.Vec2;

import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiContainerAlloyFurnace extends GuiContainerBasic {
	private static final ResourceLocation alloyFurnaceGUI = new ResourceLocation(Loader.MODID + ":textures/gui/container/alloy_furnace_gui.png");
	private static final List<Entry<AlloyRecipe, ItemStack>> recipes = AlloyRecipeManager.toList();
	
	private final InventoryPlayer player;
	private TileEntityAlloyFurnace entity;
	
	private GuiRect recipeRect = new GuiRect(162, 4, 9, 9);
	private GuiRect leftRect;
	private GuiRect rightRect;
	
	private boolean toggleRecipe = false;
	
	private int index = 0;
	
	public GuiContainerAlloyFurnace(InventoryPlayer player, TileEntityAlloyFurnace entity) {
		super(new ContainerAlloyFurnace(player, entity));
		this.player = player;
		this.entity = entity;
		this.xSize = 175;
		this.ySize = 175;
		
		leftRect = new GuiRect(this.xSize + 21, 3, 9, 9);
		rightRect = new GuiRect(this.xSize + 47, 3, 9, 9);
	}

	
	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
		String s = this.entity.hasCustomName()? this.entity.getName() : this.getLangString(this.entity.getName());
		this.renderString(s, this.xSize / 2 - this.stringWidth(s) / 2, 6, Color.BLACK);
		String s2 = this.getLangString("container.inventory");
		this.renderFormatedString("container.inventory", this.xSize - (this.stringWidth(s2) + 9), 79, Color.BLACK);
		
		if(toggleRecipe) {
			
			this.renderString(
					recipes.get(index).getValue().getDisplayName(),
					this.xSize - 1, 
					50, 
					Color.BLACK);
		}
	}


	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		this.renderBackgroundImage(alloyFurnaceGUI);
		int k = this.getK();
		int l = this.getL();
		
		if(this.entity.isBurning()) {
			int i1 = this.entity.getBurnTimeRemainingScaled(15);
			this.drawTexturedModalRect(k + 26, l + 53 + 14 - i1, 175, 14 - i1, 14, i1 + 1);
			i1 = this.entity.getCookProgressScaled(24);
			this.drawTexturedModalRect(k + 78, l + 24, 175, 15, i1 + 1, 15);
		}
		
		this.renderRecipeGui(this.xSize + k, l);
	}
	
	private void renderRecipeGui(int gx, int gy) {
		if(toggleRecipe) {
			//GL11.glDisable(GL11.GL_LIGHTING);
			RenderHelper.disableStandardItemLighting();
			RenderHelper.enableGUIStandardItemLighting();
			
			this.drawTexturedModalRect(gx, gy, 0, 175, 80, 64);
			
			List<ItemStack> stacks = recipes.get(index).getKey().getInputs();
			
			for(int y = 0; y < 2; y++) {
				for(int x = 0; x < 2; x++) {
					if(stacks.get(y*2+x) != null) {
						ItemStack stack = stacks.get(y*2+x);
						this.renderItem(stack, x * 18 + 9 + gx, y * 18 + 17 + gy);
	
					}
				}
			}
			
			if(recipes.get(index).getValue() != null) {
				this.renderItem(recipes.get(index).getValue(), gx + 56, gy + 25);
			}
			RenderHelper.enableStandardItemLighting();
		}
	}
	private void renderItem(ItemStack stack, int x, int y) {
		//this.itemRender.renderItemIntoGUI(stack, x, y);
		GlStateManager.translate(0.0f, 0.0f, 32.0f);
		this.zLevel = 200.0f;
		this.itemRender.zLevel = 200.0f;
		this.itemRender.renderItemAndEffectIntoGUI(stack, x, y);
		this.itemRender.renderItemOverlays(this.fontRendererObj, stack, x, y);
		this.zLevel = 0.0f;
		this.itemRender.zLevel = 0.0f;
	}
	
	@Override
	protected void mouseClicked(int x, int y, int button) throws IOException {
		// TODO Auto-generated method stub
		super.mouseClicked(x, y, button);
		
		int k = this.getK();
		int l = this.getL();
		
		if(button == 0 || button == 1) {
			Vec2 mc = new Vec2(x - k, y - l);
			
			if(recipeRect.collide(mc)) {
				this.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
				toggleRecipe = !toggleRecipe;
			}
			
			if(toggleRecipe) {
				
				if(leftRect.collide(mc)) {
					// Do something
					this.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
					--this.index;
					if(this.index < 0) {
						this.index = recipes.size() - 1;
					}
				}
				
				if(rightRect.collide(mc)) {
					// Do something
					this.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
					++this.index;
					if(this.index > recipes.size() - 1) {
						this.index = 0;
					}
				}
			}
		}
	}
	
	
}
