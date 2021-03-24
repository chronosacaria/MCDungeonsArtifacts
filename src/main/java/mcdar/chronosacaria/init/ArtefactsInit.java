package mcdar.chronosacaria.init;

import mcdar.chronosacaria.Mcdar;
import mcdar.chronosacaria.artefacts.BootsOfSwiftnessItem;
import mcdar.chronosacaria.artefacts.DeathCapMushroomItem;
import net.minecraft.item.Item;

public class ArtefactsInit {
    public static Item BOOTS_OF_SWIFTNESS;
    public static Item DEATH_CAP_MUSHROOM;

    public static void init(){
        BOOTS_OF_SWIFTNESS = new BootsOfSwiftnessItem(new Item.Settings().group(Mcdar.ARTEFACTS),"boots_of_swiftness");
        DEATH_CAP_MUSHROOM = new DeathCapMushroomItem(new Item.Settings().group(Mcdar.ARTEFACTS), "death_cap_mushroom");
    }
}
