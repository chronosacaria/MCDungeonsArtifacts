package chronosacaria.mcdar.api;

import chronosacaria.mcdar.mixin.CreeperEntityAccessor;
import chronosacaria.mcdar.registries.StatusEffectInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class AOEHelper {

    /** Returns targets of an AOE effect from 'attacker' around 'center'. This includes 'center'. */
    public static List<LivingEntity> getEntitiesByPredicate(LivingEntity center, float distance, Predicate<? super LivingEntity> predicate) {
        return center.getEntityWorld().getEntitiesByClass(LivingEntity.class,
                new Box(center.getBlockPos()).expand(distance), predicate
        );
    }

    public static List<? extends LivingEntity> getEntitiesByPredicate(Class<? extends LivingEntity> entityType,
                                                                      LivingEntity center, float distance, Predicate<? super LivingEntity> predicate) {
        return center.getEntityWorld().getEntitiesByClass(entityType,
                new Box(center.getBlockPos()).expand(distance), predicate
        );
    }

    public static void afflictNearbyEntities(LivingEntity user, StatusEffectInstance... statusEffectInstances) {
        for (LivingEntity nearbyEntity : getEntitiesByPredicate(user, 5,
                (nearbyEntity) -> nearbyEntity != user && !AbilityHelper.isPetOf(nearbyEntity, user) && nearbyEntity.isAlive())){
            for (StatusEffectInstance instance : statusEffectInstances)
                nearbyEntity.addStatusEffect(instance);
        }
    }

    public static void afflictNearbyEntities(Class<? extends LivingEntity> entityType, LivingEntity user, float distance,
                                             Predicate<? super LivingEntity> predicate, StatusEffectInstance... statusEffectInstances) {
        for (LivingEntity nearbyEntity : getEntitiesByPredicate(entityType, user, distance, predicate)) {
            for (StatusEffectInstance instance : statusEffectInstances)
                nearbyEntity.addStatusEffect(instance);
        }
    }

    public static void affectNearbyEntities(LivingEntity user, Consumer<LivingEntity> method) {
        for (LivingEntity nearbyEntity : getEntitiesByPredicate(user, 5,
                (nearbyEntity) -> nearbyEntity != user && !AbilityHelper.isPetOf(nearbyEntity, user) && nearbyEntity.isAlive())){
            method.accept(nearbyEntity);
        }
    }

    public static void affectNearbyEntities(LivingEntity user, float distance,
                                            Predicate<? super LivingEntity> predicate, Consumer<LivingEntity> method) {
        for (LivingEntity nearbyEntity : getEntitiesByPredicate(user, distance, predicate)) {
            method.accept(nearbyEntity);
        }
    }

    public static void affectNearbyEntities(Class<? extends LivingEntity> entityType, LivingEntity user, float distance,
                                             Predicate<? super LivingEntity> predicate, Consumer<LivingEntity> method) {
        for (LivingEntity nearbyEntity : getEntitiesByPredicate(entityType, user, distance, predicate)) {
            method.accept(nearbyEntity);
        }
    }

    public static void summonLightningBoltOnEntity(Entity target){
        TrackedData<Boolean> charged = CreeperEntityAccessor.getCHARGED();
        World world = target.getEntityWorld();
        LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(world);
        if (lightningEntity != null) {
            lightningEntity.refreshPositionAfterTeleport(target.getX(), target.getY(), target.getZ());
            lightningEntity.setCosmetic(true);
            if (target instanceof CreeperEntity creeperEntity) {
                creeperEntity.getDataTracker().set(charged, true);
            }
            world.spawnEntity(lightningEntity);
        }
    }

    public static void electrocute(LivingEntity victim, float damageAmount){
        summonLightningBoltOnEntity(victim);
        victim.damage(victim.getWorld().getDamageSources().lightningBolt(), damageAmount);
    }

    public static void electrocuteNearbyEnemies(LivingEntity user, float distance, float damageAmount, int limit){
        CleanlinessHelper.playCenteredSound(user, SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, SoundCategory.WEATHER, 1.0F, 1.0F);
        CleanlinessHelper.playCenteredSound(user, SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, SoundCategory.WEATHER, 1.0F, 1.0F);

        for (LivingEntity nearbyEntity : getEntitiesByPredicate(user, distance,
                (nearbyEntity) -> AbilityHelper.isAoeTarget(nearbyEntity, user, user))) {
            electrocute(nearbyEntity, damageAmount);

            limit--;
            if (limit <= 0) break;
        }
    }

    public static void causeExplosion(LivingEntity user, LivingEntity target, float damageAmount, float distance){
        for (LivingEntity nearbyEntity : getEntitiesByPredicate(target, distance,
                (nearbyEntity) -> AbilityHelper.isAoeTarget(nearbyEntity, user, target))) {
            nearbyEntity.damage(nearbyEntity.getWorld().getDamageSources().explosion(target, user), damageAmount);
        }

    }

    public static void knockbackNearbyEnemies(PlayerEntity user, LivingEntity nearbyEntity, float knockbackMultiplier) {
        double xRatio = user.getX() - nearbyEntity.getX();
        double zRatio;
        for (
                zRatio = user.getZ() - nearbyEntity.getZ();
                xRatio * xRatio + zRatio < 1.0E-4D;
                zRatio = (Math.random() - Math.random()) * 0.01D) {
            xRatio = (Math.random() - Math.random()) * 0.01D;
        }
        nearbyEntity.takeKnockback(0.4F * knockbackMultiplier, xRatio, zRatio);
    }

    public static void satchelOfElementsEffects(PlayerEntity user) {
        int effectInt = (new Random()).nextInt(3);

        if (effectInt == 0){ // BURNING
            for (LivingEntity nearbyEntity : getEntitiesByPredicate(user, 5,
                    (nearbyEntity) -> nearbyEntity != user && !AbilityHelper.isPetOf(nearbyEntity, user) && nearbyEntity.isAlive())){
                nearbyEntity.setOnFireFor(3);
            }
        }
        if (effectInt == 1) { // FROZEN
            afflictNearbyEntities(user, new StatusEffectInstance(StatusEffectInit.STUNNED, 100),
                    new StatusEffectInstance(StatusEffects.NAUSEA, 100),
                    new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 4));
        }
        if (effectInt == 2){ // LIGHTNING STRIKE
            for (LivingEntity nearbyEntity : getEntitiesByPredicate(user, 5,
                    (nearbyEntity) -> nearbyEntity != user && !AbilityHelper.isPetOf(nearbyEntity, user) && nearbyEntity.isAlive())){
                summonLightningBoltOnEntity(nearbyEntity);
                nearbyEntity.damage(nearbyEntity.getWorld().getDamageSources().lightningBolt(), 5.0f);
            }
        }
    }

    private static void addParticles(ServerWorld world, LivingEntity nearbyEntity, ParticleEffect particleEffect) {

        double velX = 0;
        double velY = 1;
        double velZ = 0;

        double startX = nearbyEntity.getX() - .275f;
        double startY = nearbyEntity.getY();
        double startZ = nearbyEntity.getZ() - .275f;

        for (int i = 0; i < 10; i++) {
            double frontX = .5f * world.getRandom().nextDouble();
            world.spawnParticles(particleEffect, startX + frontX, startY + world.getRandom().nextDouble() * .5, startZ + .5f,
                    1,velX, velY, velZ, 0);

            double backX = .5f * world.getRandom().nextDouble();
            world.spawnParticles(particleEffect, startX + backX, startY + world.getRandom().nextDouble() * .5, startZ,1, velX, velY,
                    velZ,0);

            double leftZ = .5f * world.getRandom().nextDouble();
            world.spawnParticles(particleEffect, startX, startY + world.getRandom().nextDouble() * .5, startZ + leftZ,1, velX, velY,
                    velZ,0);

            double rightZ = .5f * world.getRandom().nextDouble();
            world.spawnParticles(particleEffect, startX + .5f, startY + world.getRandom().nextDouble() * .5, startZ + rightZ,1, velX,
                    velY, velZ,0);
        }
    }
}
