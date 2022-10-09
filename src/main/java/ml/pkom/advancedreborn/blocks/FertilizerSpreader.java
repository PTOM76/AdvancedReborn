package ml.pkom.advancedreborn.blocks;

import ml.pkom.advancedreborn.GuiTypes;
import ml.pkom.advancedreborn.event.TileCreateEvent;
import ml.pkom.advancedreborn.tile.FarmingMachineTile;
import ml.pkom.advancedreborn.tile.FertilizerSpreaderTile;
import net.minecraft.block.entity.BlockEntity;
import reborncore.api.blockentity.IMachineGuiHandler;

public class FertilizerSpreader extends AdvancedMachineBlock {
    public FertilizerSpreader(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(TileCreateEvent event) {
        return new FertilizerSpreaderTile(event);
    }

    @Override
    public IMachineGuiHandler getGui() {
        return GuiTypes.FERTILIZER_SPREADER;
    }
}
