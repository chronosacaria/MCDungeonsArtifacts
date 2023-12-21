package chronosacaria.mcdar.entities.renderers;

import chronosacaria.mcdar.entities.TastyBoneWolfEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.WolfEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class TastyBoneWolfRenderer extends MobEntityRenderer<TastyBoneWolfEntity, WolfEntityModel<TastyBoneWolfEntity>> {
    public TastyBoneWolfRenderer(EntityRendererFactory.Context context) {
        super(context, new WolfEntityModel<>(context.getPart(EntityModelLayers.WOLF)), 0.5F);
    }

    public void render(TastyBoneWolfEntity wolfEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if (wolfEntity.isFurWet()) {
            float h = wolfEntity.getFurWetBrightnessMultiplier(g);
            this.model.setColorMultiplier(h, h, h);
        }

        super.render(wolfEntity, f, g, matrixStack, vertexConsumerProvider, i);
        if (wolfEntity.isFurWet()) {
            this.model.setColorMultiplier(1.0F, 1.0F, 1.0F);
        }
    }
    @Override
    protected float getAnimationProgress(TastyBoneWolfEntity entity, float tickDelta) {
        return entity.getTailAngle();
    }

    @Override
    public Identifier getTexture(TastyBoneWolfEntity entity){
        return new Identifier("textures/entity/wolf/wolf" + (entity.hasAngerTime() ? "_angry" : "") + ".png");
    }

}
