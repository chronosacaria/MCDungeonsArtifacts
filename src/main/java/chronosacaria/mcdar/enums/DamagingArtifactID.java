package chronosacaria.mcdar.enums;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.registries.ArtifactsRegistry;
import net.minecraft.item.Item;

import java.util.EnumMap;

public enum DamagingArtifactID implements IArtifactItem{
    BLAST_FUNGUS,
    //CORRUPTED_BEACON,
    HARVESTER,
    LIGHTNING_ROD,
    POWERSHAKER,
    UPDRAFT_TOME;

    public static EnumMap<DamagingArtifactID, Item> getItemsEnum() {
        return ArtifactsRegistry.DAMAGING_ARTIFACT;
    }

    @Override
    public Boolean mcdar$isEnabled() {
        return Mcdar.CONFIG.mcdarArtifactsStatsConfig.DAMAGING_ARTIFACT_STATS.get(this).mcdar$getIsEnabled();
    }

    @Override
    public Item mcdar$getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Float mcdar$getGeneralArtifactSpawnRate() {
        return Mcdar.CONFIG.mcdarArtifactsStatsConfig.DAMAGING_ARTIFACT_STATS.get(this).mcdar$getGeneralArtifactSpawnRate();
    }

    @Override
    public Float mcdar$getDungeonArtifactSpawnRate() {
        return Mcdar.CONFIG.mcdarArtifactsStatsConfig.DAMAGING_ARTIFACT_STATS.get(this).mcdar$getDungeonArtifactSpawnRate();
    }
}
