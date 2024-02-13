package chronosacaria.mcdar.api;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.enums.*;
import chronosacaria.mcdar.registries.EnchantsRegistry;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Locale;
import java.util.Random;

public class CleanlinessHelper {

    static final Random RANDOM = new Random();

    public static void playCenteredSound(LivingEntity center, SoundEvent soundEvent, float volume, float pitch) {
        playCenteredSound(center, soundEvent, SoundCategory.PLAYERS, volume, pitch);
    }

    public static void playCenteredSound(LivingEntity center, SoundEvent soundEvent, SoundCategory soundCategory, float volume, float pitch) {
        center.getWorld().playSound(null,
                center.getX(), center.getY(), center.getZ(),
                soundEvent, soundCategory,
                volume, pitch);
    }

    public static boolean percentToOccur (int chance) {
        return RANDOM.nextInt(100) + 1 <= chance;
    }

    public static void createLoreTTips(ItemStack stack, List<Text> tooltip) {
        String str = stack.getItem().getTranslationKey().toLowerCase(Locale.ROOT).substring(11);
        String translationKey = String.format("tooltip_info_item.mcdar.%s_", str);
        int i = 1;
        while (I18n.hasTranslation(translationKey + i)) {
            tooltip.add(Text.translatable(translationKey + i).formatted(Formatting.ITALIC));
            i++;
        }
    }

    public static void mcdar$dropItem(LivingEntity le, Item item) {
        mcdar$dropItem(le, item, 1);
    }

    public static void mcdar$dropItem(LivingEntity le, ItemStack itemStack) {
        ItemEntity it = new ItemEntity(
                le.getWorld(), le.getX(), le.getY(), le.getZ(),
                itemStack);
        le.getWorld().spawnEntity(it);
    }

    public static void mcdar$dropItem(LivingEntity le, Item item, int amount) {
        mcdar$dropItem(le, new ItemStack(item, amount));
    }

    public static boolean isCoolingDown(PlayerEntity player, Item item) {
        return player.getItemCooldownManager().isCoolingDown(item);
    }

    public static TypedActionResult<ItemStack> mcdar$cleanUseWithOptionalStatus(
            PlayerEntity player,
            Hand hand,
            Item artifact,
            @Nullable StatusEffect statusEffect,
            @Nullable Integer statusEffectDuration,
            @Nullable Integer statusEffectAmplifier,
            @Nullable StatusEffect statusEffect1,
            @Nullable Integer statusEffectDuration1,
            @Nullable Integer statusEffectAmplifier1,
            @Nullable StatusEffect statusEffect2,
            @Nullable Integer statusEffectDuration2,
            @Nullable Integer statusEffectAmplifier2
    ) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (statusEffect != null && statusEffectDuration != null && statusEffectAmplifier != null) {
            StatusEffectInstance statusEffectInstance = new StatusEffectInstance(statusEffect, statusEffectDuration, statusEffectAmplifier);
            player.addStatusEffect(statusEffectInstance);
        }
        if (statusEffect1 != null && statusEffectDuration1 != null && statusEffectAmplifier1 != null) {
            StatusEffectInstance statusEffectInstance1 = new StatusEffectInstance(statusEffect1, statusEffectDuration1, statusEffectAmplifier1);
            player.addStatusEffect(statusEffectInstance1);
        }
        if (statusEffect2 != null && statusEffectDuration2 != null && statusEffectAmplifier2 != null) {
            StatusEffectInstance statusEffectInstance2 = new StatusEffectInstance(statusEffect2, statusEffectDuration2, statusEffectAmplifier2);
            player.addStatusEffect(statusEffectInstance2);
        }
        if (!player.isCreative())
            itemStack.damage(1, player, (entity) -> entity.sendToolBreakStatus(hand));
        McdarEnchantmentHelper.mcdar$cooldownHelper(player, artifact);
        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }

    public static ActionResult mcdar$cleanUseSummon(
            ItemUsageContext itemUsageContext,
            Item artifact,
            EntityType<?> summon
    ) {
        if (itemUsageContext.getWorld() instanceof ServerWorld serverWorld) {
            PlayerEntity itemUsageContextPlayer = itemUsageContext.getPlayer();
            if (itemUsageContextPlayer != null) {
                if (SummoningHelper.mcdar$summonSummonableEntity(
                        (LivingEntity) summon.create(serverWorld),
                        itemUsageContextPlayer,
                        itemUsageContext.getBlockPos())) {

                    if (!itemUsageContextPlayer.isCreative())
                        itemUsageContext.getStack().damage(1, itemUsageContextPlayer,
                                (entity) -> entity.sendToolBreakStatus(itemUsageContext.getHand()));

                    McdarEnchantmentHelper.mcdar$cooldownHelper(
                            itemUsageContextPlayer,
                            artifact);
                    return ActionResult.CONSUME;
                }
            }
        }
        return ActionResult.SUCCESS;
    }

    public static int mcdar$artifactIDToItemCooldownTime(Item artifactItem) {
        int cooldownLevel = EnchantmentHelper.getLevel(EnchantsRegistry.COOLDOWN, artifactItem.getDefaultStack());
        //if (artifactItem instanceof IArtifactItem) {
            for (AgilityArtifactID agilityArtifactID : AgilityArtifactID.values())
                if (artifactItem.asItem() == agilityArtifactID.mcdar$getItem()
                        && Mcdar.CONFIG.mcdarArtifactsStatsConfig.AGILITY_ARTIFACT_STATS.get(agilityArtifactID)
                            .mcdar$getIsEnabled())
                    return Mcdar.CONFIG.mcdarArtifactsStatsConfig.AGILITY_ARTIFACT_STATS.get(agilityArtifactID)
                            .mcdar$getMaxCooldownEnchantmentTime();
            for (DamagingArtifactID damagingArtifactID : DamagingArtifactID.values())
                if (artifactItem.asItem() == damagingArtifactID.mcdar$getItem()
                        && Mcdar.CONFIG.mcdarArtifactsStatsConfig.DAMAGING_ARTIFACT_STATS.get(damagingArtifactID)
                            .mcdar$getIsEnabled())
                    return Mcdar.CONFIG.mcdarArtifactsStatsConfig.DAMAGING_ARTIFACT_STATS.get(damagingArtifactID)
                            .mcdar$getMaxCooldownEnchantmentTime();
            for (DefensiveArtifactID defensiveArtifactID : DefensiveArtifactID.values())
                if (artifactItem.asItem() == defensiveArtifactID.mcdar$getItem()
                        && Mcdar.CONFIG.mcdarArtifactsStatsConfig.DEFENSIVE_ARTIFACT_STATS.get(defensiveArtifactID)
                            .mcdar$getIsEnabled() && artifactItem.asItem() != DefensiveArtifactID.SOUL_HEALER.mcdar$getItem())
                    return Mcdar.CONFIG.mcdarArtifactsStatsConfig.DEFENSIVE_ARTIFACT_STATS.get(defensiveArtifactID)
                            .mcdar$getMaxCooldownEnchantmentTime();
            for (QuiverArtifactID quiverArtifactID : QuiverArtifactID.values())
                if (artifactItem.asItem() == quiverArtifactID.mcdar$getItem()
                        && Mcdar.CONFIG.mcdarArtifactsStatsConfig.QUIVER_ARTIFACT_STATS.get(quiverArtifactID)
                            .mcdar$getIsEnabled())
                    return (cooldownLevel + 1) * Mcdar.CONFIG.mcdarArtifactsStatsConfig.QUIVER_ARTIFACT_STATS.get(quiverArtifactID)
                            .mcdar$getMaxCooldownEnchantmentTime();
            for (StatusInflictingArtifactID statusInflictingArtifactID : StatusInflictingArtifactID.values())
                if (artifactItem.asItem() == statusInflictingArtifactID.mcdar$getItem()
                        && Mcdar.CONFIG.mcdarArtifactsStatsConfig.STATUS_INFLICTING_ARTIFACT_STATS.get(statusInflictingArtifactID)
                            .mcdar$getIsEnabled())
                    return Mcdar.CONFIG.mcdarArtifactsStatsConfig.STATUS_INFLICTING_ARTIFACT_STATS.get(statusInflictingArtifactID)
                            .mcdar$getMaxCooldownEnchantmentTime();
            for (SummoningArtifactID summoningArtifactID : SummoningArtifactID.values())
                if (artifactItem.asItem() == summoningArtifactID.mcdar$getItem()
                        && Mcdar.CONFIG.mcdarArtifactsStatsConfig.SUMMONING_ARTIFACT_STATS.get(summoningArtifactID)
                            .mcdar$getIsEnabled())
                    return Mcdar.CONFIG.mcdarArtifactsStatsConfig.SUMMONING_ARTIFACT_STATS.get(summoningArtifactID)
                            .mcdar$getMaxCooldownEnchantmentTime();
        //}
        return 0;
    }
}
