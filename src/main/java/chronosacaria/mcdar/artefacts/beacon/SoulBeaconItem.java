package chronosacaria.mcdar.artefacts.beacon;

import chronosacaria.mcdar.enums.DamagingArtefactID;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public abstract class SoulBeaconItem extends AbstractBeaconItem{
    public SoulBeaconItem(DamagingArtefactID artefactID) {
        super(artefactID);
    }

    public boolean canFire(PlayerEntity playerEntity, ItemStack itemStack){
        SoulBeaconItem soulBeaconItem = itemStack.getItem() instanceof SoulBeaconItem ? ((SoulBeaconItem) itemStack.getItem()) : null;
        if (soulBeaconItem != null){
            return playerEntity.experienceLevel >= soulBeaconItem.getActivationCost(itemStack) || playerEntity.isCreative();
        }
        return false;
    }

    @Override
    protected boolean consumeTick(PlayerEntity playerEntity) {
        return consumeXP(playerEntity, XP_COST_PER_TICK);
    }

    public float getActivationCost(ItemStack stack){
        return AbstractBeaconItem.XP_COST_PER_TICK;
    }

    public boolean consumeXP(PlayerEntity playerEntity, float amount){
        if (playerEntity.experienceLevel < amount) return false;
        playerEntity.experienceLevel -= amount/4;
        return true;
    }
}
