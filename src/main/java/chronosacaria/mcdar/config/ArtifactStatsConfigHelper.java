package chronosacaria.mcdar.config;

import java.util.List;

public class ArtifactStatsConfigHelper {

    List<String> comment;
    boolean isEnabled = true;
    int durability;
    int maxCooldownEnchantmentTime;
    float generalSpawnRate;
    float dungeonSpawnRate;

    public boolean mcdar$getIsEnabled() {
        return isEnabled;
    }

    public int mcdar$getDurability() {
        return durability;
    }

    public int mcdar$getMaxCooldownEnchantmentTime() {
        return maxCooldownEnchantmentTime;
    }

    public float mcdar$getGeneralArtifactSpawnRate() {
        return generalSpawnRate;
    }

    public float mcdar$getDungeonArtifactSpawnRate() {
        return dungeonSpawnRate;
    }

    @SuppressWarnings("unused")
    public ArtifactStatsConfigHelper() {
    }

    public ArtifactStatsConfigHelper(boolean isEnabled, int durability, int maxCooldownEnchantmentTime, float generalSpawnRate, float dungeonSpawnRate) {
        this.isEnabled = isEnabled;
        this.durability = durability;
        this.maxCooldownEnchantmentTime = maxCooldownEnchantmentTime;
        this.generalSpawnRate = generalSpawnRate;
        this.dungeonSpawnRate = dungeonSpawnRate;
    }
    public ArtifactStatsConfigHelper(List<String> comment, boolean isEnabled, int durability, int maxCooldownEnchantmentTime, float generalSpawnRate, float dungeonSpawnRate) {
        this.comment = comment;
        this.isEnabled = isEnabled;
        this.durability = durability;
        this.maxCooldownEnchantmentTime = maxCooldownEnchantmentTime;
        this.generalSpawnRate = generalSpawnRate;
        this.dungeonSpawnRate = dungeonSpawnRate;
    }
}
