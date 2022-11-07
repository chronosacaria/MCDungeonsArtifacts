package chronosacaria.mcdar.artifacts;

import chronosacaria.mcdar.api.AOEHelper;
import chronosacaria.mcdar.api.AbilityHelper;
import chronosacaria.mcdar.api.CleanlinessHelper;
import chronosacaria.mcdar.api.EnchantmentHelper;
import chronosacaria.mcdar.enums.AgilityArtifactID;
import chronosacaria.mcdar.init.StatusEffectInit;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
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

public class LightFeatherItem extends ArtifactAgilityItem{
    public LightFeatherItem() {
        super(AgilityArtifactID.LIGHT_FEATHER);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        user.jump();

        for (LivingEntity nearbyEntity : AOEHelper.getEntitiesByPredicate(user, 5,
                (nearbyEntity) -> nearbyEntity != user && !AbilityHelper.isPetOf(nearbyEntity, user) && nearbyEntity.isAlive())) {
            AOEHelper.knockbackNearbyEnemies(user, nearbyEntity, 5.0F);

            nearbyEntity.addStatusEffect(new StatusEffectInstance(StatusEffectInit.STUNNED, 60));
            nearbyEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 60));
            nearbyEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 4));
        }

        if (!user.isCreative())
            itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));

        EnchantmentHelper.cooldownHelper(user, this, 120);

        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        CleanlinessHelper.createLoreTTips(stack, tooltip);
    }
}
