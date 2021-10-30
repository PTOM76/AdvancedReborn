package ml.pkom.advancedreborn.mixins;

import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Explosion.class)
public interface ExplosionAccessor {
    @Accessor(value = "world")
    public World getWorld();

    @Accessor(value = "x")
    public double getX();

    @Accessor(value = "y")
    public double getY();

    @Accessor(value = "z")
    public double getZ();

    @Accessor(value = "power")
    public float getPower();

    @Accessor(value = "behavior")
    public ExplosionBehavior getBehavior();
}