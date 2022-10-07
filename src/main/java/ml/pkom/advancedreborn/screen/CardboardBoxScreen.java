package ml.pkom.advancedreborn.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import ml.pkom.advancedreborn.AdvancedReborn;
import ml.pkom.advancedreborn.Defines;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import ml.pkom.mcpitanlibarch.api.util.TextUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CardboardBoxScreen extends HandledScreen<CardboardBoxScreenHandler> {
    private static Identifier TEXTURE = AdvancedReborn.id("textures/gui/cardboard_box.png");
    private TextFieldWidget noteBox;

    public CardboardBoxScreen(CardboardBoxScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        backgroundHeight = 133;
        this.playerInventoryTitleY = this.backgroundHeight - 94;
    }

    public void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        if (client == null) return;
        client.getTextureManager().bindTexture(TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);
        getNoteBox().render(matrices, mouseX, mouseY, delta);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public void init() {
        super.init();
        noteBox = new TextFieldWidget(textRenderer, x + 98,  y + 7, 70, 9, TextUtil.literal(""));
        getNoteBox().setText(handler.tmpNote);
        getNoteBox().setDrawsBackground(false);
        getNoteBox().setFocusUnlocked(false);
        getNoteBox().setTextFieldFocused(true);
        getNoteBox().setMaxLength(2048);
        addSelectableChild(getNoteBox());
    }

    public void close() {
        super.close();
        PacketByteBuf buf = PacketByteBufs.create();
        NbtCompound data = new NbtCompound();
        data.putString("note", getNote());
        data.putDouble("x", handler.pos.getX());
        data.putDouble("y", handler.pos.getY());
        data.putDouble("z", handler.pos.getZ());
        //AdvancedReborn.LOGGER.info("nbt: " + data);
        buf.writeNbt(data);
        ClientPlayNetworking.send(Defines.CARDBOARD_BOX_CLOSE_PACKET_ID, buf);
    }

    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (getNoteBox().keyPressed(keyCode, scanCode, modifiers)) return true;
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    public void removed() {
        super.removed();
        client.keyboard.setRepeatEvents(false);
    }

    public TextFieldWidget getNoteBox() {
        return noteBox;
    }

    public void setNoteBox(TextFieldWidget noteBox) {
        this.noteBox = noteBox;
    }

    public String getNote() {
        return getNoteBox().getText();
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }
}
