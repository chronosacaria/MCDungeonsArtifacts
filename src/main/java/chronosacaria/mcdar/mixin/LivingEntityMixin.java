package chronosacaria.mcdar.mixin;

import chronosacaria.mcdar.init.StatusEffectInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow public abstract void setHealth(float health);

    @Shadow public abstract boolean clearStatusEffects();

    @Shadow public abstract boolean addStatusEffect(StatusEffectInstance effect);

    @Inject(method = "tryUseTotem", at = @At("HEAD"), cancellable = true)
    public void onSoulProtectionDeath(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir){
        LivingEntity livingEntity = (LivingEntity) (Object) this;

        if (livingEntity.hasStatusEffect(StatusEffectInit.SOUL_PROTECTION)){
            this.setHealth(1.0F);
            this.clearStatusEffects();
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 900, 1));
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));

            cir.setReturnValue(true);
        }
    }
}
