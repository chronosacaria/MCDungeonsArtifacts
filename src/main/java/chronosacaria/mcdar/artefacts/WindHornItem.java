package chronosacaria.mcdar.artefacts;

import chronosacaria.mcdar.api.AOEHelper;
import chronosacaria.mcdar.api.AbilityHelper;
import chronosacaria.mcdar.api.EnchantmentHelper;
import chronosacaria.mcdar.enums.DefenciveArtefactID;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
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

public class WindHornItem extends ArtefactDefenciveItem{
    public WindHornItem(DefenciveArtefactID artefactID) {
        super(artefactID);
    }

    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);

        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.EVENT_RAID_HORN,
                SoundCategory.BLOCKS, 5.0F, 1.0F);
        for (LivingEntity nearbyEntity : AOEHelper.getEntitiesByPredicate(user, 5,
                (nearbyEntity) -> nearbyEntity != user && !AbilityHelper.isPetOf(nearbyEntity, user) && nearbyEntity.isAlive())) {
            AOEHelper.knockbackNearbyEnemies(user, nearbyEntity, 2.0F);
        }

        if (!user.isCreative()){
            itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));
        }
        EnchantmentHelper.cooldownHelper(user, this, 250);

        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        tooltip.add(Text.translatable("tooltip_info_item.mcdar.wind_horn_1").formatted(Formatting.ITALIC));
        tooltip.add(Text.translatable("tooltip_info_item.mcdar.wind_horn_2").formatted(Formatting.ITALIC));
        tooltip.add(Text.translatable("tooltip_info_item.mcdar.wind_horn_3").formatted(Formatting.ITALIC));
    }
}
