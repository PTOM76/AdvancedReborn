package ml.pkom.advancedreborn;

import ml.pkom.advancedreborn.entities.DynamiteEntity;
import ml.pkom.advancedreborn.entities.IndustrialTNTEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.registry.Registry;

public class Entities {
    public static EntityType<DynamiteEntity> DYNAMITE = Registry.register(Registry.ENTITY_TYPE, AdvancedReborn.createID("dynamite"), (FabricEntityTypeBuilder.<DynamiteEntity>create(SpawnGroup.MISC, DynamiteEntity::new)).dimensions(EntityDimensions.fixed(0.25f,0.25f)).trackRangeBlocks(10).trackedUpdateRate(10).build());
    public static EntityType<IndustrialTNTEntity> I_TNT = Registry.register(Registry.ENTITY_TYPE, AdvancedReborn.createID("industrial_tnt"), (FabricEntityTypeBuilder.<IndustrialTNTEntity>create(SpawnGroup.MISC, IndustrialTNTEntity::new)).dimensions(EntityDimensions.fixed(0.98f,0.98f)).trackRangeBlocks(10).trackedUpdateRate(10).build());

    public static void init() {

    }
}
