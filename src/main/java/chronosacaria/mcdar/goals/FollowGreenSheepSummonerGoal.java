package chronosacaria.mcdar.goals;

import chronosacaria.mcdar.entities.EnchantedGrassGreenSheepEntity;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.LandPathNodeMaker;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

public class FollowGreenSheepSummonerGoal extends Goal {

    private final EnchantedGrassGreenSheepEntity enchantedGrassGreenSheepEntity;

    private LivingEntity summoner;
    private final WorldView worldView;
    private final double speed;
    private final EntityNavigation navigation;
    private int countdownTicks;
    private final float maxDistance;
    private final float minDistance;
    private final boolean leavesAllowed;

    public FollowGreenSheepSummonerGoal(EnchantedGrassGreenSheepEntity enchantedGrassGreenSheepEntity, LivingEntity summoner, WorldView worldView, double speed,
                                        EntityNavigation navigation, float maxDistance, float minDistance, boolean leavesAllowed){
            this.enchantedGrassGreenSheepEntity = enchantedGrassGreenSheepEntity;
            this.summoner = summoner;
            this.worldView = worldView;
            this.speed = speed;
            this.navigation = navigation;
            this.maxDistance = maxDistance;
            this.minDistance = minDistance;
            this.leavesAllowed = leavesAllowed;
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
                    this.tryTeleport();
                } else {
                    this.navigation.startMovingTo(this.summoner,this.speed);
                }
            }
        }
    }

    private void tryTeleport(){
        BlockPos blockPos = new BlockPos(this.summoner.getBlockPos());

        for (int i = 0; i < 10; ++i){
            int j = this.getRandomInt(-3, 3);
            int k = this.getRandomInt(-1, 1);
            int l = this.getRandomInt(-3, 3);
            boolean bl = this.tryTeleportTo(blockPos.getX() + j, blockPos.getY() + k, blockPos.getZ() + l); //23343
            if (bl) {
                return;
            }
        }
    }

    private boolean tryTeleportTo(int i, int j, int k){
        if (Math.abs((double) i - this.summoner.getX()) < 2.0D && Math.abs((double) k - this.summoner.getZ()) < 2.0D) {
            return false;
        } else if (!this.canTeleportTo(new BlockPos(i, j, k))){ //23344
            return false;
        } else {
            this.navigation.stop();
            return true;
        }
    }

    private boolean canTeleportTo(BlockPos blockPos) {
        if (LandPathNodeMaker.getLandNodeType(enchantedGrassGreenSheepEntity.getEntityWorld(), new BlockPos.Mutable()) != PathNodeType.WALKABLE)
            return false;
        if (!this.leavesAllowed && this.worldView.getBlockState(blockPos.down()).getBlock() instanceof LeavesBlock)
            return false;
        return this.worldView.isSpaceEmpty(this.enchantedGrassGreenSheepEntity, this.enchantedGrassGreenSheepEntity.getBoundingBox().offset(blockPos.subtract(new BlockPos(this.enchantedGrassGreenSheepEntity.getBlockPos()))));
    }

    private int getRandomInt(int i, int j){
        return this.enchantedGrassGreenSheepEntity.getRandom().nextInt(j - i + 1) + i;
    }
}
