package ml.pkom.advancedreborn.blocks;

import ml.pkom.advancedreborn.GuiTypes;
import ml.pkom.advancedreborn.event.TileCreateEvent;
import ml.pkom.advancedreborn.tile.LoggingMachineTile;
import net.minecraft.block.entity.BlockEntity;
import reborncore.api.blockentity.IMachineGuiHandler;

public class LoggingMachine extends AdvancedMachineBlock {
    public LoggingMachine(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(TileCreateEvent event) {
        return new LoggingMachineTile(event);
    }

    @Override
    public IMachineGuiHandler getGui() {
        return GuiTypes.LOGGING_MACHINE;
    }
}
