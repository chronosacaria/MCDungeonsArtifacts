package chronosacaria.mcdar.init;

import chronosacaria.mcdar.Mcdar;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {
    public static Item BLAST_FUNGUS_BLOCK_ITEM = new BlockItem(BlockRegistry.BLAST_FUNGUS_BLOCK,
            new FabricItemSettings().group(Mcdar.ARTEFACTS));

    public static void register(){
        BLAST_FUNGUS_BLOCK_ITEM = Registry.register(Registry.ITEM, new Identifier(Mcdar.MOD_ID, "blast_fungus"),
                BLAST_FUNGUS_BLOCK_ITEM);
    }
}
