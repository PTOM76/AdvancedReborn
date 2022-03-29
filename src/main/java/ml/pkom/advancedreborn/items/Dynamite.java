package ml.pkom.advancedreborn.items;

import ml.pkom.advancedreborn.entities.DynamiteEntity;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;

public class Dynamite extends Item {

    public boolean isSticky = false;
    public boolean isIndustrial = false;

    public Dynamite(Settings settings) {
        super(settings);
        DispenserBlock.registerBehavior(this, new ProjectileDispenserBehavior() {
            public ProjectileEntity createProjectile(World world, Position pos, ItemStack stack) {
                DynamiteEntity dynamiteEntity = new DynamiteEntity(world, pos.getX(), pos.getY(), pos.getZ());
                dynamiteEntity.setItem(stack);
                dynamiteEntity.setSticky(isSticky);
                dynamiteEntity.setIndustrial(isIndustrial);
                return dynamiteEntity;
            }
        });
    }

    public Dynamite(Settings settings, boolean isSticky) {
        this(settings);
        this.isSticky = isSticky;
    }

    public Dynamite(Settings settings, boolean isSticky, boolean isIndustrial) {
        this(settings);
        this.isSticky = isSticky;
        this.isIndustrial = isIndustrial;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!user.getAbilities().creativeMode) stack.decrement(1);
        if (!world.isClient()) {
            DynamiteEntity dynamiteEntity = new DynamiteEntity(world, user);
            dynamiteEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
            dynamiteEntity.setItem(stack);
            dynamiteEntity.setSticky(isSticky);
            dynamiteEntity.setIndustrial(isIndustrial);
            world.spawnEntity(dynamiteEntity);
            world.playSound(null, dynamiteEntity.getX(), dynamiteEntity.getY(), dynamiteEntity.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
        return TypedActionResult.success(stack);
    }
}
