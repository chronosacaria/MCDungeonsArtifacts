package chronosacaria.mcdar.artefacts;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.config.McdarConfig;
import chronosacaria.mcdar.enums.SummoningArtefactID;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.List;

import static chronosacaria.mcdar.api.SummoningHelper.summonWonderfulWheatLlama;

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

                summonWonderfulWheatLlama(itemUsageContextPlayer, itemUsageContext.getBlockPos());

                if (!itemUsageContextPlayer.isCreative()){
                    itemUsageContext.getStack().damage(1, itemUsageContextPlayer,
                            (entity) -> entity.sendToolBreakStatus(itemUsageContext.getHand()));
                }
                itemUsageContextPlayer.getItemCooldownManager().set(this, 600);
            }
        }
        return ActionResult.CONSUME;
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        tooltip.add(new TranslatableText("tooltip_info_item.mcdar.wonderful_wheat_1").formatted(Formatting.ITALIC));
        tooltip.add(new TranslatableText("tooltip_info_item.mcdar.wonderful_wheat_2").formatted(Formatting.ITALIC));
        tooltip.add(new TranslatableText("tooltip_info_item.mcdar.wonderful_wheat_3").formatted(Formatting.ITALIC));
    }
}
