package chronosacaria.mcdar.init;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.entities.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.List;
import java.util.Map;

public class SummonedEntityRegistry {

    public static final Map<EntityType<? extends LivingEntity>, DefaultAttributeContainer> ATTRIBUTES =
            Maps.newHashMap();
    private static final List<EntityType<?>> SUMMONED_ENTITIES = Lists.newArrayList();

    public static final EntityType<BuzzyNestBeeEntity> BUZZY_NEST_BEE_ENTITY =
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, BuzzyNestBeeEntity::new)
                    .dimensions(EntityDimensions.fixed(1, 2))
                    .build();
    public static final EntityType<EnchantedGrassGreenSheepEntity> ENCHANTED_GRASS_GREEN_SHEEP_ENTITY =
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, EnchantedGrassGreenSheepEntity::new)
                    .dimensions(EntityDimensions.fixed(1, 2))
                    .build();
    public static final EntityType<EnchantedGrassBlueSheepEntity> ENCHANTED_GRASS_BLUE_SHEEP_ENTITY =
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, EnchantedGrassBlueSheepEntity::new)
                    .dimensions(EntityDimensions.fixed(1, 2))
                    .build();
    public static final EntityType<EnchantedGrassRedSheepEntity> ENCHANTED_GRASS_RED_SHEEP_ENTITY =
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, EnchantedGrassRedSheepEntity::new)
                    .dimensions(EntityDimensions.fixed(1, 2))
                    .build();
    public static final EntityType<GolemKitGolemEntity> GOLEM_KIT_GOLEM_ENTITY =
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, GolemKitGolemEntity::new)
                    .dimensions(EntityDimensions.fixed(1, 2))
                    .build();
    public static final EntityType<TastyBoneWolfEntity> TASTY_BONE_WOLF_ENTITY =
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, TastyBoneWolfEntity::new)
                    .dimensions(EntityDimensions.fixed(1, 2))
                    .build();
    public static final EntityType<WonderfulWheatLlamaEntity> WONDERFUL_WHEAT_LLAMA_ENTITY =
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, WonderfulWheatLlamaEntity::new)
                    .dimensions(EntityDimensions.fixed(1, 2))
                    .build();

    public static void register(){
        registerEntity("buzzy_nest_bee", BeeEntity.createBeeAttributes(), BUZZY_NEST_BEE_ENTITY);
        registerEntity("enchanted_blue_sheep", EnchantedGrassBlueSheepEntity.createEnchantedBlueSheepEntityAttributes(), ENCHANTED_GRASS_BLUE_SHEEP_ENTITY);
        registerEntity("enchanted_green_sheep", EnchantedGrassGreenSheepEntity.createEnchantedGreenSheepEntityAttributes(), ENCHANTED_GRASS_GREEN_SHEEP_ENTITY);
        registerEntity("enchanted_red_sheep", EnchantedGrassRedSheepEntity.createEnchantedRedSheepAttributes(), ENCHANTED_GRASS_RED_SHEEP_ENTITY);
        registerEntity("golem_kit_golem", IronGolemEntity.createIronGolemAttributes(), GOLEM_KIT_GOLEM_ENTITY);
        registerEntity("tasty_bone_wolf", TastyBoneWolfEntity.createTastyBoneWolfAttributes(), TASTY_BONE_WOLF_ENTITY);
        registerEntity("wonderful_wheat_llama", WonderfulWheatLlamaEntity.createLlamaAttributes(), WONDERFUL_WHEAT_LLAMA_ENTITY);
    }

    public static void registerEntity(String name,
                                      DefaultAttributeContainer.Builder attributes,
                                      EntityType<? extends LivingEntity> entityType){
        FabricDefaultAttributeRegistry.register(entityType, attributes);
        Registry.register(Registries.ENTITY_TYPE, Mcdar.ID(name), entityType);
    }
}
