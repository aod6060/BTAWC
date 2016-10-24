package com.derf.btawc.jei.alloyfurnace;

import java.util.List;

import com.derf.btawc.Loader;
import com.derf.btawc.jei.BTAWCRecipeCategoryUid;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class AlloyFurnaceRecipeCategory extends BlankRecipeCategory<AlloyFurnaceRecipeWrapper>{

	public static final int INPUT_1 = 0;
	public static final int INPUT_2 = 1;
	public static final int INPUT_3 = 2;
	public static final int INPUT_4 = 3;
	public static final int OUTPUT = 4;
	public static final int FUEL_SLOT = 5;
	
	private final ResourceLocation backgroundResource;
	private final IDrawable background;
	
	public AlloyFurnaceRecipeCategory(IGuiHelper guiHelper) {
		backgroundResource = new ResourceLocation(Loader.MODID + ":textures/jei/alloy_furnace_gui.png");
		background = guiHelper.createDrawable(backgroundResource, 0, 0, 100, 64);
	}
	
	@Override
	public String getUid() {
		// TODO Auto-generated method stub
		return BTAWCRecipeCategoryUid.ALLOY_FURNACE;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return I18n.format("jei.alloy_furnace");
	}

	@Override
	public IDrawable getBackground() {
		// TODO Auto-generated method stub
		return this.background;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, AlloyFurnaceRecipeWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup sg = recipeLayout.getItemStacks();
		sg.init(INPUT_1, true, 4, 4);
		sg.init(INPUT_2, true, 22, 4);
		sg.init(INPUT_3, true, 4, 22);
		sg.init(INPUT_4, true, 22, 22);
		sg.init(OUTPUT, false, 78, 12);
		
		// Inputs
		List<List<ItemStack>> inputs = recipeWrapper.getInputs();
		sg.set(INPUT_1, inputs.get(0).get(0));
		sg.set(INPUT_2, inputs.get(0).get(1));
		sg.set(INPUT_3, inputs.get(0).get(2));
		sg.set(INPUT_4, inputs.get(0).get(3));
		
		// Output
		List<ItemStack> outputs = recipeWrapper.getOutputs();
		sg.set(OUTPUT, outputs.get(0));
	}

}
