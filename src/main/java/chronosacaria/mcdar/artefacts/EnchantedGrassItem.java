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
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class EnchantedGrassItem extends ArtefactSummoningItem{
    public EnchantedGrassItem(SummoningArtefactID artefactID) {
        super(artefactID);
    }

    public ActionResult useOnBlock (ItemUsageContext itemUsageContext){
        if (itemUsageContext.getWorld() instanceof ServerWorld serverWorld) {
            PlayerEntity itemUsageContextPlayer = itemUsageContext.getPlayer();

            if (itemUsageContextPlayer != null) {

                int effectInt = (new Random()).nextInt(3);
                SheepEntity sheep = SummoningHelper.SHEEP[effectInt].create(serverWorld);

                if (SummoningHelper.summonSummonableEntity(sheep, itemUsageContextPlayer, itemUsageContext.getBlockPos())) {
                    assert sheep != null;
                    if (CleanlinessHelper.percentToOccur(1))
                        sheep.setCustomName(Text.literal("Lilly"));
                    SummoningHelper.summonedSheepEffect(sheep, effectInt);

                    if (!itemUsageContextPlayer.isCreative())
                        itemUsageContext.getStack().damage(1, itemUsageContextPlayer,
                                (entity) -> entity.sendToolBreakStatus(itemUsageContext.getHand()));

                    EnchantmentHelper.cooldownHelper(itemUsageContextPlayer, this, 600);
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
