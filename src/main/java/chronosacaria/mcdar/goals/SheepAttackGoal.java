package chronosacaria.mcdar.goals;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.passive.SheepEntity;

public class SheepAttackGoal extends MeleeAttackGoal {
    public SheepAttackGoal (SheepEntity sheepEntity) {
        super(sheepEntity, 1.4D, true);
    }

    protected double getSquaredMaxAttackDistance (LivingEntity entity){
        return 4.0F + entity.getWidth();
    }
}
