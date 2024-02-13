package chronosacaria.mcdar.artifacts;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.api.CleanlinessHelper;
import chronosacaria.mcdar.api.McdarEnchantmentHelper;
import chronosacaria.mcdar.effects.ArtifactEffects;
import chronosacaria.mcdar.enums.DefensiveArtifactID;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class EnchantersTomeItem extends ArtifactDefensiveItem{
    public EnchantersTomeItem() {
        super(
                DefensiveArtifactID.ENCHANTERS_TOME,
                Mcdar.CONFIG.mcdarArtifactsStatsConfig.DEFENSIVE_ARTIFACT_STATS
                        .get(DefensiveArtifactID.ENCHANTERS_TOME).mcdar$getDurability()
        );
    }

    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        ArtifactEffects.enchantersTomeEffects(user);
        if (!user.isCreative())
            itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));

        McdarEnchantmentHelper.mcdar$cooldownHelper(
                user,
                this
        );

        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        CleanlinessHelper.createLoreTTips(stack, tooltip);
    }
}
