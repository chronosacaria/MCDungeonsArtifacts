package chronosacaria.mcdar.artefacts;

import chronosacaria.mcdar.api.AOEHelper;
import chronosacaria.mcdar.api.AbilityHelper;
import chronosacaria.mcdar.api.CleanlinessHelper;
import chronosacaria.mcdar.api.EnchantmentHelper;
import chronosacaria.mcdar.enums.DefenciveArtefactID;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
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

            boolean bl = user.getHealth() < user.getMaxHealth();
            float healedAmount = bl ?
                    healAlly(user) : healMostInjuredAlly(user, 12);
            if (!user.isCreative()){
                if (healedAmount > 0) {
                    user.addExperience((int) (-healedAmount));
                    itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));
                    EnchantmentHelper.cooldownHelper(user, this, bl ? 20 : 60);
                }
            }
        }

        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        CleanlinessHelper.createLoreTTips(stack, tooltip);
    }

    public static float healMostInjuredAlly(LivingEntity healer, float distance) {
        List<LivingEntity> nearbyEntities = AOEHelper.getEntitiesByPredicate(healer, distance,
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
        float maxHealth = allyToBeHealed.getMaxHealth();
        float lostHealth = maxHealth - allyToBeHealed.getHealth();
        float healedAmount = Math.min(lostHealth, 0.2F * maxHealth);
        allyToBeHealed.heal(healedAmount);
        //addParticles((ServerWorld) world, mostInjuredAlly, ParticleTypes.HEART);
        return healedAmount;
    }
}
