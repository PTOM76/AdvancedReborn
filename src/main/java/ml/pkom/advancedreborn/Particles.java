package ml.pkom.advancedreborn;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.registry.Registry;

public class Particles {
    public static DefaultParticleType ENERGY = FabricParticleTypes.simple();


    public static void init() {
        Registry.register(Registry.PARTICLE_TYPE, AdvancedReborn.id("energy"), ENERGY);
    }
}
