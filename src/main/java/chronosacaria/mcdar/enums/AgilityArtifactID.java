package chronosacaria.mcdar.enums;

import chronosacaria.mcdar.config.McdarConfig;
import chronosacaria.mcdar.init.ArtifactsInit;
import net.minecraft.item.Item;

import java.util.EnumMap;

public enum AgilityArtifactID implements IArtifactItem {
    BOOTS_OF_SWIFTNESS,
    DEATH_CAP_MUSHROOM,
    GHOST_CLOAK,
    LIGHT_FEATHER;

    public static EnumMap<AgilityArtifactID, Boolean> getEnabledItems(){
        return McdarConfig.CONFIG.ENABLE_AGILITY_ARTIFACT;
    }

    public static EnumMap<AgilityArtifactID, Item> getItemsEnum() {
        return ArtifactsInit.agilityArtifact;
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
