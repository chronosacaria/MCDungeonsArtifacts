package chronosacaria.mcdar.init;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.entities.BlastFungusEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityTypeRegistry {
    public static EntityType<BlastFungusEntity> BLAST_FUNGUS_ENTITY_TYPE;

    public static void register(){
        BLAST_FUNGUS_ENTITY_TYPE = Registry.register(Registry.ENTITY_TYPE,
                new Identifier(Mcdar.MOD_ID, "blast_fungus"),
                FabricEntityTypeBuilder.<BlastFungusEntity>create(
                        SpawnGroup.MISC, BlastFungusEntity::new)
                        .dimensions(EntityDimensions.changing(0.98F, 0.98F))
                        .trackRangeBlocks(10)
                        .trackedUpdateRate(10)
                        .build());
    }
}
