package chronosacaria.mcdar.artefacts;

import chronosacaria.mcdar.enums.AgilityArtefactID;
import chronosacaria.mcdar.init.StatusEffectInit;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.*;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;

import static chronosacaria.mcdar.api.AbilityHelper.isPetOf;

public class LightFeatherItem extends ArtefactAgilityItem{
    public LightFeatherItem(AgilityArtefactID artefactID) {
        super(artefactID);
    }

    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);

        user.jump();

        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(user.getBlockPos()).expand(5), (nearbyEntity) -> nearbyEntity != user && !isPetOf(nearbyEntity,
                        user) && nearbyEntity.isAlive());

        for (LivingEntity nearbyEntity : nearbyEntities){
            float knockbackMultiplier = 5.0F;
            double xRatio = user.getX() - nearbyEntity.getX();
            double zRatio;
            for (
                    zRatio = user.getZ() - nearbyEntity.getZ();
                    xRatio * xRatio + zRatio < 1.0E-4D;
                    zRatio = (Math.random() - Math.random()) * 0.01D) {
                    xRatio = (Math.random() - Math.random()) * 0.01D;
            }
            nearbyEntity.knockbackVelocity =
                    (float) (MathHelper.atan2(zRatio, xRatio) * 57.2957763671875D - (double) nearbyEntity.getYaw());
            nearbyEntity.takeKnockback(0.4F * knockbackMultiplier, xRatio, zRatio);

            StatusEffectInstance stunned = new StatusEffectInstance(StatusEffectInit.STUNNED, 60);
            StatusEffectInstance nausea = new StatusEffectInstance(StatusEffects.NAUSEA, 60);
            StatusEffectInstance slowness = new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 4);
            nearbyEntity.addStatusEffect(stunned);
            nearbyEntity.addStatusEffect(nausea);
            nearbyEntity.addStatusEffect(slowness);
        }

        if (!user.isCreative()){
            itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));
        }
        user.getItemCooldownManager().set(this, 120);
        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        tooltip.add(new TranslatableText("tooltip_info_item.mcdar.light_feather_1").formatted(Formatting.ITALIC));
        tooltip.add(new TranslatableText("tooltip_info_item.mcdar.light_feather_2").formatted(Formatting.ITALIC));
        tooltip.add(new TranslatableText("tooltip_info_item.mcdar.light_feather_3").formatted(Formatting.ITALIC));
    }
}
