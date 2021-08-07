package chronosacaria.mcdar.artefacts.beaconcomponents;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;

public class BeaconRenderLayer extends RenderLayer {
    public final static Identifier beaconBeamCore = new Identifier("textures/entity/beacon_beam.png");
    public final static Identifier beaconBeamMain = new Identifier("textures/entity/beacon_beam.png");
    public final static Identifier beaconBeamGlow = new Identifier("textures/entity/beacon_beam.png");

    public BeaconRenderLayer(String name, VertexFormat vertexFormat, VertexFormat.DrawMode drawMode, int expectedBufferSize, boolean hasCrumbling, boolean translucent, Runnable startAction, Runnable endAction) {
        super(name, vertexFormat, drawMode, expectedBufferSize, hasCrumbling, translucent, startAction, endAction);
    }

    public static final RenderLayer BEACON_BEAM_MAIN = RenderLayer.of(
            "BeaconBeamMain",
            VertexFormats.POSITION_COLOR,
            VertexFormat.DrawMode.QUADS,
            256,
            RenderLayer.MultiPhaseParameters.builder()
                    .texture(new Texture(beaconBeamMain, false, false))
                    .layering(VIEW_OFFSET_Z_LAYERING)
                    .transparency(TRANSLUCENT_TRANSPARENCY)
                    .depthTest(ALWAYS_DEPTH_TEST)
                    .cull(DISABLE_CULLING)
                    .shader(NO_SHADER)
                    .lightmap(DISABLE_LIGHTMAP)
                    .writeMaskState(COLOR_MASK)
                    .build(false));

    public static final RenderLayer BEACON_BEAM_GLOW = RenderLayer.of(
            "BeaconBeamMain",
            VertexFormats.POSITION_COLOR,
            VertexFormat.DrawMode.QUADS,
            256,
            RenderLayer.MultiPhaseParameters.builder()
                    .texture(new Texture(beaconBeamGlow, false, false))
                    .layering(VIEW_OFFSET_Z_LAYERING)
                    .transparency(TRANSLUCENT_TRANSPARENCY)
                    .depthTest(ALWAYS_DEPTH_TEST)
                    .cull(DISABLE_CULLING)
                    .shader(NO_SHADER)
                    .lightmap(DISABLE_LIGHTMAP)
                    .writeMaskState(COLOR_MASK)
                    .build(false));

    public static final RenderLayer BEACON_BEAM_CORE = RenderLayer.of(
            "BeaconBeamMain",
            VertexFormats.POSITION_COLOR,
            VertexFormat.DrawMode.QUADS,
            256,
            RenderLayer.MultiPhaseParameters.builder()
                    .texture(new Texture(beaconBeamCore, false, false))
                    .layering(VIEW_OFFSET_Z_LAYERING)
                    .transparency(TRANSLUCENT_TRANSPARENCY)
                    .depthTest(ALWAYS_DEPTH_TEST)
                    .cull(DISABLE_CULLING)
                    .shader(NO_SHADER)
                    .lightmap(DISABLE_LIGHTMAP)
                    .writeMaskState(COLOR_MASK)
                    .build(false));


}
