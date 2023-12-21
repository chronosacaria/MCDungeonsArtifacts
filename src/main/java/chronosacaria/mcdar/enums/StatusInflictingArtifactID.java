package chronosacaria.mcdar.enums;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.registries.ArtifactsRegistry;
import net.minecraft.item.Item;

import java.util.EnumMap;

public enum StatusInflictingArtifactID implements IArtifactItem {
    CORRUPTED_SEEDS,
    GONG_OF_WEAKENING,
    LOVE_MEDALLION,
    SATCHEL_OF_ELEMENTS,
    SHOCK_POWDER;

    public static EnumMap<StatusInflictingArtifactID, Item> getItemsEnum() {
        return ArtifactsRegistry.STATUS_INFLICTING_ARTIFACT;
    }

    @Override
    public Boolean mcdar$isEnabled() {
        return Mcdar.CONFIG.mcdarArtifactsStatsConfig.STATUS_INFLICTING_ARTIFACT_STATS.get(this).mcdar$getIsEnabled();
    }

    @Override
    public Item mcdar$getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Float mcdar$getGeneralArtifactSpawnRate() {
        return Mcdar.CONFIG.mcdarArtifactsStatsConfig.STATUS_INFLICTING_ARTIFACT_STATS.get(this).mcdar$getGeneralArtifactSpawnRate();
    }

    @Override
    public Float mcdar$getDungeonArtifactSpawnRate() {
        return Mcdar.CONFIG.mcdarArtifactsStatsConfig.STATUS_INFLICTING_ARTIFACT_STATS.get(this).mcdar$getDungeonArtifactSpawnRate();
    }
}
