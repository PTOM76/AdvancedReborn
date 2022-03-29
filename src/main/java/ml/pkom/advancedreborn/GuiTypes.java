package ml.pkom.advancedreborn;

import ml.pkom.advancedreborn.tile.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Identifier;
import techreborn.blockentity.GuiType;

public class GuiTypes {
    public static GuiType<CanningMachineTile> CANNING_MACHINE = register(AdvancedReborn.MOD_ID + "__canning_machine");
    public static GuiType<RenamingMachineTile> RENAMING_MACHINE = register(AdvancedReborn.MOD_ID + "__renaming_machine");
    public static GuiType<InductionFurnaceTile> INDUCTION_FURNACE = register(AdvancedReborn.MOD_ID + "__induction_furnace_machine");
    public static GuiType<RotaryGrinderTile> ROTARY_GRINDER = register(AdvancedReborn.MOD_ID + "__rotary_grinder_machine");
    public static GuiType<CentrifugalExtractorTile> CENTRIFUGAL_EXTRACTOR = register(AdvancedReborn.MOD_ID + "__centrifugal_extractor_machine");
    public static GuiType<SingularityCompressorTile> SINGULARITY_COMPRESSOR = register(AdvancedReborn.MOD_ID + "__singularity_compressor_machine");

    public static <T extends BlockEntity> GuiType<T> register(String id) {
        return GuiType.register(new Identifier(id));
    }

    public static void init() {

    }
}
