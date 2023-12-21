package chronosacaria.mcdar;

import chronosacaria.mcdar.config.McdarConfig;
import chronosacaria.mcdar.registries.*;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class Mcdar implements ModInitializer {
    public static final String MOD_ID = "mcdar";
    public static Identifier ID (String path){
        return new Identifier(MOD_ID, path);
    }
    public static McdarConfig CONFIG;

    @Override
    public void onInitialize() {
        McdarConfig.register();
        CONFIG = AutoConfig.getConfigHolder(McdarConfig.class).getConfig();
        ItemGroupRegistry.register();
        ArtifactsRegistry.register();
        EnchantsRegistry.register();
        StatusEffectInit.init();
        LootRegistry.register();
        SummonedEntityRegistry.register();
    }
}
