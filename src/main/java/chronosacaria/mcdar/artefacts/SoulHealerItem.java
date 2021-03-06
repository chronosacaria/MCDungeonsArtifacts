package chronosacaria.mcdar.artefacts;

import chronosacaria.mcdar.api.AOEHelper;
import chronosacaria.mcdar.api.EnchantmentHelper;
import chronosacaria.mcdar.enums.DefenciveArtefactID;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class SoulHealerItem extends ArtefactDefenciveItem{
    public SoulHealerItem(DefenciveArtefactID artefactID) {
        super(artefactID);
    }

    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);

        if (user.totalExperience >= 20 || user.isCreative()){
            if ((user.getHealth() < user.getMaxHealth())){
                float currentHealth = user.getHealth();
                float maxHealth = user.getMaxHealth();
                float lostHealth = maxHealth - currentHealth;
                float healedAmount;
                if (lostHealth < (maxHealth * 0.20F)){
                    user.setHealth(currentHealth + lostHealth);
                    healedAmount = lostHealth;
                } else {
                    user.setHealth(currentHealth + (maxHealth * 0.20F));
                    healedAmount = (maxHealth * 0.20F);
                }
                if (!user.isCreative()){
                    user.addExperience((int)(-healedAmount));
                    itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));
                }
                user.getItemCooldownManager().set(this, 20);
            } else {
                float healedAmount = AOEHelper.healMostInjuredAlly(user, 12);
                if (healedAmount > 0){
                    if (!user.isCreative()){
                        user.addExperience((int)(-healedAmount));
                        itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));
                    }
                    EnchantmentHelper.cooldownHelper(user, this, 60);
                }
            }
        }

        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        tooltip.add(Text.translatable("tooltip_info_item.mcdar.soul_healer_1").formatted(Formatting.ITALIC));
        tooltip.add(Text.translatable("tooltip_info_item.mcdar.soul_healer_2").formatted(Formatting.ITALIC));
        tooltip.add(Text.translatable("tooltip_info_item.mcdar.soul_healer_3").formatted(Formatting.ITALIC));
        tooltip.add(Text.translatable("tooltip_info_item.mcdar.soul_healer_4").formatted(Formatting.ITALIC));
    }
}
