package chronosacaria.mcdar.artefacts;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.api.SummoningHelper;
import chronosacaria.mcdar.config.McdarConfig;
import chronosacaria.mcdar.enums.DamagingArtefactID;
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

import static chronosacaria.mcdar.api.AOECloudHelper.spawnRegenCloudAtPos;
import static chronosacaria.mcdar.api.SummoningHelper.summonBuzzyNestBee;

public class BuzzyNestItem extends ArtefactSummoningItem{
    public BuzzyNestItem(SummoningArtefactID artefactID) {
        super(artefactID);
    }

    public ActionResult useOnBlock (ItemUsageContext itemUsageContext){
        World world = itemUsageContext.getWorld();

        if (world.isClient){
            return ActionResult.SUCCESS;
        } else {
            ItemStack itemUsageContextItem = itemUsageContext.getStack();
            PlayerEntity itemUsageContextPlayer = itemUsageContext.getPlayer();
            Hand itemUseContextHand = itemUsageContext.getHand();
            BlockPos itemUseContextBlockPos = itemUsageContext.getBlockPos();
            Direction itemUseContextFacing = itemUsageContext.getPlayerFacing();
            BlockState blockState = world.getBlockState(itemUseContextBlockPos);

            BlockPos blockPos;
            if (blockState.getCollisionShape(world, itemUseContextBlockPos).isEmpty()){
                blockPos = itemUseContextBlockPos;
            } else {
                blockPos = itemUseContextBlockPos.offset(itemUseContextFacing);
            }
            if (itemUsageContextPlayer != null){

                summonBuzzyNestBee(itemUsageContextPlayer, itemUseContextBlockPos);

                if (!itemUsageContextPlayer.isCreative()){
                    itemUsageContextItem.damage(1, itemUsageContextPlayer,
                            (entity) -> entity.sendToolBreakStatus(itemUseContextHand));
                }
                itemUsageContextPlayer.getItemCooldownManager().set(this, 600);
            }
        }
        return ActionResult.CONSUME;
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        tooltip.add(new TranslatableText("tooltip_info_item.mcdar.buzzy_nest_1").formatted(Formatting.ITALIC));
        tooltip.add(new TranslatableText("tooltip_info_item.mcdar.buzzy_nest_2").formatted(Formatting.ITALIC));
        tooltip.add(new TranslatableText("tooltip_info_item.mcdar.buzzy_nest_3").formatted(Formatting.ITALIC));
        tooltip.add(new TranslatableText("tooltip_info_item.mcdar.buzzy_nest_4").formatted(Formatting.ITALIC));
    }
}
