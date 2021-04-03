package chronosacaria.mcdar.entities.renderers;

import chronosacaria.mcdar.entities.GolemKitGolemEntity;
import chronosacaria.mcdar.entities.TastyBoneWolfEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.IronGolemEntityModel;
import net.minecraft.client.render.entity.model.WolfEntityModel;
import net.minecraft.util.Identifier;

public class GolemKitGolemRenderer extends MobEntityRenderer<GolemKitGolemEntity, IronGolemEntityModel<GolemKitGolemEntity>> {
    public GolemKitGolemRenderer(EntityRenderDispatcher renderDispatcher){
        super(renderDispatcher, new IronGolemEntityModel<>(), 1);
    }

    @Override
    public Identifier getTexture(GolemKitGolemEntity entity){
        return new Identifier("textures/entity/iron_golem/iron_golem.png");
    }


}
