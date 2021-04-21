package chronosacaria.mcdar.client;

import chronosacaria.mcdar.entities.renderers.BlastFungusRenderer;
import chronosacaria.mcdar.init.EntityTypeRegistry;
import chronosacaria.mcdar.init.SummonedEntityRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class McdarClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        SummonedEntityRegistry.register();
        SummonedEntityRenderRegistry.register();

        EntityRendererRegistry.INSTANCE.register(EntityTypeRegistry.BLAST_FUNGUS_ENTITY_TYPE,
                (dispatcher, context) -> new BlastFungusRenderer(dispatcher));
    }
}
