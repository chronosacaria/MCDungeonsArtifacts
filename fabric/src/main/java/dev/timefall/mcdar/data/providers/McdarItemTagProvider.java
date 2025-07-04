package dev.timefall.mcdar.data.providers;

import dev.timefall.mcdar.registry.ArtifactsRegistry;
import dev.timefall.mcdar.registry.tag.TagKeys;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class McdarItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public McdarItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture, @Nullable BlockTagProvider blockTagProvider) {
        super(output, completableFuture, blockTagProvider);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(TagKeys.VILLAGER_ARTIFACT)
                .add(ArtifactsRegistry.BOOTS_OF_SWIFTNESS.getKey().get())
                .add(ArtifactsRegistry.GHOST_CLOAK.getKey().get())
                .add(ArtifactsRegistry.LIGHT_FEATHER.getKey().get())
                .add(ArtifactsRegistry.UPDRAFT_TOME.getKey().get())
                .add(ArtifactsRegistry.IRON_HIDE_AMULET.getKey().get())
                .add(ArtifactsRegistry.WIND_HORN.getKey().get())
                .add(ArtifactsRegistry.FLAMING_QUIVER.getKey().get())
                .add(ArtifactsRegistry.BUZZY_NEST.getKey().get())
                .add(ArtifactsRegistry.ENCHANTED_GRASS.getKey().get())
                .add(ArtifactsRegistry.GOLEM_KIT.getKey().get())
                .add(ArtifactsRegistry.TASTY_BONE.getKey().get())
                .add(ArtifactsRegistry.WONDERFUL_WHEAT.getKey().get())
                .add(ArtifactsRegistry.SATCHEL_OF_ELIXIRS.getKey().get())
                .add(ArtifactsRegistry.SATCHEL_OF_SNACKS.getKey().get());
        getOrCreateTagBuilder(TagKeys.ILLAGER_ARTIFACT)
                .add(ArtifactsRegistry.DEATH_CAP_MUSHROOM.getKey().get())
                .add(ArtifactsRegistry.BLAST_FUNGUS.getKey().get())
                .add(ArtifactsRegistry.HARVESTER.getKey().get())
                .add(ArtifactsRegistry.LIGHTNING_ROD.getKey().get())
                .add(ArtifactsRegistry.POWERSHAKER.getKey().get())
                .add(ArtifactsRegistry.ENCHANTERS_TOME.getKey().get())
                .add(ArtifactsRegistry.SOUL_HEALER.getKey().get())
                .add(ArtifactsRegistry.TOTEM_OF_REGENERATION.getKey().get())
                .add(ArtifactsRegistry.TOTEM_OF_SHIELDING.getKey().get())
                .add(ArtifactsRegistry.TOTEM_OF_SOUL_PROTECTION.getKey().get())
                .add(ArtifactsRegistry.HARPOON_QUIVER.getKey().get())
                .add(ArtifactsRegistry.THUNDERING_QUIVER.getKey().get())
                .add(ArtifactsRegistry.TORMENT_QUIVER.getKey().get())
                .add(ArtifactsRegistry.CORRUPTED_SEEDS.getKey().get())
                .add(ArtifactsRegistry.GONG_OF_WEAKENING.getKey().get())
                .add(ArtifactsRegistry.LOVE_MEDALLION.getKey().get())
                .add(ArtifactsRegistry.SATCHEL_OF_ELEMENTS.getKey().get())
                .add(ArtifactsRegistry.SHOCK_POWDER.getKey().get());
        getOrCreateTagBuilder(TagKeys.VOID_ARTIFACT)
                .add(ArtifactsRegistry.TOME_OF_DUPLICATION.getKey().get());
                //.add(ArtifactsRegistry.SHADOW_SHIFTER.getKey().get());
        getOrCreateTagBuilder(TagKeys.ALL_ARTIFACT)
                .addOptionalTag(TagKeys.VILLAGER_ARTIFACT)
                .addOptionalTag(TagKeys.ILLAGER_ARTIFACT)
                .addOptionalTag(TagKeys.VOID_ARTIFACT);
    }
}
