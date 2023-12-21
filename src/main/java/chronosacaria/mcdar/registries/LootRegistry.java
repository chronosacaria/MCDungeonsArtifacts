package chronosacaria.mcdar.registries;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.enums.*;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class LootRegistry {

    public static final Collection<Identifier> ILLAGER_ARTIFACT_LOOT_TABLES =
            Set.of(LootTables.WOODLAND_MANSION_CHEST, LootTables.PILLAGER_OUTPOST_CHEST);

    public static final Collection<Identifier> VILLAGER_ARTIFACT_LOOT_TABLES =
            Set.of(LootTables.VILLAGE_ARMORER_CHEST, LootTables.VILLAGE_BUTCHER_CHEST,
                    LootTables.VILLAGE_CARTOGRAPHER_CHEST, LootTables.VILLAGE_FISHER_CHEST,
                    LootTables.VILLAGE_FLETCHER_CHEST, LootTables.VILLAGE_DESERT_HOUSE_CHEST,
                    LootTables.VILLAGE_MASON_CHEST, LootTables.VILLAGE_PLAINS_CHEST,
                    LootTables.VILLAGE_SAVANNA_HOUSE_CHEST, LootTables.VILLAGE_SHEPARD_CHEST,
                    LootTables.VILLAGE_SNOWY_HOUSE_CHEST, LootTables.VILLAGE_TAIGA_HOUSE_CHEST,
                    LootTables.VILLAGE_TANNERY_CHEST, LootTables.VILLAGE_TEMPLE_CHEST,
                    LootTables.VILLAGE_TOOLSMITH_CHEST);
    public static final Collection<Identifier> WORLD_CHESTS =
            Set.of(LootTables.ABANDONED_MINESHAFT_CHEST, LootTables.SIMPLE_DUNGEON_CHEST, LootTables.SHIPWRECK_TREASURE_CHEST);

    public static final List<IArtifactItem> illagerArtifacts = List.of(
            DamagingArtifactID.BLAST_FUNGUS,
            DamagingArtifactID.HARVESTER,
            DamagingArtifactID.LIGHTNING_ROD,
            DamagingArtifactID.POWERSHAKER,
            StatusInflictingArtifactID.CORRUPTED_SEEDS,
            StatusInflictingArtifactID.GONG_OF_WEAKENING,
            StatusInflictingArtifactID.SATCHEL_OF_ELEMENTS,
            StatusInflictingArtifactID.SHOCK_POWDER,
            AgilityArtifactID.DEATH_CAP_MUSHROOM,
            DefensiveArtifactID.ENCHANTERS_TOME,
            DefensiveArtifactID.SOUL_HEALER,
            DefensiveArtifactID.TOTEM_OF_REGENERATION,
            DefensiveArtifactID.TOTEM_OF_SHIELDING,
            DefensiveArtifactID.TOTEM_OF_SOUL_PROTECTION,
            QuiverArtifactID.HARPOON_QUIVER,
            QuiverArtifactID.THUNDERING_QUIVER,
            QuiverArtifactID.TORMENT_QUIVER
    );
    public static final List<Float> illagerArtifactSpawnRates = List.of(
            Mcdar.CONFIG.mcdarArtifactsStatsConfig.DAMAGING_ARTIFACT_STATS.get(DamagingArtifactID.BLAST_FUNGUS).mcdar$getGeneralArtifactSpawnRate(),
            Mcdar.CONFIG.mcdarArtifactsStatsConfig.DAMAGING_ARTIFACT_STATS.get(DamagingArtifactID.HARVESTER).mcdar$getGeneralArtifactSpawnRate(),
            Mcdar.CONFIG.mcdarArtifactsStatsConfig.DAMAGING_ARTIFACT_STATS.get(DamagingArtifactID.LIGHTNING_ROD).mcdar$getGeneralArtifactSpawnRate(),
            Mcdar.CONFIG.mcdarArtifactsStatsConfig.DAMAGING_ARTIFACT_STATS.get(DamagingArtifactID.POWERSHAKER).mcdar$getGeneralArtifactSpawnRate(),
            Mcdar.CONFIG.mcdarArtifactsStatsConfig.STATUS_INFLICTING_ARTIFACT_STATS.get(StatusInflictingArtifactID.CORRUPTED_SEEDS).mcdar$getGeneralArtifactSpawnRate(),
            Mcdar.CONFIG.mcdarArtifactsStatsConfig.STATUS_INFLICTING_ARTIFACT_STATS.get(StatusInflictingArtifactID.GONG_OF_WEAKENING).mcdar$getGeneralArtifactSpawnRate(),
            Mcdar.CONFIG.mcdarArtifactsStatsConfig.STATUS_INFLICTING_ARTIFACT_STATS.get(StatusInflictingArtifactID.SATCHEL_OF_ELEMENTS).mcdar$getGeneralArtifactSpawnRate(),
            Mcdar.CONFIG.mcdarArtifactsStatsConfig.STATUS_INFLICTING_ARTIFACT_STATS.get(StatusInflictingArtifactID.SHOCK_POWDER).mcdar$getGeneralArtifactSpawnRate(),
            Mcdar.CONFIG.mcdarArtifactsStatsConfig.AGILITY_ARTIFACT_STATS.get(AgilityArtifactID.DEATH_CAP_MUSHROOM).mcdar$getGeneralArtifactSpawnRate(),
            Mcdar.CONFIG.mcdarArtifactsStatsConfig.DEFENSIVE_ARTIFACT_STATS.get(DefensiveArtifactID.ENCHANTERS_TOME).mcdar$getGeneralArtifactSpawnRate(),
            Mcdar.CONFIG.mcdarArtifactsStatsConfig.DEFENSIVE_ARTIFACT_STATS.get(DefensiveArtifactID.SOUL_HEALER).mcdar$getGeneralArtifactSpawnRate(),
            Mcdar.CONFIG.mcdarArtifactsStatsConfig.DEFENSIVE_ARTIFACT_STATS.get(DefensiveArtifactID.TOTEM_OF_REGENERATION).mcdar$getGeneralArtifactSpawnRate(),
            Mcdar.CONFIG.mcdarArtifactsStatsConfig.DEFENSIVE_ARTIFACT_STATS.get(DefensiveArtifactID.TOTEM_OF_SHIELDING).mcdar$getGeneralArtifactSpawnRate(),
            Mcdar.CONFIG.mcdarArtifactsStatsConfig.DEFENSIVE_ARTIFACT_STATS.get(DefensiveArtifactID.TOTEM_OF_SOUL_PROTECTION).mcdar$getGeneralArtifactSpawnRate(),
            Mcdar.CONFIG.mcdarArtifactsStatsConfig.QUIVER_ARTIFACT_STATS.get(QuiverArtifactID.HARPOON_QUIVER).mcdar$getGeneralArtifactSpawnRate(),
            Mcdar.CONFIG.mcdarArtifactsStatsConfig.QUIVER_ARTIFACT_STATS.get(QuiverArtifactID.THUNDERING_QUIVER).mcdar$getGeneralArtifactSpawnRate(),
            Mcdar.CONFIG.mcdarArtifactsStatsConfig.QUIVER_ARTIFACT_STATS.get(QuiverArtifactID.TORMENT_QUIVER).mcdar$getGeneralArtifactSpawnRate()
    );

    public static final List<IArtifactItem> villagerArtifacts = List.of(
            AgilityArtifactID.BOOTS_OF_SWIFTNESS,
            SummoningArtifactID.BUZZY_NEST,
            SummoningArtifactID.ENCHANTED_GRASS,
            QuiverArtifactID.FLAMING_QUIVER,
            AgilityArtifactID.GHOST_CLOAK,
            SummoningArtifactID.GOLEM_KIT,
            DefensiveArtifactID.IRON_HIDE_AMULET,
            AgilityArtifactID.LIGHT_FEATHER,
            SummoningArtifactID.TASTY_BONE,
            DamagingArtifactID.UPDRAFT_TOME,
            DefensiveArtifactID.WIND_HORN,
            SummoningArtifactID.WONDERFUL_WHEAT
    );

    public static final List<IArtifactItem> allArtifacts = List.of(
            DamagingArtifactID.BLAST_FUNGUS,
            StatusInflictingArtifactID.CORRUPTED_SEEDS,
            AgilityArtifactID.DEATH_CAP_MUSHROOM,
            DefensiveArtifactID.ENCHANTERS_TOME,
            StatusInflictingArtifactID.GONG_OF_WEAKENING,
            QuiverArtifactID.HARPOON_QUIVER,
            DamagingArtifactID.HARVESTER,
            DamagingArtifactID.LIGHTNING_ROD,
            DamagingArtifactID.POWERSHAKER,
            StatusInflictingArtifactID.SATCHEL_OF_ELEMENTS,
            StatusInflictingArtifactID.SHOCK_POWDER,
            DefensiveArtifactID.SOUL_HEALER,
            QuiverArtifactID.THUNDERING_QUIVER,
            QuiverArtifactID.TORMENT_QUIVER,
            DefensiveArtifactID.TOTEM_OF_REGENERATION,
            DefensiveArtifactID.TOTEM_OF_SHIELDING,
            DefensiveArtifactID.TOTEM_OF_SOUL_PROTECTION,
            AgilityArtifactID.BOOTS_OF_SWIFTNESS,
            SummoningArtifactID.BUZZY_NEST,
            SummoningArtifactID.ENCHANTED_GRASS,
            QuiverArtifactID.FLAMING_QUIVER,
            AgilityArtifactID.GHOST_CLOAK,
            SummoningArtifactID.GOLEM_KIT,
            DefensiveArtifactID.IRON_HIDE_AMULET,
            AgilityArtifactID.LIGHT_FEATHER,
            SummoningArtifactID.TASTY_BONE,
            DamagingArtifactID.UPDRAFT_TOME,
            DefensiveArtifactID.WIND_HORN,
            SummoningArtifactID.WONDERFUL_WHEAT);


    public static void register(){
        Random randomArtifact = Random.create();

            LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (ILLAGER_ARTIFACT_LOOT_TABLES.contains(id)) {
                LootPool.Builder lootPoolBuilder = LootPool.builder();
                int selectedArtifact = randomArtifact.nextBetween(0, 16);
                lootPoolBuilder.rolls(ConstantLootNumberProvider.create(1));

                for (IArtifactItem artifactItem : illagerArtifacts) {
                    lootPoolBuilder.conditionally(
                            RandomChanceLootCondition.builder(
                                    illagerArtifactSpawnRates.get(selectedArtifact)
                            )
                    );
                    if (illagerArtifacts.get(selectedArtifact).mcdar$isEnabled()) {
                        addArtifact(lootPoolBuilder, artifactItem.mcdar$getItem());
                    }
                }
                tableBuilder.pool(lootPoolBuilder.build());

            }
            else if (VILLAGER_ARTIFACT_LOOT_TABLES.contains(id)) {
                LootPool.Builder lootPoolBuilder = LootPool.builder();
                lootPoolBuilder.rolls(ConstantLootNumberProvider.create(1));
                for (IArtifactItem artifactItem : List.of(AgilityArtifactID.BOOTS_OF_SWIFTNESS, SummoningArtifactID.BUZZY_NEST, SummoningArtifactID.ENCHANTED_GRASS,
                        QuiverArtifactID.FLAMING_QUIVER, AgilityArtifactID.GHOST_CLOAK, SummoningArtifactID.GOLEM_KIT, DefensiveArtifactID.IRON_HIDE_AMULET,
                        AgilityArtifactID.LIGHT_FEATHER, SummoningArtifactID.TASTY_BONE, DamagingArtifactID.UPDRAFT_TOME, DefensiveArtifactID.WIND_HORN,
                        SummoningArtifactID.WONDERFUL_WHEAT)) {
                    lootPoolBuilder.conditionally(RandomChanceLootCondition.builder(artifactItem.mcdar$getGeneralArtifactSpawnRate()));
                    if (artifactItem.mcdar$isEnabled())
                        addArtifact(lootPoolBuilder, artifactItem.mcdar$getItem());
                    tableBuilder.pool(lootPoolBuilder.build());
                }
            }
            else if (WORLD_CHESTS.contains(id)) {
                LootPool.Builder lootPoolBuilder = LootPool.builder();
                lootPoolBuilder.rolls(ConstantLootNumberProvider.create(1));
                for (IArtifactItem artifactItem : List.of(DamagingArtifactID.BLAST_FUNGUS, StatusInflictingArtifactID.CORRUPTED_SEEDS, AgilityArtifactID.DEATH_CAP_MUSHROOM,
                        DefensiveArtifactID.ENCHANTERS_TOME, StatusInflictingArtifactID.GONG_OF_WEAKENING, QuiverArtifactID.HARPOON_QUIVER, DamagingArtifactID.HARVESTER,
                        DamagingArtifactID.LIGHTNING_ROD, DamagingArtifactID.POWERSHAKER, StatusInflictingArtifactID.SATCHEL_OF_ELEMENTS, StatusInflictingArtifactID.SHOCK_POWDER,
                        DefensiveArtifactID.SOUL_HEALER, QuiverArtifactID.THUNDERING_QUIVER, QuiverArtifactID.TORMENT_QUIVER, DefensiveArtifactID.TOTEM_OF_REGENERATION,
                        DefensiveArtifactID.TOTEM_OF_SHIELDING, DefensiveArtifactID.TOTEM_OF_SOUL_PROTECTION, AgilityArtifactID.BOOTS_OF_SWIFTNESS, SummoningArtifactID.BUZZY_NEST,
                        SummoningArtifactID.ENCHANTED_GRASS, QuiverArtifactID.FLAMING_QUIVER, AgilityArtifactID.GHOST_CLOAK, SummoningArtifactID.GOLEM_KIT, DefensiveArtifactID.IRON_HIDE_AMULET,
                        AgilityArtifactID.LIGHT_FEATHER, SummoningArtifactID.TASTY_BONE, DamagingArtifactID.UPDRAFT_TOME, DefensiveArtifactID.WIND_HORN, SummoningArtifactID.WONDERFUL_WHEAT)) {
                    lootPoolBuilder.conditionally(RandomChanceLootCondition.builder(artifactItem.mcdar$getDungeonArtifactSpawnRate()));
                    if (artifactItem.mcdar$isEnabled())
                        addArtifact(lootPoolBuilder, artifactItem.mcdar$getItem());
                    tableBuilder.pool(lootPoolBuilder.build());
                }
            }
        }));
    }

    public static void addArtifact(LootPool.Builder poolBuilder, Item artifact){
        poolBuilder.with(ItemEntry.builder(artifact).build());
    }
}
