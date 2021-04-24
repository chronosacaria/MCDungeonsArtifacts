package chronosacaria.mcdar.api;

import chronosacaria.mcdar.entities.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;

public class AbilityHelper {

    public static boolean isPetOf(LivingEntity self, LivingEntity owner){
        if (self instanceof GolemKitGolemEntity){
            GolemKitGolemEntity pet = (GolemKitGolemEntity) self;
            return pet.getSummoner() == owner;
        }
        if (self instanceof TastyBoneWolfEntity){
            TastyBoneWolfEntity pet = (TastyBoneWolfEntity) self;
            return pet.getSummoner() == owner;
        }
        if (self instanceof WonderfulWheatLlamaEntity) {
            WonderfulWheatLlamaEntity pet = (WonderfulWheatLlamaEntity) self;
            return pet.getSummoner() == owner;
        }
        if (self instanceof EnchantedGrassRedSheepEntity) {
            EnchantedGrassRedSheepEntity pet = (EnchantedGrassRedSheepEntity) self;
            return pet.getSummoner() == owner;
        }
        if (self instanceof EnchantedGrassGreenSheepEntity) {
            EnchantedGrassGreenSheepEntity pet = (EnchantedGrassGreenSheepEntity) self;
            return pet.getSummoner() == owner;
        }
        if (self instanceof EnchantedGrassBlueSheepEntity) {
            EnchantedGrassBlueSheepEntity pet = (EnchantedGrassBlueSheepEntity) self;
            return pet.getSummoner() == owner;
        }
        return false;
    }

    private static boolean isVillagerOrIronGolem(LivingEntity nearbyEntity) {
        return (nearbyEntity instanceof VillagerEntity) || (nearbyEntity instanceof IronGolemEntity);
    }

    public static boolean canHealEntity(LivingEntity healer, LivingEntity nearbyEntity){
        return nearbyEntity != healer
                && isAllyOf(healer, nearbyEntity)
                && nearbyEntity.isAlive()
                && healer.canSee(nearbyEntity);
    }

    private static boolean isAllyOf(LivingEntity self, LivingEntity other) {
        return self.isTeammate(other)
                || isPetOf(self, other)
                || isVillagerOrIronGolem(other);
    }

    public static boolean isAoeTarget(LivingEntity self, LivingEntity attacker, LivingEntity center) {
        return self != attacker
                && self.isAlive()
                && !isAllyOf(attacker, self)
                && !isUnaffectedByAoe(self)
                && center.canSee(self);
    }

    private static boolean isUnaffectedByAoe(LivingEntity entity) {
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            if (player.isCreative()) return true;
            //return McdwEnchantsConfig.getValue("aoe_dont_affect_players");
        }

        return false;
    }
}
