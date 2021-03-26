package mcdar.chronosacaria.api;

import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;

public class AOECloudHelper {

    public static void spawnExplosionCloud(LivingEntity attacker, LivingEntity victim, float radius){
        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(victim.world, victim.getX(),
                victim.getY(), victim.getZ());
        areaEffectCloudEntity.setOwner(attacker);
        areaEffectCloudEntity.setParticleType(ParticleTypes.EXPLOSION);
        areaEffectCloudEntity.setRadius(radius);
        areaEffectCloudEntity.setDuration(0);
        attacker.world.spawnEntity(areaEffectCloudEntity);
    }

    public static void spawnRegenCloudAtPos(LivingEntity attacker, boolean arrow, BlockPos blockPos, int amplifier){
        int inGroundMitigator = arrow ? 1 : 0;
        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(attacker.world, blockPos.getX(),
                blockPos.getY() + inGroundMitigator, blockPos.getZ());
        areaEffectCloudEntity.setOwner(attacker);
        areaEffectCloudEntity.setRadius(5.0f);
        areaEffectCloudEntity.setRadiusOnUse(-0.5f);
        areaEffectCloudEntity.setWaitTime(10);
        areaEffectCloudEntity.setDuration(100);
        StatusEffectInstance regeneration = new StatusEffectInstance(StatusEffects.REGENERATION, 100, amplifier);
        areaEffectCloudEntity.addEffect(regeneration);
        attacker.world.spawnEntity(areaEffectCloudEntity);
    }
}
