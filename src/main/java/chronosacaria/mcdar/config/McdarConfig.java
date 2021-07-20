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

    public static final McdarConfig config;

    static {
        AutoConfig.register(McdarConfig.class, JanksonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(McdarConfig.class).getConfig();
    }

    // config contents:
    public EnumMap<EnchantID, Boolean> enableEnchantment = new EnumMap<>(EnchantID.class);
    public EnumMap<DamagingArtefactID, Boolean> enableDamagingArtefact = new EnumMap<>(DamagingArtefactID.class);
    public EnumMap<StatusInflictingArtefactID, Boolean> enableStatusInflictingArtefact = new EnumMap<>(StatusInflictingArtefactID.class);
    public EnumMap<QuiverArtefactID, Boolean> enableQuiverArtefact = new EnumMap<>(QuiverArtefactID.class);
    public EnumMap<SummoningArtefactID, Boolean> enableSummoningArtefact = new EnumMap<>(SummoningArtefactID.class);
    public EnumMap<AgilityArtefactID, Boolean> enableAgilityArtefact = new EnumMap<>(AgilityArtefactID.class);
    public EnumMap<DefenciveArtefactID, Boolean> enableDefenciveArtefact = new EnumMap<>(DefenciveArtefactID.class);


    @Comment("Artefact Durability")
    public int agilityArtefactDurability = 64;
    public int damagingArtefactDurability = 64;
    public int defenciveArtefactDurability = 64;
    public int quiverArtefactDurability = 7;
    public int statusArtefactDurability = 64;
    public int summoningArtefactDurability = 8;

    public int getAgilityArtefactDurability(){
        return agilityArtefactDurability;
    }
    public int getDamagingArtefactDurability(){
        return damagingArtefactDurability;
    }
    public int getDefenciveArtefactDurability(){
        return defenciveArtefactDurability;
    }
    public int getQuiverArtefactDurability(){
        return quiverArtefactDurability;
    }
    public int getStatusArtefactDurability(){
        return statusArtefactDurability;
    }
    public int getSummoningArtefactDurability(){
        return summoningArtefactDurability;
    }

    @Comment("Villager Artefact Spawn Rate (Percentage where 1.0 = 100%)")
    public float villagerArtefactSpawnRate = 0.25F;

    public float getVillagerArtefactSpawnRate(){
        return villagerArtefactSpawnRate;
    }

    @Comment("Illager Artefact Spawn Rate (Percentage where 1.0 = 100%)")
    public float illagerArtefactSpawnRate = 0.25F;

    public float getIllagerArtefactSpawnRate(){
        return illagerArtefactSpawnRate;
    }

    @Comment("Dungeon Artefact Spawn Rate (Percentage where 1.0 = 100%)")
    public float dungeonArtefactSpawnRate = 0.10F;

    public float getDungeonArtefactSpawnRate(){
        return dungeonArtefactSpawnRate;
    }

    // set config defaults
    public McdarConfig(){
        for (EnchantID enchantID : EnchantID.values()) {
            enableEnchantment.put(enchantID, true);
        }
        for (AgilityArtefactID artefactID : AgilityArtefactID.values()){
            enableAgilityArtefact.put(artefactID, true);
        }
        for (DamagingArtefactID artefactID : DamagingArtefactID.values()){
            enableDamagingArtefact.put(artefactID, true);
        }
        for (DefenciveArtefactID artefactID : DefenciveArtefactID.values()){
            enableDefenciveArtefact.put(artefactID, true);
        }
        for (QuiverArtefactID artefactID : QuiverArtefactID.values()){
            enableQuiverArtefact.put(artefactID, true);
        }
        for (StatusInflictingArtefactID artefactID : StatusInflictingArtefactID.values()){
            enableStatusInflictingArtefact.put(artefactID, true);
        }
        for (SummoningArtefactID artefactID : SummoningArtefactID.values()){
            enableSummoningArtefact.put(artefactID, true);
        }
    }

}
