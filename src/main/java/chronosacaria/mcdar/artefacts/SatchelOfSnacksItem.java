package chronosacaria.mcdar.artefacts;

import chronosacaria.mcdar.api.AbilityHelper;
import chronosacaria.mcdar.api.EnchantmentHelper;
import chronosacaria.mcdar.enums.DefenciveArtefactID;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class SatchelOfSnacksItem extends ArtefactDefenciveItem{
    public SatchelOfSnacksItem(DefenciveArtefactID artefactID) {
        super(artefactID);
    }

    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);
        Item snackToDrop =
                AbilityHelper.SATCHEL_OF_SNACKS_LIST.get(user.getRandom().nextInt(AbilityHelper.SATCHEL_OF_SNACKS_LIST.size()));

        ItemEntity snackDrop = new ItemEntity(user.world, user.getX(), user.getY(), user.getZ(),
                new ItemStack(snackToDrop));
        user.world.spawnEntity(snackDrop);

        if (!user.isCreative()){
            itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));
        }
        EnchantmentHelper.cooldownHelper(user, this, 400);

        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        tooltip.add(Text.translatable("tooltip_info_item.mcdar.satchel_of_snacks_1").formatted(Formatting.ITALIC));
        tooltip.add(Text.translatable("tooltip_info_item.mcdar.satchel_of_snacks_2").formatted(Formatting.ITALIC));
    }
}
