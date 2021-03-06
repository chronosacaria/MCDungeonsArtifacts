package chronosacaria.mcdar.api;

import chronosacaria.mcdar.damagesource.ElectricShockDamageSource;
import chronosacaria.mcdar.init.StatusEffectInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

import static chronosacaria.mcdar.Mcdar.random;

public class AOEHelper {

    /** Returns targets of an AOE effect from 'attacker' around 'center'. This includes 'center'. */
    public static List<LivingEntity> getAoeTargets(LivingEntity center, LivingEntity attacker, float distance) {
        return center.getEntityWorld().getEntitiesByClass(LivingEntity.class,
                new Box(center.getBlockPos()).expand(distance),
                (nearbyEntity) -> AbilityHelper.isAoeTarget(nearbyEntity, attacker, center)
        );
    }

    /** Returns targets of an AOE effect from 'attacker' around 'center'. This includes 'center'. */
    public static List<LivingEntity> getSummonedMobs(LivingEntity center, float distance) {
        return center.getEntityWorld().getEntitiesByClass(LivingEntity.class,
                new Box(center.getBlockPos()).expand(distance),
                (nearbyEntity) -> AbilityHelper.isPetOf(nearbyEntity, center)
        );
    }

    /* Returns targets of an AOE effect excluding the 'user' and any 'pets' of the 'user' */
    public static List<LivingEntity> excludeUserAndPetsOfUser(LivingEntity user) {
        return user.getEntityWorld().getEntitiesByClass(LivingEntity.class,
                new Box(user.getBlockPos()).expand(5),
                (nearbyEntity) -> nearbyEntity != user && !AbilityHelper.isPetOf(nearbyEntity, user) && nearbyEntity.isAlive());
    }


    public static float healMostInjuredAlly(LivingEntity healer, float distance){
        World world = healer.getEntityWorld();
        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(healer.getBlockPos()).expand(distance),
                (nearbyEntity) -> AbilityHelper.canHealEntity(healer, nearbyEntity));
        if (!nearbyEntities.isEmpty()){
            nearbyEntities.sort((o1, o2) -> {
                float o1LostHealth = o1.getMaxHealth() - o1.getHealth();
                float o2LostHealth = o2.getMaxHealth() - o2.getHealth();
                return Float.compare(o1LostHealth, o2LostHealth);
            });
            LivingEntity mostInjuredAlly = nearbyEntities.get(nearbyEntities.size() - 1);
            float currentHealth = mostInjuredAlly.getHealth();
            float maxHealth = mostInjuredAlly.getMaxHealth();
            float lostHealth = maxHealth - currentHealth;
            float healedAmount;
            if (lostHealth < (maxHealth * 0.20F)){
                mostInjuredAlly.setHealth(currentHealth + lostHealth);
                //addParticles((ServerWorld) world, mostInjuredAlly, ParticleTypes.HEART);
                healedAmount = lostHealth;
            } else {
                mostInjuredAlly.setHealth(currentHealth + (maxHealth * 0.20F));
                //addParticles((ServerWorld) world, mostInjuredAlly, ParticleTypes.HEART);
                healedAmount = (maxHealth * 0.20F);
            }
            return healedAmount;
        } else return 0;

    }

    public static void causeExplosion(LivingEntity user, LivingEntity target, float damageAmount, float distance) {
        for (LivingEntity nearbyEntity : getAoeTargets(target, user, distance)) {
            nearbyEntity.damage(DamageSource.explosion(user), damageAmount);
        }
    }

    public static void causeBlastFungusExplosions(LivingEntity user, float distance, float damageAmount){
        World world = user.getEntityWorld();
        DamageSource explosion = DamageSource.explosion(user);

        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(user.getBlockPos()).expand(distance),
                (nearbyEntity) -> AbilityHelper.isAoeTarget(nearbyEntity, user, nearbyEntity));
        if (nearbyEntities.isEmpty()) return;
        for (LivingEntity nearbyEntity : nearbyEntities) {
            if (nearbyEntity == null) return;
            if (nearbyEntity instanceof PlayerEntity && ((PlayerEntity) nearbyEntity).getAbilities().creativeMode) return;

            AOECloudHelper.spawnExplosionCloud(user, nearbyEntity, 3);
            nearbyEntity.damage(explosion, damageAmount);
        }
    }

    public static void summonLightningBoltOnEntity(Entity target){
        World world = target.getEntityWorld();
        LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(world);
        if (lightningEntity != null){
            lightningEntity.refreshPositionAfterTeleport(target.getX(), target.getY(), target.getZ());
            lightningEntity.setCosmetic(true);
            world.spawnEntity(lightningEntity);
        }
    }

    public static void electrocute(LivingEntity attacker, LivingEntity victim, float damageAmount){
        summonLightningBoltOnEntity(victim);
        ElectricShockDamageSource electricShockDamageSource = (ElectricShockDamageSource) new ElectricShockDamageSource(attacker).setUsesMagic();
        victim.damage(electricShockDamageSource, damageAmount);
    }

    public static void electrocuteNearbyEnemies(LivingEntity user, float distance, float damageAmount, int limit){
        user.world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER,
                SoundCategory.WEATHER, 1.0F, 1.0F);
        user.world.playSound(null, user.getX(), user.getY(), user.getZ(),SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT,
                SoundCategory.WEATHER, 1.0F, 1.0F);

        for (LivingEntity nearbyEntity : getAoeTargets(user, user, distance)) {
            electrocute(user, nearbyEntity, damageAmount);

            limit--;
            if (limit <= 0) break;
        }
    }

    public static void enchantersTomeEffects(World world, PlayerEntity user) {
        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(user.getBlockPos()).expand(5),
                (nearbyEntity) -> AbilityHelper.isPetOf(nearbyEntity, user));

        Random random = new Random();
        int upperLimit = 3;
        int effectInt = random.nextInt(upperLimit);

        if (effectInt == 0){ // EXTRA DAMAGE
            for (LivingEntity nearbyEntity : nearbyEntities){
                StatusEffectInstance strength = new StatusEffectInstance(StatusEffects.STRENGTH, 100, 2);
                nearbyEntity.clearStatusEffects();
                nearbyEntity.addStatusEffect(strength);
            }
        }
        if (effectInt == 1) { // FAST ATTACK
            for (LivingEntity nearbyEntity : nearbyEntities){
                StatusEffectInstance haste = new StatusEffectInstance(StatusEffects.HASTE, 100, 2);
                nearbyEntity.clearStatusEffects();
                nearbyEntity.addStatusEffect(haste);
            }
        }
        if (effectInt == 2){ // EXTRA SPEED
            for (LivingEntity nearbyEntity : nearbyEntities){
                StatusEffectInstance speed = new StatusEffectInstance(StatusEffects.SPEED, 100, 2);
                nearbyEntity.clearStatusEffects();
                nearbyEntity.addStatusEffect(speed);
            }
        }
    }

    public static void poisonAndSlowNearbyEnemies(PlayerEntity user){
        for (LivingEntity nearbyEntity : excludeUserAndPetsOfUser(user)){
            StatusEffectInstance entangled = new StatusEffectInstance(StatusEffects.SLOWNESS, 140, 4);
            StatusEffectInstance poisoned = new StatusEffectInstance(StatusEffects.POISON, 140, 1);
            nearbyEntity.addStatusEffect(entangled);
            nearbyEntity.addStatusEffect(poisoned);
        }
    }

    public static void weakenAndMakeNearbyEnemiesVulnerable(PlayerEntity user){
        for (LivingEntity nearbyEntity : excludeUserAndPetsOfUser(user)){
            StatusEffectInstance weakness = new StatusEffectInstance(StatusEffects.WEAKNESS, 140, 140);
            StatusEffectInstance vulnerability = new StatusEffectInstance(StatusEffects.RESISTANCE, 140, -2);
            nearbyEntity.addStatusEffect(weakness);
            nearbyEntity.addStatusEffect(vulnerability);
        }
    }

    public static void causeMagicExplosionAttack(LivingEntity user, LivingEntity victim, float damageAmount, float distance){
        DamageSource magicExplosion = DamageSource.explosion(user).setUsesMagic();

        for (LivingEntity nearbyEntity : getAoeTargets(victim, user, distance)) {
            nearbyEntity.damage(magicExplosion, damageAmount);
        }

    }

    public static void causeBeastBurstExplosionAttack(LivingEntity user, LivingEntity victim, float damageAmount,
                                             float distance){
        DamageSource magicExplosion = DamageSource.explosion(user).setUsesMagic();

        for (LivingEntity nearbyEntity : getSummonedMobs(victim, distance)) {
            nearbyEntity.damage(magicExplosion, damageAmount);
        }

    }

    public static void knockbackNearbyEnemies(PlayerEntity user) {
        for (LivingEntity nearbyEntity : excludeUserAndPetsOfUser(user)){
            float knockbackMultiplier = 2.0F;
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
        }
    }

    public static void satchelOfElementsEffects(PlayerEntity user) {
        Random random = new Random();
        int upperLimit = 3;
        int effectInt = random.nextInt(upperLimit);

        if (effectInt == 0){ // BURNING
            for (LivingEntity nearbyEntity : excludeUserAndPetsOfUser(user)){
                nearbyEntity.setOnFireFor(3);
            }
        }
        if (effectInt == 1) { // FROZEN
            for (LivingEntity nearbyEntity : excludeUserAndPetsOfUser(user)){
                StatusEffectInstance stunned = new StatusEffectInstance(StatusEffectInit.STUNNED, 100);
                StatusEffectInstance nausea = new StatusEffectInstance(StatusEffects.NAUSEA, 100);
                StatusEffectInstance slowness = new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 4);

                nearbyEntity.addStatusEffect(stunned);
                nearbyEntity.addStatusEffect(nausea);
                nearbyEntity.addStatusEffect(slowness);
            }
        }
        if (effectInt == 2){ // LIGHTNING STRIKE
            for (LivingEntity nearbyEntity : excludeUserAndPetsOfUser(user)){
               summonLightningBoltOnEntity(nearbyEntity);
                ElectricShockDamageSource electricShockDamageSource = (ElectricShockDamageSource) new ElectricShockDamageSource(user).setUsesMagic();
                nearbyEntity.damage(electricShockDamageSource, 5.0f);
            }
        }
    }

    public static void stunNearbyEnemies(PlayerEntity user) {
        for (LivingEntity nearbyEntity : excludeUserAndPetsOfUser(user)){

            StatusEffectInstance stunned = new StatusEffectInstance(StatusEffectInit.STUNNED, 100);
            StatusEffectInstance nausea = new StatusEffectInstance(StatusEffects.NAUSEA, 100);
            StatusEffectInstance slowness = new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 4);

            nearbyEntity.addStatusEffect(stunned);
            nearbyEntity.addStatusEffect(nausea);
            nearbyEntity.addStatusEffect(slowness);

        }
    }

    public static void updraftNearbyEnemies(PlayerEntity user) {
        for (LivingEntity nearbyEntity : excludeUserAndPetsOfUser(user)){

            nearbyEntity.setVelocity(0.0D, 1.25D, 0.0D);
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
            double frontX = .5f * random.nextDouble();
            world.spawnParticles(particleEffect, startX + frontX, startY + random.nextDouble() * .5, startZ + .5f,
                    1,velX, velY, velZ, 0);

            double backX = .5f * random.nextDouble();
            world.spawnParticles(particleEffect, startX + backX, startY + random.nextDouble() * .5, startZ,1, velX, velY,
                    velZ,0);

            double leftZ = .5f * random.nextDouble();
            world.spawnParticles(particleEffect, startX, startY + random.nextDouble() * .5, startZ + leftZ,1, velX, velY,
                    velZ,0);

            double rightZ = .5f * random.nextDouble();
            world.spawnParticles(particleEffect, startX + .5f, startY + random.nextDouble() * .5, startZ + rightZ,1, velX,
                    velY, velZ,0);
        }
    }
}
