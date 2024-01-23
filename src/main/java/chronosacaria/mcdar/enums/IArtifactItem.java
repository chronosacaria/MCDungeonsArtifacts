package chronosacaria.mcdar.enums;

import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.util.Identifier;

import java.util.Set;

public interface IArtifactItem {

    Boolean mcdar$isEnabled();

    Item mcdar$getItem();

    Set<String> mcdar$getGeneralLootTables();
    Set<String> mcdar$getDungeonLootTables();

    void mcdar$insertIntoGeneralLootPool(LootPool.Builder lootPoolBuilder, Identifier id);
    void mcdar$insertIntoDungeonLootPool(LootPool.Builder lootPoolBuilder, Identifier id);
}
