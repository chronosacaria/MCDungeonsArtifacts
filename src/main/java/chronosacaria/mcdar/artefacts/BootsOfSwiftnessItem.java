package chronosacaria.mcdar.artefacts;

import chronosacaria.mcdar.enchants.EnchantID;
import chronosacaria.mcdar.enums.AgilityArtefactID;
import chronosacaria.mcdar.init.EnchantsRegistry;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.*;
import net.minecraft.world.World;
import org.lwjgl.system.CallbackI;

import java.util.List;

public class BootsOfSwiftnessItem extends ArtefactAgilityItem{
    public BootsOfSwiftnessItem(AgilityArtefactID artefactID) {
        super(artefactID);
    }

    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);

        StatusEffectInstance swiftness = new StatusEffectInstance(StatusEffects.SPEED, 40, 2);
        user.addStatusEffect(swiftness);
        if (!user.isCreative()){
            itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));
        }
        int cooldownLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(EnchantID.COOLDOWN),
                user);
        if (cooldownLevel > 0) {
            user.getItemCooldownManager().set(this, (int) ((cooldownLevel * 0.1) * 100));
        } else {
            user.getItemCooldownManager().set(this, 100);
        }
        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        tooltip.add(new TranslatableText("tooltip_info_item.mcdar.boots_of_swiftness_1").formatted(Formatting.ITALIC));
        tooltip.add(new TranslatableText("tooltip_info_item.mcdar.boots_of_swiftness_2").formatted(Formatting.ITALIC));
        tooltip.add(new TranslatableText("tooltip_info_item.mcdar.boots_of_swiftness_3").formatted(Formatting.ITALIC));
    }
}
