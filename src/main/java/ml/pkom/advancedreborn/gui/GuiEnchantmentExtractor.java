package ml.pkom.advancedreborn.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import ml.pkom.advancedreborn.AdvancedReborn;
import ml.pkom.advancedreborn.tile.EnchantmentExtractorTile;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import reborncore.client.gui.builder.GuiBase;
import reborncore.client.gui.guibuilder.GuiBuilder;
import reborncore.common.screen.BuiltScreenHandler;

public class GuiEnchantmentExtractor extends GuiBase<BuiltScreenHandler> {

    public static final Identifier GUI = AdvancedReborn.id("textures/gui/slot_texture.png");

    public EnchantmentExtractorTile tile;
    public GuiEnchantmentExtractor(int syncId, PlayerEntity player, EnchantmentExtractorTile tile) {
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

        RenderSystem.setShaderTexture(0, GuiBuilder.defaultTextureSheet);
        drawSlot(matrixStack, 40, 25, layer); // Input slot
        drawSlot(matrixStack, 40, 65, layer); // Output slot

        drawSlot(matrixStack, 82, 40, layer);
        drawSlot(matrixStack, 100, 40, layer);
        drawSlot(matrixStack, 118, 40, layer);
        drawSlot(matrixStack, 136, 40, layer);
        drawSlot(matrixStack, 82, 58, layer);
        drawSlot(matrixStack, 100, 58, layer);
        drawSlot(matrixStack, 118, 58, layer);
        drawSlot(matrixStack, 136, 58, layer);

        drawSlot(matrixStack, 8, 72, layer);

        RenderSystem.setShaderTexture(0, GUI);
        // Book slot
        drawTexture(matrixStack, 60 + this.x - 1, 25 + this.y - 1, 0, 0, 18, 18);
    }

    public void drawForeground(MatrixStack matrixStack, int mouseX, int mouseY) {
        super.drawForeground(matrixStack, mouseX, mouseY);
        Layer layer = Layer.FOREGROUND;
        builder.drawProgressBar(matrixStack, this, tile.getProgressScaled(100), 100, 43, 45, mouseX, mouseY, GuiBuilder.ProgressDirection.DOWN, layer);
        builder.drawMultiEnergyBar(matrixStack, this, 9, 19, (int) tile.getEnergy(), (int) tile.getMaxStoredPower(), mouseX, mouseY, 0, layer);
    }
}
