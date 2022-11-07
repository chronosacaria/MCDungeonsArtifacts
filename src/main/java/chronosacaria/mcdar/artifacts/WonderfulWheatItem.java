package chronosacaria.mcdar.artifacts;

import chronosacaria.mcdar.api.CleanlinessHelper;
import chronosacaria.mcdar.api.EnchantmentHelper;
import chronosacaria.mcdar.api.SummoningHelper;
import chronosacaria.mcdar.enums.SummoningArtifactID;
import chronosacaria.mcdar.init.SummonedEntityRegistry;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

import java.util.List;

public class WonderfulWheatItem extends ArtifactSummoningItem{
    public WonderfulWheatItem() {
        super(SummoningArtifactID.WONDERFUL_WHEAT);
    }

    public ActionResult useOnBlock (ItemUsageContext itemUsageContext){
        if (itemUsageContext.getWorld() instanceof ServerWorld serverWorld) {
            PlayerEntity itemUsageContextPlayer = itemUsageContext.getPlayer();

            if (itemUsageContextPlayer != null){

                if (SummoningHelper.summonSummonableEntity(SummonedEntityRegistry.WONDERFUL_WHEAT_LLAMA_ENTITY.create(serverWorld),
                        itemUsageContextPlayer, itemUsageContext.getBlockPos())) {

                    if (!itemUsageContextPlayer.isCreative())
                        itemUsageContext.getStack().damage(1, itemUsageContextPlayer,
                                (entity) -> entity.sendToolBreakStatus(itemUsageContext.getHand()));

                    EnchantmentHelper.cooldownHelper(itemUsageContextPlayer, this, 600);
                    return ActionResult.SUCCESS;
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