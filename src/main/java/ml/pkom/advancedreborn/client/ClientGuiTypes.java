package ml.pkom.advancedreborn.client;

import ml.pkom.advancedreborn.GuiTypes;
import ml.pkom.advancedreborn.gui.*;
import ml.pkom.advancedreborn.tile.*;
import net.minecraft.block.entity.BlockEntity;
import techreborn.blockentity.GuiType;
import techreborn.client.ClientGuiType;
import techreborn.client.GuiFactory;

public class ClientGuiTypes {
    public static ClientGuiType<CanningMachineTile> CANNING_MACHINE = register(GuiTypes.CANNING_MACHINE, GuiCanningMachine::new);
    public static ClientGuiType<RenamingMachineTile> RENAMING_MACHINE = register(GuiTypes.RENAMING_MACHINE, GuiRenamingMachine::new);
    public static ClientGuiType<InductionFurnaceTile> INDUCTION_FURNACE = register(GuiTypes.INDUCTION_FURNACE, GuiInductionFurnace::new);
    public static ClientGuiType<RotaryGrinderTile> ROTARY_GRINDER = register(GuiTypes.ROTARY_GRINDER, GuiRotaryGrinder::new);
    public static ClientGuiType<CentrifugalExtractorTile> CENTRIFUGAL_EXTRACTOR = register(GuiTypes.CENTRIFUGAL_EXTRACTOR, GuiCentrifugalExtractor::new);
    public static ClientGuiType<SingularityCompressorTile> SINGULARITY_COMPRESSOR = register(GuiTypes.SINGULARITY_COMPRESSOR, GuiSingularityCompressor::new);

    public static <T extends BlockEntity> ClientGuiType<T> register(GuiType<T> type, GuiFactory<T> factory) {
        return new ClientGuiType<>(type, factory);
    }

    public static void init() {
    }
}
