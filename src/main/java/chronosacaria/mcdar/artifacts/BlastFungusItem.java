package chronosacaria.mcdar.artifacts;

import chronosacaria.mcdar.api.CleanlinessHelper;
import chronosacaria.mcdar.api.EnchantmentHelper;
import chronosacaria.mcdar.effects.ArtifactEffects;
import chronosacaria.mcdar.enums.DamagingArtifactID;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;


public class BlastFungusItem extends ArtifactDamagingItem{
    public BlastFungusItem() {
            super(DamagingArtifactID.BLAST_FUNGUS);
    }

    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);

        CleanlinessHelper.playCenteredSound(user, SoundEvents.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F);
        ArtifactEffects.causeBlastFungusExplosions(user, 5, 4);
        if (!user.isCreative()){
            itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));
        }
        EnchantmentHelper.cooldownHelper(user, this, 120);

        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        CleanlinessHelper.createLoreTTips(stack, tooltip);
    }
}
