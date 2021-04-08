package chronosacaria.mcdar.api;

import chronosacaria.mcdar.entities.GolemKitGolemEntity;
import chronosacaria.mcdar.entities.TastyBoneWolfEntity;
import chronosacaria.mcdar.entities.WonderfulWheatLlamaEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.VillagerEntity;

public class AbilityHelper {

    private static boolean isAVillagerOrIronGolem(LivingEntity nearbyEntity){
        return (nearbyEntity instanceof VillagerEntity) || (nearbyEntity instanceof IronGolemEntity);
    }

    public static boolean isPetOfAttacker(LivingEntity possibleOwner, LivingEntity possiblePet){
        if (possiblePet instanceof GolemKitGolemEntity){
            GolemKitGolemEntity pet = (GolemKitGolemEntity) possiblePet;
            return pet.getSummoner() == possibleOwner;
        }
        if (possiblePet instanceof TastyBoneWolfEntity){
            TastyBoneWolfEntity pet = (TastyBoneWolfEntity) possiblePet;
            return pet.getSummoner() == possibleOwner;
        }
        if (possiblePet instanceof WonderfulWheatLlamaEntity){
            WonderfulWheatLlamaEntity pet = (WonderfulWheatLlamaEntity) possiblePet;
            return pet.getSummoner() == possibleOwner;
        }
        return false;
    }

    private static boolean isNotTheTargetOrAttacker(LivingEntity attacker, LivingEntity target,
                                                    LivingEntity nearbyEntity){
        return nearbyEntity != target && nearbyEntity != attacker;
    }

    public static boolean canHealEntity(LivingEntity healer, LivingEntity nearbyEntity){
        return nearbyEntity != healer && isAlly(healer, nearbyEntity) && isAliveAndCanBeSeen(nearbyEntity, healer);
    }

    private static boolean isAlly (LivingEntity healer, LivingEntity nearbyEntity){
        return isPetOfAttacker(healer, nearbyEntity)
                || isAVillagerOrIronGolem(nearbyEntity)
                || healer.isTeammate(nearbyEntity);
    }

    private static boolean isAliveAndCanBeSeen(LivingEntity nearbyEntity, LivingEntity attacker){
        return nearbyEntity.isAlive() && attacker.canSee(nearbyEntity);
    }

    public static boolean canApplyToEnemy(LivingEntity attacker, LivingEntity target, LivingEntity nearbyEntity){
        return isNotTheTargetOrAttacker(attacker, target, nearbyEntity)
                && isAliveAndCanBeSeen(nearbyEntity, attacker)
                && !isAlly(attacker, nearbyEntity);
    }

    public static boolean canApplyToEnemy(LivingEntity attacker, LivingEntity nearbyEntity){
        return nearbyEntity != attacker
                && isAliveAndCanBeSeen(nearbyEntity, attacker)
                && !isAlly(attacker, nearbyEntity);
    }

}
