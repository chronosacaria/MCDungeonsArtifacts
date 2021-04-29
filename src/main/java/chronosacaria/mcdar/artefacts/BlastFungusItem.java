package chronosacaria.mcdar.artefacts;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.api.AOECloudHelper;
import chronosacaria.mcdar.api.AOEHelper;
import chronosacaria.mcdar.config.McdarConfig;
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


public class BlastFungusItem extends ArtefactDamagingItem{
    public BlastFungusItem(Settings settings, String id) {
        super(settings);
        if (McdarConfig.config.enableBlastFungus) {
            Registry.register(Registry.ITEM, new Identifier(Mcdar.MOD_ID, id), this);
        }
    }

    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);

        user.world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_GENERIC_EXPLODE,
                SoundCategory.PLAYERS, 1.0F, 1.0F);
        AOEHelper.causeBlastFungusExplosions(user, 5, 4);
        if (!user.isCreative()){
            itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));
        }
        user.getItemCooldownManager().set(this, 120); // Six Seconds of cooldown, this might need to change
        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        tooltip.add(new TranslatableText("tooltip_info_item.mcdar.blast_fungus_1").formatted(Formatting.ITALIC));
        tooltip.add(new TranslatableText("tooltip_info_item.mcdar.blast_fungus_2").formatted(Formatting.ITALIC));
        tooltip.add(new TranslatableText("tooltip_info_item.mcdar.blast_fungus_3").formatted(Formatting.ITALIC));
        tooltip.add(new TranslatableText("tooltip_info_item.mcdar.blast_fungus_4").formatted(Formatting.ITALIC));
    }
}
