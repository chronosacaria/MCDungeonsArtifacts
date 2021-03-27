package chronosacaria.mcdar.artefacts;

import chronosacaria.mcdar.Mcdar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class GhostCloakItem extends ArtefactItem{
    public GhostCloakItem(Settings settings, String id) {
        super(settings);
        Registry.register(Registry.ITEM, new Identifier(Mcdar.MOD_ID, id), this);
    }

    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);

        StatusEffectInstance invisibility = new StatusEffectInstance(StatusEffects.INVISIBILITY, 60);
        StatusEffectInstance glowing = new StatusEffectInstance(StatusEffects.GLOWING, 60);
        StatusEffectInstance resistance = new StatusEffectInstance(StatusEffects.RESISTANCE, 60, 3);
        StatusEffectInstance swiftness = new StatusEffectInstance(StatusEffects.SPEED, 60);

        user.addStatusEffect(invisibility);
        user.addStatusEffect(glowing);
        user.addStatusEffect(resistance);
        user.addStatusEffect(swiftness);

        if (!user.isCreative()){
            itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));
        }
        user.getItemCooldownManager().set(this, 120);
        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }
}
