package chronosacaria.mcdar.statuseffect;

import chronosacaria.mcdar.Mcdar;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class StunnedStatusEffect extends StatusEffect {

    public StunnedStatusEffect(StatusEffectCategory type, int color, String id) {
        super(type, color);
        Registry.register(Registry.STATUS_EFFECT, new Identifier(Mcdar.MOD_ID, id), this);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier){
        return true;
    }

}
