package chronosacaria.mcdar.enums;

import chronosacaria.mcdar.config.McdarConfig;
import chronosacaria.mcdar.init.ArtifactsInit;
import net.minecraft.item.Item;

import java.util.EnumMap;

public enum DefensiveArtifactID implements IArtifactItem{
    ENCHANTERS_TOME,
    IRON_HIDE_AMULET,
    //SATCHEL_OF_ELIXIRS,
    //SATCHEL_OF_SNACKS,
    SOUL_HEALER,
    TOTEM_OF_REGENERATION,
    TOTEM_OF_SHIELDING,
    TOTEM_OF_SOUL_PROTECTION,
    WIND_HORN;

    public static EnumMap<DefensiveArtifactID, Boolean> getEnabledItems() {
        return McdarConfig.CONFIG.ENABLE_DEFENSIVE_ARTIFACT;
    }

    public static EnumMap<DefensiveArtifactID, Item> getItemsEnum() {
        return ArtifactsInit.defensiveArtifact;
    }

    @Override
    public Boolean isEnabled() {
        return getEnabledItems().get(this);
    }

    @Override
    public Item getItem() {
        return getItemsEnum().get(this);
    }
}
