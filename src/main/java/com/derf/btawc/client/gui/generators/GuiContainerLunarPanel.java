package com.derf.btawc.client.gui.generators;

import java.util.ArrayList;
import java.util.List;

import com.derf.btawc.Loader;
import com.derf.btawc.client.Color;
import com.derf.btawc.client.gui.GuiContainerBasic;
import com.derf.btawc.inventory.container.generator.ContainerLunarPanel;
import com.derf.btawc.tileentity.generators.TileEntityLunarPanel;
import com.derf.btawc.util.GuiRectArea;
import com.derf.btawc.util.IVec2;
import com.derf.btawc.util.Vec2;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiContainerLunarPanel extends GuiContainerBasic {

	private static final ResourceLocation lunarPanelGui = new ResourceLocation(Loader.MODID + ":textures/gui/container/lunar_panel_gui.png");
	private final InventoryPlayer player;
	private TileEntityLunarPanel generator;
	
	private GuiRectArea rect = new GuiRectArea(8, 16, 23, 55);
	private Vec2 mc = new Vec2();
	
	public GuiContainerLunarPanel(InventoryPlayer player, TileEntityLunarPanel generator) {
		super(new ContainerLunarPanel(player, generator));
		this.player = player;
		this.generator = generator;
		this.xSize = 175;
		this.ySize = 175;
	}

	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		// TODO Auto-generated method stub
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		String s = this.getLangString("container.lunar_panel");
		this.renderString(s, this.getMiddleOfScreenX(s), 6, Color.BLACK);
		this.renderFormatedString("container.inventory", 8, 80, Color.BLACK);
		
		if(rect.collide(mc)) {
			String info = generator.printEnergyValue();
			this.renderString(info, (int)mc.getX(), (int)mc.getY(), Color.DARK_GREY);
		}
	}


	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		this.renderBackgroundImage(lunarPanelGui);
		this.renderEnergyStorageLevel(8, 16, 176, 0, generator.getEnergyLevelScaled(39));
		this.renderMoonPhases(76, 24);
	}
	
	private void renderMoonPhases(int x, int y) {
		
		if(this.generator.getEfficency() > 0) {
			int size = 20;
			List<IVec2> pos = new ArrayList<IVec2>();
			int index = 0;
			int k = this.getK();
			int l = this.getL();
			for(int i = 0; i < 8; i++) {
				pos.add(new IVec2(index, 176));
				index = i * 20;
			}
			int moonPhase = generator.getWorld().getMoonPhase();
			this.drawTexturedModalRect(x + k, y + l, pos.get(moonPhase).getX(), pos.get(moonPhase).getY(), size, size);
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
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		// TODO Auto-generated method stub
		super.drawScreen(mouseX, mouseY, partialTicks);
		int k = this.getK();
		int l = this.getL();
		this.mc.setX(mouseX - k);
		this.mc.setY(mouseY - l);
	}
	
	
}
