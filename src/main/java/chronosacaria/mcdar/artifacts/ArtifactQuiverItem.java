package chronosacaria.mcdar.artifacts;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.config.McdarConfig;
import chronosacaria.mcdar.enums.QuiverArtifactID;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;

public class ArtifactQuiverItem extends Item {

    public final QuiverArtifactID id;

    public ArtifactQuiverItem(QuiverArtifactID id) {
        super(new Settings().maxCount(1).group(Mcdar.ARTEFACTS).maxDamage(McdarConfig.CONFIG.getQuiverArtifactDurability()));
        this.id = id;
    }

    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }
}
