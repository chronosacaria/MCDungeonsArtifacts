package chronosacaria.mcdar.artefacts;

import chronosacaria.mcdar.api.AOEHelper;
import chronosacaria.mcdar.api.AbilityHelper;
import chronosacaria.mcdar.api.EnchantmentHelper;
import chronosacaria.mcdar.enums.DefenciveArtefactID;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
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
                float healedAmount = healAlly(user);
                if (!user.isCreative()){
                    user.addExperience((int)(-healedAmount));
                    itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));
                }
                user.getItemCooldownManager().set(this, 20);
            } else {
                float healedAmount = healMostInjuredAlly(user, 12);
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

    public static float healMostInjuredAlly(LivingEntity healer, float distance) {
        List<LivingEntity> nearbyEntities = AOEHelper.getLivingEntitiesByPredicate(healer, distance,
                (nearbyEntity) -> AbilityHelper.canHealEntity(healer, nearbyEntity));
        if (!nearbyEntities.isEmpty()) {
            nearbyEntities.sort((o1, o2) -> {
                float o1LostHealth = o1.getMaxHealth() - o1.getHealth();
                float o2LostHealth = o2.getMaxHealth() - o2.getHealth();
                return Float.compare(o1LostHealth, o2LostHealth);
            });
            LivingEntity mostInjuredAlly = nearbyEntities.get(nearbyEntities.size() - 1);
            return healAlly(mostInjuredAlly);
        } else
            return 0;
    }
    public static float healAlly(LivingEntity allyToBeHealed) {
        float currentHealth = allyToBeHealed.getHealth();
        float maxHealth = allyToBeHealed.getMaxHealth();
        float lostHealth = maxHealth - currentHealth;
        float healedAmount;
        if (lostHealth < (maxHealth * 0.20F)){
            allyToBeHealed.setHealth(maxHealth);
            //addParticles((ServerWorld) world, mostInjuredAlly, ParticleTypes.HEART);
            healedAmount = lostHealth;
        } else {
            allyToBeHealed.setHealth(currentHealth + (maxHealth * 0.20F));
            //addParticles((ServerWorld) world, mostInjuredAlly, ParticleTypes.HEART);
            healedAmount = (maxHealth * 0.20F);
        }
        return healedAmount;
    }
}
