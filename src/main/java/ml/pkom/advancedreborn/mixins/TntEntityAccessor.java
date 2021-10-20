package ml.pkom.advancedreborn.mixins;

import net.minecraft.entity.TntEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(TntEntity.class)
public interface TntEntityAccessor {
    @Accessor(value = "fuseTimer")
    public void setFuseTimer(int timer);

    @Accessor(value = "fuseTimer")
    public int getFuseTimer();


}