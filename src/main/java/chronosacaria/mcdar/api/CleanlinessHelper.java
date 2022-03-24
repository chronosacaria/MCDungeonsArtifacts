package chronosacaria.mcdar.api;

import chronosacaria.mcdar.enchants.EnchantID;
import chronosacaria.mcdar.init.EnchantsRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;

public class CleanlinessHelper {

    public static void cooldownHelper(PlayerEntity player, Item item, int maxCooldown) {
        int cooldownLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(EnchantID.COOLDOWN),
                player);
        player.getItemCooldownManager().set(item,
                cooldownLevel == 0 ? maxCooldown :
                        // Level 1 : reduce by 20%, Each further level reduces by additional 10% of maxCooldown
                        (int) (maxCooldown - (0.1 * maxCooldown * (cooldownLevel + 1))));
    }
}
