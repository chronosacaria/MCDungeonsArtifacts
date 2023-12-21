package chronosacaria.mcdar.artifacts;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.api.AOEHelper;
import chronosacaria.mcdar.api.CleanlinessHelper;
import chronosacaria.mcdar.api.EnchantmentHelper;
import chronosacaria.mcdar.enums.DamagingArtifactID;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class LightningRodItem extends ArtifactDamagingItem{
    public LightningRodItem() {
        super(
                DamagingArtifactID.LIGHTNING_ROD,
                Mcdar.CONFIG.mcdarArtifactsStatsConfig.DAMAGING_ARTIFACT_STATS
                        .get(DamagingArtifactID.LIGHTNING_ROD).mcdar$getDurability()
        );
    }

    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        if (user.totalExperience >= 15 || user.isCreative()) {
            AOEHelper.electrocuteNearbyEnemies(user, 5, 5, Integer.MAX_VALUE);

            if (!user.isCreative()){
                user.addExperience(-15);
                itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));
            }
            EnchantmentHelper.mcdar$cooldownHelper(
                    user,
                    this,
                    Mcdar.CONFIG.mcdarArtifactsStatsConfig.DAMAGING_ARTIFACT_STATS
                            .get(DamagingArtifactID.LIGHTNING_ROD)
                            .mcdar$getMaxCooldownEnchantmentTime()
            );
        }
        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        CleanlinessHelper.createLoreTTips(stack, tooltip);
    }
}
