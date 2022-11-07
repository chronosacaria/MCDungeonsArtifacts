package chronosacaria.mcdar.goals;

import chronosacaria.mcdar.api.SummoningHelper;
import chronosacaria.mcdar.entities.TastyBoneWolfEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.EntityNavigation;

public class FollowWolfSummonerGoal extends Goal {

    private final TastyBoneWolfEntity tastyBoneWolfEntity;

    private LivingEntity summoner;
    private final double speed;
    private final EntityNavigation navigation;
    private int countdownTicks;
    private final float maxDistance;
    private final float minDistance;

    public FollowWolfSummonerGoal(TastyBoneWolfEntity tastyBoneWolfEntity, LivingEntity summoner, double speed, EntityNavigation navigation, float maxDistance, float minDistance) {
        this.tastyBoneWolfEntity = tastyBoneWolfEntity;
        this.summoner = summoner;
        this.speed = speed;
        this.navigation = navigation;
        this.maxDistance = maxDistance;
        this.minDistance = minDistance;
    }

    @Override
    public boolean canStart() {
        LivingEntity livingEntity = this.tastyBoneWolfEntity.getSummoner();

        if (livingEntity == null) {
            return false;
        } else if (livingEntity.isSpectator()) {
            return false;
        } else if (this.tastyBoneWolfEntity.squaredDistanceTo(livingEntity) < (double) (this.minDistance * this.minDistance)) {
            return false;
        } else {
            this.summoner = livingEntity;
            return true;
        }
    }

    @Override
    public boolean shouldContinue() {
        if (this.navigation.isIdle()) {
            return false;
        } else {
            return this.tastyBoneWolfEntity.squaredDistanceTo(this.summoner) > (double) (this.maxDistance * this.maxDistance);
        }
    }

    public void tick() {
        this.tastyBoneWolfEntity.getLookControl().lookAt(this.summoner, 10.0F, (float) this.tastyBoneWolfEntity.getMaxLookPitchChange());
        if (--this.countdownTicks <= 0) {
            this.countdownTicks = 10;
            if (!this.tastyBoneWolfEntity.hasVehicle()) {
                if (this.tastyBoneWolfEntity.squaredDistanceTo(this.summoner) >= 144.0D) {
                    SummoningHelper.tryTeleport(this.tastyBoneWolfEntity, summoner);
                } else {
                    this.navigation.startMovingTo(this.summoner, this.speed);
                }
            }
        }
    }
}
