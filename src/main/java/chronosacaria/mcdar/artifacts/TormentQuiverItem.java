package chronosacaria.mcdar.artifacts;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.api.CleanlinessHelper;
import chronosacaria.mcdar.enums.QuiverArtifactID;
import chronosacaria.mcdar.registries.EnchantsRegistry;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class TormentQuiverItem extends ArtifactQuiverItem{
    public TormentQuiverItem() {
        super(
                QuiverArtifactID.TORMENT_QUIVER,
                Mcdar.CONFIG.mcdarArtifactsStatsConfig.QUIVER_ARTIFACT_STATS
                        .get(QuiverArtifactID.TORMENT_QUIVER).mcdar$getDurability()
        );
    }

    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);
        if (user.totalExperience >= 20 || user.isCreative()){

            int cooldownLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.COOLDOWN, user);
            user.getItemCooldownManager().set(
                    this,
                    (cooldownLevel + 1) * Mcdar.CONFIG.mcdarArtifactsStatsConfig.QUIVER_ARTIFACT_STATS
                            .get(QuiverArtifactID.TORMENT_QUIVER)
                            .mcdar$getMaxCooldownEnchantmentTime()
            );

            if (!user.isCreative()) {
                user.addExperience(-20);
                itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));
            }
        }
        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        CleanlinessHelper.createLoreTTips(stack, tooltip);
    }
}