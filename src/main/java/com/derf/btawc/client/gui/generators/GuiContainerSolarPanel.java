package com.derf.btawc.client.gui.generators;

import com.derf.btawc.Loader;
import com.derf.btawc.blocks.tileentity.generators.TileEntitySolarPanel;
import com.derf.btawc.client.Color;
import com.derf.btawc.client.gui.GuiContainerBasic;
import com.derf.btawc.inventory.container.generator.ContainerSolarPanel;
import com.derf.btawc.util.GuiRectArea;
import com.derf.btawc.util.Vec2;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiContainerSolarPanel extends GuiContainerBasic {

	private static final ResourceLocation solarPanelGui = new ResourceLocation(Loader.MODID + ":textures/gui/container/solar_panel_gui.png");
	
	private final InventoryPlayer player;
	private TileEntitySolarPanel generator;
	
	private Vec2 mouseCoord = new Vec2();
	
	GuiRectArea rect = new GuiRectArea(8, 16, 23, 55);
	
	public GuiContainerSolarPanel(InventoryPlayer player, TileEntitySolarPanel generator) {
		super(new ContainerSolarPanel(player, generator));
		this.player = player;
		this.generator = generator;
		this.xSize = 175;
		this.ySize = 175;
	}

	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		// TODO Auto-generated method stub
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		String s = this.getLangString("container.solar_panel");
		this.renderString(s, this.getMiddleOfScreenX(s), 6, Color.BLACK);
		this.renderFormatedString("container.inventory", 8, 80, Color.BLACK);
		
		if(rect.collide(mouseCoord)) {
			String info = generator.printEnergyValue();
			this.renderString(info, (int)this.mouseCoord.getX(), (int)this.mouseCoord.getY(), Color.DARK_GREY);
		}
	}


	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		this.renderBackgroundImage(solarPanelGui);
		this.renderEnergyStorageLevel(8, 16, 176, 0, generator.getEnergyLevelScaled(39));
		this.renderSun(80, 28, 176, 48);
	}
	
	protected void renderSun(int x, int y, int tx, int ty) {
		
		if(!this.generator.isLessThanZero()) {
			int k = this.getK();
			int l = this.getL();
			this.drawTexturedModalRect(x+k, y+l, tx, ty, 15, 15);
		}
	}
	
	protected void renderEnergyStorageLevel(int x, int y, int tx, int ty, int index) {
		// Update Energy delta [16, 40]
		int k = this.getK();
		int l = this.getL();
		int deltaY = ty + 39;
		this.drawTexturedModalRect(k + x, l + y + 39 - index, tx, deltaY - index, deltaY, index + 1);
	}
	
	@Override
	public void drawScreen(int x, int y, float ticks) {
		super.drawScreen(x, y, ticks);
		int k = this.getK();
		int l = this.getL();
		this.mouseCoord.setX(x - k);
		this.mouseCoord.setY(y - l);
	}
}
