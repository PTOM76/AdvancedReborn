package ml.pkom.advancedreborn;

import ml.pkom.advancedreborn.blocks.RaySolar;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class AdvancedReborn implements ModInitializer {
    public static final String MOD_ID = "advanced_reborn";
    public static final String MOD_NAME = "Advanced Reborn";
    public static Logger LOGGER = LogManager.getLogger();
    public static void log(Level level, String message){
        LOGGER.log(level, "[" + MOD_NAME + "] " + message);
    }

    // Add ItemGroup
    public static DefaultedList<ItemStack> addStacksIG = DefaultedList.of();

    public static ItemGroup AR_GROUP = FabricItemGroupBuilder.build(
            id("item_group"),
            () -> new ItemStack(Items.CHARGE_PAD_MK_FINAL));

    @Override
    public void onInitialize() {
        ModManager.beforeInit();
        Items.init();
        Blocks.init();
        Entities.init();
        Tiles.init();
        GuiTypes.init();
        Recipes.init();
        Particles.init();
        ScreenHandlers.init();
        ARDispenserBehavior.init();
        Network.init();
        ModManager.afterInit();
    }

    public static List<RaySolar> solars = new ArrayList<>();

    static {
        solars.add((RaySolar) Blocks.RAY_SOLAR_1);
    }

    public static Identifier id(String id) {
        return new Identifier(MOD_ID, id);
    }
}