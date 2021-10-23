package ml.pkom.advancedreborn;

import ml.pkom.advancedreborn.screen.GuiCanningMachine;
import ml.pkom.advancedreborn.tile.CanningMachineTile;
import net.minecraft.block.entity.BlockEntity;
import techreborn.client.GuiType;

import java.util.function.Supplier;

public class GuiTypes {
    public static GuiType<CanningMachineTile> CANNING_MACHINE = register(AdvancedReborn.MOD_ID + "__canning_machine", () -> () -> GuiCanningMachine::new);

    public static <T extends BlockEntity> GuiType<T> register(String id, Supplier<Supplier<GuiType.GuiFactory<T>>> factorySupplierMeme) {
        return GuiType.register(AdvancedReborn.createID(id), factorySupplierMeme);
    }

    public static void init() {

    }
}
