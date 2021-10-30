package ml.pkom.advancedreborn.items;

import ml.pkom.advancedreborn.AdvancedReborn;
import ml.pkom.advancedreborn.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public class AddItems extends Item {
    public AddItems(Settings settings) {
        super(settings);
    }

    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
        if (group == AdvancedReborn.AR_GROUP) {
            stacks.addAll(AdvancedReborn.addStacksIG);
        }
        super.appendStacks(group, stacks);
        stacks.removeIf((stack) -> stack.getItem().equals(Items.ADD_ITEMS));
    }
}
