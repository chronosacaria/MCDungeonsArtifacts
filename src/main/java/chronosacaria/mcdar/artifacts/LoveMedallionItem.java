package chronosacaria.mcdar.artifacts;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.api.AOEHelper;
import chronosacaria.mcdar.api.AbilityHelper;
import chronosacaria.mcdar.api.CleanlinessHelper;
import chronosacaria.mcdar.api.EnchantmentHelper;
import chronosacaria.mcdar.enums.StatusInflictingArtifactID;
import chronosacaria.mcdar.registries.StatusEffectInit;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.AmbientEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class LoveMedallionItem extends ArtifactStatusInflictingItem {
    public LoveMedallionItem() {
        super(
                StatusInflictingArtifactID.LOVE_MEDALLION,
                Mcdar.CONFIG.mcdarArtifactsStatsConfig.STATUS_INFLICTING_ARTIFACT_STATS
                        .get(StatusInflictingArtifactID.LOVE_MEDALLION).mcdar$getDurability()
        );
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);

        int i = 0;

        for (LivingEntity entitiesByPredicate : AOEHelper.getEntitiesByPredicate(MobEntity.class, user, 6,
                (nearbyEntity) -> AbilityHelper.isAoeTarget(nearbyEntity, user, user))) {
            sendIntoWildRage(entitiesByPredicate);
            i++;
            if (i >= 3)
                break;
        }

        if (!user.isCreative())
            itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));

        EnchantmentHelper.mcdar$cooldownHelper(
                user,
                this,
                Mcdar.CONFIG.mcdarArtifactsStatsConfig.STATUS_INFLICTING_ARTIFACT_STATS
                        .get(StatusInflictingArtifactID.LOVE_MEDALLION)
                        .mcdar$getMaxCooldownEnchantmentTime()
        );

        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }

    public static void sendIntoWildRage(LivingEntity mobEntity) {
        boolean bl = false;
        try {
            mobEntity.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        } catch (IllegalArgumentException e) {
            bl = true;
        }
        if (!(bl || mobEntity instanceof WitherEntity || mobEntity instanceof EnderDragonEntity || mobEntity instanceof AmbientEntity))
            mobEntity.addStatusEffect(new StatusEffectInstance(StatusEffectInit.CHARMED, 200, 0));
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        CleanlinessHelper.createLoreTTips(stack, tooltip);
    }

}

/*
3 nearby mobs give off heart particles, become allies for 10 seconds, then die

// Abridged
3 mobs within 6 radius are afflicted with wild rage for 10 seconds as is in mcdw
 */