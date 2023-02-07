package chronosacaria.mcdar.effects;

import chronosacaria.mcdar.api.AOECloudHelper;
import chronosacaria.mcdar.api.AOEHelper;
import chronosacaria.mcdar.api.AbilityHelper;
import chronosacaria.mcdar.api.CleanlinessHelper;
import chronosacaria.mcdar.api.interfaces.Summonable;
import chronosacaria.mcdar.enchants.EnchantID;
import chronosacaria.mcdar.init.EnchantsRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.PotionUtil;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;

import java.util.List;
import java.util.UUID;

public class EnchantmentEffects {

    public static float beastBossDamage(Summonable summonedEntity, ServerWorld serverWorld) {
        if (summonedEntity.getSummoner() != null) {
            UUID summonerUUID = summonedEntity.getSummoner().getUuid();
            if (summonerUUID != null) {
                Entity beastOwner = serverWorld.getEntity(summonerUUID);
                if (beastOwner instanceof LivingEntity beastOwnerAsLiving) {
                    int beastBossLevel =
                            EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(EnchantID.BEAST_BOSS),
                                    beastOwnerAsLiving);
                    if (beastBossLevel > 0) {
                        return 1.1F + (0.1F * beastBossLevel);
                    }
                }
            }
        }
        return 1f;
    }

    public static void activateBeastBurst(PlayerEntity player) {
        List<StatusEffectInstance> potionEffects = PotionUtil.getPotionEffects(player.getActiveItem());
        if (potionEffects.isEmpty()) return;
        if (potionEffects.get(0).getEffectType() == StatusEffects.INSTANT_HEALTH){
            int beastBurstLevel =
                    EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(EnchantID.BEAST_BURST),
                            player);
            if (beastBurstLevel > 0){
                for (LivingEntity summonedMob : AOEHelper.getEntitiesByPredicate(player, 10,
                        (nearbyEntity) -> AbilityHelper.isPetOf(nearbyEntity, player))) {
                    if (summonedMob == null) continue;
                    CleanlinessHelper.playCenteredSound(summonedMob, SoundEvents.ENTITY_GENERIC_EXPLODE, 0.5F, 1.0F);
                    AOECloudHelper.spawnExplosionCloud(summonedMob, summonedMob, 3.0F);
                    for (LivingEntity nearbyEntity : AOEHelper.getEntitiesByPredicate(summonedMob, 3.0F,
                            (nearbyEntity) -> AbilityHelper.isAoeTarget(nearbyEntity, summonedMob, summonedMob))) {
                        nearbyEntity.damage(DamageSource.GENERIC.setExplosive(), 15 * beastBurstLevel);
                    }
                }
            }
        }
    }

    public static void activateBeastSurge(PlayerEntity player) {
        List<StatusEffectInstance> potionEffects = PotionUtil.getPotionEffects(player.getActiveItem());
        if (potionEffects.isEmpty()) return;
        if (potionEffects.get(0).getEffectType().equals(StatusEffects.INSTANT_HEALTH)) {
            int beastSurgeLevel =
                    EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(EnchantID.BEAST_SURGE),
                            player);
            if (beastSurgeLevel > 0) {
                AOEHelper.afflictNearbyEntities(LivingEntity.class, player, 10,
                        (nearbyEntity) -> AbilityHelper.isPetOf(nearbyEntity, player),
                        new StatusEffectInstance(StatusEffects.SPEED, 10 * 20, (beastSurgeLevel * 3) - 1),
                        new StatusEffectInstance(StatusEffects.STRENGTH, 10 * 20, (beastSurgeLevel * 3) - 1));
            }
        }
    }
}
