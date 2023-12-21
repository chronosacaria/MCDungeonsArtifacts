package chronosacaria.mcdar.config;

import chronosacaria.mcdar.enchants.EnchantmentsID;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import java.util.LinkedHashMap;

@Config(name = "mcdar_enchantments_config")
public class McdarEnchantmentsConfig implements ConfigData {
    public final LinkedHashMap<EnchantmentsID, EnchantmentIDConfigHelper> ENCHANTMENT_CONFIG = new LinkedHashMap<>();

    public McdarEnchantmentsConfig() {
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.COOLDOWN,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.BEAST_BOSS,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.BEAST_BURST,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.BEAST_SURGE,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3
                )
        );
    }
}
