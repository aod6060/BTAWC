package com.derf.btawc.client.gui.generators;

import java.io.IOException;

import com.derf.btawc.Loader;
import com.derf.btawc.client.Color;
import com.derf.btawc.client.gui.GuiContainerBasic;
import com.derf.btawc.inventory.container.generator.ContainerCreativeGenerator;
import com.derf.btawc.network.PacketHandler;
import com.derf.btawc.network.packets.PacketCreativeGeneratorInfo;
import com.derf.btawc.tileentity.generators.TileEntityCreativeGenerator;
import com.derf.btawc.util.GuiRect;
import com.derf.btawc.util.Vec2;

import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
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
	
	private GuiRect speedUpgradedInc = new GuiRect(135, 47, 9, 9);
	private GuiRect speedUpgradedDec = new GuiRect(135+10, 47, 9, 9);
	
	private GuiRect insantityInc = new GuiRect(135, 63, 9, 9);
	private GuiRect insantityDec = new GuiRect(135 + 10, 63, 9, 9);
	
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
		
		// Section where I'm rendering debug info
		String energyTicks = generator.printEnergyValue();
		this.renderString(energyTicks, 40, 32, Color.BLACK);
		// Print Speed Upgrades
		String speedUpgrades = String.format("Speed Upgrades: %d", this.generator.getNumberOfSpeedUpgrades());
		this.renderString(speedUpgrades, 40, 48, Color.BLACK);
		// Print Insantiy
		String insantiy = String.format("Insantity: %d", this.generator.insantity);
		this.renderString(insantiy, 40, 64, Color.BLACK);
		
	}


	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		this.renderBackgroundImage(creativeGenertorGUI);
		this.renderEnergyStorageLevel(15, 15);
		this.renderNobes(135, 63);
	}
	
	private void renderNobes(int x, int y) {
		// TODO Auto-generated method stub
		int k = this.getK();
		int l = this.getL();
		this.drawTexturedModalRect(x + k, l + y, 175, 47, 10, 10);
		this.drawTexturedModalRect(x + k + 10, l + y, 175, 63, 10, 10);
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


	@Override
	protected void mouseClicked(int x, int y, int button) throws IOException {
		// TODO Auto-generated method stub
		super.mouseClicked(x, y, button);
		
		int k = this.getK();
		int l = this.getL();
		
		if(button == 0 || button == 1) {
			Vec2 mc = new Vec2(x - k, y - l);
			if(this.insantityInc.collide(mc)) {
				this.generator.insantity += 64;
				if(this.generator.insantity > 4096) {
					this.generator.insantity = 4096;
					this.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.BLOCK_ANVIL_LAND, 1.0f));
				} else {
					this.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
				}
			}
			
			if(this.insantityDec.collide(mc)) {
				this.generator.insantity -= 64;
				if(this.generator.insantity < 1) {
					this.generator.insantity = 1;
					this.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.BLOCK_ANVIL_LAND, 1.0f));
				} else {
					this.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
				}
				this.inventorySlots.updateProgressBar(6, generator.insantity);
			}
			
			// Send Packet to TileEntity
			PacketHandler.INSTANCE.sendToServer(
					new PacketCreativeGeneratorInfo(this.generator.getWorld().provider.getDimension(),
							this.generator.getPos(),
							this.generator.insantity));
		}
	}
	
	
}
