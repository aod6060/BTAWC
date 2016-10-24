package com.derf.btawc.jei;

import com.derf.btawc.blocks.BlockManager;
import com.derf.btawc.client.gui.chipmaker.GuiContainerChipMaker;
import com.derf.btawc.client.gui.furnace.GuiContainerAlloyFurnace;
import com.derf.btawc.inventory.container.chipmaker.ContainerChipMaker;
import com.derf.btawc.inventory.container.furnace.ContainerAlloyFurnace;
import com.derf.btawc.jei.alloyfurnace.AlloyFurnaceRecipeCategory;
import com.derf.btawc.jei.alloyfurnace.AlloyFurnaceRecipeHandler;
import com.derf.btawc.jei.alloyfurnace.AlloyFurnaceRecipeMaker;
import com.derf.btawc.jei.chipmaker.ChipMakerRecipeCategory;
import com.derf.btawc.jei.chipmaker.ChipMakerRecipeHandler;
import com.derf.btawc.jei.chipmaker.ChipMakerRecipeMaker;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class BTAWCPlugin extends BlankModPlugin {
	public static IJeiHelpers jeiHelpers;

	@Override
	public void register(IModRegistry registry) {
		// TODO Auto-generated method stub
		super.register(registry);
		
		IIngredientRegistry ir = registry.getIngredientRegistry();
		jeiHelpers = registry.getJeiHelpers();
		
		IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
		
		// Add Category 
		registry.addRecipeCategories(
				new AlloyFurnaceRecipeCategory(guiHelper), 
				new ChipMakerRecipeCategory(guiHelper));
		// Add Handler
		
		registry.addRecipeHandlers(
				new AlloyFurnaceRecipeHandler(), 
				new ChipMakerRecipeHandler());
		
		// Add Recipe Click Area
		registry.addRecipeClickArea(GuiContainerAlloyFurnace.class, 78, 32, 28, 23, BTAWCRecipeCategoryUid.ALLOY_FURNACE);
		registry.addRecipeClickArea(GuiContainerChipMaker.class, 78, 32, 28, 23, BTAWCRecipeCategoryUid.CHIP_MAKER);
		
		// Add Transfer Registry
		IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();
		recipeTransferRegistry.addRecipeTransferHandler(ContainerAlloyFurnace.class, BTAWCRecipeCategoryUid.ALLOY_FURNACE, 0, 5, 6, 36);
		recipeTransferRegistry.addRecipeTransferHandler(ContainerChipMaker.class, BTAWCRecipeCategoryUid.CHIP_MAKER, 0, 3, 4, 36);
		
		// Add Category Icon
		registry.addRecipeCategoryCraftingItem(new ItemStack(BlockManager.superFurnace), VanillaRecipeCategoryUid.SMELTING, VanillaRecipeCategoryUid.FUEL);
		registry.addRecipeCategoryCraftingItem(new ItemStack(BlockManager.alloyFurnace), BTAWCRecipeCategoryUid.ALLOY_FURNACE, VanillaRecipeCategoryUid.FUEL);
		registry.addRecipeCategoryCraftingItem(new ItemStack(BlockManager.chipMaker), BTAWCRecipeCategoryUid.CHIP_MAKER, VanillaRecipeCategoryUid.FUEL);
		
		// Add Recipes
		registry.addRecipes(AlloyFurnaceRecipeMaker.getAlloyFurnaceResults(jeiHelpers));
		registry.addRecipes(ChipMakerRecipeMaker.getChipMakerResults(jeiHelpers));
	}
	
	
}
