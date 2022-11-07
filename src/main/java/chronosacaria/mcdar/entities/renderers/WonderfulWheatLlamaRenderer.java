package chronosacaria.mcdar.entities.renderers;

import chronosacaria.mcdar.entities.WonderfulWheatLlamaEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.LlamaEntityModel;
import net.minecraft.util.Identifier;

public class WonderfulWheatLlamaRenderer extends MobEntityRenderer<WonderfulWheatLlamaEntity, LlamaEntityModel<WonderfulWheatLlamaEntity>> {
    public WonderfulWheatLlamaRenderer(EntityRendererFactory.Context context) {
        super(context, new LlamaEntityModel<>(context.getPart(EntityModelLayers.LLAMA)), 0.7F);
    }

    @Override
    public Identifier getTexture(WonderfulWheatLlamaEntity entity){
        return new Identifier("textures/entity/llama/llama.png");
    }

}
