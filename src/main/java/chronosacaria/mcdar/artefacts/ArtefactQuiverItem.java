package chronosacaria.mcdar.artefacts;

import chronosacaria.mcdar.config.McdarConfig;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;

public class ArtefactQuiverItem extends Item {
    public ArtefactQuiverItem(Settings settings) {
        super(settings.maxCount(1).maxDamage(McdarConfig.config.getQuiverArtefactDurability()));
    }

    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }
}
