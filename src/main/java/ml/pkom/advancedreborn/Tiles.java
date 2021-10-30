package ml.pkom.advancedreborn;

import ml.pkom.advancedreborn.blocks.RaySolar;
import ml.pkom.advancedreborn.tile.*;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

public class Tiles {
    public static BlockEntityType<InductionFurnaceTile> INDUCTION_FURNACE_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, AdvancedReborn.createID("induction_furnace"), create(InductionFurnaceTile::new, Blocks.INDUCTION_FURNACE));
    public static BlockEntityType<RotaryGrinderTile> ROTARY_GRINDER_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, AdvancedReborn.createID("rotary_grinder"), create(RotaryGrinderTile::new, Blocks.ROTARY_GRINDER));
    public static BlockEntityType<CentrifugalExtractorTile> CENTRIFUGAL_EXTRACTOR_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, AdvancedReborn.createID("centrifugal_extractor"), create(CentrifugalExtractorTile::new, Blocks.CENTRIFUGAL_EXTRACTOR));
    public static BlockEntityType<SingularityCompressorTile> SINGULARITY_COMPRESSOR_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, AdvancedReborn.createID("singularity_compressor"), create(SingularityCompressorTile::new, Blocks.SINGULARITY_COMPRESSOR));
    public static BlockEntityType<RaySolarTile> RAY_SOLAR_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, AdvancedReborn.createID("ray_solar"), create(RaySolarTile::new, AdvancedReborn.solars.toArray(new RaySolar[AdvancedReborn.solars.size()])));
    public static BlockEntityType<CardboardBoxTile> CARDBOARD_BOX_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, AdvancedReborn.createID("cardboard_box"), create(CardboardBoxTile::new, Blocks.CARDBOARD_BOX));
    public static BlockEntityType<CanningMachineTile> CANNING_MACHINE_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, AdvancedReborn.createID("canning_machine"), create(CanningMachineTile::new, Blocks.CANNING_MACHINE));
    public static BlockEntityType<RenamingMachineTile> RENAMING_MACHINE_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, AdvancedReborn.createID("renaming_machine"), create(RenamingMachineTile::new, Blocks.RENAMING_MACHINE));
    public static BlockEntityType<TeleporterTile> TELEPORTER_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, AdvancedReborn.createID("teleporter"), create(TeleporterTile::new, Blocks.TELEPORTER));

    public static void init() {

    }

    public static <T extends BlockEntity> BlockEntityType create(Supplier supplier, Block... blocks) {
        return BlockEntityType.Builder.create(supplier, blocks).build(null);
    }
}
