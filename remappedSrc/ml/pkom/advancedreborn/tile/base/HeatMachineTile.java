package ml.pkom.advancedreborn.tile.base;

import ml.pkom.advancedreborn.tile.InductionFurnaceTile;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import reborncore.common.powerSystem.PowerAcceptorBlockEntity;

public abstract class HeatMachineTile extends PowerAcceptorBlockEntity {
    public int heat = 0;
    public int heatMultiple = 100; // 上昇する速さ(低いほど早い)

    public HeatMachineTile(BlockEntityType<?> type) {
        super(type);
    }

    public int getHeat() {
        return heat;
    }

    public int getHeatPer() {
        if (world == null) return 0;
        BlockEntity be = world.getBlockEntity(getPos());
        if (!(be instanceof HeatMachineTile)) return 0;
        HeatMachineTile tile = (HeatMachineTile) be;
        float heat = tile.getHeat();
        float heatMultiple = tile.getHeatMultiple();
        return Math.round(heat / heatMultiple);
    }

    public void setHeat(int heat) {
        this.heat = heat;
    }

    public int getHeatMultiple() {
        return heatMultiple;
    }

    public void setHeatMultiple(int heatMultiple) {
        this.heatMultiple = heatMultiple;
    }

    public void addHeat(int amount) {
        setHeat(getHeat() + amount);
    }

    public void tick() {
        super.tick();
        if (world == null) return;
        if (getWorld().isClient()) {
            if (getWorld().isReceivingRedstonePower(getPos())) {
                if (getHeat() <= 100 * getHeatMultiple()) addHeat(1); //+0.1%
            } else if (getHeat() > 0) addHeat(-1);
            return;
        }
        if (getWorld().isReceivingRedstonePower(getPos())) {
            if (getHeat() <= 100 * getHeatMultiple()) addHeat(1); //+0.1%
            useEnergy(1);
        } else if (getHeat() > 0) addHeat(-1);
        if (getHeat() != 0) for (int i = 0;i <= getHeat() / (5 * getHeatMultiple());i++) {
            if (i >= getHeat() / (5 * getHeatMultiple())) break;
            super.tick();
            if (this instanceof InductionFurnaceTile) {
                InductionFurnaceTile tile = (InductionFurnaceTile) this;
                tile.setCookTime(tile.getCookTime() + 1);
            }
        }
    }

    public void readNbt(BlockState blockState, NbtCompound tag) {
        super.readNbt(blockState, tag);
        setHeat(tag.getInt("heat"));
    }

    public NbtCompound writeNbt(NbtCompound tag) {
        tag.putInt("heat", getHeat());
        return super.writeNbt(tag);
    }
}
