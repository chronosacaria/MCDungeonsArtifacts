package chronosacaria.mcdar.artefacts;

import chronosacaria.mcdar.artefacts.beaconcomponents.BaseBeaconItem;
import chronosacaria.mcdar.artefacts.beaconcomponents.BeaconBeamColour;
import chronosacaria.mcdar.enums.DamagingArtefactID;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class CorruptedBeaconItem extends BaseBeaconItem {
    public CorruptedBeaconItem(DamagingArtefactID artefactID) {
        super(artefactID);
    }

    public static final BeaconBeamColour CORRUPTED_BEACON_BEAM_COLOUR = new BeaconBeamColour(
            (short) 128,
            (short) 0,
            (short) 128,
            (short) 255,
            (short) 255,
            (short) 255);

    @Override
    public boolean canFireBeacon(PlayerEntity playerEntity, ItemStack itemStack) {
        return playerEntity.totalExperience >= 40F;
    }

    @Override
    protected BeaconBeamColour getBeamColour() {
        return CORRUPTED_BEACON_BEAM_COLOUR;
    }

    @Override
    protected boolean consumeTick(PlayerEntity playerEntity) {
        if (playerEntity == null) return false;
        return true;
    }
}
