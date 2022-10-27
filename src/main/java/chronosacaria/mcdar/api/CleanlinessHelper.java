package chronosacaria.mcdar.api;

import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;

import java.util.Random;

public class CleanlinessHelper {

    static Random random = new Random();

    public static void playCenteredSound(LivingEntity center, SoundEvent soundEvent, float volume, float pitch) {
        playCenteredSound(center, soundEvent, SoundCategory.PLAYERS, volume, pitch);
    }

    public static void playCenteredSound(LivingEntity center, SoundEvent soundEvent, SoundCategory soundCategory, float volume, float pitch) {
        center.world.playSound(null,
                center.getX(), center.getY(), center.getZ(),
                soundEvent, soundCategory,
                volume, pitch);
    }

    public static boolean percentToOccur (int chance) {
        return random.nextInt(100) + 1 <= chance;
    }
}
