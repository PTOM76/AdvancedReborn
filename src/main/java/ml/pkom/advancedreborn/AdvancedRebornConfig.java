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

    @Comment("link Roughly Enough Items with Tech Reborn")
    @ConfigEntry.Category("linkReiWithTR")
    public boolean linkReiWithTR = true;

    @Comment("link Roughly Enough Items with Advanced Reborn")
    @ConfigEntry.Category("linkReiWithAR")
    public boolean linkReiWithAR = true;

    @Comment("link Roughly Enough Items")
    @ConfigEntry.Category("linkRei")
    public boolean linkRei = true;


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

    @Comment("Renaming Machine Max Input (Value in EU)")
    @ConfigEntry.Category("renaming_machine")
    public int renamingMachineMaxInput = 32;

    @Comment("Renaming Machine Max Energy (Value in EU)")
    @ConfigEntry.Category("renaming_machine")
    public int renamingMachineMaxEnergy = 1000;

    @Comment("Renaming Machine Use Energy (Value in EU)")
    @ConfigEntry.Category("renaming_machine")
    public int renamingMachineUseEnergy = 4;

    @Comment("Teleporter Use Energy (Value in EU)")
    @ConfigEntry.Category("teleporter")
    public int teleporterUseEnergy = 64;

    @Comment("If you make a mistake in setting the teleporter and you get an infinite loop, you can fix it by disabling it.")
    @ConfigEntry.Category("teleporter")
    public boolean teleporterEnabled = true;
}
