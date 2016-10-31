package com.derf.btawc.client;

import org.lwjgl.opengl.GL11;

import com.derf.btawc.fluid.FluidTank;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public final class RenderUtils {
	
	public static final ResourceLocation BLOCK_TEXTURE = TextureMap.LOCATION_BLOCKS_TEXTURE;
	
	/**
	 * This method will grab the texture for the fluid inventory.
	 * 
	 * Note: Tank CrazyPants. This code is from an me reading through 
	 * some of CrazyPants's code from EnderCore.
	 * https://github.com/SleepyTrousers/EnderCore/blob/1.10/src/main/java/com/enderio/core/client/render/RenderUtil.java
	 * 
	 * @param fluid
	 * @return
	 */
	public static TextureAtlasSprite getStillSprite(FluidStack fluid){
		if(fluid == null || fluid.getFluid() == null) {
			return null;
		}
		
		ResourceLocation location = fluid.getFluid().getStill();
		
		if(location == null) {
			return null;
		}
		
		return Minecraft.getMinecraft().getTextureMapBlocks().getTextureExtry(location.toString());
	}
	
	public static void renderGuiTank(FluidTank tank, int amount, double x, double y, double width, double height) {
		FluidStack fluidStack = tank.getFluid();
		
		if(fluidStack == null || fluidStack.amount <= 0) {
			return;
		}
		
		int capacity = tank.getCapacity();
		
		Fluid fluid = fluidStack.getFluid();
		
		if(fluid == null) {
			return;
		}
		
		TextureAtlasSprite icon = getStillSprite(fluidStack);
		
		if(icon == null) {
			return;
		}
		
		int iconAmount = (int) Math.max(Math.min(height, amount * height / capacity), 1);
		
		int posY = (int) (y + height - iconAmount);
		
		RenderUtils.bindBlockTexture();
		
		int c = fluid.getColor(fluidStack);
		
		Color color = new Color(c);
		
		GL11.glColor3f(color.getRed(), color.getGreen(), color.getBlue());
		
		GlStateManager.enableBlend();
		for(int i = 0; i < width; i += 16) {
			for(int j = 0; j < iconAmount; j += 16) {
				int drawWidth = (int) Math.min(width - i, 16);
				int drawHeight = (int) Math.min(iconAmount - j, 16);
				
				int drawX = (int) (x + i);
				int drawY = (int) (posY + j);
				
				double minU = icon.getMinU();
				double maxU = icon.getMaxU();
				double minV = icon.getMinV();
				double maxV = icon.getMaxV();
				
				/*
				 *         Tessellator tessellator = Tessellator.getInstance();
					        VertexBuffer tes = tessellator.getBuffer();
					        tes.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
					        tes.pos(drawX, drawY + drawHeight, 0).tex(minU, minV + (maxV - minV) * drawHeight / 16F).endVertex();
					        tes.pos(drawX + drawWidth, drawY + drawHeight, 0).tex(minU + (maxU - minU) * drawWidth / 16F, minV + (maxV - minV) * drawHeight / 16F).endVertex();
					        tes.pos(drawX + drawWidth, drawY, 0).tex(minU + (maxU - minU) * drawWidth / 16F, minV).endVertex();
					        tes.pos(drawX, drawY, 0).tex(minU, minV).endVertex();
							tessellator.draw();
				 */
				Tessellator tess = Tessellator.getInstance();
				VertexBuffer buffer = tess.getBuffer();
				buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
				buffer.pos(drawX, drawY + drawHeight, 0).tex(minU, minV + (maxV - minV) * drawHeight / 16f).endVertex();
				buffer.pos(drawX + drawWidth, drawY + drawHeight, 0).tex(minU + (maxU - minU) * drawWidth / 16f, minV + (maxV - minV) * drawHeight / 16f);
				buffer.pos(drawX + drawWidth, drawY, 0).tex(minU + (maxU - minU) * drawWidth / 16f, minV).endVertex();
				buffer.pos(drawX, drawY, 0).tex(minU, minV).endVertex();
				tess.draw();
			}
		}
		GlStateManager.disableBlend();
	}
	
	public static TextureManager getTextureManager() {
		return Minecraft.getMinecraft().getTextureManager();
	}
	
	public static void bindBlockTexture() {
		RenderUtils.getTextureManager().bindTexture(RenderUtils.BLOCK_TEXTURE);
	}
	
	public static void bindTexture(String id) {
		RenderUtils.getTextureManager().bindTexture(new ResourceLocation(id));
	}
	
	public static void bindTexture(ResourceLocation location) {
		RenderUtils.getTextureManager().bindTexture(location);
	}
}
