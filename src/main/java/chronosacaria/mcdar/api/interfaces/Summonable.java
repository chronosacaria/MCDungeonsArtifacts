package chronosacaria.mcdar.api.interfaces;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public interface Summonable {
    void setSummoner (Entity player);
    LivingEntity getSummoner();
}
