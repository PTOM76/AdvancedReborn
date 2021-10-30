package ml.pkom.advancedreborn.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import reborncore.common.blockentity.FluidConfiguration;
import reborncore.common.blockentity.MachineBaseBlockEntity;
import reborncore.common.blockentity.RedstoneConfiguration;
import reborncore.common.blockentity.SlotConfiguration;

@Mixin(MachineBaseBlockEntity.class)
public interface MachineBaseBlockEntityAccessor {
    @Accessor(value = "slotConfiguration", remap = false)
    public void setSlotConfiguration(SlotConfiguration slotConfiguration);

    @Accessor(value = "slotConfiguration", remap = false)
    public SlotConfiguration getSlotConfiguration();

    @Accessor(value = "fluidConfiguration", remap = false)
    public void setFluidConfiguration(FluidConfiguration fluidConfiguration);

    @Accessor(value = "fluidConfiguration", remap = false)
    public FluidConfiguration getFluidConfiguration();

    @Accessor(value = "redstoneConfiguration", remap = false)
    public void setRedstoneConfiguration(RedstoneConfiguration redstoneConfiguration);

    @Accessor(value = "redstoneConfiguration", remap = false)
    public RedstoneConfiguration getRedstoneConfiguration();

}