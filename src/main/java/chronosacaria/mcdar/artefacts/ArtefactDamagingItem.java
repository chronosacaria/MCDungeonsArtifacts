package chronosacaria.mcdar.artefacts;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.config.McdarConfig;
import chronosacaria.mcdar.enums.DamagingArtefactID;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;

public class ArtefactDamagingItem extends Item {

    public final DamagingArtefactID id;

    public ArtefactDamagingItem(DamagingArtefactID id) {
        super(new Settings().maxCount(1).group(Mcdar.ARTEFACTS).maxDamage(McdarConfig.config.getDamagingArtefactDurability()));
        this.id = id;
    }

    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }
}
