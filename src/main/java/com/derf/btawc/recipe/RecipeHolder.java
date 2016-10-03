package com.derf.btawc.recipe;


/**
 * This is a simple generic class that will hold the recipe data for me...
 * @author Fred
 *
 * @param <R> This is the Recipe
 * @param <S> This is teh result of the recipe
 */
public class RecipeHolder<R, S> {
	private R recipe = null;
	private S result = null;
	
	public RecipeHolder(R recipe, S result) {
		this.recipe = recipe;
		this.result = result;
	}

	public R getRecipe() {
		return recipe;
	}


	public S getResult() {
		return result;
	}
	
	
}
