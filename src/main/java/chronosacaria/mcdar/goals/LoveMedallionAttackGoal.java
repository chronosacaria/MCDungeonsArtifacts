package chronosacaria.mcdar.goals;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;

public class LoveMedallionAttackGoal extends ActiveTargetGoal<LivingEntity> {

    public LoveMedallionAttackGoal(MobEntity mob) {
        super(mob, LivingEntity.class, 0, true, true, LoveMedallionAttackGoal::isMobNotPlayer);
    }

    private static boolean isMobNotPlayer(LivingEntity livingEntity) {
        return !(livingEntity instanceof PlayerEntity);
    }

    @Override
    public void start() {
        super.start();
        this.mob.setDespawnCounter(0);
    }

}