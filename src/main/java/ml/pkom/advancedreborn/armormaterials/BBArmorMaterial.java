package ml.pkom.advancedreborn.armormaterials;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;

public class BBArmorMaterial implements ArmorMaterial {

    public String name = "";

    public BBArmorMaterial(String name) {
        this.name = name;
    }

    @Override
    public int getDurability(EquipmentSlot slot) {
        return ArmorMaterials.IRON.getDurability(EquipmentSlot.CHEST);
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return ArmorMaterials.IRON.getProtectionAmount(EquipmentSlot.CHEST);
    }

    @Override
    public int getEnchantability() {
        return ArmorMaterials.IRON.getEnchantability();
    }

    @Override
    public SoundEvent getEquipSound() {
        return ArmorMaterials.IRON.getEquipSound();
    }

    @Override
    public Ingredient getRepairIngredient() {
        return ArmorMaterials.IRON.getRepairIngredient();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getToughness() {
        return ArmorMaterials.IRON.getToughness();
    }

    @Override
    public float getKnockbackResistance() {
        return ArmorMaterials.IRON.getKnockbackResistance();
    }
}
