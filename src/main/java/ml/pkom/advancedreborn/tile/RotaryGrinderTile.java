package ml.pkom.advancedreborn.tile;

import ml.pkom.advancedreborn.*;
import ml.pkom.advancedreborn.addons.autoconfig.AutoConfigAddon;
import ml.pkom.advancedreborn.event.TileCreateEvent;
import ml.pkom.advancedreborn.tile.base.HeatMachineTile;
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
import reborncore.common.recipes.RecipeCrafter;
import reborncore.client.screen.builder.BuiltScreenHandler;
import reborncore.client.screen.BuiltScreenHandlerProvider;
import reborncore.common.util.RebornInventory;
import techreborn.init.ModRecipes;

public class RotaryGrinderTile extends HeatMachineTile implements IToolDrop, InventoryProvider, IRecipeCrafterProvider, BuiltScreenHandlerProvider {

    public Block toolDrop;
    public int energySlot;
    public RebornInventory<?> inventory;
    public RecipeCrafter crafter;

    public RotaryGrinderTile(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        toolDrop = Blocks.ROTARY_GRINDER;
        energySlot = 3;
        inventory = new RebornInventory<>(4, "RotaryGrinderTile", 64, this);
        crafter = new RecipeCrafter(ModRecipes.GRINDER, this, 2, 1, inventory, new int[]{0}, new int[]{1});
        checkTier();
    }

    public RotaryGrinderTile(BlockPos pos, BlockState state) {
        this(Tiles.ROTARY_GRINDER_TILE, pos, state);
    }

    public RotaryGrinderTile(TileCreateEvent event) {
        this(event.getBlockPos(), event.getBlockState());
    }

    public BuiltScreenHandler createScreenHandler(int syncID, PlayerEntity player) {
        return new ScreenHandlerBuilder(AdvancedReborn.MOD_ID + "__rotary_grinder_machine").player(player.getInventory()).inventory().hotbar().addInventory()
                .blockEntity(this).slot(0, 55, 45).outputSlot(1, 101 + 18, 45).outputSlot(2, 101, 45).energySlot(3, 8, 72).syncEnergyValue()
                .syncCrafterValue().addInventory().create(this, syncID);
    }

    public long getBaseMaxPower() {
        return AutoConfigAddon.getConfig().advancedMachineMaxEnergy;
    }

    public long getBaseMaxOutput() {
        return 0;
    }

    public long getBaseMaxInput() {
        return AutoConfigAddon.getConfig().advancedMachineMaxInput;
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
        if (!getInventory().getStack(1).isEmpty()) {
            if (getStack(1).getItem().equals(getStack(2).getItem())) {
                if (getStack(2).getCount() == getStack(2).getMaxCount()) return;
                getStack(2).increment(1);
                getStack(1).decrement(1);
            } else if (getStack(2).isEmpty()) {
                setStack(2, new ItemStack(getStack(1).getItem(), 1));
                getStack(1).decrement(1);
            }
        }
    }

    public Inventory getInventory() {
        return inventory;
    }
}
