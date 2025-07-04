package dev.timefall.mcdar.artifacts;

import dev.timefall.mcdar.api.AOEHelper;
import dev.timefall.mcdar.api.CleanlinessHelper;
import dev.timefall.mcdar.artifacts.artifact_types.ArtifactStatusInflictingItem;
import dev.timefall.mcdar.config.McdarArtifactsStatsConfig;
import dev.timefall.mcdar.effect.EnchantmentEffects;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class CorruptedSeedsItem extends ArtifactStatusInflictingItem {
    public CorruptedSeedsItem() {
        super(McdarArtifactsStatsConfig.CONFIG.mcdar$getStatusInflictingArtifactStats().CORRUPTED_SEEDS_STATS);
    }

    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);
        float range = McdarArtifactsStatsConfig.CONFIG.mcdar$getStatusInflictingArtifactStats().CORRUPTED_SEEDS_STATS.mcdar$getRange();
        int duration = McdarArtifactsStatsConfig.CONFIG.mcdar$getStatusInflictingArtifactStats().CORRUPTED_SEEDS_STATS.mcdar$getDuration();
        int amplifier = McdarArtifactsStatsConfig.CONFIG.mcdar$getStatusInflictingArtifactStats().CORRUPTED_SEEDS_STATS.mcdar$getAmplifier();
        int amplifier2 = McdarArtifactsStatsConfig.CONFIG.mcdar$getStatusInflictingArtifactStats().CORRUPTED_SEEDS_STATS.mcdar$getAmplifier2();
        int maxCooldownEnchantmentTime = McdarArtifactsStatsConfig.CONFIG.mcdar$getStatusInflictingArtifactStats().CORRUPTED_SEEDS_STATS.mcdar$getMaxCooldownEnchantmentTime();
        int modifiedCooldownEnchantmentTime = EnchantmentEffects.cooldownEffect(maxCooldownEnchantmentTime, user, world);

        dev.timefall.mcdx.api.AOEHelper.afflictNearbyEntities(
                user,
                range,
                new StatusEffectInstance(StatusEffects.SLOWNESS, duration, amplifier),
                new StatusEffectInstance(StatusEffects.POISON, duration, amplifier2)
        );

        return CleanlinessHelper.mcdar$useAndDamageArtifact(user, hand, itemStack, modifiedCooldownEnchantmentTime);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext tooltipContext, List<Text> tooltip, TooltipType type) {
        CleanlinessHelper.createLoreTTips(stack, tooltip);
    }
}
