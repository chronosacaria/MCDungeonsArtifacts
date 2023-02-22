package chronosacaria.mcdar.artifacts.beacon;

import chronosacaria.mcdar.Mcdar;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.function.Function;

public class MyRenderType extends RenderLayer {
    private final static Identifier beaconBeamCore = new Identifier(Mcdar.MOD_ID + ":textures/misc/beacon_beam_core.png");
    private final static Identifier beaconBeamMain = new Identifier(Mcdar.MOD_ID + ":textures/misc/beacon_beam_main.png");
    private final static Identifier beaconBeamGlow = new Identifier(Mcdar.MOD_ID + ":textures/misc/beacon_beam_glow.png");

    public MyRenderType(String name, VertexFormat vertexFormat, VertexFormat.DrawMode drawMode, int expectedBufferSize, boolean hasCrumbling, boolean translucent, Runnable startAction, Runnable endAction) {
        super(name, vertexFormat, drawMode, expectedBufferSize, hasCrumbling, translucent, startAction, endAction);
    }

    public static final Function<Identifier, RenderLayer> BEACON_BEAM_MAIN = Util.memoize(identifier -> {
        RenderPhase.Texture textureState = new RenderPhase.Texture(identifier, false, false);
        return RenderLayer.of(
                "BeaconBeamMain",
                VertexFormats.POSITION_COLOR_TEXTURE,
                VertexFormat.DrawMode.QUADS, 256, false, true,
                RenderLayer.MultiPhaseParameters.builder()
                        .texture(new Texture(beaconBeamMain, false, false))
                        .layering(RenderPhase.NO_LAYERING)
                        .transparency(RenderPhase.TRANSLUCENT_TRANSPARENCY)
                        .depthTest(RenderPhase.ALWAYS_DEPTH_TEST)
                        .cull(RenderPhase.DISABLE_CULLING)
                        .lightmap(RenderPhase.DISABLE_LIGHTMAP)
                        .writeMaskState(RenderPhase.COLOR_MASK)
                        .build(false));
    });

    public static final Function<Identifier, RenderLayer> BEACON_BEAM_GLOW = Util.memoize(identifier -> {
        RenderPhase.Texture textureState = new RenderPhase.Texture(identifier, false, false);
        return RenderLayer.of(
                "BeaconBeamGlow",
                VertexFormats.POSITION_COLOR_TEXTURE,
                VertexFormat.DrawMode.QUADS, 256, false, true,
                RenderLayer.MultiPhaseParameters.builder()
                        .texture(new Texture(beaconBeamGlow, false, false))
                        .layering(RenderPhase.NO_LAYERING)
                        .transparency(RenderPhase.TRANSLUCENT_TRANSPARENCY)
                        .depthTest(RenderPhase.ALWAYS_DEPTH_TEST)
                        .cull(RenderPhase.DISABLE_CULLING)
                        .lightmap(RenderPhase.DISABLE_LIGHTMAP)
                        .writeMaskState(RenderPhase.COLOR_MASK)
                        .build(false));
    });

    public static final Function<Identifier, RenderLayer> BEACON_BEAM_CORE = Util.memoize(identifier -> {
        RenderPhase.Texture textureState = new RenderPhase.Texture(identifier, false, false);
        return RenderLayer.of(
                "BeaconBeamGlow",
                VertexFormats.POSITION_COLOR_TEXTURE,
                VertexFormat.DrawMode.QUADS, 256, false, true,
                RenderLayer.MultiPhaseParameters.builder()
                        .texture(new Texture(beaconBeamCore, false, false))
                        .layering(RenderPhase.NO_LAYERING)
                        .transparency(RenderPhase.TRANSLUCENT_TRANSPARENCY)
                        .depthTest(RenderPhase.ALWAYS_DEPTH_TEST)
                        .cull(RenderPhase.DISABLE_CULLING)
                        .lightmap(RenderPhase.DISABLE_LIGHTMAP)
                        .writeMaskState(RenderPhase.COLOR_MASK)
                        .build(false));
    });
}