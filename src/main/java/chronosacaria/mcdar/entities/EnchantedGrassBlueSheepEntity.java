package chronosacaria.mcdar.entities;

import chronosacaria.mcdar.api.SummoningHelper;
import chronosacaria.mcdar.api.interfaces.Summonable;
import chronosacaria.mcdar.goals.FollowBlueSheepSummonerGoal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.DyeColor;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public class EnchantedGrassBlueSheepEntity extends SheepEntity implements Summonable {

    protected static final TrackedData<Optional<UUID>> SUMMONER_UUID;

    public EnchantedGrassBlueSheepEntity(EntityType<? extends EnchantedGrassBlueSheepEntity> type, World world) {
        super(EntityType.SHEEP, world);
        this.setColor(DyeColor.BLUE);
    }

    public static DefaultAttributeContainer.Builder createEnchantedBlueSheepEntityAttributes(){
        return HostileEntity.createHostileAttributes()
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
        EatGrassGoal eatGrassGoal = new EatGrassGoal(this);
        this.goalSelector.add(0, new SwimGoal(this));
        //this.goalSelector.add(1, new AnimalMateGoal(this, 1.0D));
        //this.goalSelector.add(3, new TemptGoal(this, 1.1D, Ingredient.ofItems(Items.WHEAT), false));
        //this.goalSelector.add(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.add(4, eatGrassGoal);
        this.goalSelector.add(5, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.add(6, new FollowBlueSheepSummonerGoal(this, this.getSummoner(), 1.0,
                this.getNavigation(), 90.0F, 3.0F));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this));
    }

    public void setSummonerUuid(@Nullable UUID uuid) {
        this.dataTracker.set(SUMMONER_UUID, Optional.ofNullable(uuid));
    }

    public Optional<UUID> getSummonerUuid(){
        return this.dataTracker.get(SUMMONER_UUID);
    }

    @Override
    public void setSummoner(Entity player) {
        this.setSummonerUuid(player.getUuid());
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound tag) {
        super.writeCustomDataToNbt(tag);
        if (getSummonerUuid().isPresent())
            tag.putUuid("SummonerUUID",getSummonerUuid().get());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound tag) {
        super.readCustomDataFromNbt(tag);
        UUID id = tag.getUuid("SummonerUUID");
        if (id != null)
            this.setSummonerUuid(id);
    }

    @Override
    public boolean tryAttack(Entity target) {
        return SummoningHelper.attackTarget(this, target, SoundEvents.ENTITY_SHEEP_AMBIENT, 8.0f);
    }

    @Override
    public void setAttacker(LivingEntity attacker){
        if (attacker != null && !attacker.equals(getSummoner()))
            super.setAttacker(attacker);
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        SummoningHelper.trackAndProtectSummoner(this);
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
