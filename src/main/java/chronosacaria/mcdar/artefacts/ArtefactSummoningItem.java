package chronosacaria.mcdar.artefacts;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;

public class ArtefactSummoningItem extends Item {
    public ArtefactSummoningItem(Settings settings) {
        super(settings.maxCount(1).maxDamage(8));
    }

    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }
}
