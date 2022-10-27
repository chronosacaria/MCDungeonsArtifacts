package chronosacaria.mcdar.artefacts;

import chronosacaria.mcdar.api.EnchantmentHelper;
import chronosacaria.mcdar.api.SummoningHelper;
import chronosacaria.mcdar.enums.SummoningArtefactID;
import chronosacaria.mcdar.init.SummonedEntityRegistry;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class WonderfulWheatItem extends ArtefactSummoningItem{
    public WonderfulWheatItem(SummoningArtefactID artefactID) {
        super(artefactID);
    }

    public ActionResult useOnBlock (ItemUsageContext itemUsageContext){
        World world = itemUsageContext.getWorld();

        if (world.isClient){
            return ActionResult.SUCCESS;
        } else {
            PlayerEntity itemUsageContextPlayer = itemUsageContext.getPlayer();

            if (itemUsageContextPlayer != null){

                if (SummoningHelper.summonSummonableEntity(SummonedEntityRegistry.WONDERFUL_WHEAT_LLAMA_ENTITY.create(world), itemUsageContextPlayer, itemUsageContext.getBlockPos())) {

                    if (!itemUsageContextPlayer.isCreative()) {
                        itemUsageContext.getStack().damage(1, itemUsageContextPlayer,
                                (entity) -> entity.sendToolBreakStatus(itemUsageContext.getHand()));
                    }
                    EnchantmentHelper.cooldownHelper(itemUsageContextPlayer, this, 600);
                }
            }
        }
        return ActionResult.CONSUME;
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        tooltip.add(Text.translatable("tooltip_info_item.mcdar.wonderful_wheat_1").formatted(Formatting.ITALIC));
        tooltip.add(Text.translatable("tooltip_info_item.mcdar.wonderful_wheat_2").formatted(Formatting.ITALIC));
        tooltip.add(Text.translatable("tooltip_info_item.mcdar.wonderful_wheat_3").formatted(Formatting.ITALIC));
    }
}
