package com.derf.btawc.jei.alloyfurnace;

import jline.internal.Log;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class AlloyRecipeHandler implements IRecipeHandler<AlloyRecipeWrapper> {

	@Override
	public Class<AlloyRecipeWrapper> getRecipeClass() {
		// TODO Auto-generated method stub
		return AlloyRecipeWrapper.class;
	}

	@Override
	public String getRecipeCategoryUid() {
		// TODO Auto-generated method stub
		return "btawc.alloy_furnace";
	}

	@Override
	public String getRecipeCategoryUid(AlloyRecipeWrapper recipe) {
		// TODO Auto-generated method stub
		return "btawc.alloy_furnace";
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(AlloyRecipeWrapper recipe) {
		// TODO Auto-generated method stub
		return recipe;
	}

	@Override
	public boolean isRecipeValid(AlloyRecipeWrapper recipe) {
		if(recipe.getInputs().isEmpty()) {
			//String ri = ErrorUtil.getInfoFromBrokenRecipe(recipe, this);
			//Log.error("Recipe has no inputs. {}", ri);
		}
		
		if(recipe.getOutputs().isEmpty()) {
			//String ri = ErrorUtil.getInfoFromBrokenRecipe(recipe, this);
			//Log.error("Recipe has no outputs. {}", ri);
		}
		
		return true;
	}

}
