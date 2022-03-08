/*package chronosacaria.mcdar.client.events;

import chronosacaria.mcdar.artefacts.beacon.AbstractBeaconItem;
import chronosacaria.mcdar.artefacts.beacon.BeaconBeamRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class RenderWorldLastEvent {
    public static void renderWorldLastEvent(WorldRenderContext event){
        PlayerEntity playerEntity = MinecraftClient.getInstance().player;

        if (playerEntity == null) return;

        ItemStack heldItem = AbstractBeaconItem.getBeacon(playerEntity);

        if (heldItem.isEmpty()) return;

        BeaconBeamRenderer.renderBeam((RenderWorldLastEvent) event, playerEntity, MinecraftClient.getInstance().getLastFrameDuration());
    }
}
*/