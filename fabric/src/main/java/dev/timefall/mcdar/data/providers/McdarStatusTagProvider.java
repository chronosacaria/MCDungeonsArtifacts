package dev.timefall.mcdar.data.providers;

import dev.timefall.mcdar.registry.tag.TagKeys;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

//TODO Make sure that the tag is created properly via dategen after MCDX update
public class McdarStatusTagProvider extends FabricTagProvider<StatusEffect> {

    public McdarStatusTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.STATUS_EFFECT, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(TagKeys.IS_VALID_FOR_BEAST_EFFECTS)
                .add(StatusEffects.INSTANT_HEALTH.value())
                .add(StatusEffects.REGENERATION.value());
    }
}
