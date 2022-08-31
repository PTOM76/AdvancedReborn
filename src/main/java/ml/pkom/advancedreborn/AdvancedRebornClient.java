package ml.pkom.advancedreborn;

import ml.pkom.advancedreborn.packet.EntitySpawnPacket;
import ml.pkom.advancedreborn.renderer.IndustrialTNTEntityRenderer;
import ml.pkom.advancedreborn.screen.CardboardBoxScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.particle.EmotionParticle;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;

import java.util.UUID;

public class AdvancedRebornClient implements ClientModInitializer {

    public static MinecraftClient client = MinecraftClient.getInstance();

    public void onInitializeClient() {
        //ClientGuiTypes.init(); → TechRebornClientMixin
        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register(((atlasTexture, registry) -> {
            registry.register(AdvancedReborn.createID("particle/energy"));
        }));

        ParticleFactoryRegistry.getInstance().register(Particles.ENERGY, EmotionParticle.HeartFactory::new);

        EntityRendererRegistry.register(Entities.DYNAMITE, (context) -> new FlyingItemEntityRenderer(context));
        EntityRendererRegistry.register(Entities.I_TNT, (context) -> new IndustrialTNTEntityRenderer(context));

        HandledScreens.register(ScreenHandlers.CARDBOARD_BOX_SCREEN_HANDLER, CardboardBoxScreen::new);

        ClientPlayNetworking.registerGlobalReceiver(Defines.SPAWN_PACKET_ID, (client, handler, byteBuf, sender) -> {
            EntityType<?> et = Registry.ENTITY_TYPE.get(byteBuf.readVarInt());
            UUID uuid = byteBuf.readUuid();
            int entityId = byteBuf.readVarInt();
            Vec3d pos = EntitySpawnPacket.PacketBufUtil.readVec3d(byteBuf);
            float pitch = EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
            float yaw = EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
            if (client.world == null) return;
            Entity entity = et.create(client.world);
            if (entity == null) return;
            entity.updateTrackedPosition(pos.x, pos.y, pos.z);
            entity.setPos(pos.x, pos.y, pos.z);
            entity.setYaw(pitch);
            entity.setYaw(yaw);
            entity.setId(entityId);
            entity.setUuid(uuid);
            client.world.addEntity(entityId, entity);
        });
    }
}