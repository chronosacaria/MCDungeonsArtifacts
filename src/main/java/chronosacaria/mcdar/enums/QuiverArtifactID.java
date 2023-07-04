package chronosacaria.mcdar.enums;

import chronosacaria.mcdar.config.McdarConfig;
import chronosacaria.mcdar.registries.ArtifactsRegistry;
import net.minecraft.item.Item;

import java.util.EnumMap;

public enum QuiverArtifactID implements IArtifactItem {
    FLAMING_QUIVER,
    HARPOON_QUIVER,
    THUNDERING_QUIVER,
    TORMENT_QUIVER;

    public static EnumMap<QuiverArtifactID, Boolean> getEnabledItems(){
        return McdarConfig.CONFIG.ENABLE_QUIVER_ARTIFACT;
    }

    public static EnumMap<QuiverArtifactID, Item> getItemsEnum() {
        return ArtifactsRegistry.quiverArtifact;
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
