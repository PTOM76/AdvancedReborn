package ml.pkom.advancedreborn.mixins;

import ml.pkom.advancedreborn.client.ClientGuiTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import techreborn.TechRebornClient;

@Mixin(TechRebornClient.class)
public class TechRebornClientMixin {
    @Inject(method = "onInitializeClient", at = @At("HEAD"), remap = false)
    public void onInitializeClient(CallbackInfo ci) {
        ClientGuiTypes.init();
    }
}
