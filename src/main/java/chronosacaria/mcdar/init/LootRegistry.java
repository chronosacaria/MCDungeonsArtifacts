package chronosacaria.mcdar.init;

import chronosacaria.mcdar.config.McdarConfig;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.Item;
import net.minecraft.loot.BinomialLootTableRange;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
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
    public static final Collection<Identifier> DUNGEON_AND_MINESHAFTS =
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
                    LootTables.ABANDONED_MINESHAFT_CHEST, LootTables.SIMPLE_DUNGEON_CHEST
            )));

    public static void init(){
        LootTableLoadingCallback.EVENT.register(((resourceManager, manager, id, supplier, setter) -> {
            if (!id.getNamespace().equals("minecraft"))
                return;

            FabricLootPoolBuilder poolBuilder;

            if (ILLAGER_ARTEFACT_LOOT_TABLES.contains(id)){
                poolBuilder = FabricLootPoolBuilder.builder();
                addArtefact(poolBuilder, ArtefactsInit.BLAST_FUNGUS,                McdarConfig.config.getIllagerArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.CORRUPTED_SEEDS,             McdarConfig.config.getIllagerArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.DEATH_CAP_MUSHROOM,          McdarConfig.config.getIllagerArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.ENCHANTERS_TOME,             McdarConfig.config.getIllagerArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.GONG_OF_WEAKENING,           McdarConfig.config.getIllagerArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.HARVESTER,                   McdarConfig.config.getIllagerArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.LIGHTNING_ROD,               McdarConfig.config.getIllagerArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.POWERSHAKER,                 McdarConfig.config.getIllagerArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.SATCHEL_OF_ELEMENTS,         McdarConfig.config.getIllagerArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.SHOCK_POWDER,                McdarConfig.config.getIllagerArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.SOUL_HEALER,                 McdarConfig.config.getIllagerArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.THUNDERING_QUIVER,           McdarConfig.config.getIllagerArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.TORMENT_QUIVER,              McdarConfig.config.getIllagerArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.TOTEM_OF_REGENERATION,       McdarConfig.config.getIllagerArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.TOTEM_OF_SHIELDING,          McdarConfig.config.getIllagerArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.TOTEM_OF_SOUL_PROTECTION,    McdarConfig.config.getIllagerArtefactSpawnRate());
                supplier.pool(poolBuilder);
            } else if (VILLAGER_ARTEFACT_LOOT_TABLES.contains(id)){
                poolBuilder = FabricLootPoolBuilder.builder();
                addArtefact(poolBuilder, ArtefactsInit.BOOTS_OF_SWIFTNESS,McdarConfig.config.getVillagerArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.BUZZY_NEST,        McdarConfig.config.getVillagerArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.ENCHANTED_GRASS,   McdarConfig.config.getVillagerArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.FLAMING_QUIVER,    McdarConfig.config.getVillagerArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.GHOST_CLOAK,       McdarConfig.config.getVillagerArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.GOLEM_KIT,         McdarConfig.config.getVillagerArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.IRON_HIDE_AMULET,  McdarConfig.config.getVillagerArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.LIGHT_FEATHER,     McdarConfig.config.getVillagerArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.POWERSHAKER,       McdarConfig.config.getVillagerArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.TASTY_BONE,        McdarConfig.config.getVillagerArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.UPDRAFT_TOME,      McdarConfig.config.getVillagerArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.WIND_HORN,         McdarConfig.config.getVillagerArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.WONDERFUL_WHEAT,   McdarConfig.config.getVillagerArtefactSpawnRate());
                supplier.pool(poolBuilder);
            } else if (DUNGEON_AND_MINESHAFTS.contains(id)){
                poolBuilder = FabricLootPoolBuilder.builder();
                addArtefact(poolBuilder, ArtefactsInit.BLAST_FUNGUS,                McdarConfig.config.getDungeonArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.CORRUPTED_SEEDS,             McdarConfig.config.getDungeonArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.DEATH_CAP_MUSHROOM,          McdarConfig.config.getDungeonArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.ENCHANTERS_TOME,             McdarConfig.config.getDungeonArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.GONG_OF_WEAKENING,           McdarConfig.config.getDungeonArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.HARVESTER,                   McdarConfig.config.getDungeonArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.LIGHTNING_ROD,               McdarConfig.config.getDungeonArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.POWERSHAKER,                 McdarConfig.config.getDungeonArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.SATCHEL_OF_ELEMENTS,         McdarConfig.config.getDungeonArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.SHOCK_POWDER,                McdarConfig.config.getDungeonArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.SOUL_HEALER,                 McdarConfig.config.getDungeonArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.THUNDERING_QUIVER,           McdarConfig.config.getDungeonArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.TORMENT_QUIVER,              McdarConfig.config.getDungeonArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.TOTEM_OF_REGENERATION,       McdarConfig.config.getDungeonArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.TOTEM_OF_SHIELDING,          McdarConfig.config.getDungeonArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.TOTEM_OF_SOUL_PROTECTION,    McdarConfig.config.getDungeonArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.BOOTS_OF_SWIFTNESS,          McdarConfig.config.getDungeonArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.BUZZY_NEST,                  McdarConfig.config.getDungeonArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.ENCHANTED_GRASS,             McdarConfig.config.getDungeonArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.FLAMING_QUIVER,              McdarConfig.config.getDungeonArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.GHOST_CLOAK,                 McdarConfig.config.getDungeonArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.GOLEM_KIT,                   McdarConfig.config.getDungeonArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.IRON_HIDE_AMULET,            McdarConfig.config.getDungeonArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.LIGHT_FEATHER,               McdarConfig.config.getDungeonArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.POWERSHAKER,                 McdarConfig.config.getDungeonArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.TASTY_BONE,                  McdarConfig.config.getDungeonArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.UPDRAFT_TOME,                McdarConfig.config.getDungeonArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.WIND_HORN,                   McdarConfig.config.getDungeonArtefactSpawnRate());
                addArtefact(poolBuilder, ArtefactsInit.WONDERFUL_WHEAT,             McdarConfig.config.getDungeonArtefactSpawnRate());
                supplier.pool(poolBuilder);
            }
        }));
    }

    public static void addArtefact(FabricLootPoolBuilder poolBuilder, Item artefact, float p){
        poolBuilder.rolls(new BinomialLootTableRange(1, p));
        poolBuilder.withEntry(ItemEntry.builder(artefact).build());
    }
}
