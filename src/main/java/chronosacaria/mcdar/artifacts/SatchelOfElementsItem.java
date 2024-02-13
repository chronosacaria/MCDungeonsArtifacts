package chronosacaria.mcdar.artifacts;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.api.AOEHelper;
import chronosacaria.mcdar.api.CleanlinessHelper;
import chronosacaria.mcdar.api.McdarEnchantmentHelper;
import chronosacaria.mcdar.enums.StatusInflictingArtifactID;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class SatchelOfElementsItem extends ArtifactStatusInflictingItem{
    public SatchelOfElementsItem() {
        super(
                StatusInflictingArtifactID.SATCHEL_OF_ELEMENTS,
                Mcdar.CONFIG.mcdarArtifactsStatsConfig.STATUS_INFLICTING_ARTIFACT_STATS
                        .get(StatusInflictingArtifactID.SATCHEL_OF_ELEMENTS).mcdar$getDurability()
        );
    }

    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);

        if (user.totalExperience >= 15 || user.isCreative()) {
            AOEHelper.satchelOfElementsEffects(user);

            if (!user.isCreative()) {
                user.addExperience(-15);
                itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));
            }
            McdarEnchantmentHelper.mcdar$cooldownHelper(
                    user,
                    this
            );
        }
        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        CleanlinessHelper.createLoreTTips(stack, tooltip);
    }
}
