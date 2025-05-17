package dev.timefall.mcdar.registry;

import dev.timefall.mcdar.entity.*;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

public class DefaultAttributeRegistryFabric {

    public static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(SummonedEntityRegistry.BUZZY_NEST_BEE_ENTITY.get(), BuzzyNestBeeEntity.createBeeAttributes().build());
        FabricDefaultAttributeRegistry.register(SummonedEntityRegistry.ENCHANTED_GRASS_GREEN_SHEEP_ENTITY.get(), EnchantedGrassGreenSheepEntity.createEnchantedGreenSheepAttributes().build());
        FabricDefaultAttributeRegistry.register(SummonedEntityRegistry.ENCHANTED_GRASS_BLUE_SHEEP_ENTITY.get(), EnchantedGrassBlueSheepEntity.createEnchantedBlueSheepAttributes().build());
        FabricDefaultAttributeRegistry.register(SummonedEntityRegistry.ENCHANTED_GRASS_RED_SHEEP_ENTITY.get(), EnchantedGrassRedSheepEntity.createEnchantedRedSheepAttributes().build());
        FabricDefaultAttributeRegistry.register(SummonedEntityRegistry.GOLEM_KIT_GOLEM_ENTITY.get(), GolemKitGolemEntity.createIronGolemAttributes().build());
        FabricDefaultAttributeRegistry.register(SummonedEntityRegistry.TASTY_BONE_WOLF_ENTITY.get(), TastyBoneWolfEntity.createTastyBoneWolfAttributes().build());
        FabricDefaultAttributeRegistry.register(SummonedEntityRegistry.WONDERFUL_WHEAT_LLAMA_ENTITY.get(), WonderfulWheatLlamaEntity.createWonderfulWheatLlamaAttributes().build());
    }
}
