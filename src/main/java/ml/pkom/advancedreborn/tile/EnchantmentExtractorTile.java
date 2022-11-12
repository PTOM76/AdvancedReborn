package ml.pkom.advancedreborn.tile;

import ml.pkom.advancedreborn.AdvancedReborn;
import ml.pkom.advancedreborn.Blocks;
import ml.pkom.advancedreborn.Tiles;
import ml.pkom.advancedreborn.addons.autoconfig.AutoConfigAddon;
import ml.pkom.advancedreborn.event.TileCreateEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.Nullable;
import reborncore.api.IToolDrop;
import reborncore.api.blockentity.InventoryProvider;
import reborncore.common.blockentity.MachineBaseBlockEntity;
import reborncore.common.blocks.BlockMachineBase;
import reborncore.common.powerSystem.PowerAcceptorBlockEntity;
import reborncore.common.screen.BuiltScreenHandler;
import reborncore.common.screen.BuiltScreenHandlerProvider;
import reborncore.common.screen.builder.ScreenHandlerBuilder;
import reborncore.common.util.RebornInventory;

import java.util.HashMap;
import java.util.Map;

public class EnchantmentExtractorTile extends PowerAcceptorBlockEntity implements IToolDrop, InventoryProvider, BuiltScreenHandlerProvider {

    public Block toolDrop;
    public int energySlot;
    public RebornInventory<?> inventory;
    public int coolDownDefault = 100;
    public int coolDown = coolDownDefault;

    public EnchantmentExtractorTile(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        toolDrop = Blocks.ENCHANTMENT_EXTRACTOR;
        energySlot = 10;
        inventory = new RebornInventory<>(12, "EnchantmentExtractorTile", 64, this);
        checkTier();
    }

    public EnchantmentExtractorTile(BlockPos pos, BlockState state) {
        this(Tiles.ENCHANTMENT_EXTRACTOR_TILE, pos, state);
    }

    public EnchantmentExtractorTile(TileCreateEvent event) {
        this(event.getBlockPos(), event.getBlockState());
    }

    public BuiltScreenHandler createScreenHandler(int syncID, PlayerEntity player) {
        return new ScreenHandlerBuilder(AdvancedReborn.MOD_ID + "__enchantment_extractor").player(player.getInventory()).inventory().hotbar().addInventory()
                .blockEntity(this)
                .slot(11, 60, 25) // Book Input

                .slot(0, 40, 25) // Input
                .slot(1, 40, 65) // Output
                // Enchantment book output
                .slot(2, 82, 40).slot(3, 100, 40).slot(4, 118, 40).slot(5, 136, 40)
                .slot(6, 82, 58).slot(7, 100, 58).slot(8, 118, 58).slot(9, 136, 58)
                .energySlot(10, 8, 72).syncEnergyValue()
                .sync(this::getCoolDown, this::setCoolDown).sync(this::getCoolDownDefault, this::setCoolDownDefault).addInventory().create(this, syncID);
    }

    public void setCoolDown(int coolDown) {
        this.coolDown = coolDown;
    }

    public int getCoolDown() {
        return coolDown;
    }

    public void setCoolDownDefault(int coolDownDefault) {
        this.coolDownDefault = coolDownDefault;
    }

    public int getCoolDownDefault() {
        return coolDownDefault;
    }

    public long getBaseMaxPower() {
        return AutoConfigAddon.getConfig().enchantmentExtractorMaxEnergy;
    }

    public long getBaseMaxOutput() {
        return 0;
    }

    public long getBaseMaxInput() {
        return AutoConfigAddon.getConfig().enchantmentExtractorMaxInput;
    }

    public long getBaseUsePower() {
        return AutoConfigAddon.getConfig().enchantmentExtractorUseEnergy;
    }

    public boolean canProvideEnergy(Direction side) {
        return false;
    }

    public int getProgressScaled(int scale) {
        return (getCoolDownDefault() - getCoolDown()) * scale / getCoolDownDefault();
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
        block.setActive(getCoolDown() != getCoolDownDefault(), world, getPos());
        if (!getInventory().getStack(1).isEmpty() || getInventory().getStack(0).isEmpty() || getInventory().getStack(11).isEmpty()) {
            if (getCoolDown() <= 0) setCoolDown(getCoolDownDefault());
            return;
        }
        if (getEnergy() > getEuPerTick(getBaseUsePower())) {
            if (!getInventory().getStack(0).isEmpty()) {
                useEnergy(getEuPerTick(getBaseUsePower()));
                if (getCoolDown() <= 0) {
                    setCoolDown(getCoolDownDefault());

                    ItemStack bookStack = inventory.getStack(11);
                    ItemStack inputStack = inventory.getStack(0);
                    if (bookStack.getItem() == Items.BOOK && inputStack.hasEnchantments()) {
                        Map<Enchantment, Integer> enchantments = EnchantmentHelper.get(inputStack);
                        if (bookStack.getCount() >= enchantments.size()) {
                            for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                                ItemStack itemStack = new ItemStack(Items.ENCHANTED_BOOK, 1);
                                Map<Enchantment, Integer> map = new HashMap<>();
                                map.put(entry.getKey(), entry.getValue());
                                EnchantmentHelper.set(map, itemStack);
                                //itemStack.addEnchantment(entry.getKey(), entry.getValue());
                                bookStack.decrement(1);
                                insertStack(itemStack);
                            }
                            ItemStack newStack = inputStack.copy();
                            if (newStack.hasNbt()) {
                                if (newStack.getNbt().contains(ItemStack.ENCHANTMENTS_KEY)) {
                                    newStack.getNbt().remove(ItemStack.ENCHANTMENTS_KEY);
                                }
                            }
                            inventory.setStack(1, newStack);

                            inventory.setStack(0, ItemStack.EMPTY);
                        }
                    }

                    return;
                }
                setCoolDown(getCoolDown() - 1);
            } else {
                if (getCoolDown() != getCoolDownDefault()) {
                    setCoolDown(getCoolDownDefault());
                }
            }
        } else {
            block.setActive(false, world, getPos());
        }
    }

    public void insertStack(ItemStack stack) {
        int[] indexes = {2, 3, 4, 5, 6, 7, 8, 9};

        for (int i : indexes) {
            ItemStack slotStack = inventory.getStack(i);
            if (slotStack.isEmpty()) {
                inventory.setStack(i, stack);
                markDirty();
                return;
            }
        }

        world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack));
    }

    public Inventory getInventory() {
        return inventory;
    }
}
