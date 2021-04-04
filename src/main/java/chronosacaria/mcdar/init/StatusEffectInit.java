package chronosacaria.mcdar.init;

import chronosacaria.mcdar.statuseffect.SoulProtectionStatusEffect;
import chronosacaria.mcdar.statuseffect.StunnedStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;

public class StatusEffectInit {
    public static StatusEffect SOUL_PROTECTION;
    public static StatusEffect STUNNED;

    public static void init(){
        SOUL_PROTECTION = new SoulProtectionStatusEffect(StatusEffectType.BENEFICIAL, 0x2552a5, "soul_protection");
        STUNNED = new StunnedStatusEffect(StatusEffectType.HARMFUL, 0xFFFF00, "stunned");

    }
}
