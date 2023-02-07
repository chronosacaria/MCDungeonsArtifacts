package chronosacaria.mcdar.client;

import chronosacaria.mcdar.entities.renderers.*;
import chronosacaria.mcdar.init.SummonedEntityRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class SummonedEntityRenderRegistry {
    public static void init(){
        EntityRendererRegistry.register(SummonedEntityRegistry.BUZZY_NEST_BEE_ENTITY, BuzzyNestBeeRenderer::new);
        EntityRendererRegistry.register(SummonedEntityRegistry.ENCHANTED_GRASS_BLUE_SHEEP_ENTITY, EnchantedGrassBlueSheepRenderer::new);
        EntityRendererRegistry.register(SummonedEntityRegistry.ENCHANTED_GRASS_GREEN_SHEEP_ENTITY, EnchantedGrassGreenSheepRenderer::new);
        EntityRendererRegistry.register(SummonedEntityRegistry.ENCHANTED_GRASS_RED_SHEEP_ENTITY, EnchantedGrassRedSheepRenderer::new);
        EntityRendererRegistry.register(SummonedEntityRegistry.GOLEM_KIT_GOLEM_ENTITY, GolemKitGolemRenderer::new);
        EntityRendererRegistry.register(SummonedEntityRegistry.TASTY_BONE_WOLF_ENTITY, TastyBoneWolfRenderer::new);
        EntityRendererRegistry.register(SummonedEntityRegistry.WONDERFUL_WHEAT_LLAMA_ENTITY, WonderfulWheatLlamaRenderer::new);
    }
}
