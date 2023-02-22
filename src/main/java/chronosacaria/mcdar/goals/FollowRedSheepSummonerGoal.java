package chronosacaria.mcdar.goals;

import chronosacaria.mcdar.api.SummoningHelper;
import chronosacaria.mcdar.entities.EnchantedGrassRedSheepEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.EntityNavigation;

public class FollowRedSheepSummonerGoal extends Goal {

    private final EnchantedGrassRedSheepEntity enchantedGrassRedSheepEntity;

    private LivingEntity summoner;
    private final double speed;
    private final EntityNavigation navigation;
    private int countdownTicks;
    private final float maxDistance;
    private final float minDistance;

    public FollowRedSheepSummonerGoal(EnchantedGrassRedSheepEntity enchantedGrassRedSheepEntity, LivingEntity summoner, double speed,
                                      EntityNavigation navigation, float maxDistance, float minDistance){
            this.enchantedGrassRedSheepEntity = enchantedGrassRedSheepEntity;
            this.summoner = summoner;
        this.speed = speed;
            this.navigation = navigation;
            this.maxDistance = maxDistance;
            this.minDistance = minDistance;
    }

    @Override
    public boolean canStart() {
        LivingEntity livingEntity = this.enchantedGrassRedSheepEntity.getSummoner();

        if (livingEntity == null || livingEntity.isSpectator() || this.enchantedGrassRedSheepEntity.squaredDistanceTo(livingEntity) < (double) (this.minDistance * this.minDistance))
            return false;
        this.summoner = livingEntity;
        return true;
    }

    @Override
    public boolean shouldContinue(){
        if (this.navigation.isIdle()){
            return false;
        } else {
            return this.enchantedGrassRedSheepEntity.squaredDistanceTo(this.summoner) > (double) (this.maxDistance * this.maxDistance);
        }
    }

    public void tick(){
        this.enchantedGrassRedSheepEntity.getLookControl().lookAt(this.summoner,10.0F, (float) this.enchantedGrassRedSheepEntity.getMaxLookPitchChange());
        if (--this.countdownTicks <= 0){
            this.countdownTicks = 10;
            if (!this.enchantedGrassRedSheepEntity.hasVehicle()){
                if (this.enchantedGrassRedSheepEntity.squaredDistanceTo(this.summoner) >= 144.0D) {
                    SummoningHelper.tryTeleport(this.enchantedGrassRedSheepEntity, summoner);
                } else {
                    this.navigation.startMovingTo(this.summoner,this.speed);
                }
            }
        }
    }
}
