package chronosacaria.mcdar.goals;

import chronosacaria.mcdar.api.SummoningHelper;
import chronosacaria.mcdar.entities.EnchantedGrassGreenSheepEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.EntityNavigation;

public class FollowGreenSheepSummonerGoal extends Goal {

    private final EnchantedGrassGreenSheepEntity enchantedGrassGreenSheepEntity;

    private LivingEntity summoner;
    private final double speed;
    private final EntityNavigation navigation;
    private int countdownTicks;
    private final float maxDistance;
    private final float minDistance;

    public FollowGreenSheepSummonerGoal(EnchantedGrassGreenSheepEntity enchantedGrassGreenSheepEntity, LivingEntity summoner, double speed,
                                        EntityNavigation navigation, float maxDistance, float minDistance){
            this.enchantedGrassGreenSheepEntity = enchantedGrassGreenSheepEntity;
            this.summoner = summoner;
        this.speed = speed;
            this.navigation = navigation;
            this.maxDistance = maxDistance;
            this.minDistance = minDistance;
    }

    @Override
    public boolean canStart() {
        LivingEntity livingEntity = this.enchantedGrassGreenSheepEntity.getSummoner();

        if (livingEntity == null || livingEntity.isSpectator() || this.enchantedGrassGreenSheepEntity.squaredDistanceTo(livingEntity) < (double) (this.minDistance * this.minDistance))
            return false;
        this.summoner = livingEntity;
        return true;
    }

    @Override
    public boolean shouldContinue(){
        if (this.navigation.isIdle()){
            return false;
        } else {
            return this.enchantedGrassGreenSheepEntity.squaredDistanceTo(this.summoner) > (double) (this.maxDistance * this.maxDistance);
        }
    }

    public void tick(){
        this.enchantedGrassGreenSheepEntity.getLookControl().lookAt(this.summoner,10.0F,
                (float) this.enchantedGrassGreenSheepEntity.getMaxLookPitchChange());
        if (--this.countdownTicks <= 0){
            this.countdownTicks = 10;
            if (!this.enchantedGrassGreenSheepEntity.hasVehicle()){
                if (this.enchantedGrassGreenSheepEntity.squaredDistanceTo(this.summoner) >= 144.0D) {
                    SummoningHelper.tryTeleport(this.enchantedGrassGreenSheepEntity, summoner);
                } else {
                    this.navigation.startMovingTo(this.summoner,this.speed);
                }
            }
        }
    }
}
