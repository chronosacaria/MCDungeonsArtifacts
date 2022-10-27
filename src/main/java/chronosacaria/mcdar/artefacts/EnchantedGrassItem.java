package chronosacaria.mcdar.artefacts;

import chronosacaria.mcdar.api.CleanlinessHelper;
import chronosacaria.mcdar.api.EnchantmentHelper;
import chronosacaria.mcdar.api.SummoningHelper;
import chronosacaria.mcdar.enums.SummoningArtefactID;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class EnchantedGrassItem extends ArtefactSummoningItem{
    public EnchantedGrassItem(SummoningArtefactID artefactID) {
        super(artefactID);
    }

    public ActionResult useOnBlock (ItemUsageContext itemUsageContext){
        World world = itemUsageContext.getWorld();

        if (world.isClient){
            return ActionResult.SUCCESS;
        } else {
            PlayerEntity itemUsageContextPlayer = itemUsageContext.getPlayer();

            if (itemUsageContextPlayer != null) {

                int effectInt = (new Random()).nextInt(3);
                SheepEntity sheep = SummoningHelper.SHEEP[effectInt].create(world);

                if (SummoningHelper.summonSummonableEntity(sheep, itemUsageContextPlayer, itemUsageContext.getBlockPos())) {
                    assert sheep != null;
                    if (CleanlinessHelper.percentToOccur(1))
                        sheep.setCustomName(Text.literal("Lilly"));
                    SummoningHelper.summonedSheepEffect(sheep, effectInt);

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
        tooltip.add(Text.translatable("tooltip_info_item.mcdar.enchanted_grass_1").formatted(Formatting.ITALIC));
        tooltip.add(Text.translatable("tooltip_info_item.mcdar.enchanted_grass_2").formatted(Formatting.ITALIC));
        tooltip.add(Text.translatable("tooltip_info_item.mcdar.enchanted_grass_3").formatted(Formatting.ITALIC));
        tooltip.add(Text.translatable("tooltip_info_item.mcdar.enchanted_grass_4").formatted(Formatting.ITALIC));
    }
}
