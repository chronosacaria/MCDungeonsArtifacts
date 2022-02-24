package chronosacaria.mcdar.artefacts.beacon;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.mixin.invokers.RenderLayerInvoker;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;

public class MyRenderType extends RenderLayer {
    private final static Identifier beaconBeamCore = new Identifier(Mcdar.MOD_ID + ":textures/misc/beacon_beam_core.png");
    private final static Identifier beaconBeamMain = new Identifier(Mcdar.MOD_ID + ":textures/misc/beacon_beam_main.png");
    private final static Identifier beaconBeamGlow = new Identifier(Mcdar.MOD_ID + ":textures/misc/beacon_beam_glow.png");

    public MyRenderType(String name, VertexFormat vertexFormat, VertexFormat.DrawMode drawMode, int expectedBufferSize, boolean hasCrumbling, boolean translucent, Runnable startAction, Runnable endAction) {
        super(name, vertexFormat, drawMode, expectedBufferSize, hasCrumbling, translucent, startAction, endAction);
    }

    public static final RenderLayer BEACON_BEAM_MAIN = RenderLayerInvoker.of(
            "BeaconBeamMain",
            VertexFormats.POSITION_COLOR_TEXTURE,
            VertexFormat.DrawMode.QUADS,
            256,
            RenderLayer.MultiPhaseParameters.builder()
                    .texture(new Texture(beaconBeamMain, false, false))
                    .layering(Layering.NO_LAYERING)
                    .transparency(TRANSLUCENT_TRANSPARENCY)
                    .depthTest(ALWAYS_DEPTH_TEST)
                    .cull(DISABLE_CULLING)
                    .lightmap(DISABLE_LIGHTMAP)
                    .writeMaskState(COLOR_MASK)
                    .build(false));

    public static final RenderLayer BEACON_BEAM_GLOW = RenderLayerInvoker.of(
            "BeaconBeamMain",
            VertexFormats.POSITION_COLOR_TEXTURE,
            VertexFormat.DrawMode.QUADS,
            256,
            RenderLayer.MultiPhaseParameters.builder()
                    .texture(new Texture(beaconBeamGlow, false, false))
                    .layering(Layering.NO_LAYERING)
                    .transparency(TRANSLUCENT_TRANSPARENCY)
                    .depthTest(ALWAYS_DEPTH_TEST)
                    .cull(DISABLE_CULLING)
                    .lightmap(DISABLE_LIGHTMAP)
                    .writeMaskState(COLOR_MASK)
                    .build(false));

    public static final RenderLayer BEACON_BEAM_CORE = RenderLayerInvoker.of(
            "BeaconBeamMain",
            VertexFormats.POSITION_COLOR_TEXTURE,
            VertexFormat.DrawMode.QUADS,
            256,
            RenderLayer.MultiPhaseParameters.builder()
                    .texture(new Texture(beaconBeamCore, false, false))
                    .layering(Layering.NO_LAYERING)
                    .transparency(TRANSLUCENT_TRANSPARENCY)
                    .depthTest(ALWAYS_DEPTH_TEST)
                    .cull(DISABLE_CULLING)
                    .lightmap(DISABLE_LIGHTMAP)
                    .writeMaskState(COLOR_MASK)
                    .build(false));

}
