package ml.pkom.advancedreborn;

import ml.pkom.advancedreborn.blocks.RaySolar;
import ml.pkom.advancedreborn.tile.*;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

public class Tiles {
    public static BlockEntityType<InductionFurnaceTile> INDUCTION_FURNACE_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, AdvancedReborn.id("induction_furnace"), create(InductionFurnaceTile::new, Blocks.INDUCTION_FURNACE));
    public static BlockEntityType<RotaryGrinderTile> ROTARY_GRINDER_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, AdvancedReborn.id("rotary_grinder"), create(RotaryGrinderTile::new, Blocks.ROTARY_GRINDER));
    public static BlockEntityType<CentrifugalExtractorTile> CENTRIFUGAL_EXTRACTOR_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, AdvancedReborn.id("centrifugal_extractor"), create(CentrifugalExtractorTile::new, Blocks.CENTRIFUGAL_EXTRACTOR));
    public static BlockEntityType<SingularityCompressorTile> SINGULARITY_COMPRESSOR_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, AdvancedReborn.id("singularity_compressor"), create(SingularityCompressorTile::new, Blocks.SINGULARITY_COMPRESSOR));
    public static BlockEntityType<RaySolarTile> RAY_SOLAR_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, AdvancedReborn.id("ray_solar"), create(RaySolarTile::new, AdvancedReborn.solars.toArray(new RaySolar[AdvancedReborn.solars.size()])));
    public static BlockEntityType<CardboardBoxTile> CARDBOARD_BOX_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, AdvancedReborn.id("cardboard_box"), create(CardboardBoxTile::new, Blocks.CARDBOARD_BOX));
    public static BlockEntityType<CanningMachineTile> CANNING_MACHINE_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, AdvancedReborn.id("canning_machine"), create(CanningMachineTile::new, Blocks.CANNING_MACHINE));
    public static BlockEntityType<RenamingMachineTile> RENAMING_MACHINE_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, AdvancedReborn.id("renaming_machine"), create(RenamingMachineTile::new, Blocks.RENAMING_MACHINE));
    public static BlockEntityType<TeleporterTile> TELEPORTER_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, AdvancedReborn.id("teleporter"), create(TeleporterTile::new, Blocks.TELEPORTER));
    public static BlockEntityType<FarmingMachineTile> FARMING_MACHINE_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, AdvancedReborn.id("farming_machine"), create(FarmingMachineTile::new, Blocks.FARMING_MACHINE));
    public static BlockEntityType<LoggingMachineTile> LOGGING_MACHINE_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, AdvancedReborn.id("logging_machine"), create(LoggingMachineTile::new, Blocks.LOGGING_MACHINE));

    public static void init() {

    }

    public static <T extends BlockEntity> BlockEntityType<T> create(FabricBlockEntityTypeBuilder.Factory<T> supplier, Block... blocks) {
        return FabricBlockEntityTypeBuilder.create(supplier, blocks).build(null);
    }
}
