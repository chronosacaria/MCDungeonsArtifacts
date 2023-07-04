package chronosacaria.mcdar.mixin;

import chronosacaria.mcdar.api.AOEHelper;
import chronosacaria.mcdar.api.CleanlinessHelper;
import chronosacaria.mcdar.api.ProjectileEffectHelper;
import chronosacaria.mcdar.config.McdarConfig;
import chronosacaria.mcdar.enums.QuiverArtifactID;
import chronosacaria.mcdar.registries.StatusEffectInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PersistentProjectileEntity.class)
public class PersistentProjectileEntityMixin {

    @Inject(method = "onEntityHit", at = @At("HEAD"), cancellable = true)
    public void mcdar$entityHitPpe(EntityHitResult entityHitResult, CallbackInfo ci){
        PersistentProjectileEntity ppe = ((PersistentProjectileEntity) (Object) this);

        if (entityHitResult.getEntity() instanceof LivingEntity le) {
            if (ppe.getOwner() instanceof PlayerEntity shooter) {
                ItemStack offhand = shooter.getOffHandStack();

                // * Quivers * //
                if (CleanlinessHelper.isCoolingDown(shooter, offhand.getItem())) {

                    if (McdarConfig.CONFIG.ENABLE_QUIVER_ARTIFACT.get(QuiverArtifactID.THUNDERING_QUIVER))
                        if (offhand.isOf(QuiverArtifactID.THUNDERING_QUIVER.getItem()))
                            AOEHelper.electrocute(le, (float) ppe.getDamage());
                    if (McdarConfig.CONFIG.ENABLE_QUIVER_ARTIFACT.get(QuiverArtifactID.TORMENT_QUIVER))
                        if (offhand.isOf(QuiverArtifactID.TORMENT_QUIVER.getItem()))
                            ppe.setPunch(1);

                }
            }
            // * Shielding Status Effect * //
            if (le.hasStatusEffect(StatusEffectInit.SHIELDING)){
                if (ci.isCancellable())
                    ci.cancel();
                ProjectileEffectHelper.ricochetArrowLikeShield(ppe);
            }
        }
    }

    @Inject(method = "onBlockHit", at = @At("HEAD"), cancellable = true)
    public void onTormentingArrowBlockImpact(BlockHitResult blockHitResult, CallbackInfo ci){
        PersistentProjectileEntity ppe = (PersistentProjectileEntity) (Object) this;

        if (McdarConfig.CONFIG.ENABLE_QUIVER_ARTIFACT.get(QuiverArtifactID.TORMENT_QUIVER)) {
            if (ppe.getOwner() instanceof PlayerEntity shooter) {
                ItemStack offhand = shooter.getOffHandStack();

                if (offhand.isOf(QuiverArtifactID.TORMENT_QUIVER.getItem())) {
                    if (CleanlinessHelper.isCoolingDown(shooter, offhand.getItem())) {
                        if (ci.isCancellable()) {
                            ci.cancel();
                        }
                    }
                }
            }
        }
    }

    @Inject(method = "getDragInWater", at = @At("RETURN"), cancellable = true)
    public void onHarpoonArrowFire(CallbackInfoReturnable<Float> cir) {
        PersistentProjectileEntity ppe = (PersistentProjectileEntity) (Object) this;

        if (McdarConfig.CONFIG.ENABLE_QUIVER_ARTIFACT.get(QuiverArtifactID.HARPOON_QUIVER)) {
            if (ppe.getOwner() instanceof PlayerEntity shooter) {
                ItemStack offhand = shooter.getOffHandStack();

                if (offhand.isOf(QuiverArtifactID.HARPOON_QUIVER.getItem())) {
                    if (CleanlinessHelper.isCoolingDown(shooter, offhand.getItem())) {
                        if (ppe.isTouchingWater()) {
                            float normDrag = cir.getReturnValueF();
                            float v = (cir.getReturnValue() == null ? 0.6F : normDrag) * 1.542f;
                            cir.setReturnValue(v);
                        }
                    }
                }
            }
        }
    }
}
