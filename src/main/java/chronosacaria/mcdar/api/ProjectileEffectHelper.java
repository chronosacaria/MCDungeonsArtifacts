package chronosacaria.mcdar.api;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ProjectileEffectHelper {
    public static void ricochetArrowLikeShield(PersistentProjectileEntity ppe){
        ppe.setVelocity(ppe.getVelocity().multiply(-0.1D));
        ppe.getYaw(180.0F);
        ppe.prevYaw += 180.0F;
        if (!ppe.getWorld().isClient && ppe.getVelocity().lengthSquared() < 1.0E-7D){
            if (ppe.pickupType == PersistentProjectileEntity.PickupPermission.ALLOWED){
                ppe.dropStack(new ItemStack(Items.ARROW), 0.1F);
            }
            ppe.remove(Entity.RemovalReason.KILLED);
        }
    }
    public static void flamingQuiverArrow(PersistentProjectileEntity ppe){
        ppe.setOnFireFor(100);
    }

}
