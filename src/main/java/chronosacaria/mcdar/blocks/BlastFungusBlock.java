package chronosacaria.mcdar.blocks;

import chronosacaria.mcdar.entities.BlastFungusEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;

public class BlastFungusBlock extends Block {
    public static final BooleanProperty UNSTABLE;

    public BlastFungusBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(UNSTABLE, false));
    }

    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (!oldState.isOf(state.getBlock())) {
            if (world.isReceivingRedstonePower(pos)) {
                primeTnt(world, pos);
                world.removeBlock(pos, false);
            }

        }
    }

    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (world.isReceivingRedstonePower(pos)) {
            primeTnt(world, pos);
            world.removeBlock(pos, false);
        }

    }

    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient() && !player.isCreative() && (Boolean)state.get(UNSTABLE)) {
            primeTnt(world, pos);
        }

        super.onBreak(world, pos, state, player);
    }

    public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
        if (!world.isClient) {
            BlastFungusEntity blastFungusEntity = new BlastFungusEntity(world, (double)pos.getX() + 0.5D, (double)pos.getY(),
                    (double)pos.getZ() + 0.5D, explosion.getCausingEntity());
            blastFungusEntity.setFuse((short)(world.random.nextInt(blastFungusEntity.getFuseTimer() / 4) + blastFungusEntity.getFuseTimer() / 8));
            world.spawnEntity(blastFungusEntity);
        }
    }

    public static void primeTnt(World world, BlockPos pos) {
        primeTnt(world, pos, (LivingEntity) null);
    }

    private static void primeTnt(World world, BlockPos pos, @Nullable LivingEntity igniter) {
        if (!world.isClient) {
            BlastFungusEntity blastFungusEntity = new BlastFungusEntity(world, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, igniter);
            world.spawnEntity(blastFungusEntity);
            world.playSound((PlayerEntity)null, blastFungusEntity.getX(), blastFungusEntity.getY(),
                    blastFungusEntity.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
                              BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        if (item != Items.FLINT_AND_STEEL && item != Items.FIRE_CHARGE) {
            return super.onUse(state, world, pos, player, hand, hit);
        } else {
            primeTnt(world, pos, player);
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
            if (!player.isCreative()) {
                if (item == Items.FLINT_AND_STEEL) {
                    itemStack.damage(1, (LivingEntity)player, ((playerEntity) -> {
                        playerEntity.sendToolBreakStatus(hand);
                    }));
                } else {
                    itemStack.decrement(1);
                }
            }

            return ActionResult.success(world.isClient);
        }
    }

    public void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
        if (!world.isClient) {
            Entity entity = projectile.getOwner();
            if (projectile.isOnFire()) {
                BlockPos blockPos = hit.getBlockPos();
                primeTnt(world, blockPos, entity instanceof LivingEntity ? (LivingEntity)entity : null);
                world.removeBlock(blockPos, false);
            }
        }

    }

    public boolean shouldDropItemsOnExplosion(Explosion explosion) {
        return false;
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(UNSTABLE);
    }

    static {
        UNSTABLE = Properties.UNSTABLE;
    }
}
