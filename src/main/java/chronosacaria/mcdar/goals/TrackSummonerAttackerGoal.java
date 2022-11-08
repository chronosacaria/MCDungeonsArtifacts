package chronosacaria.mcdar.goals;

import chronosacaria.mcdar.api.interfaces.Summonable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.EnumSet;

public class TrackSummonerAttackerGoal extends TrackTargetGoal {
    private final TameableEntity tameable;
    private final Summonable summonable;
    private LivingEntity attacker;
    private int lastAttackedTime;

    public TrackSummonerAttackerGoal(TameableEntity tameable) {
        super(tameable, false);
        this.tameable = tameable;
        this.summonable = (Summonable) tameable;
        this.setControls(EnumSet.of(Control.TARGET));
    }

    public boolean canStart() {
        if (summonable != null && summonable.getSummoner() instanceof PlayerEntity summoner && !this.tameable.isSitting()) {
            this.attacker = summoner.getAttacker();
            int i = summoner.getLastAttackedTime();
            return i != this.lastAttackedTime && this.canTrack(this.attacker, TargetPredicate.DEFAULT) && this.tameable.canAttackWithOwner(this.attacker, summoner);
        } else {
            return false;
        }
    }

    public void start() {
        if (summonable != null && summonable.getSummoner() instanceof PlayerEntity summoner) {
            this.mob.setTarget(this.attacker);
            this.lastAttackedTime = summoner.getLastAttackedTime();

            super.start();
        }
    }
}