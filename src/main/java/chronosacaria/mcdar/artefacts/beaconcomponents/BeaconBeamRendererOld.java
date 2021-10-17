package chronosacaria.mcdar.artefacts.beaconcomponents;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.*;

public class BeaconBeamRendererOld {
    public static void renderBeam(MatrixStack matrixStack, PlayerEntity playerEntity, float ticks){
        ItemStack itemStack = BaseBeaconItem.getBeaconIfHeld(playerEntity);

        double range = BaseBeaconItem.RAYTRACE_DISTANCE;

        Vec3d playerPosition = playerEntity.getEyePos();
        HitResult trace = playerEntity.raycast(range, 0.0F, false);

        float speedModifier = -0.02f;

        BeaconBeamColour beaconBeamColour = BaseBeaconItem.getBeaconBeamColour(itemStack);
        if (beaconBeamColour != null){
            drawBeams(
                    matrixStack,
                    playerPosition,
                    trace,
                    0,
                    0,
                    0,
                    beaconBeamColour.getRedValue() / 255f,
                    beaconBeamColour.getGreenValue() / 255f,
                    beaconBeamColour.getBlueValue() / 255f,
                    0.02f,
                    playerEntity,
                    ticks,
                    speedModifier);
        }
    }

    private static void drawBeams(MatrixStack matrixStack, Vec3d from, HitResult trace, double xOffset,
                                  double yOffset, double zOffset, float r, float g, float b, float thickness,
                                  PlayerEntity playerEntity, float ticks, float speedmodifier){
        Hand activeHand;
        if (playerEntity.getMainHandStack().getItem() instanceof BaseBeaconItem){
            activeHand = Hand.MAIN_HAND;
        } else if (playerEntity.getOffHandStack().getItem() instanceof BaseBeaconItem){
            activeHand = Hand.OFF_HAND;
        } else {
            return;
        }

        VertexConsumer builder;
        ItemStack itemStack = playerEntity.getStackInHand(activeHand);
        double distance = Math.max(1, from.subtract(trace.getPos()).length());
        long gameTime = playerEntity.world.getTime();
        double v = gameTime * speedmodifier;
        float additiveThickness = (thickness * 3.5f) * calculateFlickerModifier(gameTime);

        float beam2r = 0;
        float beam2g = 0;
        float beam2b = 0;
        BeaconBeamColour beaconBeamColour = BaseBeaconItem.getBeaconBeamColour(itemStack);
        if (beaconBeamColour != null){
            beam2r = beaconBeamColour.getInnerRedValue() / 255f;
            beam2g = beaconBeamColour.getGreenValue() / 255f;
            beam2b = beaconBeamColour.getInnerBlueValue() / 255f;
        }

        Vec3d view = MinecraftClient.getInstance().gameRenderer.getCamera().getPos();
        VertexConsumerProvider.Immediate buffer = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();

        matrixStack.push();

        matrixStack.translate(-view.getX(), -view.getY(), -view.getZ());
        matrixStack.translate(from.x, from.y, from.z);
        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(MathHelper.lerp(ticks, -playerEntity.getYaw(), -playerEntity.prevYaw)));
        matrixStack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(MathHelper.lerp(ticks, playerEntity.getPitch(), playerEntity.prevPitch)));

        MatrixStack.Entry entry = matrixStack.peek();
        Matrix3f matrixNormal = entry.getNormal();
        Matrix4f positionMatrix = entry.getModel();

        builder = buffer.getBuffer(BeaconRenderLayer.BEACON_BEAM_GLOW);
        drawBeam(xOffset, yOffset, zOffset, builder, positionMatrix, matrixNormal, additiveThickness, activeHand, distance, 0.5, 1, ticks, r, g, b, 0.7f);

        builder = buffer.getBuffer(BeaconRenderLayer.BEACON_BEAM_MAIN);
        drawBeam(xOffset, yOffset, zOffset, builder, positionMatrix, matrixNormal, thickness, activeHand, distance, v, v + distance * 1.5, ticks, r,g,b,1f);

        builder = buffer.getBuffer(BeaconRenderLayer.BEACON_BEAM_CORE);
        drawBeam(xOffset, yOffset, zOffset, builder, positionMatrix, matrixNormal, thickness/2, activeHand, distance, v, v + distance * 1.5, ticks, beam2r, beam2g, beam2b, 1f);
        matrixStack.pop();
    }

    private static float calculateFlickerModifier(long gameTime){
        return 0.9f + 0.1f * MathHelper.sin(gameTime * 0.99f) * MathHelper.sin(gameTime * 0.3f) * MathHelper.sin(gameTime * 0.1f);
    }

    private static void drawBeam(double xOffset, double yOffset, double zOffset, VertexConsumer builder,
                                 Matrix4f positionMatrix, Matrix3f matrixNormal, float thickness, Hand hand,
                                 double distance, double v1, double v2, float ticks, float r, float g, float b,
                                 float alpha){
        Vec3f vec3f = new Vec3f(0.0f, 1.0f, 0.0f);
        vec3f.transform(matrixNormal);
        ClientPlayerEntity playerEntity = MinecraftClient.getInstance().player;
        if (MinecraftClient.getInstance().options.mainArm != Arm.RIGHT)
            hand = hand == Hand.MAIN_HAND ? Hand.OFF_HAND : Hand.MAIN_HAND;
        float startXOffset = -0.25f;
        float startYOffset = -0.115f;
        float startZOffset = 0f;
        if (playerEntity != null) {
            startZOffset = 0.56f + (1 - 0.75f); //Needs to be Field of View??
        }
        if (hand == Hand.OFF_HAND){
            startYOffset = -0.120f;
            startXOffset = 0.25f;
        }
        float f = 0;
        if (playerEntity != null){
            f = (MathHelper.lerp(ticks, playerEntity.prevPitch, playerEntity.getPitch() - MathHelper.lerp(ticks,
                    playerEntity.lastRenderPitch, playerEntity.renderPitch)));
        }
        float f1 = 0;
        if (playerEntity != null){
        f1 = (MathHelper.lerp(ticks, playerEntity.prevYaw, playerEntity.getYaw() - MathHelper.lerp(ticks,
                playerEntity.lastRenderYaw, playerEntity.renderYaw)));
        }
        startXOffset = startXOffset + (f1 / 750);
        startYOffset = startYOffset + (f / 750);

        Vector4f vec1 = new Vector4f(startXOffset, -thickness + startYOffset, startZOffset, 1.0f);
        vec1.transform(positionMatrix);
        Vector4f vec2 = new Vector4f((float) xOffset, -thickness + (float) yOffset, (float) distance + (float) zOffset, 1.0f);
        vec2.transform(positionMatrix);
        Vector4f vec3 = new Vector4f((float) xOffset, thickness + (float) yOffset, (float) distance + (float) zOffset, 1.0f);
        vec3.transform(positionMatrix);
        Vector4f vec4 = new Vector4f(startXOffset, thickness + startYOffset, startZOffset, 1.0f);
        vec4.transform(positionMatrix);

        if (hand == Hand.MAIN_HAND){
            builder.vertex(vec4.getX(), vec4.getY(), vec4.getZ(), r, g, b, alpha, 0, (float) v1, OverlayTexture.DEFAULT_UV, 15728880, vec3f.getX(), vec3f.getY(), vec3f.getZ());
            builder.vertex(vec3.getX(), vec3.getY(), vec3.getZ(), r, g, b, alpha, 0, (float) v2, OverlayTexture.DEFAULT_UV, 15728880, vec3f.getX(), vec3f.getY(), vec3f.getZ());
            builder.vertex(vec2.getX(), vec2.getY(), vec2.getZ(), r, g, b, alpha, 1, (float) v2, OverlayTexture.DEFAULT_UV, 15728880, vec3f.getX(), vec3f.getY(), vec3f.getZ());
            builder.vertex(vec1.getX(), vec1.getY(), vec1.getZ(), r, g, b, alpha, 1, (float) v1, OverlayTexture.DEFAULT_UV, 15728880, vec3f.getX(), vec3f.getY(), vec3f.getZ());
        } else {
            builder.vertex(vec1.getX(), vec1.getY(), vec1.getZ(), r, g, b, alpha, 1, (float) v1, OverlayTexture.DEFAULT_UV, 15728880, vec3f.getX(), vec3f.getY(), vec3f.getZ());
            builder.vertex(vec2.getX(), vec2.getY(), vec2.getZ(), r, g, b, alpha, 1, (float) v2, OverlayTexture.DEFAULT_UV, 15728880, vec3f.getX(), vec3f.getY(), vec3f.getZ());
            builder.vertex(vec3.getX(), vec3.getY(), vec3.getZ(), r, g, b, alpha, 0, (float) v2, OverlayTexture.DEFAULT_UV, 15728880, vec3f.getX(), vec3f.getY(), vec3f.getZ());
            builder.vertex(vec4.getX(), vec4.getY(), vec4.getZ(), r, g, b, alpha, 0, (float) v1, OverlayTexture.DEFAULT_UV, 15728880, vec3f.getX(), vec3f.getY(), vec3f.getZ());
        }
    }
}
