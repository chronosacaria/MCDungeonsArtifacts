package chronosacaria.mcdar.entities.renderers;

import chronosacaria.mcdar.entities.GolemKitGolemEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.IronGolemEntityModel;
import net.minecraft.util.Identifier;

public class GolemKitGolemRenderer extends MobEntityRenderer<GolemKitGolemEntity, IronGolemEntityModel<GolemKitGolemEntity>> {
    public GolemKitGolemRenderer(EntityRendererFactory.Context context) {
        super(context, new IronGolemEntityModel<>(context.getPart(EntityModelLayers.IRON_GOLEM)), 0.7F);
        this.addFeature(new GolemKitGolemCrackFeatureRenderer(this));
    }

    @Override
    public Identifier getTexture(GolemKitGolemEntity entity){
        return new Identifier("textures/entity/iron_golem/iron_golem.png");
    }

}
