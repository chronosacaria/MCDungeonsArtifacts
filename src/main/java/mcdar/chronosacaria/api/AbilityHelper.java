package mcdar.chronosacaria.api;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;

public class AbilityHelper {
    public static boolean isPetOfAttacker(LivingEntity possibleOwner, LivingEntity possiblePet){
        if (possiblePet instanceof TameableEntity){
            TameableEntity pet = (TameableEntity) possiblePet;
            return pet.getOwner() == possibleOwner;
        }
        return false;
    }

}
