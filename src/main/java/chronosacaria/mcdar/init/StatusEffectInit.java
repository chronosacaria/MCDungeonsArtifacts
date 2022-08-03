package chronosacaria.mcdar.init;

import chronosacaria.mcdar.statuseffect.CharmedStatusEffect;
import chronosacaria.mcdar.statuseffect.ShieldingStatusEffect;
import chronosacaria.mcdar.statuseffect.SoulProtectionStatusEffect;
import chronosacaria.mcdar.statuseffect.StunnedStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class StatusEffectInit {
    public static StatusEffect CHARMED;
    public static StatusEffect SHIELDING;
    public static StatusEffect SOUL_PROTECTION;
    public static StatusEffect STUNNED;

    public static void init(){
        CHARMED = new CharmedStatusEffect(StatusEffectCategory.HARMFUL, 0xC7005B, "charmed");
        SHIELDING = new ShieldingStatusEffect(StatusEffectCategory.BENEFICIAL, 0x808080, "shielding");
        SOUL_PROTECTION = new SoulProtectionStatusEffect(StatusEffectCategory.BENEFICIAL, 0x2552a5, "soul_protection");
        STUNNED = new StunnedStatusEffect(StatusEffectCategory.HARMFUL, 0xFFFF00, "stunned");

    }
}
