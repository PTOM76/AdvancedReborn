package ml.pkom.advancedreborn.mixins;

import ml.pkom.advancedreborn.Items;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import team.reborn.energy.Energy;
import team.reborn.energy.EnergyHandler;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "handleFallDamage", at = @At("HEAD"), cancellable = true)
    public void injectFallDamage(float fallDistance, float damageMultiplier, CallbackInfoReturnable<Boolean> info) {
        if (world.isClient()) return;
        if (!((Object)this instanceof PlayerEntity)) return;
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (player.getEquippedStack(EquipmentSlot.FEET).getItem() == Items.NANO_SUIT_BOOTS) {
            ItemStack stack = player.getEquippedStack(EquipmentSlot.FEET);
            if (!isSneaking()) {
                int vanillaPlayerDamage = this.computeFallDamage(fallDistance, damageMultiplier);
                int userDamage = vanillaPlayerDamage / 5;
                int bootDamage = (int) Math.round(vanillaPlayerDamage * 0.4375);
                if (bootDamage > 0) {
                    EnergyHandler energy = Energy.of(stack);
                    if (energy.getEnergy() <= 800 * vanillaPlayerDamage) return;
                    energy.use(800 * vanillaPlayerDamage);
                }
                if (userDamage > 0) {
                    this.damage(DamageSource.FALL, (float) userDamage);
                }
                info.cancel();
            }
        }
    }

    @Shadow
    protected abstract int computeFallDamage(float fallDistance, float damageMultiplier);
}
