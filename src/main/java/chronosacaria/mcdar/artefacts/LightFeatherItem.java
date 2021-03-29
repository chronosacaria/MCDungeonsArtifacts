package chronosacaria.mcdar.artefacts;

import chronosacaria.mcdar.Mcdar;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.List;

import static chronosacaria.mcdar.api.AbilityHelper.isPetOfAttacker;

public class LightFeatherItem extends ArtefactItem{
    public LightFeatherItem(Settings settings, String id) {
        super(settings);
        Registry.register(Registry.ITEM, new Identifier(Mcdar.MOD_ID, id), this);
    }

    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);

        user.jump();


        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(user.getBlockPos()).expand(5), (nearbyEntity) -> nearbyEntity != user && !isPetOfAttacker(user, nearbyEntity) && nearbyEntity.isAlive());

        for (LivingEntity nearbyEntity : nearbyEntities){
            float knockbackMultiplier = 1.0F;
            double xRatio = user.getX() - nearbyEntity.getX();
            double zRatio;
            for (
                    zRatio = user.getZ() - nearbyEntity.getZ();
                    xRatio * xRatio + zRatio < 1.0E-4D;
                    zRatio = (Math.random() - Math.random()) * 0.01D) {
                    xRatio = (Math.random() - Math.random()) * 0.01D;
            }
            nearbyEntity.knockbackVelocity = (float) (MathHelper.atan2(zRatio, xRatio) * 57.2957763671875D - (double) nearbyEntity.yaw);
            nearbyEntity.takeKnockback(0.4F * knockbackMultiplier, xRatio, zRatio);

            StatusEffectInstance nausea = new StatusEffectInstance(StatusEffects.NAUSEA, 60);
            StatusEffectInstance slowness = new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 4);
            nearbyEntity.addStatusEffect(nausea);
            nearbyEntity.addStatusEffect(slowness);
        }

        if (!user.isCreative()){
            itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));
        }
        user.getItemCooldownManager().set(this, 120);
        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }
}