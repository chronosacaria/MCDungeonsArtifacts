package chronosacaria.mcdar.entities.renderers;

import chronosacaria.mcdar.entities.EnchantedGrassBlueSheepEntity;
import chronosacaria.mcdar.entities.EnchantedGrassGreenSheepEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.SheepEntityModel;
import net.minecraft.util.Identifier;

public class EnchantedGrassBlueSheepRenderer extends MobEntityRenderer<EnchantedGrassBlueSheepEntity,
        SheepEntityModel<EnchantedGrassBlueSheepEntity>> {
    public EnchantedGrassBlueSheepRenderer(EntityRenderDispatcher renderDispatcher){
        super(renderDispatcher, new SheepEntityModel<>(), 1);
    }

    @Override
    public Identifier getTexture(EnchantedGrassBlueSheepEntity entity){
        return new Identifier("textures/entity/sheep/sheep.png");
    }


}
