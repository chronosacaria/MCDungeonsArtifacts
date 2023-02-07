package chronosacaria.mcdar.artifacts;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.config.McdarConfig;
import chronosacaria.mcdar.enums.AgilityArtifactID;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;

public class ArtifactAgilityItem extends Item {

    public final AgilityArtifactID id;

    public ArtifactAgilityItem(AgilityArtifactID id) {
        super(new Settings().maxCount(1).maxDamage(McdarConfig.CONFIG.getAgilityArtifactDurability()));
        ItemGroupEvents.modifyEntriesEvent(Mcdar.ARTEFACTS).register(entries -> entries.add(this));
        this.id = id;
    }

    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }
}
