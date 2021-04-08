package chronosacaria.mcdar.api;

import chronosacaria.mcdar.init.StatusEffectInit;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class AOECloudHelper {

    public static void spawnExplosionCloud(LivingEntity attacker, LivingEntity victim, float radius){
        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(victim.world, victim.getX(), victim.getY(), victim.getZ());
        areaEffectCloudEntity.setOwner(attacker);
        areaEffectCloudEntity.setParticleType(ParticleTypes.EXPLOSION);
        areaEffectCloudEntity.setRadius(radius);
        areaEffectCloudEntity.setDuration(0);
        attacker.world.spawnEntity(areaEffectCloudEntity);
    }

    public static void spawnRegenCloudAtPos(LivingEntity attacker, boolean arrow, BlockPos blockPos, int amplifier){
        int inGroundMitigator = arrow ? 1 : 0;
        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(attacker.world, blockPos.getX(), blockPos.getY() + inGroundMitigator, blockPos.getZ());
        areaEffectCloudEntity.setOwner(attacker);
        areaEffectCloudEntity.setRadius(5.0f);
        areaEffectCloudEntity.setRadiusOnUse(-0.5f);
        areaEffectCloudEntity.setWaitTime(10);
        areaEffectCloudEntity.setDuration(100);
        StatusEffectInstance regeneration = new StatusEffectInstance(StatusEffects.REGENERATION, 100, amplifier);
        areaEffectCloudEntity.addEffect(regeneration);
        attacker.world.spawnEntity(areaEffectCloudEntity);
    }

    public static void spawnEnchantersTomeCloudEffects(LivingEntity attacker, BlockPos blockPos, int amplifier) {

        Random random = new Random();
        int upperLimit = 3;
        int effectInt = random.nextInt(upperLimit);

        if (effectInt == 0){ // EXTRA DAMAGE
            AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(attacker.world, blockPos.getX(), blockPos.getY(), blockPos.getZ());
            areaEffectCloudEntity.setOwner(attacker);
            areaEffectCloudEntity.setRadius(5.0f);
            areaEffectCloudEntity.setRadiusOnUse(-0.5f);
            areaEffectCloudEntity.setWaitTime(10);
            areaEffectCloudEntity.setDuration(100);
            StatusEffectInstance strength = new StatusEffectInstance(StatusEffects.STRENGTH, 100, amplifier);
            areaEffectCloudEntity.addEffect(strength);
            attacker.world.spawnEntity(areaEffectCloudEntity);
        }
        if (effectInt == 1) { // FAST ATTACK
            AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(attacker.world, blockPos.getX(), blockPos.getY(), blockPos.getZ());
            areaEffectCloudEntity.setOwner(attacker);
            areaEffectCloudEntity.setRadius(5.0f);
            areaEffectCloudEntity.setRadiusOnUse(-0.5f);
            areaEffectCloudEntity.setWaitTime(10);
            areaEffectCloudEntity.setDuration(100);
            StatusEffectInstance haste = new StatusEffectInstance(StatusEffects.HASTE, 100, amplifier);
            areaEffectCloudEntity.addEffect(haste);
            attacker.world.spawnEntity(areaEffectCloudEntity);
        }
        if (effectInt == 2){ // EXTRA SPEED
            AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(attacker.world, blockPos.getX(), blockPos.getY(), blockPos.getZ());
            areaEffectCloudEntity.setOwner(attacker);
            areaEffectCloudEntity.setRadius(5.0f);
            areaEffectCloudEntity.setRadiusOnUse(-0.5f);
            areaEffectCloudEntity.setWaitTime(10);
            areaEffectCloudEntity.setDuration(100);
            StatusEffectInstance speed = new StatusEffectInstance(StatusEffects.SPEED, 100, amplifier);
            areaEffectCloudEntity.addEffect(speed);
            attacker.world.spawnEntity(areaEffectCloudEntity);
        }
    }

    //TODO Make Potion Effect Actually Apply from Cloud
    public static void spawnSoulProtectionCloudAtPos(LivingEntity attacker, BlockPos blockPos, int duration){
        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(attacker.world, blockPos.getX(), blockPos.getY(), blockPos.getZ());
        areaEffectCloudEntity.setOwner(attacker);
        areaEffectCloudEntity.setRadius(5.0F);
        areaEffectCloudEntity.setRadiusOnUse(-0.5F);
        areaEffectCloudEntity.setWaitTime(10);
        areaEffectCloudEntity.setDuration(duration);
        StatusEffectInstance soulProtection = new StatusEffectInstance(StatusEffectInit.SOUL_PROTECTION, duration);
        areaEffectCloudEntity.addEffect(soulProtection);
        attacker.world.spawnEntity(areaEffectCloudEntity);
    }
}
