package chronosacaria.mcdar.mixin;

import chronosacaria.mcdar.api.ProjectileEffectHelper;
import chronosacaria.mcdar.init.StatusEffectInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.hit.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PersistentProjectileEntity.class)
public class PersistentProjectileEntityMixin {
    @Inject(method = "onEntityHit", at = @At("HEAD"), cancellable = true)
    public void onShieldingArrowImpact(EntityHitResult entityHitResult, CallbackInfo ci){
        PersistentProjectileEntity arrowEntity = (PersistentProjectileEntity) (Object) this;
        Entity entity = entityHitResult.getEntity();
        if (entity instanceof LivingEntity){
            if (((LivingEntity) entity).hasStatusEffect(StatusEffectInit.SHIELDING)){
                if (ci.isCancellable()){
                    ci.cancel();
                }
                ProjectileEffectHelper.ricochetArrowLikeShield(arrowEntity, (LivingEntity) entity);
            }
        }
    }
}
