package ml.pkom.advancedreborn;

import reborncore.common.config.Config;

public class AdvancedRebornConfig {
    @Config(config = "machines", category = "rotary_grinder", key = "RotaryGrinderMaxInput", comment = "Rotary Grinder Max Input (Value in EU)")
    public static int rotaryGrinderMaxInput = 64;

    @Config(config = "machines", category = "rotary_grinder", key = "RotaryGrinderMaxEnergy", comment = "Rotary Grinder Max Energy (Value in EU)")
    public static int rotaryGrinderMaxEnergy = 5000;

    @Config(config = "machines", category = "charge_pad", key = "ChargePadMaxEnergy", comment = "Rotary Grinder Max Energy (Value in EU)")
    public static int chargePadMaxEnergy = 5000;

    @Config(config = "machines", category = "charge_pad", key = "ChargePadMax", comment = "Rotary Grinder Max Energy (Value in EU)")
    public static int chargePadMaxInput = 5000;

    @Config(config = "machines", category = "charge_pad", key = "ChargePadMaxEnergy", comment = "Rotary Grinder Max Energy (Value in EU)")
    public static int chargePadMaxOutput = 5000;
}
