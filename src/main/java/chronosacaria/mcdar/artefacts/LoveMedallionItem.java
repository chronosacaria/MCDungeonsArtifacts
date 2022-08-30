package chronosacaria.mcdar.artefacts;

import chronosacaria.mcdar.api.AbilityHelper;
import chronosacaria.mcdar.api.EnchantmentHelper;
import chronosacaria.mcdar.enums.StatusInflictingArtefactID;
import chronosacaria.mcdar.init.StatusEffectInit;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class LoveMedallionItem extends ArtefactStatusInflictingItem {
    public LoveMedallionItem(StatusInflictingArtefactID id) {
        super(id);
    }

    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);

        int i = 0;
        for (MobEntity entitiesByClass : user.getEntityWorld().getEntitiesByClass(MobEntity.class,
                new Box(user.getBlockPos()).expand(6),
                (nearbyEntity) -> AbilityHelper.isAoeTarget(nearbyEntity, user, user)
        )) {
            sendIntoWildRage(entitiesByClass);
            i++;
            if (i >= 3)
                break;
        }

        if (!user.isCreative()){
            itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));
        }
        EnchantmentHelper.cooldownHelper(user, this, 120);

        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }

    public static void sendIntoWildRage(MobEntity mobEntity) {
        mobEntity.addStatusEffect(new StatusEffectInstance(StatusEffectInit.CHARMED, 200, 0));
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        tooltip.add(new TranslatableText("tooltip_info_item.mcdar.love_medallion_1").formatted(Formatting.ITALIC));
        tooltip.add(new TranslatableText("tooltip_info_item.mcdar.love_medallion_2").formatted(Formatting.ITALIC));
        tooltip.add(new TranslatableText("tooltip_info_item.mcdar.love_medallion_3").formatted(Formatting.ITALIC));
        tooltip.add(new TranslatableText("tooltip_info_item.mcdar.love_medallion_4").formatted(Formatting.ITALIC));
    }

}

/*
3 nearby mobs give off heart particles, become allies for 10 seconds, then die
// Abridged
3 mobs within 6 radius are afflicted with wild rage for 10 seconds as is in mcdw
 */