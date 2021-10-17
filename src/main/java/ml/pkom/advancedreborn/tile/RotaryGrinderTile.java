package ml.pkom.advancedreborn.tile;

import ml.pkom.advancedreborn.AdvancedRebornConfig;
import ml.pkom.advancedreborn.Tiles;
import ml.pkom.advancedreborn.event.TileCreateEvent;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import reborncore.client.screen.BuiltScreenHandlerProvider;
import reborncore.client.screen.builder.BuiltScreenHandler;
import reborncore.client.screen.builder.ScreenHandlerBuilder;
import reborncore.common.powerSystem.PowerAcceptorBlockEntity;

public class RotaryGrinderTile extends PowerAcceptorBlockEntity implements BuiltScreenHandlerProvider {

    public RotaryGrinderTile(BlockEntityType<?> type) {
        super(type);
    }

    public RotaryGrinderTile() {
        this(Tiles.ROTARY_GRINDER_TILE);
    }

    public RotaryGrinderTile(BlockPos pos, BlockState state) {
        this(Tiles.ROTARY_GRINDER_TILE, pos, state);
    }

    public RotaryGrinderTile(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        this();
    }

    public RotaryGrinderTile(TileCreateEvent event) {
        this(event.getBlockPos(), event.getBlockState());
    }

    @Override
    public BuiltScreenHandler createScreenHandler(int syncID, PlayerEntity player) {
        return new ScreenHandlerBuilder("rotary_grinder").player(player.inventory).inventory().hotbar().addInventory()
                .blockEntity(this).slot(0, 55, 45).outputSlot(1, 101, 45).energySlot(2, 8, 72).syncEnergyValue()
                .syncCrafterValue().addInventory().create(this, syncID);
    }

    @Override
    public double getBaseMaxPower() {
        return AdvancedRebornConfig.rotaryGrinderMaxEnergy;
    }

    @Override
    public double getBaseMaxOutput() {
        return 0;
    }

    @Override
    public double getBaseMaxInput() {
        return AdvancedRebornConfig.rotaryGrinderMaxInput;
    }
}
