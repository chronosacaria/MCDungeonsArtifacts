package chronosacaria.mcdar.api;

import chronosacaria.mcdar.damagesource.ElectricShockDamageSource;
import chronosacaria.mcdar.entities.*;
import chronosacaria.mcdar.init.StatusEffectInit;
import chronosacaria.mcdar.init.SummonedEntityRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

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

    public static void summonEnchantedGrassSheep (LivingEntity entity, BlockPos blockPos){
        Random random = new Random();
        int upperLimit = 3;
        int effectInt = random.nextInt(upperLimit);

        if (effectInt == 0){ // GREEN POISON SHEEP
            EntityType<EnchantedGrassGreenSheepEntity> eg_greenSheep = SummonedEntityRegistry.ENCHANTED_GRASS_GREEN_SHEEP_ENTITY;

            World world = entity.getEntityWorld();

            EnchantedGrassGreenSheepEntity enchantedGrassGreenSheepEntity = eg_greenSheep.create(world);
            assert enchantedGrassGreenSheepEntity != null;
            enchantedGrassGreenSheepEntity.setSummoner(entity);
            enchantedGrassGreenSheepEntity.refreshPositionAndAngles(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ(), 0, 0);
            world.spawnEntity(enchantedGrassGreenSheepEntity);
        }
        if (effectInt == 1) { // BLUE SPEED SHEEP

        }
        if (effectInt == 2) { // RED BURNING SHEEP

        }
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

    public static void summonWonderfulWheatLlama (LivingEntity entity, BlockPos blockPos){
        EntityType<WonderfulWheatLlamaEntity> ww_llama = SummonedEntityRegistry.WONDERFUL_WHEAT_LLAMA_ENTITY;

        World world = entity.getEntityWorld();

        WonderfulWheatLlamaEntity wonderfulWheatLlamaEntity = ww_llama.create(world);
        assert wonderfulWheatLlamaEntity != null;
        wonderfulWheatLlamaEntity.setSummoner(entity);
        wonderfulWheatLlamaEntity.refreshPositionAndAngles(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ(), 0, 0);
        world.spawnEntity(wonderfulWheatLlamaEntity);
    }
}
