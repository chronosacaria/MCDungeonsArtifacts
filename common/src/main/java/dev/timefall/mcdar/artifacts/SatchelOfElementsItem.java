package dev.timefall.mcdar.artifacts;

import dev.timefall.mcdar.api.CleanlinessHelper;
import dev.timefall.mcdar.artifacts.artifact_types.ArtifactStatusInflictingItem;
import dev.timefall.mcdar.config.McdarArtifactsStatsConfig;
import dev.timefall.mcdar.effect.EnchantmentEffects;
import dev.timefall.mcdar.registry.StatusEffectRegistry;
import dev.timefall.mcdx.api.AOEHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

import java.util.List;

public class SatchelOfElementsItem extends ArtifactStatusInflictingItem {
    public SatchelOfElementsItem() {
        super(McdarArtifactsStatsConfig.CONFIG.mcdar$getStatusInflictingArtifactStats().SATCHEL_OF_ELEMENTS_STATS);
    }

    @Override
    public ActionResult useOnBlock (ItemUsageContext context) {
        if (context.getPlayer() != null && context.getPlayer().getWorld().getServer() != null) {
            ServerPlayerEntity user = context.getPlayer().getWorld().getServer().getPlayerManager().getPlayer(context.getPlayer().getUuid());
            if (user != null) {
                Hand hand = user.getActiveHand();
                ItemStack itemStack = user.getStackInHand(hand);
                int experienceDrain = McdarArtifactsStatsConfig.CONFIG.mcdar$getStatusInflictingArtifactStats().SATCHEL_OF_ELEMENTS_STATS.mcdar$getExperienceDrain();
                float damage = McdarArtifactsStatsConfig.CONFIG.mcdar$getStatusInflictingArtifactStats().SATCHEL_OF_ELEMENTS_STATS.mcdar$getDamageOrHealingFactor();
                float range = McdarArtifactsStatsConfig.CONFIG.mcdar$getStatusInflictingArtifactStats().SATCHEL_OF_ELEMENTS_STATS.mcdar$getRange();
                int duration = McdarArtifactsStatsConfig.CONFIG.mcdar$getStatusInflictingArtifactStats().SATCHEL_OF_ELEMENTS_STATS.mcdar$getDuration();
                int amplifier = McdarArtifactsStatsConfig.CONFIG.mcdar$getStatusInflictingArtifactStats().SATCHEL_OF_ELEMENTS_STATS.mcdar$getAmplifier();
                int amplifier2 = McdarArtifactsStatsConfig.CONFIG.mcdar$getStatusInflictingArtifactStats().SHOCK_POWDER_STATS.mcdar$getAmplifier2();
                int amplifier3 = McdarArtifactsStatsConfig.CONFIG.mcdar$getStatusInflictingArtifactStats().SHOCK_POWDER_STATS.mcdar$getAmplifier3();
                int maxCooldownEnchantmentTime = McdarArtifactsStatsConfig.CONFIG.mcdar$getStatusInflictingArtifactStats().SATCHEL_OF_ELEMENTS_STATS.mcdar$getMaxCooldownEnchantmentTime();
                int modifiedCooldownEnchantmentTime = EnchantmentEffects.cooldownEffect(maxCooldownEnchantmentTime, user, user.getWorld());

                if (user.totalExperience >= experienceDrain || user.isCreative()) {
                    satchelOfElementsEffects(user, damage, range, duration, amplifier, amplifier2, amplifier3);

                    if (!user.isCreative()) {
                        user.addExperience(-experienceDrain);
                        EquipmentSlot equipmentSlot = hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
                        itemStack.damage(1, user, equipmentSlot);
                    }
                    EnchantmentEffects.mcdar$cooldownHelper(
                            user,
                            this,
                            modifiedCooldownEnchantmentTime
                    );
                    return ActionResult.CONSUME;
                }
            }
        }
        return ActionResult.PASS;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext tooltipContext, List<Text> tooltip, TooltipType type){
        CleanlinessHelper.createLoreTTips(stack, tooltip);
    }

    private static void satchelOfElementsEffects(PlayerEntity user, float damage, float range, int duration, int amplifier, int amplifier2, int amplifier3) {
        int effectInt = (CleanlinessHelper.RANDOM.nextInt(3));

        if (effectInt == 0){ // BURNING
            for (LivingEntity nearbyEntity : AOEHelper.getEntitiesWithExclusions(user, range, McdarArtifactsStatsConfig.CONFIG.satchelExclusions)){
                nearbyEntity.setOnFireFor(3);
            }
        }
        if (effectInt == 1) { // FROZEN
            AOEHelper.afflictNearbyEntities(
                    user,
                    range,
                    new StatusEffectInstance(StatusEffectRegistry.STUNNED.getEntry(), duration, amplifier),
                    new StatusEffectInstance(StatusEffects.NAUSEA, duration, amplifier2),
                    new StatusEffectInstance(StatusEffects.SLOWNESS, duration, amplifier3)
            );
        }
        if (effectInt == 2) { // LIGHTNING STRIKE
            for (LivingEntity nearbyEntity : AOEHelper.getEntitiesWithExclusions(user, range, McdarArtifactsStatsConfig.CONFIG.satchelExclusions)){
                AOEHelper.electrocute(nearbyEntity, damage);
            }
        }
    }
}
