package chronosacaria.mcdar.entities.renderers;

import chronosacaria.mcdar.entities.BuzzyNestBeeEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.BeeEntityModel;
import net.minecraft.util.Identifier;

public class BuzzyNestBeeRenderer extends MobEntityRenderer<BuzzyNestBeeEntity, BeeEntityModel<BuzzyNestBeeEntity>> {
    public BuzzyNestBeeRenderer(EntityRenderDispatcher renderDispatcher){
        super(renderDispatcher, new BeeEntityModel<>(), 1);
    }

    @Override
    public Identifier getTexture(BuzzyNestBeeEntity entity){
        if (!entity.hasStung()){
            return new Identifier("textures/entity/bee/bee_angry.png");
        } else if (entity.hasStung()){
            return new Identifier("textures/entity/bee/bee.png");
        }
        return new Identifier("textures/entity/bee/bee.png");
    }
}
