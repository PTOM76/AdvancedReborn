package ml.pkom.advancedreborn.screen;

import ml.pkom.advancedreborn.tile.CanningMachineTile;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import reborncore.client.gui.builder.GuiBase;
import reborncore.client.gui.guibuilder.GuiBuilder;
import reborncore.client.screen.builder.BuiltScreenHandler;

public class GuiCanningMachine extends GuiBase<BuiltScreenHandler> {

    public CanningMachineTile tile;
    public GuiCanningMachine(int syncId, PlayerEntity player, CanningMachineTile tile) {
        super(player, tile, tile.createScreenHandler(syncId, player));
        this.tile = tile;
    }

    public void drawBackground(MatrixStack matrixStack, float lastFrameDuration, int mouseX, int mouseY) {
        super.drawBackground(matrixStack, lastFrameDuration, mouseX, mouseY);
        Layer layer = Layer.BACKGROUND;
        drawSlot(matrixStack, 55, 35, layer);
        drawSlot(matrixStack, 55, 55, layer);
        drawOutputSlot(matrixStack, 101, 45, layer);
        drawSlot(matrixStack, 8, 72, layer);
    }

    public void drawForeground(MatrixStack matrixStack, int mouseX, int mouseY) {
        super.drawForeground(matrixStack, mouseX, mouseY);
        Layer layer = Layer.FOREGROUND;
        builder.drawProgressBar(matrixStack, this, tile.getProgressScaled(100), 100, 76, 48, mouseX, mouseY, GuiBuilder.ProgressDirection.RIGHT, layer);
        builder.drawMultiEnergyBar(matrixStack, this, 9, 19, (int) tile.getEnergy(), (int) tile.getMaxStoredPower(), mouseX, mouseY, 0, layer);
    }
}
