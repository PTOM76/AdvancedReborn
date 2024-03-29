package ml.pkom.advancedreborn;

import ml.pkom.advancedreborn.blocks.*;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;

public class Blocks {

    public static FabricBlockSettings baseSetting = FabricBlockSettings.of(Material.METAL).requiresTool().strength(2, 8);

    public static Block CHARGE_PAD_MK_1 = new ChargePad(baseSetting, 4);
    public static Block CHARGE_PAD_MK_2 = new ChargePad(baseSetting, 16);
    public static Block CHARGE_PAD_MK_3 = new ChargePad(baseSetting, 64);
    public static Block CHARGE_PAD_MK_4 = new ChargePad(baseSetting, 128);
    public static Block CHARGE_PAD_MK_FINAL = new ChargePadFinal(baseSetting, 256);

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


    public static Block INDUCTION_FURNACE = new InductionFurnace(baseSetting);
    public static Block ROTARY_GRINDER = new RotaryGrinder(baseSetting);
    public static Block CENTRIFUGAL_EXTRACTOR = new CentrifugalExtractor(baseSetting);
    public static Block SINGULARITY_COMPRESSOR = new SingularityCompressor(baseSetting);
    public static Block CANNING_MACHINE = new CanningMachine(baseSetting);
    public static Block RENAMING_MACHINE = new RenamingMachine(baseSetting);
    public static Block TELEPORTER = new Teleporter(baseSetting);
    public static Block FARMING_MACHINE = new FarmingMachine(baseSetting);
    public static Block LOGGING_MACHINE = new LoggingMachine(baseSetting);
    public static Block FERTILIZER_SPREADER = new FertilizerSpreader(baseSetting);
    public static Block ENCHANTMENT_EXTRACTOR = new EnchantmentExtractor(baseSetting);


    //breakByHand
    public static Block CARDBOARD_BOX = new CardboardBox(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).strength(1, 3));
    public static Block CARDBOARD_BOX_MINEZON = new CardboardBox(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).strength(1, 3));
    public static Block CARDBOARD_BOX_MINETARO = new CardboardBox(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).strength(1, 3));
    public static Block CARDBOARD_BOX_NOTHING = new CardboardBox(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).strength(1, 3));

    public static Block LIGHT = new Block(FabricBlockSettings.of(Material.METAL).strength(1.5F, 4).luminance((state) -> 15));
    // breakByHand

    public static Block INDUSTRIAL_TNT = new IndustrialTNT(FabricBlockSettings.copyOf(net.minecraft.block.Blocks.TNT));

    public static void init() {
        Registry.register(Registry.BLOCK, AdvancedReborn.id("charge_pad"), CHARGE_PAD_MK_1);
        Registry.register(Registry.BLOCK, AdvancedReborn.id("charge_pad_2"), CHARGE_PAD_MK_2);
        Registry.register(Registry.BLOCK, AdvancedReborn.id("charge_pad_3"), CHARGE_PAD_MK_3);
        Registry.register(Registry.BLOCK, AdvancedReborn.id("charge_pad_4"), CHARGE_PAD_MK_4);
        Registry.register(Registry.BLOCK, AdvancedReborn.id("charge_pad_final"), CHARGE_PAD_MK_FINAL);

        Registry.register(Registry.BLOCK, AdvancedReborn.id("ray_solar_panel"), RAY_SOLAR_1);
        Registry.register(Registry.BLOCK, AdvancedReborn.id("ray_solar_panel_2"), RAY_SOLAR_2);
        Registry.register(Registry.BLOCK, AdvancedReborn.id("ray_solar_panel_3"), RAY_SOLAR_3);
        Registry.register(Registry.BLOCK, AdvancedReborn.id("ray_solar_panel_4"), RAY_SOLAR_4);
        Registry.register(Registry.BLOCK, AdvancedReborn.id("ray_generator"), RAY_GENERATOR_1);
        Registry.register(Registry.BLOCK, AdvancedReborn.id("ray_generator_2"), RAY_GENERATOR_2);
        Registry.register(Registry.BLOCK, AdvancedReborn.id("ray_generator_3"), RAY_GENERATOR_3);
        Registry.register(Registry.BLOCK, AdvancedReborn.id("ray_generator_4"), RAY_GENERATOR_4);
        Registry.register(Registry.BLOCK, AdvancedReborn.id("ray_generator_5"), RAY_GENERATOR_5);
        Registry.register(Registry.BLOCK, AdvancedReborn.id("ray_generator_6"), RAY_GENERATOR_6);
        Registry.register(Registry.BLOCK, AdvancedReborn.id("ray_generator_7"), RAY_GENERATOR_7);
        Registry.register(Registry.BLOCK, AdvancedReborn.id("ray_generator_8"), RAY_GENERATOR_8);
        Registry.register(Registry.BLOCK, AdvancedReborn.id("ray_generator_9"), RAY_GENERATOR_9);
        Registry.register(Registry.BLOCK, AdvancedReborn.id("ray_generator_10"), RAY_GENERATOR_10);

        Registry.register(Registry.BLOCK, AdvancedReborn.id("induction_furnace"), INDUCTION_FURNACE);
        Registry.register(Registry.BLOCK, AdvancedReborn.id("rotary_grinder"), ROTARY_GRINDER);
        Registry.register(Registry.BLOCK, AdvancedReborn.id("centrifugal_extractor"), CENTRIFUGAL_EXTRACTOR);
        Registry.register(Registry.BLOCK, AdvancedReborn.id("singularity_compressor"), SINGULARITY_COMPRESSOR);
        Registry.register(Registry.BLOCK, AdvancedReborn.id("canning_machine"), CANNING_MACHINE);
        Registry.register(Registry.BLOCK, AdvancedReborn.id("renaming_machine"), RENAMING_MACHINE);
        Registry.register(Registry.BLOCK, AdvancedReborn.id("teleporter"), TELEPORTER);
        Registry.register(Registry.BLOCK, AdvancedReborn.id("farming_machine"), FARMING_MACHINE);
        Registry.register(Registry.BLOCK, AdvancedReborn.id("logging_machine"), LOGGING_MACHINE);
        Registry.register(Registry.BLOCK, AdvancedReborn.id("fertilizer_spreader"), FERTILIZER_SPREADER);
        Registry.register(Registry.BLOCK, AdvancedReborn.id("enchantment_extractor"), ENCHANTMENT_EXTRACTOR);

        Registry.register(Registry.BLOCK, AdvancedReborn.id("industrial_tnt"), INDUSTRIAL_TNT);
        Registry.register(Registry.BLOCK, AdvancedReborn.id("light"), LIGHT);
        Registry.register(Registry.BLOCK, AdvancedReborn.id("cardboard_box"), CARDBOARD_BOX);
        Registry.register(Registry.BLOCK, AdvancedReborn.id("cardboard_box_minetaro"), CARDBOARD_BOX_MINETARO);
        Registry.register(Registry.BLOCK, AdvancedReborn.id("cardboard_box_minezon"), CARDBOARD_BOX_MINEZON);
        Registry.register(Registry.BLOCK, AdvancedReborn.id("cardboard_box_nothing_logo"), CARDBOARD_BOX_NOTHING);


    }
}
