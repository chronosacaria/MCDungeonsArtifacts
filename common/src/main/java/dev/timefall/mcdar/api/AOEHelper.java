package dev.timefall.mcdar.api;

import net.minecraft.entity.LivingEntity;

import java.util.function.Consumer;
import java.util.function.Function;

public class AOEHelper {

    @Deprecated
    public static Consumer<LivingEntity> causeExplosion(LivingEntity user, float damageAmount) {
        return causeExplosion(user, (t) -> damageAmount);
    }

    @Deprecated
    public static Consumer<LivingEntity> causeExplosion(LivingEntity user, Function<LivingEntity, Float> damageAmount) {
        return (target) -> target.damage(target.getWorld().getDamageSources().explosion(target, user), damageAmount.apply(target));
    }
}
