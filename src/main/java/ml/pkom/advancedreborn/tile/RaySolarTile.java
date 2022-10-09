package ml.pkom.advancedreborn.tile;

import ml.pkom.advancedreborn.Blocks;
import ml.pkom.advancedreborn.Tiles;
import ml.pkom.advancedreborn.blocks.RaySolar;
import ml.pkom.advancedreborn.event.TileCreateEvent;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import reborncore.api.IToolDrop;
import reborncore.common.blockentity.MachineBaseBlockEntity;
import reborncore.common.powerSystem.PowerAcceptorBlockEntity;

public class RaySolarTile extends PowerAcceptorBlockEntity implements IToolDrop {

    public RaySolar solar = (RaySolar) Blocks.RAY_SOLAR_1;
    public long energy = 1;

    public RaySolarTile(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.solar = (RaySolar) state.getBlock();
        this.energy = solar.energy;
    }

    public RaySolarTile(TileCreateEvent event, RaySolar solar) {
        this(event);
        this.solar = solar;
        this.energy = solar.energy;
    }

    public RaySolarTile(TileCreateEvent event) {
        this(Tiles.RAY_SOLAR_TILE, event.getBlockPos(), event.getBlockState());
    }

    // 1.17
    public RaySolarTile(BlockPos pos, BlockState state) {
        this(new TileCreateEvent(pos, state));
    }

    @Override
    public void tick(World world, BlockPos pos, BlockState state, MachineBaseBlockEntity blockEntity2) {
        super.tick(world, pos, state, blockEntity2);
        if (world == null) {
            return;
        }

        if (world.isClient()) {
            return;
        }
        if ((!world.isRaining() && !world.isThundering() && world.isDay() && world.isSkyVisible(pos.up())) || solar.isRayGenerator) {
            addEnergy(getEuPerTick(energy));
        }
    }

    protected boolean canProvideEnergy(@Nullable Direction side) {
        return true;
    }

    public long getBaseMaxPower() {
        return energy * 8;
    }

    public long getBaseMaxOutput() {
        return energy * 4;
    }

    public long getBaseMaxInput() {
        return energy * 4;
    }

    public ItemStack getToolDrop(PlayerEntity p0) {
        return new ItemStack(solar);
    }
}
