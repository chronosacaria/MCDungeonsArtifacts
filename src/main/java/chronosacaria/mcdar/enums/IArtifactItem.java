package chronosacaria.mcdar.enums;

import net.minecraft.item.Item;

public interface IArtifactItem {

    Boolean mcdar$isEnabled();

    Item mcdar$getItem();

    Float mcdar$getGeneralArtifactSpawnRate();
    Float mcdar$getDungeonArtifactSpawnRate();
}
