package ml.pkom.advancedreborn.blocks;

import ml.pkom.advancedreborn.GuiTypes;
import ml.pkom.advancedreborn.event.TileCreateEvent;
import ml.pkom.advancedreborn.tile.CentrifugalExtractorTile;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.entity.BlockEntity;
import reborncore.api.blockentity.IMachineGuiHandler;

public class CentrifugalExtractor extends AdvancedMachineBlock {

    public CentrifugalExtractor(FabricBlockSettings settings) {
        super(settings);
    }

    public BlockEntity createBlockEntity(TileCreateEvent event) {
        return new CentrifugalExtractorTile(event);
    }

    public IMachineGuiHandler getGui() {
        return GuiTypes.CENTRIFUGAL_EXTRACTOR;
    }
}
