package chronosacaria.mcdar.artifacts;

import chronosacaria.mcdar.enums.StatusInflictingArtifactID;
import chronosacaria.mcdar.registries.ItemGroupRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;

public class ArtifactStatusInflictingItem extends Item {

    public final StatusInflictingArtifactID id;

    public ArtifactStatusInflictingItem(StatusInflictingArtifactID id, int artifactDurability) {
        super(new Settings().maxCount(1).maxDamage(artifactDurability));
        this.id = id;
        ItemGroupEvents.modifyEntriesEvent(ItemGroupRegistry.ARTIFACTS).register(entries -> entries.add(this.getDefaultStack()));
    }

    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }
}
