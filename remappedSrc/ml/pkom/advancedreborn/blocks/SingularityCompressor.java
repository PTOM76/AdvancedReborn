package ml.pkom.advancedreborn.blocks;

import ml.pkom.advancedreborn.GuiTypes;
import ml.pkom.advancedreborn.event.TileCreateEvent;
import ml.pkom.advancedreborn.tile.SingularityCompressorTile;
import net.minecraft.block.entity.BlockEntity;
import reborncore.api.blockentity.IMachineGuiHandler;

public class SingularityCompressor extends AdvancedMachineBlock {

    public SingularityCompressor(Settings settings) {
        super(settings);
    }

    public BlockEntity createBlockEntity(TileCreateEvent event) {
        return new SingularityCompressorTile(event);
    }

    public IMachineGuiHandler getGui() {
        return GuiTypes.SINGULARITY_COMPRESSOR;
    }
}
