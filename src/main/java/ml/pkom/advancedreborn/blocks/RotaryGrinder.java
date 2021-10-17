package ml.pkom.advancedreborn.blocks;

import ml.pkom.advancedreborn.event.TileCreateEvent;
import ml.pkom.advancedreborn.tile.RotaryGrinderTile;
import net.minecraft.block.entity.BlockEntity;
import reborncore.api.blockentity.IMachineGuiHandler;

public class RotaryGrinder extends AdvancedMachineBlock {

    public RotaryGrinder(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(TileCreateEvent event) {
        return new RotaryGrinderTile(event);
    }

    @Override
    public IMachineGuiHandler getGui() {
        return super.getGui();
    }
}
