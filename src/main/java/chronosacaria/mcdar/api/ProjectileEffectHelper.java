package chronosacaria.mcdar.api;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ProjectileEffectHelper {
    public static void ricochetArrowLikeShield(PersistentProjectileEntity persistentProjectileEntity, LivingEntity livingEntity){
        persistentProjectileEntity.setVelocity(persistentProjectileEntity.getVelocity().multiply(-0.1D));
        persistentProjectileEntity.yaw += 180.0F;
        persistentProjectileEntity.prevYaw += 180.0F;
        if (!persistentProjectileEntity.world.isClient && persistentProjectileEntity.getVelocity().lengthSquared() < 1.0E-7D){
            if (persistentProjectileEntity.pickupType == PersistentProjectileEntity.PickupPermission.ALLOWED){
                persistentProjectileEntity.dropStack(new ItemStack(Items.ARROW), 0.1F);
            }
            persistentProjectileEntity.remove();
        }
    }
    public static void flamingQuiverArrow(PersistentProjectileEntity persistentProjectileEntity){
        persistentProjectileEntity.setOnFireFor(100);
    }

}
