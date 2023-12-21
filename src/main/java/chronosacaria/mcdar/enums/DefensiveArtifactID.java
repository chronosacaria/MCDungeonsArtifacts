package chronosacaria.mcdar.enums;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.registries.ArtifactsRegistry;
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


    public static EnumMap<DefensiveArtifactID, Item> getItemsEnum() {
        return ArtifactsRegistry.DEFENSIVE_ARTIFACT;
    }

    @Override
    public Boolean mcdar$isEnabled() {
        return Mcdar.CONFIG.mcdarArtifactsStatsConfig.DEFENSIVE_ARTIFACT_STATS.get(this).mcdar$getIsEnabled();
    }

    @Override
    public Item mcdar$getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Float mcdar$getGeneralArtifactSpawnRate() {
        return Mcdar.CONFIG.mcdarArtifactsStatsConfig.DEFENSIVE_ARTIFACT_STATS.get(this).mcdar$getGeneralArtifactSpawnRate();
    }

    @Override
    public Float mcdar$getDungeonArtifactSpawnRate() {
        return Mcdar.CONFIG.mcdarArtifactsStatsConfig.DEFENSIVE_ARTIFACT_STATS.get(this).mcdar$getDungeonArtifactSpawnRate();
    }
}
