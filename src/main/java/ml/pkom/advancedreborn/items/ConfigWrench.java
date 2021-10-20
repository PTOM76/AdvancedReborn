package ml.pkom.advancedreborn.items;

import ml.pkom.advancedreborn.AdvancedRebornClient;
import ml.pkom.advancedreborn.Items;
import ml.pkom.advancedreborn.mixins.MachineBaseBlockEntityAccessor;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import reborncore.common.blockentity.FluidConfiguration;
import reborncore.common.blockentity.MachineBaseBlockEntity;
import reborncore.common.blockentity.RedstoneConfiguration;
import reborncore.common.blockentity.SlotConfiguration;
import techreborn.items.tool.WrenchItem;

import java.util.List;

public class ConfigWrench extends Item {
    public ConfigWrench(Settings settings) {
        super(settings);
        AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
            ItemStack stack = player.getStackInHand(hand);
            if (stack.getItem().equals(Items.CONFIG_WRENCH)) {
                BlockEntity tile = world.getBlockEntity(pos);
                if (tile instanceof MachineBaseBlockEntity) {
                    if (!stack.hasTag()) return ActionResult.FAIL;
                    NbtCompound tag = stack.getTag();
                    if (!tag.contains("configs")) return ActionResult.FAIL;
                    NbtCompound config = tag.getCompound("configs");
                    MachineBaseBlockEntityAccessor accessor = (MachineBaseBlockEntityAccessor) tile;
                    if (config.contains("slot"))
                        accessor.getSlotConfiguration().read(config.getCompound("slot"));
                    if (config.contains("fluid"))
                        accessor.getFluidConfiguration().read(config.getCompound("fluid"));
                    if (config.contains("redstone"))
                        accessor.getRedstoneConfiguration().read(config.getCompound("redstone"));
                    player.sendMessage(new LiteralText("Loaded Configuration from The Config Wrench."), false);
                    return ActionResult.SUCCESS;
                }
            }
            return ActionResult.PASS;
        });
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockEntity tile = world.getBlockEntity(pos);
        if (tile == null) return ActionResult.PASS;
        if (!(tile instanceof MachineBaseBlockEntity)) return ActionResult.PASS;
        if (world.isClient()) return ActionResult.SUCCESS;
        MachineBaseBlockEntity machine = (MachineBaseBlockEntity) tile;
        MachineBaseBlockEntityAccessor machineAccessor = (MachineBaseBlockEntityAccessor) machine;
        SlotConfiguration slotConfig = null;
        FluidConfiguration fluidConfig = null;
        if (machine.hasSlotConfig()) slotConfig = machineAccessor.getSlotConfiguration();
        RedstoneConfiguration redstoneConfig = machineAccessor.getRedstoneConfiguration();
        if (machine.fluidConfiguration != null)
            fluidConfig = machineAccessor.getFluidConfiguration();

        ItemStack stack = context.getPlayer().getStackInHand(context.getHand());
        NbtCompound tag = stack.getTag();
        if (tag == null) {
            tag = new NbtCompound();
        }
        NbtCompound config = new NbtCompound();
        if (slotConfig != null)
            config.put("slot", slotConfig.write());
        if (fluidConfig != null)
            config.put("fluid", fluidConfig.write());
        if (redstoneConfig != null)
            config.put("redstone", redstoneConfig.write());
        tag.put("configs", config);
        stack.setTag(tag);
        context.getPlayer().sendMessage(new LiteralText("Saved Configuration to The Config Wrench."), false);
        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new LiteralText("Save TR Machine configurations to Wrench when Right Click with TR Machine."));
        tooltip.add(new LiteralText("Load TR Machine configurations from Wrench when Left Click with TR Machine."));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
