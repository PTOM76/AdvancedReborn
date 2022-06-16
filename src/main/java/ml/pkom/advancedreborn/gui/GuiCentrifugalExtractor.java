package ml.pkom.advancedreborn.gui;

import ml.pkom.advancedreborn.tile.CentrifugalExtractorTile;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import ml.pkom.mcpitanlib.api.text.TextUtil;
import reborncore.client.gui.builder.GuiBase;
import reborncore.client.gui.guibuilder.GuiBuilder;
import reborncore.common.screen.BuiltScreenHandler;

public class GuiCentrifugalExtractor extends GuiBase<BuiltScreenHandler> {

    public CentrifugalExtractorTile tile;
    public GuiCentrifugalExtractor(int syncId, PlayerEntity player, CentrifugalExtractorTile tile) {
        super(player, tile, tile.createScreenHandler(syncId, player));
        this.tile = tile;
        backgroundWidth = 177;
        backgroundHeight = 167;
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
        drawSlot(matrixStack, 55, 45, layer);
        drawOutputThreeLongSlot(matrixStack, 101, 45, layer);
        drawSlot(matrixStack, 8, 72, layer);
        drawText(matrixStack, TextUtil.translatable("advanced_reborn.advanced_machine.text.speed", tile.getHeatPer() + "%"), 75, 70, 0, layer);
    }

    public void drawOutputThreeLongSlot(MatrixStack matrixStack, int x, int y, Layer layer) {
        if (layer == Layer.BACKGROUND) {
            x += this.x;
            y += this.y;
        }
        drawOutputThreeLongSlotBuilder(matrixStack, this, x - 5, y - 5);
    }

    public void drawOutputThreeLongSlotBuilder(MatrixStack matrixStack, Screen gui, int posX, int posY) {
        getMinecraft().getTextureManager().bindTexture(builder.getResourceLocation());
        drawTexture(matrixStack, posX, posY, 174, 0, 26 - 4, 26);
        drawTexture(matrixStack, posX + 22, posY, 174 + 4, 0, 26 - 8, 26);
        drawTexture(matrixStack, posX + 40, posY, 174 + 4, 0, 26 - 4, 26);
    }

    public void drawForeground(MatrixStack matrixStack, int mouseX, int mouseY) {
        super.drawForeground(matrixStack, mouseX, mouseY);
        Layer layer = Layer.FOREGROUND;
        builder.drawProgressBar(matrixStack, this, tile.getProgressScaled(100), 100, 76, 48, mouseX, mouseY, GuiBuilder.ProgressDirection.RIGHT, layer);
        builder.drawMultiEnergyBar(matrixStack, this, 9, 19, (int) tile.getEnergy(), (int) tile.getMaxStoredPower(), mouseX, mouseY, 0, layer);
    }
}
