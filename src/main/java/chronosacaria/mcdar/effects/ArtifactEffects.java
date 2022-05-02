package chronosacaria.mcdar.effects;

import chronosacaria.mcdar.api.AOECloudHelper;
import chronosacaria.mcdar.api.AOEHelper;
import chronosacaria.mcdar.api.interfaces.Summonable;
import chronosacaria.mcdar.enchants.EnchantID;
import chronosacaria.mcdar.enums.DefenciveArtefactID;
import chronosacaria.mcdar.init.ArtefactsInit;
import chronosacaria.mcdar.init.EnchantsRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtil;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

import java.util.List;
import java.util.UUID;

public class ArtifactEffects {

    public static void activatePowerShaker(PlayerEntity player, LivingEntity target) {
        // Temporary way to stop crash with Industrial Revolution Slaughter Block
        if (player.getEntityName().equals("slaughter")) {
            return;
        }

        ItemStack offhand = player.getOffHandStack();
        if (target != null && offhand.getItem() == ArtefactsInit.defenciveArtefact.get(DefenciveArtefactID.POWERSHAKER).asItem()) {
            if (player.getItemCooldownManager().getCooldownProgress(offhand.getItem(), 0) > 0 && player.getRandom().nextFloat() <= 0.2F) {
                target.world.playSound(
                        null,
                        target.getX(),
                        target.getY(),
                        target.getZ(),
                        SoundEvents.ENTITY_GENERIC_EXPLODE,
                        SoundCategory.PLAYERS,
                        0.5F,
                        1.0F);
                AOECloudHelper.spawnExplosionCloud(player, target, 3.0F);
                AOEHelper.causeExplosion(player, target, target.getMaxHealth() * 0.2F, 3.0F);
            }
        }
    }

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
        List<StatusEffectInstance> potionEffects = PotionUtil.getPotionEffects(player.getMainHandStack());
        if (potionEffects.isEmpty()) return;
        if (potionEffects.get(0).getEffectType() == StatusEffects.INSTANT_HEALTH){
            int beastBurstLevel =
                    EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(EnchantID.BEAST_BURST),
                            player);
            if (beastBurstLevel > 0){
                for (Entity summonedMob : AOEHelper.getSummonedMobs(player, 10)){
                    if (summonedMob == null) return;
                    if (summonedMob instanceof LivingEntity summonedMobAsLiving){
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


    public static void activateBeastSurge(PlayerEntity player) {
        List<StatusEffectInstance> potionEffects = PotionUtil.getPotionEffects(player.getMainHandStack());
        if (potionEffects.isEmpty()) return;
        if (potionEffects.get(0).getEffectType() == StatusEffects.INSTANT_HEALTH) {
            int beastSurgeLevel =
                    EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(EnchantID.BEAST_SURGE),
                            player);
            if (beastSurgeLevel > 0) {
                for (Entity summonedMob : AOEHelper.getSummonedMobs(player, 10)) {
                    if (summonedMob == null) return;
                    if (summonedMob instanceof LivingEntity summonedMobAsLiving) {
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
