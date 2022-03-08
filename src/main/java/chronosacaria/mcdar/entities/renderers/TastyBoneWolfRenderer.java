package chronosacaria.mcdar.entities.renderers;

import chronosacaria.mcdar.entities.TastyBoneWolfEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.WolfEntityModel;
import net.minecraft.util.Identifier;

public class TastyBoneWolfRenderer extends MobEntityRenderer<TastyBoneWolfEntity, WolfEntityModel<TastyBoneWolfEntity>> {
    public TastyBoneWolfRenderer(EntityRendererFactory.Context context) {
        super(context, new WolfEntityModel(context.getPart(EntityModelLayers.WOLF)), 0.5F);
    }

    @Override
    public Identifier getTexture(TastyBoneWolfEntity entity){
        if (!entity.hasAngerTime()){
            return new Identifier("textures/entity/wolf/wolf_angry.png");
        } else if (entity.hasAngerTime()){
            return new Identifier("textures/entity/wolf/wolf.png");
        }
        return new Identifier("textures/entity/wolf/wolf.png");
    }


}
