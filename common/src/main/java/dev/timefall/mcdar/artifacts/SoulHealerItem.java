package dev.timefall.mcdar.artifacts;

import dev.timefall.mcdar.api.CleanlinessHelper;
import dev.timefall.mcdar.artifacts.artifact_types.ArtifactDefensiveItem;
import dev.timefall.mcdar.config.McdarArtifactsStatsConfig;
import dev.timefall.mcdar.effect.EnchantmentEffects;
import dev.timefall.mcdx.api.AOEHelper;
import dev.timefall.mcdx.configs.McdxCoreConfig;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class SoulHealerItem extends ArtifactDefensiveItem {
    public SoulHealerItem() {
        super(McdarArtifactsStatsConfig.CONFIG.mcdar$getDefensiveArtifactStats().SOUL_HEALER_STATS);
    }

    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand){
        int experienceDrain = McdarArtifactsStatsConfig.CONFIG.mcdar$getDefensiveArtifactStats().SOUL_HEALER_STATS.mcdar$getExperienceDrain();
        int maxCooldownEnchantmentTime = McdarArtifactsStatsConfig.CONFIG.mcdar$getDefensiveArtifactStats().SOUL_HEALER_STATS.mcdar$getMaxCooldownEnchantmentTime();
        int modifiedCooldownEnchantmentTime = EnchantmentEffects.cooldownEffect(maxCooldownEnchantmentTime, user, world);
        float range = McdarArtifactsStatsConfig.CONFIG.mcdar$getDefensiveArtifactStats().SOUL_HEALER_STATS.mcdar$getRange();

        ItemStack itemStack = user.getStackInHand(hand);

        if (user.totalExperience
                >= experienceDrain
                || user.isCreative()) {

            boolean bl = user.getHealth() < user.getMaxHealth();
            float healedAmount = bl
                    ? healAlly(user)
                    : healMostInjuredAlly(user, range);
            if (!user.isCreative()){
                if (healedAmount > 0) {
                    user.addExperience((int) (-healedAmount));
                    EquipmentSlot equipmentSlot = hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;

                    itemStack.damage(1, user, equipmentSlot);
                    EnchantmentEffects.mcdar$cooldownHelper(
                            user,
                            this,
                            bl
                                    ? 20
                                    : modifiedCooldownEnchantmentTime
                    );
                }
            }
        }

        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext tooltipContext, List<Text> tooltip, TooltipType type){
        CleanlinessHelper.createLoreTTips(stack, tooltip);
    }

    public static float healMostInjuredAlly(LivingEntity healer, float distance) {
        List<LivingEntity> nearbyEntities = AOEHelper.getEntitiesWithExclusions(healer, distance, McdxCoreConfig.INSTANCE.allyExclusions);
        if (!nearbyEntities.isEmpty()) {
            nearbyEntities.sort((o1, o2) -> {
                float o1LostHealth = o1.getMaxHealth() - o1.getHealth();
                float o2LostHealth = o2.getMaxHealth() - o2.getHealth();
                return Float.compare(o1LostHealth, o2LostHealth);
            });
            LivingEntity mostInjuredAlly = nearbyEntities.get(nearbyEntities.size() - 1);
            return healAlly(mostInjuredAlly);
        } else
            return 0;
    }

    public static float healAlly(LivingEntity allyToBeHealed) {
        float healingBase = McdarArtifactsStatsConfig.CONFIG.mcdar$getDefensiveArtifactStats().SOUL_HEALER_STATS.mcdar$getDamageOrHealingFactor();
        float maxHealth = allyToBeHealed.getMaxHealth();
        float lostHealth = maxHealth - allyToBeHealed.getHealth();
        float healedAmount = Math.min(
                lostHealth,
                healingBase * maxHealth
        );
        allyToBeHealed.heal(healedAmount);
        //addParticles((ServerWorld) world, mostInjuredAlly, ParticleTypes.HEART);
        return healedAmount;
    }
}
