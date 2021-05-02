package chronosacaria.mcdar.entities.renderers;

import chronosacaria.mcdar.entities.EnchantedGrassRedSheepEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.SheepEntityModel;
import net.minecraft.util.Identifier;

public class EnchantedGrassRedSheepRenderer extends MobEntityRenderer<EnchantedGrassRedSheepEntity,
        SheepEntityModel<EnchantedGrassRedSheepEntity>> {
    public EnchantedGrassRedSheepRenderer(EntityRenderDispatcher renderDispatcher){
        super(renderDispatcher, new SheepEntityModel<>(), 1);
    }

    @Override
    public Identifier getTexture(EnchantedGrassRedSheepEntity entity){
        return new Identifier("textures/entity/sheep/sheep.png");
    }


}
