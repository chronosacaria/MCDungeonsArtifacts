package chronosacaria.mcdar.mixin;

import chronosacaria.mcdar.registries.StatusEffectInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(method = "attack", at = @At("HEAD"), cancellable = true)
    public void onPlayerAttackWhilstStunnedTarget(Entity target, CallbackInfo ci) {
        if (((PlayerEntity) (Object) this).hasStatusEffect(StatusEffectInit.STUNNED)){
            ci.cancel();
        }
    }

    @Inject(method = "tickMovement", at = @At("HEAD"), cancellable = true)
    public void onPlayerMovementWhilstStunnedTarget(CallbackInfo ci) {
        if (((PlayerEntity) (Object) this).hasStatusEffect(StatusEffectInit.STUNNED)){
            ci.cancel();
        }
    }
}
