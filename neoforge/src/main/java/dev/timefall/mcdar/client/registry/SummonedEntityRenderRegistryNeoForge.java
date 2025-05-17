package dev.timefall.mcdar.client.registry;

import dev.timefall.mcdar.entity.renderers.*;
import dev.timefall.mcdar.registry.SummonedEntityRegistry;
import net.minecraft.client.render.entity.BeeEntityRenderer;
import net.minecraft.client.render.entity.IronGolemEntityRenderer;
import net.minecraft.client.render.entity.LlamaEntityRenderer;
import net.minecraft.client.render.entity.WolfEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.DyeColor;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class SummonedEntityRenderRegistryNeoForge {

    public static void register(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(SummonedEntityRegistry.BUZZY_NEST_BEE_ENTITY.get(), BeeEntityRenderer::new);
        event.registerEntityRenderer(SummonedEntityRegistry.ENCHANTED_GRASS_BLUE_SHEEP_ENTITY.get(), context -> new EnchantedGrassSheepRenderer(context, DyeColor.BLUE));
        event.registerEntityRenderer(SummonedEntityRegistry.ENCHANTED_GRASS_GREEN_SHEEP_ENTITY.get(), context -> new EnchantedGrassSheepRenderer(context, DyeColor.GREEN));
        event.registerEntityRenderer(SummonedEntityRegistry.ENCHANTED_GRASS_RED_SHEEP_ENTITY.get(), context -> new EnchantedGrassSheepRenderer(context, DyeColor.RED));
        event.registerEntityRenderer(SummonedEntityRegistry.GOLEM_KIT_GOLEM_ENTITY.get(), IronGolemEntityRenderer::new);
        event.registerEntityRenderer(SummonedEntityRegistry.TASTY_BONE_WOLF_ENTITY.get(), WolfEntityRenderer::new);
        event.registerEntityRenderer(SummonedEntityRegistry.WONDERFUL_WHEAT_LLAMA_ENTITY.get(), context -> new LlamaEntityRenderer(context, EntityModelLayers.TRADER_LLAMA));
    }
}
