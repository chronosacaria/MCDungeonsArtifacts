package chronosacaria.mcdar.artifacts;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.config.McdarConfig;
import chronosacaria.mcdar.enums.DefensiveArtifactID;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;

public class ArtifactDefensiveItem extends Item {

    public final DefensiveArtifactID id;

    public ArtifactDefensiveItem(DefensiveArtifactID id) {
        super(new Settings().maxCount(1).maxDamage(McdarConfig.CONFIG.getDefensiveArtifactDurability()));
        ItemGroupEvents.modifyEntriesEvent(Mcdar.ARTEFACTS).register(entries -> entries.add(this));
        this.id = id;
    }

    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }
}
