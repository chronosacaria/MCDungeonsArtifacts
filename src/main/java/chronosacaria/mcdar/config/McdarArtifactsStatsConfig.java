package chronosacaria.mcdar.config;

import chronosacaria.mcdar.enums.*;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import java.util.LinkedHashMap;
import java.util.List;

@Config(name = "mcdar_artifacts_stats_config")
public class McdarArtifactsStatsConfig implements ConfigData {
    public final LinkedHashMap<AgilityArtifactID, ArtifactStatsConfigHelper> AGILITY_ARTIFACT_STATS = new LinkedHashMap<>();
    public final LinkedHashMap<DamagingArtifactID, ArtifactStatsConfigHelper> DAMAGING_ARTIFACT_STATS = new LinkedHashMap<>();
    public final LinkedHashMap<DefensiveArtifactID, ArtifactStatsConfigHelper> DEFENSIVE_ARTIFACT_STATS = new LinkedHashMap<>();
    public final LinkedHashMap<QuiverArtifactID, ArtifactStatsConfigHelper> QUIVER_ARTIFACT_STATS = new LinkedHashMap<>();
    public final LinkedHashMap<StatusInflictingArtifactID, ArtifactStatsConfigHelper> STATUS_INFLICTING_ARTIFACT_STATS = new LinkedHashMap<>();
    public final LinkedHashMap<SummoningArtifactID, ArtifactStatsConfigHelper> SUMMONING_ARTIFACT_STATS = new LinkedHashMap<>();

    public McdarArtifactsStatsConfig() {
        AGILITY_ARTIFACT_STATS.put(
                AgilityArtifactID.BOOTS_OF_SWIFTNESS,
                new ArtifactStatsConfigHelper(
                        true,
                        64,
                        100,
                        0.25f,
                        0.1f
                )
        );
        AGILITY_ARTIFACT_STATS.put(
                AgilityArtifactID.DEATH_CAP_MUSHROOM,
                new ArtifactStatsConfigHelper(
                        true,
                        64,
                        600,
                        0.25f,
                        0.1f
                )
        );
        AGILITY_ARTIFACT_STATS.put(
                AgilityArtifactID.GHOST_CLOAK,
                new ArtifactStatsConfigHelper(
                        true,
                        64,
                        120,
                        0.25f,
                        0.1f
                )
        );
        AGILITY_ARTIFACT_STATS.put(
                AgilityArtifactID.LIGHT_FEATHER,
                new ArtifactStatsConfigHelper(
                        true,
                        64,
                        120,
                        0.25f,
                        0.1f
                )
        );

        DAMAGING_ARTIFACT_STATS.put(
                DamagingArtifactID.BLAST_FUNGUS,
                new ArtifactStatsConfigHelper(
                        true,
                        64,
                        120,
                        0.25f,
                        0.1f
                )
        );
        DAMAGING_ARTIFACT_STATS.put(
                DamagingArtifactID.HARVESTER,
                new ArtifactStatsConfigHelper(
                        true,
                        64,
                        60,
                        0.25f,
                        0.1f
                )
        );
        DAMAGING_ARTIFACT_STATS.put(
                DamagingArtifactID.LIGHTNING_ROD,
                new ArtifactStatsConfigHelper(
                        true,
                        64,
                        40,
                        0.25f,
                        0.1f
                )
        );
        DAMAGING_ARTIFACT_STATS.put(
                DamagingArtifactID.POWERSHAKER,
                new ArtifactStatsConfigHelper(
                        true,
                        64,
                        600,
                        0.25f,
                        0.1f
                )
        );
        DAMAGING_ARTIFACT_STATS.put(
                DamagingArtifactID.UPDRAFT_TOME,
                new ArtifactStatsConfigHelper(
                        true,
                        64,
                        300,
                        0.25f,
                        0.1f
                )
        );

        DEFENSIVE_ARTIFACT_STATS.put(
                DefensiveArtifactID.ENCHANTERS_TOME,
                new ArtifactStatsConfigHelper(
                        true,
                        64,
                        100,
                        0.25f,
                        0.1f
                )
        );
        DEFENSIVE_ARTIFACT_STATS.put(
                DefensiveArtifactID.IRON_HIDE_AMULET,
                new ArtifactStatsConfigHelper(
                        true,
                        64,
                        500,
                        0.25f,
                        0.1f
                )
        );
        DEFENSIVE_ARTIFACT_STATS.put(
                DefensiveArtifactID.SOUL_HEALER,
                new ArtifactStatsConfigHelper(
                        true,
                        64,
                        60,
                        0.25f,
                        0.1f
                )
        );
        DEFENSIVE_ARTIFACT_STATS.put(
                DefensiveArtifactID.TOTEM_OF_REGENERATION,
                new ArtifactStatsConfigHelper(
                        true,
                        64,
                        600,
                        0.25f,
                        0.1f
                )
        );
        DEFENSIVE_ARTIFACT_STATS.put(
                DefensiveArtifactID.TOTEM_OF_SHIELDING,
                new ArtifactStatsConfigHelper(
                        true,
                        64,
                        600,
                        0.25f,
                        0.1f
                )
        );
        DEFENSIVE_ARTIFACT_STATS.put(
                DefensiveArtifactID.TOTEM_OF_SOUL_PROTECTION,
                new ArtifactStatsConfigHelper(
                        true,
                        64,
                        600,
                        0.25f,
                        0.1f
                )
        );
        DEFENSIVE_ARTIFACT_STATS.put(
                DefensiveArtifactID.WIND_HORN,
                new ArtifactStatsConfigHelper(
                        true,
                        64,
                        250,
                        0.25f,
                        0.1f
                )
        );

        QUIVER_ARTIFACT_STATS.put(
                QuiverArtifactID.FLAMING_QUIVER,
                new ArtifactStatsConfigHelper(
                        List.of(
                                "When Quivers are activated, their effect remains",
                                "active during the cooldown phase. Therefore, their",
                                "maxCooldownEnchantmentTime should be treated as inverted:",
                                "the higher the value, the longer the effect lasts;",
                                "the lower the value, the shorter the effect lasts."
                        ),
                        true,
                        8,
                        500,
                        0.25f,
                        0.1f
                )
        );
        QUIVER_ARTIFACT_STATS.put(
                QuiverArtifactID.HARPOON_QUIVER,
                new ArtifactStatsConfigHelper(
                        List.of(
                                "When Quivers are activated, their effect remains",
                                "active during the cooldown phase. Therefore, their",
                                "maxCooldownEnchantmentTime should be treated as inverted:",
                                "the higher the value, the longer the effect lasts;",
                                "the lower the value, the shorter the effect lasts."
                        ),
                        true,
                        8,
                        600,
                        0.25f,
                        0.1f
                )
        );
        QUIVER_ARTIFACT_STATS.put(
                QuiverArtifactID.THUNDERING_QUIVER,
                new ArtifactStatsConfigHelper(
                        List.of(
                                "When Quivers are activated, their effect remains",
                                "active during the cooldown phase. Therefore, their",
                                "maxCooldownEnchantmentTime should be treated as inverted:",
                                "the higher the value, the longer the effect lasts;",
                                "the lower the value, the shorter the effect lasts."
                        ),
                        true,
                        8,
                        600,
                        0.25f,
                        0.1f
                )
        );
        QUIVER_ARTIFACT_STATS.put(
                QuiverArtifactID.TORMENT_QUIVER,
                new ArtifactStatsConfigHelper(
                        List.of(
                                "When Quivers are activated, their effect remains",
                                "active during the cooldown phase. Therefore, their",
                                "maxCooldownEnchantmentTime should be treated as inverted:",
                                "the higher the value, the longer the effect lasts;",
                                "the lower the value, the shorter the effect lasts."
                        ),
                        true,
                        8,
                        600,
                        0.25f,
                        0.1f
                )
        );

        STATUS_INFLICTING_ARTIFACT_STATS.put(
                StatusInflictingArtifactID.CORRUPTED_SEEDS,
                new ArtifactStatsConfigHelper(
                        true,
                        64,
                        100,
                        0.25f,
                        0.1f
                )
        );
        STATUS_INFLICTING_ARTIFACT_STATS.put(
                StatusInflictingArtifactID.GONG_OF_WEAKENING,
                new ArtifactStatsConfigHelper(
                        true,
                        64,
                        100,
                        0.25f,
                        0.1f
                )
        );
        STATUS_INFLICTING_ARTIFACT_STATS.put(
                StatusInflictingArtifactID.LOVE_MEDALLION,
                new ArtifactStatsConfigHelper(
                        true,
                        64,
                        120,
                        0.25f,
                        0.1f
                )
        );
        STATUS_INFLICTING_ARTIFACT_STATS.put(
                StatusInflictingArtifactID.SATCHEL_OF_ELEMENTS,
                new ArtifactStatsConfigHelper(
                        true,
                        64,
                        60,
                        0.25f,
                        0.1f
                )
        );
        STATUS_INFLICTING_ARTIFACT_STATS.put(
                StatusInflictingArtifactID.SHOCK_POWDER,
                new ArtifactStatsConfigHelper(
                        true,
                        64,
                        300,
                        0.25f,
                        0.1f
                )
        );

        SUMMONING_ARTIFACT_STATS.put(
                SummoningArtifactID.BUZZY_NEST,
                new ArtifactStatsConfigHelper(
                        true,
                        8,
                        600,
                        0.25f,
                        0.1f
                )
        );
        SUMMONING_ARTIFACT_STATS.put(
                SummoningArtifactID.ENCHANTED_GRASS,
                new ArtifactStatsConfigHelper(
                        true,
                        8,
                        600,
                        0.25f,
                        0.1f
                )
        );
        SUMMONING_ARTIFACT_STATS.put(
                SummoningArtifactID.GOLEM_KIT,
                new ArtifactStatsConfigHelper(
                        true,
                        8,
                        600,
                        0.25f,
                        0.1f
                )
        );
        SUMMONING_ARTIFACT_STATS.put(
                SummoningArtifactID.TASTY_BONE,
                new ArtifactStatsConfigHelper(
                        true,
                        8,
                        600,
                        0.25f,
                        0.1f
                )
        );
        SUMMONING_ARTIFACT_STATS.put(
                SummoningArtifactID.WONDERFUL_WHEAT,
                new ArtifactStatsConfigHelper(
                        true,
                        8,
                        600,
                        0.25f,
                        0.1f
                )
        );
    }
}
