package ml.pkom.advancedreborn;

import net.fabricmc.loader.api.FabricLoader;

public class ModManager {
    public static void init() {

    }

    public static void initAfterLoadedTR() {
        if (FabricLoader.getInstance().isModLoaded("computercraft")) {
            ml.pkom.advancedreborn.computercraft.ComputerCraftAddon.init();
        }
    }
}
