package ml.pkom.advancedreborn.tile;

import ml.pkom.advancedreborn.*;
import ml.pkom.advancedreborn.addons.autoconfig.AutoConfigAddon;
import ml.pkom.advancedreborn.event.TileCreateEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import reborncore.api.IToolDrop;
import reborncore.api.blockentity.InventoryProvider;
import reborncore.api.recipe.IRecipeCrafterProvider;
import reborncore.client.screen.builder.ScreenHandlerBuilder;
import reborncore.common.blockentity.MachineBaseBlockEntity;
import reborncore.common.blocks.BlockMachineBase;
import reborncore.common.powerSystem.PowerAcceptorBlockEntity;
import reborncore.common.recipes.RecipeCrafter;
import reborncore.client.screen.builder.BuiltScreenHandler;
import reborncore.client.screen.BuiltScreenHandlerProvider;
import reborncore.common.util.RebornInventory;

public class CanningMachineTile extends PowerAcceptorBlockEntity implements IToolDrop, InventoryProvider, IRecipeCrafterProvider, BuiltScreenHandlerProvider {

    public Block toolDrop;
    public int energySlot;
    public RebornInventory<?> inventory;
    public RecipeCrafter crafter;



    public CanningMachineTile(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        toolDrop = Blocks.CANNING_MACHINE;
        energySlot = 3;
        inventory = new RebornInventory<>(4, "CanningMachineTile", 64, this);
        crafter = new RecipeCrafter(Recipes.CANNING_MACHINE, this, 3, 1, inventory, new int[]{0, 1}, new int[]{2});
        checkTier();
    }

    public CanningMachineTile(BlockPos pos, BlockState state) {
        this(Tiles.CANNING_MACHINE_TILE, pos, state);
    }

    public CanningMachineTile(TileCreateEvent event) {
        this(event.getBlockPos(), event.getBlockState());
    }

    public BuiltScreenHandler createScreenHandler(int syncID, PlayerEntity player) {
        return new ScreenHandlerBuilder(AdvancedReborn.MOD_ID + "__canning_machine").player(player.getInventory()).inventory().hotbar().addInventory()
                .blockEntity(this).slot(0, 55, 35).slot(1, 55, 55).outputSlot(2, 101, 45).energySlot(3, 8, 72).syncEnergyValue()
                .syncCrafterValue().addInventory().create(this, syncID);
    }

    public long getBaseMaxPower() {
        return AutoConfigAddon.getConfig().canningMachineMaxEnergy;
    }

    public long getBaseMaxOutput() {
        return 0;
    }

    public long getBaseMaxInput() {
        return AutoConfigAddon.getConfig().canningMachineMaxInput;
    }

    public boolean canProvideEnergy(Direction side) {
        return false;
    }

    public int getProgressScaled(int scale) {
        if (crafter != null && crafter.currentTickTime != 0 && crafter.currentNeededTicks != 0) {
            return crafter.currentTickTime * scale / crafter.currentNeededTicks;
        }
        return 0;
    }

    public RecipeCrafter getRecipeCrafter() {
        return crafter;
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
        //BlockState state = getWorld().getBlockState(getPos());
        BlockMachineBase block = (BlockMachineBase) state.getBlock();
        block.setActive(!inventory.getStack(0).isEmpty() && !inventory.getStack(1).isEmpty(), world, getPos());
    }

    public Inventory getInventory() {
        return inventory;
    }
}
