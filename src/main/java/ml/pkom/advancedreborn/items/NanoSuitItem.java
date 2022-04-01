package ml.pkom.advancedreborn.items;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import ml.pkom.advancedreborn.Items;
import ml.pkom.advancedreborn.api.Energy;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import reborncore.api.events.ApplyArmorToDamageCallback;
import reborncore.api.items.ArmorBlockEntityTicker;
import reborncore.api.items.ItemStackModifiers;
import reborncore.common.powerSystem.PowerSystem;
import reborncore.common.powerSystem.RcEnergyItem;
import reborncore.common.powerSystem.RcEnergyTier;
import reborncore.common.util.ItemUtils;
import techreborn.items.armor.TRArmourItem;
import techreborn.utils.InitUtils;

public class NanoSuitItem extends TRArmourItem implements ItemStackModifiers, ArmorBlockEntityTicker, RcEnergyItem {
    public NanoSuitItem(ArmorMaterial material, EquipmentSlot slot, Settings settings) {

        super(material, slot, settings);
        ApplyArmorToDamageCallback.EVENT.register(((player, source, amount) -> {
            for (ItemStack stack : player.getArmorItems()) {
                if (!(stack.getItem() instanceof NanoSuitItem)) {
                    continue;
                }
                long stackEnergy = Energy.of(stack).getStoredEnergy(stack);
                if (stackEnergy == 0) {
                    continue;
                }
                //System.out.println(amount);
                long damageToAbsorb = (long) Math.min(stackEnergy, amount * 2500);
                Energy.of(stack).tryUseEnergy(stack, damageToAbsorb);
            }
            return amount;
        }));
    }
    @Override
    public long getEnergyCapacity() {
        return 1_000_000;
    }

    @Override
    public RcEnergyTier getTier() {
        return RcEnergyTier.EXTREME;
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return false;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        return ItemUtils.getPowerForDurabilityBar(stack);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return ItemUtils.getColorForDurabilityBar(stack);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> itemList) {
        if (!isIn(group)) {
            return;
        }
        InitUtils.initPoweredItems(this, itemList);
    }

    @Override
    public void getAttributeModifiers(EquipmentSlot equipmentSlot, ItemStack stack, Multimap<EntityAttribute, EntityAttributeModifier> attributes) {
        if (equipmentSlot == this.slot && Energy.of(stack).getStoredEnergy(stack) > 0) {
            attributes.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(MODIFIERS[slot.getEntitySlotId()], "Armor modifier", 2, EntityAttributeModifier.Operation.ADDITION));
        } else if (equipmentSlot == this.slot) {
            attributes.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(MODIFIERS[slot.getEntitySlotId()], "Armor modifier", -1, EntityAttributeModifier.Operation.ADDITION));
        }
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return HashMultimap.create();
    }

    @Override
    public void tickArmor(ItemStack stack, PlayerEntity player) {
        if (stack.getItem().equals(Items.NANO_SUIT_HELMET)) {
            if (Energy.of(stack).getStoredEnergy(stack) > 0) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 300, 3));
            }
        }
    }
}
