package chronosacaria.mcdar.entities.renderers;

import chronosacaria.mcdar.entities.EnchantedGrassRedSheepEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.SheepEntityModel;
import net.minecraft.util.Identifier;

public class EnchantedGrassRedSheepRenderer extends MobEntityRenderer<EnchantedGrassRedSheepEntity,
        SheepEntityModel<EnchantedGrassRedSheepEntity>> {
    public EnchantedGrassRedSheepRenderer(EntityRendererFactory.Context context){
        super(context, new SheepEntityModel(context.getPart(EntityModelLayers.SHEEP)), 0.7F);
    }

    @Override
    public Identifier getTexture(EnchantedGrassRedSheepEntity entity){
        return new Identifier("textures/entity/sheep/sheep.png");
    }


}
