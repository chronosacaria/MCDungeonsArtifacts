package chronosacaria.mcdar.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class BuzzyNestBeeEntity extends BeeEntity {

    private Entity summoner;

    public BuzzyNestBeeEntity(EntityType<? extends BuzzyNestBeeEntity> type, World world){
        super(EntityType.BEE, world);
    }

    public static DefaultAttributeContainer.Builder createBuzzyNestBeeAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 15.0D)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 2.5D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 2.5D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 48.0D);
    }

    public void setSummoner(Entity player){
        summoner = player;
    }

    protected void mobTick(){
        if (summoner instanceof PlayerEntity){
            if (((PlayerEntity)summoner).getAttacker() != null){
                this.setBeeAttacker(((PlayerEntity)summoner).getAttacker());
            }

            if (((PlayerEntity)summoner).getAttacking() != null){
                this.setBeeAttacker(((PlayerEntity)summoner).getAttacking());
            }
        }
        super.mobTick();
    }

    private boolean setBeeAttacker(LivingEntity attacker){
        if (attacker.equals(summoner))
            return false;
        setAttacker(attacker);
        return true;
    }

    public boolean tryAttack(Entity target){
        if (target.equals(summoner) || this.hasStung())
            return false;
        return super.tryAttack(target);
    }
}
