package ml.pkom.advancedreborn;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import reborncore.common.crafting.RebornRecipe;
import reborncore.common.crafting.RebornRecipeType;
import reborncore.common.crafting.RecipeManager;

public class Recipes {
    public static RebornRecipeType<RebornRecipe> CANNING_MACHINE = RecipeManager.newRecipeType(RebornRecipe::new, AdvancedReborn.createID("canning_machine"));

    public static RebornRecipeType<?> byName(Identifier identifier) {
        return (RebornRecipeType<?>) Registry.RECIPE_SERIALIZER.get(identifier);
    }

    public static void init() {

    }
}
