package chronosacaria.mcdar.artefacts;

import chronosacaria.mcdar.enchants.EnchantID;
import chronosacaria.mcdar.enums.SummoningArtefactID;
import chronosacaria.mcdar.init.EnchantsRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.List;

import static chronosacaria.mcdar.api.SummoningHelper.summonGolemKitGolem;

public class GolemKitItem extends ArtefactSummoningItem{
    public GolemKitItem(SummoningArtefactID artefactID) {
        super(artefactID);
    }

    public ActionResult useOnBlock (ItemUsageContext itemUsageContext){
        World world = itemUsageContext.getWorld();

        if (world.isClient){
            return ActionResult.SUCCESS;
        } else {
            PlayerEntity itemUsageContextPlayer = itemUsageContext.getPlayer();

            if (itemUsageContextPlayer != null){

                summonGolemKitGolem(itemUsageContextPlayer, itemUsageContext.getBlockPos());

                if (!itemUsageContextPlayer.isCreative()){
                    itemUsageContext.getStack().damage(1, itemUsageContextPlayer,
                            (entity) -> entity.sendToolBreakStatus(itemUsageContext.getHand()));
                }
                int cooldownLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(EnchantID.COOLDOWN),
                        itemUsageContextPlayer);
                if (cooldownLevel > 0) {
                    itemUsageContextPlayer.getItemCooldownManager().set(this, (int) ((cooldownLevel * 0.1) * 600));
                } else {
                    itemUsageContextPlayer.getItemCooldownManager().set(this, 600);
                }
            }
        }
        return ActionResult.CONSUME;
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        tooltip.add(new TranslatableText("tooltip_info_item.mcdar.golem_kit_1").formatted(Formatting.ITALIC));
        tooltip.add(new TranslatableText("tooltip_info_item.mcdar.golem_kit_2").formatted(Formatting.ITALIC));
        tooltip.add(new TranslatableText("tooltip_info_item.mcdar.golem_kit_3").formatted(Formatting.ITALIC));
        tooltip.add(new TranslatableText("tooltip_info_item.mcdar.golem_kit_4").formatted(Formatting.ITALIC));
    }
}
