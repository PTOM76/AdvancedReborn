package ml.pkom.advancedreborn.entities;

import ml.pkom.advancedreborn.entities.itnt.IndustrialExplosion;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.TntEntity;
import net.minecraft.network.Packet;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;

public class IndustrialTNTEntity extends TntEntity {
    public IndustrialTNTEntity(EntityType<? extends IndustrialTNTEntity> entityType, World world) {
        super(entityType, world);
    }

    public IndustrialTNTEntity(World world, double x, double y, double z, @Nullable LivingEntity entity) {
        super(world, x, y, z, entity);
    }

    public Packet<?> createSpawnPacket() {
        return super.createSpawnPacket(); //EntitySpawnPacket.create(this, Defines.SPAWN_PACKET_ID);
    }

    public void tick() {
        if (!hasNoGravity()) {
            setVelocity(getVelocity().add(0.0D, -0.04D, 0.0D));
        }

        move(MovementType.SELF, this.getVelocity());
        setVelocity(this.getVelocity().multiply(0.98D));
        if (onGround) {
            setVelocity(this.getVelocity().multiply(0.7D, -0.5D, 0.7D));
        }
        setFuse(getFuse() - 1);
        if (getFuse() <= 0) {
            kill();
            if (!world.isClient) {
                iExplode();
            }
        } else {
            updateWaterState();
            if (world.isClient) {
                world.addParticle(ParticleTypes.SMOKE, getX(), getY() + 0.5D, getZ(), 0.0D, 0.0D, 0.0D);
            }
        }

    }

    public void iExplode() {
        Explosion explosion = new IndustrialExplosion(world, this, null, null, getX(), getBodyY(0.0625D), getZ(),2.5F,false, Explosion.DestructionType.BREAK);
        explosion.collectBlocksAndDamageEntities();
        explosion.affectWorld(true);
        world.playSound(null, getBlockPos(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4.0F, (1.0F + (world.random.nextFloat() - world.random.nextFloat()) * 0.2F) * 0.7F);
        ((ServerWorld)world).spawnParticles(ParticleTypes.EXPLOSION, getX(), getY(), getZ(), 1, 0, 0, 0, 0);
    }
}
