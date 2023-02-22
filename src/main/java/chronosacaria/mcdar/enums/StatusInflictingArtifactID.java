package chronosacaria.mcdar.enums;

import chronosacaria.mcdar.config.McdarConfig;
import chronosacaria.mcdar.init.ArtifactsInit;
import net.minecraft.item.Item;

import java.util.EnumMap;

public enum StatusInflictingArtifactID implements IArtifactItem {
    CORRUPTED_SEEDS,
    GONG_OF_WEAKENING,
    LOVE_MEDALLION,
    SATCHEL_OF_ELEMENTS,
    SHOCK_POWDER;

    public static EnumMap<StatusInflictingArtifactID, Boolean> getEnabledItems(){
        return McdarConfig.CONFIG.ENABLE_STATUS_INFLICTING_ARTIFACT;
    }

    public static EnumMap<StatusInflictingArtifactID, Item> getItemsEnum() {
        return ArtifactsInit.statusInflictingArtifact;
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
