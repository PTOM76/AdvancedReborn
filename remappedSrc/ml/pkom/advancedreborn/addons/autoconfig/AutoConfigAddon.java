package ml.pkom.advancedreborn.addons.autoconfig;


import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import ml.pkom.advancedreborn.AdvancedRebornConfig;

public class AutoConfigAddon {
    public static AdvancedRebornConfig config;
    public static boolean registered = false;

    public static void init() {
        registerCheck();
    }

    public static void registerCheck() {
        if (registered) return;
        try {
            AutoConfig.register(AdvancedRebornConfig.class, JanksonConfigSerializer::new);
            config = AutoConfig.getConfigHolder(AdvancedRebornConfig.class).getConfig();
            registered = true;
        } catch (RuntimeException e) {}
    }

    public static AdvancedRebornConfig getConfig() {
        registerCheck();
        return config;
    }
}
