package ml.pkom.advancedreborn.items;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import reborncore.common.powerSystem.RcEnergyItem;
import reborncore.common.powerSystem.RcEnergyTier;
import reborncore.common.util.ItemUtils;
import techreborn.items.BatteryItem;
import techreborn.utils.InitUtils;
import techreborn.utils.MessageIDs;

import java.util.List;

public class AdvancedBattery extends Item implements RcEnergyItem {
    private final int maxEnergy;
    private final RcEnergyTier tier;

    public AdvancedBattery(Settings settings, int maxEnergy, RcEnergyTier tier) {
        super(settings);
        this.maxEnergy = maxEnergy;
        this.tier = tier;
    }

    public TypedActionResult<ItemStack> use(final World world, final PlayerEntity player, final Hand hand) {
        final ItemStack stack = player.getStackInHand(hand);
        if (player.isSneaking()) {
            ItemUtils.switchActive(stack, 1, MessageIDs.poweredToolID, player);
            return new TypedActionResult<>(ActionResult.SUCCESS, stack);
        }
        return new TypedActionResult<>(ActionResult.PASS, stack);
    }

    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        ItemUtils.switchActive(stack, 1, MessageIDs.poweredToolID, entity);
        if (world.isClient) {
            return;
        }
        if (!ItemUtils.isActive(stack)){
            return;
        }
        if (entity instanceof PlayerEntity) {
            ItemUtils.distributePowerToInventory((PlayerEntity) entity, stack, tier.getMaxOutput(), (testStack) -> !(testStack.getItem() instanceof BatteryItem));
        }
    }

    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, @Nullable World worldIn, List<Text> tooltip, TooltipContext flagIn) {
        ItemUtils.buildActiveTooltip(stack, tooltip);
    }

    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
        if (!isIn(group)) {
            return;
        }
        InitUtils.initPoweredItems(this, stacks);
    }

    public long getEnergyCapacity() {
        return maxEnergy;
    }

    public RcEnergyTier getTier() {
        return tier;
    }
}
