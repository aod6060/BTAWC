package com.derf.btawc.jei.chipmaker;

import com.derf.btawc.Loader;
import com.derf.btawc.jei.BTAWCRecipeCategoryUid;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class ChipMakerRecipeCategory extends BlankRecipeCategory<ChipMakerRecipeWrapper> {

	public static final int INPUT = 0;
	public static final int REDSTONE = 1;
	public static final int OUTPUT = 2;
	
	private final ResourceLocation backgroundResource;
	private final IDrawable background;
	
	public ChipMakerRecipeCategory(IGuiHelper helper) {
		backgroundResource = new ResourceLocation(Loader.MODID + ":textures/jei/chip_maker_gui.png");
		background = helper.createDrawable(backgroundResource, 0, 0, 100, 60);
	}
	
	@Override
	public String getUid() {
		return BTAWCRecipeCategoryUid.CHIP_MAKER;
	}

	@Override
	public String getTitle() {
		return I18n.format("jei.chip_maker");
	}

	@Override
	public IDrawable getBackground() {
		return this.background;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, ChipMakerRecipeWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup sg = recipeLayout.getItemStacks();
		sg.init(INPUT, true, 4, 16);
		sg.init(REDSTONE, true, 24, 16);
		sg.init(OUTPUT, false, 76, 16);
		
		// Input
		sg.set(INPUT, recipeWrapper.getInput());
		// Redstone
		sg.set(REDSTONE, recipeWrapper.getRedstone());
		// Output
		sg.set(OUTPUT, recipeWrapper.getOutput());
	}

}
