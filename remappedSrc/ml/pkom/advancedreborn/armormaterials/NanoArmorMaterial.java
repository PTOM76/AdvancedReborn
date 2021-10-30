package ml.pkom.advancedreborn.armormaterials;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;

public class NanoArmorMaterial implements ArmorMaterial {

    public static ArmorMaterial NANO = new NanoArmorMaterial();

    @Override
    public int getDurability(EquipmentSlot slot) {
        return ArmorMaterials.DIAMOND.getDurability(slot);
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return ArmorMaterials.DIAMOND.getProtectionAmount(slot);
    }

    @Override
    public int getEnchantability() {
        return ArmorMaterials.DIAMOND.getEnchantability();
    }

    @Override
    public SoundEvent getEquipSound() {
        return ArmorMaterials.DIAMOND.getEquipSound();
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }

    @Override
    public String getName() {
        return "nano";
    }

    @Override
    public float getToughness() {
        return ArmorMaterials.DIAMOND.getToughness();
    }

    @Override
    public float getKnockbackResistance() {
        return ArmorMaterials.DIAMOND.getKnockbackResistance();
    }
}
