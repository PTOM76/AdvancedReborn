package ml.pkom.advancedreborn.event;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class TileCreateEvent {

    // ～1.16.5
    private BlockView blockView;

    public TileCreateEvent(BlockView blockView) {
        this.blockView = blockView;
        this.blockPos = null;
        this.blockState = null;
    }

    public BlockView getBlockView() {
        return blockView;
    }

    public void setBlockView(BlockView blockView) {
        this.blockView = blockView;
    }

    public boolean hasBlockView() {
        return (blockView != null);
    }
    // ----

    // 1.17～
    private BlockPos blockPos;
    private BlockState blockState;

    public TileCreateEvent(BlockPos blockPos, BlockState blockState) {
        this.blockView = null;
        this.blockPos = blockPos;
        this.blockState = blockState;
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public void setBlockPos(BlockPos blockPos) {
        this.blockPos = blockPos;
    }

    public boolean hasBlockPos() {
        return (blockPos != null);
    }

    public BlockState getBlockState() {
        return blockState;
    }

    public void setBlockState(BlockState blockState) {
        this.blockState = blockState;
    }

    public boolean hasBlockState() {
        return (blockState != null);
    }
    // ----
}