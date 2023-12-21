package chronosacaria.mcdar.enums;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.registries.ArtifactsRegistry;
import net.minecraft.item.Item;

import java.util.EnumMap;

public enum AgilityArtifactID implements IArtifactItem {
    BOOTS_OF_SWIFTNESS,
    DEATH_CAP_MUSHROOM,
    GHOST_CLOAK,
    LIGHT_FEATHER;

    public static EnumMap<AgilityArtifactID, Item> getItemsEnum() {
        return ArtifactsRegistry.AGILITY_ARTIFACT;
    }

    @Override
    public Boolean mcdar$isEnabled() {
        return Mcdar.CONFIG.mcdarArtifactsStatsConfig.AGILITY_ARTIFACT_STATS.get(this).mcdar$getIsEnabled();
    }

    @Override
    public Item mcdar$getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Float mcdar$getGeneralArtifactSpawnRate() {
        return Mcdar.CONFIG.mcdarArtifactsStatsConfig.AGILITY_ARTIFACT_STATS.get(this).mcdar$getGeneralArtifactSpawnRate();
    }

    @Override
    public Float mcdar$getDungeonArtifactSpawnRate() {
        return Mcdar.CONFIG.mcdarArtifactsStatsConfig.AGILITY_ARTIFACT_STATS.get(this).mcdar$getDungeonArtifactSpawnRate();
    }
}
