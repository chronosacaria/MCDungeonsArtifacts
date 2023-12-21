package chronosacaria.mcdar.registries;

import chronosacaria.mcdar.enchants.BeastBossEnchantment;
import chronosacaria.mcdar.enchants.BeastBurstEnchantment;
import chronosacaria.mcdar.enchants.BeastSurgeEnchantment;
import chronosacaria.mcdar.enchants.CooldownEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class EnchantsRegistry {
    public static Enchantment COOLDOWN;
    public static Enchantment BEAST_BOSS;
    public static Enchantment BEAST_BURST;
    public static Enchantment BEAST_SURGE;
    public static void register(){
        COOLDOWN = new CooldownEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET});
        BEAST_BOSS = new BeastBossEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET});
        BEAST_BURST = new BeastBurstEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET});
        BEAST_SURGE = new BeastSurgeEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET});
    }
}
