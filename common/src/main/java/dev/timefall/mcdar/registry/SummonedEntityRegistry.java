package dev.timefall.mcdar.registry;

import dev.timefall.mcdar.ModConstants;
import dev.timefall.mcdar.entity.*;
import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;
import me.fzzyhmstrs.fzzy_config.util.platform.Registrar;
import me.fzzyhmstrs.fzzy_config.util.platform.RegistrySupplier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;

import java.util.function.Supplier;

public class SummonedEntityRegistry {

    private static final Registrar<EntityType<?>> ENTITY_TYPE = ConfigApiJava.platform().createRegistrar(ModConstants.MOD_ID, Registries.ENTITY_TYPE);

    public static final RegistrySupplier<EntityType<BuzzyNestBeeEntity>> BUZZY_NEST_BEE_ENTITY =
            registerEntity("buzzy_nest_bee", () -> EntityType.Builder
                    .create(BuzzyNestBeeEntity::new, SpawnGroup.CREATURE)
                    .dimensions(1, 2)
                    .build("buzzy_nest_bee"));
    public static final RegistrySupplier<EntityType<EnchantedGrassGreenSheepEntity>> ENCHANTED_GRASS_GREEN_SHEEP_ENTITY =
            registerEntity("enchanted_green_sheep", () -> EntityType.Builder
                    .create(EnchantedGrassGreenSheepEntity::new, SpawnGroup.CREATURE)
                    .dimensions(1, 2)
                    .build("enchanted_green_sheep"));
    public static final RegistrySupplier<EntityType<EnchantedGrassBlueSheepEntity>> ENCHANTED_GRASS_BLUE_SHEEP_ENTITY =
            registerEntity("enchanted_blue_sheep", () -> EntityType.Builder
                    .create(EnchantedGrassBlueSheepEntity::new, SpawnGroup.CREATURE)
                    .dimensions(1, 2)
                    .build("enchanted_blue_sheep"));
    public static final RegistrySupplier<EntityType<EnchantedGrassRedSheepEntity>> ENCHANTED_GRASS_RED_SHEEP_ENTITY =
            registerEntity("enchanted_red_sheep", () -> EntityType.Builder
                    .create(EnchantedGrassRedSheepEntity::new, SpawnGroup.CREATURE)
                    .dimensions(1, 2)
                    .build("enchanted_red_sheep"));
    public static final RegistrySupplier<EntityType<GolemKitGolemEntity>> GOLEM_KIT_GOLEM_ENTITY =
            registerEntity("golem_kit_golem", () -> EntityType.Builder
                    .create(GolemKitGolemEntity::new, SpawnGroup.CREATURE)
                    .dimensions(1, 2)
                    .build("golem_kit_golem"));
    public static final RegistrySupplier<EntityType<TastyBoneWolfEntity>> TASTY_BONE_WOLF_ENTITY =
            registerEntity("tasty_bone_wolf", () -> EntityType.Builder
                    .create(TastyBoneWolfEntity::new, SpawnGroup.CREATURE)
                    .dimensions(1, 2)
                    .build("tasty_bone_wolf"));
    public static final RegistrySupplier<EntityType<WonderfulWheatLlamaEntity>> WONDERFUL_WHEAT_LLAMA_ENTITY =
            registerEntity("wonderful_wheat_llama", () -> EntityType.Builder
                    .create(WonderfulWheatLlamaEntity::new, SpawnGroup.CREATURE)
                    .dimensions(1, 2)
                    .build("wonderful_wheat_llama"));

    public static void register() {
        ENTITY_TYPE.init();
    }

    public static <T extends Entity> RegistrySupplier<EntityType<T>> registerEntity(String name, Supplier<EntityType<T>> entityTypeSupplier){
        return (RegistrySupplier<EntityType<T>>) (Object) ENTITY_TYPE.register(name, entityTypeSupplier);
    }
}
