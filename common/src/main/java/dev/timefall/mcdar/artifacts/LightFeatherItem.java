package dev.timefall.mcdar.artifacts;

import dev.timefall.mcdar.api.CleanlinessHelper;
import dev.timefall.mcdar.artifacts.artifact_types.ArtifactAgilityItem;
import dev.timefall.mcdar.config.McdarArtifactsStatsConfig;
import dev.timefall.mcdar.effect.EnchantmentEffects;
import dev.timefall.mcdar.registry.StatusEffectRegistry;
import dev.timefall.mcdx.api.AOEHelper;
import dev.timefall.mcdx.configs.McdxCoreConfig;
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

public class LightFeatherItem extends ArtifactAgilityItem {
    public LightFeatherItem() {
        super(McdarArtifactsStatsConfig.CONFIG.mcdar$getAgilityArtifactStats().LIGHT_FEATHER_STATS);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        int maxCooldownEnchantmentTime = McdarArtifactsStatsConfig.CONFIG.mcdar$getAgilityArtifactStats().LIGHT_FEATHER_STATS.mcdar$getMaxCooldownEnchantmentTime();
        int modifiedCooldownEnchantmentTime = EnchantmentEffects.cooldownEffect(maxCooldownEnchantmentTime, user, world);

        user.jump();

        //TODO Replace with MCDX applyToTargets
        dev.timefall.mcdx.api.AbilityHelper.applyToNearestNTargets(user, Integer.MAX_VALUE, 5f, McdxCoreConfig.INSTANCE.allyExclusions, (nearbyEntity) -> {
            AOEHelper.knockbackNearbyEnemies(user, nearbyEntity, 5.0F);
            nearbyEntity.addStatusEffect(
                    new StatusEffectInstance(
                            StatusEffectRegistry.STUNNED.getEntry(),
                            McdarArtifactsStatsConfig.CONFIG.mcdar$getAgilityArtifactStats().DEATH_CAP_MUSHROOM_STATS.mcdar$getDuration(),
                            McdarArtifactsStatsConfig.CONFIG.mcdar$getAgilityArtifactStats().DEATH_CAP_MUSHROOM_STATS.mcdar$getAmplifier()
                    ));
            nearbyEntity.addStatusEffect(
                    new StatusEffectInstance(
                            StatusEffects.NAUSEA,
                            McdarArtifactsStatsConfig.CONFIG.mcdar$getAgilityArtifactStats().DEATH_CAP_MUSHROOM_STATS.mcdar$getDuration(),
                            McdarArtifactsStatsConfig.CONFIG.mcdar$getAgilityArtifactStats().DEATH_CAP_MUSHROOM_STATS.mcdar$getAmplifier2()
                    ));
            nearbyEntity.addStatusEffect(
                    new StatusEffectInstance(
                            StatusEffects.SLOWNESS,
                            McdarArtifactsStatsConfig.CONFIG.mcdar$getAgilityArtifactStats().DEATH_CAP_MUSHROOM_STATS.mcdar$getDuration(),
                            McdarArtifactsStatsConfig.CONFIG.mcdar$getAgilityArtifactStats().DEATH_CAP_MUSHROOM_STATS.mcdar$getAmplifier3()
                    ));
        });

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
    public void appendTooltip(ItemStack stack, TooltipContext tooltipContext, List<Text> tooltip, TooltipType type) {
        CleanlinessHelper.createLoreTTips(stack, tooltip);
    }
}
