package ml.pkom.advancedreborn.blocks;

import ml.pkom.advancedreborn.event.TileCreateEvent;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import reborncore.api.blockentity.IMachineGuiHandler;
import reborncore.common.blocks.BlockMachineBase;

public abstract class AdvancedMachineBlock extends BlockMachineBase {

    public AdvancedMachineBlock(Settings settings) {
        super(settings);
    }

    // 1.17.1へのポート用
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return createBlockEntity(new TileCreateEvent(pos, state));
    }

    public BlockEntity createBlockEntity(BlockView world) {
        return createBlockEntity(new TileCreateEvent(world));
    }

    public abstract BlockEntity createBlockEntity(TileCreateEvent event);

    public IMachineGuiHandler getGui() {
        return null;
    }
}
