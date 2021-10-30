package ml.pkom.advancedreborn.screen;

import ml.pkom.advancedreborn.ScreenHandlers;
import ml.pkom.advancedreborn.tile.CardboardBoxTile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class CardboardBoxScreenHandler extends ScreenHandler {

    public Inventory inventory;
    public String tmpNote = "";
    public BlockPos pos = new BlockPos(0, 0, 0);

    public CardboardBoxScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        this(syncId, playerInventory, new SimpleInventory(9), "", null);
        NbtCompound data = buf.readNbt();
        if (data == null) return;
        if (data.contains("x") && data.contains("y") && data.contains("z")) {
            pos = new BlockPos(data.getDouble("x"), data.getDouble("y"), data.getDouble("z"));
        }
        if (data.contains("note")) {
            tmpNote = data.getString("note");
        }
    }

    public CardboardBoxScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, String note, @Nullable CardboardBoxTile tile) {
        super(ScreenHandlers.CARDBOARD_BOX_SCREEN_HANDLER, syncId);
        checkSize(inventory, 9);
        this.inventory = inventory;
        this.tmpNote = note;
        if (tile != null) {
            pos = tile.getPos();
        }
        inventory.onOpen(playerInventory.player);
        int m;
        int l;
        for (l = 0; l < 9; ++l) {
            addSlot(new Slot(inventory, l, 8 + l * 18, 20));
        }
        for (m = 0; m < 3; ++m) {
            for (l = 0; l < 9; ++l) {
                addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 51 + m * 18));
            }
        }
        for (m = 0; m < 9; ++m) {
            addSlot(new Slot(playerInventory, m, 8 + m * 18, 109));
        }

    }

    public boolean canUse(PlayerEntity player) {
        return true;
    }

    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }
}
