package ml.pkom.advancedreborn;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "advanced_reborn")
public class AdvancedRebornConfig implements ConfigData {

    @Comment("Add Tech Reborn's ore-based tool turtle and more.")
    @ConfigEntry.Category("linkComputerCraft")
    public boolean linkComputerCraft = true;

    @Comment("Rotary Grinder, Centrifugal Extractor, Singularity Compressor Max Input (Value in EU)")
    @ConfigEntry.Category("advanced_machine")
    public int advancedMachineMaxInput = 256;

    @Comment("Rotary Grinder, Centrifugal Extractor, Singularity Compressor Max Energy (Value in EU)")
    @ConfigEntry.Category("advanced_machine")
    public int advancedMachineMaxEnergy = 10000;

    @Comment("Canning Machine Max Input (Value in EU)")
    @ConfigEntry.Category("canning_machine")
    public int canningMachineMaxInput = 32;

    @Comment("Canning Machine Max Energy (Value in EU)")
    @ConfigEntry.Category("canning_machine")
    public int canningMachineMaxEnergy = 1000;
}
