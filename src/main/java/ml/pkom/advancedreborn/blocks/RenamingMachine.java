package ml.pkom.advancedreborn.blocks;

import ml.pkom.advancedreborn.GuiTypes;
import ml.pkom.advancedreborn.event.TileCreateEvent;
import ml.pkom.advancedreborn.tile.RenamingMachineTile;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.entity.BlockEntity;
import reborncore.api.blockentity.IMachineGuiHandler;

public class RenamingMachine extends AdvancedMachineBlock {

    public RenamingMachine(FabricBlockSettings settings) {
        super(settings);
    }

    public BlockEntity createBlockEntity(TileCreateEvent event) {
        return new RenamingMachineTile(event);
    }

    public IMachineGuiHandler getGui() {
        return GuiTypes.RENAMING_MACHINE;
    }
}
