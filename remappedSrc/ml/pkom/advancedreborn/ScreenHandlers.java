package ml.pkom.advancedreborn;

import ml.pkom.advancedreborn.screen.CardboardBoxScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;

public class ScreenHandlers {
    public static ScreenHandlerType<CardboardBoxScreenHandler> CARDBOARD_BOX_SCREEN_HANDLER = ScreenHandlerRegistry.registerExtended(AdvancedReborn.createID("cardboard_box"), CardboardBoxScreenHandler::new);

    public static void init() {
    }
}
