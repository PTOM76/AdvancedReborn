package ml.pkom.advancedreborn;

import ml.pkom.advancedreborn.blocks.ChargePad;
import ml.pkom.advancedreborn.blocks.RaySolar;
import ml.pkom.advancedreborn.blocks.RotaryGrinder;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.registry.Registry;

public class Blocks {

    public static FabricBlockSettings baseSetting = FabricBlockSettings.of(Material.METAL).requiresTool().breakByTool(FabricToolTags.PICKAXES, 0).strength(2, 8);

    public static Block ROTARY_GRINDER = new RotaryGrinder(baseSetting);
    public static Block CHARGE_PAD_MK_1 = new ChargePad(baseSetting, 4);
    public static Block CHARGE_PAD_MK_2 = new ChargePad(baseSetting, 16);
    public static Block CHARGE_PAD_MK_3 = new ChargePad(baseSetting, 64);
    public static Block CHARGE_PAD_MK_4 = new ChargePad(baseSetting, 128);

    public static Block RAY_SOLAR_1 = new RaySolar(baseSetting, 1, false);
    public static Block RAY_SOLAR_2 = new RaySolar(baseSetting, 8, false);
    public static Block RAY_SOLAR_3 = new RaySolar(baseSetting, 64, false);
    public static Block RAY_SOLAR_4 = new RaySolar(baseSetting, 512, false);

    public static Block RAY_GENERATOR_1 = new RaySolar(baseSetting, 2, true);
    public static Block RAY_GENERATOR_2 = new RaySolar(baseSetting, 8, true);
    public static Block RAY_GENERATOR_3 = new RaySolar(baseSetting, 32, true);
    public static Block RAY_GENERATOR_4 = new RaySolar(baseSetting, 128, true);
    public static Block RAY_GENERATOR_5 = new RaySolar(baseSetting, 512, true);
    public static Block RAY_GENERATOR_6 = new RaySolar(baseSetting, 2048, true);
    public static Block RAY_GENERATOR_7 = new RaySolar(baseSetting, 8192, true);
    public static Block RAY_GENERATOR_8 = new RaySolar(baseSetting, 32768, true);
    public static Block RAY_GENERATOR_9 = new RaySolar(baseSetting, 131072, true);
    public static Block RAY_GENERATOR_10 = new RaySolar(baseSetting, 532480, true);

    public static void init() {
        //Registry.register(Registry.BLOCK, AdvancedReborn.createID("rotary_grinder"), ROTARY_GRINDER);
        Registry.register(Registry.BLOCK, AdvancedReborn.createID("charge_pad"), CHARGE_PAD_MK_1);
        Registry.register(Registry.BLOCK, AdvancedReborn.createID("charge_pad_2"), CHARGE_PAD_MK_2);
        Registry.register(Registry.BLOCK, AdvancedReborn.createID("charge_pad_3"), CHARGE_PAD_MK_3);
        Registry.register(Registry.BLOCK, AdvancedReborn.createID("charge_pad_4"), CHARGE_PAD_MK_4);

        Registry.register(Registry.BLOCK, AdvancedReborn.createID("ray_solar_panel"), RAY_SOLAR_1);
        Registry.register(Registry.BLOCK, AdvancedReborn.createID("ray_solar_panel_2"), RAY_SOLAR_2);
        Registry.register(Registry.BLOCK, AdvancedReborn.createID("ray_solar_panel_3"), RAY_SOLAR_3);
        Registry.register(Registry.BLOCK, AdvancedReborn.createID("ray_solar_panel_4"), RAY_SOLAR_4);
        Registry.register(Registry.BLOCK, AdvancedReborn.createID("ray_generator"), RAY_GENERATOR_1);
        Registry.register(Registry.BLOCK, AdvancedReborn.createID("ray_generator_2"), RAY_GENERATOR_2);
        Registry.register(Registry.BLOCK, AdvancedReborn.createID("ray_generator_3"), RAY_GENERATOR_3);
        Registry.register(Registry.BLOCK, AdvancedReborn.createID("ray_generator_4"), RAY_GENERATOR_4);
        Registry.register(Registry.BLOCK, AdvancedReborn.createID("ray_generator_5"), RAY_GENERATOR_5);
        Registry.register(Registry.BLOCK, AdvancedReborn.createID("ray_generator_6"), RAY_GENERATOR_6);
        Registry.register(Registry.BLOCK, AdvancedReborn.createID("ray_generator_7"), RAY_GENERATOR_7);
        Registry.register(Registry.BLOCK, AdvancedReborn.createID("ray_generator_8"), RAY_GENERATOR_8);
        Registry.register(Registry.BLOCK, AdvancedReborn.createID("ray_generator_9"), RAY_GENERATOR_9);
        Registry.register(Registry.BLOCK, AdvancedReborn.createID("ray_generator_10"), RAY_GENERATOR_10);
    }
}
