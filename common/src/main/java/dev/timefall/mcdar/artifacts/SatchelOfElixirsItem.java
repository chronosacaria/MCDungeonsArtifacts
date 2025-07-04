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

import java.util.List;

public class SatchelOfElixirsItem extends ArtifactDefensiveItem {
    public SatchelOfElixirsItem() {
        super(McdarArtifactsStatsConfig.CONFIG.mcdar$getDefensiveArtifactStats().SATCHEL_OF_ELIXIRS_STATS);
    }

    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);
        int maxCooldownEnchantmentTime = McdarArtifactsStatsConfig.CONFIG.mcdar$getDefensiveArtifactStats().SATCHEL_OF_ELIXIRS_STATS.mcdar$getMaxCooldownEnchantmentTime();
        int modifiedCooldownEnchantmentTime = EnchantmentEffects.cooldownEffect(maxCooldownEnchantmentTime, user, world);

        ItemStack potionToDrop = SATCHEL_OF_ELIXIRS_LIST.get(user.getRandom().nextInt(SATCHEL_OF_ELIXIRS_LIST.size()));

        CleanlinessHelper.mcdar$dropItem(user, potionToDrop);

        return CleanlinessHelper.mcdar$useAndDamageArtifact(user, hand, itemStack, modifiedCooldownEnchantmentTime);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext tooltipContext, List<Text> tooltip, TooltipType type){
        CleanlinessHelper.createLoreTTips(stack, tooltip);
    }

    public static final List<ItemStack> SATCHEL_OF_ELIXIRS_LIST = List.of(
            PotionContentsComponent.createStack(Items.POTION, Potions.STRENGTH),
            PotionContentsComponent.createStack(Items.POTION, Potions.SWIFTNESS),
            PotionContentsComponent.createStack(Items.POTION, Potions.INVISIBILITY));

    //recommend making into a tag
}
