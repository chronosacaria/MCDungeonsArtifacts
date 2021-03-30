package chronosacaria.mcdar.init;

import chronosacaria.mcdar.artefacts.*;
import chronosacaria.mcdar.Mcdar;
import net.minecraft.item.Item;

public class ArtefactsInit {
    public static Item BOOTS_OF_SWIFTNESS;
    public static Item CORRUPTED_SEEDS;
    public static Item DEATH_CAP_MUSHROOM;
    public static Item GHOST_CLOAK;
    public static Item GONG_OF_WEAKNESS;
    public static Item HARVESTER;
    public static Item IRON_HIDE_AMULET;
    public static Item LIGHT_FEATHER;
    public static Item LIGHTNING_ROD;
    public static Item TOTEM_OF_REGENERATION;
    public static Item TOTEM_OF_SOUL_PROTECTION;
    public static Item SOUL_HEALER;

    public static void init(){
        BOOTS_OF_SWIFTNESS = new BootsOfSwiftnessItem(new Item.Settings().group(Mcdar.ARTEFACTS),"boots_of_swiftness");
        CORRUPTED_SEEDS = new CorruptedSeedsItem(new Item.Settings().group(Mcdar.ARTEFACTS),"corrupted_seeds");
        DEATH_CAP_MUSHROOM = new DeathCapMushroomItem(new Item.Settings().group(Mcdar.ARTEFACTS), "death_cap_mushroom");
        GHOST_CLOAK = new GhostCloakItem(new Item.Settings().group(Mcdar.ARTEFACTS), "ghost_cloak");
        GONG_OF_WEAKNESS = new GongOfWeakeningItem(new Item.Settings().group(Mcdar.ARTEFACTS), "gong_of_weakness");
        HARVESTER = new HarvesterItem(new Item.Settings().group(Mcdar.ARTEFACTS), "harvester");
        IRON_HIDE_AMULET = new IronHideAmuletItem(new Item.Settings().group(Mcdar.ARTEFACTS), "iron_hide_amulet");
        LIGHT_FEATHER = new LightFeatherItem(new Item.Settings().group(Mcdar.ARTEFACTS),"light_feather");
        LIGHTNING_ROD = new LightningRodItem(new Item.Settings().group(Mcdar.ARTEFACTS), "lightning_rod");
        TOTEM_OF_REGENERATION = new TotemOfRegenerationItem(new Item.Settings().group(Mcdar.ARTEFACTS), "totem_of_regeneration");
        TOTEM_OF_SOUL_PROTECTION = new TotemOfSoulProtection(new Item.Settings().group(Mcdar.ARTEFACTS), "totem_of_soul_protection");
        SOUL_HEALER = new SoulHealerItem(new Item.Settings().group(Mcdar.ARTEFACTS), "soul_healer");
    }
}
