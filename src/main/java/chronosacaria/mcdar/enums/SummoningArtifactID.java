package chronosacaria.mcdar.enums;

import chronosacaria.mcdar.config.McdarConfig;
import chronosacaria.mcdar.init.ArtifactsInit;
import net.minecraft.item.Item;

import java.util.EnumMap;

public enum SummoningArtifactID implements IArtifactItem{
    BUZZY_NEST,
    ENCHANTED_GRASS,
    GOLEM_KIT,
    TASTY_BONE,
    WONDERFUL_WHEAT;

    public static EnumMap<SummoningArtifactID, Boolean> getEnabledItems(){
        return McdarConfig.CONFIG.ENABLE_SUMMONING_ARTIFACT;
    }

    public static EnumMap<SummoningArtifactID, Item> getItemsEnum() {
        return ArtifactsInit.summoningArtifact;
    }

    @Override
    public Boolean isEnabled() {
        return getEnabledItems().get(this);
    }

    @Override
    public Item getItem() {
        return getItemsEnum().get(this);
    }
}
