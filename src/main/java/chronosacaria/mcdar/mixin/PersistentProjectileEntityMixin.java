package chronosacaria.mcdar.mixin;

import chronosacaria.mcdar.api.AOEHelper;
import chronosacaria.mcdar.api.CleanlinessHelper;
import chronosacaria.mcdar.api.ProjectileEffectHelper;
import chronosacaria.mcdar.config.McdarConfig;
import chronosacaria.mcdar.enums.QuiverArtifactID;
import chronosacaria.mcdar.init.StatusEffectInit;
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
    public void onShieldingArrowImpact(EntityHitResult entityHitResult, CallbackInfo ci){
        if (entityHitResult.getEntity() instanceof LivingEntity living){
            if (living.hasStatusEffect(StatusEffectInit.SHIELDING)){
                if (ci.isCancellable()){
                    ci.cancel();
                }
                PersistentProjectileEntity arrowEntity = (PersistentProjectileEntity) (Object) this;
                ProjectileEffectHelper.ricochetArrowLikeShield(arrowEntity);
            }
        }
    }

    @Inject(method = "onEntityHit", at = @At("HEAD"))
    public void onThunderingArrowImpact(EntityHitResult entityHitResult, CallbackInfo ci){
        PersistentProjectileEntity ppe = ((PersistentProjectileEntity) (Object) this);

        if (McdarConfig.CONFIG.ENABLE_QUIVER_ARTIFACT.get(QuiverArtifactID.THUNDERING_QUIVER)) {
            if (ppe.getOwner() instanceof LivingEntity shooter) {
                ItemStack offhand = shooter.getOffHandStack();

                if (shooter instanceof PlayerEntity shootingPlayer && entityHitResult.getEntity() instanceof LivingEntity living &&
                        offhand.isOf(QuiverArtifactID.THUNDERING_QUIVER.getItem())) {
                    float effectTimer = CleanlinessHelper.getRemainingCD(shootingPlayer, offhand.getItem());
                    if (effectTimer > 0) {
                        AOEHelper.electrocute(shootingPlayer, living, (float) ppe.getDamage());
                    }
                }
            }
        }
    }

    @Inject(method = "onEntityHit", at = @At("HEAD"))
    public void onTormentingArrowImpact(EntityHitResult entityHitResult, CallbackInfo ci){
        PersistentProjectileEntity ppe = (PersistentProjectileEntity) (Object) this;

        if (McdarConfig.CONFIG.ENABLE_QUIVER_ARTIFACT.get(QuiverArtifactID.TORMENT_QUIVER)){
            if (ppe.getOwner() instanceof LivingEntity shooter) {
                ItemStack offhand = shooter.getOffHandStack();

                if (shooter instanceof PlayerEntity shootingPlayer && entityHitResult.getEntity() instanceof LivingEntity &&
                        offhand.isOf(QuiverArtifactID.TORMENT_QUIVER.getItem())) {
                    float effectTimer = CleanlinessHelper.getRemainingCD(shootingPlayer, offhand.getItem());
                    if (effectTimer > 0) {
                        ppe.setPunch(1);
                    }
                }
            }
        }
    }

    @Inject(method = "onBlockHit", at = @At("HEAD"), cancellable = true)
    public void onTormentingArrowBlockImpact(BlockHitResult blockHitResult, CallbackInfo ci){
        PersistentProjectileEntity ppe = (PersistentProjectileEntity) (Object) this;

        if (McdarConfig.CONFIG.ENABLE_QUIVER_ARTIFACT.get(QuiverArtifactID.TORMENT_QUIVER)) {
            if (ppe.getOwner() instanceof LivingEntity shooter) {
                ItemStack offhand = shooter.getOffHandStack();

                if (shooter instanceof PlayerEntity shootingPlayer && offhand.isOf(QuiverArtifactID.TORMENT_QUIVER.getItem())) {
                    float effectTimer = CleanlinessHelper.getRemainingCD(shootingPlayer, offhand.getItem());
                    if (effectTimer > 0) {
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
            if (ppe.getOwner() instanceof LivingEntity shooter) {
                ItemStack offhand = shooter.getOffHandStack();

                if (shooter instanceof PlayerEntity shootingPlayer && offhand.isOf(QuiverArtifactID.HARPOON_QUIVER.getItem())) {
                    float effectTimer = CleanlinessHelper.getRemainingCD(shootingPlayer, offhand.getItem());
                    if (effectTimer > 0) {
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
