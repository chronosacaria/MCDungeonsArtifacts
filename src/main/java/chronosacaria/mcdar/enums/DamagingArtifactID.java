package chronosacaria.mcdar.enums;

import chronosacaria.mcdar.config.McdarConfig;
import chronosacaria.mcdar.init.ArtifactsInit;
import net.minecraft.item.Item;

import java.util.EnumMap;

public enum DamagingArtifactID implements IArtifactItem{
    BLAST_FUNGUS,
    //CORRUPTED_BEACON,
    HARVESTER,
    LIGHTNING_ROD,
    POWERSHAKER,
    UPDRAFT_TOME;

    public static EnumMap<DamagingArtifactID, Boolean> getEnabledItems() {
        return McdarConfig.CONFIG.ENABLE_DAMAGING_ARTIFACT;
    }

    public static EnumMap<DamagingArtifactID, Item> getItemsEnum() {
        return ArtifactsInit.damagingArtifact;
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
