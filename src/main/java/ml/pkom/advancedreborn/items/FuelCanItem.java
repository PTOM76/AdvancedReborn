package ml.pkom.advancedreborn.items;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.Item;

public class FuelCanItem extends Item {
    public FuelCanItem(Settings settings) {
        super(settings);
        FuelRegistry.INSTANCE.add(this, 3200);
    }
}
