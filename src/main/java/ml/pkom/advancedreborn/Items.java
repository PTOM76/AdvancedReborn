package ml.pkom.advancedreborn;

import ml.pkom.advancedreborn.armormaterials.NanoArmorMaterial;
import ml.pkom.advancedreborn.items.AdvancedBattery;
import ml.pkom.advancedreborn.items.ConfigWrench;
import ml.pkom.advancedreborn.items.ForgeHammer;
import ml.pkom.advancedreborn.items.NanoSuitItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import team.reborn.energy.EnergyTier;
import techreborn.config.TechRebornConfig;

public class Items {
    public static FabricItemSettings baseSettings = new FabricItemSettings().group(AdvancedReborn.AR_GROUP);
    public static FabricItemSettings nothingSettings = new FabricItemSettings();

    public static Item ROTARY_GRINDER = new BlockItem(Blocks.ROTARY_GRINDER, baseSettings);
    public static Item CHARGE_PAD_MK_1 = new BlockItem(Blocks.CHARGE_PAD_MK_1, baseSettings);
    public static Item CHARGE_PAD_MK_2 = new BlockItem(Blocks.CHARGE_PAD_MK_2, baseSettings);
    public static Item CHARGE_PAD_MK_3 = new BlockItem(Blocks.CHARGE_PAD_MK_3, baseSettings);
    public static Item CHARGE_PAD_MK_4 = new BlockItem(Blocks.CHARGE_PAD_MK_4, baseSettings);

    public static Item RAY_SOLAR_1 = new BlockItem(Blocks.RAY_SOLAR_1, baseSettings);
    public static Item RAY_SOLAR_2 = new BlockItem(Blocks.RAY_SOLAR_2, baseSettings);
    public static Item RAY_SOLAR_3 = new BlockItem(Blocks.RAY_SOLAR_3, baseSettings);
    public static Item RAY_SOLAR_4 = new BlockItem(Blocks.RAY_SOLAR_4, baseSettings);
    public static Item RAY_GENERATOR_1 = new BlockItem(Blocks.RAY_GENERATOR_1, baseSettings);
    public static Item RAY_GENERATOR_2 = new BlockItem(Blocks.RAY_GENERATOR_2, baseSettings);
    public static Item RAY_GENERATOR_3 = new BlockItem(Blocks.RAY_GENERATOR_3, baseSettings);
    public static Item RAY_GENERATOR_4 = new BlockItem(Blocks.RAY_GENERATOR_4, baseSettings);
    public static Item RAY_GENERATOR_5 = new BlockItem(Blocks.RAY_GENERATOR_5, baseSettings);
    public static Item RAY_GENERATOR_6 = new BlockItem(Blocks.RAY_GENERATOR_6, baseSettings);
    public static Item RAY_GENERATOR_7 = new BlockItem(Blocks.RAY_GENERATOR_7, baseSettings);
    public static Item RAY_GENERATOR_8 = new BlockItem(Blocks.RAY_GENERATOR_8, baseSettings);
    public static Item RAY_GENERATOR_9 = new BlockItem(Blocks.RAY_GENERATOR_9, baseSettings);
    public static Item RAY_GENERATOR_10 = new BlockItem(Blocks.RAY_GENERATOR_10, baseSettings);

    public static Item CONFIG_WRENCH = new ConfigWrench(baseSettings);
    public static Item FORGE_HAMMER;
    public static Item ADVANCED_FORGE_HAMMER;

    static {
        // 実装しないでおく
        FORGE_HAMMER = new ForgeHammer(nothingSettings.recipeRemainder(FORGE_HAMMER), 80);
        ADVANCED_FORGE_HAMMER = new ForgeHammer(nothingSettings.recipeRemainder(ADVANCED_FORGE_HAMMER), 360);
    }

    public static Item NANO_SUIT_HELMET = new NanoSuitItem(NanoArmorMaterial.NANO, EquipmentSlot.HEAD, baseSettings);
    public static Item NANO_SUIT_BODY_ARMOR = new NanoSuitItem(NanoArmorMaterial.NANO, EquipmentSlot.CHEST, baseSettings);
    public static Item NANO_SUIT_LEGGINGS = new NanoSuitItem(NanoArmorMaterial.NANO, EquipmentSlot.LEGS, baseSettings);
    public static Item NANO_SUIT_BOOTS = new NanoSuitItem(NanoArmorMaterial.NANO, EquipmentSlot.FEET, baseSettings);

    // 強化バッテリー
    public static Item ADVANCED_BATTERY = new AdvancedBattery(baseSettings, 8 * TechRebornConfig.redCellBatteryMaxCharge, EnergyTier.HIGH);
    public static Item ADVANCED_BATTERY_2 = new AdvancedBattery(baseSettings, 64 * TechRebornConfig.redCellBatteryMaxCharge, EnergyTier.HIGH);
    public static Item ADVANCED_BATTERY_3 = new AdvancedBattery(baseSettings, 512 * TechRebornConfig.redCellBatteryMaxCharge, EnergyTier.HIGH);
    public static Item ADVANCED_BATTERY_4 = new AdvancedBattery(baseSettings, 4096 * TechRebornConfig.redCellBatteryMaxCharge, EnergyTier.HIGH);
    public static Item ADVANCED_BATTERY_5 = new AdvancedBattery(baseSettings, 32768 * TechRebornConfig.redCellBatteryMaxCharge, EnergyTier.EXTREME);

    public static void init() {
        //Registry.register(Registry.ITEM, AdvancedReborn.createID("rotary_grinder"), ROTARY_GRINDER);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("charge_pad"), CHARGE_PAD_MK_1);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("charge_pad_2"), CHARGE_PAD_MK_2);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("charge_pad_3"), CHARGE_PAD_MK_3);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("charge_pad_4"), CHARGE_PAD_MK_4);

        Registry.register(Registry.ITEM, AdvancedReborn.createID("ray_solar_panel"), RAY_SOLAR_1);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("ray_solar_panel_2"), RAY_SOLAR_2);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("ray_solar_panel_3"), RAY_SOLAR_3);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("ray_solar_panel_4"), RAY_SOLAR_4);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("ray_generator"), RAY_GENERATOR_1);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("ray_generator_2"), RAY_GENERATOR_2);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("ray_generator_3"), RAY_GENERATOR_3);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("ray_generator_4"), RAY_GENERATOR_4);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("ray_generator_5"), RAY_GENERATOR_5);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("ray_generator_6"), RAY_GENERATOR_6);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("ray_generator_7"), RAY_GENERATOR_7);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("ray_generator_8"), RAY_GENERATOR_8);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("ray_generator_9"), RAY_GENERATOR_9);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("ray_generator_10"), RAY_GENERATOR_10);

        Registry.register(Registry.ITEM, AdvancedReborn.createID("config_wrench"), CONFIG_WRENCH);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("forge_hammer"), FORGE_HAMMER);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("advanced_forge_hammer"), ADVANCED_FORGE_HAMMER);

        Registry.register(Registry.ITEM, AdvancedReborn.createID("advanced_battery"), ADVANCED_BATTERY);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("advanced_battery_2"), ADVANCED_BATTERY_2);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("advanced_battery_3"), ADVANCED_BATTERY_3);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("advanced_battery_4"), ADVANCED_BATTERY_4);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("advanced_battery_5"), ADVANCED_BATTERY_5);

        Registry.register(Registry.ITEM, AdvancedReborn.createID("nano_helmet"), NANO_SUIT_HELMET);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("nano_chestplate"), NANO_SUIT_BODY_ARMOR);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("nano_leggings"), NANO_SUIT_LEGGINGS);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("nano_boots"), NANO_SUIT_BOOTS);

    }
}
