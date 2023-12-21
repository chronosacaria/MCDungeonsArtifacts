package chronosacaria.mcdar.artifacts;

import chronosacaria.mcdar.enums.QuiverArtifactID;
import chronosacaria.mcdar.registries.ItemGroupRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;

public class ArtifactQuiverItem extends Item {

    public final QuiverArtifactID id;

    public ArtifactQuiverItem(QuiverArtifactID id, int artifactDurability) {
        super(new Settings().maxCount(1).maxDamage(artifactDurability));
        this.id = id;
        ItemGroupEvents.modifyEntriesEvent(ItemGroupRegistry.ARTIFACTS).register(entries -> entries.add(this.getDefaultStack()));
    }

    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }
}
