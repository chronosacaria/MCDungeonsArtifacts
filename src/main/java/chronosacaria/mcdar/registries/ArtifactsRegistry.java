package chronosacaria.mcdar.registries;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.artifacts.*;
import chronosacaria.mcdar.enums.*;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.InstrumentTags;

import java.util.EnumMap;
import java.util.Locale;


public class ArtifactsRegistry {

    public static final EnumMap<AgilityArtifactID, Item> AGILITY_ARTIFACT = new EnumMap<>(AgilityArtifactID.class);
    public static final EnumMap<DamagingArtifactID, Item> DAMAGING_ARTIFACT = new EnumMap<>(DamagingArtifactID.class);
    public static final EnumMap<DefensiveArtifactID, Item> DEFENSIVE_ARTIFACT = new EnumMap<>(DefensiveArtifactID.class);
    public static final EnumMap<QuiverArtifactID, Item> QUIVER_ARTIFACT = new EnumMap<>(QuiverArtifactID.class);
    public static final EnumMap<StatusInflictingArtifactID, Item> STATUS_INFLICTING_ARTIFACT = new EnumMap<>(StatusInflictingArtifactID.class);
    public static final EnumMap<SummoningArtifactID, Item> SUMMONING_ARTIFACT = new EnumMap<>(SummoningArtifactID.class);


    public static void register() {
        for (AgilityArtifactID artefactID : AgilityArtifactID.values()) {
            if (!Mcdar.CONFIG.mcdarArtifactsStatsConfig.AGILITY_ARTIFACT_STATS.get(artefactID).mcdar$getIsEnabled())
                continue;

            Item item = switch (artefactID) {
                case BOOTS_OF_SWIFTNESS -> new BootsOfSwiftnessItem();
                case DEATH_CAP_MUSHROOM -> new DeathCapMushroomItem();
                case GHOST_CLOAK -> new GhostCloakItem();
                case LIGHT_FEATHER -> new LightFeatherItem();
            };

            AGILITY_ARTIFACT.put(artefactID, item);
            registerArtifacts(artefactID.toString().toLowerCase(Locale.ROOT), item);
        }
        for (DamagingArtifactID artefactID : DamagingArtifactID.values()) {
            if (!Mcdar.CONFIG.mcdarArtifactsStatsConfig.DAMAGING_ARTIFACT_STATS.get(artefactID).mcdar$getIsEnabled())
                continue;

            Item item = switch (artefactID) {
                case BLAST_FUNGUS -> new BlastFungusItem();
                //case CORRUPTED_BEACON -> new CorruptedBeaconItem();
                case HARVESTER -> new HarvesterItem();
                case LIGHTNING_ROD -> new LightningRodItem();
                case POWERSHAKER -> new PowershakerItem();
                case UPDRAFT_TOME -> new UpdraftTomeItem();
            };

            DAMAGING_ARTIFACT.put(artefactID, item);
            registerArtifacts(artefactID.toString().toLowerCase(Locale.ROOT), item);
        }
        for (DefensiveArtifactID artefactID : DefensiveArtifactID.values()) {
            if (!Mcdar.CONFIG.mcdarArtifactsStatsConfig.DEFENSIVE_ARTIFACT_STATS.get(artefactID).mcdar$getIsEnabled())
                continue;

            Item item = switch (artefactID) {
                case ENCHANTERS_TOME -> new EnchantersTomeItem();
                case IRON_HIDE_AMULET -> new IronHideAmuletItem();
                //case SATCHEL_OF_ELIXIRS -> new SatchelOfElixirsItem();
                //case SATCHEL_OF_SNACKS -> new SatchelOfSnacksItem();
                case SOUL_HEALER -> new SoulHealerItem();
                case TOTEM_OF_REGENERATION -> new TotemOfRegenerationItem();
                case TOTEM_OF_SHIELDING -> new TotemOfShieldingItem();
                case TOTEM_OF_SOUL_PROTECTION -> new TotemOfSoulProtectionItem();
                case WIND_HORN -> new WindHornItem(InstrumentTags.GOAT_HORNS);
            };

            DEFENSIVE_ARTIFACT.put(artefactID, item);
            registerArtifacts(artefactID.toString().toLowerCase(Locale.ROOT), item);
        }
        for (QuiverArtifactID artefactID : QuiverArtifactID.values()) {
            if (!Mcdar.CONFIG.mcdarArtifactsStatsConfig.QUIVER_ARTIFACT_STATS.get(artefactID).mcdar$getIsEnabled())
                continue;

            Item item = switch (artefactID) {
                case FLAMING_QUIVER -> new FlamingQuiverItem();
                case HARPOON_QUIVER -> new HarpoonQuiverItem();
                case THUNDERING_QUIVER -> new ThunderingQuiverItem();
                case TORMENT_QUIVER -> new TormentQuiverItem();
            };

            QUIVER_ARTIFACT.put(artefactID, item);
            registerArtifacts(artefactID.toString().toLowerCase(Locale.ROOT), item);
        }
        for (StatusInflictingArtifactID artefactID : StatusInflictingArtifactID.values()) {
            if (!Mcdar.CONFIG.mcdarArtifactsStatsConfig.STATUS_INFLICTING_ARTIFACT_STATS.get(artefactID).mcdar$getIsEnabled())
                continue;

            Item item = switch (artefactID) {
                case CORRUPTED_SEEDS -> new CorruptedSeedsItem();
                case GONG_OF_WEAKENING -> new GongOfWeakeningItem();
                case LOVE_MEDALLION -> new LoveMedallionItem();
                case SATCHEL_OF_ELEMENTS -> new SatchelOfElementsItem();
                case SHOCK_POWDER -> new ShockPowderItem();
            };

            STATUS_INFLICTING_ARTIFACT.put(artefactID, item);
            registerArtifacts(artefactID.toString().toLowerCase(Locale.ROOT), item);
        }
        for (SummoningArtifactID artefactID : SummoningArtifactID.values()) {
            if (!Mcdar.CONFIG.mcdarArtifactsStatsConfig.SUMMONING_ARTIFACT_STATS.get(artefactID).mcdar$getIsEnabled())
                continue;

            Item item = switch (artefactID) {
                case BUZZY_NEST -> new BuzzyNestItem();
                case ENCHANTED_GRASS -> new EnchantedGrassItem();
                case GOLEM_KIT -> new GolemKitItem();
                case TASTY_BONE -> new TastyBoneItem();
                case WONDERFUL_WHEAT -> new WonderfulWheatItem();
            };

            SUMMONING_ARTIFACT.put(artefactID, item);
            registerArtifacts(artefactID.toString().toLowerCase(Locale.ROOT), item);
        }

    }

    protected static void registerArtifacts(String id, Item item) {
        Registry.register(Registries.ITEM, Mcdar.ID(id), item);
    }

}
