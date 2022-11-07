package chronosacaria.mcdar.artifacts;

import chronosacaria.mcdar.api.AOEHelper;
import chronosacaria.mcdar.api.CleanlinessHelper;
import chronosacaria.mcdar.api.EnchantmentHelper;
import chronosacaria.mcdar.enums.StatusInflictingArtifactID;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class CorruptedSeedsItem extends ArtifactStatusInflictingItem{
    public CorruptedSeedsItem() {
        super(StatusInflictingArtifactID.CORRUPTED_SEEDS);
    }

    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);

        AOEHelper.afflictNearbyEntities(user, new StatusEffectInstance(StatusEffects.SLOWNESS, 140, 4),
                new StatusEffectInstance(StatusEffects.POISON, 140, 1));

        if (!user.isCreative())
            itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));

        EnchantmentHelper.cooldownHelper(user, this, 100);

        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        CleanlinessHelper.createLoreTTips(stack, tooltip);
    }
}
