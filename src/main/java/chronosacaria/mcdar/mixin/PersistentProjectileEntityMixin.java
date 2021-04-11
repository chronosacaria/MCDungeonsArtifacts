package chronosacaria.mcdar.mixin;

import chronosacaria.mcdar.api.AOEHelper;
import chronosacaria.mcdar.api.ProjectileEffectHelper;
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

        ItemStack offhand = null;
        if (shooter != null) {
            offhand = shooter.getOffHandStack();
        }

        if (shooter instanceof PlayerEntity && entity instanceof LivingEntity && offhand.getItem() == ArtefactsInit.THUNDERING_QUIVER.asItem()){
            float effectTimer = ((PlayerEntity) shooter).getItemCooldownManager().getCooldownProgress(offhand.getItem(), 0);
            if (effectTimer > 0){
                AOEHelper.electrocute(shooter, (LivingEntity) entity, (float) damage);
            }
        }
    }

    @Inject(method = "onEntityHit", at = @At("HEAD"), cancellable = true)
    public void onTormentingArrowImpact(EntityHitResult entityHitResult, CallbackInfo ci){
        PersistentProjectileEntity persistentProjectileEntity = (PersistentProjectileEntity) (Object) this;
        Entity entity = entityHitResult.getEntity();
        LivingEntity shooter = (LivingEntity) persistentProjectileEntity.getOwner();
        damage = persistentProjectileEntity.getDamage();

        ItemStack offhand = null;
        if (shooter != null) {
            offhand = shooter.getOffHandStack();
        }
        if (shooter instanceof PlayerEntity && entity instanceof LivingEntity && offhand.getItem() == ArtefactsInit.TORMENT_QUIVER.asItem()){
            float effectTimer = ((PlayerEntity) shooter).getItemCooldownManager().getCooldownProgress(offhand.getItem(), 0);
            if (effectTimer > 0){
                persistentProjectileEntity.setPunch(1);
            }
        }
    }

    @Inject(method = "onBlockHit", at = @At("HEAD"), cancellable = true)
    public void onTormentingArrowBlockImpact(BlockHitResult blockHitResult, CallbackInfo ci){
        PersistentProjectileEntity persistentProjectileEntity = (PersistentProjectileEntity) (Object) this;
        LivingEntity shooter = (LivingEntity) persistentProjectileEntity.getOwner();

        ItemStack offhand = null;
        if (shooter != null){
            offhand = shooter.getOffHandStack();
        }
        if (shooter instanceof PlayerEntity && offhand.getItem() == ArtefactsInit.TORMENT_QUIVER.asItem()){
            float effectTimer =
                    ((PlayerEntity)shooter).getItemCooldownManager().getCooldownProgress(offhand.getItem(), 0);
            if (effectTimer > 0){
                if (ci.isCancellable()){
                    ci.cancel();
                }
            }
        }
    }
}
