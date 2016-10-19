package com.derf.btawc.client.gui.generators;

import com.derf.btawc.Loader;
import com.derf.btawc.blocks.tileentity.generators.TileEntitySolidFuelGenerator;
import com.derf.btawc.client.Color;
import com.derf.btawc.client.gui.GuiContainerBasic;
import com.derf.btawc.inventory.container.generator.ContainerSolidFuelGenerator;
import com.derf.btawc.util.GuiRectArea;
import com.derf.btawc.util.Vec2;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiContainerSolidFuelGenerator extends GuiContainerBasic {

	private static final ResourceLocation solidFuelGeneratorGui = new ResourceLocation(Loader.MODID + ":textures/gui/container/solid_fuel_generator_gui.png");
	
	private final InventoryPlayer player;
	private TileEntitySolidFuelGenerator generator;
	
	private Vec2 mouseCoord = new Vec2();
	
	private GuiRectArea rect = new GuiRectArea(7, 15, 22, 54);
	
	public GuiContainerSolidFuelGenerator(InventoryPlayer player, TileEntitySolidFuelGenerator generator) {
		super(new ContainerSolidFuelGenerator(player, generator));
		this.player = player;
		this.generator = generator;
		this.xSize = 175;
		this.ySize = 175;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		// TODO Auto-generated method stub
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		
		String s = this.getLangString("container.solid_fuel_generator");
		this.renderString(s, this.getMiddleOfScreenX(s), 6, Color.BLACK);
		this.renderFormatedString("container.inventory", 7, 87, Color.BLACK);
		
		if(rect.collide(mouseCoord)) {
			String info = generator.printEnergyValue();
			this.renderString(info, (int)this.mouseCoord.getX(), (int)this.mouseCoord.getY(), Color.DARK_GREY);
		}
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		this.renderBackgroundImage(solidFuelGeneratorGui);
		// Render Flames
		if(this.generator.isBurning()) {
			this.renderFlames(76, 36, 176, 14, this.generator.getBurningTimeRemainingScaled(14));
		}
		// Render Energy Bar
		this.renderEnergyStorageLevel(7, 15, 175, 54, generator.getEnergyLevelScaled(39));
	}

	protected void renderFlames(int x, int y, int tx, int ty, int index) {
		int k = this.getK();
		int l = this.getL();
		this.drawTexturedModalRect(k+x, l+y+ty-index, tx, ty - index, ty, index + 1);
	}
	
	protected void renderEnergyStorageLevel(int x, int y, int tx, int ty, int index) {
		// Update Energy delta [16, 40]
		int k = this.getK();
		int l = this.getL();
		int deltaY = ty;
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
