package chronosacaria.mcdar.artefacts;

import chronosacaria.mcdar.api.AOEHelper;
import chronosacaria.mcdar.api.EnchantmentHelper;
import chronosacaria.mcdar.enums.StatusInflictingArtefactID;
import chronosacaria.mcdar.init.StatusEffectInit;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;


public class ShockPowderItem extends ArtefactStatusInflictingItem{
    public ShockPowderItem(StatusInflictingArtefactID artefactID) {
        super(artefactID);
    }

    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);

        AOEHelper.afflictNearbyEntities(user, new StatusEffectInstance(StatusEffectInit.STUNNED, 100),
                new StatusEffectInstance(StatusEffects.NAUSEA, 100),
                new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 4));
        if (!user.isCreative()){
            itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));
        }
        EnchantmentHelper.cooldownHelper(user, this, 300);

        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        tooltip.add(Text.translatable("tooltip_info_item.mcdar.shock_powder_1").formatted(Formatting.ITALIC));
        tooltip.add(Text.translatable("tooltip_info_item.mcdar.shock_powder_2").formatted(Formatting.ITALIC));
    }
}
