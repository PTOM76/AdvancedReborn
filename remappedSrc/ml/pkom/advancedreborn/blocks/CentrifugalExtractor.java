package ml.pkom.advancedreborn.blocks;

import ml.pkom.advancedreborn.GuiTypes;
import ml.pkom.advancedreborn.event.TileCreateEvent;
import ml.pkom.advancedreborn.tile.CentrifugalExtractorTile;
import net.minecraft.block.entity.BlockEntity;
import reborncore.api.blockentity.IMachineGuiHandler;

public class CentrifugalExtractor extends AdvancedMachineBlock {

    public CentrifugalExtractor(Settings settings) {
        super(settings);
    }

    public BlockEntity createBlockEntity(TileCreateEvent event) {
        return new CentrifugalExtractorTile(event);
    }

    public IMachineGuiHandler getGui() {
        return GuiTypes.CENTRIFUGAL_EXTRACTOR;
    }
}
