package chronosacaria.mcdar.mixin;

import chronosacaria.mcdar.api.AOECloudHelper;
import chronosacaria.mcdar.api.AOEHelper;
import chronosacaria.mcdar.enums.DefenciveArtefactID;
import chronosacaria.mcdar.enums.QuiverArtefactID;
import chronosacaria.mcdar.init.ArtefactsInit;
import chronosacaria.mcdar.init.StatusEffectInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
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

    @Inject(method = "swingHand(Lnet/minecraft/util/Hand;)V", at = @At("HEAD"), cancellable = true)
    public void onAttackWhilstStunnedNoTarget(Hand hand, CallbackInfo ci){
        LivingEntity livingEntity = (LivingEntity) (Object) this;

        if (livingEntity.hasStatusEffect(StatusEffectInit.STUNNED)){
            ci.cancel();
        }
    }

    @Inject(method = "onDeath", at = @At("HEAD"), cancellable = true)
    public void onPowershakerExplodingKill(DamageSource source, CallbackInfo ci){
        if (!(source.getAttacker() instanceof PlayerEntity)) return;

        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        ItemStack offhand = user.getOffHandStack();

        if (user instanceof PlayerEntity && target instanceof LivingEntity && offhand.getItem() == ArtefactsInit.defenciveArtefact.get(DefenciveArtefactID.POWERSHAKER).asItem()){
            float explodingDamage = target.getMaxHealth() * 0.2F;
            float chance = user.getRandom().nextFloat();
            float effectTimer = ((PlayerEntity)user).getItemCooldownManager().getCooldownProgress(offhand.getItem(), 0);
            if (effectTimer > 0) {
                if (chance <= 0.2F) {
                    target.world.playSound(
                            null,
                            target.getX(),
                            target.getY(),
                            target.getZ(),
                            SoundEvents.ENTITY_GENERIC_EXPLODE,
                            SoundCategory.PLAYERS,
                            0.5F,
                            1.0F);
                    AOECloudHelper.spawnExplosionCloud(user, target, 3.0F);
                    AOEHelper.causeExplosion(user, target, explodingDamage, 3.0F);
                }
            }
        }
    }
}
