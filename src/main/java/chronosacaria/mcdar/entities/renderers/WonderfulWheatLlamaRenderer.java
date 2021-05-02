package chronosacaria.mcdar.entities.renderers;

import chronosacaria.mcdar.entities.WonderfulWheatLlamaEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.LlamaEntityModel;
import net.minecraft.util.Identifier;

public class WonderfulWheatLlamaRenderer extends MobEntityRenderer<WonderfulWheatLlamaEntity, LlamaEntityModel<WonderfulWheatLlamaEntity>> {
    public WonderfulWheatLlamaRenderer(EntityRenderDispatcher renderDispatcher){
        super(renderDispatcher, new LlamaEntityModel<>(1), 1);
    }

    @Override
    public Identifier getTexture(WonderfulWheatLlamaEntity entity){
        return new Identifier("textures/entity/llama/llama.png");
    }


}
