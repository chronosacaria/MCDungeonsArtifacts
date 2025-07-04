package dev.timefall.mcdar.effect;

import dev.timefall.mcdar.api.AOECloudHelper;
import dev.timefall.mcdar.api.AOEHelper;
import dev.timefall.mcdar.api.CleanlinessHelper;
import dev.timefall.mcdar.registry.EnchantmentRegistry;
import dev.timefall.mcdx.configs.AoeExclusionType;
import dev.timefall.mcdx.configs.McdxCoreConfig;
import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Tameable;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public class EnchantmentEffects {

    public static int cooldownEffect(int baseCooldown, PlayerEntity playerEntity, World world) {
        Optional<? extends RegistryEntry<Enchantment>> entry = world.getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(EnchantmentRegistry.COOLDOWN);
        int cooldownLevel = entry.map(e -> EnchantmentHelper.getEquipmentLevel(e, playerEntity)).orElse(0);
        return cooldownLevel == 0 ? baseCooldown :
                        (int) (baseCooldown - (baseCooldown * mcdar$cooldownCalcHelper(cooldownLevel)));
    }

    public static int cooldownEffectForQuivers(int baseCooldown, PlayerEntity playerEntity, World world) {
        Optional<? extends RegistryEntry<Enchantment>> entry = world.getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(EnchantmentRegistry.COOLDOWN);
        int cooldownLevel = entry.map(e -> EnchantmentHelper.getEquipmentLevel(e, playerEntity)).orElse(0);
        return cooldownLevel == 0 ? baseCooldown :
                (int) (baseCooldown - (baseCooldown / mcdar$cooldownCalcHelper(cooldownLevel)));
    }


    public static float beastBossDamage(Tameable summonedEntity, ServerWorld serverWorld) {
        if (summonedEntity.getOwner() != null) {
            UUID summonerUUID = summonedEntity.getOwnerUuid();
            if (summonerUUID != null) {
                Entity beastOwner = serverWorld.getEntity(summonerUUID);
                if (beastOwner instanceof LivingEntity beastOwnerAsLiving) {
                    Optional<? extends RegistryEntry<Enchantment>> entry = serverWorld.getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(EnchantmentRegistry.BEAST_BOSS);
                    int beastBossLevel = entry.map(e -> EnchantmentHelper.getEquipmentLevel(e, beastOwnerAsLiving)).orElse(0);
                    if (beastBossLevel > 0) {
                        return 1.1F + (0.1F * beastBossLevel);
                    }
                }
            }
        }
        return 1f;
    }

    public static void activateBeastBurst(PlayerEntity player, ServerWorld serverWorld) {
        float explosionRadius = 3.0f;
        List<StatusEffectInstance> potionEffects = PotionContentsComponent.DEFAULT.customEffects();
        if (potionEffects.isEmpty()) return;
        if (CleanlinessHelper.mcdar$isValidForBeastEffects(potionEffects)) {
            Optional<? extends RegistryEntry<Enchantment>> entry = serverWorld.getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(EnchantmentRegistry.BEAST_BURST);
            int beastBurstLevel = entry.map(e -> EnchantmentHelper.getEquipmentLevel(e, player)).orElse(0);

            if (beastBurstLevel > 0){

                List<LivingEntity> pets = dev.timefall.mcdx.api.AOEHelper.getEntitiesByPredicate(player, 10f, maybePet -> AoeExclusionType.SELF_PET.isExcluded(player, maybePet, maybePet));
                for (LivingEntity pet: pets) {
                    CleanlinessHelper.playCenteredSound(pet, SoundEvents.ENTITY_GENERIC_EXPLODE.value(), 0.5F, 1.0F);
                    AOECloudHelper.spawnExplosionCloud(pet, pet, explosionRadius);
                    List<LivingEntity> targets2 = dev.timefall.mcdx.api.AOEHelper.getEntitiesWithExclusions(pet, player, 3f, McdxCoreConfig.INSTANCE.obscuredExclusions);
                    Consumer<LivingEntity> explosion = AOEHelper.causeExplosion(player, 3 * beastBurstLevel);
                    for (LivingEntity target2 : targets2) {
                        explosion.accept(target2);
                    }
                }
            }
        }
    }

    public static void activateBeastSurge(PlayerEntity player, ServerWorld serverWorld) {
        List<StatusEffectInstance> potionEffects = PotionContentsComponent.DEFAULT.customEffects();
        if (CleanlinessHelper.mcdar$isValidForBeastEffects(potionEffects)) {
            Optional<? extends RegistryEntry<Enchantment>> entry = serverWorld.getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(EnchantmentRegistry.BEAST_SURGE);
            int beastSurgeLevel = entry.map(e -> EnchantmentHelper.getEquipmentLevel(e, player)).orElse(0);

            if (beastSurgeLevel > 0) {
                List<LivingEntity> pets = dev.timefall.mcdx.api.AOEHelper.getEntitiesByPredicate(player, 10f, maybePet -> AoeExclusionType.SELF_PET.isExcluded(player, maybePet, maybePet));
                for (LivingEntity pet: pets) {
                    pet.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 10 * 20, (beastSurgeLevel * 3) - 1));
                    pet.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 10 * 20, (beastSurgeLevel * 3) - 1));
}
            }
        }
    }

    public static int getLevel(RegistryKey<Enchantment> key, LivingEntity livingEntity, World world) {
        Optional<? extends RegistryEntry<Enchantment>> entry = world.getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(key);
        return entry.map(e -> EnchantmentHelper.getEquipmentLevel(e, livingEntity)).orElse(0);
    }

    public static void mcdar$cooldownHelper(PlayerEntity player, Item item, int maxCooldown) {
        int cooldownLevel = EnchantmentEffects.getLevel(EnchantmentRegistry.COOLDOWN, player, player.getWorld());
        if (!(ConfigApiJava.platform().isDev() && player.isCreative())) {
            player.getItemCooldownManager().set(item,
                    cooldownLevel == 0 ? maxCooldown :
                            (int) (maxCooldown - (maxCooldown * EnchantmentEffects.mcdar$cooldownCalcHelper(cooldownLevel))));
        }
    }

    public static float mcdar$cooldownCalcHelper(int level) {
        float modifier = 0.18f;
        for (int i = 0 ; i < level - 1 ; i++) {
            float j = 0.11f - (0.02f * i);
            // Level 1: 18
            // 2 onward : 29, 38, 45, 51, 55, 58, 61, 64, 67, 71, 73
            modifier += Math.max(j == 5 || j == 3 ? j + 1 : j, 0.03f);
        }
        return modifier;
    }
}
