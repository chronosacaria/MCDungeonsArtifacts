package chronosacaria.mcdar.goals;

import chronosacaria.mcdar.entities.EnchantedGrassBlueSheepEntity;
import chronosacaria.mcdar.entities.EnchantedGrassGreenSheepEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.LandPathNodeMaker;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

public class FollowBlueSheepSummonerGoal extends Goal {

    private final EnchantedGrassBlueSheepEntity enchantedGrassBlueSheepEntity;

    private LivingEntity summoner;
    private final WorldView worldView;
    private final double speed;
    private final EntityNavigation navigation;
    private int countdownTicks;
    private final float maxDistance;
    private final float minDistance;
    private final boolean leavesAllowed;

    public FollowBlueSheepSummonerGoal(EnchantedGrassBlueSheepEntity enchantedGrassBlueSheepEntity, LivingEntity summoner, WorldView worldView, double speed,
                                       EntityNavigation navigation, float maxDistance, float minDistance, boolean leavesAllowed){
            this.enchantedGrassBlueSheepEntity = enchantedGrassBlueSheepEntity;
            this.summoner = summoner;
            this.worldView = worldView;
            this.speed = speed;
            this.navigation = navigation;
            this.maxDistance = maxDistance;
            this.minDistance = minDistance;
            this.leavesAllowed = leavesAllowed;
    }

    @Override
    public boolean canStart(){
        LivingEntity livingEntity = this.enchantedGrassBlueSheepEntity.getSummoner();

        if (livingEntity == null){
            return false;
        } else if (livingEntity.isSpectator()){
            return false;
        } else if (this.enchantedGrassBlueSheepEntity.squaredDistanceTo(livingEntity) < (double) (this.minDistance * this.minDistance)){
            return false;
        } else {
            this.summoner = livingEntity;
            return true;
        }
    }

    @Override
    public boolean shouldContinue(){
        if (this.navigation.isIdle()){
            return false;
        } else {
            return this.enchantedGrassBlueSheepEntity.squaredDistanceTo(this.summoner) > (double) (this.maxDistance * this.maxDistance);
        }
    }

    public void tick(){
        this.enchantedGrassBlueSheepEntity.getLookControl().lookAt(this.summoner,10.0F, (float) this.enchantedGrassBlueSheepEntity.getLookPitchSpeed());
        if (--this.countdownTicks <= 0){
            this.countdownTicks = 10;
            if (!this.enchantedGrassBlueSheepEntity.hasVehicle()){
                if (this.enchantedGrassBlueSheepEntity.squaredDistanceTo(this.summoner) >= 144.0D) {
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

    private boolean canTeleportTo(BlockPos blockPos){
        PathNodeType pathNodeType = LandPathNodeMaker.getLandNodeType(enchantedGrassBlueSheepEntity.getEntityWorld(),
                new BlockPos.Mutable());
        if (pathNodeType != PathNodeType.WALKABLE){
            return false;
        } else {
            BlockState blockState = this.worldView.getBlockState(blockPos.down());
            if (!this.leavesAllowed && blockState.getBlock() instanceof LeavesBlock){
                return false;
            } else {
                BlockPos blockPos1 = blockPos.subtract(new BlockPos(this.enchantedGrassBlueSheepEntity.getBlockPos()));
                return this.worldView.isSpaceEmpty(this.enchantedGrassBlueSheepEntity,
                        this.enchantedGrassBlueSheepEntity.getBoundingBox().offset(blockPos1));
            }
        }
    }

    private int getRandomInt(int i, int j){
        return this.enchantedGrassBlueSheepEntity.getRandom().nextInt(j - i + 1) + i;
    }
}
