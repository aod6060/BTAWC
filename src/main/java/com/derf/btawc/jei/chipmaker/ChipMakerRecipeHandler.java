package com.derf.btawc.jei.chipmaker;

import com.derf.btawc.BTAWCLogger;
import com.derf.btawc.jei.BTAWCRecipeCategoryUid;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class ChipMakerRecipeHandler implements IRecipeHandler<ChipMakerRecipeWrapper> {

	@Override
	public Class<ChipMakerRecipeWrapper> getRecipeClass() {
		// TODO Auto-generated method stub
		return ChipMakerRecipeWrapper.class;
	}

	@Override
	public String getRecipeCategoryUid() {
		// TODO Auto-generated method stub
		return BTAWCRecipeCategoryUid.CHIP_MAKER;
	}

	@Override
	public String getRecipeCategoryUid(ChipMakerRecipeWrapper recipe) {
		// TODO Auto-generated method stub
		return BTAWCRecipeCategoryUid.CHIP_MAKER;
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(ChipMakerRecipeWrapper recipe) {
		// TODO Auto-generated method stub
		return recipe;
	}

	@Override
	public boolean isRecipeValid(ChipMakerRecipeWrapper recipe) {
		if(recipe.getInput() == null) {
			BTAWCLogger.getLogger().error("Recipe conflict...");
			return false;
		}
		
		if(recipe.getRedstone() == null) {
			BTAWCLogger.getLogger().error("Recipe conflict...");
			return false;
		}
		
		if(recipe.getOutput() == null) {
			BTAWCLogger.getLogger().error("Recipe conflict...");
			return false;
		}
		return true;
	}

}
