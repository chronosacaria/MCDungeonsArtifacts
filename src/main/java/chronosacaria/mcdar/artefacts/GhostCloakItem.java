package chronosacaria.mcdar.artefacts;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.config.McdarConfig;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.*;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.List;

public class GhostCloakItem extends ArtefactAgilityItem{
    public GhostCloakItem(Settings settings, String id) {
        super(settings);
        if (McdarConfig.config.enableGhostCloak) {
            Registry.register(Registry.ITEM, new Identifier(Mcdar.MOD_ID, id), this);
        }    }

    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);

        StatusEffectInstance invisibility = new StatusEffectInstance(StatusEffects.INVISIBILITY, 60);
        StatusEffectInstance glowing = new StatusEffectInstance(StatusEffects.GLOWING, 60);
        StatusEffectInstance resistance = new StatusEffectInstance(StatusEffects.RESISTANCE, 60, 3);
        StatusEffectInstance swiftness = new StatusEffectInstance(StatusEffects.SPEED, 60);

        user.addStatusEffect(invisibility);
        user.addStatusEffect(glowing);
        user.addStatusEffect(resistance);
        user.addStatusEffect(swiftness);

        if (!user.isCreative()){
            itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));
        }
        user.getItemCooldownManager().set(this, 120);
        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        tooltip.add(new TranslatableText("tooltip_info_item.mcdar.ghost_cloak_1").formatted(Formatting.ITALIC));
        tooltip.add(new TranslatableText("tooltip_info_item.mcdar.ghost_cloak_2").formatted(Formatting.ITALIC));
        tooltip.add(new TranslatableText("tooltip_info_item.mcdar.ghost_cloak_3").formatted(Formatting.ITALIC));
    }
}
