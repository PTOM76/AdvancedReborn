package ml.pkom.advancedreborn.blocks;

import ml.pkom.advancedreborn.GuiTypes;
import ml.pkom.advancedreborn.event.TileCreateEvent;
import ml.pkom.advancedreborn.tile.CanningMachineTile;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import reborncore.api.blockentity.IMachineGuiHandler;

public class CanningMachine extends AdvancedMachineBlock {

    public CanningMachine(Settings settings) {
        super(settings);
    }

    public BlockEntity createBlockEntity(TileCreateEvent event) {
        return new CanningMachineTile(event);
    }

    public BlockEntity createBlockEntity(BlockView world) {
        return createBlockEntity(new TileCreateEvent(world));
    }

    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return createBlockEntity(new TileCreateEvent(pos, state));
    }

    public IMachineGuiHandler getGui() {
        return GuiTypes.CANNING_MACHINE;
    }
}
