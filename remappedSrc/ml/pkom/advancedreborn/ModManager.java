package ml.pkom.advancedreborn;

import ml.pkom.advancedreborn.addons.autoconfig.AutoConfigAddon;
import net.fabricmc.loader.api.FabricLoader;

public class ModManager {
    public static void beforeInit() {
        if (FabricLoader.getInstance().isModLoaded("autoconfig1u")) {
            ml.pkom.advancedreborn.addons.autoconfig.AutoConfigAddon.init();
        }
    }

    public static void afterInit() {
    }

    public static void initAfterLoadedTR() {
        if (FabricLoader.getInstance().isModLoaded("computercraft")) {
            if (AutoConfigAddon.getConfig().linkComputerCraft) ml.pkom.advancedreborn.addons.computercraft.ComputerCraftAddon.init();
        }
    }
}
