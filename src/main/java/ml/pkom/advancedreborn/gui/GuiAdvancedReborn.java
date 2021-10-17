package ml.pkom.advancedreborn.gui;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import reborncore.client.gui.builder.GuiBase;
import reborncore.client.screen.builder.BuiltScreenHandler;

public class GuiAdvancedReborn extends GuiBase<BuiltScreenHandler> {
    public GuiAdvancedReborn(PlayerEntity player, BlockEntity blockEntity, BuiltScreenHandler screenHandler) {
        super(player, blockEntity, screenHandler);
    }
}
