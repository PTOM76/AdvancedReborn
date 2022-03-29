package ml.pkom.advancedreborn.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import ml.pkom.advancedreborn.Defines;
import ml.pkom.advancedreborn.tile.RenamingMachineTile;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.LiteralText;
import reborncore.client.gui.builder.GuiBase;
import reborncore.client.gui.guibuilder.GuiBuilder;
import reborncore.common.screen.BuiltScreenHandler;

public class GuiRenamingMachine extends GuiBase<BuiltScreenHandler> {

    public TextFieldWidget fieldBox;
    public RenamingMachineTile tile;
    public GuiRenamingMachine(int syncId, PlayerEntity player, RenamingMachineTile tile) {
        super(player, tile, tile.createScreenHandler(syncId, player));
        this.tile = tile;
    }

    public boolean isConfigEnabled() {
        return true;
    }

    public void init() {
        super.init();
        //fieldBox = new TextFieldWidget(textRenderer, x + 98,  y + 7, 70, 9, new LiteralText(""));
        fieldBox = new TextFieldWidget(textRenderer, x + 55,  y + 20, 98, 15, new LiteralText(""));
        getFieldBox().setText(tile.getName());
        getFieldBox().setFocusUnlocked(false);
        getFieldBox().setTextFieldFocused(true);
        getFieldBox().setMaxLength(2048);
        addSelectableChild(getFieldBox());
    }

    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            if (client != null) client.player.closeHandledScreen();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        tile.setNameClient(getFieldBox().getText());
        sendPacket();
        //System.out.println(keyCode);
        return super.keyReleased(keyCode, scanCode, modifiers);
    }

    public void sendPacket() {
        PacketByteBuf buf = PacketByteBufs.create();
        NbtCompound data = new NbtCompound();
        data.putString("name", getFieldBox().getText());
        data.putDouble("x", tile.getPos().getX());
        data.putDouble("y", tile.getPos().getY());
        data.putDouble("z", tile.getPos().getZ());
        buf.writeNbt(data);
        ClientPlayNetworking.send(Defines.RENAMING_PACKET_ID, buf);
    }

    public void removed() {
        super.removed();
        client.keyboard.setRepeatEvents(false);
    }

    public TextFieldWidget getFieldBox() {
        return fieldBox;
    }

    public void drawBackground(MatrixStack matrixStack, float lastFrameDuration, int mouseX, int mouseY) {
        super.drawBackground(matrixStack, lastFrameDuration, mouseX, mouseY);
        Layer layer = Layer.BACKGROUND;
        drawSlot(matrixStack, 55, 45, layer);
        drawOutputSlot(matrixStack, 101, 45, layer);
        drawSlot(matrixStack, 8, 72, layer);
        getFieldBox().render(matrixStack, mouseX, mouseY, lastFrameDuration);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public void drawForeground(MatrixStack matrixStack, int mouseX, int mouseY) {
        super.drawForeground(matrixStack, mouseX, mouseY);
        Layer layer = Layer.FOREGROUND;
        builder.drawProgressBar(matrixStack, this, tile.getProgressScaled(100), 100, 76, 48, mouseX, mouseY, GuiBuilder.ProgressDirection.RIGHT, layer);
        builder.drawMultiEnergyBar(matrixStack, this, 9, 19, (int) tile.getEnergy(), (int) tile.getMaxStoredPower(), mouseX, mouseY, 0, layer);
    }
}
