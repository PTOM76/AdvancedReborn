package ml.pkom.advancedreborn.addons.rei;

import me.shedaniel.rei.api.*;
import me.shedaniel.rei.api.plugins.REIPluginV0;
import ml.pkom.advancedreborn.AdvancedReborn;
import ml.pkom.advancedreborn.Blocks;
import ml.pkom.advancedreborn.Recipes;
import ml.pkom.advancedreborn.addons.rei.machine.TwoInputRightOutputCategory;
import net.minecraft.recipe.Recipe;
import net.minecraft.util.Identifier;
import reborncore.common.crafting.RebornRecipe;
import reborncore.common.crafting.RebornRecipeType;
import reborncore.common.crafting.RecipeManager;
import techreborn.compat.rei.MachineRecipeDisplay;
import techreborn.compat.rei.ReiPlugin;
import techreborn.init.ModRecipes;

import java.util.function.Function;
import java.util.function.Predicate;

public class REIAddon implements REIPluginV0 {

    public static Identifier PLUGIN = AdvancedReborn.createID("advanced_plugin");

    //public static Map<RebornRecipeType<?>, ItemConvertible> iconMap = new HashMap<>();

    public REIAddon() {
        ReiPlugin.iconMap.put(Recipes.CANNING_MACHINE, Blocks.CANNING_MACHINE);
        ReiPlugin.iconMap.put(ModRecipes.GRINDER, Blocks.ROTARY_GRINDER);
        ReiPlugin.iconMap.put(ModRecipes.EXTRACTOR, Blocks.CENTRIFUGAL_EXTRACTOR);
        ReiPlugin.iconMap.put(ModRecipes.COMPRESSOR, Blocks.SINGULARITY_COMPRESSOR);
    }

    public Identifier getPluginIdentifier() {
        return PLUGIN;
    }

    public void registerPluginCategories(RecipeHelper recipeHelper) {
        recipeHelper.registerCategory(new TwoInputRightOutputCategory<>(Recipes.CANNING_MACHINE));
    }

    public void registerRecipeDisplays(RecipeHelper recipeHelper) {
        RecipeManager.getRecipeTypes(AdvancedReborn.MOD_ID).forEach(rebornRecipeType -> registerMachineRecipe(recipeHelper, rebornRecipeType));
    }

    public <R extends RebornRecipe> void registerMachineRecipe(RecipeHelper recipeHelper, RebornRecipeType<R> recipeType) {
        Function<R, RecipeDisplay> recipeDisplay = r -> new MachineRecipeDisplay<>((RebornRecipe) r);
        recipeHelper.registerRecipes(recipeType.getName(), (Predicate<Recipe>) recipe -> {
            if (recipe instanceof RebornRecipe) {
                return ((RebornRecipe) recipe).getRebornRecipeType() == recipeType;
            }
            return false;
        }, recipeDisplay);
    }

    public void registerOthers(RecipeHelper recipeHelper) {
        recipeHelper.registerWorkingStations(Recipes.CANNING_MACHINE.getName(), EntryStack.create(Blocks.CANNING_MACHINE));
        recipeHelper.registerWorkingStations(ModRecipes.GRINDER.getName(), EntryStack.create(Blocks.ROTARY_GRINDER));
        recipeHelper.registerWorkingStations(ModRecipes.EXTRACTOR.getName(), EntryStack.create(Blocks.CENTRIFUGAL_EXTRACTOR));
        recipeHelper.registerWorkingStations(ModRecipes.COMPRESSOR.getName(), EntryStack.create(Blocks.SINGULARITY_COMPRESSOR));
    }
}
