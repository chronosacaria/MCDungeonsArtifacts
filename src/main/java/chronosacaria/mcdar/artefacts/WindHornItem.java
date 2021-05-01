package chronosacaria.mcdar.artefacts;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.api.AOEHelper;
import chronosacaria.mcdar.config.McdarConfig;
import chronosacaria.mcdar.enums.DefenciveArtefactID;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.*;
import net.minecraft.util.registry.Registry;
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
        AOEHelper.knockbackNearbyEnemies(user);

        if (!user.isCreative()){
            itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));
        }
        user.getItemCooldownManager().set(this, 200);
        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        tooltip.add(new TranslatableText("tooltip_info_item.mcdar.wind_horn_1").formatted(Formatting.ITALIC));
        tooltip.add(new TranslatableText("tooltip_info_item.mcdar.wind_horn_2").formatted(Formatting.ITALIC));
        tooltip.add(new TranslatableText("tooltip_info_item.mcdar.wind_horn_3").formatted(Formatting.ITALIC));
    }
}
