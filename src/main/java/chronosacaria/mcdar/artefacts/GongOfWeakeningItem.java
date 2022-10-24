package chronosacaria.mcdar.artefacts;

import chronosacaria.mcdar.api.AOEHelper;
import chronosacaria.mcdar.api.EnchantmentHelper;
import chronosacaria.mcdar.enums.StatusInflictingArtefactID;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;


public class GongOfWeakeningItem extends ArtefactStatusInflictingItem{
    public GongOfWeakeningItem(StatusInflictingArtefactID artefactID) {
        super(artefactID);
    }

    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);
        World entityWorld = user.getEntityWorld();

        entityWorld.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.BLOCK_BELL_USE,SoundCategory.BLOCKS, 2.0F, 1.0F);
        entityWorld.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.BLOCK_BELL_RESONATE,SoundCategory.BLOCKS, 1.0F, 1.0F);

        AOEHelper.afflictNearbyEnemies(user, new StatusEffectInstance(StatusEffects.WEAKNESS, 140, 140),
                new StatusEffectInstance(StatusEffects.RESISTANCE, 140, -2));

        if (!user.isCreative()){
            itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));
        }
        EnchantmentHelper.cooldownHelper(user, this, 100);

        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        tooltip.add(Text.translatable("tooltip_info_item.mcdar.gong_of_weakening_1").formatted(Formatting.ITALIC));
        tooltip.add(Text.translatable("tooltip_info_item.mcdar.gong_of_weakening_2").formatted(Formatting.ITALIC));
        tooltip.add(Text.translatable("tooltip_info_item.mcdar.gong_of_weakening_3").formatted(Formatting.ITALIC));
        tooltip.add(Text.translatable("tooltip_info_item.mcdar.gong_of_weakening_4").formatted(Formatting.ITALIC));
    }
}
