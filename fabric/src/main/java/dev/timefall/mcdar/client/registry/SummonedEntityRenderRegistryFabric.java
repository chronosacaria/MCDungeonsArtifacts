package dev.timefall.mcdar.client.registry;

import dev.timefall.mcdar.entity.renderers.*;
import dev.timefall.mcdar.registry.SummonedEntityRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.BeeEntityRenderer;
import net.minecraft.client.render.entity.IronGolemEntityRenderer;
import net.minecraft.client.render.entity.LlamaEntityRenderer;
import net.minecraft.client.render.entity.WolfEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.DyeColor;

@Environment(EnvType.CLIENT)
public class SummonedEntityRenderRegistryFabric {
    public static void register() {

        EntityRendererRegistry.register(SummonedEntityRegistry.BUZZY_NEST_BEE_ENTITY.get(), BeeEntityRenderer::new);
        EntityRendererRegistry.register(SummonedEntityRegistry.ENCHANTED_GRASS_BLUE_SHEEP_ENTITY.get(), context -> new EnchantedGrassSheepRenderer(context, DyeColor.BLUE));
        EntityRendererRegistry.register(SummonedEntityRegistry.ENCHANTED_GRASS_GREEN_SHEEP_ENTITY.get(), context -> new EnchantedGrassSheepRenderer(context,DyeColor.GREEN));
        EntityRendererRegistry.register(SummonedEntityRegistry.ENCHANTED_GRASS_RED_SHEEP_ENTITY.get(), context -> new EnchantedGrassSheepRenderer(context, DyeColor.RED));
        EntityRendererRegistry.register(SummonedEntityRegistry.GOLEM_KIT_GOLEM_ENTITY.get(), IronGolemEntityRenderer::new);
        EntityRendererRegistry.register(SummonedEntityRegistry.TASTY_BONE_WOLF_ENTITY.get(), WolfEntityRenderer::new);
        EntityRendererRegistry.register(SummonedEntityRegistry.WONDERFUL_WHEAT_LLAMA_ENTITY.get(), context -> new LlamaEntityRenderer(context, EntityModelLayers.TRADER_LLAMA));
    }
}
