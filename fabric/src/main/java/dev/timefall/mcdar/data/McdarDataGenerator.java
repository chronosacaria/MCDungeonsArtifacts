package dev.timefall.mcdar.data;

import dev.timefall.mcdar.data.providers.McdarEnchantmentsProvider;
import dev.timefall.mcdar.data.providers.McdarItemTagProvider;
import dev.timefall.mcdar.data.providers.McdarStatusTagProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryBuilder;

public class McdarDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(McdarEnchantmentsProvider::new);
        pack.addProvider((FabricDataOutput dataOutput) -> new McdarItemTagProvider(dataOutput, fabricDataGenerator.getRegistries(), null));
        pack.addProvider(McdarStatusTagProvider::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        DataGeneratorEntrypoint.super.buildRegistry(registryBuilder);
    }
}
