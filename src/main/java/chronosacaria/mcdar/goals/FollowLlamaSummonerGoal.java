package chronosacaria.mcdar.goals;

import chronosacaria.mcdar.entities.GolemKitGolemEntity;
import chronosacaria.mcdar.entities.WonderfulWheatLlamaEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.LandPathNodeMaker;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

public class FollowLlamaSummonerGoal extends Goal {

    private final WonderfulWheatLlamaEntity wonderfulWheatLlamaEntity;

    private LivingEntity summoner;
    private final WorldView worldView;
    private final double speed;
    private final EntityNavigation navigation;
    private int countdownTicks;
    private final float maxDistance;
    private final float minDistance;
    private final boolean leavesAllowed;

    public FollowLlamaSummonerGoal(WonderfulWheatLlamaEntity wonderfulWheatLlamaEntity, LivingEntity summoner, WorldView worldView,
                                   double speed,
                                   EntityNavigation navigation, float maxDistance, float minDistance, boolean leavesAllowed) {
        this.wonderfulWheatLlamaEntity = wonderfulWheatLlamaEntity;
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
                    this.tryTeleport();
                } else {
                    this.navigation.startMovingTo(this.summoner, this.speed);
                }
            }
        }
    }

    private void tryTeleport() {
        BlockPos blockPos = new BlockPos(this.summoner.getBlockPos());

        for (int i = 0; i < 10; ++i) {
            int j = this.getRandomInt(-3, 3);
            int k = this.getRandomInt(-1, 1);
            int l = this.getRandomInt(-3, 3);
            boolean bl = this.tryTeleportTo(blockPos.getX() + j, blockPos.getY() + k, blockPos.getZ() + l); //23343
            if (bl) {
                return;
            }
        }
    }

    private boolean tryTeleportTo(int i, int j, int k) {
        if (Math.abs((double) i - this.summoner.getX()) < 2.0D && Math.abs((double) k - this.summoner.getZ()) < 2.0D) {
            return false;
        } else if (!this.canTeleportTo(new BlockPos(i, j, k))) { //23344
            return false;
        } else {
            this.navigation.stop();
            return true;
        }
    }

    private boolean canTeleportTo(BlockPos blockPos) {
        if (LandPathNodeMaker.getLandNodeType(wonderfulWheatLlamaEntity.getEntityWorld(), new BlockPos.Mutable()) != PathNodeType.WALKABLE)
            return false;
        BlockState blockState = this.worldView.getBlockState(blockPos.down());
        if (!this.leavesAllowed && blockState.getBlock() instanceof LeavesBlock)
            return false;
        return this.worldView.isSpaceEmpty(this.wonderfulWheatLlamaEntity, this.wonderfulWheatLlamaEntity.getBoundingBox().offset(blockPos.subtract(new BlockPos(this.wonderfulWheatLlamaEntity.getBlockPos()))));
    }

    private int getRandomInt(int i, int j){
        return this.wonderfulWheatLlamaEntity.getRandom().nextInt(j - i + 1) + i;
    }
}
