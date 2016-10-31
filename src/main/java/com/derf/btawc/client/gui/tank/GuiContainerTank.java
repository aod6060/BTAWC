package com.derf.btawc.client.gui.tank;

import com.derf.btawc.BTAWCLogger;
import com.derf.btawc.Loader;
import com.derf.btawc.client.RenderUtils;
import com.derf.btawc.client.gui.GuiContainerBasic;
import com.derf.btawc.inventory.container.tank.ContainerTank;
import com.derf.btawc.tileentity.tank.TileEntityTank;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiContainerTank extends GuiContainerBasic {
	public static final ResourceLocation tankGUI = new ResourceLocation(Loader.MODID + ":textures/gui/container/tank_gui.png");
	// Container Fields
	private final InventoryPlayer player;
	private TileEntityTank tank;
	
	public GuiContainerTank(InventoryPlayer player, TileEntityTank tank) {
		super(new ContainerTank(player, tank));
		this.player = player;
		this.tank = tank;
		this.xSize = 176;
		this.ySize = 176;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		this.renderBackgroundImage(tankGUI);
		this.renderLiquidProgressBar();
		this.renderConfiguration();
	}

	private void renderLiquidProgressBar() {
		RenderUtils.renderGuiTank(tank.getTank(), this.tank.getTank().getFluidAmount(), 8, 16, 24 - 8, 55 - 16);
		
		if(tank.getWorld().getWorldTime() % 20L == 0) {
			BTAWCLogger.getLogger().info("["+this.tank.getTank().getFluidAmount()+"/"+this.tank.getTank().getCapacity()+"MB]");
		}
		
		if(!tank.getTank().isFluidTankEmpty()) {
			if(tank.getWorld().getWorldTime() % 20L == 0) {
				BTAWCLogger.getLogger().info("["+this.tank.getTank().getFluidAmount()+"/"+this.tank.getTank().getCapacity()+"MB] Name: "+this.tank.getTank().getFluid().getLocalizedName());
			}
		}
	}

	private void renderConfiguration() {
		// I'll handle this in a bit...
	}

}
