package ml.pkom.advancedreborn.gui;

import ml.pkom.advancedreborn.tile.FarmingMachineTile;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import reborncore.client.gui.builder.GuiBase;
import reborncore.common.screen.BuiltScreenHandler;

public class GuiFarmingMachine extends GuiBase<BuiltScreenHandler> {

    public FarmingMachineTile tile;
    public GuiFarmingMachine(int syncId, PlayerEntity player, FarmingMachineTile tile) {
        super(player, tile, tile.createScreenHandler(syncId, player));
        this.tile = tile;
    }

    public boolean isConfigEnabled() {
        return true;
    }

    public void init() {
        super.init();
    }

    public void drawBackground(MatrixStack matrixStack, float lastFrameDuration, int mouseX, int mouseY) {
        super.drawBackground(matrixStack, lastFrameDuration, mouseX, mouseY);
        Layer layer = Layer.BACKGROUND;
        drawSlot(matrixStack, 55, 32, layer);
        drawSlot(matrixStack, 73, 32, layer);
        drawSlot(matrixStack, 55, 50, layer);
        drawSlot(matrixStack, 73, 50, layer);

        drawSlot(matrixStack, 55, 72, layer);
        drawSlot(matrixStack, 73, 72, layer);
        drawSlot(matrixStack, 91, 72, layer);
        drawSlot(matrixStack, 109, 72, layer);
        drawSlot(matrixStack, 127, 72, layer);

        drawSlot(matrixStack, 8, 72, layer);
    }

    public void drawForeground(MatrixStack matrixStack, int mouseX, int mouseY) {
        super.drawForeground(matrixStack, mouseX, mouseY);
        Layer layer = Layer.FOREGROUND;
        builder.drawMultiEnergyBar(matrixStack, this, 9, 19, (int) tile.getEnergy(), (int) tile.getMaxStoredPower(), mouseX, mouseY, 0, layer);
    }
}
