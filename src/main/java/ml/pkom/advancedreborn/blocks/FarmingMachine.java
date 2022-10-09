package ml.pkom.advancedreborn.blocks;

import ml.pkom.advancedreborn.GuiTypes;
import ml.pkom.advancedreborn.event.TileCreateEvent;
import ml.pkom.advancedreborn.tile.FarmingMachineTile;
import net.minecraft.block.entity.BlockEntity;
import reborncore.api.blockentity.IMachineGuiHandler;

public class FarmingMachine extends AdvancedMachineBlock {
    public FarmingMachine(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(TileCreateEvent event) {
        return new FarmingMachineTile(event);
    }

    @Override
    public IMachineGuiHandler getGui() {
        return GuiTypes.FARMING_MACHINE;
    }
}
