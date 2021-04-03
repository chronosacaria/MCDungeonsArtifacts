package chronosacaria.mcdar.init;

import chronosacaria.mcdar.artefacts.*;
import chronosacaria.mcdar.Mcdar;
import net.minecraft.item.Item;

public class ArtefactsInit {
    public static Item BOOTS_OF_SWIFTNESS;
    public static Item BUZZY_NEST;
    public static Item CORRUPTED_SEEDS;
    public static Item DEATH_CAP_MUSHROOM;
    public static Item GHOST_CLOAK;
    public static Item GOLEM_KIT;
    public static Item GONG_OF_WEAKENING;
    public static Item HARVESTER;
    public static Item IRON_HIDE_AMULET;
    public static Item LIGHT_FEATHER;
    public static Item LIGHTNING_ROD;
    public static Item TASTY_BONE;
    public static Item TOTEM_OF_REGENERATION;
    public static Item TOTEM_OF_SOUL_PROTECTION;
    public static Item SOUL_HEALER;
    public static Item WONDERFUL_WHEAT;

    public static void init(){
        BOOTS_OF_SWIFTNESS = new BootsOfSwiftnessItem(new Item.Settings().group(Mcdar.ARTEFACTS),"boots_of_swiftness");
        BUZZY_NEST = new BuzzyNestItem(new Item.Settings().group(Mcdar.ARTEFACTS), "buzzy_nest");
        CORRUPTED_SEEDS = new CorruptedSeedsItem(new Item.Settings().group(Mcdar.ARTEFACTS),"corrupted_seeds");
        DEATH_CAP_MUSHROOM = new DeathCapMushroomItem(new Item.Settings().group(Mcdar.ARTEFACTS), "death_cap_mushroom");
        GHOST_CLOAK = new GhostCloakItem(new Item.Settings().group(Mcdar.ARTEFACTS), "ghost_cloak");
        GOLEM_KIT = new GolemKitItem(new Item.Settings().group(Mcdar.ARTEFACTS), "golem_kit");
        GONG_OF_WEAKENING = new GongOfWeakeningItem(new Item.Settings().group(Mcdar.ARTEFACTS), "gong_of_weakening");
        HARVESTER = new HarvesterItem(new Item.Settings().group(Mcdar.ARTEFACTS), "harvester");
        IRON_HIDE_AMULET = new IronHideAmuletItem(new Item.Settings().group(Mcdar.ARTEFACTS), "iron_hide_amulet");
        LIGHT_FEATHER = new LightFeatherItem(new Item.Settings().group(Mcdar.ARTEFACTS),"light_feather");
        LIGHTNING_ROD = new LightningRodItem(new Item.Settings().group(Mcdar.ARTEFACTS), "lightning_rod");
        TASTY_BONE = new TastyBoneItem(new Item.Settings().group(Mcdar.ARTEFACTS), "tasty_bone");
        TOTEM_OF_REGENERATION = new TotemOfRegenerationItem(new Item.Settings().group(Mcdar.ARTEFACTS), "totem_of_regeneration");
        TOTEM_OF_SOUL_PROTECTION = new TotemOfSoulProtection(new Item.Settings().group(Mcdar.ARTEFACTS), "totem_of_soul_protection");
        SOUL_HEALER = new SoulHealerItem(new Item.Settings().group(Mcdar.ARTEFACTS), "soul_healer");
        WONDERFUL_WHEAT = new WonderfulWheatItem(new Item.Settings().group(Mcdar.ARTEFACTS), "wonderful_wheat");
    }
}
