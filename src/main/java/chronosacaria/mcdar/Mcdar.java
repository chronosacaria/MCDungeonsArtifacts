package chronosacaria.mcdar;

import chronosacaria.mcdar.init.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.Random;

public class Mcdar implements ModInitializer {
    public static final String MOD_ID = "mcdar";

    public static Identifier ID (String path){
        return new Identifier(MOD_ID, path);
    }

    public static final Random random = new Random();

    public static final ItemGroup ARTEFACTS = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, "artefacts"),
            () -> new ItemStack(ArtefactsInit.BOOTS_OF_SWIFTNESS));

    @Override
    public void onInitialize() {
        ArtefactsInit.init();
        StatusEffectInit.init();
        LootRegistry.init();
        EntityTypeRegistry.register();
        BlockRegistry.register();
        ItemRegistry.register();
    }
}
