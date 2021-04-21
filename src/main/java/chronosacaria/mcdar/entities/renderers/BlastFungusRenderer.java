package chronosacaria.mcdar.entities.renderers;

import chronosacaria.mcdar.entities.BlastFungusEntity;
import chronosacaria.mcdar.init.BlockRegistry;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.TntMinecartEntityRenderer;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class BlastFungusRenderer extends EntityRenderer<BlastFungusEntity> {
    public BlastFungusRenderer(EntityRenderDispatcher entityRenderDispatcher){
        super(entityRenderDispatcher);
        this.shadowRadius = 0.5F;
    }

    public void render(BlastFungusEntity blastFungusEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.translate(0.0D, 0.5D, 0.0D);
        if ((float)blastFungusEntity.getFuseTimer() - g + 1.0F < 10.0F) {
            float h = 1.0F - ((float)blastFungusEntity.getFuseTimer() - g + 1.0F) / 10.0F;
            h = MathHelper.clamp(h, 0.0F, 1.0F);
            h *= h;
            h *= h;
            float j = 1.0F + h * 0.3F;
            matrixStack.scale(j, j, j);
        }

        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(-90.0F));
        matrixStack.translate(-0.5D, -0.5D, 0.5D);
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(90.0F));
        TntMinecartEntityRenderer.renderFlashingBlock(BlockRegistry.BLAST_FUNGUS_BLOCK.getDefaultState(), matrixStack,
                vertexConsumerProvider, i, blastFungusEntity.getFuseTimer() / 5 % 2 == 0);
        matrixStack.pop();
        super.render(blastFungusEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    public Identifier getTexture(BlastFungusEntity blastFungusEntity) {
        return SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE;
    }
}
