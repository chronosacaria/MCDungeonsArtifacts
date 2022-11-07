package chronosacaria.mcdar.init;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.config.McdarConfig;
import chronosacaria.mcdar.enchants.ArmorEnchantment;
import chronosacaria.mcdar.enchants.EnchantID;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.registry.Registry;

import java.util.EnumMap;
import java.util.Locale;

public class EnchantsRegistry {
    public static final EnumMap<EnchantID, Enchantment> enchants = new EnumMap<>(EnchantID.class);

    public static void init(){
        for (EnchantID enchantID : EnchantID.values()) {
            if (!McdarConfig.CONFIG.ENABLE_ENCHANTMENT.get(enchantID))
                continue;

            Enchantment enchantment = new ArmorEnchantment(enchantID);

            enchants.put(enchantID, enchantment);
            registerEnchant(enchantID.toString().toLowerCase(Locale.ROOT), enchantment);
        }
    }

    protected static void registerEnchant(String id, Enchantment enchant){
        Registry.register(Registry.ENCHANTMENT, Mcdar.ID(id), enchant);
    }
}
