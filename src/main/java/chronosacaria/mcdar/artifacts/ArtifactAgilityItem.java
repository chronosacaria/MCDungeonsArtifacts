package chronosacaria.mcdar.artifacts;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.config.McdarConfig;
import chronosacaria.mcdar.enums.AgilityArtifactID;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;

public class ArtifactAgilityItem extends Item {

    public final AgilityArtifactID id;

    public ArtifactAgilityItem(AgilityArtifactID id) {
        super(new Settings().maxCount(1).group(Mcdar.ARTEFACTS).maxDamage(McdarConfig.CONFIG.getAgilityArtifactDurability()));
        this.id = id;
    }

    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }
}
