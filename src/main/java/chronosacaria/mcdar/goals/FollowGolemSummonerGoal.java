package chronosacaria.mcdar.goals;

import chronosacaria.mcdar.api.SummoningHelper;
import chronosacaria.mcdar.entities.GolemKitGolemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.EntityNavigation;

public class FollowGolemSummonerGoal extends Goal {

    private final GolemKitGolemEntity golemKitGolemEntity;

    private LivingEntity summoner;
    private final double speed;
    private final EntityNavigation navigation;
    private int countdownTicks;
    private final float maxDistance;
    private final float minDistance;

    public FollowGolemSummonerGoal(GolemKitGolemEntity golemKitGolemEntity, LivingEntity summoner, double speed,
                                   EntityNavigation navigation, float maxDistance, float minDistance){
            this.golemKitGolemEntity = golemKitGolemEntity;
            this.summoner = summoner;
        this.speed = speed;
            this.navigation = navigation;
            this.maxDistance = maxDistance;
            this.minDistance = minDistance;
    }

    @Override
    public boolean canStart() {
        LivingEntity livingEntity = this.golemKitGolemEntity.getSummoner();

        if (livingEntity == null || livingEntity.isSpectator() || this.golemKitGolemEntity.squaredDistanceTo(livingEntity) < (double) (this.minDistance * this.minDistance))
            return false;
        this.summoner = livingEntity;
        return true;
    }

    @Override
    public boolean shouldContinue(){
        if (this.navigation.isIdle())
            return false;
        return this.golemKitGolemEntity.squaredDistanceTo(this.summoner) > (double) (this.maxDistance * this.maxDistance);
    }

    public void tick(){
        this.golemKitGolemEntity.getLookControl().lookAt(this.summoner,10.0F, (float) this.golemKitGolemEntity.getMaxLookPitchChange());
        if (--this.countdownTicks <= 0){
            this.countdownTicks = 10;
            if (!this.golemKitGolemEntity.hasVehicle()){
                if (this.golemKitGolemEntity.squaredDistanceTo(this.summoner) >= 144.0D) {
                    SummoningHelper.tryTeleport(this.golemKitGolemEntity, summoner);
                } else {
                    this.navigation.startMovingTo(this.summoner,this.speed);
                }
            }
        }
    }
}
