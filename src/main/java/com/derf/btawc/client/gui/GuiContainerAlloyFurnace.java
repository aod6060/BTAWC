package com.derf.btawc.client.gui;

import org.lwjgl.opengl.GL11;

import com.derf.btawc.Loader;
import com.derf.btawc.blocks.inventory.container.ContainerAlloyFurnace;
import com.derf.btawc.blocks.tileentity.TileEntityAlloyFurnace;
import com.derf.btawc.client.Color;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiContainerAlloyFurnace extends GuiContainer {
	private static final ResourceLocation alloyFurnaceGUI = new ResourceLocation(Loader.MODID + ":textures/gui/container/alloy_furnace_gui.png");
	private final InventoryPlayer player;
	private TileEntityAlloyFurnace entity;
	
	public GuiContainerAlloyFurnace(InventoryPlayer player, TileEntityAlloyFurnace entity) {
		super(new ContainerAlloyFurnace(player, entity));
		this.player = player;
		this.entity = entity;
		this.xSize = 175;
		this.ySize = 175;
	}

	
	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
		String s = this.entity.hasCustomInventoryName()? this.entity.getInventoryName() : I18n.format(this.entity.getInventoryName(), new Object[0]);
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s)/2, 6, Color.BLACK.toColor16());
		String s2 = I18n.format("container.inventory", new Object[0]);
		this.fontRendererObj.drawString(s2, this.xSize - (this.fontRendererObj.getStringWidth(s2) + 9), 79, Color.BLACK.toColor16());
	}


	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(alloyFurnaceGUI);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		
		if(this.entity.isBurning()) {
			int i1 = this.entity.getBurnTimeRemainingScaled(17);
			this.drawTexturedModalRect(k + 26, l + 53 + 14 - i1, 175, 14 - i1, 14, i1 + 1);
			i1 = this.entity.getCookProgressScaled(24);
			this.drawTexturedModalRect(k + 78, l + 24, 175, 15, i1 + 1, 15);
		}
	}

	
}
