package ml.pkom.advancedreborn.blocks;

import ml.pkom.advancedreborn.GuiTypes;
import ml.pkom.advancedreborn.event.TileCreateEvent;
import ml.pkom.advancedreborn.tile.RotaryGrinderTile;
import net.minecraft.block.entity.BlockEntity;
import reborncore.api.blockentity.IMachineGuiHandler;

public class RotaryGrinder extends AdvancedMachineBlock {

    public RotaryGrinder(Settings settings) {
        super(settings);
    }

    public BlockEntity createBlockEntity(TileCreateEvent event) {
        return new RotaryGrinderTile(event);
    }

    public IMachineGuiHandler getGui() {
        return GuiTypes.ROTARY_GRINDER;
    }
}
