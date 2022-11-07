package chronosacaria.mcdar.init;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.artifacts.*;
import chronosacaria.mcdar.enums.*;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

import java.util.EnumMap;
import java.util.Locale;

import static chronosacaria.mcdar.config.McdarConfig.CONFIG;

public class ArtifactsInit {

    public static final EnumMap<DamagingArtifactID, Item> damagingArtifact = new EnumMap<>(DamagingArtifactID.class);
    public static final EnumMap<StatusInflictingArtifactID, Item> statusInflictingArtifact = new EnumMap<>(StatusInflictingArtifactID.class);
    public static final EnumMap<QuiverArtifactID, Item> quiverArtifact = new EnumMap<>(QuiverArtifactID.class);
    public static final EnumMap<SummoningArtifactID, Item> summoningArtifact = new EnumMap<>(SummoningArtifactID.class);
    public static final EnumMap<AgilityArtifactID, Item> agilityArtifact = new EnumMap<>(AgilityArtifactID.class);
    public static final EnumMap<DefensiveArtifactID, Item> defensiveArtifact = new EnumMap<>(DefensiveArtifactID.class);

    public static void init() {

        for (DamagingArtifactID artefactID : DamagingArtifactID.values()) {
            if (!CONFIG.ENABLE_DAMAGING_ARTIFACT.get(artefactID))
                continue;

            Item item = switch (artefactID) {
                case BLAST_FUNGUS -> new BlastFungusItem();
                //case CORRUPTED_BEACON:
                //    item = new CorruptedBeaconItem(artefactID);
                //    break;
                case HARVESTER -> new HarvesterItem();
                case LIGHTNING_ROD -> new LightningRodItem();
                case POWERSHAKER -> new PowershakerItem();
                case UPDRAFT_TOME -> new UpdraftTomeItem();
                //noinspection UnnecessaryDefault
                default -> new ArtifactDamagingItem(artefactID);
            };

            damagingArtifact.put(artefactID, item);
            registerArtifacts(artefactID.toString().toLowerCase(Locale.ROOT), item);
        }
        for (StatusInflictingArtifactID artefactID : StatusInflictingArtifactID.values()) {
            if (!CONFIG.ENABLE_STATUS_INFLICTING_ARTIFACT.get(artefactID))
                continue;

            Item item = switch (artefactID) {
                case CORRUPTED_SEEDS -> new CorruptedSeedsItem();
                case GONG_OF_WEAKENING -> new GongOfWeakeningItem();
                //case LOVE_MEDALLION -> new LoveMedallionItem(artefactID);
                case SATCHEL_OF_ELEMENTS -> new SatchelOfElementsItem();
                case SHOCK_POWDER -> new ShockPowderItem();
                //noinspection UnnecessaryDefault
                default -> new ArtifactStatusInflictingItem(artefactID);
            };

            statusInflictingArtifact.put(artefactID, item);
            registerArtifacts(artefactID.toString().toLowerCase(Locale.ROOT), item);
        }
        for (QuiverArtifactID artefactID : QuiverArtifactID.values()) {
            if (!CONFIG.ENABLE_QUIVER_ARTIFACT.get(artefactID))
                continue;

            Item item = switch (artefactID) {
                case FLAMING_QUIVER -> new FlamingQuiverItem();
                case HARPOON_QUIVER -> new HarpoonQuiverItem();
                case THUNDERING_QUIVER -> new ThunderingQuiverItem();
                case TORMENT_QUIVER -> new TormentQuiverItem();
                //noinspection UnnecessaryDefault
                default -> new ArtifactQuiverItem(artefactID);
            };

            quiverArtifact.put(artefactID, item);
            registerArtifacts(artefactID.toString().toLowerCase(Locale.ROOT), item);
        }
        for (SummoningArtifactID artefactID : SummoningArtifactID.values()) {
            if (!CONFIG.ENABLE_SUMMONING_ARTIFACT.get(artefactID))
                continue;

            Item item = switch (artefactID) {
                case BUZZY_NEST -> new BuzzyNestItem();
                case ENCHANTED_GRASS -> new EnchantedGrassItem();
                case GOLEM_KIT -> new GolemKitItem();
                case TASTY_BONE -> new TastyBoneItem();
                case WONDERFUL_WHEAT -> new WonderfulWheatItem();
                //noinspection UnnecessaryDefault
                default -> new ArtifactSummoningItem(artefactID);
            };

            summoningArtifact.put(artefactID, item);
            registerArtifacts(artefactID.toString().toLowerCase(Locale.ROOT), item);
        }
        for (AgilityArtifactID artefactID : AgilityArtifactID.values()) {
            if (!CONFIG.ENABLE_AGILITY_ARTIFACT.get(artefactID))
                continue;

            Item item = switch (artefactID) {
                case BOOTS_OF_SWIFTNESS -> new BootsOfSwiftnessItem();
                case DEATH_CAP_MUSHROOM -> new DeathCapMushroomItem();
                case GHOST_CLOAK -> new GhostCloakItem();
                case LIGHT_FEATHER -> new LightFeatherItem();
                //noinspection UnnecessaryDefault
                default -> new ArtifactAgilityItem(artefactID);
            };

            agilityArtifact.put(artefactID, item);
            registerArtifacts(artefactID.toString().toLowerCase(Locale.ROOT), item);
        }
        for (DefensiveArtifactID artefactID : DefensiveArtifactID.values()) {
            if (!CONFIG.ENABLE_DEFENSIVE_ARTIFACT.get(artefactID))
                continue;

            Item item = switch (artefactID) {
                case ENCHANTERS_TOME -> new EnchantersTomeItem();
                case IRON_HIDE_AMULET -> new IronHideAmuletItem();
                //case SATCHEL_OF_ELIXIRS:
                //    item = new SatchelOfElixirsItem(artefactID);
                //    break;
                //case SATCHEL_OF_SNACKS:
                //    item = new SatchelOfSnacksItem(artefactID);
                //    break;
                case SOUL_HEALER -> new SoulHealerItem();
                case TOTEM_OF_REGENERATION -> new TotemOfRegenerationItem();
                case TOTEM_OF_SHIELDING -> new TotemOfShieldingItem();
                case TOTEM_OF_SOUL_PROTECTION -> new TotemOfSoulProtectionItem();
                case WIND_HORN -> new WindHornItem();
                //noinspection UnnecessaryDefault
                default -> new ArtifactDefensiveItem(artefactID);
            };

            defensiveArtifact.put(artefactID, item);
            registerArtifacts(artefactID.toString().toLowerCase(Locale.ROOT), item);
        }
    }

    protected static void registerArtifacts(String id, Item item) {
        Registry.register(Registry.ITEM, Mcdar.ID(id), item);
    }

}
