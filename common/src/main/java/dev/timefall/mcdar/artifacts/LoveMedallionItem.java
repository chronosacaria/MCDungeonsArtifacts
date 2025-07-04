package dev.timefall.mcdar.artifacts;

import dev.timefall.mcdar.api.CleanlinessHelper;
import dev.timefall.mcdar.artifacts.artifact_types.ArtifactStatusInflictingItem;
import dev.timefall.mcdar.config.McdarArtifactsStatsConfig;
import dev.timefall.mcdar.effect.EnchantmentEffects;
import dev.timefall.mcdar.registry.StatusEffectRegistry;
import dev.timefall.mcdx.api.AbilityHelper;
import dev.timefall.mcdx.configs.McdxCoreConfig;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.AmbientEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class LoveMedallionItem extends ArtifactStatusInflictingItem {
    public LoveMedallionItem() {
        super(McdarArtifactsStatsConfig.CONFIG.mcdar$getStatusInflictingArtifactStats().LOVE_MEDALLION_STATS);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);
        float range = McdarArtifactsStatsConfig.CONFIG.mcdar$getStatusInflictingArtifactStats().LOVE_MEDALLION_STATS.mcdar$getRange();
        int duration = McdarArtifactsStatsConfig.CONFIG.mcdar$getStatusInflictingArtifactStats().LOVE_MEDALLION_STATS.mcdar$getDuration();
        int amplifier = McdarArtifactsStatsConfig.CONFIG.mcdar$getStatusInflictingArtifactStats().LOVE_MEDALLION_STATS.mcdar$getAmplifier();
        int maxCooldownEnchantmentTime = McdarArtifactsStatsConfig.CONFIG.mcdar$getStatusInflictingArtifactStats().LOVE_MEDALLION_STATS.mcdar$getMaxCooldownEnchantmentTime();
        int modifiedCooldownEnchantmentTime = EnchantmentEffects.cooldownEffect(maxCooldownEnchantmentTime, user, world);

        AbilityHelper.applyToNearestNTargets(
                user,
                3,
                range,
                McdxCoreConfig.INSTANCE.aoeExclusions,
                nearbyEntity -> sendIntoWildRage(nearbyEntity, duration, amplifier)
        );

        EquipmentSlot equipmentSlot = hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
        itemStack.damage(1, user, equipmentSlot);

        EnchantmentEffects.mcdar$cooldownHelper(
                user,
                this,
                modifiedCooldownEnchantmentTime
        );

        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }

    public static void sendIntoWildRage(LivingEntity mobEntity, int duration, int amplifier) {
        if (!(mobEntity.getAttributes().hasAttribute(EntityAttributes.GENERIC_ATTACK_DAMAGE) || mobEntity instanceof WitherEntity || mobEntity instanceof EnderDragonEntity || mobEntity instanceof AmbientEntity))
            mobEntity.addStatusEffect(new StatusEffectInstance(StatusEffectRegistry.CHARMED.getEntry(), duration, amplifier));
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext tooltipContext, List<Text> tooltip, TooltipType type){
        CleanlinessHelper.createLoreTTips(stack, tooltip);
    }

}

/*
3 nearby mobs give off heart particles, become allies for 10 seconds, then die

// Abridged
3 mobs within 6 radius are afflicted with wild rage for 10 seconds as is in mcdw
 */