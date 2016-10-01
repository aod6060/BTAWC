package com.derf.btawc.client.gui.generators;

import com.derf.btawc.Loader;
import com.derf.btawc.blocks.tileentity.generators.TileEntityCreativeGenerator;
import com.derf.btawc.client.Color;
import com.derf.btawc.client.gui.GuiContainerBasic;
import com.derf.btawc.inventory.ContainerCreativeGenerator;
import com.derf.btawc.util.GuiRect;
import com.derf.btawc.util.Vec2;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiContainerCreativeGenerator extends GuiContainerBasic {
	// Statics Resources
	private static final ResourceLocation creativeGenertorGUI = new ResourceLocation(Loader.MODID + ":textures/gui/container/creative_generator_gui.png");
	// Instance Variables
	private final InventoryPlayer player;
	private TileEntityCreativeGenerator generator;
	private Vec2 mouseCoord = new Vec2();
	private Vec2 screenMouseCoord = new Vec2();
	
	private GuiRect rect;
	
	public GuiContainerCreativeGenerator(InventoryPlayer player, TileEntityCreativeGenerator generator) {
		super(new ContainerCreativeGenerator(player, generator));
		this.player = player;
		this.generator = generator;
		this.xSize = 175;
		this.ySize = 175;
		this.rect = new GuiRect(15, 15, 15, 39);
	}

	
	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
		super.drawGuiContainerForegroundLayer(p_146979_1_, p_146979_2_);
		
		String s = this.getLangString("container.creative_generator");
		this.renderString(s, this.getMiddleOfScreenX(s), 6, Color.BLACK);
		this.renderFormatedString("container.inventory", 7, 79, Color.BLACK);
		int k = this.getK();
		int l = this.getL();
		
		if(rect.collide(mouseCoord)) {
			String info = generator.printEnergyValue();
			this.renderString(info, (int)this.mouseCoord.getX(), (int)this.mouseCoord.getY(), Color.DARK_GREY);
		}
	}


	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		this.renderBackgroundImage(creativeGenertorGUI);
		this.renderEnergyStorageLevel(15, 15);
	}
	
	protected void renderEnergyStorageLevel(int x, int y) {
		// Update Energy delta [16, 40]
		int k = this.getK();
		int l = this.getL();
		int deltaY = 40;
		int i = generator.getEnergyLevelScaled(40);
		this.drawTexturedModalRect(k + x, l + y + deltaY - i, 175, deltaY - i, deltaY, i + 1);
	}


	@Override
	public void drawScreen(int mx, int my, float f) {
		// TODO Auto-generated method stub
		super.drawScreen(mx, my, f);
		int k = this.getK();
		int l = this.getL();
		
		this.mouseCoord.setX(mx - k);
		this.mouseCoord.setY(my - l);
		
		this.screenMouseCoord.setX(mx);
		this.screenMouseCoord.setY(my);
	}
	
	
}