package ml.pkom.advancedreborn;

import ml.pkom.advancedreborn.blocks.RaySolar;
import ml.pkom.advancedreborn.tile.CanningMachineTile;
import ml.pkom.advancedreborn.tile.CardboardBoxTile;
import ml.pkom.advancedreborn.tile.RaySolarTile;
import ml.pkom.advancedreborn.tile.RotaryGrinderTile;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

public class Tiles {
    public static BlockEntityType<RotaryGrinderTile> ROTARY_GRINDER_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, AdvancedReborn.createID("rotary_grinder"), create(RotaryGrinderTile::new, Blocks.ROTARY_GRINDER));
    public static BlockEntityType<RaySolarTile> RAY_SOLAR_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, AdvancedReborn.createID("ray_solar"), create(RaySolarTile::new, AdvancedReborn.solars.toArray(new RaySolar[AdvancedReborn.solars.size()])));
    public static BlockEntityType<CardboardBoxTile> CARDBOARD_BOX_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, AdvancedReborn.createID("cardboard_box"), create(CardboardBoxTile::new, Blocks.CARDBOARD_BOX));
    public static BlockEntityType<CanningMachineTile> CANNING_MACHINE_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, AdvancedReborn.createID("canning_machine"), create(CanningMachineTile::new, Blocks.CANNING_MACHINE));

    public static void init() {

    }

    public static <T extends BlockEntity> BlockEntityType create(Supplier supplier, Block... blocks) {
        return BlockEntityType.Builder.create(supplier, blocks).build(null);
    }
}
