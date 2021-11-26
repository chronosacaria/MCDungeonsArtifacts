package chronosacaria.mcdar.mixin;

import chronosacaria.mcdar.api.AOEHelper;
import chronosacaria.mcdar.api.ProjectileEffectHelper;
import chronosacaria.mcdar.config.McdarConfig;
import chronosacaria.mcdar.enums.QuiverArtefactID;
import chronosacaria.mcdar.init.ArtefactsInit;
import chronosacaria.mcdar.init.StatusEffectInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PersistentProjectileEntity.class)
public abstract class PersistentProjectileEntityMixin {
    @Shadow private double damage;

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

    @Inject(method = "onEntityHit", at = @At("HEAD"), cancellable = true)
    public void onThunderingArrowImpact(EntityHitResult entityHitResult, CallbackInfo ci){
        PersistentProjectileEntity persistentProjectileEntity = (PersistentProjectileEntity) (Object) this;
        Entity entity = entityHitResult.getEntity();
        LivingEntity shooter = (LivingEntity) persistentProjectileEntity.getOwner();
        damage = persistentProjectileEntity.getDamage();

        if (McdarConfig.config.enableQuiverArtefact.get(QuiverArtefactID.THUNDERING_QUIVER)) {
            if (shooter != null) {
                ItemStack offhand = shooter.getOffHandStack();

                if (shooter instanceof PlayerEntity && entity instanceof LivingEntity && offhand.getItem() == ArtefactsInit.quiverArtefact.get(QuiverArtefactID.THUNDERING_QUIVER).asItem()) {
                    float effectTimer = ((PlayerEntity) shooter).getItemCooldownManager().getCooldownProgress(offhand.getItem(), 0);
                    if (effectTimer > 0) {
                        AOEHelper.electrocute(shooter, (LivingEntity) entity, (float) damage);
                    }
                }
            }
        }
    }

    @Inject(method = "onEntityHit", at = @At("HEAD"), cancellable = true)
    public void onTormentingArrowImpact(EntityHitResult entityHitResult, CallbackInfo ci){
        PersistentProjectileEntity persistentProjectileEntity = (PersistentProjectileEntity) (Object) this;
        Entity entity = entityHitResult.getEntity();
        LivingEntity shooter = (LivingEntity) persistentProjectileEntity.getOwner();
        damage = persistentProjectileEntity.getDamage();

        if (McdarConfig.config.enableQuiverArtefact.get(QuiverArtefactID.TORMENT_QUIVER)){
            if (shooter != null) {
                ItemStack offhand = shooter.getOffHandStack();

                if (shooter instanceof PlayerEntity && entity instanceof LivingEntity && offhand.getItem() == ArtefactsInit.quiverArtefact.get(QuiverArtefactID.TORMENT_QUIVER).asItem()) {
                    float effectTimer = ((PlayerEntity) shooter).getItemCooldownManager().getCooldownProgress(offhand.getItem(), 0);
                    if (effectTimer > 0) {
                        persistentProjectileEntity.setPunch(1);
                    }
                }
            }
        }
    }

    @Inject(method = "onBlockHit", at = @At("HEAD"), cancellable = true)
    public void onTormentingArrowBlockImpact(BlockHitResult blockHitResult, CallbackInfo ci){
        PersistentProjectileEntity persistentProjectileEntity = (PersistentProjectileEntity) (Object) this;
        LivingEntity shooter = (LivingEntity) persistentProjectileEntity.getOwner();

        if (McdarConfig.config.enableQuiverArtefact.get(QuiverArtefactID.TORMENT_QUIVER)) {
            if (shooter != null) {
                ItemStack offhand = shooter.getOffHandStack();

                if (shooter instanceof PlayerEntity && offhand.getItem() == ArtefactsInit.quiverArtefact.get(QuiverArtefactID.TORMENT_QUIVER).asItem()) {
                    float effectTimer =
                            ((PlayerEntity) shooter).getItemCooldownManager().getCooldownProgress(offhand.getItem(), 0);
                    if (effectTimer > 0) {
                        if (ci.isCancellable()) {
                            ci.cancel();
                        }
                    }
                }
            }
        }
    }

    @Inject(method = "getDragInWater", at = @At("HEAD"), cancellable = true)
    public void onHarpoonArrowFire(CallbackInfoReturnable<Float> cir) {
        PersistentProjectileEntity persistentProjectileEntity = (PersistentProjectileEntity) (Object) this;
        LivingEntity shooter = (LivingEntity) persistentProjectileEntity.getOwner();

        if (McdarConfig.config.enableQuiverArtefact.get(QuiverArtefactID.HARPOON_QUIVER)) {
            if (shooter != null) {
                ItemStack offhand = shooter.getOffHandStack();

                if (shooter instanceof PlayerEntity && offhand.getItem() == ArtefactsInit.quiverArtefact.get(QuiverArtefactID.HARPOON_QUIVER).asItem()) {
                    float effectTimer =
                            ((PlayerEntity) shooter).getItemCooldownManager().getCooldownProgress(offhand.getItem(), 0);
                    if (effectTimer > 0) {
                        if (persistentProjectileEntity.isTouchingWater()) {
                            float m = 0.85f;
                            cir.setReturnValue(m);
                        }
                    }
                }
            }
        }
    }
}
