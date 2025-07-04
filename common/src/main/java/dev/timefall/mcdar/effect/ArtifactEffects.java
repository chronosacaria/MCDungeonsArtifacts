package dev.timefall.mcdar.effect;

import dev.timefall.mcdar.api.AOECloudHelper;
import dev.timefall.mcdar.api.AOEHelper;
import dev.timefall.mcdar.api.CleanlinessHelper;
import dev.timefall.mcdar.config.McdarArtifactsStatsConfig;
import dev.timefall.mcdar.registry.ArtifactsRegistry;
import dev.timefall.mcdx.api.AbilityHelper;
import dev.timefall.mcdx.configs.AoeExclusionType;
import dev.timefall.mcdx.configs.McdxCoreConfig;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;

import java.util.List;
import java.util.function.Consumer;

public class ArtifactEffects {

    public static void mcdar$activatePowerShaker(PlayerEntity player, LivingEntity target) {
        // Temporary way to stop crash with Industrial Revolution Slaughter Block
        if (player.getName().toString().equals("slaughter")) {
            return;
        }

        ItemStack offhand = player.getOffHandStack();
        if (target != null && offhand.getItem() == ArtifactsRegistry.POWERSHAKER.get()) {
            if (CleanlinessHelper.isCoolingDown(player, offhand.getItem()) && CleanlinessHelper.percentToOccur(20)) {
                CleanlinessHelper.playCenteredSound(target, SoundEvents.ENTITY_GENERIC_EXPLODE.value(), 0.5F, 1.0F);
                AOECloudHelper.spawnExplosionCloud(player, target, McdarArtifactsStatsConfig.CONFIG.mcdar$getDamagingArtifactStats().POWERSHAKER_STATS.mcdar$getRange());
                List<LivingEntity> targets = dev.timefall.mcdx.api.AOEHelper.getEntitiesWithExclusions(target, player, 3f, McdxCoreConfig.INSTANCE.aoeExclusions);
                for (LivingEntity target2 : targets) {
                    List<LivingEntity> targets2 = dev.timefall.mcdx.api.AOEHelper.getEntitiesWithExclusions(target2, player, McdarArtifactsStatsConfig.CONFIG.mcdar$getDamagingArtifactStats().POWERSHAKER_STATS.mcdar$getRange(), McdxCoreConfig.INSTANCE.aoeExclusions);
                    Consumer<LivingEntity> damage = AOEHelper.causeExplosion(player, (target3) -> target3.getMaxHealth() * 0.2f);
                    for(LivingEntity target4 : targets2) {
                        damage.accept(target4);
                    }
                }
            }
        }
    }

    public static void mcdar$causeBlastFungusExplosions(LivingEntity user, float distance, float damageAmount) {
        AbilityHelper.applyToNearestTarget(user, distance, McdxCoreConfig.INSTANCE.aoeExclusions, livingEntity -> {
            AOECloudHelper.spawnExplosionCloud(user, livingEntity, McdarArtifactsStatsConfig.CONFIG.mcdar$getDamagingArtifactStats().BLAST_FUNGUS_STATS.mcdar$getRange());
            AbilityHelper.applyToNearestNTargets(user, Integer.MAX_VALUE, distance, McdxCoreConfig.INSTANCE.aoeExclusions, AOEHelper.causeExplosion(user, damageAmount));
        });
    }

    private static StatusEffect[] statuses = {StatusEffects.HASTE.value(), StatusEffects.STRENGTH.value(), StatusEffects.SPEED.value()};

    public static void mcdar$enchantersTomeEffects(PlayerEntity user) {
        List<LivingEntity> pets = dev.timefall.mcdx.api.AOEHelper.getEntitiesByPredicate(
                user,
                McdarArtifactsStatsConfig.CONFIG.mcdar$getDefensiveArtifactStats().ENCHANTERS_TOME_STATS.mcdar$getRange(),
                (maybePet) -> AoeExclusionType.SELF_PET.isExcluded(user, maybePet, maybePet)
        );
        for (LivingEntity nearbyEntity : pets){

            StatusEffectInstance statusEffectInstance =
                    new StatusEffectInstance(RegistryEntry.of(
                            statuses[dev.timefall.mcdx.api.CleanlinessHelper.random.nextInt(statuses.length)]),
                            McdarArtifactsStatsConfig.CONFIG.mcdar$getDefensiveArtifactStats().ENCHANTERS_TOME_STATS.mcdar$getDuration(),
                            McdarArtifactsStatsConfig.CONFIG.mcdar$getDefensiveArtifactStats().ENCHANTERS_TOME_STATS.mcdar$getAmplifier()
                    );
            nearbyEntity.addStatusEffect(statusEffectInstance);
        }
    }

    public static void mcdar$updraftNearbyEnemies(PlayerEntity user) {
        AbilityHelper.applyToTargets(
                user,
                5,
                McdxCoreConfig.INSTANCE.allyExclusions,
                //TODO: Enchance the WEEEEE
                nearbyEntity -> nearbyEntity.setVelocity(0.0D, 1.25D, 0.0D)
        );
    }
}
