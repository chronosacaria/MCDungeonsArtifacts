package chronosacaria.mcdar.config;

import chronosacaria.mcdar.Mcdar;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = Mcdar.MOD_ID)
public class McdarConfig implements ConfigData {

    public static final McdarConfig config;

    static {
        AutoConfig.register(McdarConfig.class, JanksonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(McdarConfig.class).getConfig();
    }

    @Comment("Register the following Artefacts?")
    public boolean enableBlastFungus = true;
    public boolean enableBootsOfSwiftness = true;
    public boolean enableBuzzyNest = true;
    public boolean enableCorruptedSeeds = true;
    public boolean enableDeathCapMushroom = true;
    public boolean enableEnchantedGrass = true;
    public boolean enableEnchantersTome = true;
    public boolean enableFlamingQuiver = true;
    public boolean enableGhostCloak = true;
    public boolean enableGolemKit = true;
    public boolean enableGongOfWeakening = true;
    public boolean enableHarvester = true;
    public boolean enableIronHideAmulet = true;
    public boolean enableLightFeather = true;
    public boolean enableLightningRod = true;
    public boolean enablePowershaker = true;
    public boolean enableSatchelOfElements = true;
    public boolean enableShockPowder = true;
    public boolean enableSoulHealer = true;
    public boolean enableTastyBone = true;
    public boolean enableThunderingQuiver = true;
    public boolean enableTormentQuiver = true;
    public boolean enableTotemOfRegeneration = true;
    public boolean enableTotemOfShielding = true;
    public boolean enableTotemSoulProtection = true;
    public boolean enableUpdraftTome = true;
    public boolean enableWindHorn = true;
    public boolean enableWonderfulWheat = true;

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


}
