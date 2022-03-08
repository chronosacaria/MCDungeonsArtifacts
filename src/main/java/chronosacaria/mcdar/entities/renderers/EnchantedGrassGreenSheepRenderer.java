package chronosacaria.mcdar.entities.renderers;

import chronosacaria.mcdar.entities.EnchantedGrassGreenSheepEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.SheepEntityModel;
import net.minecraft.util.Identifier;

public class EnchantedGrassGreenSheepRenderer extends MobEntityRenderer<EnchantedGrassGreenSheepEntity,
        SheepEntityModel<EnchantedGrassGreenSheepEntity>> {
    public EnchantedGrassGreenSheepRenderer(EntityRendererFactory.Context context){
        super(context, new SheepEntityModel(context.getPart(EntityModelLayers.SHEEP)), 0.7F);
    }

    @Override
    public Identifier getTexture(EnchantedGrassGreenSheepEntity entity){
        return new Identifier("textures/entity/sheep/sheep.png");
    }


}
