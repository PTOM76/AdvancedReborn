package ml.pkom.advancedreborn;

import ml.pkom.advancedreborn.armormaterials.BBArmorMaterial;
import ml.pkom.advancedreborn.armormaterials.NanoArmorMaterial;
import ml.pkom.advancedreborn.items.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import reborncore.common.powerSystem.RcEnergyTier;
import techreborn.config.TechRebornConfig;

public class Items {
    public static FabricItemSettings baseSettings = new FabricItemSettings().group(AdvancedReborn.AR_GROUP).maxCount(64);
    public static FabricItemSettings baseSettingsMaxOne = new FabricItemSettings().group(AdvancedReborn.AR_GROUP).maxCount(1).maxDamage(-1);
    public static FabricItemSettings nothingSettings = new FabricItemSettings();

    public static Item CHARGE_PAD_MK_1 = new BlockItem(Blocks.CHARGE_PAD_MK_1, baseSettings);
    public static Item CHARGE_PAD_MK_2 = new BlockItem(Blocks.CHARGE_PAD_MK_2, baseSettings);
    public static Item CHARGE_PAD_MK_3 = new BlockItem(Blocks.CHARGE_PAD_MK_3, baseSettings);
    public static Item CHARGE_PAD_MK_4 = new BlockItem(Blocks.CHARGE_PAD_MK_4, baseSettings);
    public static Item CHARGE_PAD_MK_FINAL = new BlockItem(Blocks.CHARGE_PAD_MK_FINAL, baseSettings);

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

    public static Item INDUCTION_FURNACE = new BlockItem(Blocks.INDUCTION_FURNACE, baseSettings);
    public static Item ROTARY_GRINDER = new BlockItem(Blocks.ROTARY_GRINDER, baseSettings);
    public static Item CENTRIFUGAL_EXTRACTOR = new BlockItem(Blocks.CENTRIFUGAL_EXTRACTOR, baseSettings);
    public static Item SINGULARITY_COMPRESSOR = new BlockItem(Blocks.SINGULARITY_COMPRESSOR, baseSettings);
    public static Item CANNING_MACHINE = new BlockItem(Blocks.CANNING_MACHINE, baseSettings);
    public static Item RENAMING_MACHINE = new BlockItem(Blocks.RENAMING_MACHINE, baseSettings);
    public static Item TELEPORTER = new BlockItem(Blocks.TELEPORTER, baseSettings);

    public static Item FREQ_TRANS = new FreqTrans(baseSettingsMaxOne);
    public static Item CONFIG_WRENCH = new ConfigWrench(baseSettingsMaxOne);
    public static Item FORGE_HAMMER;
    public static Item ADVANCED_FORGE_HAMMER;

    static {
        // 実装しないでおく
        FORGE_HAMMER = new ForgeHammer(nothingSettings.recipeRemainder(FORGE_HAMMER), 80);
        ADVANCED_FORGE_HAMMER = new ForgeHammer(nothingSettings.recipeRemainder(ADVANCED_FORGE_HAMMER), 360);
    }

    public static Item NANO_SUIT_HELMET = new NanoSuitItem(NanoArmorMaterial.NANO, EquipmentSlot.HEAD, baseSettingsMaxOne);
    public static Item NANO_SUIT_BODY_ARMOR = new NanoSuitItem(NanoArmorMaterial.NANO, EquipmentSlot.CHEST, baseSettingsMaxOne);
    public static Item NANO_SUIT_LEGGINGS = new NanoSuitItem(NanoArmorMaterial.NANO, EquipmentSlot.LEGS, baseSettingsMaxOne);
    public static Item NANO_SUIT_BOOTS = new NanoSuitItem(NanoArmorMaterial.NANO, EquipmentSlot.FEET, baseSettingsMaxOne);

    // 強化バッテリー
    public static Item ADVANCED_BATTERY = new AdvancedBattery(baseSettingsMaxOne, 8 * TechRebornConfig.redCellBatteryMaxCharge, RcEnergyTier.HIGH);
    public static Item ADVANCED_BATTERY_2 = new AdvancedBattery(baseSettingsMaxOne, 64 * TechRebornConfig.redCellBatteryMaxCharge, RcEnergyTier.HIGH);
    public static Item ADVANCED_BATTERY_3 = new AdvancedBattery(baseSettingsMaxOne, 512 * TechRebornConfig.redCellBatteryMaxCharge, RcEnergyTier.HIGH);
    public static Item ADVANCED_BATTERY_4 = new AdvancedBattery(baseSettingsMaxOne, 4096 * TechRebornConfig.redCellBatteryMaxCharge, RcEnergyTier.HIGH);
    public static Item ADVANCED_BATTERY_5 = new AdvancedBattery(baseSettingsMaxOne, 32768 * TechRebornConfig.redCellBatteryMaxCharge, RcEnergyTier.EXTREME);

    // ダイナマイト (予定: 時限爆弾)
    public static Item DYNAMITE = new Dynamite(baseSettings);
    public static Item STICKY_DYNAMITE = new Dynamite(baseSettings,true);
    public static Item INDUSTRIAL_DYNAMITE = new Dynamite(baseSettings,false, true);
    public static Item INDUSTRIAL_STICKY_DYNAMITE = new Dynamite(baseSettings,true, true);
    public static Item INDUSTRIAL_TNT = new BlockItem(Blocks.INDUSTRIAL_TNT, baseSettings);

    // ライト、足場(鉄) 強化石材  ネーミングマシン
    public static Item LIGHT = new BlockItem(Blocks.LIGHT, baseSettings);

    public static Item CARDBOARD_BOX = new BlockItem(Blocks.CARDBOARD_BOX, baseSettings);
    public static Item CARDBOARD_BOX_MINETARO = new BlockItem(Blocks.CARDBOARD_BOX_MINETARO, baseSettings);
    public static Item CARDBOARD_BOX_MINEZON = new BlockItem(Blocks.CARDBOARD_BOX_MINEZON, baseSettings);
    public static Item CARDBOARD_BOX_NOTHING = new BlockItem(Blocks.CARDBOARD_BOX_NOTHING, baseSettings);

    // 缶
    public static Item EMPTY_CAN = new Item(baseSettings);
    public static Item FUEL_CAN = new FuelCanItem(new FabricItemSettings().group(AdvancedReborn.AR_GROUP).maxCount(64).recipeRemainder(EMPTY_CAN));
    public static Item FOOD_CAN = new FoodCanItem(new FabricItemSettings().group(AdvancedReborn.AR_GROUP).maxCount(64).recipeRemainder(EMPTY_CAN).food(new FoodComponent.Builder().snack().hunger(2).build()));
    //public static Item FISH_CAN = new Item(new FabricItemSettings().group(AdvancedReborn.AR_GROUP).maxCount(128).recipeRemainder(EMPTY_CAN).food(new FoodComponent.Builder().snack().hunger(2).build()));
    //public static Item BREAD_CAN = new Item(new FabricItemSettings().group(AdvancedReborn.AR_GROUP).maxCount(128).recipeRemainder(EMPTY_CAN).food(new FoodComponent.Builder().snack().hunger(2).build()));

    // Better Batpack
    public static Item BATPACK_4 = new BetterBatpackItem(baseSettingsMaxOne, TechRebornConfig.lithiumBatpackCharge * 4, new BBArmorMaterial("batpack4"), RcEnergyTier.MEDIUM);
    public static Item BATPACK_16 = new BetterBatpackItem(baseSettingsMaxOne, TechRebornConfig.lithiumBatpackCharge * 16, new BBArmorMaterial("batpack16"), RcEnergyTier.MEDIUM);
    public static Item BATPACK_64 = new BetterBatpackItem(baseSettingsMaxOne, TechRebornConfig.lithiumBatpackCharge * 64, new BBArmorMaterial("batpack64"), RcEnergyTier.MEDIUM);
    public static Item BATPACK_128 = new BetterBatpackItem(baseSettingsMaxOne, TechRebornConfig.lithiumBatpackCharge * 128, new BBArmorMaterial("batpack128"), RcEnergyTier.MEDIUM);



    // 素材アイテム
    public static Item DUCT_TAPE = new Item(baseSettings);
    public static Item CARDBOARD_SHEET = new Item(baseSettings);

    public static Item ADD_ITEMS = new AddItems(baseSettings);

    public static void init() {
        Registry.register(Registry.ITEM, AdvancedReborn.createID("charge_pad"), CHARGE_PAD_MK_1);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("charge_pad_2"), CHARGE_PAD_MK_2);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("charge_pad_3"), CHARGE_PAD_MK_3);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("charge_pad_4"), CHARGE_PAD_MK_4);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("charge_pad_final"), CHARGE_PAD_MK_FINAL);

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

        Registry.register(Registry.ITEM, AdvancedReborn.createID("induction_furnace"), INDUCTION_FURNACE);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("rotary_grinder"), ROTARY_GRINDER);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("centrifugal_extractor"), CENTRIFUGAL_EXTRACTOR);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("singularity_compressor"), SINGULARITY_COMPRESSOR);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("canning_machine"), CANNING_MACHINE);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("renaming_machine"), RENAMING_MACHINE);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("teleporter"), TELEPORTER);

        Registry.register(Registry.ITEM, AdvancedReborn.createID("freq_trans"), FREQ_TRANS);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("config_wrench"), CONFIG_WRENCH);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("forge_hammer"), FORGE_HAMMER);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("advanced_forge_hammer"), ADVANCED_FORGE_HAMMER);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("dynamite"), DYNAMITE);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("sticky_dynamite"), STICKY_DYNAMITE);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("industrial_dynamite"), INDUSTRIAL_DYNAMITE);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("industrial_sticky_dynamite"), INDUSTRIAL_STICKY_DYNAMITE);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("industrial_tnt"), INDUSTRIAL_TNT);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("light"), LIGHT);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("cardboard_box"), CARDBOARD_BOX);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("cardboard_box_minetaro"), CARDBOARD_BOX_MINETARO);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("cardboard_box_minezon"), CARDBOARD_BOX_MINEZON);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("cardboard_box_nothing_logo"), CARDBOARD_BOX_NOTHING);

        Registry.register(Registry.ITEM, AdvancedReborn.createID("advanced_battery"), ADVANCED_BATTERY);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("advanced_battery_2"), ADVANCED_BATTERY_2);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("advanced_battery_3"), ADVANCED_BATTERY_3);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("advanced_battery_4"), ADVANCED_BATTERY_4);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("advanced_battery_5"), ADVANCED_BATTERY_5);

        Registry.register(Registry.ITEM, AdvancedReborn.createID("nano_helmet"), NANO_SUIT_HELMET);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("nano_chestplate"), NANO_SUIT_BODY_ARMOR);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("nano_leggings"), NANO_SUIT_LEGGINGS);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("nano_boots"), NANO_SUIT_BOOTS);

        Registry.register(Registry.ITEM, AdvancedReborn.createID("empty_can"), EMPTY_CAN);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("fuel_can"), FUEL_CAN);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("food_can"), FOOD_CAN);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("cardboard_sheet"), CARDBOARD_SHEET);
        Registry.register(Registry.ITEM, AdvancedReborn.createID("duct_tape"), DUCT_TAPE);

        Registry.register(Registry.ITEM, "better_batpack:batpack4", BATPACK_4);
        Registry.register(Registry.ITEM, "better_batpack:batpack16", BATPACK_16);
        Registry.register(Registry.ITEM, "better_batpack:batpack64", BATPACK_64);
        Registry.register(Registry.ITEM, "better_batpack:batpack128", BATPACK_128);

        Registry.register(Registry.ITEM, AdvancedReborn.createID("z_add_items"), ADD_ITEMS);
    }
}
