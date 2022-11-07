package chronosacaria.mcdar.artifacts.beacon;

import chronosacaria.mcdar.enums.DamagingArtifactID;

public class CorruptedBeaconItem extends SoulBeaconItem{

    public static final BeaconBeamColor CORRUPTED_BEACON_BEAM_COLOR =
            new BeaconBeamColor((short) 128, (short) 0, (short) 128, (short) 255, (short) 255, (short) 255);

    public CorruptedBeaconItem(DamagingArtifactID artefactID) {
        super(artefactID);
    }

    @Override
    public BeaconBeamColor getBeamColor() {
        return CORRUPTED_BEACON_BEAM_COLOR;
    }



}
