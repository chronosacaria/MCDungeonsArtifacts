package mcdar.chronosacaria.artefacts;

import mcdar.chronosacaria.Mcdar;
import mcdar.chronosacaria.api.AOEHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class CorruptedSeedsItem extends ArtefactItem{
    public CorruptedSeedsItem(Settings settings, String id) {
        super(settings);
        Registry.register(Registry.ITEM, new Identifier(Mcdar.MOD_ID, id), this);
    }

    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);

        AOEHelper.poisonAndSlowNearbyEnemies(world, user);

        if (!user.isCreative()){
            itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));
        }
        user.getItemCooldownManager().set(this, 100);
        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }
}
