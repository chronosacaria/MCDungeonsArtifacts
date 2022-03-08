/*package chronosacaria.mcdar.artefacts.beacon;

import chronosacaria.mcdar.client.events.RenderWorldLastEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec3d;

public class BeaconBeamRenderer {
    public static void renderBeam(RenderWorldLastEvent event, PlayerEntity playerEntity, float ticks){
        ItemStack itemStack = AbstractBeaconItem.getBeacon(playerEntity);

        double range = AbstractBeaconItem.RAYTRACE_DISTANCE;

        Vec3d playerPosition = playerEntity.getCameraPosVec(ticks);
        HitResult hitResult = playerEntity.raycast(range, 0.0F, false);

        float speedModifier = -0.02F;

        BeaconBeamColor beaconBeamColor = AbstractBeaconItem.getBeaconBeamColor(itemStack);
        if (beaconBeamColor != null){
            drawBeams(event, playerPosition, hitResult, 0, 0, 0, beaconBeamColor.getRedValue() / 255f,
                    beaconBeamColor.getGreenValue() / 255f, beaconBeamColor.getBlueValue() / 255f, 0.02f, playerEntity,
                    ticks, speedModifier);
        }
    }

    private static void drawBeams(RenderWorldLastEvent event, Vec3d from, HitResult hitResult, double xOffset,
                                  double yOffset, double zOffset, float r, float g, float b, float thickness,
                                  PlayerEntity playerEntity, float ticks, float speedModifier) {
        Hand activeHand;
        if (playerEntity.getMainHandStack().getItem() instanceof AbstractBeaconItem){
            activeHand = Hand.MAIN_HAND;
        } else if (playerEntity.getOffHandStack().getItem() instanceof AbstractBeaconItem){
            activeHand = Hand.OFF_HAND;
        } else {
            return;
        }

        VertexConsumer builder;

        ItemStack itemStack = playerEntity.getStackInHand(activeHand);
        double distance = Math.max(1, from.subtract(hitResult.getPos()).length());
        long gameTime = playerEntity.world.getTime();
        double v = gameTime * speedModifier;
        float additiveThickness = (thickness * 3.5f) * calculateLaserFlickerModifier(gameTime);

        float beam2r = 0;
        float beam2g = 0;
        float beam2b = 0;
        BeaconBeamColor beaconBeamColor = AbstractBeaconItem.getBeaconBeamColor(itemStack);
        if(beaconBeamColor != null){
            beam2r = beaconBeamColor.getInnerRedValue() / 255f;
            beam2g = beaconBeamColor.getInnerGreenValue() / 255f;
            beam2b = beaconBeamColor.getInnerBlueValue() / 255f;
        }

        Vec3d view = MinecraftClient.getInstance().gameRenderer.getCamera().getPos();

        VertexConsumerProvider.Immediate buffer =
                MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();


    }
}
*/