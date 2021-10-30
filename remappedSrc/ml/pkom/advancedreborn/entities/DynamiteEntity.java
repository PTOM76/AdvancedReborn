package ml.pkom.advancedreborn.entities;

import ml.pkom.advancedreborn.AdvancedReborn;
import ml.pkom.advancedreborn.Defines;
import ml.pkom.advancedreborn.Entities;
import ml.pkom.advancedreborn.entities.itnt.IndustrialExplosion;
import ml.pkom.advancedreborn.packet.EntitySpawnPacket;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class DynamiteEntity extends ThrownItemEntity {

    public static TrackedData<Integer> FUSE = DataTracker.registerData(DynamiteEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public boolean stopped = false;
    public boolean isSticky = false;
    public boolean isIndustrial = false;
    public static int fuseTimerInit = 60;
    public int fuseTimer = fuseTimerInit;

    public DynamiteEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
        setFuse(fuseTimerInit);
    }

    public DynamiteEntity(World world, LivingEntity owner) {
        super(Entities.DYNAMITE, owner, world);
        setFuse(fuseTimerInit);
    }

    public DynamiteEntity(World world, double x, double y, double z) {
        super(Entities.DYNAMITE, x, y, z, world);
        setFuse(fuseTimerInit);
    }

    // ThrownItemEntityはパケットを送らないとレンダリングされないらしい。
    public Packet<?> createSpawnPacket() {
        return EntitySpawnPacket.create(this, Defines.SPAWN_PACKET_ID);
    }

    public void setSticky(boolean sticky) {
        isSticky = sticky;
    }

    public void setIndustrial(boolean industrial) {
        isIndustrial = industrial;
    }

    public Item getDefaultItem() {
        return getItem().getItem();
    }

    public void initDataTracker() {
        super.initDataTracker();
        dataTracker.startTracking(FUSE, fuseTimerInit);
    }

    public void setFuse(int fuse) {
        dataTracker.set(FUSE, fuse);
        fuseTimer = fuse;
    }

    public void onTrackedDataSet(TrackedData<?> data) {
        super.onTrackedDataSet(data);
        if (FUSE.equals(data)) {
            fuseTimer = getFuse();
        }
    }

    public int getFuse() {
        return dataTracker.get(FUSE);
    }

    public int getFuseTimer() {
        return fuseTimer;
    }

    public void onBlockHit(BlockHitResult blockHitResult) {
        //super.onBlockHit(blockHitResult);
        Vec3d distance = blockHitResult.getPos().subtract(getX(), getY(), getZ());
        setVelocity(distance);
        Vec3d pos = distance.normalize().multiply(0.05000000074505806D);
        setPos(getX() - pos.x, getY() - pos.y, getZ() - pos.z);
        setOnGround(true);
        stopped = true;
    }

    public void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (isSticky) {
            Vec3d distance = hitResult.getPos().subtract(getX(), getY(), getZ());
            setVelocity(distance);
            Vec3d pos = distance.normalize().multiply(0.05000000074505806D);
            setPos(getX() - pos.x, getY() - pos.y, getZ() - pos.z);
            setOnGround(true);
            setNoGravity(true);
            stopped = true;
        }
    }

    public void tick() {
        super.tick();
        if (stopped) {
            fuseTimer--;
            if (fuseTimer <= 0) {
                remove();
                if (!world.isClient()) {
                    explode();
                }
            } else {
                updateWaterState();
            }
        }
        if (world.isClient()) {
            world.addParticle(ParticleTypes.SMOKE, getX(), getY() + 0.5D, getZ(), 0.0D, 0.0D, 0.0D);
        }
    }

    public void explode() {
        if (isIndustrial) {
            Explosion explosion = new IndustrialExplosion(world, this, null, null, getX(), getBodyY(0.0625D), getZ(),2.5F,false, Explosion.DestructionType.BREAK);
            explosion.collectBlocksAndDamageEntities();
            explosion.affectWorld(true);
            world.playSound(null, getBlockPos(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4.0F, (1.0F + (world.random.nextFloat() - world.random.nextFloat()) * 0.2F) * 0.7F);
            ((ServerWorld)world).spawnParticles(ParticleTypes.EXPLOSION, getX(), getY(), getZ(), 1, 0, 0, 0, 0);
            return;
        }
        world.createExplosion(this, getX(), getBodyY(0.0625D), getZ(), 4.0F, Explosion.DestructionType.BREAK);
    }
}
