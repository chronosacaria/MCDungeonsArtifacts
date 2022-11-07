package chronosacaria.mcdar.api;

import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;
import java.util.Locale;
import java.util.Random;

public class CleanlinessHelper {

    static final Random RANDOM = new Random();

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
        return RANDOM.nextInt(100) + 1 <= chance;
    }

    public static void createLoreTTips(ItemStack stack, List<Text> tooltip) {
        String str = stack.getItem().getTranslationKey().toLowerCase(Locale.ROOT).substring(11);
        String translationKey = String.format("tooltip_info_item.mcdar.%s_", str);
        int i = 1;
        while (I18n.hasTranslation(translationKey + i)) {
            tooltip.add(Text.translatable(translationKey + i).formatted(Formatting.ITALIC));
            i++;
        }
    }

    public static void mcdar$dropItem(LivingEntity le, Item item) {
        mcdar$dropItem(le, item, 1);
    }

    public static void mcdar$dropItem(LivingEntity le, ItemStack itemStack) {
        ItemEntity it = new ItemEntity(
                le.world, le.getX(), le.getY(), le.getZ(),
                itemStack);
        le.world.spawnEntity(it);
    }

    public static void mcdar$dropItem(LivingEntity le, Item item, int amount) {
        mcdar$dropItem(le, new ItemStack(item, amount));
    }

    public static float getRemainingCD(PlayerEntity player, Item item) {
        return player.getItemCooldownManager().getCooldownProgress(item, 0);
    }
}
