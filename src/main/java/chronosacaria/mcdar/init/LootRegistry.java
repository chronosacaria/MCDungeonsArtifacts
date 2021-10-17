package chronosacaria.mcdar.init;

import chronosacaria.mcdar.config.McdarConfig;
import chronosacaria.mcdar.enums.*;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.BinomialLootNumberProvider;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class LootRegistry {

    public static final Collection<Identifier> ILLAGER_ARTEFACT_LOOT_TABLES =
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
                    LootTables.WOODLAND_MANSION_CHEST, LootTables.PILLAGER_OUTPOST_CHEST
            )));

    public static final Collection<Identifier> VILLAGER_ARTEFACT_LOOT_TABLES =
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
                    LootTables.VILLAGE_ARMORER_CHEST, LootTables.VILLAGE_BUTCHER_CHEST,
                    LootTables.VILLAGE_CARTOGRAPHER_CHEST, LootTables.VILLAGE_FISHER_CHEST,
                    LootTables.VILLAGE_FLETCHER_CHEST, LootTables.VILLAGE_DESERT_HOUSE_CHEST,
                    LootTables.VILLAGE_MASON_CHEST, LootTables.VILLAGE_PLAINS_CHEST,
                    LootTables.VILLAGE_SAVANNA_HOUSE_CHEST, LootTables.VILLAGE_SHEPARD_CHEST,
                    LootTables.VILLAGE_SNOWY_HOUSE_CHEST, LootTables.VILLAGE_TAIGA_HOUSE_CHEST,
                    LootTables.VILLAGE_TANNERY_CHEST, LootTables.VILLAGE_TEMPLE_CHEST,
                    LootTables.VILLAGE_TOOLSMITH_CHEST
            )));
    public static final Collection<Identifier> WORLD_CHESTS =
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
                    LootTables.ABANDONED_MINESHAFT_CHEST, LootTables.SIMPLE_DUNGEON_CHEST,
                    LootTables.SHIPWRECK_TREASURE_CHEST
            )));

    public static void init(){
        LootTableLoadingCallback.EVENT.register(((resourceManager, manager, id, supplier, setter) -> {
            if (!id.getNamespace().equals("minecraft"))
                return;

            FabricLootPoolBuilder poolBuilder;

            if (ILLAGER_ARTEFACT_LOOT_TABLES.contains(id)){
                poolBuilder = FabricLootPoolBuilder.builder();
                if (McdarConfig.config.enableDamagingArtefact.get(DamagingArtefactID.BLAST_FUNGUS)) {
                    addArtefact(poolBuilder, ArtefactsInit.damagingArtefact.get(DamagingArtefactID.BLAST_FUNGUS),                       McdarConfig.config.getIllagerArtefactSpawnRate());
                }
                if (McdarConfig.config.enableStatusInflictingArtefact.get(StatusInflictingArtefactID.CORRUPTED_SEEDS)) {
                    addArtefact(poolBuilder, ArtefactsInit.statusInflictingArtefact.get(StatusInflictingArtefactID.CORRUPTED_SEEDS),    McdarConfig.config.getIllagerArtefactSpawnRate());
                }
                if (McdarConfig.config.enableAgilityArtefact.get(AgilityArtefactID.DEATH_CAP_MUSHROOM)) {
                    addArtefact(poolBuilder, ArtefactsInit.agilityArtefact.get(AgilityArtefactID.DEATH_CAP_MUSHROOM),                   McdarConfig.config.getIllagerArtefactSpawnRate());
                }
                if (McdarConfig.config.enableDefenciveArtefact.get(DefenciveArtefactID.ENCHANTERS_TOME)) {
                    addArtefact(poolBuilder, ArtefactsInit.defenciveArtefact.get(DefenciveArtefactID.ENCHANTERS_TOME),                  McdarConfig.config.getIllagerArtefactSpawnRate());
                }
                if (McdarConfig.config.enableStatusInflictingArtefact.get(StatusInflictingArtefactID.GONG_OF_WEAKENING)) {
                    addArtefact(poolBuilder, ArtefactsInit.statusInflictingArtefact.get(StatusInflictingArtefactID.GONG_OF_WEAKENING),  McdarConfig.config.getIllagerArtefactSpawnRate());
                }
                if (McdarConfig.config.enableDamagingArtefact.get(DamagingArtefactID.HARVESTER)) {
                    addArtefact(poolBuilder, ArtefactsInit.damagingArtefact.get(DamagingArtefactID.HARVESTER),                          McdarConfig.config.getIllagerArtefactSpawnRate());
                }
                if (McdarConfig.config.enableDamagingArtefact.get(DamagingArtefactID.LIGHTNING_ROD)) {
                    addArtefact(poolBuilder, ArtefactsInit.damagingArtefact.get(DamagingArtefactID.LIGHTNING_ROD),                      McdarConfig.config.getIllagerArtefactSpawnRate());
                }
                if (McdarConfig.config.enableDefenciveArtefact.get(DefenciveArtefactID.POWERSHAKER)) {
                    addArtefact(poolBuilder, ArtefactsInit.defenciveArtefact.get(DefenciveArtefactID.POWERSHAKER),                      McdarConfig.config.getIllagerArtefactSpawnRate());
                }
                if (McdarConfig.config.enableStatusInflictingArtefact.get(StatusInflictingArtefactID.SATCHEL_OF_ELEMENTS)) {
                    addArtefact(poolBuilder, ArtefactsInit.statusInflictingArtefact.get(StatusInflictingArtefactID.SATCHEL_OF_ELEMENTS),McdarConfig.config.getIllagerArtefactSpawnRate());
                }
                if (McdarConfig.config.enableStatusInflictingArtefact.get(StatusInflictingArtefactID.SHOCK_POWDER)) {
                    addArtefact(poolBuilder, ArtefactsInit.statusInflictingArtefact.get(StatusInflictingArtefactID.SHOCK_POWDER),       McdarConfig.config.getIllagerArtefactSpawnRate());
                }
                if (McdarConfig.config.enableDefenciveArtefact.get(DefenciveArtefactID.SOUL_HEALER)) {
                    addArtefact(poolBuilder, ArtefactsInit.defenciveArtefact.get(DefenciveArtefactID.SOUL_HEALER),                      McdarConfig.config.getIllagerArtefactSpawnRate());
                }
                if (McdarConfig.config.enableQuiverArtefact.get(QuiverArtefactID.THUNDERING_QUIVER)) {
                    addArtefact(poolBuilder, ArtefactsInit.quiverArtefact.get(QuiverArtefactID.THUNDERING_QUIVER),                      McdarConfig.config.getIllagerArtefactSpawnRate());
                }
                if (McdarConfig.config.enableQuiverArtefact.get(QuiverArtefactID.TORMENT_QUIVER)) {
                    addArtefact(poolBuilder, ArtefactsInit.quiverArtefact.get(QuiverArtefactID.TORMENT_QUIVER),                         McdarConfig.config.getIllagerArtefactSpawnRate());
                }
                if (McdarConfig.config.enableDefenciveArtefact.get(DefenciveArtefactID.TOTEM_OF_REGENERATION)) {
                    addArtefact(poolBuilder, ArtefactsInit.defenciveArtefact.get(DefenciveArtefactID.TOTEM_OF_REGENERATION),            McdarConfig.config.getIllagerArtefactSpawnRate());
                }
                if (McdarConfig.config.enableDefenciveArtefact.get(DefenciveArtefactID.TOTEM_OF_SHIELDING)) {
                    addArtefact(poolBuilder, ArtefactsInit.defenciveArtefact.get(DefenciveArtefactID.TOTEM_OF_SHIELDING),               McdarConfig.config.getIllagerArtefactSpawnRate());
                }
                if (McdarConfig.config.enableDefenciveArtefact.get(DefenciveArtefactID.TOTEM_OF_SOUL_PROTECTION)) {
                    addArtefact(poolBuilder, ArtefactsInit.defenciveArtefact.get(DefenciveArtefactID.TOTEM_OF_SOUL_PROTECTION),         McdarConfig.config.getIllagerArtefactSpawnRate());
                }
                supplier.pool(poolBuilder);
            } else if (VILLAGER_ARTEFACT_LOOT_TABLES.contains(id)){
                poolBuilder = FabricLootPoolBuilder.builder();
                if (McdarConfig.config.enableAgilityArtefact.get(AgilityArtefactID.BOOTS_OF_SWIFTNESS)) {
                    addArtefact(poolBuilder, ArtefactsInit.agilityArtefact.get(AgilityArtefactID.BOOTS_OF_SWIFTNESS),                   McdarConfig.config.getVillagerArtefactSpawnRate());
                }
                if (McdarConfig.config.enableSummoningArtefact.get(SummoningArtefactID.BUZZY_NEST)) {
                    addArtefact(poolBuilder, ArtefactsInit.summoningArtefact.get(SummoningArtefactID.BUZZY_NEST),                       McdarConfig.config.getVillagerArtefactSpawnRate());
                }
                if (McdarConfig.config.enableSummoningArtefact.get(SummoningArtefactID.ENCHANTED_GRASS)) {
                    addArtefact(poolBuilder, ArtefactsInit.summoningArtefact.get(SummoningArtefactID.ENCHANTED_GRASS),                  McdarConfig.config.getVillagerArtefactSpawnRate());
                }
                if (McdarConfig.config.enableQuiverArtefact.get(QuiverArtefactID.FLAMING_QUIVER)) {
                    addArtefact(poolBuilder, ArtefactsInit.quiverArtefact.get(QuiverArtefactID.FLAMING_QUIVER),                         McdarConfig.config.getVillagerArtefactSpawnRate());
                }
                if (McdarConfig.config.enableAgilityArtefact.get(AgilityArtefactID.GHOST_CLOAK)) {
                    addArtefact(poolBuilder, ArtefactsInit.agilityArtefact.get(AgilityArtefactID.GHOST_CLOAK),                          McdarConfig.config.getVillagerArtefactSpawnRate());
                }
                if (McdarConfig.config.enableSummoningArtefact.get(SummoningArtefactID.GOLEM_KIT)) {
                    addArtefact(poolBuilder, ArtefactsInit.summoningArtefact.get(SummoningArtefactID.GOLEM_KIT),                        McdarConfig.config.getVillagerArtefactSpawnRate());
                }
                if (McdarConfig.config.enableDefenciveArtefact.get(DefenciveArtefactID.IRON_HIDE_AMULET)) {
                    addArtefact(poolBuilder, ArtefactsInit.defenciveArtefact.get(DefenciveArtefactID.IRON_HIDE_AMULET),                 McdarConfig.config.getVillagerArtefactSpawnRate());
                }
                if (McdarConfig.config.enableAgilityArtefact.get(AgilityArtefactID.LIGHT_FEATHER)) {
                    addArtefact(poolBuilder, ArtefactsInit.agilityArtefact.get(AgilityArtefactID.LIGHT_FEATHER),                        McdarConfig.config.getVillagerArtefactSpawnRate());
                }
                if (McdarConfig.config.enableSummoningArtefact.get(SummoningArtefactID.TASTY_BONE)) {
                    addArtefact(poolBuilder, ArtefactsInit.summoningArtefact.get(SummoningArtefactID.TASTY_BONE),                       McdarConfig.config.getVillagerArtefactSpawnRate());
                }
                if (McdarConfig.config.enableDamagingArtefact.get(DamagingArtefactID.UPDRAFT_TOME)) {
                    addArtefact(poolBuilder, ArtefactsInit.damagingArtefact.get(DamagingArtefactID.UPDRAFT_TOME),                       McdarConfig.config.getVillagerArtefactSpawnRate());
                }
                if (McdarConfig.config.enableDefenciveArtefact.get(DefenciveArtefactID.WIND_HORN)) {
                    addArtefact(poolBuilder, ArtefactsInit.defenciveArtefact.get(DefenciveArtefactID.WIND_HORN),                        McdarConfig.config.getVillagerArtefactSpawnRate());
                }
                if (McdarConfig.config.enableSummoningArtefact.get(SummoningArtefactID.WONDERFUL_WHEAT)) {
                    addArtefact(poolBuilder, ArtefactsInit.summoningArtefact.get(SummoningArtefactID.WONDERFUL_WHEAT),                  McdarConfig.config.getVillagerArtefactSpawnRate());
                }
                supplier.pool(poolBuilder);
            } else if (WORLD_CHESTS.contains(id)){
                poolBuilder = FabricLootPoolBuilder.builder();
                if (McdarConfig.config.enableDamagingArtefact.get(DamagingArtefactID.BLAST_FUNGUS)) {
                    addArtefact(poolBuilder, ArtefactsInit.damagingArtefact.get(DamagingArtefactID.BLAST_FUNGUS),                       McdarConfig.config.getDungeonArtefactSpawnRate());
                }
                if (McdarConfig.config.enableStatusInflictingArtefact.get(StatusInflictingArtefactID.CORRUPTED_SEEDS)) {
                    addArtefact(poolBuilder, ArtefactsInit.statusInflictingArtefact.get(StatusInflictingArtefactID.CORRUPTED_SEEDS),    McdarConfig.config.getDungeonArtefactSpawnRate());
                }
                if (McdarConfig.config.enableAgilityArtefact.get(AgilityArtefactID.DEATH_CAP_MUSHROOM)) {
                    addArtefact(poolBuilder, ArtefactsInit.agilityArtefact.get(AgilityArtefactID.DEATH_CAP_MUSHROOM),                   McdarConfig.config.getDungeonArtefactSpawnRate());
                }
                if (McdarConfig.config.enableDefenciveArtefact.get(DefenciveArtefactID.ENCHANTERS_TOME)) {
                    addArtefact(poolBuilder, ArtefactsInit.defenciveArtefact.get(DefenciveArtefactID.ENCHANTERS_TOME),                  McdarConfig.config.getDungeonArtefactSpawnRate());
                }
                if (McdarConfig.config.enableStatusInflictingArtefact.get(StatusInflictingArtefactID.GONG_OF_WEAKENING)) {
                    addArtefact(poolBuilder, ArtefactsInit.statusInflictingArtefact.get(StatusInflictingArtefactID.GONG_OF_WEAKENING),  McdarConfig.config.getDungeonArtefactSpawnRate());
                }
                if (McdarConfig.config.enableDamagingArtefact.get(DamagingArtefactID.HARVESTER)) {
                    addArtefact(poolBuilder, ArtefactsInit.damagingArtefact.get(DamagingArtefactID.HARVESTER),                          McdarConfig.config.getDungeonArtefactSpawnRate());
                }
                if (McdarConfig.config.enableDamagingArtefact.get(DamagingArtefactID.LIGHTNING_ROD)) {
                    addArtefact(poolBuilder, ArtefactsInit.damagingArtefact.get(DamagingArtefactID.LIGHTNING_ROD),                      McdarConfig.config.getDungeonArtefactSpawnRate());
                }
                if (McdarConfig.config.enableDefenciveArtefact.get(DefenciveArtefactID.POWERSHAKER)) {
                    addArtefact(poolBuilder, ArtefactsInit.defenciveArtefact.get(DefenciveArtefactID.POWERSHAKER),                      McdarConfig.config.getDungeonArtefactSpawnRate());
                }
                if (McdarConfig.config.enableStatusInflictingArtefact.get(StatusInflictingArtefactID.SATCHEL_OF_ELEMENTS)) {
                    addArtefact(poolBuilder, ArtefactsInit.statusInflictingArtefact.get(StatusInflictingArtefactID.SATCHEL_OF_ELEMENTS),McdarConfig.config.getDungeonArtefactSpawnRate());
                }
                if (McdarConfig.config.enableStatusInflictingArtefact.get(StatusInflictingArtefactID.SHOCK_POWDER)) {
                    addArtefact(poolBuilder, ArtefactsInit.statusInflictingArtefact.get(StatusInflictingArtefactID.SHOCK_POWDER),       McdarConfig.config.getDungeonArtefactSpawnRate());
                }
                if (McdarConfig.config.enableDefenciveArtefact.get(DefenciveArtefactID.SOUL_HEALER)) {
                    addArtefact(poolBuilder, ArtefactsInit.defenciveArtefact.get(DefenciveArtefactID.SOUL_HEALER),                      McdarConfig.config.getDungeonArtefactSpawnRate());
                }
                if (McdarConfig.config.enableQuiverArtefact.get(QuiverArtefactID.THUNDERING_QUIVER)) {
                    addArtefact(poolBuilder, ArtefactsInit.quiverArtefact.get(QuiverArtefactID.THUNDERING_QUIVER),                      McdarConfig.config.getDungeonArtefactSpawnRate());
                }
                if (McdarConfig.config.enableQuiverArtefact.get(QuiverArtefactID.TORMENT_QUIVER)) {
                    addArtefact(poolBuilder, ArtefactsInit.quiverArtefact.get(QuiverArtefactID.TORMENT_QUIVER),                         McdarConfig.config.getDungeonArtefactSpawnRate());
                }
                if (McdarConfig.config.enableDefenciveArtefact.get(DefenciveArtefactID.TOTEM_OF_REGENERATION)) {
                    addArtefact(poolBuilder, ArtefactsInit.defenciveArtefact.get(DefenciveArtefactID.TOTEM_OF_REGENERATION),            McdarConfig.config.getDungeonArtefactSpawnRate());
                }
                if (McdarConfig.config.enableDefenciveArtefact.get(DefenciveArtefactID.TOTEM_OF_SHIELDING)) {
                    addArtefact(poolBuilder, ArtefactsInit.defenciveArtefact.get(DefenciveArtefactID.TOTEM_OF_SHIELDING),               McdarConfig.config.getDungeonArtefactSpawnRate());
                }
                if (McdarConfig.config.enableDefenciveArtefact.get(DefenciveArtefactID.TOTEM_OF_SOUL_PROTECTION)) {
                    addArtefact(poolBuilder, ArtefactsInit.defenciveArtefact.get(DefenciveArtefactID.TOTEM_OF_SOUL_PROTECTION),         McdarConfig.config.getDungeonArtefactSpawnRate());
                }

                if (McdarConfig.config.enableAgilityArtefact.get(AgilityArtefactID.BOOTS_OF_SWIFTNESS)) {
                    addArtefact(poolBuilder, ArtefactsInit.agilityArtefact.get(AgilityArtefactID.BOOTS_OF_SWIFTNESS),                   McdarConfig.config.getDungeonArtefactSpawnRate());
                }
                if (McdarConfig.config.enableSummoningArtefact.get(SummoningArtefactID.BUZZY_NEST)) {
                    addArtefact(poolBuilder, ArtefactsInit.summoningArtefact.get(SummoningArtefactID.BUZZY_NEST),                       McdarConfig.config.getDungeonArtefactSpawnRate());
                }
                if (McdarConfig.config.enableSummoningArtefact.get(SummoningArtefactID.ENCHANTED_GRASS)) {
                    addArtefact(poolBuilder, ArtefactsInit.summoningArtefact.get(SummoningArtefactID.ENCHANTED_GRASS),                  McdarConfig.config.getDungeonArtefactSpawnRate());
                }
                if (McdarConfig.config.enableQuiverArtefact.get(QuiverArtefactID.FLAMING_QUIVER)) {
                    addArtefact(poolBuilder, ArtefactsInit.quiverArtefact.get(QuiverArtefactID.FLAMING_QUIVER),                         McdarConfig.config.getDungeonArtefactSpawnRate());
                }
                if (McdarConfig.config.enableAgilityArtefact.get(AgilityArtefactID.GHOST_CLOAK)) {
                    addArtefact(poolBuilder, ArtefactsInit.agilityArtefact.get(AgilityArtefactID.GHOST_CLOAK),                          McdarConfig.config.getDungeonArtefactSpawnRate());
                }
                if (McdarConfig.config.enableSummoningArtefact.get(SummoningArtefactID.GOLEM_KIT)) {
                    addArtefact(poolBuilder, ArtefactsInit.summoningArtefact.get(SummoningArtefactID.GOLEM_KIT),                        McdarConfig.config.getDungeonArtefactSpawnRate());
                }
                if (McdarConfig.config.enableDefenciveArtefact.get(DefenciveArtefactID.IRON_HIDE_AMULET)) {
                    addArtefact(poolBuilder, ArtefactsInit.defenciveArtefact.get(DefenciveArtefactID.IRON_HIDE_AMULET),                 McdarConfig.config.getDungeonArtefactSpawnRate());
                }
                if (McdarConfig.config.enableAgilityArtefact.get(AgilityArtefactID.LIGHT_FEATHER)) {
                    addArtefact(poolBuilder, ArtefactsInit.agilityArtefact.get(AgilityArtefactID.LIGHT_FEATHER),                        McdarConfig.config.getDungeonArtefactSpawnRate());
                }
                if (McdarConfig.config.enableSummoningArtefact.get(SummoningArtefactID.TASTY_BONE)) {
                    addArtefact(poolBuilder, ArtefactsInit.summoningArtefact.get(SummoningArtefactID.TASTY_BONE),                       McdarConfig.config.getDungeonArtefactSpawnRate());
                }
                if (McdarConfig.config.enableDamagingArtefact.get(DamagingArtefactID.UPDRAFT_TOME)) {
                    addArtefact(poolBuilder, ArtefactsInit.damagingArtefact.get(DamagingArtefactID.UPDRAFT_TOME),                       McdarConfig.config.getDungeonArtefactSpawnRate());
                }
                if (McdarConfig.config.enableDefenciveArtefact.get(DefenciveArtefactID.WIND_HORN)) {
                    addArtefact(poolBuilder, ArtefactsInit.defenciveArtefact.get(DefenciveArtefactID.WIND_HORN),                        McdarConfig.config.getDungeonArtefactSpawnRate());
                }
                if (McdarConfig.config.enableSummoningArtefact.get(SummoningArtefactID.WONDERFUL_WHEAT)) {
                    addArtefact(poolBuilder, ArtefactsInit.summoningArtefact.get(SummoningArtefactID.WONDERFUL_WHEAT),                  McdarConfig.config.getDungeonArtefactSpawnRate());
                }
                supplier.pool(poolBuilder);
            }
        }));
    }

    public static void addArtefact(FabricLootPoolBuilder poolBuilder, Item artefact, float p){
        poolBuilder.rolls(BinomialLootNumberProvider.create(1, p));
        poolBuilder.withEntry(ItemEntry.builder(artefact).build());
    }
}
