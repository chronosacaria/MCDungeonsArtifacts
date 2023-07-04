package chronosacaria.mcdar;

import chronosacaria.mcdar.registries.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class Mcdar implements ModInitializer {
    public static final String MOD_ID = "mcdar";
    public static Identifier ID (String path){
        return new Identifier(MOD_ID, path);
    }

    @Override
    public void onInitialize() {
        ItemGroupRegistry.register();
        ArtifactsRegistry.register();
        EnchantsRegistry.register();
        StatusEffectInit.init();
        LootRegistry.register();
        SummonedEntityRegistry.register();
    }
}
