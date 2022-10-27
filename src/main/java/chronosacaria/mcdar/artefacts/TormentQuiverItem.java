package chronosacaria.mcdar.artefacts;

import chronosacaria.mcdar.api.CleanlinessHelper;
import chronosacaria.mcdar.enchants.EnchantID;
import chronosacaria.mcdar.enums.QuiverArtefactID;
import chronosacaria.mcdar.init.EnchantsRegistry;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class TormentQuiverItem extends ArtefactQuiverItem{
    public TormentQuiverItem(QuiverArtefactID artefactID) {
        super(artefactID);
    }

    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);
        if (user.totalExperience >= 20 || user.isCreative()){

            int cooldownLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(EnchantID.COOLDOWN), user);
            user.getItemCooldownManager().set(this, 600 * (cooldownLevel + 1));

            if (!user.isCreative()) {
                user.addExperience(-20);
                itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));
            }
        }
        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        CleanlinessHelper.createLoreTTips(stack, tooltip);
    }
}