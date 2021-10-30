package ml.pkom.advancedreborn;

import ml.pkom.advancedreborn.gui.*;
import ml.pkom.advancedreborn.tile.*;
import net.minecraft.block.entity.BlockEntity;
import techreborn.client.GuiType;

import java.util.function.Supplier;

public class GuiTypes {
    public static GuiType<CanningMachineTile> CANNING_MACHINE = register(AdvancedReborn.MOD_ID + "__canning_machine", () -> () -> GuiCanningMachine::new);
    public static GuiType<RenamingMachineTile> RENAMING_MACHINE = register(AdvancedReborn.MOD_ID + "__renaming_machine", () -> () -> GuiRenamingMachine::new);
    public static GuiType<InductionFurnaceTile> INDUCTION_FURNACE = register(AdvancedReborn.MOD_ID + "__induction_furnace_machine", () -> () -> GuiInductionFurnace::new);
    public static GuiType<RotaryGrinderTile> ROTARY_GRINDER = register(AdvancedReborn.MOD_ID + "__rotary_grinder_machine", () -> () -> GuiRotaryGrinder::new);
    public static GuiType<CentrifugalExtractorTile> CENTRIFUGAL_EXTRACTOR = register(AdvancedReborn.MOD_ID + "__centrifugal_extractor_machine", () -> () -> GuiCentrifugalExtractor::new);
    public static GuiType<SingularityCompressorTile> SINGULARITY_COMPRESSOR = register(AdvancedReborn.MOD_ID + "__singularity_compressor_machine", () -> () -> GuiSingularityCompressor::new);

    public static <T extends BlockEntity> GuiType<T> register(String id, Supplier<Supplier<GuiType.GuiFactory<T>>> factorySupplierMeme) {
        return GuiType.register(AdvancedReborn.createID(id), factorySupplierMeme);
    }

    public static void init() {

    }
}
