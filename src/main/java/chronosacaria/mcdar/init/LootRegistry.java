package chronosacaria.mcdar.init;

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
                addArtefact(poolBuilder, ArtefactsInit.CORRUPTED_SEEDS,             0.25F);
                addArtefact(poolBuilder, ArtefactsInit.DEATH_CAP_MUSHROOM,          0.25F);
                addArtefact(poolBuilder, ArtefactsInit.ENCHANTERS_TOME,             0.25F);
                addArtefact(poolBuilder, ArtefactsInit.GONG_OF_WEAKENING,           0.25F);
                addArtefact(poolBuilder, ArtefactsInit.HARVESTER,                   0.25F);
                addArtefact(poolBuilder, ArtefactsInit.LIGHTNING_ROD,               0.25F);
                addArtefact(poolBuilder, ArtefactsInit.POWERSHAKER,                 0.25F);
                addArtefact(poolBuilder, ArtefactsInit.SATCHEL_OF_ELEMENTS,         0.25F);
                addArtefact(poolBuilder, ArtefactsInit.SHOCK_POWDER,                0.25F);
                addArtefact(poolBuilder, ArtefactsInit.SOUL_HEALER,                 0.25F);
                addArtefact(poolBuilder, ArtefactsInit.THUNDERING_QUIVER,           0.25F);
                addArtefact(poolBuilder, ArtefactsInit.TORMENT_QUIVER,              0.25F);
                addArtefact(poolBuilder, ArtefactsInit.TOTEM_OF_REGENERATION,       0.25F);
                addArtefact(poolBuilder, ArtefactsInit.TOTEM_OF_SHIELDING,          0.25F);
                addArtefact(poolBuilder, ArtefactsInit.TOTEM_OF_SOUL_PROTECTION,    0.25F);
                supplier.pool(poolBuilder);
            } else if (VILLAGER_ARTEFACT_LOOT_TABLES.contains(id)){
                poolBuilder = FabricLootPoolBuilder.builder();
                addArtefact(poolBuilder, ArtefactsInit.BOOTS_OF_SWIFTNESS,0.25F);
                addArtefact(poolBuilder, ArtefactsInit.BUZZY_NEST,        0.25F);
                addArtefact(poolBuilder, ArtefactsInit.ENCHANTED_GRASS,   0.25F);
                addArtefact(poolBuilder, ArtefactsInit.FLAMING_QUIVER,    0.25F);
                addArtefact(poolBuilder, ArtefactsInit.GHOST_CLOAK,       0.25F);
                addArtefact(poolBuilder, ArtefactsInit.GOLEM_KIT,         0.25F);
                addArtefact(poolBuilder, ArtefactsInit.IRON_HIDE_AMULET,  0.25F);
                addArtefact(poolBuilder, ArtefactsInit.LIGHT_FEATHER,     0.25F);
                addArtefact(poolBuilder, ArtefactsInit.POWERSHAKER,       0.25F);
                addArtefact(poolBuilder, ArtefactsInit.TASTY_BONE,        0.25F);
                addArtefact(poolBuilder, ArtefactsInit.UPDRAFT_TOME,      0.25F);
                addArtefact(poolBuilder, ArtefactsInit.WONDERFUL_WHEAT,   0.25F);
                supplier.pool(poolBuilder);
            } else if (DUNGEON_AND_MINESHAFTS.contains(id)){
                poolBuilder = FabricLootPoolBuilder.builder();
                addArtefact(poolBuilder, ArtefactsInit.CORRUPTED_SEEDS,             0.10F);
                addArtefact(poolBuilder, ArtefactsInit.DEATH_CAP_MUSHROOM,          0.10F);
                addArtefact(poolBuilder, ArtefactsInit.ENCHANTERS_TOME,             0.10F);
                addArtefact(poolBuilder, ArtefactsInit.GONG_OF_WEAKENING,           0.10F);
                addArtefact(poolBuilder, ArtefactsInit.HARVESTER,                   0.10F);
                addArtefact(poolBuilder, ArtefactsInit.LIGHTNING_ROD,               0.10F);
                addArtefact(poolBuilder, ArtefactsInit.POWERSHAKER,                 0.10F);
                addArtefact(poolBuilder, ArtefactsInit.SATCHEL_OF_ELEMENTS,         0.10F);
                addArtefact(poolBuilder, ArtefactsInit.SHOCK_POWDER,                0.10F);
                addArtefact(poolBuilder, ArtefactsInit.SOUL_HEALER,                 0.10F);
                addArtefact(poolBuilder, ArtefactsInit.THUNDERING_QUIVER,           0.10F);
                addArtefact(poolBuilder, ArtefactsInit.TORMENT_QUIVER,              0.10F);
                addArtefact(poolBuilder, ArtefactsInit.TOTEM_OF_REGENERATION,       0.10F);
                addArtefact(poolBuilder, ArtefactsInit.TOTEM_OF_SHIELDING,          0.10F);
                addArtefact(poolBuilder, ArtefactsInit.TOTEM_OF_SOUL_PROTECTION,    0.10F);
                addArtefact(poolBuilder, ArtefactsInit.BOOTS_OF_SWIFTNESS,          0.10F);
                addArtefact(poolBuilder, ArtefactsInit.BUZZY_NEST,                  0.10F);
                addArtefact(poolBuilder, ArtefactsInit.ENCHANTED_GRASS,             0.10F);
                addArtefact(poolBuilder, ArtefactsInit.FLAMING_QUIVER,              0.10F);
                addArtefact(poolBuilder, ArtefactsInit.GHOST_CLOAK,                 0.10F);
                addArtefact(poolBuilder, ArtefactsInit.GOLEM_KIT,                   0.10F);
                addArtefact(poolBuilder, ArtefactsInit.IRON_HIDE_AMULET,            0.10F);
                addArtefact(poolBuilder, ArtefactsInit.LIGHT_FEATHER,               0.10F);
                addArtefact(poolBuilder, ArtefactsInit.POWERSHAKER,                 0.10F);
                addArtefact(poolBuilder, ArtefactsInit.TASTY_BONE,                  0.10F);
                addArtefact(poolBuilder, ArtefactsInit.UPDRAFT_TOME,                0.10F);
                addArtefact(poolBuilder, ArtefactsInit.WONDERFUL_WHEAT,             0.10F);
                supplier.pool(poolBuilder);
            }
        }));
    }

    public static void addArtefact(FabricLootPoolBuilder poolBuilder, Item artefact, float p){
        poolBuilder.rolls(new BinomialLootTableRange(1, p));
        poolBuilder.withEntry(ItemEntry.builder(artefact).build());
    }
}
