package chronosacaria.mcdar.api;

import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;

public class AOECloudHelper {

    public static void spawnExplosionCloud(LivingEntity attacker, LivingEntity victim, float radius){
        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(victim.getWorld(), victim.getX(), victim.getY(), victim.getZ());
        areaEffectCloudEntity.setOwner(attacker);
        areaEffectCloudEntity.setParticleType(ParticleTypes.EXPLOSION);
        areaEffectCloudEntity.setRadius(radius);
        areaEffectCloudEntity.setDuration(0);
        attacker.getWorld().spawnEntity(areaEffectCloudEntity);
    }

    public static void spawnStatusEffectCloud(LivingEntity owner, BlockPos blockPos, int duration, StatusEffectInstance... statusEffectInstances) {
        AreaEffectCloudEntity aoeCloudEntity = new AreaEffectCloudEntity(owner.getWorld(), blockPos.getX(), blockPos.getY() + 1, blockPos.getZ());
        aoeCloudEntity.setOwner(owner);
        aoeCloudEntity.setRadius(5.0f);
        aoeCloudEntity.setRadiusOnUse(-0.5f);
        aoeCloudEntity.setWaitTime(10);
        aoeCloudEntity.setDuration(duration);
        for (StatusEffectInstance instance : statusEffectInstances)
            aoeCloudEntity.addEffect(instance);
        owner.getWorld().spawnEntity(aoeCloudEntity);
    }

}
