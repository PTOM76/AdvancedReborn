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
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import reborncore.api.IToolDrop;
import reborncore.api.blockentity.InventoryProvider;
import reborncore.client.screen.BuiltScreenHandlerProvider;
import reborncore.client.screen.builder.BuiltScreenHandler;
import reborncore.client.screen.builder.ScreenHandlerBuilder;
import reborncore.common.blocks.BlockMachineBase;
import reborncore.common.powerSystem.PowerAcceptorBlockEntity;
import reborncore.common.util.RebornInventory;
import team.reborn.energy.EnergySide;

public class RenamingMachineTile extends PowerAcceptorBlockEntity implements IToolDrop, InventoryProvider, BuiltScreenHandlerProvider {

    public Block toolDrop;
    public int energySlot;
    public RebornInventory<?> inventory;
    public int coolDownDefault = 150;
    public int coolDown = coolDownDefault;
    public String name = "";

    public RenamingMachineTile(BlockEntityType<?> type) {
        super(type);
        toolDrop = Blocks.RENAMING_MACHINE;
        energySlot = 2;
        inventory = new RebornInventory<>(3, "RenamingMachineTile", 64, this);
        checkTier();
    }

    public RenamingMachineTile() {
        this(Tiles.RENAMING_MACHINE_TILE);
    }

    public RenamingMachineTile(BlockPos pos, BlockState state) {
        this(Tiles.RENAMING_MACHINE_TILE, pos, state);
    }

    public RenamingMachineTile(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        this();
    }

    public RenamingMachineTile(TileCreateEvent event) {
        this(event.getBlockPos(), event.getBlockState());
    }

    public BuiltScreenHandler createScreenHandler(int syncID, PlayerEntity player) {
        return new ScreenHandlerBuilder(AdvancedReborn.MOD_ID + "__renaming_machine").player(player.inventory).inventory().hotbar().addInventory()
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

    public double getBaseMaxPower() {
        return AutoConfigAddon.getConfig().renamingMachineMaxEnergy;
    }

    public double getBaseMaxOutput() {
        return 0;
    }

    public double getBaseMaxInput() {
        return AutoConfigAddon.getConfig().renamingMachineMaxInput;
    }

    public double getBaseUsePower() {
        return AutoConfigAddon.getConfig().renamingMachineUseEnergy;
    }

    public boolean canProvideEnergy(EnergySide side) {
        return false;
    }

    public int getProgressScaled(int scale) {
        return (getCoolDownDefault() - getCoolDown()) * scale / getCoolDownDefault();
    }

    public ItemStack getToolDrop(PlayerEntity p0) {
        return new ItemStack(toolDrop, 1);
    }

    public void tick() {
        super.tick();
        if (world == null || world.isClient) {
            return;
        }
        charge(energySlot);

        BlockState state = getWorld().getBlockState(getPos());
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
                    else stack.setCustomName(new LiteralText(getName()));
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

    public NbtCompound writeNbt(NbtCompound tag) {
        if (getName() != null) tag.putString("option_name", getName());
        tag.putInt("option_time", coolDown);
        return super.writeNbt(tag);
    }

    public void readNbt(BlockState blockState, NbtCompound tag) {
        super.readNbt(blockState, tag);
        if (tag.contains("option_name")) setName(tag.getString("option_name"));
        if (tag.contains("option_time")) coolDown = tag.getInt("option_time");
    }
}
