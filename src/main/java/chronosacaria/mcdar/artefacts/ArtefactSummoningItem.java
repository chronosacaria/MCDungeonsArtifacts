package chronosacaria.mcdar.artefacts;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.config.McdarConfig;
import chronosacaria.mcdar.enums.SummoningArtefactID;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;

public class ArtefactSummoningItem extends Item {

    public final SummoningArtefactID id;

    public ArtefactSummoningItem(SummoningArtefactID id) {
        super(new Settings().maxCount(1).group(Mcdar.ARTEFACTS).maxDamage(McdarConfig.config.getSummoningArtefactDurability()));
        this.id = id;
    }

    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }
}
