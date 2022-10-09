package ml.pkom.advancedreborn;

import ml.pkom.advancedreborn.tile.*;
import net.minecraft.block.entity.BlockEntity;
import techreborn.blockentity.GuiType;

public class GuiTypes {
    public static GuiType<CanningMachineTile> CANNING_MACHINE = register(AdvancedReborn.MOD_ID + "__canning_machine");
    public static GuiType<RenamingMachineTile> RENAMING_MACHINE = register(AdvancedReborn.MOD_ID + "__renaming_machine");
    public static GuiType<InductionFurnaceTile> INDUCTION_FURNACE = register(AdvancedReborn.MOD_ID + "__induction_furnace_machine");
    public static GuiType<RotaryGrinderTile> ROTARY_GRINDER = register(AdvancedReborn.MOD_ID + "__rotary_grinder_machine");
    public static GuiType<CentrifugalExtractorTile> CENTRIFUGAL_EXTRACTOR = register(AdvancedReborn.MOD_ID + "__centrifugal_extractor_machine");
    public static GuiType<SingularityCompressorTile> SINGULARITY_COMPRESSOR = register(AdvancedReborn.MOD_ID + "__singularity_compressor_machine");
    public static GuiType<FarmingMachineTile> FARMING_MACHINE = register(AdvancedReborn.MOD_ID + "__farming_machine");
    public static GuiType<LoggingMachineTile> LOGGING_MACHINE = register(AdvancedReborn.MOD_ID + "__logging_machine");
    public static GuiType<FertilizerSpreaderTile> FERTILIZER_SPREADER = register(AdvancedReborn.MOD_ID + "__fertilizer_spreader");
    public static GuiType<EnchantmentExtractorTile> ENCHANTMENT_EXTRACTOR = register(AdvancedReborn.MOD_ID + "__enchantment_extractor");

    public static <T extends BlockEntity> GuiType<T> register(String id) {
        return GuiType.register(AdvancedReborn.id(id));
    }

    public static void init() {

    }
}
