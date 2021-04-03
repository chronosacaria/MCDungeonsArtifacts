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
    public static void register() {
        registerRenderMob(SummonedEntityRegistry.BUZZY_NEST_BEE_ENTITY, BuzzyNestBeeRenderer.class);
        registerRenderMob(SummonedEntityRegistry.GOLEM_KIT_GOLEM_ENTITY, GolemKitGolemRenderer.class);
        registerRenderMob(SummonedEntityRegistry.TASTY_BONE_WOLF_ENTITY, TastyBoneWolfRenderer.class);
        registerRenderMob(SummonedEntityRegistry.WONDERFUL_WHEAT_LLAMA_ENTITY, WonderfulWheatLlamaRenderer.class);
    }

    private static void registerRenderMob(EntityType<?> entity, Class<? extends MobEntityRenderer<?, ?>> renderer) {
        EntityRendererRegistry.INSTANCE.register(entity, ((entityRenderDispatcher, context) -> {
            MobEntityRenderer<?, ?> render = null;
            try {
                render = renderer.getConstructor(entityRenderDispatcher.getClass()).newInstance(entityRenderDispatcher);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return render;
        }));
    }

    private static void registerRenderAny(EntityType<?> entity, Class<? extends EntityRenderer<?>> renderer) {
        EntityRendererRegistry.INSTANCE.register(entity, ((entityRenderDispatcher, context) -> {
            EntityRenderer<?> render = null;
            try {
                render = renderer.getConstructor(entityRenderDispatcher.getClass()).newInstance(entityRenderDispatcher);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return render;
        }));
    }
}
