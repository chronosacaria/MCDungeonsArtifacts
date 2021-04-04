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

import static chronosacaria.mcdar.Mcdar.random;

public class AOEHelper {

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
                return healedAmount;
            } else {
                mostInjuredAlly.setHealth(currentHealth + (maxHealth * 0.20F));
                //addParticles((ServerWorld) world, mostInjuredAlly, ParticleTypes.HEART);
                healedAmount = (maxHealth * 0.20F);
                return healedAmount;
            }
        } else return 0;

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
        World world = user.getEntityWorld();

        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(user.getBlockPos()).expand(distance),
                (nearbyEntity) -> AbilityHelper.canApplyToEnemy(user, nearbyEntity));
        if (nearbyEntities.isEmpty()) return;
        if (limit > nearbyEntities.size()) limit = nearbyEntities.size();
        user.world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER,
                SoundCategory.WEATHER, 1.0F, 1.0F);
        user.world.playSound(null, user.getX(), user.getY(), user.getZ(),SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT,
                SoundCategory.WEATHER, 1.0F, 1.0F);
        for (int i = 0; i < limit; i++){
            if (nearbyEntities.size() >= i + 1){
                LivingEntity nearbyEntity = nearbyEntities.get(i);
                electrocute(user, nearbyEntity, damageAmount);
            }
        }
    }

    public static void poisonAndSlowNearbyEnemies(World world, PlayerEntity user){
        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(user.getBlockPos()).expand(5), (nearbyEntity) -> nearbyEntity != user && !AbilityHelper.isPetOfAttacker(user, nearbyEntity) && nearbyEntity.isAlive());
        for (LivingEntity nearbyEntity : nearbyEntities){
            StatusEffectInstance entangled = new StatusEffectInstance(StatusEffects.SLOWNESS, 140, 4);
            StatusEffectInstance poisoned = new StatusEffectInstance(StatusEffects.POISON, 140, 1);
            nearbyEntity.addStatusEffect(entangled);
            nearbyEntity.addStatusEffect(poisoned);
        }
    }

    public static void weakenAndMakeNearbyEnemiesVulnerable(World world, PlayerEntity user){
        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(user.getBlockPos()).expand(5), (nearbyEntity) -> nearbyEntity != user && !AbilityHelper.isPetOfAttacker(user, nearbyEntity) && nearbyEntity.isAlive());
        for (LivingEntity nearbyEntity : nearbyEntities){
            StatusEffectInstance weakness = new StatusEffectInstance(StatusEffects.WEAKNESS, 140, 140);
            StatusEffectInstance vulnerability = new StatusEffectInstance(StatusEffects.RESISTANCE, 140, -2);
            nearbyEntity.addStatusEffect(weakness);
            nearbyEntity.addStatusEffect(vulnerability);
        }
    }

    public static void causeMagicExplosionAttack(LivingEntity user, LivingEntity victim, float damageAmount,
                                                 float distance){
        World world = victim.getEntityWorld();
        DamageSource magicExplosion = DamageSource.explosion(user).setUsesMagic();

        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(user.getBlockPos()).expand(distance),
                (nearbyEntity) -> AbilityHelper.canApplyToEnemy(user, nearbyEntity));
        if (nearbyEntities.isEmpty()) return;
        for (LivingEntity nearbyEntity : nearbyEntities){
            nearbyEntity.damage(magicExplosion, damageAmount);
        }

    }

    public static void knockbackNearbyEnemies(World world, PlayerEntity user) {
        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(user.getBlockPos()).expand(5), (nearbyEntity) -> nearbyEntity != user && !AbilityHelper.isPetOfAttacker(user, nearbyEntity) && nearbyEntity.isAlive());

        for (LivingEntity nearbyEntity : nearbyEntities) {
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
        }
    }

    public static void stunNearbyEnemies(World world, PlayerEntity user) {
        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(user.getBlockPos()).expand(5), (nearbyEntity) -> nearbyEntity != user && !AbilityHelper.isPetOfAttacker(user, nearbyEntity) && nearbyEntity.isAlive());

        for (LivingEntity nearbyEntity : nearbyEntities) {

            StatusEffectInstance stunned = new StatusEffectInstance(StatusEffectInit.STUNNED, 100);
            StatusEffectInstance nausea = new StatusEffectInstance(StatusEffects.NAUSEA, 100);
            StatusEffectInstance slowness = new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 4);

            nearbyEntity.addStatusEffect(stunned);
            nearbyEntity.addStatusEffect(nausea);
            nearbyEntity.addStatusEffect(slowness);

        }
    }

    public static void updraftNearbyEnemies(World world, PlayerEntity user) {
        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(user.getBlockPos()).expand(5), (nearbyEntity) -> nearbyEntity != user && !AbilityHelper.isPetOfAttacker(user, nearbyEntity) && nearbyEntity.isAlive());

        for (LivingEntity nearbyEntity : nearbyEntities) {

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
