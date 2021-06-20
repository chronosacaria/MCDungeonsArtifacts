package chronosacaria.mcdar.client;

import chronosacaria.mcdar.entities.renderers.*;
import chronosacaria.mcdar.init.SummonedEntityRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.entity.EntityType;

@Environment(EnvType.CLIENT)
public class SummonedEntityRenderRegistry {
    public static void init(){
        EntityRendererRegistry.INSTANCE.register(SummonedEntityRegistry.BUZZY_NEST_BEE_ENTITY, BuzzyNestBeeRenderer::new);
        EntityRendererRegistry.INSTANCE.register(SummonedEntityRegistry.ENCHANTED_GRASS_BLUE_SHEEP_ENTITY, EnchantedGrassBlueSheepRenderer::new);
        EntityRendererRegistry.INSTANCE.register(SummonedEntityRegistry.ENCHANTED_GRASS_GREEN_SHEEP_ENTITY, EnchantedGrassGreenSheepRenderer::new);
        EntityRendererRegistry.INSTANCE.register(SummonedEntityRegistry.ENCHANTED_GRASS_RED_SHEEP_ENTITY, EnchantedGrassRedSheepRenderer::new);
        EntityRendererRegistry.INSTANCE.register(SummonedEntityRegistry.GOLEM_KIT_GOLEM_ENTITY, GolemKitGolemRenderer::new);
        EntityRendererRegistry.INSTANCE.register(SummonedEntityRegistry.TASTY_BONE_WOLF_ENTITY, TastyBoneWolfRenderer::new);
        EntityRendererRegistry.INSTANCE.register(SummonedEntityRegistry.WONDERFUL_WHEAT_LLAMA_ENTITY, WonderfulWheatLlamaRenderer::new);

    }
    /*


    public static void register() {
        registerRenderMob(SummonedEntityRegistry.BUZZY_NEST_BEE_ENTITY, BuzzyNestBeeRenderer.class);
        registerRenderMob(SummonedEntityRegistry.ENCHANTED_GRASS_BLUE_SHEEP_ENTITY, EnchantedGrassBlueSheepRenderer.class);
        registerRenderMob(SummonedEntityRegistry.ENCHANTED_GRASS_GREEN_SHEEP_ENTITY, EnchantedGrassGreenSheepRenderer.class);
        registerRenderMob(SummonedEntityRegistry.ENCHANTED_GRASS_RED_SHEEP_ENTITY, EnchantedGrassRedSheepRenderer.class);
        registerRenderMob(SummonedEntityRegistry.GOLEM_KIT_GOLEM_ENTITY, GolemKitGolemRenderer.class);
        registerRenderMob(SummonedEntityRegistry.TASTY_BONE_WOLF_ENTITY, TastyBoneWolfRenderer.class);
        registerRenderMob(SummonedEntityRegistry.WONDERFUL_WHEAT_LLAMA_ENTITY, WonderfulWheatLlamaRenderer.class);
    }

    private static void registerRenderMob(EntityType<?> entity, Class<? extends MobEntityRenderer<?, ?>> renderer) {
        EntityRendererRegistry.INSTANCE.register(entity, (context) -> {
            MobEntityRenderer render = null;
            try {
                render = renderer.getConstructor(context.getClass()).newInstance(context);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return render;
        });
    }

    private static void registerRenderAny(EntityType<?> entity, Class<? extends EntityRenderer<?>> renderer) {
        EntityRendererRegistry.INSTANCE.register(entity, (context) -> {
            EntityRenderer render = null;
            try {
                render = renderer.getConstructor(context.getClass()).newInstance(context);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return render;
        });
    }
    */
}
