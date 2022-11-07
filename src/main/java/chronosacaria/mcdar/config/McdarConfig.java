package chronosacaria.mcdar.config;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.enchants.EnchantID;
import chronosacaria.mcdar.enums.*;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

import java.util.EnumMap;

@Config(name = Mcdar.MOD_ID)
public class McdarConfig implements ConfigData {

    public static final McdarConfig CONFIG;

    static {
        AutoConfig.register(McdarConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(McdarConfig.class).getConfig();
    }

    // CONFIG contents:
    public final EnumMap<EnchantID, Boolean> ENABLE_ENCHANTMENT = new EnumMap<>(EnchantID.class);
    public final EnumMap<DamagingArtifactID, Boolean> ENABLE_DAMAGING_ARTIFACT = new EnumMap<>(DamagingArtifactID.class);
    public final EnumMap<StatusInflictingArtifactID, Boolean> ENABLE_STATUS_INFLICTING_ARTIFACT = new EnumMap<>(StatusInflictingArtifactID.class);
    public final EnumMap<QuiverArtifactID, Boolean> ENABLE_QUIVER_ARTIFACT = new EnumMap<>(QuiverArtifactID.class);
    public final EnumMap<SummoningArtifactID, Boolean> ENABLE_SUMMONING_ARTIFACT = new EnumMap<>(SummoningArtifactID.class);
    public final EnumMap<AgilityArtifactID, Boolean> ENABLE_AGILITY_ARTIFACT = new EnumMap<>(AgilityArtifactID.class);
    public final EnumMap<DefensiveArtifactID, Boolean> ENABLE_DEFENSIVE_ARTIFACT = new EnumMap<>(DefensiveArtifactID.class);

    @Comment("Artifact Durability")
    public final int AGILITY_ARTIFACT_DURABILITY = 64;
    public final int DAMAGING_ARTIFACT_DURABILITY = 64;
    public final int DEFENSIVE_ARTIFACT_DURABILITY = 64;
    public final int QUIVER_ARTIFACT_DURABILITY = 7;
    public final int STATUS_ARTIFACT_DURABILITY = 64;
    public final int SUMMONING_ARTIFACT_DURABILITY = 8;

    public int getAgilityArtifactDurability(){
        return AGILITY_ARTIFACT_DURABILITY;
    }
    public int getDamagingArtifactDurability(){
        return DAMAGING_ARTIFACT_DURABILITY;
    }
    public int getDefensiveArtifactDurability(){
        return DEFENSIVE_ARTIFACT_DURABILITY;
    }
    public int getQuiverArtifactDurability(){
        return QUIVER_ARTIFACT_DURABILITY;
    }
    public int getStatusArtifactDurability(){
        return STATUS_ARTIFACT_DURABILITY;
    }
    public int getSummoningArtifactDurability(){
        return SUMMONING_ARTIFACT_DURABILITY;
    }

    @Comment("Villager Artifact Spawn Rate (Percentage where 1.0 = 100%)")
    public final float VILLAGER_ARTIFACT_SPAWN_RATE = 0.25F;

    public float getVillagerArtifactSpawnRate(){
        return VILLAGER_ARTIFACT_SPAWN_RATE;
    }

    @Comment("Illager Artifact Spawn Rate (Percentage where 1.0 = 100%)")
    public final float ILLAGER_ARTIFACT_SPAWN_RATE = 0.25F;

    public float getIllagerArtifactSpawnRate(){
        return ILLAGER_ARTIFACT_SPAWN_RATE;
    }

    @Comment("World Artifact Spawn Rate (Percentage where 1.0 = 100%)")
    public final float WORLD_ARTIFACT_SPAWN_RATE = 0.10F;

    public float getWorldArtifactSpawnRate(){
        return WORLD_ARTIFACT_SPAWN_RATE;
    }

    // set CONFIG defaults
    public McdarConfig(){
        for (EnchantID enchantID : EnchantID.values())
            ENABLE_ENCHANTMENT.put(enchantID, true);

        for (AgilityArtifactID artefactID : AgilityArtifactID.values())
            ENABLE_AGILITY_ARTIFACT.put(artefactID, true);
        for (DamagingArtifactID artefactID : DamagingArtifactID.values())
            ENABLE_DAMAGING_ARTIFACT.put(artefactID, true);
        for (DefensiveArtifactID artefactID : DefensiveArtifactID.values())
            ENABLE_DEFENSIVE_ARTIFACT.put(artefactID, true);
        for (QuiverArtifactID artefactID : QuiverArtifactID.values())
            ENABLE_QUIVER_ARTIFACT.put(artefactID, true);
        for (StatusInflictingArtifactID artefactID : StatusInflictingArtifactID.values())
            ENABLE_STATUS_INFLICTING_ARTIFACT.put(artefactID, true);
        for (SummoningArtifactID artefactID : SummoningArtifactID.values())
            ENABLE_SUMMONING_ARTIFACT.put(artefactID, true);
    }

}
