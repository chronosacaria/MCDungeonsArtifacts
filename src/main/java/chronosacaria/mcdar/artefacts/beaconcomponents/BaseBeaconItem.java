package chronosacaria.mcdar.artefacts.beaconcomponents;

import chronosacaria.mcdar.artefacts.ArtefactDamagingItem;
import chronosacaria.mcdar.enums.DamagingArtefactID;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
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


public abstract class BaseBeaconItem extends ArtefactDamagingItem {

    public static final double RAYTRACE_DISTANCE = 256;
    public static final float BEAM_DAMAGE_PER_TICK = 0.5F;

    public int beamType = 0;

    public BaseBeaconItem(DamagingArtefactID id) {
        super(id);
    }

    @Nullable
    public static BeaconBeamColour getBeaconBeamColour(ItemStack itemStack){
        Item item = itemStack.getItem();
        return item instanceof BaseBeaconItem ? ((BaseBeaconItem)item).getBeamColour() : null;
    }

    public static ItemStack getBeaconIfHeld(PlayerEntity playerEntity){
        ItemStack heldItem = playerEntity.getMainHandStack();
        if (!(heldItem.getItem() instanceof BaseBeaconItem)){
            heldItem = playerEntity.getOffHandStack();
            if (!(heldItem.getItem() instanceof BaseBeaconItem)){
                return ItemStack.EMPTY;
            }
        }
        return heldItem;
    }

    protected abstract BeaconBeamColour getBeamColour();

    @Override
    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);

        if (world.isClient) {
            //if (user.totalExperience > 0) {
                user.world.playSound(user, user.getX(), user.getY(), user.getZ(), SoundEvents.BLOCK_BEACON_ACTIVATE,
                        SoundCategory.PLAYERS, 1.0F, 1.0F);
            //}
            return new TypedActionResult<>(ActionResult.PASS, itemStack);
        }
        //if (user.totalExperience < 0){
        //    return new TypedActionResult<>(ActionResult.FAIL, itemStack);
        //}
        user.setCurrentHand(hand);
        return new TypedActionResult<>(ActionResult.PASS, itemStack);
    }

    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        user.world.playSound((PlayerEntity) user, user.getX(), user.getY(), user.getZ(), SoundEvents.BLOCK_BEACON_DEACTIVATE,
                SoundCategory.PLAYERS, 1.0F, 1.0F);
    }

    @Override
    public UseAction getUseAction(ItemStack itemStack){
        return UseAction.NONE;
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        super.usageTick(world, user, stack, remainingUseTicks);
        if (user instanceof PlayerEntity playerEntity){

            if (playerEntity.isCreative() || this.consumeTick(playerEntity)) {
                if (!playerEntity.world.isClient) {
                    HitResult result = playerEntity.raycast(RAYTRACE_DISTANCE, 1.0f, false);
                    Vec3d eyeVector = playerEntity.getEyePos();
                    Vec3d lookVector = playerEntity.getRotationVec(1.0F);
                    Vec3d targetVector = eyeVector.add(lookVector.x * RAYTRACE_DISTANCE, lookVector.y * RAYTRACE_DISTANCE, lookVector.z * RAYTRACE_DISTANCE);
                    Box box = playerEntity.getBoundingBox().stretch(lookVector.multiply(RAYTRACE_DISTANCE)).expand(1.0D, 1.0D, 1.0D);
                    EntityHitResult entityHitResult = ProjectileUtil.raycast(playerEntity, eyeVector, targetVector,
                            box,
                            entity -> entity instanceof LivingEntity && !entity.isSpectator() && entity.collides(), 0.0F);
                    if (entityHitResult != null) {
                        entityHitResult.getEntity().damage(DamageSource.MAGIC, BEAM_DAMAGE_PER_TICK);
                    }
                }

                if (remainingUseTicks % 20 == 0){
                    stack.damage(1, user, (entity) -> entity.sendToolBreakStatus(user.getActiveHand()));
                    //((PlayerEntity)user).addExperience(-7);
                }
            }
        }
    }
    protected abstract boolean consumeTick(PlayerEntity playerEntity);
}
