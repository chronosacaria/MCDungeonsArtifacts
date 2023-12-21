package chronosacaria.mcdar.mixin;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.api.ProjectileEffectHelper;
import chronosacaria.mcdar.enums.QuiverArtifactID;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BowItem.class)
public abstract class BowItemMixin {

    @Inject(method = "onStoppedUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z"), locals =
            LocalCapture.CAPTURE_FAILHARD)
    public void onFlamingQuiverArrowLoosing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks,
                                            CallbackInfo ci, PlayerEntity playerEntity, boolean bl,
                                            ItemStack itemStack, int i, float f, boolean bl2, ArrowItem arrowItem, PersistentProjectileEntity persistentProjectileEntity){
        if (Mcdar.CONFIG.mcdarArtifactsStatsConfig.QUIVER_ARTIFACT_STATS.get(QuiverArtifactID.FLAMING_QUIVER).mcdar$getIsEnabled()){
            if (playerEntity.getOffHandStack().isOf(QuiverArtifactID.FLAMING_QUIVER.mcdar$getItem())) {
                float effectTimer = playerEntity.getItemCooldownManager().getCooldownProgress(playerEntity.getOffHandStack().getItem(), 0);
                if (effectTimer > 0) {
                    ProjectileEffectHelper.flamingQuiverArrow(persistentProjectileEntity);
                }
            }
        }
    }
}
