package chronosacaria.mcdar.api;

import chronosacaria.mcdar.api.interfaces.Summonable;
import chronosacaria.mcdar.init.SummonedEntityRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SummoningHelper {

    public static final EntityType<SheepEntity>[] SHEEP = new EntityType[]{
            SummonedEntityRegistry.ENCHANTED_GRASS_GREEN_SHEEP_ENTITY,
            SummonedEntityRegistry.ENCHANTED_GRASS_BLUE_SHEEP_ENTITY,
            SummonedEntityRegistry.ENCHANTED_GRASS_RED_SHEEP_ENTITY
    };

    public static void summonedSheepEffect(LivingEntity sheep, int effectInt) {
        switch (effectInt) {
            case 0 -> AOEHelper.afflictNearbyEntities(MobEntity.class, sheep, 5,
                    (nearbyEntity) -> nearbyEntity != sheep && nearbyEntity.isAlive(), new StatusEffectInstance(StatusEffects.POISON, 100, 4));
            case 1 -> AOEHelper.afflictNearbyEntities(PlayerEntity.class, sheep, 10,
                    LivingEntity::isAlive, new StatusEffectInstance(StatusEffects.SPEED, 600, 2));
            case 2 -> {
                for (LivingEntity nearbyEntity : AOEHelper.getEntitiesByPredicate(MobEntity.class, sheep, 5,
                        (nearbyEntity) -> nearbyEntity != sheep && nearbyEntity.isAlive())) {
                    nearbyEntity.setOnFireFor(100);
                }
            }
            default -> {}
        }
    }

    public static boolean summonSummonableEntity(LivingEntity entityToSpawn, LivingEntity summoner, BlockPos blockPos) {
        World world = summoner.getWorld();

        if (entityToSpawn instanceof Summonable summonableEntity) {
            try {
                summonableEntity.setSummoner(summoner);
                entityToSpawn.refreshPositionAndAngles(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ(), 0, 0);
                world.spawnEntity(entityToSpawn);
                return true;
            } catch (RuntimeException exception) {
                return false;
            }
        }
        return false;
    }

}
