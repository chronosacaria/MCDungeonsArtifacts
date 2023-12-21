package chronosacaria.mcdar.registries;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.enums.DamagingArtifactID;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

public class ItemGroupRegistry {
    public static final RegistryKey<ItemGroup> ARTIFACTS = RegistryKey.of(RegistryKeys.ITEM_GROUP, Mcdar.ID("artifacts"));

    public static void register() {
        Registry.register(Registries.ITEM_GROUP, ARTIFACTS, FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.mcdar.artifacts"))
                .icon(() -> new ItemStack(ArtifactsRegistry.DAMAGING_ARTIFACT.get(DamagingArtifactID.LIGHTNING_ROD)))
                .build());
    }
}
