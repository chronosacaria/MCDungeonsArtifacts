/*
package chronosacaria.mcdar.artefacts.beaconcomponents;

import chronosacaria.mcdar.Mcdar;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Matrix3f;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import software.bernie.geckolib3.model.AnimatedGeoModel;

@Environment(EnvType.CLIENT)
public class BeaconBeamRenderer {
    AnimatedGeoModel geoModelProvider;
    private static final Identifier CORRUPTED_BEAM_TEXTURE = new Identifier(Mcdar.MOD_ID, "textures/misc" +
            "/beacon_beam_core.png");
    private static final Identifier CORRUPTED_PUMPKIN_TEXTURE = new Identifier(Mcdar.MOD_ID, "textures/entity/corrupted_pumpkin_texture.png");
    private static final Identifier EYE_OF_THE_GUARDIAN_TEXTURE = new Identifier(Mcdar.MOD_ID, "textures/entity/eye_of_the_guardian_texture.png");

    public long allDelta = 0;
    public long deltaGathered = 0;
    public long deltaTime = 0;
    public long lastTime = 0;
    public long currentTime = 0;

    public int scaleSteps = 0;
    public int scaleMaxSteps = 15;
    public int animationSteps = 0;
    public int animationMaxSteps = 20;
    public boolean scaleComplete = false;

    public int beamType = 0;

    public void render(MatrixStack matrixStack, VertexConsumerProvider bufferIn, int packedLightIn,
                       PlayerEntity playerEntity, float ticks){
        ItemStack itemStack = BaseBeaconItem.getBeaconIfHeld(playerEntity);

        double range = BaseBeaconItem.RAYTRACE_DISTANCE;

        Vec3d playerPosition = playerEntity.getEyePos();
        HitResult trace = playerEntity.raycast(range, 0.0F, false);

        float speedModifier = -0.02f;

        BeaconBeamColour beaconBeamColour = BaseBeaconItem.getBeaconBeamColour(itemStack);
        if (beaconBeamColour != null){
            renderBeam(
                    playerEntity,
                    ticks,
                    matrixStack,
                    bufferIn,
                    packedLightIn);
        }
    }

    private void renderBeam(PlayerEntity entity, float partialTicks, MatrixStack matrixStack,
                            VertexConsumerProvider bufferIn, int packedLightIn){
        float h = 20;
        float j = 1;
        float k = j * 0.5F % 1.0F;
        float l = 1.625F;
        matrixStack.push();

        matrixStack.translate(-0.250D, (double)l, -0.750D);

        this.currentTime = entity.world.getTime();
        this.deltaTime = this.currentTime - this.lastTime;
        this.lastTime = this.currentTime;
        if (this.deltaTime > 0) {
            this.deltaGathered += this.deltaTime;
        }

        if (this.deltaGathered > 0) {
            if (!this.scaleComplete) {
                this.scaleSteps += 1;
            }

            this.animationSteps += 1;
            if (this.scaleSteps >= this.scaleMaxSteps) {
                this.scaleComplete = true;

            }
            this.deltaGathered = 0;
        }

        float scalefactor = this.scaleSteps * 0.0625F;
        float pOffset = -11F;
        float pMulti = 2.3F;
        float adjustedPitch = (entity.getPitch(0) * pMulti)+pOffset;
        double pitchClamped = -(Math.max(Math.min(adjustedPitch, 20.0F), -20.0F));
        // Apply pitch to beam.
        matrixStack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion((int) pitchClamped));

        // Widen beam over time.
        matrixStack.translate(0.250F, -0.250F, 0);
        matrixStack.scale(0.0625F+scalefactor, 0.0625F+scalefactor, 1.F);
        matrixStack.translate(-0.250F, 0.250F, 0);

        // Rotate beam.
        matrixStack.translate(0.250F, -0.250F, 0);
        matrixStack.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(this.animationSteps * 24));
        matrixStack.translate(-0.250F, 0.250F, 0);

        Vec3d vec3d2 = Vec3d.ZERO;
        Vec3d vec3d = new Vec3d(vec3d2.x, vec3d2.y, vec3d2.z);
        Vec3d vec3d3 = vec3d.subtract(vec3d2);
        float m = (float)(vec3d3.length() + 1.0D);
        vec3d3 = vec3d3.normalize();
        float n = (float)Math.acos(vec3d3.y);
        float o = (float)Math.atan2(vec3d3.z, vec3d3.x);
        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion((1.5707964F - o) * 57.295776F));
        matrixStack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(n * 57.295776F));

        float r = h * h;
        int R = 64 + (int)(r * 191.0F);
        int G = 32 + (int)(r * 191.0F);
        int B = 128 - (int)(r * 64.0F);
        float aq = -1.0F + k;
        float ar = m * 2.5F + aq;

        Identifier beamTexture;
        if (this.beamType == 0) {
            beamTexture = CORRUPTED_BEAM_TEXTURE;
        } else if (this.beamType == 1){
            beamTexture = CORRUPTED_PUMPKIN_TEXTURE;
        } else {
            beamTexture = EYE_OF_THE_GUARDIAN_TEXTURE;
        }

        RenderLayer customRenderLayer = RenderLayer.getEyes(beamTexture);
        VertexConsumer vertexConsumer = bufferIn.getBuffer(customRenderLayer);
        MatrixStack.Entry entry = matrixStack.peek();
        Matrix4f matrix4f = entry.getModel();
        Matrix3f matrix3f = entry.getNormal();

        // "left" face                                     X     Y     Z
        renderConsumer(vertexConsumer, matrix4f, matrix3f, 0.0F, 0.0F, 0.0F, R, G, B, 0.5F, ar);
        renderConsumer(vertexConsumer, matrix4f, matrix3f, 6.0F, 0.0F, 0.0F, R, G, B, 0.5F, aq);
        renderConsumer(vertexConsumer, matrix4f, matrix3f, 6.0F, 0.5F, 0.0F, R, G, B, 0.0F, aq);
        renderConsumer(vertexConsumer, matrix4f, matrix3f, 0.0F, 0.5F, 0.0F, R, G, B, 0.0F, ar);
        // "top" face
        renderConsumer(vertexConsumer, matrix4f, matrix3f, 6.0F, 0.5F, 0.0F, R, G, B, 0.0F, aq);
        renderConsumer(vertexConsumer, matrix4f, matrix3f, 0.0F, 0.5F, 0.0F, R, G, B, 0.0F, ar);
        renderConsumer(vertexConsumer, matrix4f, matrix3f, 0.0F, 0.5F, 0.5F, R, G, B, 0.5F, ar);
        renderConsumer(vertexConsumer, matrix4f, matrix3f, 6.0F, 0.5F, 0.5F, R, G, B, 0.5F, aq);
        // "front" face
        renderConsumer(vertexConsumer, matrix4f, matrix3f, 0.0F, 0.0F, 0.5F, R, G, B, 0.0F, aq);
        renderConsumer(vertexConsumer, matrix4f, matrix3f, 0.0F, 0.5F, 0.5F, R, G, B, 0.0F, ar);
        renderConsumer(vertexConsumer, matrix4f, matrix3f, 0.0F, 0.5F, 0.0F, R, G, B, 0.5F, ar);
        renderConsumer(vertexConsumer, matrix4f, matrix3f, 0.0F, 0.0F, 0.0F, R, G, B, 0.5F, aq);
        // "back" face
        renderConsumer(vertexConsumer, matrix4f, matrix3f, 6.0F, 0.0F, 0.5F, R, G, B, 0.0F, aq);
        renderConsumer(vertexConsumer, matrix4f, matrix3f, 6.0F, 0.5F, 0.5F, R, G, B, 0.0F, ar);
        renderConsumer(vertexConsumer, matrix4f, matrix3f, 6.0F, 0.5F, 0.0F, R, G, B, 0.5F, ar);
        renderConsumer(vertexConsumer, matrix4f, matrix3f, 6.0F, 0.0F, 0.0F, R, G, B, 0.5F, aq);
        // "bottom" face
        renderConsumer(vertexConsumer, matrix4f, matrix3f, 0.0F, 0.0F, 0.5F, R, G, B, 0.0F, aq);
        renderConsumer(vertexConsumer, matrix4f, matrix3f, 6.0F, 0.0F, 0.5F, R, G, B, 0.0F, ar);
        renderConsumer(vertexConsumer, matrix4f, matrix3f, 6.0F, 0.0F, 0.0F, R, G, B, 0.5F, ar);
        renderConsumer(vertexConsumer, matrix4f, matrix3f, 0.0F, 0.0F, 0.0F, R, G, B, 0.5F, aq);
        // "right" face
        renderConsumer(vertexConsumer, matrix4f, matrix3f, 6.0F, 0.5F, 0.5F, R, G, B, 0.5F, ar);
        renderConsumer(vertexConsumer, matrix4f, matrix3f, 0.0F, 0.5F, 0.5F, R, G, B, 0.5F, aq);
        renderConsumer(vertexConsumer, matrix4f, matrix3f, 0.0F, 0.0F, 0.5F, R, G, B, 0.0F, aq);
        renderConsumer(vertexConsumer, matrix4f, matrix3f, 6.0F, 0.0F, 0.5F, R, G, B, 0.0F, ar);

        matrixStack.pop();
    }

    public void renderConsumer(VertexConsumer vertexConsumer, Matrix4f matrix4f, Matrix3f matrix3f, float f, float g, float h, int i, int j, int k, float l, float m) {
        vertexConsumer.vertex(matrix4f, f, g, h).color(i, j, k, 128).texture(l, m).overlay(OverlayTexture.DEFAULT_UV).light(15728880).normal(matrix3f, 0.0F, 1.0F, 0.0F).next();
    }
}
*/
