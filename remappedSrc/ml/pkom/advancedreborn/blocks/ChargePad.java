package ml.pkom.advancedreborn.blocks;

import ml.pkom.advancedreborn.Particles;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import reborncore.common.blockentity.MachineBaseBlockEntity;
import team.reborn.energy.Energy;
import techreborn.blockentity.storage.energy.EnergyStorageBlockEntity;

import java.util.Random;

public class ChargePad extends Block {

    public int multiple = 4;

    public static final VoxelShape SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.5D, 16.0D);

    public static BooleanProperty USING = BooleanProperty.of("using");
    public static DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    @SuppressWarnings("deprecation")
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @SuppressWarnings("deprecation")
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return state.get(USING) ? 15 : 0;
    }

    public ChargePad(Settings settings, int multiple) {
        super(settings);
        setDefaultState(getDefaultState().with(FACING, Direction.NORTH).with(USING, false));
        this.multiple = multiple;
    }

    public void setFacing(Direction facing, World world, BlockPos pos) {
        world.setBlockState(pos, world.getBlockState(pos).with(FACING, facing));
    }

    public Direction getFacing(BlockState state) {
        return state.get(FACING);
    }

    public void onPlaced(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.onPlaced(worldIn, pos, state, placer, stack);
        if(placer != null) {
            setFacing(placer.getHorizontalFacing().getOpposite(), worldIn, pos);
        }

        BlockEntity blockEntity = worldIn.getBlockEntity(pos);
        if (blockEntity instanceof MachineBaseBlockEntity) {
            ((MachineBaseBlockEntity) blockEntity).onPlace(worldIn, pos, state, placer, stack);
        }
    }

    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, USING);
        super.appendProperties(builder);
    }

    Random random = new Random(256);

    @SuppressWarnings("deprecation")
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        super.onEntityCollision(state, world, pos, entity);
        if (world.isClient()) return;
        if (!(entity instanceof PlayerEntity)) return;
        if (world.getBlockEntity(pos.down()) instanceof EnergyStorageBlockEntity) {
            EnergyStorageBlockEntity tile = (EnergyStorageBlockEntity) world.getBlockEntity(pos.down());
            if (tile == null) return;
            long eu = (long) tile.getEnergy();
            if (eu <= 5) return;
            //if (!tile.canProvideEnergy(EnergySide.UP)) return;
            long outputEU = 0;
            if (tile.getEuPerTick(eu) > tile.getEuPerTick(tile.getBaseMaxOutput() * multiple)) {
                outputEU = (long) tile.getBaseMaxOutput() * multiple;
            } else {
                outputEU = eu;
            }
            //System.out.println("EU: " + eu + ", OutputEU: " + outputEU);
            long storageEU = outputEU;
            PlayerEntity player = (PlayerEntity) entity;
            for (int i = 0; i < player.inventory.size(); i++) {
                if (storageEU <= 0) {
                    break;
                }
                ItemStack invStack = player.inventory.getStack(i);

                if (invStack.isEmpty()) {
                    continue;
                }

                if (Energy.valid(invStack)) {
                    double energy = Energy.of(invStack).getEnergy();
                    Energy.of(invStack).set(energy + storageEU);
                    storageEU -= Energy.of(invStack).getEnergy() - energy;
                }
            }
            tile.setEnergy(eu - outputEU);
            double rX = random.nextInt(9) * 0.1;
            double rZ = random.nextInt(9) * 0.1;
            ((ServerWorld)world).spawnParticles(Particles.ENERGY, pos.getX() + 0.1 + rX, pos.getY() + 0.25, pos.getZ() + 0.1 + rZ, 1, 0, 0.3, 0, 0);
            world.setBlockState(pos, state.with(USING, true));
            world.getBlockTickScheduler().schedule(pos, this, 5);
            world.updateComparators(pos, this);
        }
    }

    @SuppressWarnings("deprecation")
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.scheduledTick(state, world, pos, random);
        world.setBlockState(pos, state.with(USING, false));
        world.updateComparators(pos, this);
    }

    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}
