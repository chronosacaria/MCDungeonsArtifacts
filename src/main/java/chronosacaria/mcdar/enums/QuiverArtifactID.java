package chronosacaria.mcdar.enums;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.registries.ArtifactsRegistry;
import net.minecraft.item.Item;

import java.util.EnumMap;

public enum QuiverArtifactID implements IArtifactItem {
    FLAMING_QUIVER,
    HARPOON_QUIVER,
    THUNDERING_QUIVER,
    TORMENT_QUIVER;


    public static EnumMap<QuiverArtifactID, Item> getItemsEnum() {
        return ArtifactsRegistry.QUIVER_ARTIFACT;
    }

    @Override
    public Boolean mcdar$isEnabled() {
        return Mcdar.CONFIG.mcdarArtifactsStatsConfig.QUIVER_ARTIFACT_STATS.get(this).mcdar$getIsEnabled();
    }

    @Override
    public Item mcdar$getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Float mcdar$getGeneralArtifactSpawnRate() {
        return Mcdar.CONFIG.mcdarArtifactsStatsConfig.QUIVER_ARTIFACT_STATS.get(this).mcdar$getGeneralArtifactSpawnRate();
    }

    @Override
    public Float mcdar$getDungeonArtifactSpawnRate() {
        return Mcdar.CONFIG.mcdarArtifactsStatsConfig.QUIVER_ARTIFACT_STATS.get(this).mcdar$getDungeonArtifactSpawnRate();
    }
}
