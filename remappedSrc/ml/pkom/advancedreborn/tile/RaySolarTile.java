package ml.pkom.advancedreborn.tile;

import ml.pkom.advancedreborn.Blocks;
import ml.pkom.advancedreborn.Tiles;
import ml.pkom.advancedreborn.blocks.RaySolar;
import ml.pkom.advancedreborn.event.TileCreateEvent;
import net.minecraft.block.entity.BlockEntityType;
import reborncore.common.powerSystem.PowerAcceptorBlockEntity;

public class RaySolarTile extends PowerAcceptorBlockEntity {

    public RaySolar solar = (RaySolar) Blocks.RAY_SOLAR_1;
    public long energy = 1;

    public RaySolarTile(BlockEntityType<?> type) {
        super(type);
    }

    public RaySolarTile(TileCreateEvent event, RaySolar solar) {
        this();
        this.solar = solar;
        this.energy = solar.energy;
    }

    public RaySolarTile() {
        this(Tiles.RAY_SOLAR_TILE);
    }

    public void tick() {
        super.tick();
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

    @Override
    public double getBaseMaxPower() {
        return energy * 8;
    }

    @Override
    public double getBaseMaxOutput() {
        return energy * 4;
    }

    @Override
    public double getBaseMaxInput() {
        return energy * 4;
    }
}
