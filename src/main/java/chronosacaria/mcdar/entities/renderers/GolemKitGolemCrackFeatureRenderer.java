package chronosacaria.mcdar.entities.renderers;

import chronosacaria.mcdar.entities.GolemKitGolemEntity;
import com.google.common.collect.ImmutableMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.IronGolemEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.util.Identifier;

import java.util.Map;

@Environment(EnvType.CLIENT)
public class GolemKitGolemCrackFeatureRenderer extends FeatureRenderer<GolemKitGolemEntity, IronGolemEntityModel<GolemKitGolemEntity>> {
    private static final Map<IronGolemEntity.Crack, Identifier> DAMAGE_TO_TEXTURE;

    public GolemKitGolemCrackFeatureRenderer(FeatureRendererContext<GolemKitGolemEntity, IronGolemEntityModel<GolemKitGolemEntity>> featureRendererContext) {
        super(featureRendererContext);
    }

    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, GolemKitGolemEntity ironGolemEntity, float f, float g, float h, float j, float k, float l) {
        if (!ironGolemEntity.isInvisible()) {
            GolemKitGolemEntity.Crack crack = ironGolemEntity.getCrack();
            if (crack != GolemKitGolemEntity.Crack.NONE) {
                Identifier identifier = DAMAGE_TO_TEXTURE.get(crack);
                renderModel(this.getContextModel(), identifier, matrixStack, vertexConsumerProvider, i, ironGolemEntity, 1.0F, 1.0F, 1.0F);
            }
        }
    }

    static {
        DAMAGE_TO_TEXTURE = ImmutableMap.of(GolemKitGolemEntity.Crack.LOW, new Identifier("textures/entity/iron_golem/iron_golem_crackiness_low.png"), IronGolemEntity.Crack.MEDIUM, new Identifier("textures/entity/iron_golem/iron_golem_crackiness_medium.png"), IronGolemEntity.Crack.HIGH, new Identifier("textures/entity/iron_golem/iron_golem_crackiness_high.png"));
    }
}
