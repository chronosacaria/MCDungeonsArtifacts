package chronosacaria.mcdar.api;

import chronosacaria.mcdar.enums.*;
import net.minecraft.loot.LootTables;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Set;

public class GroupedObjectsHelper {
    public static final Set<Identifier> ILLAGER_ARTIFACT_GENERAL_LOOT_TABLES =
            Set.of(LootTables.WOODLAND_MANSION_CHEST, LootTables.PILLAGER_OUTPOST_CHEST);

    public static final Set<Identifier> VILLAGER_ARTIFACT_GENERAL_LOOT_TABLES =
            Set.of(LootTables.VILLAGE_ARMORER_CHEST, LootTables.VILLAGE_BUTCHER_CHEST,
                    LootTables.VILLAGE_CARTOGRAPHER_CHEST, LootTables.VILLAGE_FISHER_CHEST,
                    LootTables.VILLAGE_FLETCHER_CHEST, LootTables.VILLAGE_DESERT_HOUSE_CHEST,
                    LootTables.VILLAGE_MASON_CHEST, LootTables.VILLAGE_PLAINS_CHEST,
                    LootTables.VILLAGE_SAVANNA_HOUSE_CHEST, LootTables.VILLAGE_SHEPARD_CHEST,
                    LootTables.VILLAGE_SNOWY_HOUSE_CHEST, LootTables.VILLAGE_TAIGA_HOUSE_CHEST,
                    LootTables.VILLAGE_TANNERY_CHEST, LootTables.VILLAGE_TEMPLE_CHEST,
                    LootTables.VILLAGE_TOOLSMITH_CHEST);
    public static final Set<Identifier> ALL_ARTIFACTS_DUNGEON_LOOT_TABLES =
            Set.of(LootTables.ABANDONED_MINESHAFT_CHEST, LootTables.SIMPLE_DUNGEON_CHEST, LootTables.SHIPWRECK_TREASURE_CHEST);

    public static final List<IArtifactItem> illagerArtifacts = List.of(
            AgilityArtifactID.DEATH_CAP_MUSHROOM,
            DamagingArtifactID.BLAST_FUNGUS,
            DamagingArtifactID.HARVESTER,
            DamagingArtifactID.LIGHTNING_ROD,
            DamagingArtifactID.POWERSHAKER,
            DefensiveArtifactID.ENCHANTERS_TOME,
            DefensiveArtifactID.SOUL_HEALER,
            DefensiveArtifactID.TOTEM_OF_REGENERATION,
            DefensiveArtifactID.TOTEM_OF_SHIELDING,
            DefensiveArtifactID.TOTEM_OF_SOUL_PROTECTION,
            QuiverArtifactID.HARPOON_QUIVER,
            QuiverArtifactID.THUNDERING_QUIVER,
            QuiverArtifactID.TORMENT_QUIVER,
            StatusInflictingArtifactID.CORRUPTED_SEEDS,
            StatusInflictingArtifactID.GONG_OF_WEAKENING,
            StatusInflictingArtifactID.LOVE_MEDALLION,
            StatusInflictingArtifactID.SATCHEL_OF_ELEMENTS,
            StatusInflictingArtifactID.SHOCK_POWDER
    );

    public static final List<IArtifactItem> villagerArtifacts = List.of(
            AgilityArtifactID.BOOTS_OF_SWIFTNESS,
            AgilityArtifactID.GHOST_CLOAK,
            AgilityArtifactID.LIGHT_FEATHER,
            DamagingArtifactID.UPDRAFT_TOME,
            DefensiveArtifactID.IRON_HIDE_AMULET,
            DefensiveArtifactID.WIND_HORN,
            QuiverArtifactID.FLAMING_QUIVER,
            SummoningArtifactID.BUZZY_NEST,
            SummoningArtifactID.ENCHANTED_GRASS,
            SummoningArtifactID.GOLEM_KIT,
            SummoningArtifactID.TASTY_BONE,
            SummoningArtifactID.WONDERFUL_WHEAT
    );

    public static final List<IArtifactItem> allArtifacts = List.of(
            AgilityArtifactID.BOOTS_OF_SWIFTNESS,
            AgilityArtifactID.DEATH_CAP_MUSHROOM,
            AgilityArtifactID.GHOST_CLOAK,
            AgilityArtifactID.LIGHT_FEATHER,
            DamagingArtifactID.BLAST_FUNGUS,
            DamagingArtifactID.HARVESTER,
            DamagingArtifactID.LIGHTNING_ROD,
            DamagingArtifactID.POWERSHAKER,
            DamagingArtifactID.UPDRAFT_TOME,
            DefensiveArtifactID.ENCHANTERS_TOME,
            DefensiveArtifactID.IRON_HIDE_AMULET,
            DefensiveArtifactID.SOUL_HEALER,
            DefensiveArtifactID.TOTEM_OF_REGENERATION,
            DefensiveArtifactID.TOTEM_OF_SHIELDING,
            DefensiveArtifactID.TOTEM_OF_SOUL_PROTECTION,
            DefensiveArtifactID.WIND_HORN,
            QuiverArtifactID.FLAMING_QUIVER,
            QuiverArtifactID.HARPOON_QUIVER,
            QuiverArtifactID.THUNDERING_QUIVER,
            QuiverArtifactID.TORMENT_QUIVER,
            StatusInflictingArtifactID.CORRUPTED_SEEDS,
            StatusInflictingArtifactID.GONG_OF_WEAKENING,
            StatusInflictingArtifactID.SATCHEL_OF_ELEMENTS,
            StatusInflictingArtifactID.SHOCK_POWDER,
            SummoningArtifactID.BUZZY_NEST,
            SummoningArtifactID.ENCHANTED_GRASS,
            SummoningArtifactID.GOLEM_KIT,
            SummoningArtifactID.TASTY_BONE,
            SummoningArtifactID.WONDERFUL_WHEAT);
}
