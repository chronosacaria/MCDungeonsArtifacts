package chronosacaria.mcdar.entities;

import chronosacaria.mcdar.api.SummoningHelper;
import chronosacaria.mcdar.api.interfaces.Summonable;
import chronosacaria.mcdar.goals.FollowSummonerGoal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Tameable;
import net.minecraft.entity.ai.goal.ProjectileAttackGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.passive.TraderLlamaEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.EntityView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class WonderfulWheatLlamaEntity extends TraderLlamaEntity implements Tameable, Summonable {

    @Nullable
    UUID ownerEntityUUID = null;

    public WonderfulWheatLlamaEntity(EntityType<? extends WonderfulWheatLlamaEntity> type, World world) {
        super(type, world);
    }

    public static DefaultAttributeContainer.Builder createLlamaAttributes() {
        return LlamaEntity.createLlamaAttributes().add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0D);
    }

    @Override
    protected void initGoals(){

        this.goalSelector.add(6, new FollowSummonerGoal<>(this, 1.0,
                this.getNavigation(), 90.0F, 3.0F));
        this.initCustomGoals();
    }

    protected void initCustomGoals(){
        this.goalSelector.add(3, new ProjectileAttackGoal(this, 1.25D, 40, 20.0F));
        this.targetSelector.add(2, new RevengeGoal(this));
    }

    @Override
    public void setSummoner(Entity player) {
        ownerEntityUUID = player.getUuid();
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound tag) {
        super.writeCustomDataToNbt(tag);
        if (getOwnerUuid() != null)
            tag.putUuid("SummonerUUID",getOwnerUuid());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound tag) {
        super.readCustomDataFromNbt(tag);
        UUID id = tag.getUuid("SummonerUUID");
        if (id != null)
            this.ownerEntityUUID = id;
    }

    @Override
    public void setAttacker(LivingEntity attacker){
        if (attacker != null && !attacker.equals(getOwner()))
            super.setAttacker(attacker);
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        SummoningHelper.mcdar$trackAndProtectSummoner(this);
    }

    @Nullable
    @Override
    public UUID getOwnerUuid() {
        return ownerEntityUUID;
    }

    @Override
    public EntityView method_48926() {
        return this.getEntityWorld();
    }
}
