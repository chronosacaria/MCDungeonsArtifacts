package mcdar.chronosacaria.api;

import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

import static mcdar.chronosacaria.Mcdar.random;

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
