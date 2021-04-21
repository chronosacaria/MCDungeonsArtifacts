package chronosacaria.mcdar.init;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.blocks.BlastFungusBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockRegistry {
    public static Block BLAST_FUNGUS_BLOCK =
            new BlastFungusBlock(FabricBlockSettings.of(Material.TNT).breakInstantly().sounds(BlockSoundGroup.GRASS));

    public static void register(){
        BLAST_FUNGUS_BLOCK = Registry.register(Registry.BLOCK, new Identifier(Mcdar.MOD_ID, "blast_fungus_block"),
                BLAST_FUNGUS_BLOCK);
    }
}
