package chronosacaria.mcdar.artefacts.beacon;

import chronosacaria.mcdar.enums.DamagingArtefactID;

public class CorruptedBeaconItem extends SoulBeaconItem{

    public static final BeaconBeamColor CORRUPTED_BEACON_BEAM_COLOR =
            new BeaconBeamColor((short) 128, (short) 0, (short) 128, (short) 255, (short) 255, (short) 255);

    public CorruptedBeaconItem(DamagingArtefactID artefactID) {
        super(artefactID);
    }

    @Override
    public BeaconBeamColor getBeamColor() {
        return CORRUPTED_BEACON_BEAM_COLOR;
    }



}
