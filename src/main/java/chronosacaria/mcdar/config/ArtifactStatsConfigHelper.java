package chronosacaria.mcdar.config;

import java.util.List;
import java.util.Set;

public class ArtifactStatsConfigHelper {

    List<String> comment;
    boolean isEnabled = true;
    int durability;
    int maxCooldownEnchantmentTime;
    int generalSpawnWeight;
    int dungeonSpawnWeight;
    Set<String> generalLootTables;
    Set<String> dungeonLootTables;

    public boolean mcdar$getIsEnabled() {
        return isEnabled;
    }

    public int mcdar$getDurability() {
        return durability;
    }

    public int mcdar$getMaxCooldownEnchantmentTime() {
        return maxCooldownEnchantmentTime;
    }

    public int mcdar$getGeneralArtifactSpawnWeight() {
        return generalSpawnWeight;
    }

    public int mcdar$getDungeonArtifactSpawnWeight() {
        return dungeonSpawnWeight;
    }
    public Set<String> mcdar$getGeneralLootTables() {
        return generalLootTables;
    }

    public Set<String> mcdar$getDungeonLootTables() {
        return dungeonLootTables;
    }

    @SuppressWarnings("unused")
    public ArtifactStatsConfigHelper() {
    }

    public ArtifactStatsConfigHelper(boolean isEnabled, int durability, int maxCooldownEnchantmentTime, int generalSpawnWeight, int dungeonSpawnWeight, Set<String> generalLootTables, Set<String> dungeonLootTables) {
        this.isEnabled = isEnabled;
        this.durability = durability;
        this.maxCooldownEnchantmentTime = maxCooldownEnchantmentTime;
        this.generalSpawnWeight = generalSpawnWeight;
        this.dungeonSpawnWeight = dungeonSpawnWeight;
        this.generalLootTables = generalLootTables;
        this.dungeonLootTables = dungeonLootTables;
    }
    public ArtifactStatsConfigHelper(List<String> comment, boolean isEnabled, int durability, int maxCooldownEnchantmentTime, int generalSpawnWeight, int dungeonSpawnWeight, Set<String> generalLootTables, Set<String> dungeonLootTables) {
        this.comment = comment;
        this.isEnabled = isEnabled;
        this.durability = durability;
        this.maxCooldownEnchantmentTime = maxCooldownEnchantmentTime;
        this.generalSpawnWeight = generalSpawnWeight;
        this.dungeonSpawnWeight = dungeonSpawnWeight;
        this.generalLootTables = generalLootTables;
        this.dungeonLootTables = dungeonLootTables;
    }
}
