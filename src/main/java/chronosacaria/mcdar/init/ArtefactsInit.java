package chronosacaria.mcdar.init;

import chronosacaria.mcdar.artefacts.*;
import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.enums.*;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

import java.util.EnumMap;

import static chronosacaria.mcdar.config.McdarConfig.config;

public class ArtefactsInit {

    public static final EnumMap<DamagingArtefactID, Item> damagingArtefact = new EnumMap<>(DamagingArtefactID.class);
    public static final EnumMap<StatusInflictingArtefactID, Item> statusInflictingArtefact = new EnumMap<>(StatusInflictingArtefactID.class);
    public static final EnumMap<QuiverArtefactID, Item> quiverArtefact = new EnumMap<>(QuiverArtefactID.class);
    public static final EnumMap<SummoningArtefactID, Item> summoningArtefact = new EnumMap<>(SummoningArtefactID.class);
    public static final EnumMap<AgilityArtefactID, Item> agilityArtefact = new EnumMap<>(AgilityArtefactID.class);
    public static final EnumMap<DefenciveArtefactID, Item> defenciveArtefact = new EnumMap<>(DefenciveArtefactID.class);

    public static void init() {

        for (DamagingArtefactID artefactID : DamagingArtefactID.values()) {
            if (!config.enableDamagingArtefact.get(artefactID))
                continue;

            Item item;

            switch (artefactID) {
                case BLAST_FUNGUS:
                    item = new BlastFungusItem(artefactID);
                    break;
                //case CORRUPTED_BEACON:
                //    item = new CorruptedBeaconItem(artefactID);
                //    break;
                case HARVESTER:
                    item = new HarvesterItem(artefactID);
                    break;
                case LIGHTNING_ROD:
                    item = new LightningRodItem(artefactID);
                    break;
                case UPDRAFT_TOME:
                    item = new UpdraftTomeItem(artefactID);
                    break;
                default:
                    item = new ArtefactDamagingItem(artefactID);
                    break;
            }
            damagingArtefact.put(artefactID, item);
            registerArtefacts(artefactID.toString().toLowerCase(), item);
        }
        for (StatusInflictingArtefactID artefactID : StatusInflictingArtefactID.values()) {
            if (!config.enableStatusInflictingArtefact.get(artefactID))
                continue;

            Item item;

            switch (artefactID) {
                case CORRUPTED_SEEDS:
                    item = new CorruptedSeedsItem(artefactID);
                    break;
                case GONG_OF_WEAKENING:
                    item = new GongOfWeakeningItem(artefactID);
                    break;
                case SATCHEL_OF_ELEMENTS:
                    item = new SatchelOfElementsItem(artefactID);
                    break;
                case SHOCK_POWDER:
                    item = new ShockPowderItem(artefactID);
                    break;
                default:
                    item = new ArtefactStatusInflictingItem(artefactID);
                    break;
            }
            statusInflictingArtefact.put(artefactID, item);
            registerArtefacts(artefactID.toString().toLowerCase(), item);
        }
        for (QuiverArtefactID artefactID : QuiverArtefactID.values()) {
            if (!config.enableQuiverArtefact.get(artefactID))
                continue;

            Item item;

            switch (artefactID) {
                case FLAMING_QUIVER:
                    item = new FlamingQuiverItem(artefactID);
                    break;
                case HARPOON_QUIVER:
                    item = new HarpoonQuiverItem(artefactID);
                    break;
                case THUNDERING_QUIVER:
                    item = new ThunderingQuiverItem(artefactID);
                    break;
                case TORMENT_QUIVER:
                    item = new TormentQuiverItem(artefactID);
                    break;
                default:
                    item = new ArtefactQuiverItem(artefactID);
                    break;
            }
            quiverArtefact.put(artefactID, item);
            registerArtefacts(artefactID.toString().toLowerCase(), item);
        }
        for (SummoningArtefactID artefactID : SummoningArtefactID.values()) {
            if (!config.enableSummoningArtefact.get(artefactID))
                continue;

            Item item;

            switch (artefactID) {
                case BUZZY_NEST:
                    item = new BuzzyNestItem(artefactID);
                    break;
                case ENCHANTED_GRASS:
                    item = new EnchantedGrassItem(artefactID);
                    break;
                case GOLEM_KIT:
                    item = new GolemKitItem(artefactID);
                    break;
                case TASTY_BONE:
                    item = new TastyBoneItem(artefactID);
                    break;
                case WONDERFUL_WHEAT:
                    item = new WonderfulWheatItem(artefactID);
                    break;
                default:
                    item = new ArtefactSummoningItem(artefactID);
                    break;
            }
            summoningArtefact.put(artefactID, item);
            registerArtefacts(artefactID.toString().toLowerCase(), item);
        }
        for (AgilityArtefactID artefactID : AgilityArtefactID.values()) {
            if (!config.enableAgilityArtefact.get(artefactID))
                continue;

            Item item;

            switch (artefactID) {
                case BOOTS_OF_SWIFTNESS:
                    item = new BootsOfSwiftnessItem(artefactID);
                    break;
                case DEATH_CAP_MUSHROOM:
                    item = new DeathCapMushroomItem(artefactID);
                    break;
                case GHOST_CLOAK:
                    item = new GhostCloakItem(artefactID);
                    break;
                case LIGHT_FEATHER:
                    item = new LightFeatherItem(artefactID);
                    break;
                default:
                    item = new ArtefactAgilityItem(artefactID);
                    break;
            }
            agilityArtefact.put(artefactID, item);
            registerArtefacts(artefactID.toString().toLowerCase(), item);
        }
        for (DefenciveArtefactID artefactID : DefenciveArtefactID.values()) {
            if (!config.enableDefenciveArtefact.get(artefactID))
                continue;

            Item item;

            switch (artefactID) {
                case ENCHANTERS_TOME:
                    item = new EnchantersTomeItem(artefactID);
                    break;
                case IRON_HIDE_AMULET:
                    item = new IronHideAmuletItem(artefactID);
                    break;
                case POWERSHAKER:
                    item = new PowershakerItem(artefactID);
                    break;
                //case SATCHEL_OF_ELIXIRS:
                //    item = new SatchelOfElixirsItem(artefactID);
                //    break;
                //case SATCHEL_OF_SNACKS:
                //    item = new SatchelOfSnacksItem(artefactID);
                //    break;
                case SOUL_HEALER:
                    item = new SoulHealerItem(artefactID);
                    break;
                case TOTEM_OF_REGENERATION:
                    item = new TotemOfRegenerationItem(artefactID);
                    break;
                case TOTEM_OF_SHIELDING:
                    item = new TotemOfShieldingItem(artefactID);
                    break;
                case TOTEM_OF_SOUL_PROTECTION:
                    item = new TotemOfSoulProtectionItem(artefactID);
                    break;
                case WIND_HORN:
                    item = new WindHornItem(artefactID);
                    break;
                default:
                    item = new ArtefactDefenciveItem(artefactID);
                    break;
            }
            defenciveArtefact.put(artefactID, item);
            registerArtefacts(artefactID.toString().toLowerCase(), item);
        }
    }

    protected static void registerArtefacts(String id, Item item) {
        Registry.register(Registry.ITEM, Mcdar.ID(id), item);
    }

}
