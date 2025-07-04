package dev.timefall.mcdar.config;

import dev.timefall.mcdar.ModConstants;
import dev.timefall.mcdar.api.GroupedObjectsHelper;
import dev.timefall.mcdar.config.item_sections.*;
import dev.timefall.mcdx.configs.AoeExclusionType;
import me.fzzyhmstrs.fzzy_config.annotations.IgnoreVisibility;
import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;
import me.fzzyhmstrs.fzzy_config.config.Config;
import me.fzzyhmstrs.fzzy_config.validation.collection.ValidatedChoiceList;
import me.fzzyhmstrs.fzzy_config.validation.collection.ValidatedList;
import me.fzzyhmstrs.fzzy_config.validation.collection.ValidatedSet;
import me.fzzyhmstrs.fzzy_config.validation.minecraft.ValidatedIdentifier;
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedEnum;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@IgnoreVisibility
@SuppressWarnings("FieldMayBeFinal")
public class McdarArtifactsStatsConfig extends Config {

    public static final McdarArtifactsStatsConfig CONFIG = ConfigApiJava.registerAndLoadConfig(McdarArtifactsStatsConfig::new);

    private float artifactGeneralSpawnChance = 0.25f;
    private float artifactDungeonSpawnChance = 0.1f;

    transient Set<Identifier> villager = GroupedObjectsHelper.VILLAGER_ARTIFACT_GENERAL_LOOT_TABLES.stream().map(RegistryKey::getValue).collect(Collectors.toSet());
    transient Set<Identifier> illager = GroupedObjectsHelper.ILLAGER_ARTIFACT_GENERAL_LOOT_TABLES.stream().map(RegistryKey::getValue).collect(Collectors.toSet());
    transient Set<Identifier> dungeon = GroupedObjectsHelper.ALL_ARTIFACTS_DUNGEON_LOOT_TABLES.stream().map(RegistryKey::getValue).collect(Collectors.toSet());

    // Determines if the table is valid
    private ValidatedSet<Identifier> villagerLootTables = ValidatedIdentifier.ofDynamicKey(RegistryKeys.LOOT_TABLE, "mcdar_artifact_stats_villager_loot_tables", (id, entry) -> true).toSet(villager);
    private ValidatedSet<Identifier> illagerLootTables = ValidatedIdentifier.ofDynamicKey(RegistryKeys.LOOT_TABLE, "mcdar_artifacts_stats_illager_loot_tables", (id, entry) -> true).toSet(illager);
    private ValidatedSet<Identifier> dungeonLootTables = ValidatedIdentifier.ofDynamicKey(RegistryKeys.LOOT_TABLE, "mcdar_artifacts_stats_dungeon_loot_tables", (id, entry) -> true).toSet(dungeon);

    private DamagingArtifactStats damagingArtifactStats = new DamagingArtifactStats();
    private AgilityArtifactStats agilityArtifactStats = new AgilityArtifactStats();
    private DefensiveArtifactStats defensiveArtifactStats = new DefensiveArtifactStats();
    private QuiverArtifactStats quiverArtifactStats = new QuiverArtifactStats();
    private StatusInflictingArtifactStats statusInflictingArtifactStats = new StatusInflictingArtifactStats();
    private SummoningArtifactStats summoningArtifactStats = new SummoningArtifactStats();

    public float mcdar$getGeneralSpawnChance() {
        return artifactGeneralSpawnChance;
    }
    public float mcdar$getDungeonSpawnChance() {
        return artifactDungeonSpawnChance;
    }
    public DamagingArtifactStats mcdar$getDamagingArtifactStats() {
        return damagingArtifactStats;
    }

    public AgilityArtifactStats mcdar$getAgilityArtifactStats() {
        return agilityArtifactStats;
    }

    public DefensiveArtifactStats mcdar$getDefensiveArtifactStats() {
        return defensiveArtifactStats;
    }

    public QuiverArtifactStats mcdar$getQuiverArtifactStats() {
        return quiverArtifactStats;
    }

    public StatusInflictingArtifactStats mcdar$getStatusInflictingArtifactStats() {
        return statusInflictingArtifactStats;
    }

    public SummoningArtifactStats mcdar$getSummoningArtifactStats() {
        return summoningArtifactStats;
    }


    public ValidatedChoiceList<AoeExclusionType> satchelExclusions = new ValidatedList<>(
            AoeExclusionType.TYPES,
            new ValidatedEnum<>(AoeExclusionType.CREATIVE_PLAYER)
    ).toChoiceList(
            List.of(
                AoeExclusionType.SELF,
                AoeExclusionType.SELF_PET,
                AoeExclusionType.OBSCURED
            )
    );

    public Set<Identifier> mcdar$getVillagerLootTables() {
        return villagerLootTables;
    }

    public Set<Identifier> mcdar$getIllagerLootTables() {
        return illagerLootTables;
    }

    public Set<Identifier> mcdar$getDungeonLootTables() {
        return dungeonLootTables;
    }


    public McdarArtifactsStatsConfig() {
        super(ModConstants.id("mcdar_artifacts_stats_config"));
    }
}