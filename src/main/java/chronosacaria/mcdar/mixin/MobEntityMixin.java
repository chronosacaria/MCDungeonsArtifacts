package chronosacaria.mcdar.mixin;

import chronosacaria.mcdar.init.StatusEffectInit;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin {
    @Shadow public abstract boolean isAiDisabled();

    @Inject(method = "isAiDisabled", at = @At("HEAD"), cancellable = true)
    public void onStunnedMob(CallbackInfoReturnable<Boolean> cir){
        MobEntity mobEntity = (MobEntity) (Object) this;

        if (mobEntity.hasStatusEffect(StatusEffectInit.STUNNED)){
            cir.setReturnValue(true);
        }
    }
}
