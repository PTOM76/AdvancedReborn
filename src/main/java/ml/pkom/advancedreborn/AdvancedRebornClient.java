package ml.pkom.advancedreborn;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.particle.EmotionParticle;
import net.minecraft.screen.PlayerScreenHandler;

public class AdvancedRebornClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register(((atlasTexture, registry) -> {
            registry.register(AdvancedReborn.createID("particle/energy"));
        }));

        ParticleFactoryRegistry.getInstance().register(Particles.ENERGY, EmotionParticle.HeartFactory::new);

    }
}