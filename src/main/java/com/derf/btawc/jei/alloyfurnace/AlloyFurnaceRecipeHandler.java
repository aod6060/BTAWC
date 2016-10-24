package com.derf.btawc.jei.alloyfurnace;

import com.derf.btawc.BTAWCLogger;
import com.derf.btawc.jei.BTAWCRecipeCategoryUid;

import jline.internal.Log;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class AlloyFurnaceRecipeHandler implements IRecipeHandler<AlloyFurnaceRecipeWrapper> {

	@Override
	public Class<AlloyFurnaceRecipeWrapper> getRecipeClass() {
		// TODO Auto-generated method stub
		return AlloyFurnaceRecipeWrapper.class;
	}

	@Override
	public String getRecipeCategoryUid() {
		// TODO Auto-generated method stub
		return BTAWCRecipeCategoryUid.ALLOY_FURNACE;
	}

	@Override
	public String getRecipeCategoryUid(AlloyFurnaceRecipeWrapper recipe) {
		// TODO Auto-generated method stub
		return BTAWCRecipeCategoryUid.ALLOY_FURNACE;
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(AlloyFurnaceRecipeWrapper recipe) {
		// TODO Auto-generated method stub
		return recipe;
	}

	@Override
	public boolean isRecipeValid(AlloyFurnaceRecipeWrapper recipe) {
		if(recipe.getInputs().isEmpty()) {
			//String ri = ErrorUtil.getInfoFromBrokenRecipe(recipe, this);
			//Log.error("Recipe has no inputs. {}", ri);
			BTAWCLogger.getLogger().error("Recipe conflict...");
			return false;
		}
		
		if(recipe.getOutputs().isEmpty()) {
			//String ri = ErrorUtil.getInfoFromBrokenRecipe(recipe, this);
			//Log.error("Recipe has no outputs. {}", ri);
			BTAWCLogger.getLogger().error("Recipe doesn't exist...");
			return false;
		}
		
		return true;
	}

}
