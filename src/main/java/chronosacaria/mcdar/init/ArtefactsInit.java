package chronosacaria.mcdar.init;

import chronosacaria.mcdar.artefacts.*;
import chronosacaria.mcdar.Mcdar;
import net.minecraft.item.Item;

public class ArtefactsInit {
    public static Item BOOTS_OF_SWIFTNESS;
    public static Item BUZZY_NEST;
    public static Item CORRUPTED_SEEDS;
    public static Item DEATH_CAP_MUSHROOM;
    public static Item ENCHANTED_GRASS;
    public static Item ENCHANTERS_TOME;
    public static Item FLAMING_QUIVER;
    public static Item GHOST_CLOAK;
    public static Item GOLEM_KIT;
    public static Item GONG_OF_WEAKENING;
    public static Item HARVESTER;
    public static Item IRON_HIDE_AMULET;
    public static Item LIGHT_FEATHER;
    public static Item LIGHTNING_ROD;
    public static Item SATCHEL_OF_ELEMENTS;
    public static Item SHOCK_POWDER;
    public static Item TASTY_BONE;
    public static Item THUNDERING_QUIVER;
    public static Item TORMENT_QUIVER;
    public static Item TOTEM_OF_REGENERATION;
    public static Item TOTEM_OF_SHIELDING;
    public static Item TOTEM_OF_SOUL_PROTECTION;
    public static Item UPDRAFT_TOME;
    public static Item SOUL_HEALER;
    public static Item WONDERFUL_WHEAT;

    public static void init(){
        BOOTS_OF_SWIFTNESS = new BootsOfSwiftnessItem(new Item.Settings().group(Mcdar.ARTEFACTS),"boots_of_swiftness");
        BUZZY_NEST = new BuzzyNestItem(new Item.Settings().group(Mcdar.ARTEFACTS), "buzzy_nest");
        CORRUPTED_SEEDS = new CorruptedSeedsItem(new Item.Settings().group(Mcdar.ARTEFACTS),"corrupted_seeds");
        DEATH_CAP_MUSHROOM = new DeathCapMushroomItem(new Item.Settings().group(Mcdar.ARTEFACTS), "death_cap_mushroom");
        ENCHANTED_GRASS = new EnchantedGrassItem(new Item.Settings().group(Mcdar.ARTEFACTS), "enchanted_grass");
        ENCHANTERS_TOME = new EnchantersTomeItem(new Item.Settings().group(Mcdar.ARTEFACTS), "enchanters_tome");
        FLAMING_QUIVER = new FlamingQuiverItem(new Item.Settings().group(Mcdar.ARTEFACTS), "flaming_quiver");
        GHOST_CLOAK = new GhostCloakItem(new Item.Settings().group(Mcdar.ARTEFACTS), "ghost_cloak");
        GOLEM_KIT = new GolemKitItem(new Item.Settings().group(Mcdar.ARTEFACTS), "golem_kit");
        GONG_OF_WEAKENING = new GongOfWeakeningItem(new Item.Settings().group(Mcdar.ARTEFACTS), "gong_of_weakening");
        HARVESTER = new HarvesterItem(new Item.Settings().group(Mcdar.ARTEFACTS), "harvester");
        IRON_HIDE_AMULET = new IronHideAmuletItem(new Item.Settings().group(Mcdar.ARTEFACTS), "iron_hide_amulet");
        LIGHT_FEATHER = new LightFeatherItem(new Item.Settings().group(Mcdar.ARTEFACTS),"light_feather");
        LIGHTNING_ROD = new LightningRodItem(new Item.Settings().group(Mcdar.ARTEFACTS), "lightning_rod");
        SATCHEL_OF_ELEMENTS = new SatchelOfElementsItem(new Item.Settings().group(Mcdar.ARTEFACTS), "satchel_of_elements");
        SHOCK_POWDER = new ShockPowderItem(new Item.Settings().group(Mcdar.ARTEFACTS), "shock_powder");
        TASTY_BONE = new TastyBoneItem(new Item.Settings().group(Mcdar.ARTEFACTS), "tasty_bone");
        THUNDERING_QUIVER = new ThunderingQuiverItem(new Item.Settings().group(Mcdar.ARTEFACTS), "thundering_quiver");
        TORMENT_QUIVER = new TormentQuiverItem(new Item.Settings().group(Mcdar.ARTEFACTS), "torment_quiver");
        TOTEM_OF_REGENERATION = new TotemOfRegenerationItem(new Item.Settings().group(Mcdar.ARTEFACTS), "totem_of_regeneration");
        TOTEM_OF_SHIELDING = new TotemOfShieldingItem(new Item.Settings().group(Mcdar.ARTEFACTS), "totem_of_shielding");
        TOTEM_OF_SOUL_PROTECTION = new TotemOfSoulProtectionItem(new Item.Settings().group(Mcdar.ARTEFACTS), "totem_of_soul_protection");
        UPDRAFT_TOME = new UpdraftTomeItem(new Item.Settings().group(Mcdar.ARTEFACTS), "updraft_tome");
        SOUL_HEALER = new SoulHealerItem(new Item.Settings().group(Mcdar.ARTEFACTS), "soul_healer");
        WONDERFUL_WHEAT = new WonderfulWheatItem(new Item.Settings().group(Mcdar.ARTEFACTS), "wonderful_wheat");
    }
}
