package chronosacaria.mcdar.entities.renderers;

import chronosacaria.mcdar.entities.TastyBoneWolfEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.WolfEntityModel;
import net.minecraft.util.Identifier;

public class TastyBoneWolfRenderer extends MobEntityRenderer<TastyBoneWolfEntity, WolfEntityModel<TastyBoneWolfEntity>> {
    public TastyBoneWolfRenderer(EntityRenderDispatcher renderDispatcher){
        super(renderDispatcher, new WolfEntityModel<>(), 1);
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
