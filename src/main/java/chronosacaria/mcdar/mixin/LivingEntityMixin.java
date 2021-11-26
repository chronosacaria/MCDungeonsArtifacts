package chronosacaria.mcdar.mixin;

import chronosacaria.mcdar.api.AOECloudHelper;
import chronosacaria.mcdar.api.AOEHelper;
import chronosacaria.mcdar.api.SummoningHelper;
import chronosacaria.mcdar.api.interfaces.Summonable;
import chronosacaria.mcdar.config.McdarConfig;
import chronosacaria.mcdar.enchants.EnchantID;
import chronosacaria.mcdar.enums.DefenciveArtefactID;
import chronosacaria.mcdar.enums.QuiverArtefactID;
import chronosacaria.mcdar.init.ArtefactsInit;
import chronosacaria.mcdar.init.EnchantsRegistry;
import chronosacaria.mcdar.init.StatusEffectInit;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtil;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.UUID;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow
    public abstract void setHealth(float health);

    @Shadow
    public abstract boolean clearStatusEffects();

    @Shadow
    public abstract boolean addStatusEffect(StatusEffectInstance effect);

    @Shadow public abstract ItemStack getMainHandStack();

    @Inject(method = "tryUseTotem", at = @At("HEAD"), cancellable = true)
    public void onSoulProtectionDeath(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;

        if (livingEntity.hasStatusEffect(StatusEffectInit.SOUL_PROTECTION)) {
            this.setHealth(1.0F);
            this.clearStatusEffects();
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 900, 1));
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));

            cir.setReturnValue(true);
        }
    }

    @Inject(method = "swingHand(Lnet/minecraft/util/Hand;)V", at = @At("HEAD"), cancellable = true)
    public void onAttackWhilstStunnedNoTarget(Hand hand, CallbackInfo ci) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;

        if (livingEntity.hasStatusEffect(StatusEffectInit.STUNNED)) {
            ci.cancel();
        }
    }

    @Inject(method = "onDeath", at = @At("HEAD"), cancellable = true)
    public void onPowershakerExplodingKill(DamageSource source, CallbackInfo ci) {
        if (!(source.getAttacker() instanceof PlayerEntity)) return;

        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        ItemStack offhand = user.getOffHandStack();

        if (McdarConfig.config.enableDefenciveArtefact.get(DefenciveArtefactID.POWERSHAKER)){

            // Temporary way to stop crash with Industrial Revolution Slaughter Block
            if (user.getEntityName().equals("slaughter")) {
                return;
            }

            if (user instanceof PlayerEntity && target instanceof LivingEntity && offhand.getItem() == ArtefactsInit.defenciveArtefact.get(DefenciveArtefactID.POWERSHAKER).asItem()) {
                if (((PlayerEntity) user).getItemCooldownManager().getCooldownProgress(offhand.getItem(), 0) > 0 && user.getRandom().nextFloat() <= 0.2F) {
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
                    AOEHelper.causeExplosion(user, target, target.getMaxHealth() * 0.2F, 3.0F);
                }
            }
        }
    }

    @Inject(method = "applyDamage", at = @At("HEAD"), cancellable = true)
    public void onBeastBossDamage(DamageSource source, float amount, CallbackInfo ci){
        LivingEntity target = (LivingEntity) (Object) this;
        Entity trueSource = source.getSource();

        if (trueSource == null) return;

        if (trueSource.world instanceof ServerWorld && SummoningHelper.isEntitySummonable(trueSource)){
            ServerWorld serverWorld = (ServerWorld) trueSource.world;
            LivingEntity summoner = (((Summonable) trueSource).getSummoner());
            UUID summonerUUID = summoner.getUuid();
            if (summonerUUID != null){
                Entity beastOwner = serverWorld.getEntity(summonerUUID);
                if (beastOwner instanceof LivingEntity) {
                    LivingEntity beastOwnerAsLiving = ((LivingEntity) beastOwner);
                    int beastBossLevel =
                            EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(EnchantID.BEAST_BOSS),
                                    beastOwnerAsLiving);
                    if (beastBossLevel > 0) {
                        float beastBossFactor = 0.1F + (0.1F * beastBossLevel);
                        float newDamage = amount * beastBossFactor;
                        float h = target.getHealth();
                        target.setHealth(h - newDamage);
                    }
                }
            }
        }
    }

    @Inject(method = "consumeItem", at = @At("HEAD"), cancellable = true)
    public void onBeastBurstPotionConsumption(CallbackInfo ci){
        LivingEntity livingEntity = (LivingEntity) (Object) this;

        if (!(livingEntity instanceof PlayerEntity)) return;

        if ( livingEntity.isAlive() && livingEntity.world instanceof ServerWorld){
            List<StatusEffectInstance> potionEffects = PotionUtil.getPotionEffects(getMainHandStack());
            if (potionEffects.isEmpty()) return;
            if (potionEffects.get(0).getEffectType() == StatusEffects.INSTANT_HEALTH){
                int beastBurstLevel =
                        EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(EnchantID.BEAST_BURST),
                                livingEntity);
                if (beastBurstLevel > 0){
                    for (Entity summonedMob : AOEHelper.getSummonedMobs(livingEntity, 10)){
                        if (summonedMob == null) return;
                        if (summonedMob instanceof LivingEntity){
                            LivingEntity summonedMobAsLiving = (LivingEntity) summonedMob;
                            summonedMobAsLiving.world.playSound(
                                    null,
                                    summonedMobAsLiving.getX(),
                                    summonedMobAsLiving.getY(),
                                    summonedMobAsLiving.getZ(),
                                    SoundEvents.ENTITY_GENERIC_EXPLODE,
                                    SoundCategory.PLAYERS,
                                    0.5F,
                                    1.0F);
                            AOECloudHelper.spawnExplosionCloud(summonedMobAsLiving, summonedMobAsLiving, 3.0F);
                            AOEHelper.causeExplosion(summonedMobAsLiving, summonedMobAsLiving, 15 * beastBurstLevel,
                                    3.0F);
                        }
                    }
                }
            }
        }
    }

    @Inject(method = "consumeItem", at = @At("HEAD"), cancellable = true)
    public void onBeastSurgePotionConsumption(CallbackInfo ci){
        LivingEntity livingEntity = (LivingEntity) (Object) this;

        if (!(livingEntity instanceof PlayerEntity)) return;

        if ( livingEntity.isAlive() && livingEntity.world instanceof ServerWorld){
            List<StatusEffectInstance> potionEffects = PotionUtil.getPotionEffects(getMainHandStack());
            if (potionEffects.isEmpty()) return;
            if (potionEffects.get(0).getEffectType() == StatusEffects.INSTANT_HEALTH){
                int beastSurgeLevel =
                        EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(EnchantID.BEAST_SURGE),
                                livingEntity);
                if (beastSurgeLevel > 0){
                    for (Entity summonedMob : AOEHelper.getSummonedMobs(livingEntity, 10)){
                        if (summonedMob == null) return;
                        if (summonedMob instanceof LivingEntity){
                            LivingEntity summonedMobAsLiving = (LivingEntity) summonedMob;
                            StatusEffectInstance beastSurgeSpeed = new StatusEffectInstance(StatusEffects.SPEED,
                                    10 * 20, (beastSurgeLevel * 3) - 1);
                            StatusEffectInstance beastSurgeAttack = new StatusEffectInstance(StatusEffects.STRENGTH,
                                    10 * 20, (beastSurgeLevel * 3) - 1);
                            summonedMobAsLiving.addStatusEffect(beastSurgeSpeed);
                            summonedMobAsLiving.addStatusEffect(beastSurgeAttack);
                        }
                    }
                }
            }
        }
    }
}
