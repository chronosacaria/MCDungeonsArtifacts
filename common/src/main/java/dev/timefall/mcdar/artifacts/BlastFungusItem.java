package dev.timefall.mcdar.artifacts;

import dev.timefall.mcdar.api.CleanlinessHelper;
import dev.timefall.mcdar.artifacts.artifact_types.ArtifactDamagingItem;
import dev.timefall.mcdar.config.McdarArtifactsStatsConfig;
import dev.timefall.mcdar.effect.ArtifactEffects;
import dev.timefall.mcdar.effect.EnchantmentEffects;
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


public class BlastFungusItem extends ArtifactDamagingItem {

    public BlastFungusItem() {
            super(McdarArtifactsStatsConfig.CONFIG.mcdar$getDamagingArtifactStats().BLAST_FUNGUS_STATS);
    }

    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);

        float range = McdarArtifactsStatsConfig.CONFIG.mcdar$getDamagingArtifactStats().BLAST_FUNGUS_STATS.mcdar$getRange();
        float damage = McdarArtifactsStatsConfig.CONFIG.mcdar$getDamagingArtifactStats().BLAST_FUNGUS_STATS.mcdar$getDamage();
        int maxCooldownEnchantmentTime = McdarArtifactsStatsConfig.CONFIG.mcdar$getDamagingArtifactStats().BLAST_FUNGUS_STATS.mcdar$getMaxCooldownEnchantmentTime();
        int modifiedCooldownEnchantmentTime = EnchantmentEffects.cooldownEffect(maxCooldownEnchantmentTime, user, world);


        CleanlinessHelper.playCenteredSound(user, SoundEvents.ENTITY_GENERIC_EXPLODE.value(), 1.0F, 1.0F);
        ArtifactEffects.mcdar$causeBlastFungusExplosions(user, range, damage);
        return CleanlinessHelper.mcdar$useAndDamageArtifact(user, hand, itemStack, modifiedCooldownEnchantmentTime);

    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext tooltipContext, List<Text> tooltip, TooltipType type){
        CleanlinessHelper.createLoreTTips(stack, tooltip);
    }
}
