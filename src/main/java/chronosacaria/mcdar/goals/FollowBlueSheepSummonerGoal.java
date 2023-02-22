package chronosacaria.mcdar.goals;

import chronosacaria.mcdar.api.SummoningHelper;
import chronosacaria.mcdar.entities.EnchantedGrassBlueSheepEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.EntityNavigation;

public class FollowBlueSheepSummonerGoal extends Goal {

    private final EnchantedGrassBlueSheepEntity enchantedGrassBlueSheepEntity;

    private LivingEntity summoner;
    private final double speed;
    private final EntityNavigation navigation;
    private int countdownTicks;
    private final float maxDistance;
    private final float minDistance;

    public FollowBlueSheepSummonerGoal(EnchantedGrassBlueSheepEntity enchantedGrassBlueSheepEntity, LivingEntity summoner, double speed,
                                       EntityNavigation navigation, float maxDistance, float minDistance) {
        this.enchantedGrassBlueSheepEntity = enchantedGrassBlueSheepEntity;
        this.summoner = summoner;
        this.speed = speed;
        this.navigation = navigation;
        this.maxDistance = maxDistance;
        this.minDistance = minDistance;
    }

    @Override
    public boolean canStart() {
        LivingEntity livingEntity = this.enchantedGrassBlueSheepEntity.getSummoner();

        if (livingEntity == null || livingEntity.isSpectator() || this.enchantedGrassBlueSheepEntity.squaredDistanceTo(livingEntity) < (double) (this.minDistance * this.minDistance))
            return false;
        this.summoner = livingEntity;
        return true;
    }

    @Override
    public boolean shouldContinue() {
        if (this.navigation.isIdle()) {
            return false;
        } else {
            return this.enchantedGrassBlueSheepEntity.squaredDistanceTo(this.summoner) > (double) (this.maxDistance * this.maxDistance);
        }
    }

    public void tick() {
        this.enchantedGrassBlueSheepEntity.getLookControl().lookAt(this.summoner, 10.0F,
                (float) this.enchantedGrassBlueSheepEntity.getMaxLookPitchChange());
        if (--this.countdownTicks <= 0) {
            this.countdownTicks = 10;
            if (!this.enchantedGrassBlueSheepEntity.hasVehicle()) {
                if (this.enchantedGrassBlueSheepEntity.squaredDistanceTo(this.summoner) >= 144.0D) {
                    SummoningHelper.tryTeleport(this.enchantedGrassBlueSheepEntity, summoner);
                } else {
                    this.navigation.startMovingTo(this.summoner, this.speed);
                }
            }
        }
    }
}
