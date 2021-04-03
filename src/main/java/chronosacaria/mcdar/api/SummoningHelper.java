package chronosacaria.mcdar.api;

import chronosacaria.mcdar.entities.BuzzyNestBeeEntity;
import chronosacaria.mcdar.entities.GolemKitGolemEntity;
import chronosacaria.mcdar.entities.TastyBoneWolfEntity;
import chronosacaria.mcdar.init.SummonedEntityRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SummoningHelper {

    public static void summonBuzzyNestBee (LivingEntity entity, BlockPos blockPos){
        EntityType<BuzzyNestBeeEntity> bn_bee = SummonedEntityRegistry.BUZZY_NEST_BEE_ENTITY;

        World world = entity.getEntityWorld();

        BuzzyNestBeeEntity buzzyNestBeeEntity = bn_bee.create(world);
        assert buzzyNestBeeEntity != null;
        buzzyNestBeeEntity.setSummoner(entity);
        buzzyNestBeeEntity.refreshPositionAndAngles(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ(), 0, 0);
        world.spawnEntity(buzzyNestBeeEntity);
    }

    public static void summonGolemKitGolem (LivingEntity entity, BlockPos blockPos){
        EntityType<GolemKitGolemEntity> gk_golem = SummonedEntityRegistry.GOLEM_KIT_GOLEM_ENTITY;

        World world = entity.getEntityWorld();

        GolemKitGolemEntity golemKitGolemEntity = gk_golem.create(world);
        assert golemKitGolemEntity != null;
        golemKitGolemEntity.setSummoner(entity);
        golemKitGolemEntity.refreshPositionAndAngles(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ(), 0, 0);
        world.spawnEntity(golemKitGolemEntity);
    }

    public static void summonTastyBoneWolf (LivingEntity entity, BlockPos blockPos){
        EntityType<TastyBoneWolfEntity> tb_wolf = SummonedEntityRegistry.TASTY_BONE_WOLF_ENTITY;

        World world = entity.getEntityWorld();

        TastyBoneWolfEntity tastyBoneWolfEntity = tb_wolf.create(world);
        assert tastyBoneWolfEntity != null;
        tastyBoneWolfEntity.setSummoner(entity);
        tastyBoneWolfEntity.refreshPositionAndAngles(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ(), 0, 0);
        world.spawnEntity(tastyBoneWolfEntity);
    }
}
