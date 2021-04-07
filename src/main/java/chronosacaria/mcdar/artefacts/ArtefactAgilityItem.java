package chronosacaria.mcdar.artefacts;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;

public class ArtefactAgilityItem extends Item {
    public ArtefactAgilityItem(Settings settings) {
        super(settings.maxCount(1).maxDamage(64));
    }

    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }
}
