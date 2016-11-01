package com.derf.btawc.client.gui;

import org.lwjgl.opengl.GL11;

import com.derf.btawc.client.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public abstract class GuiContainerBasic extends GuiContainer {

	public GuiContainerBasic(Container container) {
		super(container);
	}
	
	/**
	 * This simply returns the K value for locations on the x axis for the guie
	 * @return int
	 */
	protected int getK() {
		return (this.width - this.xSize) / 2;
	}
	
	/**
	 * This simple returns the L value for the locations on the y axis for the gui
	 * @return int
	 */
	protected int getL() {
		return (this.height - this.ySize) / 2;
	}
	
	/**
	 * This is a simple method to render the background image for a container gui.
	 * @param resource
	 */
	protected void renderBackgroundImage(ResourceLocation resource) {
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.bindTexture(resource);
		int k = this.getK();
		int l = this.getL();
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}
	
	/**
	 * This will bind a texture to the main MC stuff
	 * @param resource
	 */
	protected void bindTexture(ResourceLocation resource) {
		this.mc.getTextureManager().bindTexture(resource);
	}
	
	/**
	 * This is a simple string render. It wraps the this.fontRenderObj and uses my color system
	 * @param String str
	 * @param int x
	 * @param int y
	 * @param Color color
	 */
	protected void renderString(String str, int x, int y, Color color) {
		this.fontRendererObj.drawString(str, x, y, color.toColor16());
	}
	
	/**
	 * This is a formated version of a string that grabs the container.[name] from the lang file and renders it.
	 * @param String format
	 * @param int x
	 * @param int y
	 * @param Color color
	 * @param Object... args
	 */
	protected void renderFormatedString(String format, int x, int y, Color color, Object... args) {
		this.renderString(I18n.format(format, args), x, y, color);
	}
	
	/**
	 * Returns the string's Width
	 * @param String str
	 * @return int
	 */
	protected int stringWidth(String str) {
		return this.fontRendererObj.getStringWidth(str);
	}
	
	/**
	 * This wraps the I18n.format method into a nice and simple method
	 * @param format
	 * @param args
	 * @return
	 */
	protected String getLangString(String format, Object... args) {
		return I18n.format(format, args);
	}
	
	protected int getMiddleOfScreenX(String s) {
		return this.xSize / 2 - this.stringWidth(s) / 2;
	}
	
	protected TextureAtlasSprite grabTextureFromAtlas(ResourceLocation location) {
		return Minecraft.getMinecraft().getTextureMapBlocks().getTextureExtry(location.toString());
	}
	
	protected void drawTexturedRect(int left, int top, int right, int bottom, TextureAtlasSprite icon) {
		Tessellator t = Tessellator.getInstance();
		VertexBuffer b = t.getBuffer();
		b.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		b.pos(left, bottom, 0.0).tex(icon.getMinU(), icon.getMinV()).endVertex();
		b.pos(right, bottom, 0.0).tex(icon.getMaxU(), icon.getMinV()).endVertex();
		b.pos(right, top, 0.0).tex(icon.getMaxU(), icon.getMaxV()).endVertex();
		b.pos(left, top, 0.0).tex(icon.getMinU(), icon.getMaxV()).endVertex();
		t.draw();
	}
}
