package chronosacaria.mcdar.goals;

import chronosacaria.mcdar.api.SummoningHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Tameable;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.mob.PathAwareEntity;

public class FollowSummonerGoal<T extends PathAwareEntity & Tameable> extends Goal {
    private final T summonedEntity;
    private final double speed;
    private final EntityNavigation navigation;
    private int countdownTicks;
    private final float maxDistance;
    private final float minDistance;

    public FollowSummonerGoal(T summonedEntity, double speed,
                                       EntityNavigation navigation, float maxDistance, float minDistance) {
        this.summonedEntity = summonedEntity;
        this.speed = speed;
        this.navigation = navigation;
        this.maxDistance = maxDistance;
        this.minDistance = minDistance;
    }

    @Override
    public boolean canStart() {
        LivingEntity livingEntity = this.summonedEntity.getOwner();

        if (livingEntity == null
                || livingEntity.isSpectator()
                || this.summonedEntity.squaredDistanceTo(livingEntity) < (double) (this.minDistance * this.minDistance))
            return false;
        return true;
    }

    @Override
    public boolean shouldContinue() {
        if (this.navigation.isIdle()) {
            return false;
        } else {
            return this.summonedEntity.squaredDistanceTo(this.summonedEntity.getOwner()) > (double) (this.maxDistance * this.maxDistance);
        }
    }

    public void tick() {
        this.summonedEntity.getLookControl().lookAt(this.summonedEntity.getOwner(), 10.0F,
                (float) this.summonedEntity.getMaxLookPitchChange());
        if (--this.countdownTicks <= 0) {
            this.countdownTicks = 10;
            if (!this.summonedEntity.hasVehicle()) {
                if (this.summonedEntity.squaredDistanceTo(this.summonedEntity.getOwner()) >= 144.0D) {
                    SummoningHelper.tryTeleport(this.summonedEntity, this.summonedEntity.getOwner());
                } else {
                    this.navigation.startMovingTo(this.summonedEntity.getOwner(), this.speed);
                }
            }
        }
    }

}
