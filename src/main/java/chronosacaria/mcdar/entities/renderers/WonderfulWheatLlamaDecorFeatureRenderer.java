package chronosacaria.mcdar.entities.renderers;

import chronosacaria.mcdar.entities.WonderfulWheatLlamaEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.LlamaEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class WonderfulWheatLlamaDecorFeatureRenderer extends FeatureRenderer<WonderfulWheatLlamaEntity, LlamaEntityModel<WonderfulWheatLlamaEntity>> {
    private static final Identifier TRADER_LLAMA_DECOR = new Identifier("textures/entity/llama/decor/trader_llama.png");
    private final LlamaEntityModel<WonderfulWheatLlamaEntity> model;

    public WonderfulWheatLlamaDecorFeatureRenderer(FeatureRendererContext<WonderfulWheatLlamaEntity, LlamaEntityModel<WonderfulWheatLlamaEntity>> context, EntityModelLoader loader) {
        super(context);
        this.model = new LlamaEntityModel<>(loader.getModelPart(EntityModelLayers.LLAMA_DECOR));
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, WonderfulWheatLlamaEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        this.getContextModel().copyStateTo(this.model);
        this.model.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(TRADER_LLAMA_DECOR));
        this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
    }
}
