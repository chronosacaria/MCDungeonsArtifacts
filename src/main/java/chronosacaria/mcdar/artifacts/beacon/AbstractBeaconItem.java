package chronosacaria.mcdar.artifacts.beacon;

import chronosacaria.mcdar.artifacts.ArtifactDamagingItem;
import chronosacaria.mcdar.enums.DamagingArtifactID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractBeaconItem extends ArtifactDamagingItem {

    public static final double RAYTRACE_DISTANCE = 256;
    public static final float BEAM_DAMAGE_PER_TICK = 0.5F;
    public static final float XP_COST_PER_TICK = 0.625F;

    public AbstractBeaconItem(DamagingArtifactID artefactID, int artifactDurability) {
        super(artefactID, artifactDurability);
    }

    @Nullable
    public static BeaconBeamColor getBeaconBeamColor(ItemStack itemStack){
        Item stackItem = itemStack.getItem();
        return stackItem instanceof AbstractBeaconItem ? ((AbstractBeaconItem) stackItem).getBeamColor() : null;
    }

    public abstract boolean canFire(PlayerEntity playerEntity, ItemStack itemStack);

    public static ItemStack getBeacon(PlayerEntity player) {
        ItemStack heldItem = player.getMainHandStack();
        if (!(heldItem.getItem() instanceof AbstractBeaconItem)) {
            heldItem = player.getOffHandStack();
            if (!(heldItem.getItem() instanceof AbstractBeaconItem)) {
                return ItemStack.EMPTY;
            }
        }
        return heldItem;
    }

    public abstract BeaconBeamColor getBeamColor();

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);

        if (canFire(user, itemStack)){
            user.playSound(
                    SoundEvents.BLOCK_BEACON_ACTIVATE,
                    1.0f,
                    1.0f
            );
        } else {
            return new TypedActionResult<>(ActionResult.FAIL, itemStack);
        }

        if (!world.isClient) {
            user.setCurrentHand(hand);
        }
        return new TypedActionResult<>(ActionResult.PASS, itemStack);
    }

    @Override
    public int getMaxUseTime(ItemStack stack){
        return 72000;
    }

    @Override
    public UseAction getUseAction(ItemStack stack){
        return UseAction.NONE;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks){
        user.playSound(
                SoundEvents.BLOCK_BEACON_DEACTIVATE,
                1.0f,
                1.0f
        );
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks){
        if (user instanceof PlayerEntity playerEntity){

            if (playerEntity.isCreative() || this.consumeTick(playerEntity)) {
                HitResult result = playerEntity.raycast(RAYTRACE_DISTANCE, 1.0f, false);
                Vec3d eyeVector = playerEntity.getCameraPosVec(1.0f);
                Vec3d lookVector = playerEntity.getRotationVector();
                Vec3d targetVector = eyeVector.add(lookVector.x * RAYTRACE_DISTANCE, lookVector.y * RAYTRACE_DISTANCE
                        , lookVector.z * RAYTRACE_DISTANCE);
                Box box = playerEntity.getBoundingBox().stretch(lookVector.multiply(RAYTRACE_DISTANCE)).expand(1.0D,
                        1.0D, 1.0D);
                EntityHitResult entityHitResult = ProjectileUtil.getEntityCollision(world, playerEntity, eyeVector,
                        targetVector, box,
                        entity -> entity instanceof LivingEntity && !entity.isSpectator() /*&& entity.collides()*/);
                if (entityHitResult != null && result.getPos().squaredDistanceTo(eyeVector) > entityHitResult.getPos().squaredDistanceTo(eyeVector)){
                    if (!world.isClient()){
                        Entity entity = entityHitResult.getEntity();
                        entity.timeUntilRegen = 0;
                        entity.damage(playerEntity.getDamageSources().indirectMagic(playerEntity, playerEntity), BEAM_DAMAGE_PER_TICK);
                    }
                }
                if (getMaxCount() % 20 == 0){
                    stack.damage(1, playerEntity, (entity) -> entity.sendToolBreakStatus(playerEntity.getActiveHand()));
                }
            }
        }
    }

    protected abstract boolean consumeTick(PlayerEntity playerEntity);
}
