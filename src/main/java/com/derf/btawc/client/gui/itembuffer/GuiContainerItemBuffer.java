package com.derf.btawc.client.gui.itembuffer;

import java.io.IOException;

import com.derf.btawc.Loader;
import com.derf.btawc.client.Color;
import com.derf.btawc.client.gui.GuiContainerBasic;
import com.derf.btawc.inventory.container.itembuffer.ContainerItemBuffer;
import com.derf.btawc.network.PacketHandler;
import com.derf.btawc.network.data.FactoryPacketData;
import com.derf.btawc.network.data.server.PacketDataSixSidedConfiguration;
import com.derf.btawc.tileentity.EnumSixSided;
import com.derf.btawc.tileentity.itembuffer.TileEntityItemBuffer;
import com.derf.btawc.util.GuiRect;
import com.derf.btawc.util.Vec2;

import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class GuiContainerItemBuffer extends GuiContainerBasic {
	private static final ResourceLocation itemBufferGUI = new ResourceLocation(Loader.MODID + ":textures/gui/container/item_buffer_gui.png");
	
	private final InventoryPlayer player;
	private TileEntityItemBuffer itembuffer;
	
	private GuiRect west = new GuiRect(128, 40, 8, 8);
	private GuiRect east = new GuiRect(144, 40, 8, 8);
	private GuiRect south = new GuiRect(136, 40, 8, 8);
	private GuiRect north = new GuiRect(144, 32, 8, 8);
	private GuiRect up = new GuiRect(136, 32, 8, 8);
	private GuiRect down = new GuiRect(136, 48, 8, 8);
	
	public GuiContainerItemBuffer(InventoryPlayer player, TileEntityItemBuffer itembuffer) {
		super(new ContainerItemBuffer(player, itembuffer));
		this.player = player;
		this.itembuffer = itembuffer;
		this.xSize = 176;
		this.ySize = 176;
	}

	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = this.itembuffer.hasCustomName()? this.itembuffer.getName() : this.getLangString(this.itembuffer.getName());
		this.renderString(s, this.xSize / 2 - this.stringWidth(s)/2, 6, Color.BLACK);
		this.renderFormatedString("container.inventory", 8, 80, Color.BLACK);
	}


	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		this.renderBackgroundImage(itemBufferGUI);
		renderConfiguration();
	}


	private void renderConfiguration() {
		renderWest();
		renderEast();
		renderSouth();
		renderNorth();
		renderUp();
		renderDown();
	}
	
	private void renderWest() {
		this.renderStuff(EnumFacing.WEST, 128, 40);
	}


	private void renderEast() {
		this.renderStuff(EnumFacing.EAST, 144, 40);
	}


	private void renderSouth() {
		this.renderStuff(EnumFacing.SOUTH, 136, 40);
	}


	private void renderNorth() {
		this.renderStuff(EnumFacing.NORTH, 144, 32);
	}


	private void renderUp() {
		this.renderStuff(EnumFacing.UP, 136, 32);
	}


	private void renderDown() {
		this.renderStuff(EnumFacing.DOWN, 136, 48);
	}

	private void renderStuff(EnumFacing facing, int cx, int cy) {
		int k = this.getK();
		int l = this.getL();
		
		int x = cx + k;
		int y = cy + l;
		
		int pullX = 176;
		int pullY = 0;
		
		int pushX = 176;
		int pushY = 8;
		
		int disX = 176;
		int disY = 16;
		
		
		EnumSixSided type = itembuffer.getType(facing);
		
		if(type == EnumSixSided.PULL) {
			this.drawTexturedModalRect(x, y, pullX, pullY, 8, 8);
		} else if(type == EnumSixSided.PUSH) {
			this.drawTexturedModalRect(x, y, pushX, pushY, 8, 8);
		} else if(type == EnumSixSided.DISABLED) {
			this.drawTexturedModalRect(x, y, disX, disY, 8, 8);
		}
	}
	
	@Override
	protected void mouseClicked(int x, int y, int button) throws IOException {
		// TODO Auto-generated method stub
		super.mouseClicked(x, y, button);
		
		int k = this.getK();
		int l = this.getL();
		
		if(button == 0 || button == 1) {
			Vec2 mc = new Vec2(x - k, y - l);
			if(this.west.collide(mc)) {
				this.toggleType(EnumFacing.WEST);
				this.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
			}
			if(this.east.collide(mc)) {
				this.toggleType(EnumFacing.EAST);
				this.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
			}
			if(this.south.collide(mc)) {
				this.toggleType(EnumFacing.SOUTH);
				this.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
			}
			if(this.north.collide(mc)) {
				this.toggleType(EnumFacing.NORTH);
				this.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
			}
			if(this.up.collide(mc)) {
				this.toggleType(EnumFacing.UP);
				this.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
			}
			if(this.down.collide(mc)) {
				this.toggleType(EnumFacing.DOWN);
				this.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
			}
		}
	}
	
	private void toggleType(EnumFacing facing) {
		EnumSixSided type = this.itembuffer.getType(facing);
		if(type == EnumSixSided.OFF) {
			type = EnumSixSided.PULL;
		} else if(type == EnumSixSided.PULL) {
			type = EnumSixSided.PUSH;
		} else if(type == EnumSixSided.PUSH) {
			type = EnumSixSided.DISABLED;
		} else if(type == EnumSixSided.DISABLED) {
			type = EnumSixSided.OFF;
		}
		this.itembuffer.setType(facing, type);
		// Handle Packet for ItemBuffer
		
		PacketHandler.sendPacketToServer(
				FactoryPacketData.createPacketData("six_sided_configuration", 
						PacketDataSixSidedConfiguration.createCallback(this.itembuffer.getPos(), facing, type)), itembuffer.getWorld());
		this.inventorySlots.detectAndSendChanges();
	}
}
