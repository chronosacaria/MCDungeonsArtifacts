package dev.timefall.mcdar.registry;

import dev.timefall.mcdar.ModConstants;
import dev.timefall.mcdar.artifacts.*;
import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;
import me.fzzyhmstrs.fzzy_config.util.platform.Registrar;
import me.fzzyhmstrs.fzzy_config.util.platform.RegistrySupplier;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.InstrumentTags;

import java.util.function.Supplier;


public class ArtifactsRegistry {

    private static final Registrar<Item> ITEM = ConfigApiJava.platform().createRegistrar(ModConstants.MOD_ID, Registries.ITEM);

    //Corrupted Beacon
    //Corrupted Pumpkin
    //Ice Wand          - Mob
    //Scatter Mines     - Mob
    //Shadow Shifter    - Use shadow from lib
    //Soul Lantern      - Mob
    //Totem of Casting  - Status Effect, AOE cloud
    //Vexing Chant      - Mob
    //Void Quiver       - Pull Void Shot from MCDW -> MCDX -> MCDAR

    // Illager Artifacts
    public static final RegistrySupplier<Item> DEATH_CAP_MUSHROOM         = registerArtifact("death_cap_mushroom", DeathCapMushroomItem::new);
    public static final RegistrySupplier<Item> BLAST_FUNGUS               = registerArtifact("blast_fungus", BlastFungusItem::new);
    public static final RegistrySupplier<Item> HARVESTER                  = registerArtifact("harvester", HarvesterItem::new);
    public static final RegistrySupplier<Item> LIGHTNING_ROD              = registerArtifact("lightning_rod", LightningRodItem::new);
    public static final RegistrySupplier<Item> POWERSHAKER                = registerArtifact("powershaker", PowershakerItem::new);
    public static final RegistrySupplier<Item> ENCHANTERS_TOME            = registerArtifact("enchanters_tome", EnchantersTomeItem::new);
    public static final RegistrySupplier<Item> SOUL_HEALER                = registerArtifact("soul_healer", SoulHealerItem::new);
    public static final RegistrySupplier<Item> TOTEM_OF_REGENERATION      = registerArtifact("totem_of_regeneration", TotemOfRegenerationItem::new);
    public static final RegistrySupplier<Item> TOTEM_OF_SHIELDING         = registerArtifact("totem_of_shielding", TotemOfShieldingItem::new);
    public static final RegistrySupplier<Item> TOTEM_OF_SOUL_PROTECTION   = registerArtifact("totem_of_soul_protection", TotemOfSoulProtectionItem::new);
    public static final RegistrySupplier<Item> HARPOON_QUIVER             = registerArtifact("harpoon_quiver", HarpoonQuiverItem::new);
    public static final RegistrySupplier<Item> THUNDERING_QUIVER          = registerArtifact("thundering_quiver", ThunderingQuiverItem::new);
    public static final RegistrySupplier<Item> TORMENT_QUIVER             = registerArtifact("torment_quiver", TormentQuiverItem::new);
    public static final RegistrySupplier<Item> CORRUPTED_SEEDS            = registerArtifact("corrupted_seeds", CorruptedSeedsItem::new);
    public static final RegistrySupplier<Item> GONG_OF_WEAKENING          = registerArtifact("gong_of_weakening", GongOfWeakeningItem::new);
    public static final RegistrySupplier<Item> LOVE_MEDALLION             = registerArtifact("love_medallion", LoveMedallionItem::new);
    public static final RegistrySupplier<Item> SATCHEL_OF_ELEMENTS        = registerArtifact("satchel_of_elements", SatchelOfElementsItem::new);
    public static final RegistrySupplier<Item> SHOCK_POWDER               = registerArtifact("shock_powder", ShockPowderItem::new);

    // Villager Artifacts
    public static final RegistrySupplier<Item> BOOTS_OF_SWIFTNESS         = registerArtifact("boots_of_swiftness", BootsOfSwiftnessItem::new);
    public static final RegistrySupplier<Item> GHOST_CLOAK                = registerArtifact("ghost_cloak", GhostCloakItem::new);
    public static final RegistrySupplier<Item> LIGHT_FEATHER              = registerArtifact("light_feather", LightFeatherItem::new);
    public static final RegistrySupplier<Item> UPDRAFT_TOME               = registerArtifact("updraft_tome", UpdraftTomeItem::new);
    public static final RegistrySupplier<Item> IRON_HIDE_AMULET           = registerArtifact("iron_hide_amulet", IronHideAmuletItem::new);
    public static final RegistrySupplier<Item> WIND_HORN                  = registerArtifact("wind_horn",  () -> new WindHornItem(InstrumentTags.GOAT_HORNS));
    public static final RegistrySupplier<Item> FLAMING_QUIVER             = registerArtifact("flaming_quiver", FlamingQuiverItem::new);
    public static final RegistrySupplier<Item> BUZZY_NEST                 = registerArtifact("buzzy_nest", BuzzyNestItem::new);
    public static final RegistrySupplier<Item> ENCHANTED_GRASS            = registerArtifact("enchanted_grass", EnchantedGrassItem::new);
    public static final RegistrySupplier<Item> GOLEM_KIT                  = registerArtifact("golem_kit", GolemKitItem::new);
    public static final RegistrySupplier<Item> TASTY_BONE                 = registerArtifact("tasty_bone", TastyBoneItem::new);
    public static final RegistrySupplier<Item> WONDERFUL_WHEAT            = registerArtifact("wonderful_wheat", WonderfulWheatItem::new);
    public static final RegistrySupplier<Item> SATCHEL_OF_SNACKS          = registerArtifact("satchel_of_snacks", SatchelOfSnacksItem::new);
    public static final RegistrySupplier<Item> SATCHEL_OF_ELIXIRS         = registerArtifact("satchel_of_elixirs", SatchelOfElixirsItem::new);

    // Void Artifacts
    public static final RegistrySupplier<Item> TOME_OF_DUPLICATION         = registerArtifact("tome_of_duplication", TomeOfDuplicationItem::new);

    protected static RegistrySupplier<Item> registerArtifact(String id, Supplier<Item> itemSupplier) {
        return ITEM.register(id, itemSupplier);
    }

    public static void register() {
        ITEM.init();
    }

}
