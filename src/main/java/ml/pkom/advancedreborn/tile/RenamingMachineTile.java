package ml.pkom.advancedreborn.tile;

import ml.pkom.advancedreborn.AdvancedReborn;
import ml.pkom.advancedreborn.Blocks;
import ml.pkom.advancedreborn.Tiles;
import ml.pkom.advancedreborn.addons.autoconfig.AutoConfigAddon;
import ml.pkom.advancedreborn.event.TileCreateEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import ml.pkom.mcpitanlibarch.api.util.TextUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import reborncore.api.IToolDrop;
import reborncore.api.blockentity.InventoryProvider;
import reborncore.common.screen.builder.ScreenHandlerBuilder;
import reborncore.common.blockentity.MachineBaseBlockEntity;
import reborncore.common.blocks.BlockMachineBase;
import reborncore.common.powerSystem.PowerAcceptorBlockEntity;
import reborncore.common.screen.BuiltScreenHandler;
import reborncore.common.screen.BuiltScreenHandlerProvider;
import reborncore.common.util.RebornInventory;

public class RenamingMachineTile extends PowerAcceptorBlockEntity implements IToolDrop, InventoryProvider, BuiltScreenHandlerProvider {

    public Block toolDrop;
    public int energySlot;
    public RebornInventory<?> inventory;
    public int coolDownDefault = 150;
    public int coolDown = coolDownDefault;
    public String name = "";

    public RenamingMachineTile(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        toolDrop = Blocks.RENAMING_MACHINE;
        energySlot = 2;
        inventory = new RebornInventory<>(3, "RenamingMachineTile", 64, this);
        checkTier();
    }

    public RenamingMachineTile(BlockPos pos, BlockState state) {
        this(Tiles.RENAMING_MACHINE_TILE, pos, state);
    }

    public RenamingMachineTile(TileCreateEvent event) {
        this(event.getBlockPos(), event.getBlockState());
    }

    public BuiltScreenHandler createScreenHandler(int syncID, PlayerEntity player) {
        return new ScreenHandlerBuilder(AdvancedReborn.MOD_ID + "__renaming_machine").player(player.getInventory()).inventory().hotbar().addInventory()
                .blockEntity(this).slot(0, 55, 45).outputSlot(1, 101, 45).energySlot(2, 8, 72).syncEnergyValue()
                .sync(this::getName, this::setName).sync(this::getCoolDown, this::setCoolDown).sync(this::getCoolDownDefault, this::setCoolDownDefault).addInventory().create(this, syncID);
    }



    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    public void setNameClient(@Nullable String name) {
        setName(name);
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
        return AutoConfigAddon.getConfig().renamingMachineMaxEnergy;
    }

    public long getBaseMaxOutput() {
        return 0;
    }

    public long getBaseMaxInput() {
        return AutoConfigAddon.getConfig().renamingMachineMaxInput;
    }

    public long getBaseUsePower() {
        return AutoConfigAddon.getConfig().renamingMachineUseEnergy;
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

        //BlockState state = getWorld().getBlockState(getPos());
        BlockMachineBase block = (BlockMachineBase) state.getBlock();
        block.setActive(getCoolDown() != getCoolDownDefault(), world, getPos());
        if (!getInventory().getStack(1).isEmpty()) {
            if (getCoolDown() <= 0) setCoolDown(getCoolDownDefault());
            return; // 出力スロットにアイテムがあれば停止
        }
        if (getEnergy() > getEuPerTick(getBaseUsePower())) {
            if (!getInventory().getStack(0).isEmpty()) {
                useEnergy(getEuPerTick(getBaseUsePower()));
                if (getCoolDown() <= 0) {
                    setCoolDown(getCoolDownDefault());
                    ItemStack stack = getStack(0).copy();
                    getInventory().setStack(0, ItemStack.EMPTY);
                    if (getName().isEmpty()) stack.removeCustomName();
                    else stack.setCustomName(TextUtil.literal(getName()));
                    getInventory().setStack(1, stack);
                    world.playSound(null, getPos(), SoundEvents.BLOCK_ANVIL_USE, SoundCategory.BLOCKS, 0.75F, 1.5F);
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

    public Inventory getInventory() {
        return inventory;
    }

    public void writeNbt(NbtCompound tag) {
        if (getName() != null) tag.putString("option_name", getName());
        tag.putInt("option_time", coolDown);
        super.writeNbt(tag);
    }

    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        if (tag.contains("option_name")) setName(tag.getString("option_name"));
        if (tag.contains("option_time")) coolDown = tag.getInt("option_time");
    }
}
