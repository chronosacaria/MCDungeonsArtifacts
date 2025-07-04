package dev.timefall.mcdar.artifacts;

import dev.timefall.mcdar.api.CleanlinessHelper;
import dev.timefall.mcdar.artifacts.artifact_types.ArtifactDefensiveItem;
import dev.timefall.mcdar.config.McdarArtifactsStatsConfig;
import dev.timefall.mcdar.effect.EnchantmentEffects;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.potion.Potions;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TomeOfDuplicationItem extends ArtifactDefensiveItem {
    public TomeOfDuplicationItem() {
        super(McdarArtifactsStatsConfig.CONFIG.mcdar$getDefensiveArtifactStats().TOME_OF_DUPLICATION_STATS);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        int maxCooldownEnchantmentTime = McdarArtifactsStatsConfig.CONFIG.mcdar$getDefensiveArtifactStats().TOME_OF_DUPLICATION_STATS.mcdar$getMaxCooldownEnchantmentTime();
        int modifiedCooldownEnchantmentTime = EnchantmentEffects.cooldownEffect(maxCooldownEnchantmentTime, user, world);
        int experienceDrain = McdarArtifactsStatsConfig.CONFIG.mcdar$getDefensiveArtifactStats().TOME_OF_DUPLICATION_STATS.mcdar$getExperienceDrain();

        if (user.totalExperience
                >= experienceDrain
                || user.isCreative()) {

            if (user.totalExperience >= experienceDrain || user.isCreative()) {
                Item itemToDrop = TOME_OF_DUPLICATION_LIST.get(user.getRandom().nextInt(TOME_OF_DUPLICATION_LIST.size()));

                if (CleanlinessHelper.percentToOccur(75)) {
                    CleanlinessHelper.mcdar$dropItem(user, itemToDrop);
                } else {
                    CleanlinessHelper.mcdar$dropItem(user, itemToDrop, 2);
                }

                if (!user.isCreative()) {
                    EquipmentSlot equipmentSlot = hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
                    itemStack.damage(1, user, equipmentSlot);
                }

                EnchantmentEffects.mcdar$cooldownHelper(
                        user,
                        this,
                        modifiedCooldownEnchantmentTime
                );
            }
        }
        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext tooltipContext, List<Text> tooltip, TooltipType type) {
        CleanlinessHelper.createLoreTTips(stack, tooltip);
    }

    public static final List<Item> TOME_OF_DUPLICATION_LIST = Collections.unmodifiableList(Arrays.asList(
            Items.APPLE, Items.BREAD, Items.COOKED_SALMON, Items.COOKED_PORKCHOP, Items.COOKED_MUTTON,
            Items.COOKED_COD, Items.COOKED_COD, Items.COOKED_RABBIT, Items.COOKED_CHICKEN, Items.COOKED_BEEF,
            Items.MELON_SLICE, Items.CARROT, Items.GOLDEN_CARROT, Items.GOLDEN_APPLE, Items.BAKED_POTATO, Items.TNT,
            PotionContentsComponent.createStack(Items.POTION, Potions.STRENGTH).getItem(),
            PotionContentsComponent.createStack(Items.POTION, Potions.SWIFTNESS).getItem(),
            PotionContentsComponent.createStack(Items.POTION, Potions.INVISIBILITY).getItem()));
}