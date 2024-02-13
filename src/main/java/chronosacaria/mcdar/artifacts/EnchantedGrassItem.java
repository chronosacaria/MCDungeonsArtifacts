package chronosacaria.mcdar.artifacts;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.api.CleanlinessHelper;
import chronosacaria.mcdar.api.McdarEnchantmentHelper;
import chronosacaria.mcdar.api.SummoningHelper;
import chronosacaria.mcdar.enums.SummoningArtifactID;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class EnchantedGrassItem extends ArtifactSummoningItem{
    public EnchantedGrassItem() {
        super(
                SummoningArtifactID.ENCHANTED_GRASS,
                Mcdar.CONFIG.mcdarArtifactsStatsConfig.SUMMONING_ARTIFACT_STATS
                        .get(SummoningArtifactID.ENCHANTED_GRASS).mcdar$getDurability()
        );
    }

    public ActionResult useOnBlock (ItemUsageContext itemUsageContext){
        if (itemUsageContext.getWorld() instanceof ServerWorld serverWorld) {
            PlayerEntity itemUsageContextPlayer = itemUsageContext.getPlayer();

            if (itemUsageContextPlayer != null) {

                int effectInt = (new Random()).nextInt(3);
                SheepEntity sheep = SummoningHelper.SHEEP.get(effectInt).create(serverWorld);

                if (SummoningHelper.mcdar$summonSummonableEntity(sheep, itemUsageContextPlayer, itemUsageContext.getBlockPos())) {
                    assert sheep != null;
                    if (CleanlinessHelper.percentToOccur(1))
                        sheep.setCustomName(Text.literal("Lilly"));
                    SummoningHelper.mcdar$summonedSheepEffect(sheep, effectInt);

                    if (!itemUsageContextPlayer.isCreative())
                        itemUsageContext.getStack().damage(1, itemUsageContextPlayer,
                                (entity) -> entity.sendToolBreakStatus(itemUsageContext.getHand()));

                    McdarEnchantmentHelper.mcdar$cooldownHelper(
                            itemUsageContextPlayer,
                            this
                    );
                    return ActionResult.CONSUME;
                }
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        CleanlinessHelper.createLoreTTips(stack, tooltip);
    }
}
