package ml.pkom.advancedreborn.blocks;

import ml.pkom.advancedreborn.GuiTypes;
import ml.pkom.advancedreborn.event.TileCreateEvent;
import ml.pkom.advancedreborn.tile.InductionFurnaceTile;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.entity.BlockEntity;
import reborncore.api.blockentity.IMachineGuiHandler;

public class InductionFurnace extends AdvancedMachineBlock {

    public InductionFurnace(FabricBlockSettings settings) {
        super(settings);
    }

    public BlockEntity createBlockEntity(TileCreateEvent event) {
        return new InductionFurnaceTile(event);
    }

    public IMachineGuiHandler getGui() {
        return GuiTypes.INDUCTION_FURNACE;
    }
}
