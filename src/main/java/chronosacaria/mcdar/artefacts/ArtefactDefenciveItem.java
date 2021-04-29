package chronosacaria.mcdar.artefacts;

import chronosacaria.mcdar.config.McdarConfig;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;

public class ArtefactDefenciveItem extends Item {
    public ArtefactDefenciveItem(Settings settings) {
        super(settings.maxCount(1).maxDamage(McdarConfig.config.getDefenciveArtefactDurability()));
    }

    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }
}
