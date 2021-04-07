package chronosacaria.mcdar.entities.renderers;

import chronosacaria.mcdar.entities.EnchantedGrassGreenSheepEntity;
import chronosacaria.mcdar.entities.GolemKitGolemEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.IronGolemEntityModel;
import net.minecraft.client.render.entity.model.SheepEntityModel;
import net.minecraft.util.Identifier;

public class EnchantedGrassGreenSheepRenderer extends MobEntityRenderer<EnchantedGrassGreenSheepEntity,
        SheepEntityModel<EnchantedGrassGreenSheepEntity>> {
    public EnchantedGrassGreenSheepRenderer(EntityRenderDispatcher renderDispatcher){
        super(renderDispatcher, new SheepEntityModel<>(), 1);
    }

    @Override
    public Identifier getTexture(EnchantedGrassGreenSheepEntity entity){
        return new Identifier("textures/entity/sheep/sheep.png");
    }


}
