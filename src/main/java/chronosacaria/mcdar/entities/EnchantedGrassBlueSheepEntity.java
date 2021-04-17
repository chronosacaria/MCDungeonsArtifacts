package chronosacaria.mcdar.entities;

import chronosacaria.mcdar.api.interfaces.Summonable;
import chronosacaria.mcdar.goals.FollowBlueSheepSummonerGoal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.EatGrassGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.DyeColor;
import net.minecraft.world.World;

import java.util.Optional;
import java.util.UUID;

public class EnchantedGrassBlueSheepEntity extends SheepEntity implements Summonable {

    protected static final TrackedData<Optional<UUID>> SUMMONER_UUID;
    private EatGrassGoal eatGrassGoal;

    public EnchantedGrassBlueSheepEntity(EntityType<? extends EnchantedGrassBlueSheepEntity> type, World world) {
        super(EntityType.SHEEP, world);
        this.setColor(DyeColor.BLUE);
    }

    public static DefaultAttributeContainer.Builder createEnchantedBlueSheepEntityAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 8.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 24.0D);
    }

    protected void initDataTracker(){
        super.initDataTracker();
        this.dataTracker.startTracking(SUMMONER_UUID, Optional.empty());
    }

    @Override
    protected void initGoals(){
        this.eatGrassGoal = new EatGrassGoal(this);
        this.goalSelector.add(6, new FollowBlueSheepSummonerGoal(this, this.getSummoner(), this.world, 1.0,
                this.getNavigation(), 90.0F, 3.0F, true));
        //this.initCustomGoals();
    }

   protected void initCustomGoals(){
       this.goalSelector.add(1, new MeleeAttackGoal(this, 1.0D, true));
       this.targetSelector.add(2, (new RevengeGoal(this, new Class[0])));
   }

    private void setSummonerUuid (UUID uuid){
        this.dataTracker.set(SUMMONER_UUID, Optional.ofNullable(uuid));
    }

    public Optional<UUID> getSummonerUuid(){
        return this.dataTracker.get(SUMMONER_UUID);
    }

    @Override
    public void setSummoner(Entity player) {
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
    public boolean tryAttack(Entity target) {
        boolean bl = target.damage(DamageSource.mob(this),
                (float) this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE));
        if (bl) {
            this.dealDamage(this, target);
            this.playSound(SoundEvents.ENTITY_SHEEP_AMBIENT, 1f, 1f);
        }

        return bl;
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
            }
        }
        super.tickMovement();
    }

    @Override
    protected void mobTick(){

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
        SUMMONER_UUID = DataTracker.registerData(EnchantedGrassBlueSheepEntity.class, TrackedDataHandlerRegistry.OPTIONAL_UUID);
    }
}
