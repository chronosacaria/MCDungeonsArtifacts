package chronosacaria.mcdar.goals;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Tameable;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.EnumSet;

public class TrackSummonerAttackerGoal<T extends PathAwareEntity & Tameable> extends TrackTargetGoal {
    private final T summonedEntity;
    private LivingEntity attacker;
    private int lastAttackedTime;

    public TrackSummonerAttackerGoal(T summonedEntity) {
        super(summonedEntity, false);
        this.summonedEntity = summonedEntity;
        this.setControls(EnumSet.of(Control.TARGET));
    }

    @SuppressWarnings("SimplifiableConditionalExpression") // Humans need to read this, IntelliJ...
    public boolean canStart() {
        if (summonedEntity != null && summonedEntity.getOwner() instanceof PlayerEntity summoner) {
            if (summonedEntity instanceof TameableEntity tameableSummonedEntity && tameableSummonedEntity.isSitting())
                return false;
            this.attacker = summoner.getAttacker();
            int i = summoner.getLastAttackedTime();
            return i != this.lastAttackedTime
                    && this.canTrack(this.attacker, TargetPredicate.DEFAULT)
                    && (summonedEntity instanceof TameableEntity tameableSummonedEntity)
                        ? tameableSummonedEntity.canAttackWithOwner(this.attacker, summoner)
                        : true;
        } else {
            return false;
        }
    }

    public void start() {
        if (summonedEntity != null && summonedEntity.getOwner() instanceof PlayerEntity summoner) {
            this.mob.setTarget(this.attacker);
            this.lastAttackedTime = summoner.getLastAttackedTime();
            super.start();
        }
    }
}