package chronosacaria.mcdar.entities.renderers;

import chronosacaria.mcdar.entities.EnchantedGrassGreenSheepEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.SheepWoolFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.SheepEntityModel;
import net.minecraft.client.render.entity.model.SheepWoolEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

public class EnchantedGrassGreenSheepWoolFeatureRenderer extends FeatureRenderer<EnchantedGrassGreenSheepEntity, SheepEntityModel<EnchantedGrassGreenSheepEntity>> {
    private static final Identifier SKIN = new Identifier("textures/entity/sheep/sheep_fur.png");
    private final SheepWoolEntityModel<EnchantedGrassGreenSheepEntity> model;
    public EnchantedGrassGreenSheepWoolFeatureRenderer(FeatureRendererContext<EnchantedGrassGreenSheepEntity, SheepEntityModel<EnchantedGrassGreenSheepEntity>> context, EntityModelLoader loader) {
        super(context);
        this.model = new SheepWoolEntityModel<>(loader.getModelPart(EntityModelLayers.SHEEP_FUR));

    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, EnchantedGrassGreenSheepEntity sheepEntity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        float u;
        float t;
        float s;
        if (sheepEntity.isSheared()) {
            return;
        }
        if (sheepEntity.isInvisible()) {
            MinecraftClient minecraftClient = MinecraftClient.getInstance();
            boolean bl = minecraftClient.hasOutline(sheepEntity);
            if (bl) {
                this.getContextModel().copyStateTo(this.model);
                this.model.animateModel(sheepEntity, limbAngle, limbDistance, tickDelta);
                this.model.setAngles(sheepEntity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
                VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getOutline(SKIN));
                this.model.render(matrixStack, vertexConsumer, light, LivingEntityRenderer.getOverlay(sheepEntity, 0.0f), 0.0f, 0.0f, 0.0f, 1.0f);
            }
            return;
        }
        if (sheepEntity.hasCustomName() && "Lilly".equals(sheepEntity.getName().getString())) {
            float[] fs = SheepEntity.getRgbColor(DyeColor.byId(6));
            s = fs[0];
            t = fs[1];
            u = fs[2];
        } else {
            float[] hs = SheepEntity.getRgbColor(DyeColor.GREEN);
            s = hs[0];
            t = hs[1];
            u = hs[2];
        }
        SheepWoolFeatureRenderer.render(this.getContextModel(), this.model, SKIN, matrixStack, vertexConsumerProvider, light, sheepEntity, limbAngle, limbDistance, animationProgress, headYaw, headPitch, tickDelta, s, t, u);
    }
}
