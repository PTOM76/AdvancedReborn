package ml.pkom.advancedreborn.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import ml.pkom.advancedreborn.tile.InductionFurnaceTile;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import ml.pkom.mcpitanlibarch.api.util.TextUtil;
import reborncore.client.gui.builder.GuiBase;
import reborncore.client.gui.guibuilder.GuiBuilder;
import reborncore.common.screen.BuiltScreenHandler;

public class GuiInductionFurnace extends GuiBase<BuiltScreenHandler> {

    public InductionFurnaceTile tile;
    public GuiInductionFurnace(int syncId, PlayerEntity player, InductionFurnaceTile tile) {
        super(player, tile, tile.createScreenHandler(syncId, player));
        this.tile = tile;
        backgroundWidth = 176;
        backgroundHeight = 166;
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
        // 本当はリソースロケーションを指定しないといけないが、今回はdrawSlot内の処理で指定しているためとりあえず指定せずにおいておく
        drawSlot(matrixStack, 8, 72, layer);
        drawTwoLongSlot(matrixStack, 55 - 18, 45, layer);
        drawOutputTwoLongSlot(matrixStack, 101, 45, layer);
        drawText(matrixStack, TextUtil.translatable("advanced_reborn.advanced_machine.text.speed", tile.getHeatPer() + "%"), 75, 70, 0, layer);
    }

    public void drawTwoLongSlot(MatrixStack matrixStack, int x, int y, Layer layer) {
        if (layer == Layer.BACKGROUND) {
            x += this.x;
            y += this.y;
        }
        drawTwoLongSlotBuilder(matrixStack, this, x - 1, y - 1);
    }

    public void drawTwoLongSlotBuilder(MatrixStack matrixStack, Screen gui, int posX, int posY) {
        getMinecraft().getTextureManager().bindTexture(builder.getResourceLocation());
        drawTexture(matrixStack, posX, posY, 150, 0, 18 - 4, 18);
        drawTexture(matrixStack, posX + 14, posY, 150 + 4, 0, 18 - 8, 18);
        drawTexture(matrixStack, posX + 22, posY, 150 + 4, 0, 18 - 4, 18);
    }

    public void drawOutputTwoLongSlot(MatrixStack matrixStack, int x, int y, Layer layer) {
        if (layer == Layer.BACKGROUND) {
            x += this.x;
            y += this.y;
        }
        drawOutputTwoLongSlotBuilder(matrixStack, this, x - 5, y - 5);
    }

    public void drawOutputTwoLongSlotBuilder(MatrixStack matrixStack, Screen gui, int posX, int posY) {
        getMinecraft().getTextureManager().bindTexture(builder.getResourceLocation());
        drawTexture(matrixStack, posX, posY, 174, 0, 26 - 4, 26);
        drawTexture(matrixStack, posX + 22, posY, 174 + 4, 0, 26 - 4, 26);
    }

    public void drawForeground(MatrixStack matrixStack, int mouseX, int mouseY) {
        super.drawForeground(matrixStack, mouseX, mouseY);
        Layer layer = Layer.FOREGROUND;
        builder.drawProgressBar(matrixStack, this, tile.getProgressScaled(100), 100, 76, 48, mouseX, mouseY, GuiBuilder.ProgressDirection.RIGHT, layer);
        builder.drawMultiEnergyBar(matrixStack, this, 9, 19, (int) tile.getEnergy(), (int) tile.getMaxStoredPower(), mouseX, mouseY, 0, layer);
    }
}
