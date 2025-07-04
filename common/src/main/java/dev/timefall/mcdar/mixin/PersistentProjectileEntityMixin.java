package dev.timefall.mcdar.mixin;

import dev.timefall.mcdar.api.CleanlinessHelper;
import dev.timefall.mcdar.api.ProjectileEffectHelper;
import dev.timefall.mcdar.config.McdarArtifactsStatsConfig;
import dev.timefall.mcdar.registry.ArtifactsRegistry;
import dev.timefall.mcdar.registry.StatusEffectRegistry;
import dev.timefall.mcdx.api.AOEHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PersistentProjectileEntity.class)
public class PersistentProjectileEntityMixin {

    @Inject(method = "onEntityHit", at = @At("HEAD"), cancellable = true)
    public void mcdar$entityHitPpe(EntityHitResult entityHitResult, CallbackInfo ci){
        PersistentProjectileEntity persistentProjectileEntity = ((PersistentProjectileEntity) (Object) this);

        if (entityHitResult.getEntity() instanceof LivingEntity livingEntity) {
            if (persistentProjectileEntity.getOwner() instanceof PlayerEntity shooter) {
                ItemStack offhand = shooter.getOffHandStack();

                // * Quivers * //
                if (CleanlinessHelper.isCoolingDown(shooter, offhand.getItem())) {

                    if (McdarArtifactsStatsConfig.CONFIG.mcdar$getQuiverArtifactStats().THUNDERING_QUIVER_STATS.mcdar$getIsEnabled())
                        if (offhand.isOf(ArtifactsRegistry.THUNDERING_QUIVER.get()))
                            AOEHelper.electrocute(livingEntity, (float) persistentProjectileEntity.getDamage());
                    if (McdarArtifactsStatsConfig.CONFIG.mcdar$getQuiverArtifactStats().TORMENT_QUIVER_STATS.mcdar$getIsEnabled())
                        if (offhand.isOf(ArtifactsRegistry.TORMENT_QUIVER.get())) {

                            // TODO: There's gotta be a better way to do this
                            double d = Math.max(0.0, 1.0 - livingEntity.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE));
                            Vec3d vec3d = persistentProjectileEntity.getVelocity().multiply(1.0, 0.0, 1.0).normalize().multiply(0.6 * d);
                            if (vec3d.lengthSquared() > 0.0) {
                                livingEntity.addVelocity(vec3d.x, 0.1, vec3d.z);
                            }
                        }


                }
            }
            // * Shielding Status Effect * //
            if (livingEntity.hasStatusEffect(StatusEffectRegistry.SHIELDING.getEntry())){
                if (ci.isCancellable())
                    ci.cancel();
                ProjectileEffectHelper.ricochetArrowLikeShield(persistentProjectileEntity);
            }
        }
    }

    @Inject(method = "onBlockHit", at = @At("HEAD"), cancellable = true)
    public void onTormentingArrowBlockImpact(BlockHitResult blockHitResult, CallbackInfo ci){
        PersistentProjectileEntity ppe = (PersistentProjectileEntity) (Object) this;

        if (McdarArtifactsStatsConfig.CONFIG.mcdar$getQuiverArtifactStats().TORMENT_QUIVER_STATS.mcdar$getIsEnabled()) {
            if (ppe.getOwner() instanceof PlayerEntity shooter) {
                ItemStack offhand = shooter.getOffHandStack();

                if (offhand.isOf(ArtifactsRegistry.TORMENT_QUIVER.get())) {
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

        if (McdarArtifactsStatsConfig.CONFIG.mcdar$getQuiverArtifactStats().HARPOON_QUIVER_STATS.mcdar$getIsEnabled()) {
            if (ppe.getOwner() instanceof PlayerEntity shooter) {
                ItemStack offhand = shooter.getOffHandStack();

                if (offhand.isOf(ArtifactsRegistry.HARPOON_QUIVER.get())) {
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
