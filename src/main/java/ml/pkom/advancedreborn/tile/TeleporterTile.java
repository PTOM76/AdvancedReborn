package ml.pkom.advancedreborn.tile;

import ml.pkom.advancedreborn.Tiles;
import ml.pkom.advancedreborn.addons.autoconfig.AutoConfigAddon;
import ml.pkom.advancedreborn.event.TileCreateEvent;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import techreborn.blockentity.storage.energy.EnergyStorageBlockEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TeleporterTile extends BlockEntity implements BlockEntityTicker<TeleporterTile> {

    private static VoxelShape SHAPE_RANGE = VoxelShapes.cuboid(-2, -2, -2, 3, 3, 3);
    private BlockPos teleportPos = null;

    public TeleporterTile(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public TeleporterTile(BlockPos pos, BlockState state) {
        this(Tiles.TELEPORTER_TILE, pos, state);
    }

    public TeleporterTile(TileCreateEvent event) {
        this(event.getBlockPos(), event.getBlockState());
    }

    public void tick(World world, BlockPos pos, BlockState state, TeleporterTile tile) {
        if (!AutoConfigAddon.getConfig().teleporterEnabled) return;
        if (world == null) return;
        if (world.isClient()) return;
        if (getTeleportPos() == null) return;
        List<Entity> entities = getEntities();
        if (entities.isEmpty()) return;
        if (!world.isReceivingRedstonePower(getPos())) return;
        if (use()) {
            for (Entity entity : entities) {
                entity.teleport(getTeleportPos().getX() - 0.5D, getTeleportPos().getY() - 0.5D, getTeleportPos().getZ() - 0.5D);
                return;
            }
        }
    }

    public boolean useTile(BlockEntity blockEntity) {
        if (blockEntity instanceof EnergyStorageBlockEntity) {
            EnergyStorageBlockEntity energyStorage = (EnergyStorageBlockEntity) blockEntity;
            if (energyStorage.getEnergy() >= AutoConfigAddon.getConfig().teleporterUseEnergy) {
                energyStorage.useEnergy(AutoConfigAddon.getConfig().teleporterUseEnergy);
                return true;
            }
        }
        return false;
    }

    public boolean use() {
        if (world == null) return false;
        BlockEntity up = world.getBlockEntity(pos.up());
        BlockEntity down =  world.getBlockEntity(pos.down());
        BlockEntity north = world.getBlockEntity(pos.north());
        BlockEntity south = world.getBlockEntity(pos.south());
        BlockEntity east =  world.getBlockEntity(pos.east());
        BlockEntity west =  world.getBlockEntity(pos.west());

        if (useTile(up))
            return true;
        if (useTile(down))
            return true;
        if (useTile(north))
            return true;
        if (useTile(south))
            return true;
        if (useTile(east))
            return true;
        if (useTile(west))
            return true;
        return false;
    }

    @Nullable
    public BlockPos getTeleportPos() {
        return teleportPos;
    }

    @Nullable
    public void setTeleportPos(BlockPos teleportPos) {
        //System.out.println(teleportPos);
        this.teleportPos = teleportPos;
    }

    public List<Entity> getEntities() {
        VoxelShape voxelShape = SHAPE_RANGE;
        try {
            return voxelShape.getBoundingBoxes().stream().flatMap((box) -> getWorld().getEntitiesByClass(Entity.class, box.offset(getX(), getY(), getZ()), EntityPredicates.VALID_ENTITY).stream()).collect(Collectors.toList());
        } catch (NullPointerException e) {
            return new ArrayList<>();
        }
    }

    public double getX() {
        return getPos().getX();
    }

    public double getY() {
        return getPos().getY();
    }

    public double getZ() {
        return getPos().getZ();
    }

    public NbtCompound writeNbt(NbtCompound nbt) {
        if (getTeleportPos() != null) {
            nbt.putDouble("tpX", getTeleportPos().getX());
            nbt.putDouble("tpY", getTeleportPos().getY());
            nbt.putDouble("tpZ", getTeleportPos().getZ());
        }
        return super.writeNbt(nbt);
    }

    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        if (tag.contains("tpX") && tag.contains("tpY") && tag.contains("tpZ")) teleportPos = new BlockPos(tag.getDouble("tpX"), tag.getDouble("tpY"), tag.getDouble("tpZ"));
    }
}
