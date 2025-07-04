package dev.timefall.mcdar.artifacts;

import dev.timefall.mcdar.api.CleanlinessHelper;
import dev.timefall.mcdar.artifacts.artifact_types.ArtifactStatusInflictingItem;
import dev.timefall.mcdar.config.McdarArtifactsStatsConfig;
import dev.timefall.mcdar.effect.EnchantmentEffects;
import dev.timefall.mcdar.registry.StatusEffectRegistry;
import dev.timefall.mcdx.api.AOEHelper;
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


public class ShockPowderItem extends ArtifactStatusInflictingItem {
    public ShockPowderItem() {
        super(McdarArtifactsStatsConfig.CONFIG.mcdar$getStatusInflictingArtifactStats().SHOCK_POWDER_STATS);
    }

    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);
        float range = McdarArtifactsStatsConfig.CONFIG.mcdar$getStatusInflictingArtifactStats().SHOCK_POWDER_STATS.mcdar$getRange();
        int duration = McdarArtifactsStatsConfig.CONFIG.mcdar$getStatusInflictingArtifactStats().SHOCK_POWDER_STATS.mcdar$getDuration();
        int amplifier = McdarArtifactsStatsConfig.CONFIG.mcdar$getStatusInflictingArtifactStats().SHOCK_POWDER_STATS.mcdar$getAmplifier();
        int amplifier2 = McdarArtifactsStatsConfig.CONFIG.mcdar$getStatusInflictingArtifactStats().SHOCK_POWDER_STATS.mcdar$getAmplifier2();
        int amplifier3 = McdarArtifactsStatsConfig.CONFIG.mcdar$getStatusInflictingArtifactStats().SHOCK_POWDER_STATS.mcdar$getAmplifier3();
        int maxCooldownEnchantmentTime = McdarArtifactsStatsConfig.CONFIG.mcdar$getStatusInflictingArtifactStats().SHOCK_POWDER_STATS.mcdar$getMaxCooldownEnchantmentTime();
        int modifiedCooldownEnchantmentTime = EnchantmentEffects.cooldownEffect(maxCooldownEnchantmentTime, user, world);

        AOEHelper.afflictNearbyEntities(
                user,
                range,
                new StatusEffectInstance(StatusEffectRegistry.STUNNED.getEntry(), duration, amplifier),
                new StatusEffectInstance(StatusEffects.NAUSEA, duration, amplifier2),
                new StatusEffectInstance(StatusEffects.SLOWNESS, duration, amplifier3)
        );

        if (!user.isCreative()){
            EquipmentSlot equipmentSlot = hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
            itemStack.damage(1, user, equipmentSlot);
        }

        EnchantmentEffects.mcdar$cooldownHelper(
                user,
                this,
                modifiedCooldownEnchantmentTime
        );
        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext tooltipContext, List<Text> tooltip, TooltipType type){
        CleanlinessHelper.createLoreTTips(stack, tooltip);
    }
}
