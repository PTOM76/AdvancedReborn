package ml.pkom.advancedreborn.tile;

import ml.pkom.advancedreborn.AdvancedReborn;
import ml.pkom.advancedreborn.Blocks;
import ml.pkom.advancedreborn.Tiles;
import ml.pkom.advancedreborn.addons.autoconfig.AutoConfigAddon;
import ml.pkom.advancedreborn.event.TileCreateEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.apache.commons.lang3.ArrayUtils;
import reborncore.api.IToolDrop;
import reborncore.api.blockentity.InventoryProvider;
import reborncore.common.blockentity.MachineBaseBlockEntity;
import reborncore.common.blocks.BlockMachineBase;
import reborncore.common.powerSystem.PowerAcceptorBlockEntity;
import reborncore.common.screen.BuiltScreenHandler;
import reborncore.common.screen.BuiltScreenHandlerProvider;
import reborncore.common.screen.builder.ScreenHandlerBuilder;
import reborncore.common.util.RebornInventory;

import java.util.ArrayList;
import java.util.List;

public class FarmingMachineTile extends PowerAcceptorBlockEntity implements IToolDrop, InventoryProvider, BuiltScreenHandlerProvider {

    public Block toolDrop;
    public int energySlot;

    public RebornInventory<?> inventory;

    public int[] harvestItemSlotIndex = new int[] {4, 5, 6, 7, 8};
    public int[] plantItemSlotIndex = new int[] {0, 1, 2, 3};

    public int coolDownDefault = 5;
    public int coolDown = coolDownDefault;

    public FarmingMachineTile(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        toolDrop = Blocks.FARMING_MACHINE;
        energySlot = 9;
        inventory = new RebornInventory<>(10, "FarmingMachineTile", 64, this);
        checkTier();
    }

    public FarmingMachineTile(BlockPos pos, BlockState state) {
        this(Tiles.FARMING_MACHINE_TILE, pos, state);
    }

    public FarmingMachineTile(TileCreateEvent event) {
        this(event.getBlockPos(), event.getBlockState());
    }

    public BuiltScreenHandler createScreenHandler(int syncID, PlayerEntity player) {
        return new ScreenHandlerBuilder(AdvancedReborn.MOD_ID + "__FARMING_MACHINE").player(player.getInventory()).inventory().hotbar().addInventory()
                .blockEntity(this)
                .slot(0, 55, 32).slot(1, 73, 32).slot(2, 55, 50).slot(3, 73, 50)
                .slot(4, 55, 72).slot(5, 73, 72).slot(6, 91, 72).slot(7, 109, 72).slot(8, 127, 72)
                .energySlot(9, 8, 72).syncEnergyValue()
                .addInventory().create(this, syncID);
    }

    public long getBaseMaxPower() {
        return AutoConfigAddon.getConfig().farmingMachineMaxEnergy;
    }

    public long getBaseMaxOutput() {
        return 0;
    }

    public long getBaseMaxInput() {
        return AutoConfigAddon.getConfig().farmingMachineMaxInput;
    }

    public boolean canProvideEnergy(Direction side) {
        return false;
    }

    public ItemStack getToolDrop(PlayerEntity p0) {
        return new ItemStack(toolDrop, 1);
    }

    public void tick(World world, BlockPos pos, BlockState state, MachineBaseBlockEntity blockEntity2) {
        super.tick(world, pos, state, blockEntity2);
        if (world == null || world.isClient) {
            return;
        }
        charge(energySlot);
        BlockMachineBase block = (BlockMachineBase) state.getBlock();

        block.setActive(getEnergy() > 0, world, getPos());
        if (coolDown <= 0) coolDown = coolDownDefault;
        else {
            coolDown--;
            return;
        }

        if (isActive()) {
            long harvestUseEnergy = getEuPerTick(AutoConfigAddon.config.farmingMachineHarvestUseEnergy);
            if (getEnergy() > harvestUseEnergy) {
                List<ItemStack> drops = new ArrayList<>();
                if (tryHarvest(world, pos, AutoConfigAddon.config.farmingMachineRange, drops)) {
                    for (ItemStack drop : drops) {
                        insertStack(drop);
                    }
                    useEnergy(harvestUseEnergy);
                }
            }

            // ここから!isEmpty↓
            if (getInventory().isEmpty()) return;

            long plantUseEnergy = getEuPerTick(AutoConfigAddon.config.farmingMachinePlantUseEnergy);

            if (getEnergy() > plantUseEnergy) {
                ItemStack stack =  getPlantStack();
                if (tryPlant(world, pos, AutoConfigAddon.config.farmingMachineRange, stack)) {
                    stack.decrement(1);
                    useEnergy(plantUseEnergy);
                }
            }
        }
    }

    public ItemStack getPlantStack() {
        for (int i : plantItemSlotIndex) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty()) {
                if (!(stack.getItem() instanceof BlockItem && ((BlockItem) stack.getItem()).getBlock() instanceof CropBlock)) continue;
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    public void insertStack(ItemStack stack) {
        int[] indexes = harvestItemSlotIndex;
        if (stack.getItem() instanceof BlockItem && ((BlockItem) stack.getItem()).getBlock() instanceof CropBlock) {
            indexes = ArrayUtils.addAll(plantItemSlotIndex, harvestItemSlotIndex);
        }
        for (int i : indexes) {
            ItemStack slotStack = inventory.getStack(i);
            if (slotStack.isEmpty()) {
                inventory.setStack(i, stack);
                markDirty();
                return;
            }
            if (slotStack.getItem() == stack.getItem() && slotStack.getCount() + stack.getCount() < 64) {
                inventory.setStack(i, new ItemStack(stack.getItem(), stack.getCount() + inventory.getStack(i).getCount()));
                markDirty();
                return;
            }
        }

        world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack));
    }


    public static void setFarmland(World world, BlockPos pos, int range) {
        if (world.isClient) return;

        BlockPos downPos = pos.down();

        for (int x = -range; x < range + 1; x++) {
            for (int z = -range; z < range + 1; z++) {
                BlockPos executePos = new BlockPos(downPos.getX() + x, downPos.getY(), downPos.getZ() + z);
                if (world.getBlockState(executePos).isIn(BlockTags.DIRT)) {
                    world.setBlockState(executePos, net.minecraft.block.Blocks.FARMLAND.getDefaultState());
                }
            }
        }
    }

    public static boolean tryHarvest(World world, BlockPos pos, int range, List<ItemStack> drops) {
        for (int x = -range; x < range + 1; x++) {
            for (int z = -range; z < range + 1; z++) {
                BlockPos executePos = new BlockPos(pos.getX() + x, pos.getY(), pos.getZ() + z);
                BlockState state = world.getBlockState(executePos);
                if (state.getBlock() instanceof CropBlock) {
                    CropBlock block = (CropBlock) state.getBlock();
                    if (block.isMature(world.getBlockState(executePos))) {
                        if (drops != null)
                            drops.addAll(CropBlock.getDroppedStacks(state, (ServerWorld) world, executePos, null));
                        world.breakBlock(executePos, false);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean tryPlant(World world, BlockPos pos, int range, ItemStack stack) {
        if (!(stack.getItem() instanceof BlockItem && ((BlockItem) stack.getItem()).getBlock() instanceof CropBlock)) return false;
        for (int x = -range; x < range + 1; x++) {
            for (int z = -range; z < range + 1; z++) {
                BlockPos executePos = new BlockPos(pos.getX() + x, pos.getY(), pos.getZ() + z);
                if (!world.getBlockState(executePos).isAir()) continue;

                if (world.getBlockState(executePos.down()).getBlock() instanceof FarmlandBlock) {
                    world.setBlockState(executePos, ((BlockItem) stack.getItem()).getBlock().getDefaultState(), 11);
                    return true;
                }
            }
        }
        return false;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void onPlace(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.onPlace(worldIn, pos, state, placer, stack);
        setFarmland(worldIn, pos, AutoConfigAddon.config.farmingMachineRange);
    }
}
