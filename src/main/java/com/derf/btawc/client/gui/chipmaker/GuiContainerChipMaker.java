package com.derf.btawc.client.gui.chipmaker;

import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;

import org.lwjgl.opengl.GL11;

import com.derf.btawc.Loader;
import com.derf.btawc.client.Color;
import com.derf.btawc.client.gui.GuiContainerBasic;
import com.derf.btawc.inventory.container.chipmaker.ContainerChipMaker;
import com.derf.btawc.recipe.handler.ChipMakerRecipe;
import com.derf.btawc.recipe.handler.ChipMakerRecipeManager;
import com.derf.btawc.tileentity.chipmaker.TileEntityChipMaker;
import com.derf.btawc.util.GuiRectArea;
import com.derf.btawc.util.OreDictionaryUtils;
import com.derf.btawc.util.Vec2;

import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
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
	private int index = 0;
	private int material_loop = 0;
	private int redstone_loop = 0;
	
	private GuiRectArea recipe_button = new GuiRectArea(160, 8, 169, 16);
	private GuiRectArea recipe_left;
	private GuiRectArea recipe_right;
	
	public GuiContainerChipMaker(InventoryPlayer player, TileEntityChipMaker entity) {
		super(new ContainerChipMaker(player, entity));
		this.player = player;
		this.entity = entity;
		this.xSize = 176;
		this.ySize = 176;
		
		this.recipe_left = new GuiRectArea(this.xSize + 38, 3, this.xSize + 47, 12);
		this.recipe_right = new GuiRectArea(this.xSize + 72, 3, this.xSize + 81, 12);
	}

	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = this.entity.hasCustomName()? this.entity.getName() : this.getLangString(this.entity.getName());
		this.renderString(s, this.xSize / 2 - this.stringWidth(s) / 2, 6, Color.BLACK);
		String s2 = this.getLangString("container.inventory");
		this.renderFormatedString("container.inventory", 8, 79, Color.BLACK);
		
		if(toggleRecipe) {
			String n = recipes.get(index).getValue().getDisplayName();
			
			this.renderString(
					n,
					this.xSize + ((120 / 2) - this.stringWidth(n) / 2), 
					50, 
					Color.BLACK);
		}
		
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
		
		this.renderRecipeGui(this.xSize + k, l);
	}
	
	private void renderRecipeGui(int x, int y) {
		if(toggleRecipe) {
			this.mc.getTextureManager().bindTexture(chipMakerRecipeGui);
			this.drawTexturedModalRect(x, y, 0, 0, 120, 64);
			RenderHelper.disableStandardItemLighting();
			RenderHelper.enableGUIStandardItemLighting();
			// Grabbing Stuff
			Entry<ChipMakerRecipe, ItemStack> inputs = recipes.get(index);
			// Materials
			ItemStack material = inputs.getKey().getMaterial();
			// Redstone
			ItemStack redstone = inputs.getKey().getRedstone();
			// Result
			ItemStack result = inputs.getValue();
			// Ore Dictionary
			List<ItemStack> materials = OreDictionaryUtils.getItemsStacksFromItemStack(material);
			List<ItemStack> redstones = OreDictionaryUtils.getItemsStacksFromItemStack(redstone);
			this.renderItem(material, x + 9, y + 17);
			this.renderItem(redstone, x + 33, y + 17);
			this.renderItem(result, x + 95, y + 17);
			RenderHelper.enableStandardItemLighting();
		}
	}
	
	private void renderItem(ItemStack stack, int x, int y) {
		GlStateManager.translate(0.0f, 0.0f, 32.0f);
		this.zLevel = 200.0f;
		this.itemRender.zLevel = 200.0f;
		this.itemRender.renderItemAndEffectIntoGUI(stack, x, y);
		this.itemRender.renderItemOverlays(this.fontRendererObj, stack, x, y);
		this.zLevel = 0.0f;
		this.itemRender.zLevel = 0.0f;
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
				if(this.recipe_left.collide(mc)) {
					this.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
					--this.index;
					if(this.index < 0) {
						this.index = recipes.size() - 1;
					}
				}
				
				if(this.recipe_right.collide(mc)) {
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
