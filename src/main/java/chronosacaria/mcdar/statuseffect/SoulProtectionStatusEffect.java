package chronosacaria.mcdar.statuseffect;

import chronosacaria.mcdar.Mcdar;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SoulProtectionStatusEffect extends StatusEffect {

    public SoulProtectionStatusEffect(StatusEffectType type, int color, String id) {
        super(type, color);
        Registry.register(Registry.STATUS_EFFECT, new Identifier(Mcdar.MOD_ID, id), this);
    }
}
