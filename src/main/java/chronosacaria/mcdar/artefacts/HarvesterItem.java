package chronosacaria.mcdar.artefacts;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.api.AOECloudHelper;
import chronosacaria.mcdar.api.AOEHelper;
import chronosacaria.mcdar.config.McdarConfig;
import chronosacaria.mcdar.enums.DamagingArtefactID;
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

public class HarvesterItem extends ArtefactDamagingItem{
    public HarvesterItem(DamagingArtefactID artefactID) {
        super(artefactID);
    }

    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);

        if (user.totalExperience >= 40 || user.isCreative()){
            if (!user.isCreative()){
                user.addExperience(-40);
            }
            user.world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_GENERIC_EXPLODE,
                    SoundCategory.PLAYERS, 1.0F, 1.0F);
            AOECloudHelper.spawnExplosionCloud(user, user, 3.0f);
            AOEHelper.causeMagicExplosionAttack(user, user, 15, 3.0F);

            if (!user.isCreative()){
                itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));
            }
            user.getItemCooldownManager().set(this, 20);
        }
        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        tooltip.add(new TranslatableText("tooltip_info_item.mcdar.harvester_1").formatted(Formatting.ITALIC));
        tooltip.add(new TranslatableText("tooltip_info_item.mcdar.harvester_2").formatted(Formatting.ITALIC));
        tooltip.add(new TranslatableText("tooltip_info_item.mcdar.harvester_3").formatted(Formatting.ITALIC));
    }
}
