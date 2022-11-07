package chronosacaria.mcdar.entities;

import chronosacaria.mcdar.api.interfaces.Summonable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public class BuzzyNestBeeEntity extends BeeEntity implements Summonable {

    protected static final TrackedData<Optional<UUID>> SUMMONER_UUID;

    public BuzzyNestBeeEntity(EntityType<? extends BuzzyNestBeeEntity> type, World world){
        super(type, world);
    }

    public static DefaultAttributeContainer.Builder createBuzzyNestBeeAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 15.0D)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 2.5D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 2.5D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 48.0D);
    }

    protected void initDataTracker(){
        super.initDataTracker();
        this.dataTracker.startTracking(SUMMONER_UUID, Optional.empty());
    }

    public Optional<UUID> getSummonerUuid(){
        return this.dataTracker.get(SUMMONER_UUID);
    }

    public void setSummonerUuid(@Nullable UUID uuid) {
        this.dataTracker.set(SUMMONER_UUID, Optional.ofNullable(uuid));
    }

    @Override
    public void setSummoner(Entity player) {
        this.setSummonerUuid(player.getUuid());
    }

    public LivingEntity getSummoner(){
        try {
            Optional<UUID> uUID = this.getSummonerUuid();
            return uUID.map(value -> this.world.getPlayerByUuid(value)).orElse(null);
        } catch (IllegalArgumentException var2){
            return null;
        }
    }

    protected void mobTick(){
        if (getSummoner() instanceof PlayerEntity summoner){
            if (summoner.getAttacking() != null)
                this.setBeeAttacker(summoner.getAttacking());
            else if (summoner.getAttacker() != null)
                this.setBeeAttacker(summoner.getAttacker());
        }
        super.mobTick();
    }

    private void setBeeAttacker(LivingEntity attacker){
        if (!attacker.equals(getSummoner()))
            super.setAttacker(attacker);
    }

    public boolean tryAttack(Entity target){
        if (target.equals(getSummoner()) || this.hasStung())
            return false;
        return super.tryAttack(target);
    }

    static {
        SUMMONER_UUID = DataTracker.registerData(BuzzyNestBeeEntity.class, TrackedDataHandlerRegistry.OPTIONAL_UUID);
    }
}
