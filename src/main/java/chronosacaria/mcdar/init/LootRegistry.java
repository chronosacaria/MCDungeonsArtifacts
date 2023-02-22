package chronosacaria.mcdar.init;

import chronosacaria.mcdar.config.McdarConfig;
import chronosacaria.mcdar.enums.*;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class LootRegistry {

    public static final Collection<Identifier> ILLAGER_ARTEFACT_LOOT_TABLES =
            Set.of(LootTables.WOODLAND_MANSION_CHEST, LootTables.PILLAGER_OUTPOST_CHEST);

    public static final Collection<Identifier> VILLAGER_ARTEFACT_LOOT_TABLES =
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

    public static void init(){
        LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, tableBuilder, source) -> {

            if (ILLAGER_ARTEFACT_LOOT_TABLES.contains(id)) {
                LootPool.Builder lootPoolBuilder = LootPool.builder();
                lootPoolBuilder.rolls(ConstantLootNumberProvider.create(1));
                lootPoolBuilder.conditionally(RandomChanceLootCondition.builder(McdarConfig.CONFIG.ILLAGER_ARTIFACT_SPAWN_RATE));
                for (IArtifactItem artifactItem : List.of(DamagingArtifactID.BLAST_FUNGUS, StatusInflictingArtifactID.CORRUPTED_SEEDS, AgilityArtifactID.DEATH_CAP_MUSHROOM,
                        DefensiveArtifactID.ENCHANTERS_TOME, StatusInflictingArtifactID.GONG_OF_WEAKENING, QuiverArtifactID.HARPOON_QUIVER, DamagingArtifactID.HARVESTER,
                        DamagingArtifactID.LIGHTNING_ROD, DamagingArtifactID.POWERSHAKER, StatusInflictingArtifactID.SATCHEL_OF_ELEMENTS, StatusInflictingArtifactID.SHOCK_POWDER,
                        DefensiveArtifactID.SOUL_HEALER, QuiverArtifactID.THUNDERING_QUIVER, QuiverArtifactID.TORMENT_QUIVER, DefensiveArtifactID.TOTEM_OF_REGENERATION,
                        DefensiveArtifactID.TOTEM_OF_SHIELDING, DefensiveArtifactID.TOTEM_OF_SOUL_PROTECTION)) {
                    if (artifactItem.isEnabled())
                        addArtifact(lootPoolBuilder, artifactItem.getItem());
                }
                tableBuilder.pool(lootPoolBuilder.build());
            }
            else if (VILLAGER_ARTEFACT_LOOT_TABLES.contains(id)) {
                LootPool.Builder lootPoolBuilder = LootPool.builder();
                lootPoolBuilder.rolls(ConstantLootNumberProvider.create(1));
                lootPoolBuilder.conditionally(RandomChanceLootCondition.builder(McdarConfig.CONFIG.VILLAGER_ARTIFACT_SPAWN_RATE));
                for (IArtifactItem artifactItem : List.of(AgilityArtifactID.BOOTS_OF_SWIFTNESS, SummoningArtifactID.BUZZY_NEST, SummoningArtifactID.ENCHANTED_GRASS,
                        QuiverArtifactID.FLAMING_QUIVER, AgilityArtifactID.GHOST_CLOAK, SummoningArtifactID.GOLEM_KIT, DefensiveArtifactID.IRON_HIDE_AMULET,
                        AgilityArtifactID.LIGHT_FEATHER, SummoningArtifactID.TASTY_BONE, DamagingArtifactID.UPDRAFT_TOME, DefensiveArtifactID.WIND_HORN,
                        SummoningArtifactID.WONDERFUL_WHEAT)) {
                    if (artifactItem.isEnabled())
                        addArtifact(lootPoolBuilder, artifactItem.getItem());
                }
                tableBuilder.pool(lootPoolBuilder.build());
            }
            else if (WORLD_CHESTS.contains(id)) {
                LootPool.Builder lootPoolBuilder = LootPool.builder();
                lootPoolBuilder.rolls(ConstantLootNumberProvider.create(1));
                lootPoolBuilder.conditionally(RandomChanceLootCondition.builder(McdarConfig.CONFIG.WORLD_ARTIFACT_SPAWN_RATE));
                for (IArtifactItem artifactItem : List.of(DamagingArtifactID.BLAST_FUNGUS, StatusInflictingArtifactID.CORRUPTED_SEEDS, AgilityArtifactID.DEATH_CAP_MUSHROOM,
                        DefensiveArtifactID.ENCHANTERS_TOME, StatusInflictingArtifactID.GONG_OF_WEAKENING, QuiverArtifactID.HARPOON_QUIVER, DamagingArtifactID.HARVESTER,
                        DamagingArtifactID.LIGHTNING_ROD, DamagingArtifactID.POWERSHAKER, StatusInflictingArtifactID.SATCHEL_OF_ELEMENTS, StatusInflictingArtifactID.SHOCK_POWDER,
                        DefensiveArtifactID.SOUL_HEALER, QuiverArtifactID.THUNDERING_QUIVER, QuiverArtifactID.TORMENT_QUIVER, DefensiveArtifactID.TOTEM_OF_REGENERATION,
                        DefensiveArtifactID.TOTEM_OF_SHIELDING, DefensiveArtifactID.TOTEM_OF_SOUL_PROTECTION, AgilityArtifactID.BOOTS_OF_SWIFTNESS, SummoningArtifactID.BUZZY_NEST,
                        SummoningArtifactID.ENCHANTED_GRASS, QuiverArtifactID.FLAMING_QUIVER, AgilityArtifactID.GHOST_CLOAK, SummoningArtifactID.GOLEM_KIT, DefensiveArtifactID.IRON_HIDE_AMULET,
                        AgilityArtifactID.LIGHT_FEATHER, SummoningArtifactID.TASTY_BONE, DamagingArtifactID.UPDRAFT_TOME, DefensiveArtifactID.WIND_HORN, SummoningArtifactID.WONDERFUL_WHEAT)) {
                    if (artifactItem.isEnabled())
                        addArtifact(lootPoolBuilder, artifactItem.getItem());
                }
                tableBuilder.pool(lootPoolBuilder.build());
            }
        }));
    }

    public static void addArtifact(LootPool.Builder poolBuilder, Item artefact){
        poolBuilder.with(ItemEntry.builder(artefact).build());
    }
}
