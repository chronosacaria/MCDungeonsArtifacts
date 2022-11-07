package chronosacaria.mcdar.effects;

import chronosacaria.mcdar.api.AOECloudHelper;
import chronosacaria.mcdar.api.AOEHelper;
import chronosacaria.mcdar.api.AbilityHelper;
import chronosacaria.mcdar.api.CleanlinessHelper;
import chronosacaria.mcdar.enums.DamagingArtifactID;
import chronosacaria.mcdar.init.ArtifactsInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
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
            if (player.getItemCooldownManager().getCooldownProgress(offhand.getItem(), 0) > 0 && CleanlinessHelper.percentToOccur(20)) {
                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_GENERIC_EXPLODE, 0.5F, 1.0F);
                AOECloudHelper.spawnExplosionCloud(player, target, 3.0F);
                for (LivingEntity nearbyEntity : AOEHelper.getEntitiesByPredicate(target, 3.0F,
                        (nearbyEntity) -> AbilityHelper.isAoeTarget(nearbyEntity, player, target))) {
                    nearbyEntity.damage(DamageSource.explosion(player), target.getMaxHealth() * 0.2F);
                }
            }
        }
    }

    public static void causeBlastFungusExplosions(LivingEntity user, float distance, float damageAmount) {

        for (LivingEntity nearbyEntity : AOEHelper.getEntitiesByPredicate(user, distance,
                (nearbyEntity) -> AbilityHelper.isAoeTarget(nearbyEntity, user, nearbyEntity))) {
            if (nearbyEntity instanceof PlayerEntity playerEntity && playerEntity.getAbilities().creativeMode) continue;

            AOECloudHelper.spawnExplosionCloud(user, nearbyEntity, 3);
            nearbyEntity.damage(DamageSource.explosion(user), damageAmount);
        }
    }

    public static void enchantersTomeEffects(PlayerEntity user) {
        for (LivingEntity nearbyEntity : AOEHelper.getEntitiesByPredicate(user, 5,
                (nearbyEntity) -> AbilityHelper.isPetOf(nearbyEntity, user))){
            //noinspection RedundantSuppression
            StatusEffectInstance statusEffectInstance = new StatusEffectInstance(
                    switch ((new Random()).nextInt(3)) {
                        case 0 -> StatusEffects.STRENGTH;
                        case 1 -> StatusEffects.HASTE;
                        //noinspection DuplicateBranchesInSwitch
                        case 2 -> StatusEffects.SPEED;
                        default -> StatusEffects.SPEED;
                    }, 100, 2);
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
