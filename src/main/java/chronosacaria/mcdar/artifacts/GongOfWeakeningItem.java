package chronosacaria.mcdar.artifacts;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.api.AOEHelper;
import chronosacaria.mcdar.api.CleanlinessHelper;
import chronosacaria.mcdar.api.McdarEnchantmentHelper;
import chronosacaria.mcdar.enums.StatusInflictingArtifactID;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;


public class GongOfWeakeningItem extends ArtifactStatusInflictingItem{
    public GongOfWeakeningItem() {
        super(
                StatusInflictingArtifactID.GONG_OF_WEAKENING,
                Mcdar.CONFIG.mcdarArtifactsStatsConfig.STATUS_INFLICTING_ARTIFACT_STATS
                        .get(StatusInflictingArtifactID.GONG_OF_WEAKENING).mcdar$getDurability()
        );
    }

    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);

        CleanlinessHelper.playCenteredSound(user, SoundEvents.BLOCK_BELL_USE, SoundCategory.BLOCKS, 2.0F, 1.0F);
        CleanlinessHelper.playCenteredSound(user, SoundEvents.BLOCK_BELL_RESONATE, SoundCategory.BLOCKS, 1.0F, 1.0F);

        AOEHelper.afflictNearbyEntities(user, new StatusEffectInstance(StatusEffects.WEAKNESS, 140, 140),
                new StatusEffectInstance(StatusEffects.RESISTANCE, 140, -2));

        if (!user.isCreative())
            itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));

        McdarEnchantmentHelper.mcdar$cooldownHelper(
                user,
                this
        );
        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        CleanlinessHelper.createLoreTTips(stack, tooltip);
    }
}
