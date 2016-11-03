package com.derf.btawc.client.gui.tank;

import com.derf.btawc.Loader;
import com.derf.btawc.client.Color;
import com.derf.btawc.client.gui.GuiContainerBasic;
import com.derf.btawc.fluid.FluidTank;
import com.derf.btawc.inventory.container.tank.ContainerTank;
import com.derf.btawc.tileentity.tank.TileEntityTank;
import com.derf.btawc.util.GuiRect;
import com.derf.btawc.util.GuiRectArea;
import com.derf.btawc.util.Vec2;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class GuiContainerTank extends GuiContainerBasic {
	public static final ResourceLocation tankGUI = new ResourceLocation(Loader.MODID + ":textures/gui/container/tank_gui.png");
	// Container Fields
	private final InventoryPlayer player;
	private TileEntityTank tank;
	
	private GuiRectArea rect = new GuiRectArea(8, 16, 23, 55);
	
	// Sides
	private GuiRect[] sides = new GuiRect[6];
	
	public GuiContainerTank(InventoryPlayer player, TileEntityTank tank) {
		super(new ContainerTank(player, tank));
		this.player = player;
		this.tank = tank;
		this.xSize = 176;
		this.ySize = 176;
		
		// Init all sides for the tank...
		// West
		sides[EnumFacing.WEST.ordinal()] = new GuiRect(72, 28, 79 - 72, 35 - 28);
		// East
		sides[EnumFacing.EAST.ordinal()] = new GuiRect(88, 28, 95 - 88, 35 - 28);
		// South
		sides[EnumFacing.SOUTH.ordinal()] = new GuiRect(80, 28, 87-80, 35 - 28);
		// North
		sides[EnumFacing.NORTH.ordinal()] = new GuiRect(88, 20, 85 - 88, 27 - 20);
		// Up
		sides[EnumFacing.UP.ordinal()] = new GuiRect(80, 20, 87 - 80, 27 - 20);
		// Down
		sides[EnumFacing.DOWN.ordinal()] = new GuiRect(80, 36, 87 - 80, 43 - 36);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = this.tank.hasCustomName()? this.tank.getName() : this.getLangString(this.tank.getName());
		this.renderString(s, this.xSize / 2 - this.stringWidth(s)/2, 6, Color.BLACK);
		this.renderFormatedString("container.inventory", 8, 80, Color.BLACK);
		
		int mx = mouseX - this.getK();
		int my = mouseY - this.getL();
		
		Vec2 v = new Vec2(mx, my);
		
		if(this.rect.collide(v)) {
			this.renderString(tank.grabString(), mx, my, Color.DARK_GREY);
		}
			
		
	}


	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		this.renderBackgroundImage(tankGUI);
		this.renderLiquidProgressBar(7, 16);
		this.renderConfiguration();
		int mx = this.getK() - mouseX;
		int my = this.getL() - mouseY;
		
		Vec2 v = new Vec2(mx, my);
		
		if(this.rect.collide(v)) {
			this.drawRect(mx, my, mx + this.stringWidth(tank.grabString()), my + this.fontRendererObj.FONT_HEIGHT, Color.LIGHT_GREY.toColor24());
		}
	}

	private void renderLiquidProgressBar(int x, int y) {
		int k = this.getK();
		int l = this.getL();
		
		FluidTank tank = this.tank.getTank();
		
		FluidStack fluidStack = tank.getFluid();
		
		if(fluidStack == null || fluidStack.amount <= 0) {
			return;
		}
		
		int capacity = tank.getCapacity();
		
		Fluid fluid = fluidStack.getFluid();
		
		ResourceLocation location = fluid.getStill();
		
		TextureAtlasSprite icon = this.grabTextureFromAtlas(location);
		
		int i = (int) ((double)fluidStack.amount * 40.0 / (double)capacity);
		
		int c = fluid.getColor(fluidStack);
		
		int posX = x + k;
		int posY = y + l + 40 - i;
		int bottom = y + l + 40;
		
		this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		
		//this.drawRect(posX, posY, posX + 16, bottom, c);
		this.drawTexturedRect(posX, posY, posX+16, bottom, icon);
		
		this.bindTexture(tankGUI);
		// Render Stuff from the side
		this.drawTexturedModalRect(posX, posY, 175, 40 - i, 40, i+1);
	}

	private void renderConfiguration() {
		int k = this.getK();
		int l = this.getL();
		int u = 176;
		
		// Pull
		int pullV = 48;
		// Push
		int pushV = 56;		
		// Disabled
		int disabledV = 64;
		
		/*
		 * for now i'm going to work on the packet system.
		for(EnumFacing facing : EnumFacing.values()) {
			GuiRect r = this.sides[facing.ordinal()];
		}
		*/
	}

}
