package chronosacaria.mcdar.entities;

import chronosacaria.mcdar.goals.FollowGolemSummonerGoal;
import chronosacaria.mcdar.goals.FollowLlamaSummonerGoal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.ProjectileAttackGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.World;

import java.util.Optional;
import java.util.UUID;

public class WonderfulWheatLlamaEntity extends LlamaEntity {

    protected static final TrackedData<Optional<UUID>> SUMMONER_UUID;


    public WonderfulWheatLlamaEntity(EntityType<? extends WonderfulWheatLlamaEntity> type, World world) {
        super(EntityType.LLAMA, world);
        //this.setTamed(true);
    }

    public static DefaultAttributeContainer.Builder createLlamaAttributes() {
        return createAbstractDonkeyAttributes().add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0D);
    }

    protected void initDataTracker(){
        super.initDataTracker();
        this.dataTracker.startTracking(SUMMONER_UUID, Optional.empty());
    }

    @Override
    protected void initGoals(){

        this.goalSelector.add(6, new FollowLlamaSummonerGoal(this, this.getSummoner(), this.world, 1.0,
                this.getNavigation(), 90.0F, 3.0F, true));
        this.initCustomGoals();
    }

    protected void initCustomGoals(){
        this.goalSelector.add(3, new ProjectileAttackGoal(this, 1.25D, 40, 20.0F));
        this.targetSelector.add(2, (new RevengeGoal(this, new Class[0])));
    }

    private void setSummonerUuid (UUID uuid){
        this.dataTracker.set(SUMMONER_UUID, Optional.ofNullable(uuid));
    }

    public Optional<UUID> getSummonerUuid(){
        return this.dataTracker.get(SUMMONER_UUID);
    }

    public void setSummoner (Entity player){
        this.setSummonerUuid(player.getUuid());
    }

    public void writeCustomDateToTag(CompoundTag tag){
        super.writeCustomDataToTag(tag);
        tag.putUuid("SummonerUUID",getSummonerUuid().get());
    }

    public void readCustomDataFromTag(CompoundTag tag){
        super.readCustomDataFromTag(tag);
        UUID id;
        if (tag.contains("SummonerUUID")){
            id = tag.getUuid("SummonerUUID");
        } else {
            id = tag.getUuid("SummonerUUID");
        }
        if (id != null){
            this.setSummonerUuid(tag.getUuid("SummonerUUID"));
        }
    }

    @Override
    public void setAttacker(LivingEntity attacker){
        if (attacker == getSummoner()) {

        } else {
            super.setAttacker(attacker);
        }
    }

    @Override
    public void tickMovement(){
        if (this.isAlive()){
            if (getSummoner() != null){
                if (getSummoner().getAttacker() != null){
                    this.setTarget(getSummoner().getAttacker());
                } else if (getSummoner().getAttacking() != null && getSummoner().getAttacking() != this) {
                    this.setTarget(getSummoner().getAttacking());
                }
            } else {

            }

        }
        super.tickMovement();
    }

    public LivingEntity getSummoner(){
        try {
            Optional<UUID> uUID = this.getSummonerUuid();
            return uUID.map(value -> this.world.getPlayerByUuid(value)).orElse(null);
        } catch (IllegalArgumentException var2){
            return null;
        }
    }

    static {
        SUMMONER_UUID = DataTracker.registerData(WonderfulWheatLlamaEntity.class, TrackedDataHandlerRegistry.OPTIONAL_UUID);
    }
}
