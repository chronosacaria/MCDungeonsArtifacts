package chronosacaria.mcdar.statuseffect;

import chronosacaria.mcdar.Mcdar;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class StunnedStatusEffect extends StatusEffect {
    public StunnedStatusEffect(StatusEffectCategory type, int color, String id) {
        super(type, color);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(Mcdar.MOD_ID, id), this);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier){
        return true;
    }

}
