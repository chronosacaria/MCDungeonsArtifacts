package chronosacaria.mcdar.goals;

import chronosacaria.mcdar.api.SummoningHelper;
import chronosacaria.mcdar.entities.WonderfulWheatLlamaEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.EntityNavigation;

public class FollowLlamaSummonerGoal extends Goal {

    private final WonderfulWheatLlamaEntity wonderfulWheatLlamaEntity;

    private LivingEntity summoner;
    private final double speed;
    private final EntityNavigation navigation;
    private int countdownTicks;
    private final float maxDistance;
    private final float minDistance;

    public FollowLlamaSummonerGoal(WonderfulWheatLlamaEntity wonderfulWheatLlamaEntity, LivingEntity summoner,
                                   double speed, EntityNavigation navigation, float maxDistance, float minDistance) {
        this.wonderfulWheatLlamaEntity = wonderfulWheatLlamaEntity;
        this.summoner = summoner;
        this.speed = speed;
        this.navigation = navigation;
        this.maxDistance = maxDistance;
        this.minDistance = minDistance;
    }

    @Override
    public boolean canStart() {
        LivingEntity livingEntity = this.wonderfulWheatLlamaEntity.getSummoner();

        if (livingEntity == null || livingEntity.isSpectator() || this.wonderfulWheatLlamaEntity.squaredDistanceTo(livingEntity) < (double) (this.minDistance * this.minDistance))
            return false;
        this.summoner = livingEntity;
        return true;
    }

    @Override
    public boolean shouldContinue() {
        if (this.navigation.isIdle()) {
            return false;
        } else {
            return this.wonderfulWheatLlamaEntity.squaredDistanceTo(this.summoner) > (double) (this.maxDistance * this.maxDistance);
        }
    }

    public void tick() {
        this.wonderfulWheatLlamaEntity.getLookControl().lookAt(this.summoner, 10.0F, (float) this.wonderfulWheatLlamaEntity.getMaxLookPitchChange());
        if (--this.countdownTicks <= 0) {
            this.countdownTicks = 10;
            if (!this.wonderfulWheatLlamaEntity.hasVehicle()) {
                if (this.wonderfulWheatLlamaEntity.squaredDistanceTo(this.summoner) >= 144.0D) {
                    SummoningHelper.tryTeleport(this.wonderfulWheatLlamaEntity, summoner);
                } else {
                    this.navigation.startMovingTo(this.summoner, this.speed);
                }
            }
        }
    }
}
