package chronosacaria.mcdar.init;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.artefacts.*;
import chronosacaria.mcdar.artefacts.beacon.CorruptedBeaconItem;
import chronosacaria.mcdar.enums.*;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

import java.util.EnumMap;
import java.util.Locale;

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

            Item item = switch (artefactID) {
                case BLAST_FUNGUS -> new BlastFungusItem(artefactID);
                //case CORRUPTED_BEACON:
                //    item = new CorruptedBeaconItem(artefactID);
                //    break;
                case HARVESTER -> new HarvesterItem(artefactID);
                case LIGHTNING_ROD -> new LightningRodItem(artefactID);
                case UPDRAFT_TOME -> new UpdraftTomeItem(artefactID);
                //noinspection UnnecessaryDefault
                default -> new ArtefactDamagingItem(artefactID);
            };

            damagingArtefact.put(artefactID, item);
            registerArtefacts(artefactID.toString().toLowerCase(Locale.ROOT), item);
        }
        for (StatusInflictingArtefactID artefactID : StatusInflictingArtefactID.values()) {
            if (!config.enableStatusInflictingArtefact.get(artefactID))
                continue;

            Item item = switch (artefactID) {
                case CORRUPTED_SEEDS -> new CorruptedSeedsItem(artefactID);
                case GONG_OF_WEAKENING -> new GongOfWeakeningItem(artefactID);
                case LOVE_MEDALLION -> new LoveMedallionItem(artefactID);
                case SATCHEL_OF_ELEMENTS -> new SatchelOfElementsItem(artefactID);
                case SHOCK_POWDER -> new ShockPowderItem(artefactID);
                //noinspection UnnecessaryDefault
                default -> new ArtefactStatusInflictingItem(artefactID);
            };

            statusInflictingArtefact.put(artefactID, item);
            registerArtefacts(artefactID.toString().toLowerCase(Locale.ROOT), item);
        }
        for (QuiverArtefactID artefactID : QuiverArtefactID.values()) {
            if (!config.enableQuiverArtefact.get(artefactID))
                continue;

            Item item = switch (artefactID) {
                case FLAMING_QUIVER -> new FlamingQuiverItem(artefactID);
                case HARPOON_QUIVER -> new HarpoonQuiverItem(artefactID);
                case THUNDERING_QUIVER -> new ThunderingQuiverItem(artefactID);
                case TORMENT_QUIVER -> new TormentQuiverItem(artefactID);
                //noinspection UnnecessaryDefault
                default -> new ArtefactQuiverItem(artefactID);
            };

            quiverArtefact.put(artefactID, item);
            registerArtefacts(artefactID.toString().toLowerCase(Locale.ROOT), item);
        }
        for (SummoningArtefactID artefactID : SummoningArtefactID.values()) {
            if (!config.enableSummoningArtefact.get(artefactID))
                continue;

            Item item = switch (artefactID) {
                case BUZZY_NEST -> new BuzzyNestItem(artefactID);
                case ENCHANTED_GRASS -> new EnchantedGrassItem(artefactID);
                case GOLEM_KIT -> new GolemKitItem(artefactID);
                case TASTY_BONE -> new TastyBoneItem(artefactID);
                case WONDERFUL_WHEAT -> new WonderfulWheatItem(artefactID);
                //noinspection UnnecessaryDefault
                default -> new ArtefactSummoningItem(artefactID);
            };

            summoningArtefact.put(artefactID, item);
            registerArtefacts(artefactID.toString().toLowerCase(Locale.ROOT), item);
        }
        for (AgilityArtefactID artefactID : AgilityArtefactID.values()) {
            if (!config.enableAgilityArtefact.get(artefactID))
                continue;

            Item item = switch (artefactID) {
                case BOOTS_OF_SWIFTNESS -> new BootsOfSwiftnessItem(artefactID);
                case DEATH_CAP_MUSHROOM -> new DeathCapMushroomItem(artefactID);
                case GHOST_CLOAK -> new GhostCloakItem(artefactID);
                case LIGHT_FEATHER -> new LightFeatherItem(artefactID);
                //noinspection UnnecessaryDefault
                default -> new ArtefactAgilityItem(artefactID);
            };

            agilityArtefact.put(artefactID, item);
            registerArtefacts(artefactID.toString().toLowerCase(Locale.ROOT), item);
        }
        for (DefenciveArtefactID artefactID : DefenciveArtefactID.values()) {
            if (!config.enableDefenciveArtefact.get(artefactID))
                continue;

            Item item = switch (artefactID) {
                case ENCHANTERS_TOME -> new EnchantersTomeItem(artefactID);
                case IRON_HIDE_AMULET -> new IronHideAmuletItem(artefactID);
                case POWERSHAKER -> new PowershakerItem(artefactID);
                //case SATCHEL_OF_ELIXIRS:
                //    item = new SatchelOfElixirsItem(artefactID);
                //    break;
                //case SATCHEL_OF_SNACKS:
                //    item = new SatchelOfSnacksItem(artefactID);
                //    break;
                case SOUL_HEALER -> new SoulHealerItem(artefactID);
                case TOTEM_OF_REGENERATION -> new TotemOfRegenerationItem(artefactID);
                case TOTEM_OF_SHIELDING -> new TotemOfShieldingItem(artefactID);
                case TOTEM_OF_SOUL_PROTECTION -> new TotemOfSoulProtectionItem(artefactID);
                case WIND_HORN -> new WindHornItem(artefactID);
                //noinspection UnnecessaryDefault
                default -> new ArtefactDefenciveItem(artefactID);
            };

            defenciveArtefact.put(artefactID, item);
            registerArtefacts(artefactID.toString().toLowerCase(Locale.ROOT), item);
        }
    }

    protected static void registerArtefacts(String id, Item item) {
        Registry.register(Registry.ITEM, Mcdar.ID(id), item);
    }

}
