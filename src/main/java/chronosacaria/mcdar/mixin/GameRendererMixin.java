package chronosacaria.mcdar.mixin;

import chronosacaria.mcdar.artefacts.beaconcomponents.BaseBeaconItem;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Inject(at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/render/WorldRenderer;render(Lnet/minecraft/client/util/math/MatrixStack;FJZLnet/minecraft/client/render/Camera;Lnet/minecraft/client/render/GameRenderer;Lnet/minecraft/client/render/LightmapTextureManager;Lnet/minecraft/util/math/Matrix4f;)V",
            shift = At.Shift.AFTER
    ),
    method = "renderWorld")

    private void renderWorldLast(float tickDelta, long limitTime, MatrixStack matrixStack, CallbackInfo ci){
        //BaseBeaconItem.onRenderWorldLast(tickDelta, matrixStack);
    }
}
