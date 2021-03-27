package chronosacaria.mcdar.artefacts;

import chronosacaria.mcdar.Mcdar;
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

import static chronosacaria.mcdar.api.AOEHelper.weakenAndMakeNearbyEnemiesVulnerable;

public class GongOfWeakeningItem extends ArtefactItem{
    public GongOfWeakeningItem(Settings settings, String id) {
        super(settings);
        Registry.register(Registry.ITEM, new Identifier(Mcdar.MOD_ID, id), this);
    }

    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);
        World entityWorld = user.getEntityWorld();

        entityWorld.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.BLOCK_BELL_USE,SoundCategory.BLOCKS, 2.0F, 1.0F);
        entityWorld.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.BLOCK_BELL_RESONATE,SoundCategory.BLOCKS, 1.0F, 1.0F);

        weakenAndMakeNearbyEnemiesVulnerable(world, user);

        if (!user.isCreative()){
            itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));
        }
        user.getItemCooldownManager().set(this, 100);
        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }
}
