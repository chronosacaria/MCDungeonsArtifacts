package chronosacaria.mcdar.api;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ProjectileEffectHelper {
    public static void ricochetArrowLikeShield(PersistentProjectileEntity arrowEntity, LivingEntity livingEntity){
        arrowEntity.setVelocity(arrowEntity.getVelocity().multiply(-0.1D));
        arrowEntity.yaw += 180.0F;
        arrowEntity.prevYaw += 180.0F;
        if (!arrowEntity.world.isClient && arrowEntity.getVelocity().lengthSquared() < 1.0E-7D){
            if (arrowEntity.pickupType == PersistentProjectileEntity.PickupPermission.ALLOWED){
                arrowEntity.dropStack(new ItemStack(Items.ARROW), 0.1F);
            }
            arrowEntity.remove();
        }
    }
}
