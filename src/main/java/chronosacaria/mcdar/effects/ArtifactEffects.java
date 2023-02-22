package chronosacaria.mcdar.effects;

import chronosacaria.mcdar.api.AOECloudHelper;
import chronosacaria.mcdar.api.AOEHelper;
import chronosacaria.mcdar.api.AbilityHelper;
import chronosacaria.mcdar.api.CleanlinessHelper;
import chronosacaria.mcdar.enums.DamagingArtifactID;
import chronosacaria.mcdar.init.ArtifactsInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;

import java.util.Random;

public class ArtifactEffects {

    public static void activatePowerShaker(PlayerEntity player, LivingEntity target) {
        // Temporary way to stop crash with Industrial Revolution Slaughter Block
        if (player.getEntityName().equals("slaughter")) {
            return;
        }

        ItemStack offhand = player.getOffHandStack();
        if (target != null && offhand.getItem() == ArtifactsInit.damagingArtifact.get(DamagingArtifactID.POWERSHAKER).asItem()) {
            if (CleanlinessHelper.isCoolingDown(player, offhand.getItem()) && CleanlinessHelper.percentToOccur(20)) {
                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_GENERIC_EXPLODE, 0.5F, 1.0F);
                AOECloudHelper.spawnExplosionCloud(player, target, 3.0F);
                AOEHelper.affectNearbyEntities(player, 3.0f,
                        (nearbyEntity) -> AbilityHelper.isAoeTarget(nearbyEntity, player, target),
                        livingEntity -> livingEntity.damage(DamageSource.explosion(player), target.getMaxHealth() * 0.2F)
                );
            }
        }
    }

    public static void causeBlastFungusExplosions(LivingEntity user, float distance, float damageAmount) {
        AOEHelper.affectNearbyEntities(user, distance,
                (nearbyEntity) -> AbilityHelper.isAoeTarget(nearbyEntity, user, nearbyEntity),
                livingEntity -> {
                    if (!(livingEntity instanceof PlayerEntity playerEntity && playerEntity.getAbilities().creativeMode)) {
                        AOECloudHelper.spawnExplosionCloud(user, livingEntity, 3);
                        livingEntity.damage(DamageSource.explosion(user), damageAmount);
                    }
                }
        );
    }

    public static void enchantersTomeEffects(PlayerEntity user) {
        for (LivingEntity nearbyEntity : AOEHelper.getEntitiesByPredicate(user, 5,
                (nearbyEntity) -> AbilityHelper.isPetOf(nearbyEntity, user))){
            StatusEffect[] statuses = {StatusEffects.HASTE, StatusEffects.STRENGTH, StatusEffects.SPEED};
            StatusEffectInstance statusEffectInstance =
                    new StatusEffectInstance(statuses[new Random().nextInt(statuses.length)], 100, 2);
            nearbyEntity.addStatusEffect(statusEffectInstance);
        }
    }

    public static void updraftNearbyEnemies(PlayerEntity user) {
        for (LivingEntity nearbyEntity : AOEHelper.getEntitiesByPredicate(user, 5,
                (nearbyEntity) -> nearbyEntity != user && !AbilityHelper.isPetOf(nearbyEntity, user) && nearbyEntity.isAlive())){
            nearbyEntity.setVelocity(0.0D, 1.25D, 0.0D);
        }
    }
}
