package chronosacaria.mcdar.artifacts;

import chronosacaria.mcdar.api.CleanlinessHelper;
import chronosacaria.mcdar.api.EnchantmentHelper;
import chronosacaria.mcdar.enums.AgilityArtifactID;
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

public class DeathCapMushroomItem extends ArtifactAgilityItem{
    public DeathCapMushroomItem() {
        super(AgilityArtifactID.DEATH_CAP_MUSHROOM);
    }

    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        user.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 180, 3));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 180, 1));
        if (!user.isCreative())
            itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));

        EnchantmentHelper.cooldownHelper(user, this, 600);

        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        CleanlinessHelper.createLoreTTips(stack, tooltip);
    }
}
