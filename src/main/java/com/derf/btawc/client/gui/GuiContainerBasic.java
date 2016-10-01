package com.derf.btawc.client.gui;

import org.lwjgl.opengl.GL11;

import com.derf.btawc.client.Color;

import net.minecraft.client.gui.inventory.GuiContainer;
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
		this.mc.getTextureManager().bindTexture(resource);
		int k = this.getK();
		int l = this.getL();
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
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
}