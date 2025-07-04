package dev.timefall.mcdar.artifacts;

import dev.timefall.mcdar.api.AOECloudHelper;
import dev.timefall.mcdar.api.AOEHelper;
import dev.timefall.mcdar.api.CleanlinessHelper;
import dev.timefall.mcdar.artifacts.artifact_types.ArtifactDamagingItem;
import dev.timefall.mcdar.config.McdarArtifactsStatsConfig;
import dev.timefall.mcdar.effect.EnchantmentEffects;
import dev.timefall.mcdx.api.AbilityHelper;
import dev.timefall.mcdx.configs.McdxCoreConfig;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class HarvesterItem extends ArtifactDamagingItem {
    public HarvesterItem() {
        super(McdarArtifactsStatsConfig.CONFIG.mcdar$getDamagingArtifactStats().HARVESTER_STATS);
    }

    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);
        int experienceDrain = McdarArtifactsStatsConfig.CONFIG.mcdar$getDamagingArtifactStats().HARVESTER_STATS.mcdar$getExperienceDrain();
        int maxCooldownEnchantmentTime = McdarArtifactsStatsConfig.CONFIG.mcdar$getDamagingArtifactStats().HARVESTER_STATS.mcdar$getMaxCooldownEnchantmentTime();
        int modifiedCooldownEnchantmentTime = EnchantmentEffects.cooldownEffect(maxCooldownEnchantmentTime, user, world);
        int damage = McdarArtifactsStatsConfig.CONFIG.mcdar$getDamagingArtifactStats().HARVESTER_STATS.mcdar$getDamageOrHealing();
        float range = McdarArtifactsStatsConfig.CONFIG.mcdar$getDamagingArtifactStats().HARVESTER_STATS.mcdar$getRange();

        if (user.totalExperience >= experienceDrain || user.isCreative()) {
            CleanlinessHelper.playCenteredSound(user, SoundEvents.ENTITY_GENERIC_EXPLODE.value(), 1.0F, 1.0F);
            AOECloudHelper.spawnExplosionCloud(user, user, range);
            AbilityHelper.applyToNearestNTargets(user, Integer.MAX_VALUE, range, McdxCoreConfig.INSTANCE.aoeExclusions, AOEHelper.causeExplosion(user, damage));

            if (!user.isCreative()) {
                user.addExperience(-experienceDrain);
                EquipmentSlot equipmentSlot = hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
                itemStack.damage(1, user, equipmentSlot);
            }

            user.getItemCooldownManager().set(this, modifiedCooldownEnchantmentTime);
        }
        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext tooltipContext, List<Text> tooltip, TooltipType type){
        CleanlinessHelper.createLoreTTips(stack, tooltip);
    }
}
