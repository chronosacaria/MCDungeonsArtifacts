package chronosacaria.mcdar.statuseffect;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.goals.LoveMedallionAttackGoal;
import chronosacaria.mcdar.mixin.MobEntityAccessor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class CharmedStatusEffect extends StatusEffect {
    public CharmedStatusEffect(StatusEffectCategory statusEffectCategory, int color, String id) {
        super(statusEffectCategory, color);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(Mcdar.MOD_ID, id), this);
    }

    private static Goal inLoveIdiot;

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        if (entity instanceof MobEntity mobEntity) {
            inLoveIdiot = new LoveMedallionAttackGoal(mobEntity);
            ((MobEntityAccessor) mobEntity).targetSelector().add(0, inLoveIdiot);
        }
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        if (entity instanceof MobEntity mobEntity) {
            if (inLoveIdiot != null) {
                ((MobEntityAccessor) mobEntity).targetSelector().remove(inLoveIdiot);
            }
        }
        entity.damage(DamageSource.MAGIC, (float) attributes.getValue(EntityAttributes.GENERIC_ATTACK_DAMAGE));
    }
}
