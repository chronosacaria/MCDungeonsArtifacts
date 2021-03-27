package chronosacaria.mcdar.artefacts;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.api.AOECloudHelper;
import chronosacaria.mcdar.api.AOEHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class HarvesterItem extends ArtefactItem{
    public HarvesterItem(Settings settings, String id) {
        super(settings);
        Registry.register(Registry.ITEM, new Identifier(Mcdar.MOD_ID, id), this);
    }

    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);

        if (user.totalExperience >= 40 || user.isCreative()){
            if (!user.isCreative()){
                user.addExperience(-40);
            }
            user.world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_GENERIC_EXPLODE,
                    SoundCategory.PLAYERS, 1.0F, 1.0F);
            AOECloudHelper.spawnExplosionCloud(user, user, 3.0f);
            AOEHelper.causeMagicExplosionAttack(user, user, 15, 3.0F);

            if (!user.isCreative()){
                itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));
            }
            user.getItemCooldownManager().set(this, 20);
        }
        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }
}
