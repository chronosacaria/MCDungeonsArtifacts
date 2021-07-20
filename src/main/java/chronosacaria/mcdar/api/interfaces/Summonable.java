package chronosacaria.mcdar.api.interfaces;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public interface Summonable {
    Optional<UUID> getSummonerUuid();
    void setSummonerUuid(@Nullable UUID uuid);
    void setSummoner (Entity player);
    LivingEntity getSummoner();
}
